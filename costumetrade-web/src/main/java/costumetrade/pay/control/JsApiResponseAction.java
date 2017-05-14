package costumetrade.pay.control;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.pay.common.RequestHandler;
import costumetrade.pay.common.ResponseHandler;
import costumetrade.pay.service.ITradeInfoService;

@RequestMapping("/return")
@Controller
public class JsApiResponseAction {

	private static final Logger log = LoggerFactory
			.getLogger(JsApiResponseAction.class);

	@Autowired
	private ITradeInfoService tradeInfoService;
	
	/**
	 * 接收微信支付返回的通知
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @throws ParseException
	 */
	@RequestMapping("/notify")
	@ResponseBody
	public String execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		log.error("########################js_api_notify	start########################");

		try {		
			// 签名验证
			ResponseHandler resHandler = new ResponseHandler(request,response);
			//TreeMap<String, String>  treeMap = ServiceUtil.convertMapToTreeMap(resultMap);
			SortedMap<String, String> resultMap = resHandler.getAllParameters();
			log.error("微信回调结果:" + resultMap.toString());
			
			String result_code = resultMap.get("result_code");
			String return_msg = resultMap.get("return_msg");
			
			String is_subscribe = resultMap.get("is_subscribe");
			String out_trade_no = resultMap.get("out_trade_no");
			String transaction_id = resultMap.get("transaction_id");
			String sign = resultMap.get("sign");
			String time_end = resultMap.get("time_end");
			String bank_type = resultMap.get("bank_type");
			String return_code = resultMap.get("return_code");
			String openid = resultMap.get("openid");
			
/*			if(!resHandler.isValidSign()){
				log.error("微信回调 签名失败，反馈信息:"+return_msg);
				return null;
			}*/
			if (!return_code.equals("SUCCESS")) {
				log.error("微信反馈失败，信息:"+return_msg);
			}
			
			tradeInfoService.updateTradeWithFinancial(out_trade_no,openid,transaction_id);
			request.setAttribute("out_trade_no", out_trade_no);

			response.getOutputStream().write(RequestHandler.setXML("SUCCESS", "OK").getBytes("utf-8"));
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (UnsupportedEncodingException msg) {
			log.error(">>>>>>>>>>异常信息:"+msg.getMessage());
		} catch (IOException msg) {
			log.error(">>>>>>>>>>异常信息:"+msg.getMessage());
		} 
		
		log.error("########################js_api_notify   end########################");
		return null;
	}
	
	



	
}
