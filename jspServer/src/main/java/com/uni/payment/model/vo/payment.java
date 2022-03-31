package com.uni.payment.model.vo;

import java.sql.Date;

public class payment {
	private String merchantUid;
	private int buyerNo;
	private int boardNo;
	private int price;
	private Date paymentDate;
	
	public payment() {
		// TODO Auto-generated constructor stub
	}
	
	

	public payment(String merchantUid, int buyerNo, int boardNo, int price) {
		super();
		this.merchantUid = merchantUid;
		this.buyerNo = buyerNo;
		this.boardNo = boardNo;
		this.price = price;
	}



	public payment(String merchantUid, int buyerNo, int boardNo, int price, Date paymentDate) {
		super();
		this.merchantUid = merchantUid;
		this.buyerNo = buyerNo;
		this.boardNo = boardNo;
		this.price = price;
		this.paymentDate = paymentDate;
	}

	public String getMerchantUid() {
		return merchantUid;
	}

	public void setMerchantUid(String merchantUid) {
		this.merchantUid = merchantUid;
	}

	public int getBuyerNo() {
		return buyerNo;
	}

	public void setBuyerNo(int buyerNo) {
		this.buyerNo = buyerNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "payment [merchantUid=" + merchantUid + ", buyerNo=" + buyerNo + ", boardNo=" + boardNo + ", price="
				+ price + ", paymentDate=" + paymentDate + "]";
	}
	
	

}
