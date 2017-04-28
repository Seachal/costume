/**
 * Copyright (C) 2014-2016, hrfax and/or its affiliates. All rights reserved.
 * hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.http;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dante
 * @Date 2016年11月14日
 */
public class HttpClientUtilsWrapper {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtilsWrapper.class);

	public static HttpResponse doPost(HttpMessage message) throws UnsupportedOperationException, IOException {
		
		logger.info("请求【{}】开始调用....",message.getUrl());
		
		try (CloseableHttpClient httpclient = HttpClients.custom().build();){
			HttpPost request = new HttpPost(message.getUrl());
			request.setEntity(message.getHttpEntity());
			try (CloseableHttpResponse response = httpclient.execute(request);){
				int statusCode = response.getStatusLine().getStatusCode();
				logger.info("请求【{}】返回状态吗【{}】",message.getUrl(),statusCode);
				
				if(HttpStatus.SC_OK==statusCode){
					String result =  IOUtils.toString(response.getEntity().getContent(),StandardCharsets.UTF_8.name());
					logger.info("返回消息:{}",result);
					return new HttpResponse(statusCode,result,message.getCallbackData());
				}
				return new HttpResponse(statusCode,null,message.getCallbackData());
			}
		}
	}


}
