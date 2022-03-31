package com.uni.auction.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.uni.auction.model.service.AuctionService;
import com.uni.auction.model.vo.Auction;
import com.uni.auction.model.vo.AuctionAttachment;
import com.uni.common.MyFileRenamePolicy;
import com.uni.common.UploadUtil;
import com.uni.usedItemBoard.model.vo.UsedAttachment;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class InsertAuctionitemServlet
 */
@WebServlet("/insertAuctionform.do")
@MultipartConfig(
	    fileSizeThreshold = 1024*1024,
	    maxFileSize = 1024*1024*10, //10메가
	    maxRequestSize = 1024*1024*10*10 // 10메가 10개까지
	)
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
		request.setCharacterEncoding("UTF-8"); // 인코딩
		
		UploadUtil uploadUtil = UploadUtil.create(request.getServletContext()); // UploadUtil 객체 생성 --> 저장하기 위해
		
		// part API 를 통해 list 생성
		// 참고 사이트1 : https://dev-gorany.tistory.com/289 
		// 참고 사이트2 : https://velog.io/@godkimchichi/Java-19-%ED%8C%8C%EC%9D%BC-%EC%97%85%EB%A1%9C%EB%93%9C-%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C#2-apache-jakarta%EC%9D%98-commons
		List<Part> parts = (List<Part>)request.getParts();
		
		// 파일 어레이리스트 생성
		ArrayList<AuctionAttachment> fileList = new ArrayList<>();
		
		int index = 0; // 인덱스 생성 --> 첫 파일에 1을 붙여줄 것이기 때문
		
		// for each문 사용
		for(Part part : parts) {
			
			if(!part.getName().equals("file1")) continue; //file1(input name 값)로 들어온 Part가 아니면 스킵
			// getName() : part 객체가 가진 input:file 의 name을 가져오는 메소드
			
			if(part.getSubmittedFileName().equals("")) continue; //업로드 된 파일 이름이 없으면 스킵
			// getSubmittedFileName() : part 객체가 가지고 있는 file 이름을 가져오는 메소드
			
			// 파일의 이름을 String형으로 받음
			String fileName = part.getSubmittedFileName();
			System.out.println("fileName ==> "+ fileName); // 확인용
			
			// index가 0이면 --> 첫 파일이면
            if(index == 0) {
               fileName = 1+fileName; // 앞에 1을 붙여줌
               index++;
               System.out.println("fileName ==> "+ fileName); // 확인용
            }else {
               index++;
            }
            
            // uploadUtil의 saveFiles 메소드 사용하여 파일을 저장
            uploadUtil.saveFiles(part, fileName, uploadUtil.createFilePathAuction());
			
			
			// AuctionAttachment객체 생성
			AuctionAttachment at = new AuctionAttachment();
			
			// 저장경로와 이름 저장
			at.setFilePath(uploadUtil.createFilePathAuction());
			at.setOriginName(fileName);
			
			// fileList에 담기
			fileList.add(at);
		}
		System.out.println("서블렛 fileList ==> " + fileList); // 값 확인용
				
		
		
		// 작성자, 제목, 내용 받아오기
		String writer = String.valueOf(((User)request.getSession().getAttribute("user")).getUserNo());
		String title = request.getParameter("title").replaceAll("\u0020", "&nbsp;");
		String content = request.getParameter("content").replaceAll("\u0020", "&nbsp;").replaceAll("\n", "<br>"); 
		
		System.out.println("writer 서블렛 ==> "+writer);
		System.out.println("title 서블렛 ==> "+title);
		System.out.println("content 서블렛 ==> "+content);
		
		// 상품상태
		String productStatus = request.getParameter("productStatus");
		
		//경매 시작 가격
		int fprice = Integer.parseInt(request.getParameter("fprice"));
		
		//올릴 경매가
		int sprice = Integer.parseInt(request.getParameter("sprice"));
		
		//즉시 판매가
		int tprice = Integer.parseInt(request.getParameter("tprice"));
		
		// 카테고리 고유코드
		String categoryLarge = request.getParameter("large");
		String categoryMiddle = request.getParameter("middle");
		String categorySmall = request.getParameter("small");
		
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

		
		
		//폼 전체 값
		int result = new AuctionService().insertAuctionItem(ub, fileList);
		
		if(result > 0) {
			response.sendRedirect("auctionPage.do");// 화면전환
		}else {
			
			for(int i = 0 ; i < fileList.size() ; i++) {
				File failedFile = new File(uploadUtil.createFilePathAuction() + fileList.get(i).getChangeName());
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
