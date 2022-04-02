package com.uni.chatting.model.vo;

public class Chatting {
	private int chatNo;//채팅방 넘버 
	private int boardNo; //연결 게시글 넘버 
	private int sendP;//보낸 사람
	private int answP;//받는 사람
	private String answNick;//받는사람 아이디
	private String sendNick;//보내는 사람 아이디
	
	public Chatting() {
		// TODO Auto-generated constructor stub
	}

	public Chatting(int boardNo, int sendP, int answP) {
		super();
		this.boardNo = boardNo;
		this.sendP = sendP;
		this.answP = answP;
	}
	
	

	public Chatting(String  sendNick, String answNick) {
		super();
		this.answNick = answNick;
		this.sendNick = sendNick;
	}

	public Chatting( int boardNo, int sendP, int answP, String answNick, String sendNick) {
		super();
		this.boardNo = boardNo;
		this.sendP = sendP;
		this.answP = answP;
		this.answNick = answNick;
		this.sendNick = sendNick;
	}

	public Chatting(int chatNo, int boardNo, int sendP, int answP) {
		super();
		this.chatNo = chatNo;
		this.boardNo = boardNo;
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

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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
		return "Chatting [chatNo=" + chatNo + ", boardNo=" + boardNo + ", sendP=" + sendP + ", answP=" + answP
				+ ", answNick=" + answNick + ", sendNick=" + sendNick + "]";
	}

	
	
	
}
