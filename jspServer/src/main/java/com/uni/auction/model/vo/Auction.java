package com.uni.auction.model.vo;

import java.sql.Date;

public class Auction {

	private int auctionNo; //게시글 번호
	private String category; //카테고리 분류(1~7)
	//1. 신발, 2. 의류, 3.패션 잡화, 4. 라이프, 5. 테크, 6. 진품 명품, 7.기타
	private String categorycode; //고유코드(최대 소분류)
	private String auctionWriter; //게시글 작성자(번호 또는 이름)
	private String auctionTitle; //게시글 제목
	private String auctionContent; //게시글 내용
	private String itemCondition; //상품상태( 중고상품, 새상품)
	private int itemPrice;//경매 시작가 
	private int itemUp; //올릴 경매가 
	private int itemDirect; //즉시 판매가 
	private String sellStatus; //거래 상태(판매중, 판매완료, 거래중)
	private int count; //조회수
	private Date createDate; //게시글 작성일
	private String status; //게시글 상태값(Y, N) -> 삭제 할시 N으로 
	private String titleImg; //게시글 타이틀 이미지 (서버에 입력되어있는 이름)
	
	public Auction() {
		// TODO Auto-generated constructor stub
	}

	////글번호, 제목, 조회수, 즉시판매갑,판매상태, 사진 메인
	
	
	public Auction(int auctionNo, String category, String categorycode, String auctionWriter, String auctionTitle,
			String auctionContent, String itemCondition, int itemPrice, int itemUp, int itemDirect, String sellStatus,
			int count, Date createDate, String status, String titleImg) {
		super();
		this.auctionNo = auctionNo;
		this.category = category;
		this.categorycode = categorycode;
		this.auctionWriter = auctionWriter;
		this.auctionTitle = auctionTitle;
		this.auctionContent = auctionContent;
		this.itemCondition = itemCondition;
		this.itemPrice = itemPrice;
		this.itemUp = itemUp;
		this.itemDirect = itemDirect;
		this.sellStatus = sellStatus;
		this.count = count;
		this.createDate = createDate;
		this.status = status;
		this.titleImg = titleImg;
	}

	public Auction(int auctionNo, String auctionTitle, int itemDirect, String sellStatus, int count, String titleImg) {
		super();
		this.auctionNo = auctionNo;
		this.auctionTitle = auctionTitle;
		this.itemDirect = itemDirect;
		this.sellStatus = sellStatus;
		this.count = count;
		this.titleImg = titleImg;
	}

	public int getAuctionNo() {
		return auctionNo;
	}

	public void setAuctionNo(int auctionNo) {
		this.auctionNo = auctionNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategorycode() {
		return categorycode;
	}

	public void setCategorycode(String categorycode) {
		this.categorycode = categorycode;
	}

	public String getAuctionWriter() {
		return auctionWriter;
	}

	public void setAuctionWriter(String auctionWriter) {
		this.auctionWriter = auctionWriter;
	}

	public String getAuctionTitle() {
		return auctionTitle;
	}

	public void setAuctionTitle(String auctionTitle) {
		this.auctionTitle = auctionTitle;
	}

	public String getAuctionContent() {
		return auctionContent;
	}

	public void setAuctionContent(String auctionContent) {
		this.auctionContent = auctionContent;
	}

	public String getItemCondition() {
		return itemCondition;
	}

	public void setItemCondition(String itemCondition) {
		this.itemCondition = itemCondition;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemUp() {
		return itemUp;
	}

	public void setItemUp(int itemUp) {
		this.itemUp = itemUp;
	}

	public int getItemDirect() {
		return itemDirect;
	}

	public void setItemDirect(int itemDirect) {
		this.itemDirect = itemDirect;
	}

	public String getSellStatus() {
		return sellStatus;
	}

	public void setSellStatus(String sellStatus) {
		this.sellStatus = sellStatus;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	@Override
	public String toString() {
		return "Auction [auctionNo=" + auctionNo + ", category=" + category + ", categorycode=" + categorycode
				+ ", auctionWriter=" + auctionWriter + ", auctionTitle=" + auctionTitle + ", auctionContent="
				+ auctionContent + ", itemCondition=" + itemCondition + ", itemPrice=" + itemPrice + ", itemUp="
				+ itemUp + ", itemDirect=" + itemDirect + ", sellStatus=" + sellStatus + ", count=" + count
				+ ", createDate=" + createDate + ", status=" + status + ", titleImg=" + titleImg + "]";
	}
	
	
}
