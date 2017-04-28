/**
 * Copyright (C) 2014-2016, Utry and/or its affiliates. All rights reserved. Utry
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * ip工具类
 * @author zhouyq
 * @Date 2017年1月23日
 */
public class IPUtils {

	private static final String[] HEADERS_TO_TRY = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

	public static String getClientIpAddress(HttpServletRequest request) {
		
		if(request == null){
			return "";
		}

		for (String header : HEADERS_TO_TRY) {
			String ip = request.getHeader(header);
			if (StringUtils.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}
	
	public static String getClientIpAddress() {
		
		ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		if(sra==null){
			return "";
		}
	    HttpServletRequest req = sra.getRequest();
		return getClientIpAddress(req);
	}
}
