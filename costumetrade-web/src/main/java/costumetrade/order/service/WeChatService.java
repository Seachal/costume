package costumetrade.order.service;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import costumetrade.order.query.OrderQuery;
import costumetrade.user.domain.InputMessage;
import costumetrade.user.domain.ScWeChat;





public interface WeChatService  {

	/**
	 * 获取access
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * */
	public  String getAccessToken(String appId,String appSecret) throws Exception;
	
	/**
	 * 生成二维码
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * */
	public String getTwoCode(String sceneStr) throws Exception;
	
	/**
	 * 根据微信登录方法得到code，
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * */
	
	public String getOpenIdAndKey(String Code) throws Exception;
	
	public String getWechatTwoCode() throws Exception;
	/**
	 * 扫描二维码绑定客户/供应商、朋友 员工 
	 * */
	public int bindOpenidScan(InputMessage message);
	
	/**
	 * 获取微信用户信息
	 * */
	public String getWeChatUserInfo(String openid)throws Exception;
	
	/*
	 * 小程序发送消息
	 * **/
	public void sendMessage(InputMessage message);
	
	public ScWeChat getWeChat(ScWeChat chat);
	
	public void sendTemplate(OrderQuery param) throws Exception;
}
