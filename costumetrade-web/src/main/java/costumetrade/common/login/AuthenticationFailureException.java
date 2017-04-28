/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.login;

/**
 * @author zhouyq
 * @Date 2017年1月22日
 */
public class AuthenticationFailureException extends RuntimeException{
	
	private static final long serialVersionUID = 974246868658662804L;

	public AuthenticationFailureException() {
        super();
    }

    public AuthenticationFailureException(String message) {
        super(message);
    }

  
    public AuthenticationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
