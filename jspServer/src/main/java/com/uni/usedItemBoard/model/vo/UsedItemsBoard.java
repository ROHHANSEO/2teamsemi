package com.uni.usedItemBoard.model.vo;

import java.util.Date;

public class UsedItemsBoard {
	private int usedBoardNo;		// 게시글 고유 번호
	private int category;			// 카테고리 분류번호
	private int categorycode;		// 고유코드(소분류)
	private String usedBoardTitle;	// 게시글 제목
	private String usedBoardWriter;	// 게시글 작성자 (번호 또는 이름)
	private String usedBoardContent;// 게시글 내용
	private int price;				// 가격
	private String saleStatus;		// 판매 상태
	private String itemCondition;	// 상품 상태
	private int likeCount;			// 게시글 찜 수
	private Date createDate;		// 게시글 작성일
	private String paymentOne;		// 만나서 결제 (Y, N)
	private String paymentTwo;		// 직접 결제 (Y, N)
	private String status;			// 게시글 상태값(Y, N)
	private String titleImg;		// 게시글의 타이틀 이미지 (실제 서버에 업로드되어있는 이름)
	
	
	public UsedItemsBoard() {
		
	}


	public UsedItemsBoard(int usedBoardNo, String usedBoardTitle, int price, String saleStatus, int likeCount,
			String status) {
		super();
		this.usedBoardNo = usedBoardNo;
		this.usedBoardTitle = usedBoardTitle;
		this.price = price;
		this.saleStatus = saleStatus;
		this.likeCount = likeCount;
		this.status = status;
	}


	public UsedItemsBoard(int usedBoardNo, int category, int categorycode, String usedBoardTitle,
			String usedBoardWriter, String usedBoardContent, int price, String saleStatus, String itemCondition,
			int likeCount, Date createDate, String paymentOne, String paymentTwo, String status, String titleImg) {
		super();
		this.usedBoardNo = usedBoardNo;
		this.category = category;
		this.categorycode = categorycode;
		this.usedBoardTitle = usedBoardTitle;
		this.usedBoardWriter = usedBoardWriter;
		this.usedBoardContent = usedBoardContent;
		this.price = price;
		this.saleStatus = saleStatus;
		this.itemCondition = itemCondition;
		this.likeCount = likeCount;
		this.createDate = createDate;
		this.paymentOne = paymentOne;
		this.paymentTwo = paymentTwo;
		this.status = status;
		this.titleImg = titleImg;
	}


	public int getUsedBoardNo() {
		return usedBoardNo;
	}


	public void setUsedBoardNo(int usedBoardNo) {
		this.usedBoardNo = usedBoardNo;
	}


	public int getCategory() {
		return category;
	}


	public void setCategory(int category) {
		this.category = category;
	}


	public int getCategorycode() {
		return categorycode;
	}


	public void setCategorycode(int categorycode) {
		this.categorycode = categorycode;
	}


	public String getUsedBoardTitle() {
		return usedBoardTitle;
	}


	public void setUsedBoardTitle(String usedBoardTitle) {
		this.usedBoardTitle = usedBoardTitle;
	}


	public String getUsedBoardWriter() {
		return usedBoardWriter;
	}


	public void setUsedBoardWriter(String usedBoardWriter) {
		this.usedBoardWriter = usedBoardWriter;
	}


	public String getUsedBoardContent() {
		return usedBoardContent;
	}


	public void setUsedBoardContent(String usedBoardContent) {
		this.usedBoardContent = usedBoardContent;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getSaleStatus() {
		return saleStatus;
	}


	public void setSaleStatus(String saleStatus) {
		this.saleStatus = saleStatus;
	}


	public String getItemCondition() {
		return itemCondition;
	}


	public void setItemCondition(String itemCondition) {
		this.itemCondition = itemCondition;
	}


	public int getLikeCount() {
		return likeCount;
	}


	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getPaymentOne() {
		return paymentOne;
	}


	public void setPaymentOne(String paymentOne) {
		this.paymentOne = paymentOne;
	}


	public String getPaymentTwo() {
		return paymentTwo;
	}


	public void setPaymentTwo(String paymentTwo) {
		this.paymentTwo = paymentTwo;
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
		return "UsedItemsBoard [usedBoardNo=" + usedBoardNo + ", category=" + category + ", categorycode="
				+ categorycode + ", usedBoardTitle=" + usedBoardTitle + ", usedBoardWriter=" + usedBoardWriter
				+ ", usedBoardContent=" + usedBoardContent + ", price=" + price + ", saleStatus=" + saleStatus
				+ ", itemCondition=" + itemCondition + ", likeCount=" + likeCount + ", createDate=" + createDate
				+ ", paymentOne=" + paymentOne + ", paymentTwo=" + paymentTwo + ", status=" + status + ", titleImg="
				+ titleImg + "]";
	}

		


}

