/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.verify;

/**
 * @author zhouyq
 * @Date 2017年3月17日
 */
public interface ITempTokenServce {
	
	public boolean isTempToken(String account);
	
	public String get();
}
