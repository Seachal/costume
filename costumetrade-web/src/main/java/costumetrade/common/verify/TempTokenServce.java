/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.verify;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import costumetrade.common.login.JwtService;
import costumetrade.common.login.domain.LoginInfo;

/**
 * @author zhouyq
 * @Date 2017年3月17日
 */
@Service
public class TempTokenServce implements ITempTokenServce {
	
	public static final String TEMP_TOKEN_PREFIX = "fakeToken_";


	//@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private JwtService jwtService;

	@Override
	public boolean isTempToken(String account) {
		
		return account.startsWith(TEMP_TOKEN_PREFIX);
	}

	@Override
	public String get() {

		String account = TEMP_TOKEN_PREFIX + RandomStringUtils.random(6);
		String token = jwtService.tokenFor(account);
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setAccount(account);
		redisTemplate.opsForValue().set(token, loginInfo, 10, TimeUnit.SECONDS);
		return token;
	}

}
