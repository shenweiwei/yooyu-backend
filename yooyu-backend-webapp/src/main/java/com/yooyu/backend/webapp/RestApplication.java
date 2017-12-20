package com.yooyu.backend.webapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages="com.yooyu.backend.reponsitory")
@ComponentScan(basePackages="com.yooyu.backend")
public class RestApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RestApplication.class, args);
	}
}
