/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import akka.actor.UntypedActor;
import costumetrade.common.login.LoginContext;

/**
 * @author zhouyq
 * @Date 2017年1月6日
 */
public class BusiEventListener<E extends BusiEvent> extends UntypedActor {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusiEventListener.class);
	
	private ApplicationContext applicationContext;

	public BusiEventListener(ApplicationContext applicationContext) {
		
		this.applicationContext = applicationContext;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onReceive(Object event) throws Exception {
		E busiEvent = (E) event;
		try {
			LoginContext.put(busiEvent.getLoginInfo());
			onEvent(busiEvent);
		} catch (Exception e) {
			LOGGER.error("业务异常,事件" + busiEvent.getClass().getSimpleName(), e);
		} finally {
			LoginContext.remove();
		}

	}

	protected void onEvent(E event) {

	}

	protected <T> T getBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}

}
