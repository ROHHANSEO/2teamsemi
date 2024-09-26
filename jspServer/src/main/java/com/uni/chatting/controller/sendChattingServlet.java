package com.uni.chatting.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.chatting.model.service.ChattingService;
import com.uni.chatting.model.vo.ChattingLog;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class sendChattingServlet
 */
@WebServlet("/sendChatting.do")
public class sendChattingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendChattingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("content");//내용
		int chatNo =Integer.parseInt(request.getParameter("chatNo"));//채팅방 넘버
		String writer=String.valueOf(((User)request.getSession().getAttribute("user")).getUserNo());
		
		System.out.println("대화 내용"+content);
		System.out.println("채팅방 넘버"+chatNo);
		
		ChattingLog cl = new ChattingLog();
		cl.setChatCont(content);
		cl.setCahtNo(chatNo);//연결된 채팅방 넘버
		cl.setUserNo(writer);//댓글 작성자 
		
		int result = new ChattingService().insertChat(cl);
		

		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.print("success");
		}else {
			out.print("fail");
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
