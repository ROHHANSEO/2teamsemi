package com.uni.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uni.admin.model.service.QtoAService;
import com.uni.admin.model.service.Reply;

/**
 * Servlet implementation class ReplyListServlet
 */
@WebServlet("/replyList.do")//댓글 리스트 서블랫
public class ReplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int scno = Integer.parseInt(request.getParameter("scno"));//문의사항 넘버
		System.out.println(scno + "문의사항 넘버 들고오냐");
		ArrayList<Reply> list = new QtoAService().selectRList(scno);
		
		response.setContentType("application/json; charset = utf-8");//배열이면 이거 해줘야 한다
		Gson gson  = new GsonBuilder().setDateFormat("MM/dd/HH:MM").create();
		gson.toJson(list,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
