/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author zhouyq
 * @Date 2017年3月24日
 */
@Component
public class NamespaceStringRedisSerializer implements RedisSerializer<String>{
	
	public static final String keyword = "@Nps_";
	
	private final StringRedisSerializer stringRedisSerializer;
	
	@Value("redis.system.namespace:fengine")
	private String namespace;

	public NamespaceStringRedisSerializer() {
		this.stringRedisSerializer = new StringRedisSerializer();
	}
	
	@Override
	public byte[] serialize(String rawkey) throws SerializationException {
		String key = rawkey;
		if(key.indexOf(keyword)==-1){
			key = key+keyword+namespace;
		}
		return stringRedisSerializer.serialize(key);
	}

	@Override
	public String deserialize(byte[] bytes) throws SerializationException {
		return stringRedisSerializer.deserialize(bytes);
	}

}
