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
import com.uni.usedItemBoard.model.vo.PageInfo;
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
		
		// 페이징 처리
		int listCount; // 총 게시글 개수
		int currentPage; //현재 페이지(요청한 페이지) 
		int startPage; // 현재 페이지 하단에 보여지는 페이지 시작 수(맨 왼쪽 페이지 번호 시작수)
		int endPage; // 현재 페이지 하단에 보여지는 페이징 바의 끝 수
		int maxPage; // 전체페이지의 마지막 페이지인 수
		
		int pageLimit; // 한 페이지 하단에 보여진 페이지 최대 갯수
		int boardLimit; // 한 페이지당 보여질 게시글 최대 갯수
		
		// 총 게시글 갯수
		listCount = new UsedItemsBoardService().getCountList();
		
		//현재 페이지
		currentPage = 1;
		
		//현재 가고싶은 페이지를 가고싶을때 currentPage 바꾸면되지
		//페이지 전환시 전달받은 페이지가 있을 경우 전달받은 페이지를 currentPage 에 담아요
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 페이지 최대 갯수 재 정의
		pageLimit = 5;
		
		// 게시글 최대 갯수 재 정의
		boardLimit = 16;
		
		// listCount 와 boardLimit를 나누어 double타입으로 형변환 하여 Math.ceil을 이용해 올림 후 int로 형변환 하기
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		// * startPage : 현재 페이지에 보여지는 페이징 바의 시작 수
		
		// currentPage(현재페이지)에서 -1을 한 값을 pageLimit(페이지 최대 갯수)와 pageLimit(페이지 최대 갯수)를 곱하고 1을 더한값과 나누어 담기
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		// startPage startPage(페이지 시작 수)와 pageLimit(한 페이지 하단에 보여진 페이지 최대 갯수)를 더하고 - 1을 해 담는다
		endPage = startPage + pageLimit - 1;

		// 단, 예를 들어 maxPage가 13이면 endPage도 13으로 만든다
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		// 페이지인포 객체를 생성 
		PageInfo pi = new PageInfo(listCount, currentPage, startPage, endPage, maxPage, pageLimit, boardLimit);
		
		String category = request.getParameter("cate");
		int minprice = Integer.parseInt(request.getParameter("minprice"));
		int maxprice = Integer.parseInt(request.getParameter("maxprice"));
		String search = request.getParameter("search");
		String except = (Boolean.parseBoolean(request.getParameter("except")) == true)? "거래완료":"";
		
		System.out.println(category);
		System.out.println(minprice);
		System.out.println(maxprice);
		System.out.println(search);
		System.out.println(except);
		
		// 필터를 거친 리스트 객체 생성
		ArrayList<UsedItemsBoard> list = new UsedItemsBoardService().filteringList(category, minprice, maxprice, search, except, pi);
		
		if(!list.isEmpty()) {
			response.setContentType("application/json; charset=utf-8"); // 꼭 이렇게 응답해야한다
			new Gson().toJson(list, response.getWriter()); // 응답할 리스트적기 
		}else {
			
			String msg = "결과가 일치한 게시물이 없습니다.";
			response.getWriter().print(msg);
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
