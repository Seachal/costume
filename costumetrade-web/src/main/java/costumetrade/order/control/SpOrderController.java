package costumetrade.order.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.domain.ScLogistics;
import costumetrade.order.domain.ScStoreAddr;
import costumetrade.order.domain.SpProduct;
import costumetrade.order.domain.SsFinancial;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.query.OrderDetailQuery;
import costumetrade.order.query.OrderQuery;
import costumetrade.order.service.SpOrderService;
import costumetrade.order.service.SpProductService;

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
	@Autowired
	private SpProductService spProductService;
	@Autowired
    private HttpSession httpSession;
	
	@RequestMapping("/saveOrders")
	@ResponseBody
	public ApiResponse saveOrders(OrderQuery param,SsStoOrder order) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		//获取货品单位
		List<SpProduct> products = spProductService.selectProductById(param.getProductId(),order.getSellerstoreid());
		param.setClientId((Integer) httpSession.getAttribute("clientId"));
		order.setClientId(param.getClientId());
		order.setCreateby(param.getClientId()+"");
		order.setModifyby(param.getClientId()+"");

		List<SsStoDetail> details = new ArrayList<SsStoDetail>();
		if(param.getProductId().size()>0){
			for(int i=0 ;i<param.getProductId().size();i++){
				SsStoDetail detail = new SsStoDetail();
				detail.setCount(param.getCount().get(i));
				detail.setProductsize(param.getSize().get(i));
				detail.setProductcolor(param.getColor().get(i));
				detail.setPrice(param.getPrice().get(i));
				detail.setProductid(param.getProductId().get(i)+"");
				detail.setProductname(param.getProductName().get(i));
				
				if(param.getProductId().get(i) == products.get(i).getId()){
					detail.setProductunit(products.get(i).getUnit());
				}
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
	
		SsStoOrder o = spOrderService.saveOrders(details,order,param.getClientId());
		if(o == null){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			result.setData(o);
			return result;
		}
		
	}
	@RequestMapping("/orderInit")
	@ResponseBody
	public ApiResponse orderInit() {
		Integer clientId = (Integer) httpSession.getAttribute("clientId");
		ApiResponse result = new ApiResponse();
		ScStoreAddr addr = spOrderService.orderInit(clientId);
		return result.getInstance(addr);
		
	}
	@RequestMapping("/getOrder")
	@ResponseBody
	public ApiResponse getOrder(SsStoOrder order) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		order.setClientId((Integer) httpSession.getAttribute("clientId"));
		String orderNo  = order.getPayorderno() ;
		Integer orderType =  Integer.parseInt(order.getOrdertype());
		Integer clientId = order.getClientId();
		OrderDetailQuery query = spOrderService.getOrder(orderNo,orderType,clientId);
		
		if(query == null){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			result.setData(query);
			return result;
		}
		
	}
	@RequestMapping("/getOrders")
	@ResponseBody   
	public ApiResponse getOrders(SsStoOrder order) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		order.setClientId((Integer) httpSession.getAttribute("clientId"));
		Integer orderType  = Integer.parseInt(order.getOrdertype()) ;
		Integer orderStatus =  order.getOrderstatus();
		Integer clientId = order.getClientId();
		
		List<SsStoOrder> query = spOrderService.getOrders(orderType,orderStatus,clientId);
		
		if(query.size() < 0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			result.setData(query);
			return result;
		}
		
	}

	@RequestMapping("/orderOperate")
	@ResponseBody
	public ApiResponse orderOperate(OrderQuery param) {
		ApiResponse result = new ApiResponse();
		
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		if(param == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		//param.setClientId((Integer) httpSession.getAttribute("clientId"));
		int operate = spOrderService.orderOperate(param);
		
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
	public ApiResponse orderPay(SsFinancial ssFinancial) {
		ApiResponse result = new ApiResponse();
		
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(ssFinancial == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		ssFinancial.setClientId((Integer) httpSession.getAttribute("clientId"));
		int operate = spOrderService.orderPay(ssFinancial);
		
		if(operate <= 0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			return result;
		}
		
	}
	
	@RequestMapping("/confirmLogistic")
	@ResponseBody
	public ApiResponse confirmLogistic(ScLogistics scLogistics) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		scLogistics.setCreateby((String) httpSession.getAttribute("clientId"));
		int  confirm = spOrderService.confirmLogistic(scLogistics);
		
		if(confirm <= 0){
			result.setCode(ResponseInfo.EXCEPTION.code);
			result.setMsg(ResponseInfo.EXCEPTION.msg);
			return result;
		}else{
			return result;
		}
		
		
	}

}
