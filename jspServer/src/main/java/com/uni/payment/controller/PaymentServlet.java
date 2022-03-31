package com.uni.payment.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.payment.model.service.paymentService;
import com.uni.payment.model.vo.payment;

/**
 * Servlet implementation class usedPaymentServlet
 */
@WebServlet("/Itempayment")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String merchantUid = request.getParameter("merchant_uid");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int buyerNo = Integer.parseInt(request.getParameter("buyerNo"));
		int price = Integer.parseInt(request.getParameter("amount"));
		
		payment pay = new payment(merchantUid,buyerNo,boardNo,price);
		
		int result = new paymentService().Itempayment(pay);
		
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.print("success");
		} else {
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
