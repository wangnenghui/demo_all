//package com.example.demo.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebFilter(filterName="testFilter",urlPatterns= {"/*"})
//public class testFilter implements Filter {
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest requestq = (HttpServletRequest) request;
//		HttpServletResponse responseq = (HttpServletResponse) response;
//		HttpSession session = requestq.getSession(false);
//		String uri = requestq.getRequestURI();
//		String key=requestq.getParameter("key");
//		System.out.println("#######testFilter");
//		if("true".equals(key)) {
//			chain.doFilter(requestq, responseq);
//		}else {
//			 responseq.sendRedirect(requestq.getContextPath()+"/user/login.html");
//		}
//	}
//
//}
