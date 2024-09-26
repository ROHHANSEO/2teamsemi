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
 * Servlet implementation class EventUpdateServlet
 */
@WebServlet("/updateNotice.do")
public class EventUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Event event = new Event(); //notice 객체를 불러올 것을 담아준다. 각 형식에 맞춰서 작업한다.
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		//설정해준다.
		event.setNoticeTitle(title);
		event.setNoticeContent(content);
		event.setNoticeno(nno);
		
		// 형식에 맞추어서 보낸준다.
		int result = new EventService().updateNotice(event);
		
		if(result > 0 ) {
			request.getSession().setAttribute("msg", "공지사항이 성공적으로 수정되었습니다. ");
			response.sendRedirect("eventpage.do?nno="+nno);
		}else {
			request.setAttribute("msg", "공지사항수정실패");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
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
