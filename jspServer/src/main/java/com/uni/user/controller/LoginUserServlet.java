package com.uni.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uni.user.model.service.UserService;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class LoginUserServlet
 */
@WebServlet("/loginUser")
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("password");
		//checkbox 값은 체크시 on 미체크시 null 출력
		String check = request.getParameter("loginCip");
		
		User user = new UserService().loginUser(new User(userId, userPwd));
		
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("userPwd", userPwd);
			response.sendRedirect("/");
		} else {
			request.setAttribute("msg", "로그인에 실패했습니다");
			request.getRequestDispatcher("views/common/eroorPage.jsp").forward(request, response);
			
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
