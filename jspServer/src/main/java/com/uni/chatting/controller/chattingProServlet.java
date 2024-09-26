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
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class chattingPro
 */
@WebServlet("/chattingPro.do")
public class chattingProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chattingProServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sendp = Integer.parseInt(request.getParameter("sendp"));//보낸 사람
		int ansp = Integer.parseInt(request.getParameter("ansp"));// 받는 사람
		String nick = request.getParameter("nick");//게시물 작성자
		String nick2 = request.getParameter("nick2");//현재 조회자 
		Chatting ct = new Chatting(sendp, ansp, nick, nick2);
		System.out.println("채팅 확인니이이니ㅣ"+ct);
	
		
		int result = new ChattingService().addNewChatting(ct);//채팅 생성
		
		int chat = new ChattingService().findChattingNo(sendp, ansp);//채팅 넘버
		
		if(result>0) {
			ct.setChatNo(chat);
			request.setAttribute("ct", ct);
			RequestDispatcher view = request.getRequestDispatcher("views/chatting/chatting.jsp");
			view.forward(request, response);
			
		}else {
			request.setAttribute("msg", "채팅 연결 실패");
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
