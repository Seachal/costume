package costumetrade.common.util;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

/**
 * <pre>
 *   Title: YdDataSecurity.java
 *   Description: 数据加密工具类
 *   Project:member project
 *   Copyright: yundaex.com Copyright (c) 2013
 *   Company: shanghai yundaex
 * </pre>
 * 
 * @author panhao
 * @version 2.0
 * @date 2013-4-28
 */
public class DataSecurity {
	
	/**
	 * @param partnerid
	 * @param password
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String security(String partnerid, String password, String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		data = new String(new Base64().encode(data.getBytes()));
		
		// 签名内容 = base64(data) + partnerid + password;
		String validation = data + partnerid + password;
		validation = md5(validation);
		
		partnerid = URLEncoder.encode(partnerid, "UTF-8");
		data = URLEncoder.encode(data, "UTF-8");
		validation = URLEncoder.encode(validation, "UTF-8");
		
		return new StringBuffer().append("partnerid").append("=")
								 .append(partnerid).append("&").append("xmldata").append("=")
								 .append(data).append("&").append("validation").append("=").append(validation).toString();
	}
	
	/**
	 * md5加密方法
	 * @param source 源字符串
	 * @return 加密后的字符串
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5(String source) throws NoSuchAlgorithmException {
		
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
                'a', 'b', 'c', 'd', 'e', 'f' };
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(source.getBytes());
		byte[] tmp = md.digest();
		char[] str = new char[16 * 2];
		
		int k = 0;
		for (int i = 0; i < 16; i++) {
			byte byte0 = tmp[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}
		
		return new String(str);
	}
}
