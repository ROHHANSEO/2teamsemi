package com.uni.usedItemBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.uni.usedItemBoard.model.service.UsedItemsBoardService;
import com.uni.usedItemBoard.model.vo.Category;

/**
 * Servlet implementation class CategoryLargeServlet
 */
@WebServlet("/selectLargeBefore.do")
public class CategoryLargeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryLargeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Category> large = new UsedItemsBoardService().selectCategory();
		
		System.out.println("서블렛 미들 셀렉트"+large);
		if(!large.isEmpty()) {
			response.setContentType("application/json; charset=utf-8"); // 꼭 이렇게 응답해야한다
			new Gson().toJson(large, response.getWriter()); // 응답할 리스트적기 
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
