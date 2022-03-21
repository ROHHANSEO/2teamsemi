package com.uni.serviceCenter.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.serviceCenter.model.service.ServiceCenterService;
import com.uni.serviceCenter.model.vo.ServiceCenter;

/**
 * Servlet implementation class insertServiceCenter
 */
@WebServlet("/insertServiceCenter.do")
public class insertServiceCenter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertServiceCenter() {
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
		String writer = "1";//일단은 wirter = 1로 받는다 
		//String writer=String.valueOf(((Member)request.getSession().getAttribute("loginUser")).getUserNo());
		
		System.out.println(title +"   ==insertservlet에서");//제목 성공
		System.out.println("_____전______ "+ content);
		//category, title,content,writer
		ServiceCenter sc = new ServiceCenter(category,title.replaceAll("\u0020", "&nbsp;"),content.replaceAll("\n", "<br>").replaceAll("\u0020", "&nbsp;"), writer);
		System.out.println("_____후______ "+  title.replaceAll("\n", "<br>"));
		System.out.println(category + "===insertservlet category들어왔나");
		
		int result = new ServiceCenterService().insertServiceCenter(sc);
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "고객센터 글이 성공적으로 등록되었습니다.");
			request.getRequestDispatcher("views/service/serviceCenter.jsp").forward(request, response);
		}else {
			request.setAttribute("msg","고객센터 글 작성이 실패하였습니다.");
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
