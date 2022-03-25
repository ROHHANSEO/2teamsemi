package com.uni.event.model.vo;

import java.sql.Date;

public class Event {
	
	private int noticeno;
	private int userno;
	private String userid;
	private String noticeTitle;
	private String noticeContent;
	private String category;
	private Date createDate;
	private String status;
	
	public Event (int noticeno,  String noticeTitle, String userid, String category, Date createDate ){
		super();
		this.noticeno = noticeno;
		this.noticeTitle = noticeTitle;
		this.userid = userid;
		this.category = category;
		this.createDate = createDate;
		
	}
	
	

	public Event(int noticeno,  String noticeTitle, Date createDate ){
		super();
		this.noticeno = noticeno;
		this.noticeTitle = noticeTitle;
		this.createDate = createDate;

	}
	
	public Event (int noticeno,  String noticeTitle, String userid, String category, String noticeContent, Date createDate ){
		super();
		this.noticeno = noticeno;
		this.noticeTitle = noticeTitle;
		this.userid = userid;
		this.category = category;
		this.noticeContent=noticeContent;
		this.createDate = createDate;
		
	}



	public int getNoticeno() {
		return noticeno;
	}



	public void setNoticeno(int noticeno) {
		this.noticeno = noticeno;
	}



	public int getUserno() {
		return userno;
	}



	public void setUserno(int userno) {
		this.userno = userno;
	}



	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getNoticeTitle() {
		return noticeTitle;
	}



	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}



	public String getNoticeContent() {
		return noticeContent;
	}



	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "Event [noticeno=" + noticeno + ", userno=" + userno + ", userid=" + userid + ", noticeTitle="
				+ noticeTitle + ", noticeContent=" + noticeContent + ", category=" + category + ", createDate="
				+ createDate + ", status=" + status + "]";
	}
	
	
	
	
	
	
}