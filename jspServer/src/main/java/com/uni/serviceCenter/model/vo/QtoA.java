package com.uni.serviceCenter.model.vo;

import java.sql.Date;

public class QtoA {
	
	private int questionNo;//문의 넘버 
	private String userNo; //회원번호 
	private String questionTitle;//문의 제목
	private String questionContent;//문의 내용
	private Date createDate;//문의 작성일
	private String category;//문의 카테고리 -> 고객센터 카테고리 번호와 동일
	
	public QtoA() {
		// TODO Auto-generated constructor stub
	}
	
	

	public QtoA(String userNo, String questionTitle, String questionContent, String category) {
		super();
		this.userNo = userNo;
		this.questionTitle = questionTitle;
		this.questionContent = questionContent;
		this.category = category;
	}



	public QtoA(int questionNo, String userNo, String questionTitle, String questionContent, Date createDate,
			String category) {
		super();
		this.questionNo = questionNo;
		this.userNo = userNo;
		this.questionTitle = questionTitle;
		this.questionContent = questionContent;
		this.createDate = createDate;
		this.category = category;
	}

	public int getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "QtoA [questionNo=" + questionNo + ", userNo=" + userNo + ", questionTitle=" + questionTitle
				+ ", questionContent=" + questionContent + ", createDate=" + createDate + ", category=" + category
				+ "]";
	}
	
	
	
}
