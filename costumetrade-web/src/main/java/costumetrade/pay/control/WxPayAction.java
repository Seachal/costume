package costumetrade.pay.control;

import java.math.BigDecimal;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.cookie.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.common.util.MD5Util;
import costumetrade.common.util.OrderNoGenerator;
import costumetrade.common.util.ServiceUtil;
import costumetrade.common.util.Sha1Util;
import costumetrade.pay.common.WxPayConfig;
import costumetrade.pay.domain.TradeInfo;
import costumetrade.pay.enums.EnumResultCode;
import costumetrade.pay.enums.EnumcheckNameType;
import costumetrade.pay.req.EntTransferReq;
import costumetrade.pay.req.PayInfoReq;
import costumetrade.pay.req.PayParam;
import costumetrade.pay.res.ResCloseOrder;
import costumetrade.pay.service.impl.TradeInfoService;
import costumetrade.pay.utils.IpUtils;
import costumetrade.pay.utils.WxPayPubHelper;
import costumetrade.user.domain.ScWeChat;
import costumetrade.user.mapper.ScWeChatMapper;
import costumetrade.user.service.SpStoreService;


/*******************************************************************************
 * <b>类名： WxPayAction.java</b> <br/>
 * 功能：微信支付，调用jsapi<br/>
 * 日期：2015-12-29<br/>
 * 
 * @author <a href="mailto:406224709@qq.com">zhongming</a>
 * @version 1.0
 * 
 ******************************************************************************/

@RequestMapping("/wxpay")
@RestController
public class WxPayAction  {
	
	Log log = LogFactory.getLog(this.getClass());
/*	private String code,state,outTradeNo,provinceNo;
	private PayInfoReq infoReq;
	private RefundReq refundReq;*/
	
	@Autowired
	private TradeInfoService tradeInfoService;
	@Autowired
	private SpStoreService spStoreService;
	@Autowired 
	private ScWeChatMapper scWeChatMapper;

