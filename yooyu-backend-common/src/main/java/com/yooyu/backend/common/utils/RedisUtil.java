package com.yooyu.backend.common.utils;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

	public static JedisConnectionFactory getConnectionFactory(String hostName,int port,int dbNo) {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		redisConnectionFactory.setHostName(hostName);
		redisConnectionFactory.setPort(port);
		redisConnectionFactory.setDatabase(dbNo);
		redisConnectionFactory.afterPropertiesSet();

		return redisConnectionFactory;
	}
	
	public static JedisConnectionFactory getConnectionFactory(int dbNo) {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		redisConnectionFactory.setDatabase(dbNo);
		redisConnectionFactory.afterPropertiesSet();

		return redisConnectionFactory;
	}
}
