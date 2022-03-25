package com.uni.event.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.event.model.service.EventService;
import com.uni.event.model.vo.Event;

/**
 * Servlet implementation class detailEventServlet
 */
@WebServlet("/detailEvent.do")
public class detailEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public detailEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		Event event = new EventService().selectEvent(nno);
		System.out.println("nno===" + nno );
		String view = "";
		if(event != null) {
			request.setAttribute("event", event);
			view = "views/EventNotice/EventDetailView.jsp";
		}else {
			request.setAttribute("msg", "이벤트&공지사항 조회에 실패했습니다.");
			view = "views/common/errorPage.jsp";
		}
		request.getRequestDispatcher(view).forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
