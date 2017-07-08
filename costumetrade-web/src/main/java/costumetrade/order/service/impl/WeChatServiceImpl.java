package costumetrade.order.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.httpclient.HttpClientUtils;

import costumetrade.common.util.HttpPostUtil;
import costumetrade.common.util.StringUtil;
import costumetrade.order.domain.ScFocusShop;
import costumetrade.order.domain.SpClient;
import costumetrade.order.mapper.ScFocusShopMapper;
import costumetrade.order.mapper.SpClientMapper;
import costumetrade.order.service.WeChatService;
import costumetrade.user.domain.InputMessage;
import costumetrade.user.domain.QRCodeScanParam;
import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpEmployee;
import costumetrade.user.domain.SpStore;
import costumetrade.user.domain.SpUser;
import costumetrade.user.mapper.ScWeChatMapper;
import costumetrade.user.mapper.SpEmployeeMapper;
import costumetrade.user.mapper.SpStoreMapper;
import costumetrade.user.mapper.SpUserMapper;



@Service
@Transactional
public class WeChatServiceImpl implements WeChatService {
	private final static String APP_ID="wx0f02d5eacaf954e7";
	private final static String APP_SECRET="8d7f55d6a5008b7f8efead72672008a6";
//	private final static String APP_ID="wxf3b0d53cdb909d00";
//	private final static String APP_SECRET="a8cbc70d8ae728dea2a4f00f0dcd9410";
//	private final static String APP_ID="wx223537d8341fd657";//微信公众号测试号
//	private final static String APP_SECRET="11f146a95fdf222f899cd385615061f4";
//	private final static String APP_ID="wx82428b2ac752c6a3";
//	private final static String APP_SECRET="ed8c5aa16cf56f66339fcb4be3377e30";
//	private final static String APP_ID="wx5f22c054831a13c1";
//	private final static String APP_SECRET="e495479fe303ea701670c0ebc35c87c8";
	
	@Autowired
	private SpClientMapper spClientMapper;
	@Autowired
	private SpEmployeeMapper spEmployeeMapper;
	@Autowired
	private ScFocusShopMapper scFocusShopMapper;
	@Autowired
	private ScWeChatMapper scWeChatMapper;
	@Autowired
	private SpUserMapper spUserMapper;
	@Autowired
	private SpStoreMapper  spStoreMapper;
	/**
	 * 获取access
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * */
	public  String getAccessToken(String appId,String appSecret) throws Exception{
		String url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret;
		String response = HttpClientUtils.get(url, "utf-8");
		return response;
	}
	
	/**
	 * 生成二维码
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * */
	public String getTwoCode(String sceneStr) throws Exception{
		
		
		String APP_ID="wx223537d8341fd657";//微信公众号测试号
		String APP_SECRET="11f146a95fdf222f899cd385615061f4";
//		String APP_ID="wx763d2ab0aa1a1bc5";//微信公众号 正式
//		String APP_SECRET="65bc54299a5a43c632ab3f3a0da7e26d";
		String chat = getAccessToken(APP_ID,APP_SECRET);
		JSONObject json = JSON.parseObject(chat);
		
		BigDecimal big = new BigDecimal(RandomStringUtils.randomNumeric(5));
		JSONObject object = JSONObject.parseObject(sceneStr);
    	QRCodeScanParam scan = JSONObject.toJavaObject(object, QRCodeScanParam.class);//获取二维码中的参数值
    	
    	SpClient client = new SpClient();
    	client.setScene(big+"");
    	client.setId(scan.getId());
    	client.setType(scan.getType()+"");
    	client.setStoreId(scan.getStoreId());
    	spClientMapper.insertSelective(client);
		//sceneStr ="123,23,34";
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+json.getString("access_token");
		//String param ="{'action_name': 'QR_LIMIT_STR_SCENE', 'action_info': {'scene': {'scene_str': '"+sceneStr+"'}}}";//
		String param ="{'expire_seconds': 604800, 'action_name': 'QR_SCENE', 'action_info': {'scene': {'scene_id': "+big+"}}}";
		JSONObject jsonObject = HttpPostUtil.sendHTTPSPostRequestJSON(url, JSONObject.parseObject(param));
		
		String ticketUrl ="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+jsonObject.getString("ticket");
	
		return ticketUrl;
	}
	
	/**
	 * 根据微信登录方法得到code，
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * */
	
	public String getOpenIdAndKey(String Code,String appId,String appSecret) throws Exception{
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"&secret="+appSecret+"&js_code="+Code+"&grant_type=authorization_code";
		String response = HttpClientUtils.get(url, "utf-8");
		return response;
	}
	
