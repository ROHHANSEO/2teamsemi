package com.uni.usedItemBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.uni.admin.model.vo.BlockBoard;
import com.uni.usedItemBoard.model.service.UsedItemsBoardService;

/**
 * Servlet implementation class ReportInsertUsedItemBoardServlet
 */
@WebServlet("/insertUsedReportform.do")
public class ReportInsertUsedItemBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportInsertUsedItemBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		String bTitle = request.getParameter("bTitle").replaceAll("\u0020", "&nbsp;");
		String title = request.getParameter("title").replaceAll("\u0020", "&nbsp;");
		String category = request.getParameter("category");
		String content = request.getParameter("content").replaceAll("\n", "<br>").replaceAll("\u0020", "&nbsp;");
		
		BlockBoard bb = new BlockBoard(title, bNo, content, bTitle, category);
		
		int result = new UsedItemsBoardService().reportUsedItemBoard(bb);
		
		response.setContentType("application/json; charset=utf-8"); // 꼭 이렇게 응답해야한다
		new Gson().toJson(result, response.getWriter()); // 응답할 리스트적기 
		
	}

}
