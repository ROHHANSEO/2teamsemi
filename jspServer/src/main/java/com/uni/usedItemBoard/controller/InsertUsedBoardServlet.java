package com.uni.usedItemBoard.controller;

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

import com.uni.common.MyFileRenamePolicy;
import com.uni.common.UploadUtil;
import com.uni.usedItemBoard.model.service.UsedItemsBoardService;
import com.uni.usedItemBoard.model.vo.UsedAttachment;
import com.uni.usedItemBoard.model.vo.UsedItemsBoard;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class InsertUsedBoardServlet
 */
@WebServlet("/insertUsed.do")
@MultipartConfig(
	    fileSizeThreshold = 1024*1024,
	    maxFileSize = 1024*1024*10, //10메가
	    maxRequestSize = 1024*1024*10*10 // 10메가 10개까지
	)
public class InsertUsedBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertUsedBoardServlet() {
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
			ArrayList<UsedAttachment> fileList = new ArrayList<>();
			
			int index = 0; // 인덱스 생성 --> 첫 파일에 1을 붙여줄 것이기 때문
			
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
				uploadUtil.saveFiles(part, rename, uploadUtil.createFilePath());
				
				// UsedAttachment객체 생성
				UsedAttachment at = new UsedAttachment();
				
				// 저장경로와 이름 저장
				at.setFilePath(uploadUtil.createFilePath());
				at.setOriginName(fileName);
				at.setChangeName(rename);
				
				// fileList에 담기
				fileList.add(at);
			}
			System.out.println("서블렛 fileList ==> " + fileList); // 값 확인용
		
			// 작성자, 제목, 내용 받아오기
			String writer = String.valueOf(((User)request.getSession().getAttribute("user")).getUserNo());
			String title = request.getParameter("title").replaceAll("\n", "<br>").replaceAll("\u0020", "&nbsp;");
			String content = request.getParameter("content").replaceAll("\n", "<br>").replaceAll("\u0020", "&nbsp;"); 
			
			System.out.println("writer 서블렛 ==> "+writer);
			System.out.println("title 서블렛 ==> "+title);
			System.out.println("content 서블렛 ==> "+content);
			
			// 상품상태
			String productStatus = request.getParameter("productStatus");
			
			//가격
			int price = Integer.parseInt(request.getParameter("price"));
			
			// 바로결제와 만나서 결제
			String[] payments = request.getParameterValues("payments");
			String paymentOne = "";
			String paymentTwo = "";
			
			// for문 이용하여 결제 방식 넣기
			for(int i = 0 ; i < payments.length ; i++) {
				// 처음 인덱스에 직접 결제가 존재 할 시
				if(!payments[0].isEmpty() && payments[0].contains("바로 결제")) {
					paymentOne = "Y";
					System.out.println(paymentOne);
				}else {
					paymentOne = "N"; 
				}
				// 만나서 결제만 햇을 시
				if(!payments[0].isEmpty() && payments[0].contains("만나서 결제")) {
					paymentTwo = "Y";
					System.out.println(paymentTwo);
				}else if(i == 1 && !payments[1].isEmpty() && payments[1].contains("만나서 결제")) {
					// 직접결제와 만나서결졔를 같이 햇을 시
					paymentTwo = "Y";
				}else {// 직접결제만 했을 시
					paymentTwo = "N";
				}
			}
			
			// 카테고리 고유코드
			String categoryLarge = request.getParameter("large");
			String categoryMiddle = request.getParameter("middle");
			String categorySmall = request.getParameter("small");
			
			System.out.println("대분류 =>" + categoryLarge);
			System.out.println("중분류 =>" + categoryMiddle);
			System.out.println("소분류 =>" + categorySmall);
			
			UsedItemsBoard ub = null;
			// 대분류만 선택시
			if(categoryMiddle.equals("중분류")) {
				// 보드 객체생성 및 담기(아직 안담음)
				System.out.println("대분류만 선택 되었습니다.");
				ub = new UsedItemsBoard(categoryLarge, title, writer, content, price, productStatus, paymentOne, paymentTwo);
				System.out.println("서블렛 ub 객체 생성 =>"+ub);
			}
			if(categoryMiddle.equals("중분류") == false && categorySmall.equals("소분류") == true) {
				// 중분류만 선택시
				System.out.println("중분류까지만 선택 되었습니다.");
				ub = new UsedItemsBoard(categoryMiddle, title, writer, content, price, productStatus, paymentOne, paymentTwo);
				System.out.println("서블렛 ub 객체 생성 =>"+ub);
			}
			if(categoryMiddle.equals("중분류") == false && categorySmall.equals("소분류") == false){
				// 소분류 까지 선택시
				System.out.println("소분류까지 선택 되었습니다.");
				ub = new UsedItemsBoard(categorySmall, title, writer, content, price, productStatus, paymentOne, paymentTwo);
				System.out.println("서블렛 ub 객체 생성 =>"+ub);
			}
			

			
			int result = new UsedItemsBoardService().insertUsedBoard(ub, fileList);
			
			if(result > 0) {
				response.sendRedirect("usedBoardList.do");// 화면전환
			}else {
				
				for(int i = 0 ; i < fileList.size() ; i++) {
					File failedFile = new File(uploadUtil.createFilePath() + fileList.get(i).getOriginName());
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
