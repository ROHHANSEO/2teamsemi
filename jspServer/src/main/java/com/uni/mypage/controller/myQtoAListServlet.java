package com.uni.mypage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.mypage.model.service.MyPageService;
import com.uni.serviceCenter.model.vo.QtoA;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class myQtoAListServlet
 */
@WebServlet("/myQtoAList")
public class myQtoAListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myQtoAListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNo = ((User)request.getSession().getAttribute("user")).getUserNo();
		
		ArrayList<QtoA> list = new MyPageService().myQtoAList(userNo);
		System.out.println(list);
		
		if(list != null) {
			request.setAttribute("list", list);
			request.setAttribute("msg", "true");
		} else {
			request.setAttribute("msg", "void");
		}
		
		request.getRequestDispatcher("views/mypage/QtoAListPage.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
