/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.http;

/**
 * @author zhouyq
 * @Date 2017年1月13日
 */
public class NoRetryStrategy implements RetryStrategy {
	
	public static final NoRetryStrategy NoRetry = new NoRetryStrategy();
	
	private NoRetryStrategy(){
		
	}

	@Override
	public int getInterval() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int retry() {
		
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean needRetry() {
		
		return false;
	}

}
