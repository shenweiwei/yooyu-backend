package com.yooyu.backend.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.yooyu.backend.common.utils.RedisUtil;

public abstract class BaseService {
	
	@Autowired
	private RedisTemplate<String,String> redisTemplate;

	@Resource(name = "redisTemplate")
	private ValueOperations<String , Object> valueOperations;
	
	@Value("${app.session-redis}")
	private int sessionRedis;
	
	@Value("${app.pic-redis}")
	private int pictureRedis;
	
	public void changePictureRedisDB(){
		JedisConnectionFactory connectionFactory = RedisUtil.getConnectionFactory(pictureRedis);
		redisTemplate.setConnectionFactory(connectionFactory);
	}
	
	public void changeSessionRedisDB(){
		JedisConnectionFactory connectionFactory = RedisUtil.getConnectionFactory(sessionRedis);
		redisTemplate.setConnectionFactory(connectionFactory);
	}
}
