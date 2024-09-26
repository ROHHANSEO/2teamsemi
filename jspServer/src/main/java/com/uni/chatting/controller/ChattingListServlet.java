package com.uni.chatting.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uni.chatting.model.service.ChattingService;
import com.uni.chatting.model.vo.ChattingLog;

/**
 * Servlet implementation class ChattingListServlet
 */
@WebServlet("/chattingList.do")
public class ChattingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChattingListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int chatNo = Integer.parseInt(request.getParameter("chatNo"));//채팅방 넘버
		System.out.println("채팅방 번호 넘버"+chatNo);
		ArrayList<ChattingLog> list = new ChattingService().selectCList(chatNo);
		System.out.println("채팅 리스트 "+list);
		response.setContentType("application/json; charset = utf-8");//배열이면 이거 해줘야 한다
		new Gson().toJson(list, response.getWriter());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
