package com.uni.auction.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.uni.auction.model.service.AuctionService;
import com.uni.auction.model.vo.Auction;
import com.uni.auction.model.vo.AuctionAttachment;
import com.uni.common.UploadUtil;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class updateAuctionitemServlet
 */
@WebServlet("/updateAuctionItem.do")
@MultipartConfig(
	    fileSizeThreshold = 1024*1024,
	    maxFileSize = 1024*1024*10, //10메가
	    maxRequestSize = 1024*1024*10*10 // 10메가 10개까지
	)
public class updateAuctionitemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateAuctionitemServlet() {
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
		
		int scno = Integer.parseInt(request.getParameter("scno"));
		
		UploadUtil uploadUtil = UploadUtil.create(request.getServletContext()); // UploadUtil 객체 생성 --> 저장하기 위해
		
		// part API 를 통해 list 생성
		// 참고 사이트1 : https://dev-gorany.tistory.com/289 
		// 참고 사이트2 : https://velog.io/@godkimchichi/Java-19-%ED%8C%8C%EC%9D%BC-%EC%97%85%EB%A1%9C%EB%93%9C-%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C#2-apache-jakarta%EC%9D%98-commons
		List<Part> parts = (List<Part>)request.getParts();
		
		// 파일 어레이리스트 생성
		ArrayList<AuctionAttachment> fileList = new ArrayList<>();
		
		int index = 0; // 인덱스 생성 --> 첫 파일에 1을 붙여줄 것이기 때문
		int result1 = 0; // result1 생성 --> 파일 삭제를 위해
		// 기존 파일 리스트 가져와 삭제하기
		ArrayList<AuctionAttachment> existing = new AuctionService().selectAttachment(scno);
		for(int i = 0 ; i < existing.size() ; i++) {
			System.out.println(existing.get(i).getOriginName());
			File deleteFile = new File(uploadUtil.createFilePath()+existing.get(i).getOriginName());
			System.out.println("deleteFile => "+deleteFile);
			
			deleteFile.delete();
			
			result1 = new AuctionService().deleteAttachment(scno);
		}
		// for each문 사용
		for(Part part : parts) {
			if(!part.getName().equals("file1")) continue; //file1(input name 값)로 들어온 Part가 아니면 스킵
			// getName() : part 객체가 가진 input:file 의 name을 가져오는 메소드
			
			if(part.getSubmittedFileName().equals("")) continue; //업로드 된 파일 이름이 없으면 스킵
			// getSubmittedFileName() : part 객체가 가지고 있는 file 이름을 가져오는 메소드
			
			// 파일의 이름을 String형으로 받음
			String fileName = part.getSubmittedFileName();
			
			// 업로드 날짜 받기
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String currentTime = sdf.format(new Date());
			
			// 수정명 : 파일업로드한시간(년월일시분초) + 10000~99999사이의 랜덤값 (5자리랜덤값) + 확장자
			int ranNum = (int)(Math.random()*90000+10000);
			
			// 확장자
			String ext = "";
			// 도트
			int dot = fileName.lastIndexOf(".");
			ext = fileName.substring(dot);
			
			String rename =  currentTime + ranNum + ext;
			
			
			System.out.println("fileName rename ==> "+ rename); // 확인용
			
            // uploadUtil의 saveFiles 메소드 사용하여 파일을 저장
            uploadUtil.saveFiles(part, fileName, uploadUtil.createFilePathAuction());
			
			
			// AuctionAttachment객체 생성
			AuctionAttachment at = new AuctionAttachment();
			
			
			// 저장경로와 이름 저장
			at.setFilePath(uploadUtil.createFilePathAuction());
			at.setOriginName(fileName);
			at.setChangeName(rename);
			// fileList에 담기
			fileList.add(at);
		}
		//System.out.println("서블렛 fileList ==> " + fileList); // 값 확인용
		System.out.println("서블렛 fileList ==> " + fileList); // 값 확인용	
		// 작성자, 제목, 내용 받아오기
		String writer = String.valueOf(((User)request.getSession().getAttribute("user")).getUserNo());
		String title = request.getParameter("title").replaceAll("\u0020", "&nbsp;");
		String content = request.getParameter("content").replaceAll("\u0020", "&nbsp;").replaceAll("\n", "<br>"); 
		
		//System.out.println("writer 서블렛 ==> "+writer);
		//System.out.println("title 서블렛 ==> "+title);
		//System.out.println("content 서블렛 ==> "+content);
		
		// 상품상태
		String productStatus = request.getParameter("productStatus");
		
		//경매 시작 가격
		int fprice = Integer.parseInt(request.getParameter("fprice"));
		
		//올릴 경매가
		int sprice = Integer.parseInt(request.getParameter("sprice"));
		
		//즉시 판매가
		int tprice = Integer.parseInt(request.getParameter("tprice"));
		// 카테고리 고유코드
		String centerselect = request.getParameter("centerselect");
		//카테고리 고유코드, 작성자, 제목, 내용, 상품 상태, 시작가격, 올릴가결, 즉시판매가
		Auction at = new Auction(centerselect, writer, title, content, productStatus, fprice, sprice, tprice);
		at.setAuctionNo(scno);
		System.out.println("dlrjs adfsjwo"+at);
		//폼 전체 값
		int result = new AuctionService().updateAuctionItem(at, fileList);
		
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
