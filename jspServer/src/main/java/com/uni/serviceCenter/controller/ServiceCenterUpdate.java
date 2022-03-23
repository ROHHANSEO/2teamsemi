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
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class ServiceCenterUpdate
 */
@WebServlet("/updateServiceCenter.do")
public class ServiceCenterUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceCenterUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int scno = Integer.parseInt(request.getParameter("scno"));
		String writer=String.valueOf(((User)request.getSession().getAttribute("user")).getUserNo());
		System.out.println(title +"   ==insertservlet에서");//제목 성공
		
		System.out.println("_____전______ "+ content);
		//category, title,content,writer
		ServiceCenter updateSC = new ServiceCenter();
		updateSC.setCategory(category);
		updateSC.setServiceTitle(title.replaceAll("\u0020", "&nbsp;"));
		updateSC.setServiceContent(content.replaceAll("\n", "<br>").replaceAll("\u0020", "&nbsp;"));
		updateSC.setServiceNo(scno);
		updateSC.setServiceWriter(writer);
		System.out.println("updateSC 알아보기 "+updateSC);
		int result =  new ServiceCenterService().updateSC(updateSC);
		
		
		if(result >0) {
			request.getSession().setAttribute("msg", "성공적으로 글을 수정하였습니다.");
			
			response.sendRedirect("serviceCenter.do");
		}else {
			request.setAttribute("msg", "글 수정에 실패하였습니다.");
			
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
