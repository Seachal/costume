package costumetrade.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
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

import costumetrade.common.util.DataSecurity;
import costumetrade.common.util.HttpClient;
import costumetrade.common.util.OrderNoGenerator;
import costumetrade.common.util.XMLUtil;
import costumetrade.order.domain.YDLogisticsRequest;
import costumetrade.order.domain.YDLogisticsResponse;
import costumetrade.order.service.SFLogisticsService;

@Transactional
@Service
public class SFLogisticsServiceImpl implements SFLogisticsService {
	public static Logger logger = Logger
			.getLogger(SFLogisticsServiceImpl.class);

	@Override
	public MessageResp<TokenRespDto> getAccessToken() {
		// 测试URL
		String urlRef = "https://open-sbox.sf-express.com/public/v1.0/security/access_token/sf_appid/"
				+ "{sf_appid}/sf_appkey/{sf_appkey}";
		AppInfo appInfo = new AppInfo();
		appInfo.setAppId("00026897");
		appInfo.setAppKey("9A08DFCE2DC4765E4FB3011C4C395C70");
		MessageReq<TokenReqDto> accessTokenReq = new MessageReq<TokenReqDto>();
		HeadMessageReq head = new HeadMessageReq();
		head.setTransType(301);// 获取access_token
		head.setTransMessageId(OrderNoGenerator.generate(""));

		accessTokenReq.setHead(head);
		MessageResp<TokenRespDto> response = null;
		try {
			response = SecurityTools.applyAccessToken(urlRef, appInfo,
					accessTokenReq);
		} catch (Exception e1) {
			logger.error("获取access_token失败：" + e1.getMessage());
		}
		return response;
	}

	@Override
	public MessageResp<OrderRespDto> orderSF(OrderReqDto orderReqDto) {
		MessageReq<OrderReqDto> orderReq = new MessageReq<OrderReqDto>();
		// 测试URL
		String url = "https://open-sbox.sf-express.com/rest/v1.0/order/access_token/{access_token}/sf_appid/{sf_appid}/sf_appkey/{sf_appkey}";
		AppInfo appInfo = new AppInfo();
		appInfo.setAppId("00026897");
		appInfo.setAppKey("9A08DFCE2DC4765E4FB3011C4C395C70");
		/*
		 * 首先获取access_token
		 */
		MessageResp<TokenRespDto> response = getAccessToken();

		TokenRespDto tokenRespDto = response.getBody();
		appInfo.setAccessToken(tokenRespDto.getAccessToken());
		appInfo.setRefreshToken(tokenRespDto.getRefreshToken());

		HeadMessageReq head = new HeadMessageReq();
		head.setTransType(200);// 下单
		head.setTransMessageId(OrderNoGenerator.generate(""));
		orderReq.setHead(head);

		if (orderReqDto.getCustId() == null
				|| (orderReqDto.getCustId() != null && orderReqDto.getCustId()
						.trim() == null)) {
			orderReqDto.setCustId("7550010173");
		}
		orderReq.setBody(orderReqDto);
		MessageResp<OrderRespDto> response1 = null;
		try {
			response1 = OrderTools.order(url, appInfo, orderReq);
		} catch (Exception e) {
			logger.error("获取access_token失败：" + e.getMessage());
		}
		return response1;
	}

	@Override
	public MessageResp<OrderQueryRespDto> querySF(
			OrderQueryReqDto orderQueryReqDto) {
		// 测试URL
		String url = "https://open-sbox.sf-express.com/rest/v1.0/order/query/access_token/{access_token}/"
				+ "sf_appid/{sf_appid}/sf_appkey/{sf_appkey}";

		AppInfo appInfo = new AppInfo();
		appInfo.setAppId("00026897");
		appInfo.setAppKey("9A08DFCE2DC4765E4FB3011C4C395C70");
		/*
		 * 首先获取access_token
		 */
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
		// OrderQueryReqDto orderQueryReqDto = new OrderQueryReqDto();
		// orderQueryReqDto.setOrderId("201704261418379991219499");//由自己设置，不能重复

		req.setBody(orderQueryReqDto);
		MessageResp<OrderQueryRespDto> response1 = null;
		try {
			response1 = OrderTools.orderQuery(url, appInfo, req);
		} catch (Exception e) {
			logger.error("获取access_token失败：" + e.getMessage());
		}
		return response1;
	}

