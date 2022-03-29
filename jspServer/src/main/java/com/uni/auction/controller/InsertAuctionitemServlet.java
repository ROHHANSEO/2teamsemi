package com.uni.auction.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.uni.auction.model.service.AuctionService;
import com.uni.auction.model.vo.Auction;
import com.uni.auction.model.vo.AuctionAttachment;
import com.uni.common.MyFileRenamePolicy;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class InsertAuctionitemServlet
 */
@WebServlet("/insertAuctionform.do")
public class InsertAuctionitemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAuctionitemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//multipart 됏는지 확인
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 10*1024*1024; //10Mbyte로 제한
			
			//저장할 경로파일지정
			// getRealPath : 절대 정로 파일
			String resouces = request.getSession().getServletContext().getRealPath("/resources");
			
			// 저장하는 경로 지정
			String savePath = resouces + "\\auction_upfiles\\";
			
			System.out.println("savePath : " + savePath);
			
			// MultipartRequest객체 생성								요청, 저장하는 경로, 최대 파일크기, 인코딩, 따로 파일의 이름을 정하는 객체
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
		
			// 작성자, 제목, 내용 받아오기
			String writer = String.valueOf(((User)request.getSession().getAttribute("user")).getUserNo());
			String title = multiRequest.getParameter("title").replaceAll("\u0020", "&nbsp;");
			String content = multiRequest.getParameter("content").replaceAll("\u0020", "&nbsp;").replaceAll("\n", "<br>"); 
			
			System.out.println("writer 서블렛 ==> "+writer);
			System.out.println("title 서블렛 ==> "+title);
			System.out.println("content 서블렛 ==> "+content);
			
			// 상품상태
			String productStatus = multiRequest.getParameter("productStatus");
			
			//경매 시작 가격
			int fprice = Integer.parseInt(multiRequest.getParameter("fprice"));
			
			//올릴 경매가
			int sprice = Integer.parseInt(multiRequest.getParameter("sprice"));
			
			//즉시 판매가
			int tprice = Integer.parseInt(multiRequest.getParameter("tprice"));
			
			// 카테고리 고유코드
			String categoryLarge = multiRequest.getParameter("large");
			String categoryMiddle = multiRequest.getParameter("middle");
			String categorySmall = multiRequest.getParameter("small");
			
			System.out.println("대분류 =>" + categoryLarge);
			System.out.println("중분류 =>" + categoryMiddle);
			System.out.println("소분류 =>" + categorySmall);
			
			Auction ub = null;
			// 대분류만 선택시
			if(categoryMiddle.equals("중분류")) {
				// 보드 객체생성 및 담기(아직 안담음)
				System.out.println("대분류만 선택 되었습니다.");
				ub = new Auction(categoryLarge, writer, title, content, productStatus,fprice, sprice, tprice);
				System.out.println("서블렛 ub 객체 생성 =>"+ub);
			}
			if(categoryMiddle.equals("중분류") == false && categorySmall.equals("소분류") == true) {
				// 중분류만 선택시
				System.out.println("중분류까지만 선택 되었습니다.");
				ub = new Auction(categoryMiddle, writer, title, content, productStatus,fprice, sprice, tprice);
				System.out.println("서블렛 ub 객체 생성 =>"+ub);
			}
			if(categoryMiddle.equals("중분류") == false && categorySmall.equals("소분류") == false){
				// 소분류 까지 선택시
				System.out.println("소분류까지 선택 되었습니다.");
				ub = new Auction(categorySmall, writer, title, content, productStatus,fprice, sprice, tprice);
				System.out.println("서블렛 ub 객체 생성 =>"+ub);
			}

		
			
			// 어레이리스트 생성
			ArrayList<AuctionAttachment> fileList = new ArrayList<>();
			AuctionAttachment at = null;
			
			
				// 오리지널 이름이 null이 아니면
				if(multiRequest.getOriginalFileName("file1") != null) {
					
					String originName = multiRequest.getOriginalFileName("file1");
					String changeName = multiRequest.getFilesystemName("file1");
					
					System.out.println(originName);
					System.out.println(changeName);
					
					at = new AuctionAttachment();
					at.setFilePath(savePath);
					at.setOriginName(originName);
					at.setChangeName(changeName);
					
					fileList.add(at);
				}
			System.out.println(fileList +"리스트 !!!");
			
			
			int result = new AuctionService().insertAuctionItem(ub, fileList);
			
			if(result > 0) {
				response.sendRedirect("auctionPage.do");// 화면전환
			}else {
				
				for(int i = 0 ; i < fileList.size() ; i++) {
					File failedFile = new File(savePath + fileList.get(i).getChangeName());
					failedFile.delete();
				}
				
				request.setAttribute("msg", "게시물 등록 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
			
			
			
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
