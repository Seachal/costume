/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.conf;

/**
 * @author zhouyq
 * @Date 2017年1月16日
 */
public class IllegalConfigurationException extends RuntimeException {

	private static final long serialVersionUID = -4848748063944126955L;
	
	public IllegalConfigurationException() {
        super("配置错误");
    }
	
	public IllegalConfigurationException(String message) {
        super(message);
    }
	
	public IllegalConfigurationException(String message, Throwable cause) {
        super(message,cause);
    }

}
