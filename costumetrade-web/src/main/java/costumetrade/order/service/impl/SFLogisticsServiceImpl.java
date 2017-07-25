package costumetrade.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import akka.Main;

import com.alibaba.fastjson.JSONObject;
import com.sf.openapi.common.entity.AppInfo;
import com.sf.openapi.common.entity.HeadMessageReq;
import com.sf.openapi.common.entity.MessageReq;
import com.sf.openapi.common.entity.MessageResp;
import com.sf.openapi.express.sample.order.dto.AddedServiceDto;
import com.sf.openapi.express.sample.order.dto.CargoInfoDto;
import com.sf.openapi.express.sample.order.dto.DeliverConsigneeInfoDto;
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

import costumetrade.common.util.ConfigProperties;
import costumetrade.common.util.DataSecurity;
import costumetrade.common.util.DigestUtil;
import costumetrade.common.util.HttpClient;
import costumetrade.common.util.OrderNoGenerator;
import costumetrade.common.util.ZTOHttpUtil;
import costumetrade.order.domain.YDLogisticsRequest;
import costumetrade.order.domain.YDLogisticsResponse;
import costumetrade.order.domain.ZTOLogistics;
import costumetrade.order.service.SFLogisticsService;

@Transactional
@Service
public class SFLogisticsServiceImpl implements SFLogisticsService {
	public static Logger logger = Logger
			.getLogger(SFLogisticsServiceImpl.class);

