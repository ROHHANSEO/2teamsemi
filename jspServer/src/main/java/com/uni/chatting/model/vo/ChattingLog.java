package com.uni.chatting.model.vo;

import java.sql.Date;

public class ChattingLog {

	private int chatlogNo; //채팅분류 번호 
	private int cahtNo;//연결된 채팅 넘버
	private Date time;//날짜
	private String timelist;// 날짜 시간 수정
	private String chatCont;//채팅내용
	private Date appointT;//약속 시간
	private String appointW;//약속 장소
	private String userNo;//유저넘버
	
	
	public ChattingLog() {
		// TODO Auto-generated constructor stub
	}
	
	

	public ChattingLog(int cahtNo, String timelist, String chatCont, String userNo) {
		super();
		this.cahtNo = cahtNo;
		this.timelist = timelist;
		this.chatCont = chatCont;
		this.userNo = userNo;
	}



	public ChattingLog(int chatlogNo, int cahtNo, Date time, String chatCont, Date appointT, String appointW,
			String userNo) {
		super();
		this.chatlogNo = chatlogNo;
		this.cahtNo = cahtNo;
		this.time = time;
		this.chatCont = chatCont;
		this.appointT = appointT;
		this.appointW = appointW;
		this.userNo = userNo;
	}



	public String getTimelist() {
		return timelist;
	}



	public void setTimelist(String timelist) {
		this.timelist = timelist;
	}



	public String getUserNo() {
		return userNo;
	}



	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}



	public int getChatlogNo() {
		return chatlogNo;
	}

	public void setChatlogNo(int chatlogNo) {
		this.chatlogNo = chatlogNo;
	}

	public int getCahtNo() {
		return cahtNo;
	}

	public void setCahtNo(int cahtNo) {
		this.cahtNo = cahtNo;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getChatCont() {
		return chatCont;
	}

	public void setChatCont(String chatCont) {
		this.chatCont = chatCont;
	}

	public Date getAppointT() {
		return appointT;
	}

	public void setAppointT(Date appointT) {
		this.appointT = appointT;
	}

	public String getAppointW() {
		return appointW;
	}

	public void setAppointW(String appointW) {
		this.appointW = appointW;
	}



	@Override
	public String toString() {
		return "ChattingLog [chatlogNo=" + chatlogNo + ", cahtNo=" + cahtNo + ", time=" + time + ", timelist="
				+ timelist + ", chatCont=" + chatCont + ", appointT=" + appointT + ", appointW=" + appointW
				+ ", userNo=" + userNo + "]";
	}



}
