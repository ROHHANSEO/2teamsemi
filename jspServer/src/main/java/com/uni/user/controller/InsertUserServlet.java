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
 * Servlet implementation class InsertUserServlet
 */
@WebServlet("/insertUser")
public class InsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String name = request.getParameter("name");
		String citiNumber = request.getParameter("citiNumber");
		String phone = request.getParameter("phone");
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		User user = new User(userId,userPwd,name,citiNumber,phone,nickName,email,gender);
		System.out.println(user);
		
		int result = new UserService().insertUser(user);
		
		if(result > 0) {
			request.setAttribute("name", name);
			request.setAttribute("msg", " 간편 회원가입");
			request.getRequestDispatcher("views/login/UserSuccsse.jsp").forward(request, response);
		} else {
			request.setAttribute("mag", "회원가입 실패");
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
