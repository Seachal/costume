/**
 * Copyright (C) 2014-2016, hrfax and/or its affiliates. All rights reserved. hrfax
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.http;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.DeadLetterActorRef;
import akka.actor.Scheduler;
import akka.actor.UntypedActor;
import costumetrade.common.akka.ActorSystemContext;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.duration.Duration;

/**
 * @author dante
 * @Date 2016年11月14日
 */
public class HttpWorker extends UntypedActor {

	private static final Logger logger = LoggerFactory.getLogger(HttpWorker.class);

	private static Scheduler scheduler = ActorSystemContext.get().scheduler();

	private static ExecutionContextExecutor dispatcher = ActorSystemContext.get().dispatcher();

	@Override
	public void onReceive(Object msg) throws Exception {

		HttpMessage message = (HttpMessage) msg;
		try {
			HttpResponse httpResponse = this.sendRequest(message);
			returnMessage(httpResponse);
		} catch (NoHttpResponseException | ConnectTimeoutException | SocketTimeoutException e) {
			if (message.needRetry()) {
				logger.info("请求失败,不需要重试或者达到最大重试次数,url:{}", message.getUrl());
				returnMessage(new HttpResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR,null,message.getCallbackData()));
				return;
			}
			message.retry();
			scheduler.scheduleOnce(Duration.create(message.getInterval(), TimeUnit.SECONDS), getSelf(),
					message, dispatcher, getSender());
		} catch (Exception e) {
			logger.error("请求失败", e);
			returnMessage(new HttpResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR,null,message.getCallbackData()));
		}
	}

	/**
	 *  @param httpResponse
	 */
	private void returnMessage(HttpResponse httpResponse) {
		ActorRef sender = getSender();
		if (!(sender instanceof DeadLetterActorRef)) {
			sender.tell(httpResponse, ActorRef.noSender());
		}
	}

	protected HttpResponse sendRequest(HttpMessage message) throws ClientProtocolException, IOException {
		return HttpClientUtilsWrapper.doPost(message);
	}
	
	

}
