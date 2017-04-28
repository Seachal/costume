/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.verify;

/**
 * @author zhouyq
 * @Date 2017年3月16日
 */
public interface ICaptchaService {
	
	/**
	 * 保存验证码
	 *  @param businessKey 业务key
	 *  @param code 后台生成的验证码
	 */
	public void save(String businessKey,String code);
	
	/**
	 * 验证验证码
	 *  @param businessKey 业务key
	 *  @param code 前台提交的验证码
	 *  @return
	 */
	public boolean valid(String businessKey,String code);
}
