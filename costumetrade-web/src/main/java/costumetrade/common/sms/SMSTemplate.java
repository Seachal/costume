/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.sms;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author zhouyq
 * @Date 2017年3月16日
 */
public class SMSTemplate {

	public static final String RETRIVE_PASSWORD_BUSSINESS = "retrivePassword";
	public static final String BIND_MOBILE_BUSSINESS = "bindMobile";
	
	public static final String UNBIND_MOBILE_BUSSINESS = "unBindMobile";

	public static final Map<String, String> templates = new HashMap<>();

	static {
		templates.put("regist", "【惠融金服】您正在注册惠融金服平台，验证码是：{0}");
		templates.put(RETRIVE_PASSWORD_BUSSINESS, "【惠融金服】您正在找回惠融金服平台密码，验证码是：{0}");
		templates.put(BIND_MOBILE_BUSSINESS, "【惠融金服】您正在绑定手机号，验证码是：{0}");
		templates.put(UNBIND_MOBILE_BUSSINESS, "【惠融金服】您正在解绑手机号，验证码是：{0}");
		templates.put("cutomerMaterialsDownload", "【惠融金服】您正在申请客户资料下载，验证码是：{0}");
		templates.put("bankInterfaceUnvaliable", "【惠融金服】您的银行接口服务器暂时不可用，请及时处理!");
		templates.put("stageServerUnvaliable", "【惠融金服】银行分期业务服务器暂时不可用，请知悉!");
	}

	public static String getTemplate(String key) {
		return templates.get(key);
	}
}
