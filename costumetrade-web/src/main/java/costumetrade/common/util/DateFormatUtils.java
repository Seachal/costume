/**
 * Copyright (C) 2014-2016, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.util;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * 日期格式化工具类
 * @author zhouyq
 * @Date 2016年12月30日
 */
public class DateFormatUtils extends org.apache.commons.lang3.time.DateFormatUtils{
	
	public static final FastDateFormat yyyyMMddHHmmss_FORMAT
    = FastDateFormat.getInstance("yyyyMMddHHmmss");
}
