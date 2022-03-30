package com.uni.auction.model.vo;

import java.sql.Date;

public class Auction {

	private int auctionNo; //게시글 번호
	private String category; //카테고리 분류
	private String categorycode; //고유코드(최대 소분류)
	private String auctionWriter; //게시글 작성자(번호 또는 이름)
	private String auctionId;//작성자 아이디
	private String auctionTitle; //게시글 제목
	private String auctionContent; //게시글 내용
	private String itemCondition; //상품상태( 중고상품, 새상품)
	private int itemPrice;//경매 시작가 
	private int itemUp; //올릴 경매가 
	private int itemDirect; //즉시 판매가 
	private String priceFo;//가격 콤
	private String sellStatus; //거래 상태(판매중, 판매완료, 거래중)
	private int count; //조회수
	private String dateget;//가져온 날짜(작성 날)
	private String datenext;//가져온 날짜(하루 지난 날)
	private Date createDate; //게시글 작성일
	private String status; //게시글 상태값(Y, N) -> 삭제 할시 N으로 
	private String titleImg; //게시글 타이틀 이미지 (서버에 입력되어있는 이름)
	
	public Auction() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Auction(int auctionNo,String categorycode, String auctionWriter,String auctionId, String auctionTitle, String auctionContent,
			String itemCondition, int itemPrice, int itemUp, int itemDirect,
			 String sellStatus, int count, String dateget, String datenext, String status) {
		super();
		this.auctionNo = auctionNo;
		this.categorycode = categorycode;
		this.auctionWriter = auctionWriter;
		this.auctionId = auctionId;
		this.auctionTitle = auctionTitle;
		this.auctionContent = auctionContent;
		this.itemCondition = itemCondition;
		this.itemPrice = itemPrice;
		this.itemUp = itemUp;
		this.itemDirect = itemDirect;
		this.sellStatus = sellStatus;
		this.count = count;
		this.dateget = dateget;
		this.datenext = datenext;
		this.status = status;
	}



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

	public Auction(String categorycode, String auctionWriter, String auctionTitle, String auctionContent,
			String itemCondition, int itemPrice, int itemUp, int itemDirect) {
		super();
		this.categorycode = categorycode;
		this.auctionWriter = auctionWriter;
		this.auctionTitle = auctionTitle;
		this.auctionContent = auctionContent;
		this.itemCondition = itemCondition;
		this.itemPrice = itemPrice;
		this.itemUp = itemUp;
		this.itemDirect = itemDirect;
	}

	public Auction(int auctionNo,String categorycode, String auctionTitle, int itemDirect, String sellStatus, int count, String titleImg, String dateget) {
		super();
		this.auctionNo = auctionNo;
		this.categorycode= categorycode;
		this.auctionTitle = auctionTitle;
		this.itemDirect = itemDirect;
		this.sellStatus = sellStatus;
		this.count = count;
		this.titleImg = titleImg;
		this.dateget = dateget;
	}
	public Auction(int auctionNo,String categorycode, String auctionTitle, String priceFo, String sellStatus, int count, String titleImg, String dateget) {
		super();
		this.auctionNo = auctionNo;
		this.categorycode= categorycode;
		this.auctionTitle = auctionTitle;
		this.priceFo = priceFo;
		this.sellStatus = sellStatus;
		this.count = count;
		this.titleImg = titleImg;
		this.dateget = dateget;
	}

	
	
	
	public String getAuctionId() {
		return auctionId;
	}



	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}



	public String getDatenext() {
		return datenext;
	}



	public void setDatenext(String datenext) {
		this.datenext = datenext;
	}



	public String getPriceFo() {
		return priceFo;
	}



	public void setPriceFo(String priceFo) {
		this.priceFo = priceFo;
	}



	public String getDateget() {
		return dateget;
	}



	public void setDateget(String dateget) {
		this.dateget = dateget;
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
				+ ", auctionWriter=" + auctionWriter + ", auctionId=" + auctionId + ", auctionTitle=" + auctionTitle
				+ ", auctionContent=" + auctionContent + ", itemCondition=" + itemCondition + ", itemPrice=" + itemPrice
				+ ", itemUp=" + itemUp + ", itemDirect=" + itemDirect + ", priceFo=" + priceFo + ", sellStatus="
				+ sellStatus + ", count=" + count + ", dateget=" + dateget + ", datenext=" + datenext + ", createDate="
				+ createDate + ", status=" + status + ", titleImg=" + titleImg + "]";
	}



}
