package com.uni.usedItemBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.usedItemBoard.model.service.UsedItemsBoardService;
import com.uni.usedItemBoard.model.vo.LikeProduct;
import com.uni.usedItemBoard.model.vo.UsedAttachment;
import com.uni.usedItemBoard.model.vo.UsedItemsBoard;

/**
 * Servlet implementation class DetailUsedBoardServlet
 */
@WebServlet("/detailview.do")
public class DetailUsedBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailUsedBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		System.out.println(bNo);
		
		UsedItemsBoard ub = new UsedItemsBoardService().selectUsedBoard(bNo);
		ArrayList<UsedAttachment> ua = new UsedItemsBoardService().selectAttachment(bNo);
		ArrayList<LikeProduct> like = new UsedItemsBoardService().selectLike(bNo);
		
		System.out.println("서블렛 ub =>" + ub);
		System.out.println("서블렛 ua =>" + ua);
		System.out.println("서블렛 like =>" + like);
		
		if(ub != null && !ua.isEmpty()) {
			request.setAttribute("ub", ub);
			request.setAttribute("fileList", ua);
			request.setAttribute("like", like);
			
			request.getRequestDispatcher("views/used_item_board/usedItemDetail.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "게시물 등록 실패");
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
