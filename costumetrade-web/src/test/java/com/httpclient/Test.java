package com.httpclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;

import costumetrade.common.util.HttpPostUtil;

public class Test {
	public static void main(String[] args) throws Exception {
		String chat = getAccessToken();
		com.alibaba.fastjson.JSONObject json = JSON.parseObject(chat);
		String url = "http://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+json.getString("access_token");
		String param ="{'scene':'1', 'width':430,'auto_color': false,'line_color': {'r': '0', 'g': '0', 'b':'0'}}";
		
		
		//String param ="{'action_name': 'QR_LIMIT_STR_SCENE', 'action_info': {'scene': {'scene_str': '123'}}}";
		
		
//		Map<String, Object> paramMap =new  HashMap<String,Object>();
//		paramMap.put("scene", "1");
//		paramMap.put("width", 430);
//		paramMap.put("auto_color", false);
//		paramMap.put("line_color", "{\"r\":\"0\",\"g\":\"0\",\"b\":\"0\"}");
		JSONObject jsonObject = HttpPostUtil.sendPostRequestJSON(url, JSONObject.fromObject(param));
//		String response = HttpClientUtils.postDo(url, paramMap,"utf-8");
		System.out.println(jsonObject.toString());
		//JSONObject jsonObject = HttpPostUtil.sendPostRequestJSON(url, JSONObject.fromObject(param));
		
	}

	
	private final static String APP_ID="wx0f02d5eacaf954e7";
	private final static String APP_SECRET="8d7f55d6a5008b7f8efead72672008a6";
//	private final static String APP_ID="wx82428b2ac752c6a3";
//	private final static String APP_SECRET="ed8c5aa16cf56f66339fcb4be3377e30";
//	private final static String APP_ID="wx5f22c054831a13c1";
//	private final static String APP_SECRET="e495479fe303ea701670c0ebc35c87c8";
	/**
	 * 获取access
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * */
	public static String getAccessToken() throws Exception{
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
		com.alibaba.fastjson.JSONObject json = JSON.parseObject(chat);
		
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+json.getString("access_token");
		String param ="{'action_name': 'QR_LIMIT_STR_SCENE', 'action_info': {'scene': {'scene_str': '123'}}}";
		JSONObject jsonObject = HttpPostUtil.sendHTTPSPostRequestJSON(url, JSONObject.fromObject(param));
		
		String ticketUrl ="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+jsonObject.getString("ticket");
	
		return ticketUrl;
	}
	
	/**
	 * 根据微信登录方法得到code，
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * */
	
	public  String getOpenIdAndKey(String Code,String appId,String appSecret) throws Exception{
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"&secret="+appSecret+"&js_code="+Code+"&grant_type=authorization_code";
		String response = HttpClientUtils.get(url, "utf-8");
		return response;
	}
	
	/**
	 * wechat 小程序生成码
	 * @throws Exception 
	 * 
	 * */
	
	public static String getWechatTwoCode() throws Exception{
		String chat = getAccessToken();
		com.alibaba.fastjson.JSONObject json = JSON.parseObject(chat);
		String url = "http://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+json.getString("access_token");
		String param ="{'scene':'1', 'width':430,'auto_color': false,'line_color': {'r': '0', 'g': '0', 'b':'0'}}";
		
		
		//String param ="{'action_name': 'QR_LIMIT_STR_SCENE', 'action_info': {'scene': {'scene_str': '123'}}}";
		
		
		Map<String, Object> paramMap =new  HashMap<String,Object>();
		paramMap.put("scene", "1");
		paramMap.put("width", 430);
		paramMap.put("auto_color", false);
		paramMap.put("line_color", "{\"r\":\"0\",\"g\":\"0\",\"b\":\"0\"}");
		JSONObject jsonObject = HttpPostUtil.sendPostRequestJSON(url, JSONObject.fromObject(param));
//		String response = HttpClientUtils.postDo(url, paramMap,"utf-8");
		System.out.println(jsonObject.toString());
		//JSONObject jsonObject = HttpPostUtil.sendPostRequestJSON(url, JSONObject.fromObject(param));
		return jsonObject.toString();
	}
}
