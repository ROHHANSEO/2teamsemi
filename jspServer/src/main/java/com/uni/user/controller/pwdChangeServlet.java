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
 * Servlet implementation class pwdChangeServlet
 */
@WebServlet("/pwdChange")
public class pwdChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pwdChangeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		String pwd = request.getParameter("userPwd");
		System.out.println(userNo);
		System.out.println(pwd);
		
		User user = new User();
		user.setUserNo(userNo);
		user.setUserPwd(pwd);
		
		int result = new UserService().pwdChange(user);
		
		if(result > 0) {
			request.setAttribute("msg", "비밀번호 변경");
			request.setAttribute("result", "true");
			request.getRequestDispatcher("views/login/UserSuccsse.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "비밀번호 변경 실패");
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
