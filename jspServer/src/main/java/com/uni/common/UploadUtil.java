package com.uni.common;

import java.io.File; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

public class UploadUtil {
	private String uploadPath;
	private ServletContext app; // 절대경로
	
	/* 생성 메서드 */
	public static UploadUtil create(ServletContext app) {
		
		UploadUtil uploadUtil = new UploadUtil();
		uploadUtil.setApp(app);
		uploadUtil.setUploadPath(app.getRealPath("/resources"));
		
		return uploadUtil;
	}
	
	private void setApp(ServletContext app) {
		this.app = app;
	}
	private void setUploadPath(String realPath) {
		this.uploadPath = realPath;
	}
	
	/* 파일 저장 */
	public void saveFiles(Part filePart, String fileName, String folderPath) {
		
		String realPath = folderPath; // 절대 경로
		String filePath = realPath + fileName; // 파일을 포함한 절대 경로
		
		try(
			// InputStream, OutputStream 객체생성
			InputStream fis = filePart.getInputStream();
			OutputStream fos = new FileOutputStream(filePath);)
		{
			byte[] buf = new byte[1024];
			int len = 0;
			
			while((len = fis.read(buf, 0, 1024)) != -1)
				fos.write(buf, 0, len);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*/upload 하위 폴더 경로 생성 */
	public String createFilePath() {
		
		// 저장하는 경로 지정
		String savePath = app.getRealPath("/resources") + "\\usedboard_upfiles\\";
		
		createFolders(savePath); // 존재여부 및 생성하는 메소드 실행
		
		return savePath;
	}
	
	// 폴더가 존재 하지 않을 시 생성하는 메소드
	private void createFolders(String paths) {
		
		// file 객체 생성
		File folders = new File(uploadPath, paths);
		
		if(!folders.exists()) // 폴더가 존재 하지않으면
			folders.mkdirs(); // 생성
	}
}
