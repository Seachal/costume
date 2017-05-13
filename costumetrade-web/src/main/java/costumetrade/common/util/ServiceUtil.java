package costumetrade.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * 自定义类，在官方文档中没有
 * @author 钟鸣
 *
 */
public class ServiceUtil {
	private static final Log logger = LogFactory.getLog(ServiceUtil.class);
	private static final String charsets="utf-8";
	/**
	 * 第二步：通过code换取网页授权access_token
	 * 根据授权码code获取access_token，参考：http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E4.BA.8C.E6.AD.A5.EF.BC.9A.E9.80.9A.E8.BF.87code.E6.8D.A2.E5.8F.96.E7.BD.91.E9.A1.B5.E6.8E.88.E6.9D.83access_token
	 */
	public static String getOpenId(String code) {
		
		String openParam = "appid=" + ConfigProperties.getProperty("wx.app.id") + "&secret=" + ConfigProperties.getProperty("wx.app.secret") + "&code=" + code + "&grant_type=authorization_code";
		String openJsonStr = HttpClientUtil.sendGET("https://api.weixin.qq.com/sns/oauth2/access_token", openParam);
		System.out.println("openJsonStr:"+openJsonStr);
		
		// 获取openid
		JSONObject openJson = JSON.parseObject(openJsonStr);
		
		String openid = openJson.getString("openid");
		return openid;
	} 
	
	/**
	 * 统一下单接口
	 * 参考文档：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
	 * @return
	 * @throws Exception 
	 */
	public static String unifiedorder(String requestXml) throws Exception{
		// 统一下单接口提交 xml格式
        HttpClientUtil  clientUtil = new HttpClientUtil();
		String retmsg = clientUtil.post(ConfigProperties.getProperty("wx.pay.request.url"), requestXml, "NOT_UTF8");
		System.out.println("result=========返回的xml=============" + retmsg.toString());
		Map<String, String> orderMap = XMLUtil.doXMLParse(retmsg.toString());
		System.out.println("orderMap===========================" + orderMap);
		
		// 获取
		String prepay_id = orderMap.get("prepay_id");
		return prepay_id;
	}
	
	
	public static Map<String, String>   convertStreamToMap(HttpServletRequest request){
		Map<String, String> resultMap  = null;
		try {
			InputStream  inStream = request.getInputStream();
			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outSteam.write(buffer, 0, len);
			}
			outSteam.close();
			inStream.close();
			String resultStr = new String(outSteam.toByteArray(),charsets);
		    resultMap = XMLUtil.doXMLParse(resultStr);
		} catch (IOException e) {
			logger.error("convertStreamToMap>>>>>异常信息："+e.getMessage());
		} catch (Exception e) {
			logger.error("convertStreamToMap>>>>>异常信息："+e.getMessage());
		}
		
		return resultMap;
	}
	
	public static TreeMap<String, String>   convertStreamToTreeMap(HttpServletRequest request){
		TreeMap<String, String> resultMap  = null;
		try {
			InputStream  inStream = request.getInputStream();
			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outSteam.write(buffer, 0, len);
			}
			outSteam.close();
			inStream.close();
			String resultStr = new String(outSteam.toByteArray(), charsets);
		    resultMap = XMLUtil.doXMLParseTreeMap(resultStr);
		} catch (IOException e) {
			logger.error("convertStreamToMap>>>>>异常信息："+e.getMessage());
		} catch (Exception e) {
			logger.error("convertStreamToMap>>>>>异常信息："+e.getMessage());
		}
		
		return resultMap;
	}
	public static TreeMap<String, String> convertMapToTreeMap(Map<String,String> paraMap){
		TreeMap<String, String> resultMap  = new TreeMap<String,String>();
		if(null==paraMap || paraMap.size()<=0){
			return resultMap;
		}
		
		TreeMap<String,String> m = new TreeMap<String,String>();
		for(String key:paraMap.keySet()){
			resultMap.put(key, paraMap.get(key));
		}
		return resultMap;
	}
	/**
	 * 自定义函数，在官方文档中没有
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String convertMapToXml(SortedMap parameters) throws UnsupportedEncodingException {

		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}

		sb.append("</xml>");
		return sb.toString();
	}
	
}
