package com.uni.mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.uni.user.model.service.UserService;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/userUpdate")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			JSONParser parse = new JSONParser();
			JSONObject data = (JSONObject)parse.parse(request.getParameter("jsonArr"));
			
			String userNo = (String) data.get("userNo");
			String userId = (String) data.get("userId");
			String userPwd = (String) data.get("userPwd");
			String userName = (String) data.get("userName");
			String citiNo = (String) data.get("citiNo");
			String phoneNo = (String) data.get("phoneNo");
			String nickName = (String) data.get("nickName");
			String email = (String) data.get("email");
			String gender = (String) data.get("gender");
			
			User user = new User(Integer.parseInt(userNo),userId,userPwd,userName,citiNo,phoneNo,nickName,email,gender);
			System.out.println(user);
			int result = new UserService().updateUser(user);
			System.out.println(result);
			PrintWriter out = response.getWriter();
			if (result > 0) {
				HttpSession session = request.getSession();
				if(!userPwd.equals((String)session.getAttribute("userPwd"))) {
					request.getSession().invalidate();
					Cookie cookie = new Cookie("loginCookie",null);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					out.print("password");
				} else {
					session.setAttribute("user", user);
					session.setAttribute("userPwd", userPwd);

				}
				out.print("success");
			} else {
				out.print("fail");
			}

			out.flush();
			out.close();
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
