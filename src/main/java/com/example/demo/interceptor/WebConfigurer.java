//package com.example.demo.interceptor;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//@Configuration
//public class WebConfigurer extends WebMvcConfigurationSupport{
//	@Autowired
//	private InterceptorTest interceptorTest;
//
//	@Override
//	protected void addInterceptors(InterceptorRegistry registry) {
//		// TODO Auto-generated method stub
//		registry.addInterceptor(interceptorTest ).addPathPatterns("/**").excludePathPatterns("hello","register");
//		registry.addInterceptor(interceptorTest ).addPathPatterns("/**").excludePathPatterns("hello","register");
//		super.addInterceptors(registry);
//	}
//}