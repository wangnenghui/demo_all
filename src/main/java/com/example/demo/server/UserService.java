package com.example.demo.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.UserMapperTem;
import com.example.demo.po.User;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserMapperTem userTemMapper;

	public User getUserInfo(int id) {
		return userMapper.selectByPrimaryKey(id);
	}
	@Transactional
	public Integer addUser(User req) {
	 	userMapper.insertSelective(req);
		return 	userTemMapper.insertSelective(req);
	}
}