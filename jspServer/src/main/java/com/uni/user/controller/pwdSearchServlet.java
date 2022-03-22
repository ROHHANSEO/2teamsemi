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
 * Servlet implementation class pwdSearchServlet
 */
@WebServlet("/pwdSearchServlet")
public class pwdSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pwdSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		String citiNo = request.getParameter("citiNumber");
		String phone = request.getParameter("phone");
		
		User user = new UserService().pwdSearch(new User(userId,name,citiNo,phone));
		
		if(user != null) {
			request.setAttribute("msg", "비밀번호 수정");
			request.getRequestDispatcher("views/login/UserSuccsse.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "비밀번호 찾기");
			request.setAttribute("msg2", "등록된 정보가 일치하지 않습니다.");
			request.getRequestDispatcher("views/login/UserSuccsse.jsp").forward(request, response);
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
