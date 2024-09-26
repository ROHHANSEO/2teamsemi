package com.uni.mypage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.mypage.model.service.MyPageService;
import com.uni.usedItemBoard.model.vo.UsedItemsBoard;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class likeProductPageServlet
 */
@WebServlet("/likeProductPage")
public class likeProductPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public likeProductPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNo = ((User)request.getSession().getAttribute("user")).getUserNo();
		System.out.println(userNo);
		
		ArrayList<UsedItemsBoard> list = new MyPageService().likeProductList(userNo);
		
		request.setAttribute("msg", "찜 리스트");
		
		if(!list.isEmpty()) {
			request.setAttribute("msg2", "true");
			request.setAttribute("likeProduct", list);
		} else {
			request.setAttribute("msg2", "void");
		}
		request.getRequestDispatcher("views/mypage/salesRecordPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
