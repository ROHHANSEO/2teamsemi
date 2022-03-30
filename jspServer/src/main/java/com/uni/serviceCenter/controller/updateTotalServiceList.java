package com.uni.serviceCenter.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.serviceCenter.model.service.ServiceCenterService;
import com.uni.serviceCenter.model.vo.ServiceCenter;

/**
 * Servlet implementation class updateTotalServiceList
 */
@WebServlet("/updateTotalServiceList.do")//수정하기 버튼을 눌렀을 시에 나오는 창
public class updateTotalServiceList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateTotalServiceList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int scno = Integer.parseInt(request.getParameter("scno"));
		System.out.println(scno +" 수정하기 버튼 눌렀을 시에 서블렛updateTotalserviceList");
		
		ServiceCenter sc = new ServiceCenterService().selectServiceCenter(scno);
		
		if(sc != null) {
			request.setAttribute("sc", sc);
			request.getRequestDispatcher("views/service/updateServiceForm.jsp").forward(request, response);
		}else {
			request.setAttribute("msg","수정할 게시글을 불러오는데 실패했습니다.");
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
