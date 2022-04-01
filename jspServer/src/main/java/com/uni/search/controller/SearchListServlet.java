package com.uni.search.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.auction.model.service.AuctionService;
import com.uni.auction.model.vo.Auction;
import com.uni.event.model.service.EventService;
import com.uni.event.model.vo.Event;
import com.uni.usedItemBoard.model.service.UsedItemsBoardService;
import com.uni.usedItemBoard.model.vo.UsedItemsBoard;

/**
 * Servlet implementation class SearchListServlet
 */
@WebServlet("/searchList.do")
public class SearchListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String search = request.getParameter("search");
		System.out.println(search);
		ArrayList<Event> eList = new EventService().SearchfiveList(search);
		ArrayList<UsedItemsBoard> ubList = new UsedItemsBoardService().SearchfiveList(search);
		ArrayList<Auction> acList = new AuctionService().SearchfiveList(search);
		
		System.out.println("서치 eList 서블릿 ->" + eList);
		System.out.println("서치 ubList 서블릿 ->" + ubList);
		System.out.println("서치 acList 서블릿 ->" + acList);
		
		request.setAttribute("search", search);
		request.setAttribute("eList", eList);
		request.setAttribute("ubList", ubList);
		request.setAttribute("acList", acList);
		
		request.getRequestDispatcher("views/search/combineSearchList.jsp").forward(request, response);
		
	}

}
