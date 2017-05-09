package costumetrade.order.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.domain.ScStoreAddr;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.query.OrderDetailQuery;
import costumetrade.order.query.OrderQuery;
import costumetrade.order.query.PayQuery;
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
	public ApiResponse saveOrders(OrderQuery param,SsStoOrder order) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		List<SsStoDetail> details = new ArrayList<SsStoDetail>();
		if(param.getProductId().size()>0){
			for(int i=0 ;i<param.getProductId().size();i++){
				SsStoDetail detail = new SsStoDetail();
				detail.setCount(param.getCount().get(i));
				detail.setProductsize(param.getSize().get(i));
				detail.setProductcolor(param.getColor().get(i));
				detail.setPrice(param.getPrice().get(i));
				detail.setProductname(param.getProductName().get(i));
				detail.setCreateby(order.getCreateby());
				detail.setCreatetime(new Date());
				detail.setModifyby(order.getModifyby());
				detail.setModifytime(new Date());
				detail.setStoreid(order.getSellerstoreid()+"");
				details.add(detail);
			}
		}
	
		if(order == null || param == null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int save = spOrderService.saveOrders(details,order,param.getMemberTag());
		if(save <= 0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			return result;
		}
		
	}
	@RequestMapping("/orderInit")
	@ResponseBody
	public ApiResponse orderInit(Integer clientId) {
		ApiResponse result = new ApiResponse();
		ScStoreAddr addr = spOrderService.orderInit(clientId);
		return result.getInstance(addr);
		
	}
	@RequestMapping("/getOrder")
	@ResponseBody
	public ApiResponse getOrder(OrderDetailQuery param) {
		ApiResponse result = new ApiResponse();
		
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(param == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		OrderDetailQuery query = spOrderService.getOrder(param.getOrderId());
		
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
	public ApiResponse orderAudit(OrderQuery param) {
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
	public ApiResponse orderCancel(OrderQuery param) {
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
	public ApiResponse orderPay(PayQuery param) {
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
