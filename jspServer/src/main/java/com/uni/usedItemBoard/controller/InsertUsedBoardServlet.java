package com.uni.usedItemBoard.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
					String resouces = request.getSession().getServletContext().getRealPath("/resouces");
					
					// 저장하는 경로 지정
					String savePath = resouces + "\\Usedboard_upfiles\\";
					
					System.out.println("savePath : " + savePath);
					
					// MultipartRequest객체 생성								요청, 저장하는 경로, 최대 파일크기, 인코딩, 따로 파일의 이름을 정하는?
					MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
					
				
					// 작성자, 제목, 내용 받아오기
					String writer = multiRequest.getParameter("writer");
					String title = multiRequest.getParameter("title");
					String content = multiRequest.getParameter("content"); 
					// 카테고리 고유코드
					
					
					// 상품상태
					String productStatus = multiRequest.getParameter("productStatus");
					
					int price = Integer.parseInt(multiRequest.getParameter("price")); //가격
					// 바로결제와 만나서 결제
					String[] payments = multiRequest.getParameterValues("payments");
					
					if(payments[0].isBlank() == false && payments[1].isBlank() == false) {
						String paymOne = "Y";
						String paymTwo = "Y";
					}else if(payments[0].isBlank() == true && payments[1].isBlank() == false) {
						String paymOne = "N";
						String paymTwo = "Y";
					}else if(payments[0].isBlank() == false && payments[1].isBlank() == true) {
						String paymOne = "Y";
						String paymTwo = "N";
					}
					
					// 보드 객체생성
					UsedItemsBoard ub = new UsedItemsBoard
					
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
