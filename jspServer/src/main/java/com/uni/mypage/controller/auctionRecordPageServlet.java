package com.uni.mypage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.auction.model.vo.Auction;
import com.uni.mypage.model.service.MyPageService;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class auctionRecordPageServlet
 */
@WebServlet("/auctionRecordPage")
public class auctionRecordPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public auctionRecordPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNo = ((User)request.getSession().getAttribute("user")).getUserNo();
		
		ArrayList<Auction> list = new MyPageService().actionList(userNo);
		
		request.setAttribute("msg", "경매정산");
		
		if(!list.isEmpty()) {
			request.setAttribute("msg2", "true");
			request.setAttribute("action", list);
		} else {
			request.setAttribute("msg2", "void");
		}
		request.getRequestDispatcher("views/mypage/salesRecordPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
