package com.uni.event.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.event.model.service.EventService;
import com.uni.event.model.vo.Event;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class EventEnrollServlet
 */
@WebServlet("/insertEventPage.do")
public class EventEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String noticeTitle = request.getParameter("title");
		String noticeContent = request.getParameter("content");
		String userid = String.valueOf(((User)request.getSession().getAttribute("user")).getUserNo());
		System.out.println("______전______" + noticeContent);
		Event ev = new Event(noticeTitle, userid, noticeContent.replaceAll("\n", "<br>"));
		System.out.println("______후______" + noticeContent.replaceAll("\n", "<br>"));
		System.out.println("title===" + noticeTitle);
		int result = new EventService().insertNotice(ev);
		
		if(result>0) {
			request.getSession().setAttribute("msg", "공지사항이 성공적으로 등록되었습니다.");
			response.sendRedirect("eventpage.do");
		}else {
			request.setAttribute("msg", "공지사항등록실패");
			request.getRequestDispatcher("view/common/errorPage.jsp").forward(request, response);
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
