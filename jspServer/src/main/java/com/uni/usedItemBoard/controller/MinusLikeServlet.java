package com.uni.usedItemBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.uni.usedItemBoard.model.service.UsedItemsBoardService;

/**
 * Servlet implementation class MinusLikeServlet
 */
@WebServlet("/notLike.do")
public class MinusLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MinusLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		int uNo = Integer.parseInt(request.getParameter("uNo"));
		int result = new UsedItemsBoardService().minusLike(bNo, uNo);
		
		if(result > 0) {
			String msg = "업데이트 성공";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(msg); // 응답할 리스트적기 
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
