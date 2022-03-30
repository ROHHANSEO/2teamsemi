package com.uni.event.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.event.model.service.EventService;
import com.uni.event.model.vo.Event;

/**
 * Servlet implementation class UpdateEventServlet
 */
@WebServlet("/updateEvent.do")
public class UpdateEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		Event event = new EventService().selectEvent(nno); 
		
		System.out.println("nno확인 ======" + nno );
		RequestDispatcher page = null; // 화면 출력
		if(event != null) { 
			request.setAttribute("event", event); 
			page = request.getRequestDispatcher("views/EventNotice/EventUpdate.jsp");
			
		}else {
			request.setAttribute("msg", "공지사항 수정 하기를 진행할수 없습니다.");
			page = request.getRequestDispatcher("views/common/errorPage.jsp");
		}
		page.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
