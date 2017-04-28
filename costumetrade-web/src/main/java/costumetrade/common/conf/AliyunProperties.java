/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.conf;

/**
 * @author zhouyq
 * @Date 2017年3月6日
 */
public class AliyunProperties {

	public static String getEndpoint() {
		return ConfigProperties.getProperty("aliyun.oss.endpoint", "");
	}

	public static String getAccessId() {
		return ConfigProperties.getProperty("aliyun.oss.accessId", "");
	}

	public static String getAccessKey() {
		return ConfigProperties.getProperty("aliyun.oss.accessKey", "");
	}

	public static String getBucketName() {
		return ConfigProperties.getProperty("aliyun.oss.bucket", "");
	}

	public static String getHost() {
		return ConfigProperties.getProperty("aliyun.oss.host", "");
	}

	public static String getDir() {
		return ConfigProperties.getProperty("aliyun.oss.dir", "costumetrade/");
	}

	public static String getStyle() {
		return ConfigProperties.getProperty("aliyun.oss.style", "aliyun.oss.style:?x-oss-process=style/costumetrade");
	}

}
