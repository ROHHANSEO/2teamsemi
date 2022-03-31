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
		
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		String writer=String.valueOf(((User)request.getSession().getAttribute("user")).getUserNo());
		
		System.out.println(title +"   ==insertservlet에서");//제목 성공
		//System.out.println("_____전______ "+ content);
		//category, title,content,writer
		Event sc = new Event(category,title.replaceAll("\u0020", "&nbsp;"),content.replaceAll("\n", "<br>").replaceAll("\u0020", "&nbsp;"));
		//System.out.println("_____후______ "+  title.replaceAll("\n", "<br>"));
		//System.out.println(category + "===insertservlet category들어왔나");
		
		int result = new EventService().insertnotice(sc);
		if(result > 0) {
			request.getSession().setAttribute("msg", "게시글 등록 성공");
			response.sendRedirect("eventpage.do");
		}else {
			request.setAttribute("msg","고객센터 글 작성이 실패하였습니다.");
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
