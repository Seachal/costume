/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.conf;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import costumetrade.common.util.PropertiesUtils;


/**
 * @author chenyj
 * @Date 2017年1月13日
 */
public class ConfigProperties {
	
	private static  Properties properties = null;
	static {
		 Map<String, String> map = System.getenv();  
		 String fileName = "config.properties";
		properties = PropertiesUtils.getProperties(fileName);
	}
	
	public static String getProperty(String key){
		return properties.getProperty(key);
	}
	
	public static String getProperty(String key,String defaultValue){
		return properties.getProperty(key,defaultValue);
	}

}
