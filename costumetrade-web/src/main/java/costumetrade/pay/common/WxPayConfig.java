package costumetrade.pay.common;

import costumetrade.common.util.ConfigProperties;



public class WxPayConfig {
	// appid
	public static String APP_ID = "wx0f02d5eacaf954e7";
	
	// mch appid
	//public static String MCH_APP_ID = Config.getConfigValue(SPConstents.CWX_MCH_APP_ID);
	
	// JSAPI接口中获取openid，审核后在公众平台开启开发模式后可查看
	public static String APP_SECRET = "13a29793a995e14ad5ac7d5282e27334";
	// 受理商ID，身份标识
	public static String MCH_ID = "1338697401";
	// 商户支付密钥Key，装完整数后，配置得到。
	public static String KEY = "4d70e8033e26470deaf318b79a1250ce";
	// 异步回调地址
	public static String NOTIFY_URL = "http://192.168.31.166:8080/return/notify";
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
