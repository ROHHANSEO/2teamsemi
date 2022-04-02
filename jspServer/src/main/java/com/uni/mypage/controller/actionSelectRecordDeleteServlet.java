package com.uni.mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.mypage.model.service.MyPageService;

/**
 * Servlet implementation class actionSelectRecordDeleteServlet
 */
@WebServlet("/actionSelectDelete")
public class actionSelectRecordDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public actionSelectRecordDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] bnoArr = request.getParameterValues("bnoArr");
		
		for(int i = 0; i < bnoArr.length; i++) {
			System.out.println(bnoArr[i]);
		}
		
		int result = new MyPageService().actionSelectDelete(bnoArr);
		
		PrintWriter out = response.getWriter();
		if (result <= 0) {
			out.print("success");
		} else {
			out.print("fail");
		}

		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
