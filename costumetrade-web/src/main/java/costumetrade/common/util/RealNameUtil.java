package costumetrade.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *  <p>Title: RealNameUtil.java<／p>
 *  <p>Description: <／p>
 *  @author yh.yu
 *  @date  2015年10月27日
 */
public class RealNameUtil {

	/**
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String doRequest(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type",
	                        "application/x-www-form-urlencoded");
	        // 填入apikey到HTTP header
	        //connection.setRequestProperty("apikey",  "087f64e37570e2ada4fb825df982949a");
	        connection.setDoOutput(true);
	        connection.getOutputStream().write(httpArg.getBytes("UTF-8"));
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
/*	*//**
	 * 
	 * 文档地址 http://www.id98.cn/doc/idcard
	 * 
	 * isok:1 查询成功，结果如下
	 *	code	说明
	*  0 一致：姓名与身份证号码一致。
    *  1 不一致：姓名与身份证号码不一致。
    *  2 无此身份证号码：身份证中心数据库中无此身份证号码。
	 *	
	 *  isok:0 查询失败（不扣费），原因如下
	 *  code	说明
	 *  11	参数不正确
	 *  12	商户余额不足
	 *  13	appkey不存在
	 *  14	IP被拒绝
	 *  20	身份证中心维护中
	 *  21	其他系统错误，请联系我们
	 *  
	 *//*
	public  static  JSONObject queryRealName(String cardno,String name) {
		
		String httpUrl = "http://api.id98.cn/api/idcard";
		String appkey ="09ec5979ee6fba919a21678714dd790b";
//		cardno ="420621198911288316";
//		name ="余勇辉";
		String httpArg = "appkey=" +appkey+
				"&cardno=" +cardno+
				"&name=" +name+
				"&output=json";
		String jsonResult = doRequest(httpUrl, httpArg);
		JSONObject json = JSONObject.fromObject(jsonResult);
		if (json.get("isok")=="0") {
			System.out.println(json.get("isok").toString()+json.get("code").toString());
		}
	
		return json;
		
	}
	public static void main(String[] args) {
		queryRealName("余勇辉","420488989888888882");
		
	}*/
}
