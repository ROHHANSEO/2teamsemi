package com.uni.auction.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.auction.model.service.AuctionService;
import com.uni.usedItemBoard.model.vo.Category;

/**
 * Servlet implementation class AuctionItemInsertServlet
 */
@WebServlet("/insertAuction.do")
public class AuctionItemInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionItemInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Category> cList = new AuctionService().categoryList();
		//카테고리를 가져온다 
		
		
		request.setAttribute("category", cList);//대중소 카테고리를 다 가져온다.

		request.getRequestDispatcher("views/auction/auctionItemsInsertForm.jsp").forward(request, response); // 화면 전환
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
