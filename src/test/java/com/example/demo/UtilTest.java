package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.po.User;
import com.example.demo.server.UserService;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUser(){
    	User user=userService.getUserInfo(3);
    	System.out.println(new Gson().toJson(user));
    }
    @Test
    public void addUser(){
    	User user=new User();
		user.setId(25);
		user.setPassword("wwww");
		user.setUsername("dddd");
    	Integer ret=userService.addUser(user);
    	System.out.println("###"+ret);
    }
}