package costumetrade.pay.res;


public class WxBaseRes {
     /**
      * 公众账号ID
      */
	private String appid;
	/**
	 * 商户号
	 */
	private String mch_id;
	/**
	 * 随机字符串
	 */
	private String nonce_str;
	
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 错误代码
	 */
	private String err_code;
	/**
	 * 返回信息
	 */
	private String return_msg;
	/**
	 * 错误代码描述
	 */
	private String err_code_des;
	/**
	 * 业务结果
	 */
	private String result_code;
	
	
	public String getAppid() {
		return appid;
	}
	public String getMch_id() {
		return mch_id;
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
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
	
	
}
