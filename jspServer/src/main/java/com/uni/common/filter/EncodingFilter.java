package com.uni.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//@WebFilter(filterName="encodingFilter",urlPatterns="/*")
//하나만걸거면 상관없지만 사용x
public class EncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("인코딩 필터 동작 ");
		
		if(((HttpServletRequest) request).getMethod().equalsIgnoreCase("post")) {
			System.out.println("post방식요청됨");
			request.setCharacterEncoding("UTF-8");
		}
		
		chain.doFilter(request, response);
		
		System.out.println("===서블릿이 다 동작하고 나서 출력됨===");
	}

}
