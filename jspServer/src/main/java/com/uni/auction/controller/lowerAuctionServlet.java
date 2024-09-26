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
import com.uni.auction.model.vo.PageInfo;
import com.uni.usedItemBoard.model.vo.Category;

/**
 * Servlet implementation class lowerAuctionServlet
 */
@WebServlet("/lowerAuction.do")
public class lowerAuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public lowerAuctionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int listCount; 		// 총게시글 갯수 
		int currentPage; 	// 현재페이지 (요청한 페이지)
		int startPage; 		// 현재 페이지 하단에 보여지는 페이징바의 시작수 
		int endPage; 		// 현재 페이지 하단에 보여지는 페이징바의 끝수
		int maxPage; 		// 전체 페이지의 가장마지막페이지
		
		int pageLimit; 		// 한페이지 하단에 보여질 페이지 최대갯수
		int boardLimit; 	// 한페이지에 보여질 게시글 최대갯수 
	
		//총개시글 갯수 
		listCount = new AuctionService().getListCount();
		
		//현재페이지
		currentPage = 1;
		
		//페이지 전환시 전달받은 페이지가 있을경우 전달받은 페이지를 currentPage 에 담기
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//페이지 최대갯수
		pageLimit = 10;

		//게시글 최대갯수 
		boardLimit = 5;
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		// * endPage : 현재 페이지에 보여지는 페이징 바의 끝 수
		// startPage : 1	=> endPage : 10
		// startPage : 11	=> endPage : 20
		
		endPage = startPage + pageLimit -1;
		
		// 단, 예를 들어 maxPage가 13 이면 endPage 도 13
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		// 페이지인포 객체를 생성 
		PageInfo pi = new PageInfo(listCount, currentPage, startPage, endPage, maxPage, pageLimit, boardLimit);
		
		ArrayList<Auction> aulist = new AuctionService().selectlowList(pi);
		//System.out.println("Alist 옥션 리스트 =="+aulist);
		ArrayList<Category> list = new AuctionService().categoryList();//카테고리 리스트 값 받아오기 
		// 담은 ArrayList 객체인 list를 속성에 담고
		request.setAttribute("aulist", aulist);
		// pi 객체 또한 속성에 담는다
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/auction/lowPAuctionListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
