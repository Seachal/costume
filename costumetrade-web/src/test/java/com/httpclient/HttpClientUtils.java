package com.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.params.CookieSpecPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {

    public static String get(String url, String encoding) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			httpclient.getParams().setBooleanParameter(CookieSpecPNames.SINGLE_COOKIE_HEADER, true);
			//log.info("GET "+url);
			HttpGet httpget = new HttpGet(url);
			httpget.addHeader(
					"User-Agent",
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022)");
			HttpResponse response = httpclient.execute(httpget);
			//log.info(response.getStatusLine().getStatusCode());
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, encoding);
		}
		finally {
			if(httpclient!=null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
	}
    
	public static String post(String url, Map<String, String> paramMap, String encode) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>(); 
			for (String key : paramMap.keySet()) {
				params.add(new BasicNameValuePair(key,  paramMap.get(key)));  
			}
			HttpPost httppost = new HttpPost(url);
			httppost.addHeader(
					"User-Agent",
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022)");
			UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, encode);
	        httppost.setEntity(ent);
	        HttpResponse response = httpclient.execute(httppost);  
	        HttpEntity resEntity = response.getEntity(); 
	        String html = EntityUtils.toString(resEntity,"utf-8");
	        //log.info(response.getStatusLine().getStatusCode());
	        return html;
		}
		finally {
			if(httpclient!=null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
	}
    
	public static String postDo(String url, Map<String, Object> paramMap, String encode) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>(); 
			for (String key : paramMap.keySet()) {
				params.add(new BasicNameValuePair(key, paramMap.get(key).toString()));  
			}
			HttpPost httppost = new HttpPost(url);
			httppost.addHeader(
					"User-Agent",
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022)");
			UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, encode);
			httppost.setEntity(ent);
			HttpResponse response = httpclient.execute(httppost);  
			HttpEntity resEntity = response.getEntity(); 
			String html = EntityUtils.toString(resEntity,"utf-8");
			//log.info(response.getStatusLine().getStatusCode());
			return html;
		}
		finally {
			if(httpclient!=null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
	}
	
}
