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
public interface RetryStrategy {
	
	public int getInterval();
	
	public int retry();
	
	public boolean needRetry();

}
