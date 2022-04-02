package com.uni.chatting.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.chatting.model.service.ChattingService;
import com.uni.chatting.model.vo.Chatting;

/**
 * Servlet implementation class chattingCheckServlet
 */
@WebServlet("/chattingCheck.do")
public class chattingCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chattingCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo =  Integer.parseInt(request.getParameter("boardNo"));//게시글 넘버
		int sendperson = Integer.parseInt(request.getParameter("sendperson"));//보낸 사람
		int answerperson = Integer.parseInt(request.getParameter("answerperson"));// 받는 사람
		//오리지날
		Chatting ct = new Chatting(boardNo, sendperson, answerperson);
		
		System.out.println("채팅 확인니이이니ㅣ"+ct);
		
		int result = new ChattingService().checkChatting(ct);//받는사람-> 게시글 작성자, 보낸사람 -> 채팅 누른사람
		
		PrintWriter out = response.getWriter();
		if(result  >0) {//채팅방이 있을때
			out.print("already");
		}else {//새로 생성해야할 때 
			out.print("new");
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
