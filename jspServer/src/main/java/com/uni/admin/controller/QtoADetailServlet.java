package com.uni.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.admin.model.service.QtoAService;
import com.uni.serviceCenter.model.vo.QtoA;

/**
 * Servlet implementation class QtoADetailServlet
 */
@WebServlet("/detailQtoA.do")
public class QtoADetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QtoADetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//QtoA의 디테일 페이지
		int scno = Integer.parseInt(request.getParameter("scno"));//question_no -> 문의 넘버
		System.out.println(scno +"====QtoADetailServlet에서 ");
		
		QtoA qa = new QtoAService().selectQtoA(scno);
		if(qa != null) {
			request.setAttribute("qa", qa);
			request.getRequestDispatcher("views/admin/QtoADetailView.jsp").forward(request, response);
		}else {
			request.setAttribute("msg","1:1문의사항 상세조회실패");
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
