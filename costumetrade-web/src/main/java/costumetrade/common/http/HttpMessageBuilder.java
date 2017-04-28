/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.http;

import org.apache.http.HttpEntity;

/**
 * @author zhouyq
 * @Date 2017年1月16日
 */
public class HttpMessageBuilder {

	private String url;

	private RetryStrategy retryStrategy = NoRetryStrategy.NoRetry;

	private HttpEntity httpEntity;

	private Object callbackData;

	public HttpMessageBuilder setUrl(String url) {
		this.url = url;
		return this;
	}

	public HttpMessageBuilder setRetryStrategy(RetryStrategy retryStrategy) {
		this.retryStrategy = retryStrategy;
		return this;
	}

	public HttpMessageBuilder setHttpEntity(HttpEntity httpEntity) {
		this.httpEntity = httpEntity;
		return this;
	}

	public HttpMessageBuilder setCallbackData(Object callbackData) {
		this.callbackData = callbackData;
		return this;
	}
	
	public HttpMessage build(){
		return new HttpMessage(this.url, this.httpEntity, this.retryStrategy, this.callbackData);
	}

}
