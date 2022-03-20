package com.uni.serviceCenter.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.uni.serviceCenter.model.service.ServiceCenterService;
import com.uni.serviceCenter.model.vo.ServiceCenter;

/**
 * Servlet implementation class findServiceCenterListServlet
 */
@WebServlet("/findserviceCenter.do")
public class findServiceCenterListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findServiceCenterListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("input");
		String selectinput = request.getParameter("selectinput");

		System.out.println("입력값selectinput findserviceCenterLIstServlet =====" + selectinput);
		System.out.println("입력값input findserviceCenterLIstServlet =====" + input.replaceAll("\u0020", "&nbsp;"));
		
		
		ArrayList<ServiceCenter> list = new ServiceCenterService().findCList(selectinput,input.replaceAll("\u0020", "&nbsp;"));
		System.out.println("list 값을 받아와야 한다 findserviceCenterLIstServlet ====="+ list);
		response.setContentType("application/json; charset = utf-8");
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
