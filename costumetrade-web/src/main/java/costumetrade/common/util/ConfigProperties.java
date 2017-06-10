/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.util;

import java.util.Map;
import java.util.Properties;


/**
 * @author zhouyq
 * @Date 2017年1月13日
 */
public class ConfigProperties {
	private static  Properties properties = null;


	
	public static String getProperty(String key){
		if(null==properties){
			 Map<String, String> map = System.getenv();  
			 String fileName = "dbconfig.properties";
			 properties = PropertiesUtils.getProperties(fileName);
		}

		return properties.getProperty(key);
	}
	
	public static String getProperty(String key,String defaultValue){
		if(null==properties){
			 Map<String, String> map = System.getenv();  
			 String fileName = "config.properties";
			 properties = PropertiesUtils.getProperties(fileName);
		}
		return properties.getProperty(key,defaultValue);
	}

}
