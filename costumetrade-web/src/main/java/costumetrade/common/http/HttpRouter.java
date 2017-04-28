/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.http;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RandomPool;
import costumetrade.common.akka.ActorSystemContext;

/**
 * @author zhouyq
 * @Date 2017年1月13日
 */
public class HttpRouter{

	private static ActorRef router;

	static {
		router = ActorSystemContext.get().actorOf(new RandomPool(10).props(Props.create(HttpWorker.class)),
				"HttpRouter");
	}
	
	public static void tell(HttpMessage message,ActorRef sender){
		router.tell(message, sender);
	}

	public static ActorRef getInstance() {

		return router;
	}

}
