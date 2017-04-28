/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.http;

/**
 * 递增重试策略
 * @author zhouyq
 * @Date 2017年1月13日
 */
public class AscendingRetryStrategy implements RetryStrategy {

	private int retryCount = 3;
	
	private int intervalFactor = 5;
	
	private int execCount = 0;
	
	public AscendingRetryStrategy() {
	}

	public AscendingRetryStrategy(int intervalFactor) {
		
		this(3,intervalFactor);
	}
	
	public AscendingRetryStrategy(int retryCount, int intervalFactor) {

		if(intervalFactor>=1){
			this.intervalFactor = intervalFactor;
		}
		this.retryCount = retryCount;
	}

	@Override
	public int getInterval() {
	  
		return execCount*intervalFactor;
	}


	@Override
	public int retry() {
		execCount++;
		return this.retryCount--;
		
	}


	@Override
	public boolean needRetry() {
		
		return retryCount>0;
	}

}
