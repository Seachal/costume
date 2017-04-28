/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.conf;

import java.util.Properties;

import costumetrade.common.util.PropertiesUtils;


/**
 * @author zhouyq
 * @Date 2017年1月13日
 */
public class ConfigProperties {
	
	private static final Properties properties = PropertiesUtils.getProperties("config.properties");
	
	public static String getProperty(String key){
		return properties.getProperty(key);
	}
	
	public static String getProperty(String key,String defaultValue){
		return properties.getProperty(key,defaultValue);
	}

}
