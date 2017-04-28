/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.verify;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import costumetrade.common.conf.SystemProperties;
import costumetrade.common.login.LoginContext;
import costumetrade.common.sms.SMSActor;
import costumetrade.common.sms.SMSActor.Sms;

/**
 * @author zhouyq
 * @Date 2017年3月16日
 */
@Service
public class SmsService implements ISmsService {

	private final String cacheNamespace = SystemProperties.name + ".sms.code";

	//@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void save(String businessKey, String code) {
		String key = buildKey(businessKey);
		redisTemplate.opsForValue().set(key, code, 500, TimeUnit.SECONDS);
	}

	@Override
	public boolean valid(String businessKey, String code) {

		String key = buildKey(businessKey);
		String smsCode = (String) redisTemplate.opsForValue().get(key);
		if (StringUtils.isEmpty(smsCode)) {
			return false;
		}
		boolean result = smsCode.equals(code);
		if (result) {
			redisTemplate.delete(key);
		}
		return result;
	}

	private String buildKey(String businessKey) {
		StringBuilder builder = new StringBuilder(cacheNamespace);
		builder.append(".").append(LoginContext.getAccount()).append(".").append("businessKey");
		return builder.toString();
	}

	@Override
	public void send(String mobile, String content) {
		SMSActor.send(new Sms(mobile, content));	
	}
}
