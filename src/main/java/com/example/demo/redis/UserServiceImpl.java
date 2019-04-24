package com.example.demo.redis;

import java.util.Random;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IuserService {
	//缓存key为：users::id_参数id的缓存数据（非持久化状态）
	@Override
	@Cacheable(value = "users", key = "'id_' + #id")
	public User find(Integer id) {
		System.out.println("@Cacheable....");
		User u =new  User("name" + new Random().nextInt(1000),new Random().nextInt(100));
		return u;
	}
	/**
	 * 获取最新数据，覆盖原缓存数据
	 * 与cacheable结合使用
	 */
	@CachePut(value = "users", key = "'id_' + #id")
	public User userPut(Integer id) {
		System.out.println("CachePut.....");
		User u =new  User("name" + new Random().nextInt(1000),new Random().nextInt(100));
		return u;
	}
	//删除key为：users::id_参数id的缓存数据
	@CacheEvict(value = "users", key = "'id_' + #id")
	public User update(Integer id) {
		System.out.println("update:users");
		User u =new  User("name" + new Random().nextInt(1000),new Random().nextInt(100));
		return u;
		
	}
	/*
	 * 清除多个缓存数据
	 */
	@Caching(evict = {
			@CacheEvict(value = "users", key = "key"),
			@CacheEvict(value = "users", key = "'id_' + #user.id")
	})//清除缓存
	public User cachingClear(User user) {
		System.out.println("update:users");
		User u =new  User("name" + new Random().nextInt(1000),new Random().nextInt(100));
		return u;
	}
}
