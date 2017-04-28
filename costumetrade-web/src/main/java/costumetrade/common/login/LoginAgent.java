/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.login;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;

/**
 * @author zhouyq
 * @Date 2017年1月22日
 */
public enum LoginAgent {
	
	WEB,
	IOS,
	ANDROID;
	
	public static boolean isApp(String name){
		
		if(IOS.name().equals(name)){
			return true;
		}
		
		if(ANDROID.name().equals(name)){
			return true;
		}
		return false;
		
	}
	
	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("111111"));
	}

}
