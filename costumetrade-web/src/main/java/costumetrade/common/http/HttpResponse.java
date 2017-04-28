/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.http;

import org.apache.http.HttpStatus;

/**
 * @author zhouyq
 * @Date 2017年1月13日
 */
public class HttpResponse {

	public final int code;

	public final String result;

	public final Object data;
	
	public boolean isSuccess(){
		
		return this.code == HttpStatus.SC_OK;
	}

	public HttpResponse(int code) {
		this(code, null,null);
	}

	public HttpResponse(int code, String result,Object data) {
		this.code = code;
		this.result = result;
		this.data = data;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getCallbackData(Class<T> requredType) {
		return (T) data;
	}

}
