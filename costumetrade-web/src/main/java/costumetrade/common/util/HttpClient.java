package costumetrade.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <pre>
 *   Title: HttpClient.java
 *   Description: HTTP Post
 *   Copyright: Maple Copyright (c) 2012
 *   Company: yundaex
 * </pre>
 * 
 * @author 
 * @version 1.0
 * @date 2012-12-16
 */
public class HttpClient {
	
	public static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";
	public static final String CONTENT_TYPE_XML = "application/xml;charset=utf-8";
	public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded;charset=utf-8";
	
	/**
	 * Post方式请求
	 * @param url 请求地址
	 * @param data 参数
	 * @param contentType 数据类型
	 * 		  1 CONTENT_TYPE_FORM
	 * 		  2 CONTENT_TYPE_XML
	 * 		  3 CONTENT_TYPE_JSON
	 * @return xml数据格式
	 * @throws Exception
	 */
	public static String post(String url, String data, String contentType) throws Exception {
		StringBuffer buffer = new StringBuffer();
		URL getUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection)getUrl.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", contentType);
		connection.setRequestProperty("Connection", "Keep-Alive"); 
		connection.setUseCaches(false); 
		connection.setConnectTimeout(10000);
		connection.connect();
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		out.write(data);
		out.flush();
		out.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line = "";
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		reader.close();
		return buffer.toString();
		
	}
	
	/***
	 * 
	 * @param url
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, String data) throws Exception {
		 return post(url, data, CONTENT_TYPE_FORM);
	}
	
}
