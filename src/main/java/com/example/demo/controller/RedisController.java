package com.example.demo.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.redis.UserServiceImpl;
import com.google.gson.Gson;

@RestController
public class RedisController {
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@RequestMapping("redis/String")
	public String String(String key) {
		/* 处理string类型 */
		String aa=stringRedisTemplate.opsForValue().get("wangnenghuid");
		System.out.println("存储前："+aa);
		if(aa==null) {
			stringRedisTemplate.opsForValue().set("wangnenghuid", "你好", 10000, TimeUnit.SECONDS);
		}
		Long l=stringRedisTemplate.getExpire("wangnenghuid");
		System.out.println("过期时间："+l);
		String aa1=stringRedisTemplate.opsForValue().get("wangnenghuid");
		System.out.println("存储后："+aa1);
		/* 处理list */
	
		return null;
	}
	@RequestMapping("redis/all")
	public String all(String key) {
		/* String */
	    redisTemplate.opsForValue().set("num","123");
	    String st= redisTemplate.opsForValue().get("num"); 
		System.out.println("st:"+st);
		String aa1=redisTemplate.opsForValue().get("wangnenghuid");
		System.out.println("stringRedisTemplate存储后："+aa1);
		/* list */
		redisTemplate.opsForList().leftPush("list_1","43");
		redisTemplate.opsForList().leftPush("list_1","44");
		redisTemplate.opsForList().leftPush("list_1","45");
		String[] str= {"sf","dfa"};
		redisTemplate.opsForList().leftPushAll("list_1",str);
		List<java.lang.String> listSize=redisTemplate.opsForList().range("list_1",0,-1);
		System.out.println("list:"+new Gson().toJson(listSize));
		/* hash */
		
		return null;
	}
	@RequestMapping("redis/cacheable")
	public String redisTest(Integer id) {
		com.example.demo.redis.User u=userService.find(id);
		return u.toString();
	}
	@RequestMapping("redis/userPut")
	public String userPut(Integer id) {
		com.example.demo.redis.User u=userService.userPut(id);
		return u.toString();
	}
	@RequestMapping("redis/cacheable/update")
	public String redisUpdate(Integer id) {
		com.example.demo.redis.User u=userService.update(id);
		return u.toString();
	}

}