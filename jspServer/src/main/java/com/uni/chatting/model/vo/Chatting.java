package com.uni.chatting.model.vo;

public class Chatting {
	private int chatNo;//채팅방 넘버 
	private int sendP;//보낸 사람
	private int answP;//받는 사람
	private String sendNick;//받는사람 아이디
	private String answNick;//보내는 사람 아이디
	
	public Chatting() {
		// TODO Auto-generated constructor stub
	}

	public Chatting( int sendP, int answP) {
		super();
		this.sendP = sendP;
		this.answP = answP;
	}
	
	

	public Chatting(int chatNo, String  sendNick, String answNick) {
		super();
		this.chatNo = chatNo;
		this.answNick = answNick;
		this.sendNick = sendNick;
	}

	public Chatting( int sendP, int answP, String sendNick, String answNick) {
		super();
		this.sendP = sendP;
		this.answP = answP;
		this.answNick = answNick;
		this.sendNick = sendNick;
	}
	public Chatting(int chatNo, int sendP, int answP, String sendNick, String answNick) {
		super();
		this.chatNo = chatNo;
		this.sendP = sendP;
		this.answP = answP;
		this.answNick = answNick;
		this.sendNick = sendNick;
	}
	public Chatting(int chatNo, int sendP, int answP) {
		super();
		this.chatNo = chatNo;
		this.sendP = sendP;
		this.answP = answP;
	}
	
	

	public String getAnswNick() {
		return answNick;
	}

	public void setAnswNick(String answNick) {
		this.answNick = answNick;
	}

	public String getSendNick() {
		return sendNick;
	}

	public void setSendNick(String sendNick) {
		this.sendNick = sendNick;
	}

	public int getChatNo() {
		return chatNo;
	}

	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}

	public int getSendP() {
		return sendP;
	}

	public void setSendP(int sendP) {
		this.sendP = sendP;
	}

	public int getAnswP() {
		return answP;
	}

	public void setAnswP(int answP) {
		this.answP = answP;
	}

	@Override
	public String toString() {
		return "Chatting [chatNo=" + chatNo + ", sendP=" + sendP + ", answP=" + answP + ", answNick=" + answNick
				+ ", sendNick=" + sendNick + "]";
	}



	
	
	
}
