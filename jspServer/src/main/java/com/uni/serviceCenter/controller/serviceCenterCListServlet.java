package com.uni.serviceCenter.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.uni.serviceCenter.model.service.ServiceCenterService;
import com.uni.serviceCenter.model.vo.ServiceCenter;

/**
 * Servlet implementation class serviceCenterCListServlet
 */
@WebServlet("/serviceCenterCList.do")
public class serviceCenterCListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public serviceCenterCListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String input = request.getParameter("input");
		System.out.println(input +"serviceCListServlet==========");
		int result = 0;
		switch(input) {
		case "운영정책" : result = 1;break;
		case "계정/인증" : result = 2;break;
		case "구매/판매" : result = 3;break;
		case "거래 품목" : result = 4;break;
		case "거래 매너" : result = 5;break;
		case "이벤트" : result = 6;break;
		case "이용 제재" :result = 7;break;
		case "커뮤니티" : result = 8; break;
		case "경매" : result = 9;break;
		case "채팅" : result = 10;break;
		case "기타" : result = 11; break;
		}
		System.out.println(result +"serviceCListServlet result!");
		
		ArrayList<ServiceCenter> list = new ServiceCenterService().selectCList(result);

		System.out.println("serviceCenterCList  ====="+list);

		response.setContentType("application/json; charset = utf-8");
		
		new Gson().toJson(list, response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
