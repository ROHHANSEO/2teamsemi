package com.uni.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
		String arr = request.getParameter("arr");
		System.out.println(arr);
		String userPwd = null;
		String nickName = null;
		String phone = null;
		String email = null;
		
		
		
		try {
			JSONParser parse = new JSONParser();
			JSONObject data = (JSONObject)parse.parse(arr);
			
			if(data.containsKey("userPwd")) {
				userPwd = (String) data.get("userPwd");
			}
			if(data.containsKey("nickName")) {
				nickName = (String) data.get("nickName");
			}
			if(data.containsKey("phone")) {
				nickName = (String) data.get("phone");
			}
			if(data.containsKey("email")) {
				nickName = (String) data.get("email");
			}
			
			
			
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
