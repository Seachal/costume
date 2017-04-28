package costumetrade.common.icbc.constant;

public class ICBCConstants {

	public static final String CHARSET_ENCODING = "UTF-8";
	public static final String HTTPMETHOD_POST = "POST";
	public static final String HTTPMETHOD_GET = "GET";
	public static final String CONTENTTYPE_JSON = "application/json";
	/**
	 * 请求成功
	 */
	public static final String SUCCESS_CODE = "00000";
	/**
	 * 该继续申请没有在数据库中找到原记录或原记录没有退回
	 */
	public static final String DATA_NOT_EXIT = "00092";
	public static final int TIME_OUT = 10000;
	/**
	 * [--专项卡申请--]已存在记录信息，不得重复申请
	 */
	public static final String CARD_RECORD_EXIST = "10000";
	
	/**
	 * 平台编号
	 */
	public static final String platno="hrkj"; 

	/**
	 * 服务器地址 
	 */
//	public static final String HOST = "http://192.168.0.42:8080/bank-transport/";
	
	/**
	 * 服务器正式地址
	 */
//	public static final String HOST = "http://60.191.72.101:9101/";
	
	/**
	 * 服务器测试地址
	 */
//	public static final String HOST = "http://60.191.72.101:9010/";
	
	/**
	 * 银行图片测试环境地址
	 */
//	public static final String HOST_IMAGE = "http://109.2.13.57:9101/";
	
	/**
     * 银行图片正式环境地址
     */
//	public static final String HOST_IMAGE = "http://109.2.18.252:9101/";

	/**
	 *=============================================
	 * 接口地址
	 *=============================================
	 */
	
	/**
	 * 分期、征信API
	 */
	public static final String ICBCAPI = "/icbc";
	
	/**
	 * 请求图片地址PAI
	 */
//	public static final String ICBCAPI_IMG = DomainContext.getIcbcServer() + "/image/";

	
	/**
	 * RSA私钥
	 */
	public static String PRIVATEKEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANNE3MF64OvtzmLOKKoGRU0uWq2wY1/zOk3z1ggj3YGGn+I1zSmurzuvh2y03uY4mAbU89VIFMCsjQM3nuHSKes5r6Gum2R4ZLV62wC7dNpnB1lpha8uBU3Vd89OOK52x8u00f8Q9WxAVXX/qPr83bsbIr3o6RL7y031m6xxHzTdAgMBAAECgYEArVsECX7asL0hbNHdbCZZF0vB/GOQJ1tdrDk4ltVXHMFq0CN1hY5hG4ichwajICWwGWsD6S6MWM09qDYrmQDqgyvYipQsxsvrn4BvK/QLMjuJxeUUyuNm3FGiPGgzBUOX8kofR+/1er+uyGCuer+kOLLjvFaX18SJcWSr8EdetcECQQDppR1Ls1f8+TkkNx2LS2RPToRPBD23JP0QkgHH8r3j8jw++93IFLZJZQRaT9L22XpalrTwEbifgiZsKUhrZxmHAkEA53usAl3nf9N5/voz6SO4BZzyvP8ySEDSSkY9mAXlCm5TM6Oe9xX6L7cNG2AvfzRZ7glCM3h0p9alYAY1d9LHewJAcaw4dZUa0OQ8DceW4ydZ9U/BBWSN1iXzzXUL0sjh5B39e7M0ctzI1yqDlKDThe/HHH3IKCinB0vd/6+SIVb2zwJBAMwObTc46FX6alSm4xPl9Nad4C1TGVQwMDBhcvKMX7euQZIH7p4JtZ5SUy+KvtP3pj770mvlB5Dc2rOnRYL3P/kCQQCv5l0eWWtgH/3HzPPxb8rc9EhvtktU5NIZzUwkwYioRELP6rwZoUCW6ZU4zEFZLAoTOgy5fs3T6MKqV1iqgC/0";

	
	//////////////////////////////////////////////////////////////////////////
	/**
	 * HTTP URL协议异常
	 */
	public static final String URLException = "100001";
	
	/**
	 * 平台接口异常
	 */
	public static final String ERROR = "-1";
	
	public static final String ERROR_STR = "调用银行API失败";
	
	/**
	 * ===========================================================
	 * 接口名称
	 * ===========================================================
	 */
	/**
	 * 申请分期接口
	 */
	public static final String APPLY_STATGE = "/applyForStage.html";
	
	/**
	 * 征信查询
	 */
	public static final String CREDIT_INQUIRY = "/queryCredit.html";
	
	/**
	 * 取图片
	 */
//	public static final String URL_IMAGE = "queryMaterialsImage.html";
	
	/**
	 * 展示图片
	 */
//	public static final String URL_IMAGE_SHOW = "showImage.html";
	
	/**
	 * 专项卡申请信息上送
	 */
	public static final String CREDIT_CARD_APPLY = "/creditCardApplyJSON.html";
	
	/**
	 * 服务监听
	 */
	public static final String SERVER_MONITOR = "/serverMonitorJSON.html";
}
