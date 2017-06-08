package costumetrade.order.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.httpclient.HttpClientUtils;

import costumetrade.common.util.HttpPostUtil;
import costumetrade.order.domain.ScFocusShop;
import costumetrade.order.domain.SpClient;
import costumetrade.order.mapper.ScFocusShopMapper;
import costumetrade.order.mapper.SpClientMapper;
import costumetrade.order.service.WeChatService;
import costumetrade.user.domain.InputMessage;
import costumetrade.user.domain.QRCodeScanParam;
import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpEmployee;
import costumetrade.user.domain.SpUser;
import costumetrade.user.mapper.ScWeChatMapper;
import costumetrade.user.mapper.SpEmployeeMapper;
import costumetrade.user.mapper.SpUserMapper;



@Service
@Transactional
public class WeChatServiceImpl implements WeChatService {
//	private final static String APP_ID="wx0f02d5eacaf954e7";
//	private final static String APP_SECRET="8d7f55d6a5008b7f8efead72672008a6";
//	private final static String APP_ID="wxf3b0d53cdb909d00";
//	private final static String APP_SECRET="a8cbc70d8ae728dea2a4f00f0dcd9410";
	private final static String APP_ID="wx223537d8341fd657";//微信公众号测试号
	private final static String APP_SECRET="11f146a95fdf222f899cd385615061f4";
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

	/**
	 * 获取access
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * */
	public  String getAccessToken() throws Exception{
		String url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APP_ID+"&secret="+APP_SECRET;
		String response = HttpClientUtils.get(url, "utf-8");
		return response;
	}
	
	/**
	 * 生成二维码
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * */
	public String getTwoCode(String sceneStr) throws Exception{
		String chat = getAccessToken();
		JSONObject json = JSON.parseObject(chat);
		//sceneStr ="123,23,34";
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+json.getString("access_token");
		String param ="{'action_name': 'QR_LIMIT_STR_SCENE', 'action_info': {'scene': {'scene_str': '"+sceneStr+"'}}}";//
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
		String chat = getAccessToken();
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
    	JSONObject object = JSONObject.parseObject(eventKey);
    	QRCodeScanParam param = JSONObject.toJavaObject(object, QRCodeScanParam.class);//获取二维码中的参数值
    	String userInfo =null;
    	
    	try {
    		userInfo = getWeChatUserInfo(message.getFromUserName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	JSONObject json = JSON.parseObject(userInfo);
    	String nickName = json.getString("nickName"); 
    	String headimgurl = json.getString("headimgurl");
    	
    	ScWeChat wechat = scWeChatMapper.selectByOpenId(message.getFromUserName());
    	SpUser user = new SpUser();
    	if(wechat !=null && wechat.getUserid()!=null){
    		user = spUserMapper.selectByPrimaryKey(wechat.getUserid());
    	}
    	 /**
         * 扫描类型 1：加客户，2.加供应商 3.加朋友 4、加员工
         * */
    	int save =0;
    	if(param != null&&param.getStoreId()!=null){
    		if(param.getType()==1||param.getType()==2||param.getType()==3){
    			SpClient client = new SpClient();
    			client.setId(param.getId());
    			client.setType(param.getType()+"");
    			client.setOpenid(message.getFromUserName());
    			client.setStoreId(param.getStoreId());
    			client.setCreateTime(new Date());
    			client.setModifyTime(new Date());
    			client.setImage(headimgurl);
    			client.setNickName(nickName);
    			client.setAddress(user.getAddress());
    			client.setTelephone(user.getPhone());
    			SpClient c = spClientMapper.selectByPrimaryKey(param.getId());
    			if(c == null){//重复扫只保存一次
    				save =spClientMapper.insertSelective(client);
    			}
    			//加关注
    			ScFocusShop focusShop = new ScFocusShop();
    			focusShop.setOpenid(message.getFromUserName());
    			focusShop.setShopid(param.getStoreId());
    			List<ScFocusShop> shops = scFocusShopMapper.select(focusShop);
    			if(shops ==null || shops.size()<=0){
    				focusShop.setCreateTime(new Date());
    				focusShop.setModifyTime(new Date());
    				scFocusShopMapper.insertSelective(focusShop);
    			}
    		}else if(param.getType()==4){
    			SpEmployee employee = new SpEmployee();
    			employee.setId(param.getId());
    			employee.setOpenid(message.getFromUserName());
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
    				save =spEmployeeMapper.insertSelective(employee);
    			}
    			
    		}
    	}
		return save;
	}

	@Override
	public String getWeChatUserInfo(String openid) throws Exception {
		String chat = getAccessToken();
		JSONObject json = JSON.parseObject(chat);
		String url ="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+json.getString("access_token")+"&openid="+openid+"&lang=zh_CN";
		String response = HttpClientUtils.get(url, "utf-8");
		return response;
	}
}
