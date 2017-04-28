/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.event;

import java.io.Serializable;
import java.util.UUID;

import costumetrade.common.login.LoginContext;
import costumetrade.common.login.domain.LoginInfo;

/**d
 * 业务事件
 * @author zhouyq
 * @Date 2017年1月6日
 */
public abstract class BusiEvent implements Serializable {

	private static final long serialVersionUID = 7099057708183571937L;

	public final String uuid;
	
	public final long timestamp;
	
	private final LoginInfo loginInfo;

	public BusiEvent() {
		
		this.uuid = UUID.randomUUID().toString();
		this.timestamp = System.currentTimeMillis();
		this.loginInfo = LoginContext.get();
	}
	
	protected final LoginInfo getLoginInfo() {
		return this.loginInfo;
	}

}
