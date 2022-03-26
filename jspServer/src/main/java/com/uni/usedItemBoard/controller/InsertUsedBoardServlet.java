package com.uni.usedItemBoard.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.uni.common.MyFileRenamePolicy;
import com.uni.usedItemBoard.model.service.UsedItemsBoardService;
import com.uni.usedItemBoard.model.vo.UsedAttachment;
import com.uni.usedItemBoard.model.vo.UsedItemsBoard;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class InsertUsedBoardServlet
 */
@WebServlet("/insertUsed.do")
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
			
			int maxSize = 10*1024*1024; //10Mbyte로 제한
			
			//저장할 경로파일지정
			// getRealPath : 절대 정로 파일
			String resouces = request.getSession().getServletContext().getRealPath("/resources");
			
			// 저장하는 경로 지정
			String savePath = resouces + "\\usedboard_upfiles\\";
			
			System.out.println("savePath : " + savePath);
			
			// MultipartRequest객체 생성								요청, 저장하는 경로, 최대 파일크기, 인코딩, 따로 파일의 이름을 정하는 객체
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
		
			// 작성자, 제목, 내용 받아오기
			String writer = String.valueOf(((User)request.getSession().getAttribute("user")).getUserNo());
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content").replaceAll("\n", "<br>"); 
			
			// 상품상태
			String productStatus = multiRequest.getParameter("productStatus");
			
			//가격
			int price = Integer.parseInt(multiRequest.getParameter("price"));
			
			// 바로결제와 만나서 결제
			String[] payments = multiRequest.getParameterValues("payments");
			String paymentOne = "";
			String paymentTwo = "";
			
			// for문 이용하여 결제 방식 넣기
			for(int i = 0 ; i < payments.length ; i++) {
				// 처음 인덱스에 직접 결제가 존재 할 시
				if(!payments[0].isEmpty() && payments[0].contains("직접 결제")) {
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
			String categoryLarge = multiRequest.getParameter("large");
			String categoryMiddle = multiRequest.getParameter("middle");
			String categorySmall = multiRequest.getParameter("small");
			
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
			
			// 어레이리스트 생성
			ArrayList<UsedAttachment> fileList = new ArrayList<>();
			
			// for문 돌려서 파일 이름 생성
			for(int i = 1 ; i <= 10 ; i++) {
				// name 이름 얻기
				String name = "file"+i;
				
				// 오리지널 이름이 null이 아니면
				if(multiRequest.getOriginalFileName(name) != null) {
					
					String originName = multiRequest.getOriginalFileName(name);
					String changeName = multiRequest.getFilesystemName(name);
					
					System.out.println(originName);
					System.out.println(changeName);
					
					UsedAttachment at = new UsedAttachment();
					at.setFilePath(savePath);
					at.setOriginName(originName);
					at.setChangeName(changeName);
					
					fileList.add(at);
				}
			}
			
			int result = new UsedItemsBoardService().insertUsedBoard(ub, fileList);
			
			if(result > 0) {
				response.sendRedirect("usedBoardList.do");// 화면전환
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
