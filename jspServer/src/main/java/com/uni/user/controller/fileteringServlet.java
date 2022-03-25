package com.uni.user.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.uni.usedItemBoard.model.service.UsedItemsBoardService;
import com.uni.usedItemBoard.model.vo.UsedItemsBoard;

/**
 * Servlet implementation class fileteringServlet
 */
@WebServlet("/fileterling.do")
public class fileteringServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fileteringServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category = request.getParameter("cate");
		String minprice = request.getParameter("minprice");
		String maxprice = request.getParameter("maxprice");
		String search = request.getParameter("search");
		String except = (request.getParameter("except") == "true")? "거래완료":"";
		
		System.out.println(category);
		System.out.println(minprice);
		System.out.println(maxprice);
		System.out.println(search);
		System.out.println(except);
		
		// 필터를 거친 리스트 객체 생성
		ArrayList<UsedItemsBoard> list = new UsedItemsBoardService().filteringList(category, minprice, maxprice, search, except);
		
		if(!list.isEmpty()) {
			response.setContentType("application/json; charset=utf-8"); // 꼭 이렇게 응답해야한다
			new Gson().toJson(list, response.getWriter()); // 응답할 리스트적기 
		}else {
			String msg = "결과가 일치한 게시물이 없습니다.";
			response.getWriter();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
