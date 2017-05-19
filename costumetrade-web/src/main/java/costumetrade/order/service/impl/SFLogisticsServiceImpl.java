package costumetrade.order.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sf.openapi.common.entity.AppInfo;
import com.sf.openapi.common.entity.HeadMessageReq;
import com.sf.openapi.common.entity.MessageReq;
import com.sf.openapi.common.entity.MessageResp;
import com.sf.openapi.express.sample.order.dto.OrderQueryReqDto;
import com.sf.openapi.express.sample.order.dto.OrderQueryRespDto;
import com.sf.openapi.express.sample.order.dto.OrderReqDto;
import com.sf.openapi.express.sample.order.dto.OrderRespDto;
import com.sf.openapi.express.sample.order.tools.OrderTools;
import com.sf.openapi.express.sample.route.dto.RouteReqDto;
import com.sf.openapi.express.sample.route.dto.RouteRespDto;
import com.sf.openapi.express.sample.route.tools.RouteTools;
import com.sf.openapi.express.sample.security.dto.TokenReqDto;
import com.sf.openapi.express.sample.security.dto.TokenRespDto;
import com.sf.openapi.express.sample.security.tools.SecurityTools;

import costumetrade.common.util.OrderNoGenerator;
import costumetrade.order.service.SFLogisticsService;

@Transactional
@Service
public class SFLogisticsServiceImpl implements SFLogisticsService{
	public static Logger logger = Logger.getLogger(SFLogisticsServiceImpl.class);
	@Override
	public MessageResp<TokenRespDto> getAccessToken() {
		//测试URL
		String urlRef = "https://open-sbox.sf-express.com/public/v1.0/security/access_token/sf_appid/" + 
			     "{sf_appid}/sf_appkey/{sf_appkey}";
		AppInfo appInfo = new AppInfo();
		appInfo.setAppId("00026897");
		appInfo.setAppKey("9A08DFCE2DC4765E4FB3011C4C395C70");
		MessageReq<TokenReqDto> accessTokenReq = new MessageReq<TokenReqDto>();
		HeadMessageReq head = new HeadMessageReq();
		head.setTransType(301);//获取access_token
		head.setTransMessageId(OrderNoGenerator.generate(""));
		
		accessTokenReq.setHead(head);
		MessageResp<TokenRespDto> response =null;
		try {
			response = SecurityTools.applyAccessToken(urlRef, appInfo, accessTokenReq);
		} catch (Exception e1) {
			logger.error("获取access_token失败："+e1.getMessage());
		}
		return response;
	}

	@Override
	public MessageResp<OrderRespDto> orderSF(OrderReqDto orderReqDto) {
		MessageReq<OrderReqDto> orderReq = new MessageReq<OrderReqDto>();	
		//测试URL
		String url = "https://open-sbox.sf-express.com/rest/v1.0/order/access_token/{access_token}/sf_appid/{sf_appid}/sf_appkey/{sf_appkey}";
		AppInfo appInfo = new AppInfo();
		appInfo.setAppId("00026897");
		appInfo.setAppKey("9A08DFCE2DC4765E4FB3011C4C395C70");
		/*
		 * 首先获取access_token
		 * */
		MessageResp<TokenRespDto> response = getAccessToken();
		
		TokenRespDto tokenRespDto = response.getBody();
		appInfo.setAccessToken(tokenRespDto.getAccessToken());
		appInfo.setRefreshToken(tokenRespDto.getRefreshToken());

		HeadMessageReq head = new HeadMessageReq();
		head.setTransType(200);//下单
		head.setTransMessageId(OrderNoGenerator.generate(""));
		orderReq.setHead(head);
		
		if(orderReqDto.getCustId() == null ||(orderReqDto.getCustId()!=null && orderReqDto.getCustId().trim() == null)){
			orderReqDto.setCustId("7550010173");
		}
		orderReq.setBody(orderReqDto);
		MessageResp<OrderRespDto> response1 =null;
		try {
			response1 = OrderTools.order(url, appInfo, orderReq);
		} catch (Exception e) {
			logger.error("获取access_token失败："+e.getMessage());
		}
		return response1;
	}

	@Override
	public MessageResp<OrderQueryRespDto> querySF(OrderQueryReqDto orderQueryReqDto) {
		//测试URL
		String url = "https://open-sbox.sf-express.com/rest/v1.0/order/query/access_token/{access_token}/" + 
				"sf_appid/{sf_appid}/sf_appkey/{sf_appkey}";
	
		AppInfo appInfo = new AppInfo();
		appInfo.setAppId("00026897");
		appInfo.setAppKey("9A08DFCE2DC4765E4FB3011C4C395C70");
		/*
		 * 首先获取access_token
		 * */
		MessageResp<TokenRespDto> response = getAccessToken();
		
		TokenRespDto tokenRespDto = response.getBody();
		appInfo.setAccessToken(tokenRespDto.getAccessToken());
		appInfo.setRefreshToken(tokenRespDto.getRefreshToken());
		MessageReq<OrderQueryReqDto> req = new MessageReq<OrderQueryReqDto>();
		HeadMessageReq head = new HeadMessageReq();
		head.setTransType(203);
		head.setTransMessageId(OrderNoGenerator.generate(""));
		req.setHead(head);
//
//		OrderQueryReqDto orderQueryReqDto = new OrderQueryReqDto();
//		orderQueryReqDto.setOrderId("201704261418379991219499");//由自己设置，不能重复

		req.setBody(orderQueryReqDto);
		MessageResp<OrderQueryRespDto> response1= null;
		try {
			response1 = OrderTools.orderQuery(url, appInfo, req);
		} catch (Exception e) {
			logger.error("获取access_token失败："+e.getMessage());
		}
		return response1;
	}

	@Override
	public MessageResp<List<RouteRespDto>> queryRouteSF(RouteReqDto routeReqDto) {
		//测试环境
		String url = "https://open-sbox.sf-express.com/rest/v1.0/route/query/access_token/{access_token}/" + 
				"sf_appid/{sf_appid}/sf_appkey/{sf_appkey}";
		//正式环境
//		String url = "https://open-prod.sf-express.com/rest/v1.0/route/query/access_token/{access_token}/" + 
//				"sf_appid/{sf_appid}/sf_appkey/{sf_appkey}";
		
		AppInfo appInfo = new AppInfo();
		appInfo.setAppId("00026897");
		appInfo.setAppKey("9A08DFCE2DC4765E4FB3011C4C395C70");
		/*
		 * 首先获取access_token
		 * */
		MessageResp<TokenRespDto> response = getAccessToken();
		
		TokenRespDto tokenRespDto = response.getBody();
		appInfo.setAccessToken(tokenRespDto.getAccessToken());
		appInfo.setRefreshToken(tokenRespDto.getRefreshToken());
		MessageReq<RouteReqDto> req = new MessageReq<RouteReqDto>();
		HeadMessageReq head = new HeadMessageReq();
		head.setTransType(501);
		head.setTransMessageId(OrderNoGenerator.generate(""));
		req.setHead(head);

		//RouteReqDto routeReqDto = new RouteReqDto();
		//routeReqDto.setTrackingNumber("444004750227");
		
		routeReqDto.setMethodType(1);
		routeReqDto.setTrackingType(1);
		req.setBody(routeReqDto);
		MessageResp<List<RouteRespDto>> response1 =null;
		try {
			response1 = RouteTools.routeQuery(url, appInfo, req);
		} catch (Exception e) {
			logger.error("获取access_token失败："+e.getMessage());
		}
		return response1;
	}
	

}
