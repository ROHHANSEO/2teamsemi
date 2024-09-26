package com.uni.auction.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.admin.model.vo.BlockBoard;
import com.uni.auction.model.service.AuctionService;

/**
 * Servlet implementation class insertBlockAuctionServlet
 */
@WebServlet("/insertABlockform.do")
public class insertBlockAuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertBlockAuctionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String category = request.getParameter("category");//카테고리
		String title = request.getParameter("title").replaceAll("\u0020", "&nbsp;");//제목
		String content = request.getParameter("content").replaceAll("\u0020", "&nbsp;").replaceAll("\n", "<br>");//내용
		String btitle = request.getParameter("btitle").replaceAll("\u0020", "&nbsp;");//내용
		int scno  =Integer.parseInt(request.getParameter("scno"));//게시글 번호
		System.out.println("category 카테고리 " + category);
		System.out.println("title 제목 " + title);
		System.out.println("content 내용 " + content);
		System.out.println("scno 게시글번호 " + scno);
		System.out.println("btitle 게시글 제목 " + btitle);
		
		 
		BlockBoard bb = new BlockBoard();
		bb.setContent(content);//내용
		bb.setCategoryNm(category);//신고 카테고리
		bb.setBoardNo(scno);//게시글 번호
		bb.setTitle(title);//신고 타이틀
		bb.setBoardTitle(btitle);//게시글 제목
		
		System.out.println(bb+"서블렛에서 ");
		int result = new AuctionService().insertBlock(bb);
		
		//System.out.println(result +"서버 통신 성고후에ㅔㄷ지;ㅈㄱㄷ");
		if(result>0) {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(result);
		}else {
			request.setAttribute("msg", "비밀번호 변경에 실패했습니다.");
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