	/**
	 * wechat 小程序生成码
	 * @throws Exception 
	 * 
	 * */
	
	public String getWechatTwoCode() throws Exception{
		String APP_ID="wx0f02d5eacaf954e7";
		String APP_SECRET="8d7f55d6a5008b7f8efead72672008a6";
		String chat = getAccessToken(APP_ID,APP_SECRET);
		com.alibaba.fastjson.JSONObject json = JSON.parseObject(chat);
		String url = "http://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+json.getString("access_token");
		String param ="{'scene':'1','width':430,'auto_color':false,'line_color':{'r':'0','g':'0','b':'0'}}";
		
		
		//String param ="{'action_name': 'QR_LIMIT_STR_SCENE', 'action_info': {'scene': {'scene_str': '123'}}}";
		
		
		Map<String, Object> paramMap =new  HashMap<String,Object>();
		paramMap.put("scene", "1");
		paramMap.put("width", 430);
		paramMap.put("auto_color", false);
		paramMap.put("line_color", "{\"r\":\"0\",\"g\":\"0\",\"b\":\"0\"}");
		JSONObject jsonObject = HttpPostUtil.sendPostRequestJSON(url, JSONObject.parseObject(param));
//		String response = HttpClientUtils.postDo(url, paramMap,"utf-8");
		System.out.println(jsonObject.toString());
		//JSONObject jsonObject = HttpPostUtil.sendPostRequestJSON(url, JSONObject.fromObject(param));
		return jsonObject.toString();
	}

