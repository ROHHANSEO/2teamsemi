package com.uni.event.controller;

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

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.uni.common.UploadUtil;
import com.uni.event.model.service.EventService;
import com.uni.event.model.vo.Event;
import com.uni.event.model.vo.NoticeAttachment;
import com.uni.user.model.vo.User;

/**
 * Servlet implementation class EventEnrollServlet
 */
@WebServlet("/insertEventPage.do")
@MultipartConfig(
	    fileSizeThreshold = 1024*1024,
	    maxFileSize = 1024*1024*10, //10메가
	    maxRequestSize = 1024*1024*10*10 // 10메가 10개까지
	)
public class EventEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventEnrollServlet() {
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
					ArrayList<NoticeAttachment> fileList = new ArrayList<>();
					
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
					
						// uploadUtil의 saveFiles 메소드 사용하여 파일을 저장
						uploadUtil.saveFiles(part, fileName, uploadUtil.createFilePath());
						
						// UsedAttachment객체 생성
						NoticeAttachment nt = new NoticeAttachment();
						
						// 저장경로와 이름 저장
						nt.setFilePath(uploadUtil.createFilePath());
						nt.setOriginName(fileName);
						
						// fileList에 담기
						fileList.add(nt);
					}
					System.out.println("서블렛 fileList ==> " + fileList); // 값 확인용
				
					// 작성자, 제목, 내용 받아오기
					String writer = String.valueOf(((User)request.getSession().getAttribute("user")).getUserNo());
					String title = request.getParameter("title").replaceAll("\n", "<br>").replaceAll("\u0020", "&nbsp;");
					String content = request.getParameter("content").replaceAll("\n", "<br>").replaceAll("\u0020", "&nbsp;"); 
					
					System.out.println("writer 서블렛 ==> "+writer);
					System.out.println("title 서블렛 ==> "+title);
					System.out.println("content 서블렛 ==> "+content);
					
					// 이벤트 글 분류 상태
					String NoticeStatus = request.getParameter("NoticeStatus");
					
					
					
					int result = new EventService().insertNotice(fileList);
					
					if(result > 0) {
						response.sendRedirect("eventpage.do");// 화면전환
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
