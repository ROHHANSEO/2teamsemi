package com.uni.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.admin.model.service.QtoAService;
import com.uni.admin.model.service.Reply;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class ReplyInsertServlet
 */
@WebServlet("/replyInsert.do")
public class ReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("content");
		System.out.println(content + "1:1 질문 답변");
		int scno = Integer.parseInt(request.getParameter("scno"));//질문 넘버 
		String writer=String.valueOf(((User)request.getSession().getAttribute("user")).getUserNo());
		
		System.out.println(writer+ "유저의 넘버");
		Reply r = new Reply();
		r.setReplyContent(content);
		r.setRefQuestion(scno);
		r.setReplyWriter(writer);
		
		int result = new QtoAService().insertReply(r);
		
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
