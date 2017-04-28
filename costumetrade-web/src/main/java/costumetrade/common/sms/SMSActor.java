/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.sms;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.Scheduler;
import akka.actor.UntypedActor;
import costumetrade.common.akka.ActorSystemContext;
import costumetrade.common.conf.ConfigProperties;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.duration.Duration;

/**
 * 短信Actor
 * @author zhouyq
 * @Date 2017年3月16日
 */
public class SMSActor extends UntypedActor {

	private static final Logger LOGGER = LoggerFactory.getLogger(SMSActor.class);

	private static final Scheduler scheduler = ActorSystemContext.get().scheduler();

	private static final ExecutionContextExecutor dispatcher = ActorSystemContext.get().dispatcher();
	
	private static final ActorRef actor = ActorSystemContext.createActor(SMSActor.class, "SMSActor");
	
	
	public static void send(Sms sms){
		actor.tell(sms, ActorRef.noSender());
	}

	private void doSend(Sms sms) {

		String ecodingContent = null;
		try {
			ecodingContent = URLEncoder.encode(sms.content, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException ignore) {
		}

		StringBuilder params = new StringBuilder();

		params.append("action=").append(ConfigProperties.getProperty("sms.action"))
		.append("&userid=").append(ConfigProperties.getProperty("sms.userid"))
		.append("&account=").append(ConfigProperties.getProperty("sms.account"))
		.append("&password=").append(ConfigProperties.getProperty("sms.password"))
		.append("&mobile=").append(sms.mobile)
		.append("&content=").append(ecodingContent);

		String xml = sendPostRequestByForm(ConfigProperties.getProperty("sms.url"), params.toString());
		boolean success = isOk(xml);
		if (!success) {
			if (sms.retryCount > 0) {
				scheduler.scheduleOnce(Duration.create(sms.interval, TimeUnit.SECONDS), getSelf(),
						new Sms(sms.mobile, sms.content, sms.retryCount - 1), dispatcher, ActorRef.noSender());
			}

		}

	}

	private boolean isOk(String xml) {
		try {
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();// 获取根节点
			String returnstatus = root.elementTextTrim("returnstatus");
			return "Success".equals(returnstatus);
		} catch (Exception e) {
			LOGGER.error("解析短信发送结果返回数据失败");
		}

		return false;
	}

	/**
	 * 通过HttpURLConnection模拟post表单提交
	 */
	private String sendPostRequestByForm(String path, String params) {
		HttpURLConnection conn = null;
		InputStream inStream = null;
		try {
			URL url = new URL(path);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");// 提交模式
			conn.setDoOutput(true);// 是否输入参数
			conn.setDoInput(true);
			conn.getOutputStream().write(params.getBytes());// 输入参数
			inStream = conn.getInputStream();
			return IOUtils.toString(inStream, StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new RuntimeException("发送短信失败", e);
		} finally {
			IOUtils.closeQuietly(inStream);
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	@Override
	public void onReceive(Object obj) throws Exception {
		Sms sms = (Sms) obj;
		try {
			doSend(sms);
		} catch (Exception e) {
			LOGGER.error("发送短信失败", e);
		}
	}

	public static class Sms {

		public final String mobile;

		public final String content;

		public final Integer retryCount;

		public final Integer interval;

		/**
		 * @param mobile
		 *            手机号
		 * @param content
		 *            短信内容
		 */
		public Sms(String mobile, String content) {
			this.mobile = mobile;
			this.content = content;
			this.retryCount = 0;
			this.interval = 10;
		}

		/**
		 * @param mobile
		 *            手机号
		 * @param content
		 *            短信内容
		 * @param retryCount
		 *            重试次数
		 */
		public Sms(String mobile, String content, Integer retryCount) {
			this.mobile = mobile;
			this.content = content;
			this.retryCount = retryCount;
			this.interval = 10;
		}

	}
}
