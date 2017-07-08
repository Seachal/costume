package costumetrade.order.control;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sf.openapi.common.entity.MessageResp;
import com.sf.openapi.express.sample.route.dto.RouteRespDto;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.common.util.StringUtil;
import costumetrade.order.domain.ScLogistics;
import costumetrade.order.domain.ScStoreAddr;
import costumetrade.order.domain.SsCgsorder;
import costumetrade.order.domain.SsFinancial;
import costumetrade.order.domain.SsProductReview;
import costumetrade.order.domain.SsStoDetail;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.query.OrderCountQuery;
import costumetrade.order.query.OrderDetailQuery;
import costumetrade.order.query.OrderQuery;
import costumetrade.order.service.SpOrderService;
import costumetrade.order.service.SpProductService;
import costumetrade.user.domain.SsDataDictionary;

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
	public ApiResponse saveOrders(@RequestBody OrderQuery param) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if( param == null ||param.getOrder() == null || param.getStoDetails() == null||StringUtils.isBlank(param.getOpenid())){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		SsStoOrder order = param.getOrder();
		order.setOpenid(param.getOpenid());
		List<SsStoDetail> details = param.getStoDetails();

		Integer save = spOrderService.saveOrders(details,order,param.getOpenid());
		if(save <=0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
		}else if(save ==3){//自家店铺不能下单
			result.setCode(ResponseInfo.SUCCESS.code);
			result.setMsg(ResponseInfo.ORDER_EXCEPTION.msg);
			result.setData(ResponseInfo.ORDER_EXCEPTION.code);
			return result;
		}else if(save ==2){//库存不够
			result.setCode(ResponseInfo.SUCCESS.code);
			result.setMsg(ResponseInfo.NO_STOCK.msg);
			result.setData(ResponseInfo.NO_STOCK.code);
			return result;
		}
		return result;
	}
	
	@RequestMapping("/orderInit")
	@ResponseBody
	public ApiResponse orderInit(String openid) {
		ApiResponse result = new ApiResponse();
		ScStoreAddr addr = spOrderService.orderInit(openid);
		return result.getInstance(addr);
		
	}
	
	@RequestMapping("/orderFeeInit")
	@ResponseBody
	public ApiResponse orderFeeInit(String openid) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(StringUtils.isBlank(openid)){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		List<SsDataDictionary> dicts = spOrderService.orderFeeInit(openid);
		if(dicts == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
		}else{
			result.setData(dicts);
		}
		return result;
		
	}
	@RequestMapping("/saveOrderFee")
	@ResponseBody
	public ApiResponse saveOrderFee(@RequestBody List<SsCgsorder> orders) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(orders == null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int save = spOrderService.saveOrderFee(orders);
		if(save <=0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
		}

		return result;
		
	}
	@RequestMapping("/countOrders")
	@ResponseBody
	public ApiResponse countOrders(String openid) {
		ApiResponse result = new ApiResponse();
		if(openid == null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		OrderCountQuery query = spOrderService.countOrders(openid);
		if(query == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
		}
		return result.getInstance(query);
		
	}
	

	@RequestMapping("/enterPay")
	@ResponseBody
	public ApiResponse enterPay(String storeId) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(StringUtil.isBlank(storeId)){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		
		List<SsDataDictionary> dicts = spOrderService.enterPay(storeId);
		
		if(dicts == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(dicts);
			return result;
		}
		
	}
	
	@RequestMapping("/getOrder")
	@ResponseBody
	public ApiResponse getOrder(SsStoOrder order) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		
		String orderNo  = order.getPayorderno() ;
		Integer orderType =  Integer.parseInt(order.getOrdertype());
		String openid = order.getOpenid();
		OrderDetailQuery query = spOrderService.getOrder(orderNo,orderType,openid);
		
		if(query == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
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
		
		Integer orderType  = Integer.parseInt(order.getOrdertype()) ;
		Integer orderStatus =  order.getOrderstatus();
		String openid = order.getOpenid();
		
		List<SsStoOrder> query = spOrderService.getOrders(orderType,orderStatus,openid,order.getPageNum());
		
		if(query.size() < 0){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(query);
			return result;
		}
		
	}
	@RequestMapping("/updateOrder")
	@ResponseBody   
	public ApiResponse updateOrder(@RequestBody OrderQuery query) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(query==null){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		int save = spOrderService.updateOrder(query);
		if(save <= 0){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
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
		int operate = spOrderService.orderOperate(param);
		
		if(operate <= 0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			result.setData(ResponseInfo.OPERATE_EXPIRED.code);
			return result;
		}else if(operate == 2){//缺少库存
			result.setCode(ResponseInfo.SUCCESS.code);
			result.setMsg(ResponseInfo.NO_STOCK.msg);
			result.setData(ResponseInfo.NO_STOCK.code);
			return result;
		}else if(operate == 3){//不允许作废
			result.setCode(ResponseInfo.SUCCESS.code);
			result.setMsg(ResponseInfo.RETURN_EXCEPTION.msg);
			result.setData(ResponseInfo.RETURN_EXCEPTION.code);
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
		
		int operate = spOrderService.orderPay(ssFinancial);
		
		if(operate <= 0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
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
		
		int  confirm = spOrderService.confirmLogistic(scLogistics);
		
		if(confirm <= 0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}else{
			return result;
		}
		
		
	}
	@RequestMapping("/logisticInit")
	@ResponseBody
	public ApiResponse logisticInit(SsStoOrder order) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		List<Object> objects= spOrderService.logisticInit(order);
		
		if(objects == null){
			result.setData(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}else{
			result.setData(objects);
		}
		return result;
		
	}
	
	@RequestMapping("/queryLogistic")
	@ResponseBody
	public ApiResponse queryLogistic(SsStoOrder ssStoOrder) throws Exception {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		//scLogistics.setCreateby((String) httpSession.getAttribute("clientId"));
		MessageResp<List<RouteRespDto>> response = spOrderService.queryLogistic(ssStoOrder);
		if(response == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(response);
			return result;
		}
		
		
	}
	

	@RequestMapping("/saveReview")
	@ResponseBody
	public ApiResponse saveReview(SsProductReview ssProductReview) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);

		int response =spOrderService.saveReview(ssProductReview);
		if(response <= 0){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
			return result;
		}
		return result;
		
	}
	

}
