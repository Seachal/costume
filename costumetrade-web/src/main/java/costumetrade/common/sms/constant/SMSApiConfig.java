/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年2月25日
 */
package costumetrade.common.sms.constant;

/**
 * 短信参数配置
 * @author liyb
 * @version SMSApiConfig.java,2016年2月25日 下午5:24:21
 */
public class SMSApiConfig {

    public static final String url = "http://sh2.ipyy.com/sms.aspx";
    /**
     * userid
     */
    public static final String userid = "2434";
    /**
     * 用户名
     */
    public static final String account = "jksc500";
    /**
     * 密码
     */
    public static final String password = "442365";
    public static final String charset = "UTF-8";
    
    /**
     * 短信发送接口名
     */
    public static final String action = "send";
    
    /**
     * 短信内容模版
     */
    public static final String content_template="【惠融金服】您正在注册惠融金服平台，验证码是：";
    
    /**
     * 下载岗短信内容模版
     */
    public static final String DOWNLOAD_CONTENT_TEMPLATE="【惠融金服】您正在申请客户资料下载，验证码是：";
    
    /**
     * 银行接口服务器是否可用模版
     */
    public static final String SERVER_CONTENT_TEMPLATE="【惠融金服】您的银行接口服务器暂时不可用，请及时处理!";
    
    public static final String BANK_SERVER_CONTENT_TEMPLATE="【惠融金服】银行分期业务服务器暂时不可用，请知悉!";
    
    public static final String WEB_CONTENT_TEMPLATE="【惠融金服】您的网站主页已变更，请知悉!";
    
    public static final String success = "Success";//成功
}
