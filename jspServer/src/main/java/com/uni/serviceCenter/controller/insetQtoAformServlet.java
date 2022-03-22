package com.uni.serviceCenter.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.serviceCenter.model.service.ServiceCenterService;
import com.uni.serviceCenter.model.vo.QtoA;

/**
 * Servlet implementation class insetQtoAformServlet
 */
@WebServlet("/insertQtoAform.do")
public class insetQtoAformServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insetQtoAformServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");//카테고리
		String title = request.getParameter("title");//제목
		String content = request.getParameter("content");//내용
		String writer = "1";//일단은 wirter = 1로 받는다 
		//String writer=String.valueOf(((Member)request.getSession().getAttribute("loginUser")).getUserNo());
		
		System.out.println(category +"   ==insertservlet에서 category");//제목 성공
		System.out.println(title.replaceAll("\u0020", "&nbsp;") +"   ==insertservlet에서 title");//제목 성공
		System.out.println(content.replaceAll("\n", "<br>").replaceAll("\u0020", "&nbsp;") +"   ==insertservlet에서 content");//제목 성공
		
		QtoA qa = new QtoA(writer,title.replaceAll("\u0020", "&nbsp;"), content.replaceAll("\n", "<br>").replaceAll("\u0020", "&nbsp;"), category);//qa에 받아온 값을 넣어준다 
		
		int result = new ServiceCenterService().insertQtoA(qa);
		RequestDispatcher view = request.getRequestDispatcher("views/service/serviceCenterQtoA.jsp");
		if(result>0) {
			request.setAttribute("close", "close");
		}else {
			request.setAttribute("msg", "비밀번호 변경에 실패했습니다.");
		}
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
