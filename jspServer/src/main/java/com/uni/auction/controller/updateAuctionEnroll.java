package com.uni.auction.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.auction.model.service.AuctionService;
import com.uni.auction.model.vo.Auction;
import com.uni.auction.model.vo.AuctionAttachment;

/**
 * Servlet implementation class updateAuctionEnroll
 */
@WebServlet("/updateAuctionEnroll.do")
public class updateAuctionEnroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateAuctionEnroll() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int scno = Integer.parseInt(request.getParameter("scno"));//게시물 번호 
		System.out.println(scno + "게시물 번호 ");
		
		Auction ac = new AuctionService().selectAuction(scno);//디테일 정보를 가져오기 
		ArrayList<AuctionAttachment> at = new AuctionService().selectAttachment(scno);//사진들 가져오기 
		String category  = new AuctionService().selectAllcategory(ac.getCategorycode());//카테고리 넘버 
		System.out.println(ac + "수정 정보 ");
		System.out.println("수정 할 사진 !!"+ at);
		System.out.println("카테고리 수정" + category);
	
		
		if(ac != null) {
			request.setAttribute("ac", ac);
			request.setAttribute("at", at);
			request.setAttribute("category", category);
			request.getRequestDispatcher("views/auction/auctionUpdateForm.jsp").forward(request, response);
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
