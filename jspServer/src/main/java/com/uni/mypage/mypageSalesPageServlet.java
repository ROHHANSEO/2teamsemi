package com.uni.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.usedItemBoard.model.service.UsedItemsBoardService;
import com.uni.usedItemBoard.model.vo.UsedItemsBoard;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class mypageSalesPageServlet
 */
@WebServlet("/salesRecordPage")
public class mypageSalesPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mypageSalesPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNo = ((User)request.getSession().getAttribute("user")).getUserNo();
		ArrayList<UsedItemsBoard> list = new UsedItemsBoardService().myPostList(userNo);
		
		request.setAttribute("msg", "판매기록");
		if(list.isEmpty()) {
			request.setAttribute("content", list);
			request.getRequestDispatcher("views/mypage/VoidRecordPage.jsp").forward(request, response);
		} else {
			request.setAttribute("salesList", list);
			request.getRequestDispatcher("views/mypage/salesRecordPage.jsp").forward(request, response);
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
