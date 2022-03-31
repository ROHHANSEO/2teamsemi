package com.uni.usedItemBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.usedItemBoard.model.service.UsedItemsBoardService;
import com.uni.usedItemBoard.model.vo.Category;
import com.uni.usedItemBoard.model.vo.UsedAttachment;
import com.uni.usedItemBoard.model.vo.UsedItemsBoard;

/**
 * Servlet implementation class UpdateUsedItemsBoardEnrollServlet
 */
@WebServlet("/updateUsedEnroll.do")
public class UsedItemsBoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsedItemsBoardEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		
		UsedItemsBoard ub = new UsedItemsBoardService().selectUsedBoard(bNo);
		ArrayList<UsedAttachment> ua = new UsedItemsBoardService().selectAttachment(bNo);
		ArrayList<Category> cList = new UsedItemsBoardService().selectAllCategory();
		System.out.println("인롤 서블릿 allcategory ==> " + cList);
		System.out.println("인롤 서블릿 ub ==> " + ub);
		System.out.println("인롤 서블릿 ua ==> " + ua);
		
		if(ub != null) {
			request.setAttribute("ub", ub);
			request.setAttribute("ua", ua);
			request.setAttribute("cList", cList);
			request.getRequestDispatcher("views/used_item_board/usedItemsEnrollForm.jsp").forward(request, response);
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
