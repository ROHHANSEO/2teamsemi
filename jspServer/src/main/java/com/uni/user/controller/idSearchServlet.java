package com.uni.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.user.model.service.UserService;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class idSearchServlet
 */
@WebServlet("/idSearch")
public class idSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public idSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String citiNo = request.getParameter("citiNumber");
		String phone = request.getParameter("phone");
		
		User user = new UserService().idSearch(new User(name,citiNo,phone));
		
		if(user != null) {
			request.setAttribute("userId", user);
			request.setAttribute("msg", "아이디 찾기");
			request.setAttribute("msg2", "아이디");
		} else {
			request.setAttribute("msg", "아이디 찾기");
			request.setAttribute("msg2", "false");
			request.setAttribute("result", "등록된 정보가 일치하지 않습니다.");
		}
		request.getRequestDispatcher("views/login/UserSuccsse.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
