package costumetrade.pay.utils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import costumetrade.common.util.ConfigProperties;
import costumetrade.common.util.HttpClientUtil;
import costumetrade.common.util.ServiceUtil;
import costumetrade.common.util.Sha1Util;
import costumetrade.common.util.XMLUtil;
import costumetrade.pay.common.RequestHandler;
import costumetrade.pay.common.SPConstents;
import costumetrade.pay.common.WxPayConfig;
import costumetrade.pay.enums.EnumResultCode;
import costumetrade.pay.req.PayInfoReq;
import costumetrade.pay.res.ResCloseOrder;
import costumetrade.pay.res.ResEntTransfer;
import costumetrade.pay.res.ResRefundInfo;


public class WxPayPubHelper {
	Log log = LogFactory.getLog(this.getClass());
    private HttpServletRequest request;
    private HttpServletResponse response;
    
	public WxPayPubHelper(HttpServletRequest request,HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	public String unifiedorder(PayInfoReq payReq){
		log.error("begin>>>>>>>>>unifiedorder");
		String prepay_id = "";
		try {
			// 生成随机字符串
			String noncestr = Sha1Util.getNonceStr();	
			RequestHandler reqHandler = new RequestHandler(request, response);
			// 初始化 RequestHandler 类可以在微信的文档中找到.还有相关工具类
			reqHandler.init();
			// 执行统一下单接口 获得预支付id，一下是必填参数
			// 微信分配的公众账号ID（企业号corpid即为此appId）
			reqHandler.setParameter("appid","wx3b3c3dff64dafa20"); 
			// 微信支付分配的商户号
			reqHandler.setParameter("mch_id", "1338697401"); 
			// 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
			reqHandler.setParameter("device_info", payReq.getDevice_info()); 
			// 随机字符串，不长于32位。推荐随机数生成算法
			reqHandler.setParameter("nonce_str", noncestr); 
			// 商品描述   模拟测试
			//reqHandler.setParameter("body", body); 
			reqHandler.setParameter("body", payReq.getBody());
			// 商家订单号
			reqHandler.setParameter("out_trade_no", payReq.getOut_trade_no()); 
			// 商品金额,以分为单位  模拟测试
			//reqHandler.setParameter("total_fee", order_price); 
			reqHandler.setParameter("total_fee", String.valueOf(payReq.getTotalFeeFenLong())); 
			// APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
			reqHandler.setParameter("spbill_create_ip", IpUtils.getIpAddr(request)); 
			// 下面的notify_url是用户支付成功后为微信调用的action 异步回调.
			reqHandler.setParameter("notify_url", WxPayConfig.NOTIFY_URL);
			// 交易类型,取值如下：JSAPI，NATIVE，APP，详细说明见参数规定
			reqHandler.setParameter("trade_type", "JSAPI");
			// ------------需要进行用户授权获取用户openid-------------
			reqHandler.setParameter("openid", payReq.getOpenid()); // 这个必填.
			// 生成签名，并且转为xml
			String requestXml = reqHandler.getRequestXml();
			// 得到的预支付id
		    prepay_id = ServiceUtil.unifiedorder(requestXml);
			log.error("end>>>>>>>>>requestXml"+requestXml);
		} catch (UnsupportedEncodingException msg) {
			log.error("异常信息>>>>>>>>>"+msg.getMessage());
		} catch (Exception msg) {
			log.error("调用unifiedorder异常信息>>>>>>>>>"+msg.getMessage());
		}

		return prepay_id;
	}
	/**
	 * 查询订单
	 * 参考文档：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
	 * @return
	 * @throws Exception 
	 */
	/*public static ResOrderInfo orderquery(String requestXml) throws Exception{
		
        HttpClientUtil  clientUtil = new HttpClientUtil();
		String retmsg = clientUtil.post(ConfigProperties.getPropert(SPConstents.CWX_ORDER_QUERY_REQUEST_URL), requestXml, "NOT_UTF8");
		System.out.println("result=========返回的xml=============" + retmsg.toString());
		Map<String, String> orderMap = XMLUtil.doXMLParse(retmsg.toString());
		System.out.println("orderMap===========================" + orderMap);
		
		String result_code = orderMap.get("result_code");
		String return_msg = orderMap.get("return_msg");
		ResOrderInfo orderInfo = new ResOrderInfo();
		
		ResCloseOrder closeOrder = new ResCloseOrder();
		closeOrder.setResult_code(result_code);
		closeOrder.setReturn_msg(return_msg);
		
		if(EnumResultCode.RESULT_CODE_SUCCESS.getCode().equalsIgnoreCase(result_code)){
			orderInfo.setAppid(orderMap.get("appi"));
			orderInfo.setMch_id (orderMap.get("mch_id"));
			orderInfo.setNonce_str(orderMap.get("nonce_str"));
			orderInfo.setSign(orderMap.get("sign"));
			orderInfo.setResult_code(orderMap.get("result_code"));
			orderInfo.setErr_code(orderMap.get("err_code"));
			orderInfo.setErr_code_des (orderMap.get("err_code_des"));
			orderInfo.setDevice_info (orderMap.get("device_info"));
			orderInfo.setOpenid(orderMap.get("openid"));
			orderInfo.setIs_subscribe(orderMap.get("is_subscribe"));
			orderInfo.setTrade_type(orderMap.get("trade_type"));
			orderInfo.setTrade_state(orderMap.get("trade_state"));
			orderInfo.setBank_type(orderMap.get("bank_type"));
			orderInfo.setTotal_fee(orderMap.get("total_fee"));
			orderInfo.setFee_type(orderMap.get("fee_type"));
			orderInfo.setCash_fee(orderMap.get("cash_fee"));
			orderInfo.setCash_fee_type(orderMap.get("cash_fee_type"));
			orderInfo.setCoupon_fee(orderMap.get("coupon_fee"));
			orderInfo.setCoupon_count(orderMap.get("coupon_count"));
			orderInfo.setCoupon_batch_id_$n(orderMap.get("coupon_batch_id_$n"));
			orderInfo.setCoupon_id_$n(orderMap.get("coupon_id_$n"));
			orderInfo.setCoupon_fee_$n (orderMap.get("coupon_fee_$n "));
			orderInfo.setTransaction_id(orderMap.get("transaction_id"));
			orderInfo.setOut_trade_no(orderMap.get("out_trade_no"));
			orderInfo.setAttach(orderMap.get("attach"));
			//yyyyMMddHHmmss
			orderInfo.setTime_end (orderMap.get("time_end"));
			orderInfo.setTrade_state_desc(orderMap.get("trade_state_desc"));
			
		}
		return orderInfo;
	}*/
	
	/**
	 * 关闭订单
	 * 参考文档：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
	 * @return
	 * @throws Exception 
	 */
	public static ResCloseOrder closeOrder(String requestXml) throws Exception{
		
        HttpClientUtil  clientUtil = new HttpClientUtil();
		String retmsg = clientUtil.post(ConfigProperties.getProperty(SPConstents.CWX_ORDER_CLOSE_REQUEST_URL), requestXml, "NOT_UTF8");
		System.out.println("result=========返回的xml=============" + retmsg.toString());
		Map<String, String> orderMap = XMLUtil.doXMLParse(retmsg.toString());
		System.out.println("orderMap===========================" + orderMap);
		
		String return_code = orderMap.get("return_code");
		String return_msg = orderMap.get("return_msg");
		ResCloseOrder closeOrder = new ResCloseOrder();
		closeOrder.setResult_code(return_code);
		closeOrder.setReturn_msg(return_msg);
		if(EnumResultCode.RESULT_CODE_SUCCESS.getCode().equalsIgnoreCase(return_code)){
			closeOrder.setAppid(orderMap.get("appi"));
			closeOrder.setMch_id (orderMap.get("mch_id"));
			closeOrder.setNonce_str(orderMap.get("nonce_str"));
			closeOrder.setSign(orderMap.get("sign"));
			closeOrder.setResult_code(orderMap.get("return_code"));
			closeOrder.setErr_code(orderMap.get("err_code"));
			closeOrder.setReturn_msg(orderMap.get("return_msg"));
			
		}
		return closeOrder;
	}
	
	/**
	 * 企业转制
	 * 参考文档：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
	 * @return
	 * @throws Exception 
	 */
	public static ResEntTransfer entTransfer(String requestXml) throws Exception{
		
        HttpClientUtil  clientUtil = new HttpClientUtil();
		String retmsg = clientUtil.post(ConfigProperties.getProperty(SPConstents.CWX_ORDER_CLOSE_REQUEST_URL), requestXml, "NOT_UTF8");
		System.out.println("result=========返回的xml=============" + retmsg.toString());
		Map<String, String> orderMap = XMLUtil.doXMLParse(retmsg.toString());
		System.out.println("orderMap===========================" + orderMap);
		
		String return_code = orderMap.get("return_code");
		String return_msg = orderMap.get("return_msg");
		ResEntTransfer entTransfer = new ResEntTransfer();
		entTransfer.setReturn_code(return_code);
		entTransfer.setReturn_msg(return_msg);
		if(EnumResultCode.RESULT_CODE_SUCCESS.getCode().equalsIgnoreCase(return_code)){
			entTransfer.setMch_appid(orderMap.get("mch_appid"));
			entTransfer.setMchid(orderMap.get("mchid"));
			entTransfer.setDevice_info(orderMap.get("device_info"));
			entTransfer.setNonce_str(orderMap.get("nonce_str"));
			entTransfer.setResult_code(orderMap.get("return_code"));
			entTransfer.setErr_code(orderMap.get("err_code"));
			entTransfer.setErr_code_des(orderMap.get("err_code_des"));
			
			String result_code = orderMap.get("result_code");
			if(EnumResultCode.RESULT_CODE_SUCCESS.getCode().equalsIgnoreCase(result_code)){
				entTransfer.setPartner_trade_no(orderMap.get("partner_trade_no"));
				entTransfer.setPayment_no(orderMap.get("payment_no"));
				entTransfer.setPayment_time(orderMap.get("payment_time"));
			}
			
		}
		return entTransfer;
	}	
	
	/**
	 * 企业转制
	 * 参考文档：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
	 * @return
	 * @throws Exception 
	 */
	public static String entTransferXml(String requestXml) throws Exception{
		
        HttpClientUtil  clientUtil = new HttpClientUtil();
		//String retmsg = clientUtil.post(ConfigProperties.getPropert(SPConstents.CWX_ENT_TRANSFER_REQUEST_URL), requestXml, "NOT_UTF8");
       // String trasferUrl = ConfigProperties.getPropert(SPConstents.CWX_ENT_TRANSFER_REQUEST_URL);
        String trasferUrl ="https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
        String retmsg = clientUtil.postSSL(trasferUrl, requestXml, "NOT_UTF8");
		System.out.println("result=========返回的xml=============" + retmsg.toString());
		Map<String, String> orderMap = XMLUtil.doXMLParse(retmsg.toString());
		System.out.println("orderMap===========================" + orderMap);
		return retmsg;
	}
	/**
	 * 退款申请
	 * 参考文档：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
	 * @return
	 * @throws Exception 
	 */
	public static ResRefundInfo refund(String requestXml) throws Exception{
		
        HttpClientUtil  clientUtil = new HttpClientUtil();
		//String retmsg = clientUtil.postSSL(ConfigProperties.getPropert(SPConstents.CWX_PAY_REFUND_URL), requestXml, "NOT_UTF8");
        
        String retmsg = clientUtil.postSSL("https://api.mch.weixin.qq.com/secapi/pay/refund", requestXml, "NOT_UTF8");
		Map<String, String> orderMap = XMLUtil.doXMLParse(retmsg.toString());
		System.out.println("orderMap===========================" + orderMap);
		
		String result_code = orderMap.get("result_code");
		String return_msg = orderMap.get("return_msg");
		
		ResRefundInfo refundInfo = new ResRefundInfo();
		refundInfo.setResult_code(result_code);
		refundInfo.setReturn_msg(return_msg);
		
		if(EnumResultCode.RESULT_CODE_SUCCESS.getCode().equalsIgnoreCase(result_code)){
			refundInfo.setAppid(orderMap.get("appi"));
			refundInfo.setMch_id (orderMap.get("mch_id"));
			refundInfo.setNonce_str(orderMap.get("nonce_str"));
			refundInfo.setSign(orderMap.get("sign"));
			refundInfo.setResult_code(orderMap.get("result_code"));
			refundInfo.setErr_code(orderMap.get("err_code"));
			refundInfo.setErr_code_des(orderMap.get("err_code_des"));
			refundInfo.setDevice_info(orderMap.get("device_info"));
			refundInfo.setTransaction_id(orderMap.get("transaction_id"));
			refundInfo.setOut_trade_no(orderMap.get("out_trade_no"));
			refundInfo.setOut_refund_no(orderMap.get("out_refund_no"));
			refundInfo.setRefund_id(orderMap.get("refund_id"));
			refundInfo.setRefund_channel(orderMap.get("refund_channel"));
			refundInfo.setRefund_fee(orderMap.get("refund_fee"));
			refundInfo.setTotal_fee(orderMap.get("total_fee"));
			refundInfo.setFee_type(orderMap.get("fee_type"));
			refundInfo.setCash_fee(orderMap.get("cash_fee"));
			refundInfo.setCash_refund_fee(orderMap.get("cash_refund_fee"));
		}
		return refundInfo;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	
}
