package costumetrade.pay.common;

import costumetrade.common.util.ConfigProperties;



public class WxPayConfig {
	// appid
	public static String APP_ID = ConfigProperties.getProperty(SPConstents.APP_ID_KEY);
	
	// mch appid
	//public static String MCH_APP_ID = Config.getConfigValue(SPConstents.CWX_MCH_APP_ID);
	
	// JSAPI接口中获取openid，审核后在公众平台开启开发模式后可查看
	public static String APP_SECRET = ConfigProperties.getProperty(SPConstents.APP_SECRET_KEY);
	// 受理商ID，身份标识
	public static String MCH_ID = ConfigProperties.getProperty(SPConstents.CWX_MCH_ID);
	// 商户支付密钥Key，装完整数后，配置得到。
	public static String KEY = ConfigProperties.getProperty(SPConstents.CWX_KEY);
	// 异步回调地址
	public static String NOTIFY_URL = ConfigProperties.getProperty(SPConstents.CWX_PAY_NOTIFY_URL);
	// 字符编码
	public static String CHARTSET = "UTF-8";
	// 加密方式
	public static String SIGN_TYPE = "MD5";
	// redirect_uri，微信授权重定向地址  
	public static String REDIRECT_URI=ConfigProperties.getProperty(SPConstents.CWX_PAY_URL);
	
	/*static {
		try {
			REDIRECT_URI = URLEncoder.encode(Config.getConfigValue(SPConstents.CWX_PAY_URL), CHARTSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}*/
}