	public MessageResp<TokenRespDto> getAccessToken() {
		// 测试URL
		String urlRef = "https://"+ConfigProperties.getProperty("sf.url")+"/public/v1.0/security/access_token/sf_appid/"
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
		String url = "https://"+ConfigProperties.getProperty("sf.url")+"/rest/v1.0/order/access_token/{access_token}/sf_appid/{sf_appid}/sf_appkey/{sf_appkey}";
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
		String url = "https://"+ConfigProperties.getProperty("sf.url")+"/rest/v1.0/order/query/access_token/{access_token}/"
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
		String url = "https://"+ConfigProperties.getProperty("sf.url")+"/rest/v1.0/route/query/access_token/{access_token}/"
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
			System.out.println(result);
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
		System.out.println(result);
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
	        			||ele.element("mail_no")!=null
	        			||ele.element("pdf_info")!=null
	        			||ele.element("order_serial_no")!=null){
	        		resp.setStatus(ele.element("status").getText());
	        		//resp.setJsonData(ele.element("json_data").getText());
		        	resp.setMsg(ele.element("msg").getText());
		        	//resp.setPrintFile(ele.element("print_file").getText());
		        	//resp.setOrderStatus(ele.element("order_status").getText());
		        	resp.setMailno(ele.element("mail_no").getText());
		        	resp.setOrderId(ele.element("order_serial_no").getText());
		        	//resp.setPdfInfo(ele.element("pdf_info").getText());
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

	@Override
	public ZTOLogistics createOrderToZTO(ZTOLogistics request) {
		ZTOLogistics res = new ZTOLogistics();
		Map map = new HashMap();
		try {
			String data = JSONObject.toJSONString(request);
			map.put("data", data);
			map.put("msg_type", "COMMONORDER_CREATE");
			map.put("data_digest", DigestUtil.digest(data, "1ba059868f79", DigestUtil.UTF8));
			map.put("company_id", "7b7cda2af7c4485ba32748fec33b54ad");
			String response = ZTOHttpUtil.post(ConfigProperties.getProperty("zto.create"), "UTF-8", map);
			res = JSONObject.parseObject(response, ZTOLogistics.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		MessageReq<OrderReqDto> orderReq = new MessageReq<OrderReqDto>();
		// 测试URL
				String url = "https://open-sbox.sf-express.com/rest/v1.0/order/query/access_token/{access_token}/"
						+ "sf_appid/{sf_appid}/sf_appkey/{sf_appkey}";

				AppInfo appInfo = new AppInfo();
				appInfo.setAppId("00026897");
				appInfo.setAppKey("9A08DFCE2DC4765E4FB3011C4C395C70");
		String urlRef = "https://open-sbox.sf-express.com/public/v1.0/security/access_token/sf_appid/"
				+ "{sf_appid}/sf_appkey/{sf_appkey}";
		/*
		 * 首先获取access_token
		 */
		MessageReq<TokenReqDto> accessTokenReq = new MessageReq<TokenReqDto>();
		HeadMessageReq head1 = new HeadMessageReq();
		head1.setTransType(301);// 获取access_token
		head1.setTransMessageId(OrderNoGenerator.generate(""));

		accessTokenReq.setHead(head1);
		MessageResp<TokenRespDto> response = SecurityTools.applyAccessToken(urlRef, appInfo,
				accessTokenReq);
		//MessageResp<TokenRespDto> response = getAccessToken();

		TokenRespDto tokenRespDto = response.getBody();
		appInfo.setAccessToken(tokenRespDto.getAccessToken());
		appInfo.setRefreshToken(tokenRespDto.getRefreshToken());
		MessageReq<OrderQueryReqDto> req = new MessageReq<OrderQueryReqDto>();
		HeadMessageReq head = new HeadMessageReq();
		head.setTransType(203);
		head.setTransMessageId(OrderNoGenerator.generate(""));
		req.setHead(head);
		
		 OrderQueryReqDto orderQueryReqDto = new OrderQueryReqDto();
		 orderQueryReqDto.setOrderId("20170905141837934342");//由自己设置，不能重复

		req.setBody(orderQueryReqDto);
		MessageResp<OrderQueryRespDto> response1 = null;
		try {
			response1 = OrderTools.orderQuery(url, appInfo, req);
			System.out.println(response1);
		} catch (Exception e) {
			logger.error("获取access_token失败：" + e.getMessage());
		}
//		HeadMessageReq head = new HeadMessageReq();
//		head.setTransType(200);// 下单
//		head.setTransMessageId(OrderNoGenerator.generate(""));
//		orderReq.setHead(head);
//
////		if (orderReqDto.getCustId() == null
////				|| (orderReqDto.getCustId() != null && orderReqDto.getCustId()
////						.trim() == null)) {
////			orderReqDto.setCustId("7550010173");
////		}
//		
//		OrderReqDto orderReqDto = new OrderReqDto();
//
//		orderReqDto.setOrderId("20170905141837934342");
//		orderReqDto.setExpressType(new Short("1"));
//		orderReqDto.setPayMethod(new Short("1"));
//		orderReqDto.setNeedReturnTrackingNo(new Short("0"));
//		orderReqDto.setIsDoCall(new Short("1"));
//		orderReqDto.setIsGenBillNo(new Short("1"));
//		orderReqDto.setCustId("7550010173");
//		orderReqDto.setPayArea("755CQ");
//		orderReqDto.setSendStartTime("2014-4-24 09:30:00");
//		orderReqDto.setRemark("易碎物品，小心轻放");
//
//		DeliverConsigneeInfoDto deliverInfoDto = new DeliverConsigneeInfoDto();
//		deliverInfoDto.setAddress("上地");
//		deliverInfoDto.setCity("萍乡");
//		deliverInfoDto.setCompany("京东");
//		deliverInfoDto.setContact("李四");
//		deliverInfoDto.setCountry("中国");
//		deliverInfoDto.setProvince("江西");
//		deliverInfoDto.setCounty("上栗");
//		deliverInfoDto.setShipperCode("787564");
//		deliverInfoDto.setTel("010-95123669");
//		deliverInfoDto.setMobile("13612822894");
//
//		DeliverConsigneeInfoDto consigneeInfoDto = new DeliverConsigneeInfoDto();
//		consigneeInfoDto.setAddress("世界第一广场");
//		consigneeInfoDto.setCity("深圳");
//		consigneeInfoDto.setCounty("南山");
//		consigneeInfoDto.setCompany("顺丰");
//		consigneeInfoDto.setContact("张三");
//		consigneeInfoDto.setCountry("中国");
//		consigneeInfoDto.setProvince("广东");
//		consigneeInfoDto.setShipperCode("518100");
//		consigneeInfoDto.setTel("0755-33915561");
//		consigneeInfoDto.setMobile("18588413321");
//
//		CargoInfoDto cargoInfoDto = new CargoInfoDto();
//		cargoInfoDto.setParcelQuantity(Integer.valueOf(1));//包裹数
//		cargoInfoDto.setCargo("手机");//货物名称
//		cargoInfoDto.setCargoCount("1000");//货物数量
//		cargoInfoDto.setCargoUnit("部");//货物单位
//		cargoInfoDto.setCargoWeight("121");//货物重量
//		cargoInfoDto.setCargoAmount("5200");//货物单价
//		cargoInfoDto.setCargoTotalWeight(new BigDecimal(121000));//货物总重量
//
//		List<AddedServiceDto> addedServiceDtos = new ArrayList<AddedServiceDto>();
//		AddedServiceDto addedServiceDto = new AddedServiceDto();
//		addedServiceDto.setName("COD");
//		addedServiceDto.setValue("20000");//代收货款值上限为20000
//		addedServiceDtos.add(addedServiceDto);
//
//		AddedServiceDto addedServiceCodDto = new AddedServiceDto();
//		addedServiceCodDto.setName("CUSTID");//代收货款
//		addedServiceCodDto.setValue("7550010173");//用户月结卡号
//		addedServiceDtos.add(addedServiceCodDto);
//
//		orderReqDto.setDeliverInfo(deliverInfoDto);
//		orderReqDto.setConsigneeInfo(consigneeInfoDto);
//		orderReqDto.setCargoInfo(cargoInfoDto);
//		orderReqDto.setAddedServices(addedServiceDtos);
//		orderReq.setBody(orderReqDto);
//		MessageResp<OrderRespDto> response1 = null;
//		try {
//			response1 = OrderTools.order(url, appInfo, orderReq);
//			System.out.println(response1);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			logger.error("获取access_token失败：" + e.getMessage());
//		}
//		Map map = new HashMap();
//		try {
//			String data = null;
//			
//			data="{"+
//    "\"partnerCode\": \"13052014201123\","+
//   " \"type\": \"1\","+
//   " \"tradeId\": \"270184323432522222\","+
//   " \"mailNo\": \"1000000000016\","+
//   "  \"sender\": {"+
// 
//   "     \"name\": \"李琳\","+
//   "     \"company\": \"新南电子商务有限公司\","+
//   "     \"mobile\": \"13912345678\","+
//   "     \"phone\": \"021-87654321\","+
//   "     \"prov\": \"上海市\","+
//   "     \"city\": \"上海市\","+
//   "     \"county\": \"青浦区\","+
//   "     \"address\": \"华新镇华志路123号\","+
//   " },"+
//   " \"receiver\": {"+
// 
//   "    \"name\": \"杨逸嘉\","+
//   "     \"company\": \"逸嘉实业有限公司\","+
//   "     \"mobile\": \"13687654321\","+
//   "     \"phone\": \"010-22226789\","+
//   "     \"prov\": \"四川省\","+
//   "     \"city\":\"成都市\","+
//   "     \"county\": \"武侯区\","+
//   "     \"address\": \"育德路497号\","+
//   " },"+
//   " \"item\": {"+
//  
//   "         \"name\": \"迷你风扇\","+
//   "         \"category\": \"电子产品\","+
//   "         \"material\": \"金属\","+
//   "         \"size\": \"12,11,23\","+
//   "         \"weight\": \"1\","+
//   "         \"unitprice\": \"79\","+
//   "         \"quantity\": \"1\","+
//   "         \"remark\": \"黑色大号\""+
//   "     },"+
//   " \"starttime\": \"2013-05-20 12:00:00\","+
//   " \"endtime\": \"2013-05-20 15:00:00\","+
//   " \"weight\": 753,"+
//   " \"size\": \"12,23,11\","+
//   " \"quantity\": 2,"+
//   " \"price\": 12650,"+
//   " \"freight\": 1000,"+
//   " \"premium\": 50,"+
//   " \"packCharges\": 100,"+
//   " \"otherCharges\": 0,"+
//   " \"orderSum\": 0,"+
//   " \"collectMoneytype\": \"CNY\","+
//   " \"collectSum\": 1200,"+
//   " \"remark\": \"请勿摔货\""+
//"}	";
//			map.put("data", data);
//			map.put("msg_type", "COMMONORDER_CREATE");
//			map.put("data_digest", DigestUtil.digest(data, "1ba059868f79", DigestUtil.UTF8));
//			map.put("company_id", "7b7cda2af7c4485ba32748fec33b54ad");
//			System.out.println(ZTOHttpUtil.post("http://58.40.16.125:9001/gateway.do", "UTF-8", map));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		ZTOLogistics res = new ZTOLogistics();
//		Map map = new HashMap();
//		String mailNo = "761690680320";
//		try {
//			String data = "['"+mailNo+"']";
//			map.put("data", data);
//			map.put("msg_type", "TRACEINTERFACE_TRACES");
//			map.put("data_digest", DigestUtil.digest(data, "1ba059868f79", DigestUtil.UTF8));
//			map.put("company_id", "7b7cda2af7c4485ba32748fec33b54ad");
//			String response = ZTOHttpUtil.post("http://japi.zto.cn/gateway.do", "UTF-8", map);
//			System.out.println(response);
//			res = JSONObject.parseObject(response, ZTOLogistics.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public ZTOLogistics queryZTO(String mailNo) {
		ZTOLogistics res = new ZTOLogistics();
		Map map = new HashMap();
		try {
			String data = "['"+mailNo+"']";
			map.put("data", data);
			map.put("msg_type", "TRACEINTERFACE_TRACES");
			map.put("data_digest", DigestUtil.digest(data, "1ba059868f79", DigestUtil.UTF8));
			map.put("company_id", "7b7cda2af7c4485ba32748fec33b54ad");
			String response = ZTOHttpUtil.post(ConfigProperties.getProperty("zto.query"), "UTF-8", map);
			res = JSONObject.parseObject(response, ZTOLogistics.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	 
}