	@Override
	public MessageResp<List<RouteRespDto>> queryRouteSF(RouteReqDto routeReqDto) {
		// 测试环境
		String url = "https://open-sbox.sf-express.com/rest/v1.0/route/query/access_token/{access_token}/"
				+ "sf_appid/{sf_appid}/sf_appkey/{sf_appkey}";
		// 正式环境
		// String url =
		// "https://open-prod.sf-express.com/rest/v1.0/route/query/access_token/{access_token}/"
		// +
		// "sf_appid/{sf_appid}/sf_appkey/{sf_appkey}";

		AppInfo appInfo = new AppInfo();
		appInfo.setAppId("00026897");
		appInfo.setAppKey("9A08DFCE2DC4765E4FB3011C4C395C70");
		/*
		 * 首先获取access_token
		 */
		MessageResp<TokenRespDto> response = getAccessToken();

		TokenRespDto tokenRespDto = response.getBody();
		appInfo.setAccessToken(tokenRespDto.getAccessToken());
		appInfo.setRefreshToken(tokenRespDto.getRefreshToken());
		MessageReq<RouteReqDto> req = new MessageReq<RouteReqDto>();
		HeadMessageReq head = new HeadMessageReq();
		head.setTransType(501);
		head.setTransMessageId(OrderNoGenerator.generate(""));
		req.setHead(head);

		// RouteReqDto routeReqDto = new RouteReqDto();
		// routeReqDto.setTrackingNumber("444004750227");

		routeReqDto.setMethodType(1);
		routeReqDto.setTrackingType(1);
		req.setBody(routeReqDto);
		MessageResp<List<RouteRespDto>> response1 = null;
		try {
			response1 = RouteTools.routeQuery(url, appInfo, req);
		} catch (Exception e) {
			logger.error("获取access_token失败：" + e.getMessage());
		}
		return response1;
	}

