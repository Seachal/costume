/**
 * Copyright (C) 2014-2016, hrfax and/or its affiliates. All rights reserved.
 * hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.akka;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.EventStream;
import scala.concurrent.duration.Duration;

/**
 * ActorSystem 一个系统只需要一个
 * 
 * @author dante
 * @Date 2016年8月26日
 */
@Component
public class ActorSystemContext implements DisposableBean {

	private static ActorSystem system = init();

	public static ActorSystem get() {
		return system;
	}

	public ActorSystemContext() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				system.shutdown();
			}
		});
	}

	private static ActorSystem init() {

		return ActorSystem.create("costumetrade");
	}

	public static EventStream getEventStream() {
		return get().eventStream();

	}

	@Override
	public void destroy() throws Exception {
		system.awaitTermination(Duration.create(10, TimeUnit.SECONDS));
	}

	public static <T> ActorRef createActor(Class<T> clazz) {

		return createActor(clazz, null);
	}

	public static <T> ActorRef createActor(Class<T> clazz, String name) {
		return createActor(Props.create(clazz), name);
	}

	public static <T> ActorRef createActor(Props props, String name) {
		ActorSystem system = ActorSystemContext.get();
		if (StringUtils.isEmpty(name)) {
			return system.actorOf(props);
		}
		return system.actorOf(props, name);
	}

}
