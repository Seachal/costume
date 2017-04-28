/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.event;

import akka.actor.ActorRef;
import costumetrade.common.akka.ActorSystemContext;

/**
 * 业务时间总线
 * @author zhouyq
 * @Date 2017年1月6日
 */
public class BusiEventBus {

	public static void publish(BusiEvent event){
		ActorSystemContext.getEventStream().publish(event);
	}
	
	public static void subscribe(ActorRef actorRef,Class<? extends BusiEvent> event){
		ActorSystemContext.getEventStream().subscribe(actorRef, event);
	}
	
}
