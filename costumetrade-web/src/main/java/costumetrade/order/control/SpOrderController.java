package costumetrade.order.control;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.query.OrderDetailKeyParam;
import costumetrade.order.query.OrderDetailQuery;
import costumetrade.order.query.OrderQuery;
import costumetrade.order.query.PayParam;
import costumetrade.order.service.SpOrderService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/order")
@Controller
public class SpOrderController {
	@Autowired
	private SpOrderService spOrderService;
	
	@RequestMapping("/saveOrders")
	@ResponseBody
	public ApiResponse saveOrders(String query) {
		ApiResponse result = new ApiResponse();
		OrderQuery query1 = new OrderQuery();
		JSONObject json = JSONObject.fromObject(query);
		query1 = (OrderQuery) json.toBean(json, OrderQuery.class);

		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(query == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int save = spOrderService.saveOrders(query1);
		
		if(save <= 0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			return result;
		}
		
	}
	@RequestMapping("/getOrder")
	@ResponseBody
	public ApiResponse getOrder(OrderDetailKeyParam param) {
		ApiResponse result = new ApiResponse();
		
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(param == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		OrderDetailQuery query = spOrderService.getOrder(param);
		
		if(query == null){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			result.setData(query);
			return result;
		}
		
	}
	@RequestMapping("/orderAudit")
	@ResponseBody
	public ApiResponse orderAudit(OrderDetailKeyParam param) {
		ApiResponse result = new ApiResponse();
		
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(param == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int operate = spOrderService.orderAudit(param);
		
		if(operate <= 0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			
			return result;
		}
		
	}
	@RequestMapping("/orderCancel")
	@ResponseBody
	public ApiResponse orderCancel(OrderDetailKeyParam param) {
		ApiResponse result = new ApiResponse();
		
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(param == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int operate = spOrderService.orderAudit(param);
		
		if(operate <= 0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			return result;
		}
		
	}
	@RequestMapping("/orderPay")
	@ResponseBody
	public ApiResponse orderPay(PayParam param) {
		ApiResponse result = new ApiResponse();
		
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(param == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int operate = spOrderService.orderPay(param);
		
		if(operate <= 0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			return result;
		}
		
	}

}