	@Override
	public int bindOpenidScan(InputMessage message) {
		String eventKey = message.getEventKey();
		eventKey=eventKey.replace("qrscene_",""); 
//    	JSONObject object = JSONObject.parseObject(eventKey);
//    	QRCodeScanParam param = JSONObject.toJavaObject(object, QRCodeScanParam.class);//获取二维码中的参数值
		
		System.out.println(eventKey);
//		
//		param.setId(eventKey.substring(0, 17));
//		param.setType(Integer.parseInt(eventKey.charAt(18)+""));
//		param.setStoreId(eventKey.substring(19, eventKey.length()-1));
    	String userInfo =null;
    	QRCodeScanParam param = new QRCodeScanParam();
    	
    	SpClient clientCheck = new SpClient();//同一个店铺加客户或者供应商 朋友，只能存在一个openid
    	clientCheck.setScene(eventKey);
		List<SpClient> clients = spClientMapper.select(clientCheck, null);
		
		SpClient client = new SpClient();
		if(clients !=null || clients.size()>0){//重复扫只保存一次
			client = clients.get(0);
		}else{
			return -1;
		}
		if(StringUtil.isNotBlank(client.getId())){
			param.setStoreId(client.getStoreId());
			param.setId(client.getId());
			param.setType(Integer.parseInt(client.getType()));
		}
    	
    	try {
    		userInfo = getWeChatUserInfo(message.getFromUserName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	JSONObject json = JSON.parseObject(userInfo);
    	System.out.println("json:"+json);
    	String nickName = json.getString("nickname"); 
    	String headimgurl = json.getString("headimgurl");
    	String unionid = json.getString("unionid");
    	ScWeChat wechat = scWeChatMapper.selectByOpenId(message.getFromUserName());
    	SpUser user = new SpUser();
    	SpStore store = new SpStore();
    	boolean enableBeCustomer = true;
    	boolean enableBeSupplier = true;
    	boolean enableBeFriend = true;
    	if(wechat !=null&& wechat.getId() !=null){
    		if(wechat.getStoreid() != null){
    			store = spStoreMapper.selectByPrimaryKey(wechat.getStoreid());
    		}else if(wechat.getUserid()!=null){
    			user = spUserMapper.selectByPrimaryKey(wechat.getUserid());
    		}
    		
    		if(wechat.getUserid() !=null&&(param.getType()==2||param.getType()==3)){
    			enableBeSupplier =false;
    			enableBeFriend =false;
    		}else if(wechat.getEmpid()!=null &&(param.getType()==2||param.getType()==3||param.getType()==1)){
    			enableBeCustomer =false;
    			enableBeSupplier =false;
    			enableBeFriend =false;
    		}
    	}
    	System.out.println("enableBeCustomer:"+enableBeCustomer+",enableBeSupplier:"+enableBeCustomer+",enableBeFriend:"+enableBeFriend+"," );
    	 /**
         * 扫描类型 1：加客户，2.加供应商 3.加朋友 4、加员工
         * */
    	int save =0;
    	/**
    	 * 控制店员不能加客户/供应商、朋友
    	 * 控制普通消费者不能加 供应商、朋友
    	 * */
    	if(param != null&&param.getStoreId()!=null){
    		if((param.getType()==1&&enableBeCustomer)
    				||(param.getType()==2&&enableBeSupplier)
    				||(param.getType()==3&&enableBeFriend)){
    			client.setId(param.getId());
    			client.setType(param.getType()+"");
    			if(StringUtil.isNotBlank(unionid)){
    				client.setOpenid(unionid);
    			}else{
    				client.setOpenid(message.getFromUserName());
    			}
    			
    			client.setCate(1+"");
    			client.setStoreId(param.getStoreId());
    			client.setScene(-1+"");
    			client.setCreateTime(new Date());
    			client.setModifyTime(new Date());
    			client.setImage(headimgurl);
    			client.setNickName(nickName);
    			if(store != null && store.getId() !=null){
    				client.setAddress(store.getAddress());
        			client.setTelephone(store.getPhone());
        			client.setReallyName(store.getName());
    			}else if(user != null && user.getId() !=null){
    				client.setAddress(user.getAddress());
        			client.setTelephone(user.getPhone());
        			client.setReallyName(user.getName());
    			}
    			
    			
    			save =spClientMapper.updateByPrimaryKeySelective(client);
    			
    			//加关注
    			ScFocusShop focusShop = new ScFocusShop();
    			if(param.getType()==2&&enableBeSupplier){
    				ScWeChat we = new ScWeChat();
    				we.setStoreid(param.getStoreId());
    				we = scWeChatMapper.selectWechat(wechat);
    				if(we!=null){
    					focusShop.setOpenid(we.getOpenid());
    					focusShop.setShopid(wechat.getStoreid());
    				}
    			}else{
        			if(StringUtil.isNotBlank(unionid)){
        				focusShop.setOpenid(unionid);
        			}else{
        				focusShop.setOpenid(message.getFromUserName());
        			}
        			focusShop.setShopid(param.getStoreId());
    			}
    			
    			List<ScFocusShop> shops = scFocusShopMapper.select(focusShop);
    			if(shops ==null || shops.size()<=0){
    				focusShop.setCreateTime(new Date());
    				focusShop.setModifyTime(new Date());
    				scFocusShopMapper.insertSelective(focusShop);
    			}
    		}else if(param.getType()==4){
    			SpEmployee employee = new SpEmployee();
    			if(StringUtil.isNotBlank(unionid)){
    				employee.setOpenid(unionid);
    			}else{
    				employee.setOpenid(message.getFromUserName());
    			}
    			
    			employee.setStoreId(param.getStoreId());
    			employee.setWeChatNo(message.getToUserName());
    			employee.setCreateTime(new Date());
    			employee.setModifyTime(new Date());
    			employee.setImage(headimgurl);
    			employee.setNickName(nickName);
    			employee.setAddress(user.getAddress());
    			employee.setTelephone(user.getPhone());
    			SpEmployee e = spEmployeeMapper.selectByPrimaryKey(employee);
    			if(e == null){//重复扫只保存一次
    				employee.setId(Integer.parseInt(param.getId()));
    				save =spEmployeeMapper.insertSelective(employee);
    			}else{
    				return -1;
    			}
    			
    		}
    	}
		return save;
	}

	@Override
	public String getWeChatUserInfo(String openid) throws Exception {
		String APP_ID="wx223537d8341fd657";//微信公众号测试号
		String APP_SECRET="11f146a95fdf222f899cd385615061f4";
//		String APP_ID="wx763d2ab0aa1a1bc5";
//		String APP_SECRET="65bc54299a5a43c632ab3f3a0da7e26d";
		String chat = getAccessToken(APP_ID,APP_SECRET);
		JSONObject json = JSON.parseObject(chat);
		String url ="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+json.getString("access_token")+"&openid="+openid+"&lang=zh_CN";
		String response = HttpClientUtils.get(url, "utf-8");
		System.out.println("response:"+response);
		return response;
	}

	@Override
	public void sendMessage(InputMessage message) {
		String chat;
		try {
			String APP_ID="wx0f02d5eacaf954e7";
			String APP_SECRET="8d7f55d6a5008b7f8efead72672008a6";
			
			chat = getAccessToken(APP_ID,APP_SECRET);
			com.alibaba.fastjson.JSONObject json = JSON.parseObject(chat);
			String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+json.getString("access_token");
			
			String param ="{'touser': 'oDy7t0GCpfxdFdFyNPhu_VYVufS4', 'msgtype': 'text', 'text': {'content': '"+message.getContent()+"'}}";//
			JSONObject jsonObject = HttpPostUtil.sendHTTPSPostRequestJSON(url, JSONObject.parseObject(param));
			System.out.println("jsonObject:"+jsonObject);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