	@RequestMapping("/orderInput")
	public String orderInput(HttpServletRequest request,String code) throws Exception {
		// 获取openID
		String openid = ServiceUtil.getOpenId(code);
		request.getSession().setAttribute("openid", openid);

		return "orderInput";
	}
 
	 
	/**
	 * 微信客户端授权成功后根据redirect_uri参数调整到pay接口，去准备支付前信息接口
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/pay")
	public ApiResponse pay(PayInfoReq infoReq,HttpServletRequest request,HttpServletResponse response) throws Exception {
		

		//String openid = String.valueOf(request.getSession().getAttribute("openid"));
		WxPayPubHelper  pubHelper = new  WxPayPubHelper(request,response);
		String out_trade_no = OrderNoGenerator.generate("TI", 10);
		infoReq.setOut_trade_no(out_trade_no);
		TradeInfo tradeInfo=getTradeInfo(infoReq);
		//由于openid和unionID字段值互换，openid实际值是unionID，unionID实际值openid
		ScWeChat chat = scWeChatMapper.selectByUnionid(infoReq.getOpenid());
		infoReq.setOpenid("oDy7t0IKwk7Ko6wpa0clZ9WdqnQo");
		tradeInfoService.insert(tradeInfo);
		
		// 生成支付签名,这个签名 给 微信支付的调用使用
		String prepay_id  = pubHelper.unifiedorder(infoReq);
		String timestamp =DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
		SortedMap<Object,Object> signMap = new TreeMap<Object,Object>();
        signMap.put("appId", WxPayConfig.APP_ID); 
        signMap.put("timeStamp", timestamp);
        signMap.put("nonceStr", infoReq.getNonce_str());
        signMap.put("package", "prepay_id=" + prepay_id);
        signMap.put("signType", "MD5");
        log.error(">>>>>>>>>params"+JSON.toJSONString(signMap));
        // 微信支付签名
        String paySign = MD5Util.createSign(signMap, WxPayConfig.KEY);
        log.error(">>>>>>>>>微信支付签名"+paySign);
       
		PayParam param = new PayParam();
		//微信分配的公众账号ID（企业号corpid即为此appId）
		param.setAppId(WxPayConfig.APP_ID);
		// 时间戳
		param.setTimeStamp(timestamp);
		// 随机字符串
		param.setNonceStr(infoReq.getNonce_str());
		// 预支付id ,就这样的格式
		param.setPackages("prepay_id=" + prepay_id);
		// 加密格式
		param.setSignType(WxPayConfig.SIGN_TYPE);
		// 微信支付签名
		param.setPaySign(paySign);
		
		return ApiResponse.getInstance(param);
	}
	
	@RequestMapping("/payRedirect")
	public ApiResponse payRedirect(PayParam param){
		return ApiResponse.getInstance(param);
	}
	/**
	 * 支付成功跳转
	 * */
	@RequestMapping("/paySuccess")
	@ResponseBody
	public ApiResponse paySuccess(@RequestParam("openid") String openid,@RequestParam("name")String name,@RequestParam("image")String image){
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(openid == null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		result.setData(spStoreService.insertStore(openid,name,image));
		return result;
	}
	/**
	 * 微信关闭订单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/closeOrder")
	@ResponseBody
	public ApiResponse closeOrder(HttpServletRequest request,String outTradeNo) throws Exception {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		if(StringUtils.isBlank(outTradeNo)){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}
		
		SortedMap<Object,Object> signMap = new TreeMap<Object,Object>();
        signMap.put("appid", WxPayConfig.APP_ID); 
        signMap.put("mch_id", WxPayConfig.MCH_ID);
        signMap.put("nonce_str", Sha1Util.getNonceStr());
        signMap.put("out_trade_no", outTradeNo);
        
        log.error(">>>>>>>>>params"+JSON.toJSONString(signMap));
        //关闭订单签名
        String paySign = MD5Util.createSign(signMap, WxPayConfig.KEY);
        signMap.put("sign", paySign);
        log.error(">>>>>>>>>关闭订单签名"+paySign);
        String requestXml = ServiceUtil.convertMapToXml(signMap);
        ResCloseOrder resOrder = WxPayPubHelper.closeOrder(requestXml);
        
        if (!EnumResultCode.RESULT_CODE_SUCCESS.getCode().equalsIgnoreCase(resOrder.getResult_code())) {
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(resOrder.getReturn_msg());
			return result;
		}
        return result;
	}
	
	
	/**
	 * 微信企业转帐
	 * @return
	 * @throws Exception
	 */
		@RequestMapping("/entTransfer")
		@ResponseBody
	 public ApiResponse entTransfer(HttpServletRequest request) throws Exception {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		TreeMap<String, String>  treeMap = ServiceUtil.convertStreamToTreeMap(request);
		EntTransferReq   transferReq = new EntTransferReq();
		transferReq.setPartner_trade_no(treeMap.get("partner_trade_no"));
		transferReq.setAmount(treeMap.get("amount"));
		if(StringUtils.isBlank( treeMap.get("partner_trade_no"))
				||StringUtils.isBlank(treeMap.get("desc"))
				||StringUtils.isBlank(treeMap.get("amount"))
				||StringUtils.isBlank(treeMap.get("openid"))){
		
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg("用户openid,校验用户姓名选项,金额,企业付款描述信息必填!");
			return result;
		}
		SortedMap<Object,Object> signMap = new TreeMap<Object,Object>();
        signMap.put("mch_appid", WxPayConfig.APP_ID); 
        signMap.put("mchid", WxPayConfig.MCH_ID);
        signMap.put("device_info", "WEB");
        signMap.put("nonce_str", Sha1Util.getNonceStr());
        signMap.put("partner_trade_no", treeMap.get("partner_trade_no"));
        signMap.put("openid", treeMap.get("openid"));
        //signMap.put("check_name", treeMap.get("check_name"));
        signMap.put("check_name", EnumcheckNameType.NO_CHECK.getCode());
        //signMap.put("re_user_name", treeMap.get("re_user_name"));
        signMap.put("amount", treeMap.get("amount"));      
        signMap.put("desc", treeMap.get("desc"));
        signMap.put("spbill_create_ip", IpUtils.getIpAddr(request));      
        
        log.error(">>>>>>>>>params"+JSON.toJSONString(signMap));
        //关闭订单签名
        String paySign = MD5Util.createSign(signMap, WxPayConfig.KEY);
        signMap.put("sign", paySign);
        log.error(">>>>>>>>>企业转帐签名"+paySign);
        String requestXml = ServiceUtil.convertMapToXml(signMap);
        String resXml = WxPayPubHelper.entTransferXml(requestXml);

		result.setMsg(resXml);
		return result;

	}
	
	
	
	private TradeInfo getTradeInfo(PayInfoReq infoReq){
		TradeInfo tradeInfo = new TradeInfo();
		tradeInfo.setTradeno(infoReq.getOut_trade_no());
		tradeInfo.setTradeamt(StringUtils.isBlank(infoReq.getTotal_fee())?new BigDecimal(0):new BigDecimal(infoReq.getTotal_fee()));
		return tradeInfo;
		
	}





	
}
