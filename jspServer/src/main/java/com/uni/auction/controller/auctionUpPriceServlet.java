package com.uni.auction.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.uni.auction.model.service.AuctionService;
import com.uni.auction.model.vo.Auction;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class auctionUpPriceServlet
 */
@WebServlet("/changeprice.do")
public class auctionUpPriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public auctionUpPriceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dealer = String.valueOf(((User)request.getSession().getAttribute("user")).getUserNo());//버튼 누른사람
		int scno = Integer.parseInt(request.getParameter("scno"));//게시글 번호
		int changeP = Integer.parseInt(request.getParameter("changeP"));//바뀔값(현재값+올릴값)
		System.out.println(scno);//확인완료
		Auction a = new Auction();
		a.setAuctionNo(scno);
		a.setAuctionWriter(dealer);
		a.setItemPrice(changeP);//바뀔값
		
		int result = new AuctionService().changePrice(a);
		System.out.println(changeP);
		if (result >0 ) {
			response.setContentType("application/json; charset=utf-8"); // 꼭 이렇게 응답해야한다
			new Gson().toJson(a, response.getWriter()); // 응답할 리스트적기
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