	@Override
	public YDLogisticsResponse createOrderToYD(YDLogisticsRequest request) {
		String partnerid = "8053995263";
		String password = "veQTcMSZmPbFWs8DI5qaihpGJu27n4";
		String version = "1.0";
		String xmldata = 
				" <orders> " 
				+ " <order> "
				+ "	<order_serial_no>"+request.getOrderId()+"</order_serial_no> "
				+ "	<khddh>"+request.getKhddh()+"</khddh> " 
				+ "	<nbckh>"+request.getNbckh()+"</nbckh> "
				+ "	<order_type>"+request.getOrderType()+"</order_type> "
				+ "	<sender> " 
				+ "		<name>"+request.getSender().getName()+"</name> "
				+ "		<company>"+request.getSender().getCompany()+"</company> " 
				+ "		<city>"+request.getSender().getCity()+"</city> "
				+ "		<address>"+request.getSender().getAddress()+"</address> "
				+ "		<postcode>"+request.getSender().getPostcode()+"</postcode> "
				+ "		<phone>"+request.getSender().getPhone()+"</phone> "
				+ "		<mobile>"+request.getSender().getMobile()+"</mobile> "
				+ "		<branch>"+request.getSender().getBranch()+"</branch> " 
				+ "	</sender> " 
				+ "	<receiver> "
				+ "		<name>"+request.getReceiver().getName()+"</name> " 
				+ "		<company>"+request.getReceiver().getCompany()+"</company> "
				+ "		<city>"+request.getReceiver().getCity()+"</city> "
				+ "		<address>"+request.getReceiver().getAddress()+"</address> "
				+ "		<postcode>"+request.getReceiver().getPostcode()+"</postcode> "
				+ "		<phone>"+request.getReceiver().getPhone()+"</phone> "
				+ "		<mobile>"+request.getReceiver().getMobile()+"</mobile> "
				+ "		<branch>"+request.getReceiver().getBranch()+"</branch> " 
				+ "	</receiver> "
				+ "	<weight>"+request.getWeight()+"</weight> " 
				+ "	<size>"+request.getSize()+"</size> "
				+ "	<value>"+request.getValue()+"</value> " 
				+ "	<freight>"+request.getFreight()+"</freight> "
				+ "	<premium>"+request.getPremium()+"</premium> " 
				+ "	<other_charges>"+request.getOtherCharges()+"</other_charges> "
				+ "	<collection_currency>"+request.getCollectionCurrency()+"</collection_currency> "
				+ "	<collection_value>"+request.getCollectionValue()+"</collection_value> "
				+ "	<special>"+request.getSpecial()+"</special> " 
				+ "	<items> " 
				+ "		<item> "
				+ "			<name>"+request.getItem().getName()+"</name> " 
				+ "			<number>"+request.getItem().getNumber()+"</number> "
				+ "			<remark>"+request.getItem().getRemark()+"</remark> " 
				+ "		</item> " 
				+ "	 </items> "
				+ "	<remark>"+request.getRemark()+"</remark> " 
				+ "	<cus_area1>"+request.getCusArea1()+"</cus_area1> "
				+ "	<cus_area2>"+request.getCusArea2()+"</cus_area2> " 
				+ "	<wave_no>"+request.getWaveNo()+"</wave_no> " 
				+ "	<callback_id>"+request.getCallbackId()+"</callback_id> " 
				+ " </order> " 
				+ " </orders>";
		
		YDLogisticsResponse response = null;
		try {
			String data = DataSecurity.security(partnerid, password, xmldata);
			data += "&version=" + version + "&request=data";
			String result = HttpClient
					.post("http://orderdev.yundasys.com:10110/cus_order/order_interface/interface_receive_order__mailno.php",
							data);
			Document document =  DocumentHelper.parseText(result);
			//获取根节点元素对象  
	        Element root = document.getRootElement(); 
	        int size =getChildren( root).size();
	        if(size>0){
	        	response = getChildren( root).get(0);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<YDLogisticsResponse> queryOrderYD(YDLogisticsRequest request) {
		String partnerid = "8053995263";
		String password = "veQTcMSZmPbFWs8DI5qaihpGJu27n4";
		String version = "1.0";
		String xmldata = 
				"<orders>"
				+ "<order>"
				+ "   <orderid>"+request.getOrderId()+"</orderid>"
				+ "   <callback>D_00001</callback>"
				+ "   <mailno>"+request.getMailno()+"</mailno>"
				+ " </order>"
				+ "</orders>";
	 /*xmldata = "<orders>"
				+ "<order>"
				+ "   <orderid>12580133222111</orderid>"
				+ "   <callback>D_00001</callback>"
				+ "   <order_type>pt</order_type>"
				+ "   <customerid>761342</customerid>"
				+ "   <mailno>4060002317687</mailno>"
				
				+ " </order>"

				+ "   <order>" 
				+ "     <orderid>12580133222111</orderid>"
				+ "        <callback>D_00002</callback>"
				+ "       <mailno>4060002317687</mailno>" 
				+ "    </order>"

				+ "    <order>" 
				+ "        <orderid>12580133222111</orderid>"
				+ "        <callback>D_00003</callback>" 
				+ "        <mailno>4060002317687</mailno>"
				+ "   </order>"
				+ "</orders>";
		*/
		List<YDLogisticsResponse> response =new ArrayList<YDLogisticsResponse>();
		try {
			String data = DataSecurity.security(partnerid, password, xmldata);
			data += "&version=" + version + "&request=data";
			String result = HttpClient
					.post("http://orderdev.yundasys.com:10110/cus_order/order_interface/interface_order_info.php",
							data);
		
			Document document =  DocumentHelper.parseText(result);
			//获取根节点元素对象  
	        Element root = document.getRootElement(); 
	        response = getChildren( root);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public  List<YDLogisticsResponse> getChildren(Element node) {
		List<YDLogisticsResponse> response = new ArrayList<YDLogisticsResponse>();
		//首先获取当前节点的所有属性节点  
        
		List<Element> emlList =  node.elements();
		if(emlList!=null){
			YDLogisticsResponse resp = new YDLogisticsResponse();
			//遍历属性节点  
	        for(Element ele : emlList){
	        	if(ele.element("status")!=null
	        			||ele.element("json_data")!=null
	        			||ele.element("msg")!=null
	        			||ele.element("print_file")!=null
	        			||ele.element("order_status")!=null
	        			||ele.element("mailno")!=null
	        			||ele.element("pdf_info")!=null
	        			||ele.element("order_serial_no")!=null){
	        		resp.setStatus(ele.element("status").getText());
	        		resp.setJsonData(ele.element("json_data").getText());
		        	resp.setMsg(ele.element("msg").getText());
		        	resp.setPrintFile(ele.element("print_file").getText());
		        	resp.setOrderStatus(ele.element("order_status").getText());
		        	resp.setMailno(ele.element("mailno").getText());
		        	resp.setOrderId(ele.element("order_serial_no").getText());
		        	resp.setPdfInfo(ele.element("pdf_info").getText());
		        	response.add(resp);
	        	}
	        	
	        	
	        }
		}
          
        //同时迭代当前节点下面的所有子节点  
        //使用递归  
        @SuppressWarnings("unchecked")
		Iterator<Element> iterator = node.elementIterator();  
        while(iterator.hasNext()){  
            Element e = iterator.next();  
            getChildren(e);  
        }  
	
		return response;
	}
}
