package costumetrade.order.service;

import java.io.IOException;




import org.apache.http.client.ClientProtocolException;





public interface WeChatService  {

	/**
	 * 获取access
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * */
	public  String getAccessToken() throws Exception;
	
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
}
