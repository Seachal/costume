/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年10月19日
 */
package costumetrade.common.wenqian.constant;

/**
 * 文签电子合同API常量类
 * @author liyb
 * @version WQAPIConstants.java,2016年10月19日 下午2:51:38
 */
public class WQAPIConstants {
    
    /*****************************************************************
     * 生产环境地址参数
     ****************************************************************/
//    public static final String HOST="https://api.wenqian.cn";
//
//    /**
//     * 向文签申请的第三方ID
//     */
//    public static final String CLIENT_ID="k1me3iea860iqcmkwscq";
//    
//    /**
//     * 向文签申请的第三方密码
//     */
//    public static final String CLIENT_SECRET="qjmz4icam5yxndkc6v9k";
    
    
    /*****************************************************************
     * 测试环境地址参数
     ****************************************************************/
    public static final String HOST="https://www.wenqian.cn:8448";

    /**
     * 向文签申请的第三方ID
     */
    public static final String CLIENT_ID="7agw8bf1vs9rxuku3et6";
    
    /**
     * 向文签申请的第三方密码
     */
    public static final String CLIENT_SECRET="mzv4u6b4e6bwe9bmgg5s";
    
    /**
     * 授权类型(固定值)
     */
    public static final String GRANT_TYPE="client_credentials";
    
    /**
     * 访问权限(默认值)
     */
    public static final String SCOPE="read";
    
    /*************************************************
     * 生产环境接口地址
     ************************************************/
    
    
    /*************************************************
     * 测试环境接口地址
     ************************************************/
    /**
     * 获取授权码token
     */
    public static final String token_api=HOST+"/api/oauth/token";
    
    /**
     * 同步用户到文签
     */
    public static final String sync_user_api=HOST+"/api/sec/user/regist/ent";
    
    /**
     * 上传合同
     */
    public static final String upload_api=HOST+"/api/sec/document/upload";
    
    /**
     * 发起合同关键字签署
     */
    public static final String sendkeyword_api=HOST+"/api/sec/document/{0}/sendKeyword";
    
    /**
     * 上传签章
     */
    public static final String upload_stamp_api=HOST+"/api/sec/stamp/upload";
    
    /**
     * 签署合同
     */
    public static final String sign_api=HOST+"/api/sec/document/{0}/sign";
    
    /**
     * 下载合同
     */
    public static final String downloancontract_api=HOST+"/eds/noauthApiDownloadPdf.action";
    
    
    /************************************************
     * 错误码
     ***********************************************/
    public static final Integer success=0;//成功
    public static final int params_error=-100;//请求参数错误
    public static final int phone_error=-101;//电话号码格式错误
    public static final int name_null_error=-108;//名称是空格
    public static final int create_user_error=-1;//用户创建失败
    public static final int phone_exist_error=-2;//电话号码已经存在
    public static final int company_exist_error=-4;//企业名称已经存在
    public static final int user_exist_error=-5;//该用户已经存在
    public static final int upload_contract_error=-10;//上传合同出错
    public static final int upload_error=-999;//上传失败
    
    
    /************************************************
     * 系统返回错误码
     ***********************************************/
    public static final int sign_generate_fail=-10001;
    public static final String sign_generate_fail_tips="签署合同生成失败";
    public static final int sign_fail=-10002;
    public static final String sign_fail_tips="签署合同失败";
    public static final String success_tips="success";
    
}
