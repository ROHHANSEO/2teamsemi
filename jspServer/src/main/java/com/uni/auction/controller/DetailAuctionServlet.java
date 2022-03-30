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
 * Servlet implementation class DetailAuctionServlet
 */
@WebServlet("/detailAuction.do")
public class DetailAuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailAuctionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int scno = Integer.parseInt(request.getParameter("scno"));
		System.out.println(scno +"scno 옥션 디테일");
		
		Auction ac = new AuctionService().selectAuction(scno);
		ArrayList<AuctionAttachment> at = new AuctionService().selectAttachment(scno);
		
		System.out.println("디테일 옥션 + "+ ac);
		//System.out.println("디테일 옥션 + "+ at);
		
		if(ac != null & !at.isEmpty()) {
			request.setAttribute("ac", ac);//옥션 정보 
			request.setAttribute("fileList", at);//사진 정보들 
			
			request.getRequestDispatcher("views/auction/auctionDetail.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "게시물 등록 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
