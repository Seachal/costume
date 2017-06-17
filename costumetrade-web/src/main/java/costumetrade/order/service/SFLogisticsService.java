package costumetrade.order.service;

import java.util.List;

import com.sf.openapi.common.entity.MessageResp;
import com.sf.openapi.express.sample.order.dto.OrderQueryReqDto;
import com.sf.openapi.express.sample.order.dto.OrderQueryRespDto;
import com.sf.openapi.express.sample.order.dto.OrderReqDto;
import com.sf.openapi.express.sample.order.dto.OrderRespDto;
import com.sf.openapi.express.sample.route.dto.RouteReqDto;
import com.sf.openapi.express.sample.route.dto.RouteRespDto;
import com.sf.openapi.express.sample.security.dto.TokenRespDto;

import costumetrade.order.domain.YDLogisticsRequest;
import costumetrade.order.domain.YDLogisticsResponse;


public interface SFLogisticsService {
	/**
	 *获取access_token 
	 * */
	public MessageResp<TokenRespDto> getAccessToken();
	
	/**
	 * 快速下单
	 * */
	public MessageResp<OrderRespDto> orderSF(OrderReqDto orderReqDto);
	
	/**
	 * 查询运单号
	 * */
	public MessageResp<OrderQueryRespDto> querySF(OrderQueryReqDto orderQueryReqDto);
	 
	/**
	 * 查询物流信息
	 * */
	public MessageResp<List<RouteRespDto>> queryRouteSF(RouteReqDto routeReqDto);
	
	/**
	 * 下单值韵达系统
	 * */
	public YDLogisticsResponse createOrderToYD(YDLogisticsRequest request);
	/**
	 * 查询韵达订单
	 * */
	public List<YDLogisticsResponse> queryOrderYD(YDLogisticsRequest request);
}
