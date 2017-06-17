package costumetrade.order.control;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sf.openapi.common.entity.MessageResp;
import com.sf.openapi.express.sample.order.dto.OrderQueryReqDto;
import com.sf.openapi.express.sample.order.dto.OrderQueryRespDto;
import com.sf.openapi.express.sample.order.dto.OrderReqDto;
import com.sf.openapi.express.sample.order.dto.OrderRespDto;
import com.sf.openapi.express.sample.route.dto.RouteReqDto;
import com.sf.openapi.express.sample.route.dto.RouteRespDto;

import costumetrade.common.param.ApiResponse;
import costumetrade.common.param.ResponseInfo;
import costumetrade.order.domain.ScLogistics;
import costumetrade.order.domain.YDLogisticsRequest;
import costumetrade.order.domain.YDLogisticsResponse;
import costumetrade.order.service.SFLogisticsService;
import costumetrade.order.service.SpOrderService;

/**
 *
 * 
 * @author fancy
 * @Date 2017年4月21日
 */
@RequestMapping("/logistic")
@Controller
public class SFLogisticController {
	@Autowired
	private SFLogisticsService sFLogisticsService;
	@Autowired
	private SpOrderService spOrderService;


	@RequestMapping("/orderSF")
	@ResponseBody
	public ApiResponse orderSF(@RequestBody OrderReqDto orderReqDto) {
		MessageResp<OrderRespDto> response = new MessageResp<OrderRespDto>();
		response = sFLogisticsService.orderSF(orderReqDto);
		MessageResp<OrderQueryRespDto> response1 =null;
		if(response.getHead().getTransType()==4200){//下单成功
			OrderQueryReqDto orderQueryReqDto = new OrderQueryReqDto();
			orderQueryReqDto.setOrderId(orderReqDto.getOrderId());
			response1 = sFLogisticsService.querySF(orderQueryReqDto);
			ScLogistics logistics = new ScLogistics();
			logistics.setStoreid(1);//storeId createBy  获取session中的值
			logistics.setLogisticsname("顺丰");
			logistics.setLogisticsCode("SF");
			logistics.setCreatetime(new Date());
			logistics.setCreateby(1+"");
			logistics.setLogisticsno(response1.getBody().getMailNo());
			logistics.setOrderno(orderReqDto.getOrderId());
			spOrderService.confirmLogistic(logistics);//物流单号，+订单号 绑定
		}
		return  ApiResponse.getInstance(response1);
	}
	@RequestMapping("/createOrderToYD")
	@ResponseBody
	public ApiResponse createOrderToYD(YDLogisticsRequest request) {
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		YDLogisticsResponse response = sFLogisticsService.createOrderToYD(request);
		if(response==null){
			result.setCode(ResponseInfo.OPERATE_EXPIRED.code);
			result.setMsg(ResponseInfo.OPERATE_EXPIRED.msg);
		}else{
			if("1".equals(response.getStatus())){//下单成功
				
				ScLogistics logistics = new ScLogistics();
				logistics.setStoreid(1);//storeId createBy  获取session中的值
				logistics.setLogisticsname("顺丰");
				logistics.setLogisticsCode("SF");
				logistics.setCreatetime(new Date());
				logistics.setCreateby(1+"");
				logistics.setLogisticsno(response.getMailno());
				logistics.setOrderno(response.getOrderId());
				spOrderService.confirmLogistic(logistics);//物流单号，+订单号 绑定
			}
			
			result.setData(response);
		}
		return  result;
	}

	@RequestMapping("/querySF")
	@ResponseBody
	public ApiResponse querySF(@RequestBody OrderQueryReqDto orderQueryReqDto) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		if(orderQueryReqDto == null ){
			result.setCode(ResponseInfo.LACK_PARAM.code);
			result.setMsg(ResponseInfo.LACK_PARAM.msg);
			return result;
		}
		MessageResp<OrderQueryRespDto> response = sFLogisticsService.querySF(orderQueryReqDto);
		if(response == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(response);
		}
		return result;

	}
	@RequestMapping("/queryOrderYD")
	@ResponseBody
	public ApiResponse queryOrderYD(YDLogisticsRequest request) {
		
		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
		
		List<YDLogisticsResponse> response =sFLogisticsService.queryOrderYD( request);
		if(response==null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
		}else{
			result.setData(response);
		}
		return result;
		
	}

	@RequestMapping("/queryRouteSF")
	@ResponseBody
	public ApiResponse queryRouteSF(@RequestBody RouteReqDto routeReqDto) {

		ApiResponse result = new ApiResponse();
		result.setCode(ResponseInfo.SUCCESS.code);
		result.setMsg(ResponseInfo.SUCCESS.msg);
	
		MessageResp<List<RouteRespDto>> response = sFLogisticsService.queryRouteSF(routeReqDto);
		if(response == null){
			result.setCode(ResponseInfo.NOT_DATA.code);
			result.setMsg(ResponseInfo.NOT_DATA.msg);
			return result;
		}else{
			result.setData(response);
		}
		return result;
	}
	


	
}
