/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.verify;

/**
 * @author zhouyq
 * @Date 2017年4月4日
 */
public class MobileVerificationCodeErrorException extends RuntimeException {
	
	private static final long serialVersionUID = -3502263131144432593L;

	public MobileVerificationCodeErrorException() {
        super("手机验证码错误");
    }
	
	public MobileVerificationCodeErrorException(String message) {
        super(message);
    }
	
	public MobileVerificationCodeErrorException(String message, Throwable cause) {
        super(message,cause);
    }
}
