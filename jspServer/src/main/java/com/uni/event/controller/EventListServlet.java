	package com.uni.event.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.event.model.service.EventService;
import com.uni.event.model.vo.Event;
import com.uni.event.model.vo.PageInfo;

/**
 * Servlet implementation class EventListServlet
 */
@WebServlet("/eventpage.do")
public class EventListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			//페이징 처리 
		
			int listCount; 		// 총게시글 갯수 
			int currentPage; 	// 현재페이지 (요청한 페이지)
			int startPage; 		// 현재 페이지 하단에 보여지는 페이징바의 시작수 
			int endPage; 		// 현재 페이지 하단에 보여지는 페이징바의 끝수
			int maxPage; 		// 전체 페이지의 가장마지막페이지
				
			int pageLimit; 		// 한페이지 하단에 보여질 페이지 최대갯수
			int boardLimit; 	// 한페이지에 보여질 게시글 최대갯수 
				
			//총개시글 갯수 
			listCount = new EventService().getListCount();
				
			//현재페이지
			currentPage = 1;
				
			//페이지 전환시 전달받은 페이지가 있을경우 전달받은 페이지를 currentPage 에 담기
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
				
			//페이지 최대갯수
			pageLimit = 100;
			
			//게시글 최대갯수 
			boardLimit = 10;
				
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
				
			ArrayList<Event> list = new EventService().selectList(pi);
			System.out.println("list eventlistservlet =="+list);
		
		
		
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			
			System.out.println("pi ====="+pi);
			request.getRequestDispatcher("views/EventNotice/EventNoticePage.jsp").forward(request, response);
		}
			

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
