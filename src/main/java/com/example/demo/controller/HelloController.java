package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.po.User;
import com.example.demo.server.UserService;

@RestController
public class HelloController {

	@Autowired
	private UserService userService;

	@RequestMapping("/getUser")
	public String GetUser(Integer id) {
		System.out.println("#######"+id);
		return userService.getUserInfo(id).toString();
	}
	/**
	 * 测试事务
	 * @param id
	 * @return
	 */
	@RequestMapping("/addUser")
	public String addUser(Integer id) {
		System.out.println("#######addUser....");
		User user=new User();
		user.setId(id);
		user.setPassword("aaaa");
		user.setUsername("dddd");
		Integer ret=userService.addUser(user);
		 System.out.println("结果："+ret);
		return null;
	}
	
	@RequestMapping("/hello")
	public String hello(HttpServletRequest request) {
		System.out.println("执行方法");
		
		return "342423";
	}

	@RequestMapping("/register")
	public String register(HttpServletRequest request) {
		System.out.println("register");
		return "342423";
	}
}
