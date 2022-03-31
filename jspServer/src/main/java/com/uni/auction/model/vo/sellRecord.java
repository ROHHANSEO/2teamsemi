package com.uni.auction.model.vo;

import java.sql.Date;

public class sellRecord {
	
	private int sellNo;//입찰 번호
	private int auctionRef;//참조 게시글 번호
	private String userNo;//회원번호
	private int auctionPrice;//입찰 금액
	private Date auctionTime;//입찰 시간
	private String getTime;//받아온 시간
	private String status;//입찰 여부(Y,N) -> default : N
	

	
	
	
	public sellRecord(int sellNo, int auctionRef, String userNo, int auctionPrice, Date auctionTime, String getTime,
			String status) {
		super();
		this.sellNo = sellNo;
		this.auctionRef = auctionRef;
		this.userNo = userNo;
		this.auctionPrice = auctionPrice;
		this.auctionTime = auctionTime;
		this.getTime = getTime;
		this.status = status;
	}
	//연관 게시글, 유저 아이디, 가격, 변환 날짜, 낙찰여부
	public sellRecord(int auctionRef, String userNo, int auctionPrice, String getTime, String status) {
		super();
		this.auctionRef = auctionRef;
		this.userNo = userNo;
		this.auctionPrice = auctionPrice;
		this.getTime = getTime;
		this.status = status;
	}
	public int getSellNo() {
		return sellNo;
	}
	public void setSellNo(int sellNo) {
		this.sellNo = sellNo;
	}
	public int getAuctionRef() {
		return auctionRef;
	}
	public void setAuctionRef(int auctionRef) {
		this.auctionRef = auctionRef;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public int getAuctionPrice() {
		return auctionPrice;
	}
	public void setAuctionPrice(int auctionPrice) {
		this.auctionPrice = auctionPrice;
	}
	public Date getAuctionTime() {
		return auctionTime;
	}
	public void setAuctionTime(Date auctionTime) {
		this.auctionTime = auctionTime;
	}
	public String getGetTime() {
		return getTime;
	}
	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "sellRecord [sellNo=" + sellNo + ", auctionRef=" + auctionRef + ", userNo=" + userNo + ", auctionPrice="
				+ auctionPrice + ", auctionTime=" + auctionTime + ", getTime=" + getTime + ", status=" + status + "]";
	}
	
	
}
