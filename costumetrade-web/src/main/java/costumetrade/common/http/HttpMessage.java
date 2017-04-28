/**
 * Copyright (C) 2014-2016, hrfax and/or its affiliates. All rights reserved.
 * hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.http;

import java.io.Serializable;

import org.apache.http.HttpEntity;

/**
 * @author dante
 * @Date 2016年11月14日
 */
public class HttpMessage implements Serializable{
    
    private static final long serialVersionUID = -7324810546223674760L;

    private String url;
    
    private RetryStrategy retryStrategy;
    
    private HttpEntity httpEntity;
    
    private Object callbackData;
    
    public HttpMessage(String url,HttpEntity httpEntity){
        
    	this(url,httpEntity,NoRetryStrategy.NoRetry,null);  
    }
    
    public HttpMessage(String url,HttpEntity httpEntity,Object data){
        
    	this(url,httpEntity,NoRetryStrategy.NoRetry,data);  
    }
    
    public HttpMessage(String url,HttpEntity httpEntity,RetryStrategy retryStrategy){
        
    	this(url,httpEntity,retryStrategy,null);  
    }


    public HttpMessage(String url, HttpEntity httpEntity, RetryStrategy retryStrategy,Object data) {
    	this.url = url;
        this.httpEntity = httpEntity;
        this.retryStrategy = retryStrategy;
        this.callbackData = data;
    }
    
	/**
     * 重试计数
     * @param retryCount the retryCount to set
     */
    void retry() {
    	retryStrategy.retry();
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    
    public HttpEntity getHttpEntity(){
    	
        return httpEntity;
    }

	/**
	 *  @return
	 */
	public boolean needRetry() {

		return retryStrategy.needRetry();
	}

	/**
	 *  @return
	 */
	public int getInterval() {

		return retryStrategy.getInterval();
	}

	public Object getCallbackData() {

		return callbackData;
	}

}
