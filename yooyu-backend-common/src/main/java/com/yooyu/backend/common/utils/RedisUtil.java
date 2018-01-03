package com.yooyu.backend.common.utils;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
	
	@Autowired
	private RedisTemplate<String,String> redisTemplate;

	@Resource(name = "redisTemplate")
	private ValueOperations<String , Object> valueOperations;

	public void changeDataBase(String hostName,int dbNo) {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		redisConnectionFactory.setHostName(hostName);
		redisConnectionFactory.setPort(6379);
		redisConnectionFactory.setDatabase(dbNo);
		redisConnectionFactory.afterPropertiesSet();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
	}
}
