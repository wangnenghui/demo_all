package com.example.demo.redis;

import java.util.Random;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IuserService {
	//����keyΪ��users::id_����id�Ļ������ݣ��ǳ־û�״̬��
	@Override
	@Cacheable(value = "users", key = "'id_' + #id")
	public User find(Integer id) {
		System.out.println("@Cacheable....");
		User u =new  User("name" + new Random().nextInt(1000),new Random().nextInt(100));
		return u;
	}
	/**
	 * ��ȡ�������ݣ�����ԭ��������
	 * ��cacheable���ʹ��
	 */
	@CachePut(value = "users", key = "'id_' + #id")
	public User userPut(Integer id) {
		System.out.println("CachePut.....");
		User u =new  User("name" + new Random().nextInt(1000),new Random().nextInt(100));
		return u;
	}
	//ɾ��keyΪ��users::id_����id�Ļ�������
	@CacheEvict(value = "users", key = "'id_' + #id")
	public User update(Integer id) {
		System.out.println("update:users");
		User u =new  User("name" + new Random().nextInt(1000),new Random().nextInt(100));
		return u;
		
	}
	/*
	 * ��������������
	 */
	@Caching(evict = {
			@CacheEvict(value = "users", key = "key"),
			@CacheEvict(value = "users", key = "'id_' + #user.id")
	})//�������
	public User cachingClear(User user) {
		System.out.println("update:users");
		User u =new  User("name" + new Random().nextInt(1000),new Random().nextInt(100));
		return u;
	}
}
