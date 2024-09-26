package com.uni.usedItemBoard.model.vo;

import java.sql.Date;

public class UsedAttachment {
	private int fileNo;			// 파일 고유 번호
	private int refBoardNo;		// 참조하고 있는 게시글 번호
	private String originName;	// 파일 원본명
	private String filePath;	// 파일이 저장된 폴더 경로
	private Date uploadDate;	// 파일 업로드일
	private String changeName;	// 파일 변경명
	private String status;		// 파일 상태값
	
	public UsedAttachment() {
		
	}
	
	
	
	public UsedAttachment(int fileNo, String originName) {
		super();
		this.fileNo = fileNo;
		this.originName = originName;
	}



	public UsedAttachment(int fileNo, int refBoardNo, String originName, String filePath, Date uploadDate,
			String changeName, String status) {
		super();
		this.fileNo = fileNo;
		this.refBoardNo = refBoardNo;
		this.originName = originName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.changeName = changeName;
		this.status = status;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public int getRefBoardNo() {
		return refBoardNo;
	}

	public void setRefBoardNo(int refBoardNo) {
		this.refBoardNo = refBoardNo;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	public String getChangeName() {
		return changeName;
	}



	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "UsedAttachment [fileNo=" + fileNo + ", refBoardNo=" + refBoardNo + ", originName=" + originName
				+ ", filePath=" + filePath + ", uploadDate=" + uploadDate + ", changeName=" + changeName + ", status="
				+ status + "]";
	}

	

	
	
	
	
}
