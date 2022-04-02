package com.uni.chatting.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.chatting.model.service.ChattingService;
import com.uni.chatting.model.vo.Chatting;

/**
 * Servlet implementation class AlreadyChattingServlet
 */
@WebServlet("/alreaychat.do")
public class AlreadyChattingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlreadyChattingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno =  Integer.parseInt(request.getParameter("bno"));//게시글 넘버
		int sendp = Integer.parseInt(request.getParameter("sendp"));//보낸 사람
		int ansp = Integer.parseInt(request.getParameter("ansp"));// 받는 사람
		
		Chatting act = new ChattingService().findChatting(sendp, ansp);//보낸사람 아이디
		act.setBoardNo(bno);
		act.setSendP(sendp);
		act.setAnswP(ansp);
		
		System.out.println("채팅 확인니이이니ㅣ"+act);
		request.setAttribute("act", act);
		RequestDispatcher view = request.getRequestDispatcher("views/chatting/chatting.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
