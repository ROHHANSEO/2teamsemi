package com.uni.admin.model.vo;

import java.sql.Date;

public class BlockBoard {
	private int blockNo;
	private String boardCategoryNm;
	private String boardTitle;
	private int boardNo;
	private String upTime;
	private String content;
	private String title;
	private String categoryNm;
	private int reportCount;
	
	public BlockBoard() {
		// TODO Auto-generated constructor stub
	}

	public BlockBoard(String boardCategoryNm, String boardTitle, int boardNo, int reportCount) {
		super();
		this.boardCategoryNm = boardCategoryNm;
		this.boardTitle = boardTitle;
		this.boardNo = boardNo;
		this.reportCount = reportCount;
	}
	
	
	
	
	public BlockBoard(String title, int boardNo, String content, String boardTitle, String categoryNm) {
		super();
		this.title = title;
		this.boardNo = boardNo;
		this.content = content;
		this.boardTitle = boardTitle;
		this.categoryNm = categoryNm;
	}

	public BlockBoard(int blockNo, String title, String categoryNm) {
		super();
		this.blockNo = blockNo;
		this.title = title;
		this.categoryNm = categoryNm;
	}
	
	

	public BlockBoard(String content, String title) {
		super();
		this.content = content;
		this.title = title;
	}

	public int getBlockNo() {
		return blockNo;
	}

	public void setBlockNo(int blockNo) {
		this.blockNo = blockNo;
	}

	public String getBoardCategoryNm() {
		return boardCategoryNm;
	}

	public void setBoardCategoryNm(String boardCategoryNm) {
		this.boardCategoryNm = boardCategoryNm;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getUpTime() {
		return upTime;
	}

	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategoryNm() {
		return categoryNm;
	}

	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	@Override
	public String toString() {
		return "BlockBoard [blockNo=" + blockNo + ", boardCategoryNm=" + boardCategoryNm + ", boardTitle=" + boardTitle
				+ ", boardNo=" + boardNo + ", upTime=" + upTime + ", content=" + content + ", title=" + title
				+ ", categoryNm=" + categoryNm + ", reportCount=" + reportCount + "]";
	}
	
	
	

}
