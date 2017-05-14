package costumetrade.pay.req;

import costumetrade.common.util.Sha1Util;
import costumetrade.pay.common.WxPayConfig;

public class WxBaseReq {
     /**
      * 公众账号ID
      */
	private String appid = WxPayConfig.APP_ID;
	/**
	 * 商户号
	 */
	private String mch_id = WxPayConfig.MCH_ID;
	/**
	 * 设备号
	 */
	private String device_info="WEB";
	/**
	 * 随机字符串
	 */
	private String nonce_str = Sha1Util.getNonceStr();
	
	/**
	 * 签名
	 */
	private String sign;
	
	public String getAppid() {
		return appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}

	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
	
}
