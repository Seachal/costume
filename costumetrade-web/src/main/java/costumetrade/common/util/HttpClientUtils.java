/**
 * Copyright (C) 2014-2016, hrfax and/or its affiliates. All rights reserved.
 * hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dante
 * @Date 2016年11月14日
 */
public class HttpClientUtils {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

	public static String doPost(String url, HttpEntity httpEntity) {

		logger.info("请求【{}】开始调用....", url);

		try (CloseableHttpClient httpclient = HttpClients.custom().build();) {
			HttpPost request = new HttpPost(url);
			request.setEntity(httpEntity);
			try (CloseableHttpResponse response = httpclient.execute(request);) {
				int statusCode = response.getStatusLine().getStatusCode();
				logger.info("请求【{}】返回状态吗【{}】", url, statusCode);
				if (HttpStatus.SC_OK == statusCode) {
					return IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8.name());
				}
				throw new RuntimeException(String.format("请求【%s】返回状态吗【%s】", url, statusCode));
			}
		} catch (Exception e) {
			throw new RuntimeException(String.format("请求%s异常", url),e);
		}
	}

	/**
	 * @param url
	 * @param 参数
	 * @return
	 */
	public static String doPost(String url, Map<String, Object> params) {
		List<BasicNameValuePair> nvps = new ArrayList<>(params.size());
		for (String key : params.keySet()) {
			Object value = params.get(key);
			if (value != null) {
				nvps.add(new BasicNameValuePair(key, value.toString()));
			}
		}
		return doPost(url, new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
	}


	public static InputStream getInputStream(String url) {

		try (CloseableHttpClient httpclient = HttpClients.custom().build();) {
			HttpGet request = new HttpGet(url);
			try (CloseableHttpResponse response = httpclient.execute(request);) {
				int statusCode = response.getStatusLine().getStatusCode();
				logger.info("请求【{}】返回状态吗【{}】", url, statusCode);
				if (HttpStatus.SC_OK == statusCode) {
					return response.getEntity().getContent();
				}
				throw new RuntimeException(String.format("请求【%s】返回状态码【%s】", url, statusCode));
			}
		} catch (IOException e) {
			throw new RuntimeException(String.format("请求%s异常", url));
		}
	}
	
	public static String get(String url) {
		
		try(InputStream inputStream = getInputStream(url);){
			return IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
		} catch (IOException e) {
			throw new RuntimeException("读取返回内容出错,请求url："+url);
		}
		
	}

}
