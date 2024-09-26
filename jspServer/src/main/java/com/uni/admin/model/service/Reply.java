package com.uni.admin.model.service;

import java.sql.Date;

public class Reply {
	private int answerNo;//댓글 번호
	private String replyContent;//댓글 내용
	private int refQuestion;//참조 문의글
	private String replyWriter; //조회시 : 작성자 이름, 댓글 작성시 : 회원번호로 쓰인다. 
	private Date createDate;//작성 날짜
	private Date modifyDate;//수정 날짜 
	private String status; //작성 상태
	
	public Reply() {
		// TODO Auto-generated constructor stub
	}

	
	public Reply(int answerNo, String replyContent, String replyWriter) {
		super();
		this.answerNo = answerNo;
		this.replyContent = replyContent;
		this.replyWriter = replyWriter;
	}


	public Reply(int answerNo, String replyContent, String replyWriter, Date createDate) {
		super();
		this.answerNo = answerNo;
		this.replyContent = replyContent;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
	}

	public Reply(int answerNo, String replyContent, int refQuestion, String replyWriter, Date createDate,
			Date modifyDate, String status) {
		super();
		this.answerNo = answerNo;
		this.replyContent = replyContent;
		this.refQuestion = refQuestion;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}

	public int getAnswerNo() {
		return answerNo;
	}

	public void setAnswerNo(int answerNo) {
		this.answerNo = answerNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getRefQuestion() {
		return refQuestion;
	}

	public void setRefQuestion(int refQuestion) {
		this.refQuestion = refQuestion;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reply [answerNo=" + answerNo + ", replyContent=" + replyContent + ", refQuestion=" + refQuestion
				+ ", replyWriter=" + replyWriter + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", status=" + status + "]";
	}
	
	
	
	
}
