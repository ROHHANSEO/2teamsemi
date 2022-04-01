package com.uni.auction.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.uni.auction.model.service.AuctionService;
import com.uni.usedItemBoard.model.vo.Category;

/**
 * Servlet implementation class CategoryMiddleServlet
 */
@WebServlet("/largeSelectA.do")
public class CategoryMiddleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryMiddleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category = request.getParameter("category");
		ArrayList<Category> middle = new AuctionService().selectMiddle(category);
		
		//System.out.println("서블렛 미들 셀렉트"+middle);
		if(!category.isEmpty()) {
			response.setContentType("application/json; charset=utf-8"); // 꼭 이렇게 응답해야한다
			new Gson().toJson(middle, response.getWriter()); // 응답할 리스트적기 
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
