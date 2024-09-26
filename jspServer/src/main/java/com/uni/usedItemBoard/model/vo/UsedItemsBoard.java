package com.uni.usedItemBoard.model.vo;

import java.util.Date;

public class UsedItemsBoard {
	private int usedBoardNo;		// 게시글 고유 번호
	private int category;			// 카테고리 분류번호
	private String categorycode;		// 고유코드(최대 소분류)
	private String usedBoardTitle;	// 게시글 제목
	private String usedBoardWriter;	// 게시글 작성자 (번호 또는 이름)
	private String nickName;		// 닉네임
	private String usedBoardContent;// 게시글 내용
	private int price;				// 가격
	private String comprice;		// 컴마가 들어간 가격
	private String saleStatus;		// 판매 상태
	private String itemCondition;	// 상품 상태
	private int likeCount;			// 게시글 찜 수
	private Date createDate;		// 게시글 작성일
	private String paymentOne;		// 직접 결제 (Y, N)
	private String paymentTwo;		// 만나서 결제 (Y, N)
	private String paymentStatus;	// 판매 상태 (Y, N)
	private String status;			// 게시글 상태값(Y, N)
	private String titleImg;		// 게시글의 타이틀 이미지 (실제 서버에 업로드되어있는 이름)
	private int buyer;
	

	public UsedItemsBoard(String categorycode, String usedBoardTitle, String usedBoardWriter, String usedBoardContent,
			int price, String itemCondition, String paymentOne, String paymentTwo) {
		super();
		this.categorycode = categorycode;
		this.usedBoardTitle = usedBoardTitle;
		this.usedBoardWriter = usedBoardWriter;
		this.usedBoardContent = usedBoardContent;
		this.price = price;
		this.itemCondition = itemCondition;
		this.paymentOne = paymentOne;
		this.paymentTwo = paymentTwo;
	}
	

	public UsedItemsBoard(int usedBoardNo, String usedBoardTitle, String comprice, String saleStatus, int likeCount,
			String titleImg) {
		super();
		this.usedBoardNo = usedBoardNo;
		this.usedBoardTitle = usedBoardTitle;
		this.comprice = comprice;
		this.saleStatus = saleStatus;
		this.likeCount = likeCount;
		this.titleImg = titleImg;
	}


	public UsedItemsBoard(int usedBoardNo, String usedBoardTitle, int price, String itemCondition, int likeCount,
			String titleImg) {
		super();
		this.usedBoardNo = usedBoardNo;
		this.usedBoardTitle = usedBoardTitle;
		this.price = price;
		this.itemCondition = itemCondition;
		this.likeCount = likeCount;
		this.titleImg = titleImg;
	}

	
	public UsedItemsBoard(String categorycode, String usedBoardTitle, String nickName, String usedBoardContent,
			int price, String saleStatus, String itemCondition, int likeCount, String paymentOne, String paymentTwo,
			String paymentStatus) {
		super();
		this.categorycode = categorycode;
		this.usedBoardTitle = usedBoardTitle;
		this.nickName = nickName;
		this.usedBoardContent = usedBoardContent;
		this.price = price;
		this.saleStatus = saleStatus;
		this.itemCondition = itemCondition;
		this.likeCount = likeCount;
		this.paymentOne = paymentOne;
		this.paymentTwo = paymentTwo;
		this.paymentStatus = paymentStatus;
	}
	
	
	


	public UsedItemsBoard(String categorycode, String usedBoardTitle, String usedBoardWriter, String nickName,
			String usedBoardContent, int price, String saleStatus, String itemCondition, int likeCount,
			String paymentOne, String paymentTwo, String paymentStatus) {
		super();
		this.categorycode = categorycode;
		this.usedBoardTitle = usedBoardTitle;
		this.usedBoardWriter = usedBoardWriter;
		this.nickName = nickName;
		this.usedBoardContent = usedBoardContent;
		this.price = price;
		this.saleStatus = saleStatus;
		this.itemCondition = itemCondition;
		this.likeCount = likeCount;
		this.paymentOne = paymentOne;
		this.paymentTwo = paymentTwo;
		this.paymentStatus = paymentStatus;
	}

	public UsedItemsBoard(String categorycode, String usedBoardTitle, String usedBoardWriter, String nickName,
			String usedBoardContent, String comprice, String saleStatus, String itemCondition, int likeCount,
			String paymentOne, String paymentTwo, String paymentStatus) {
		super();
		this.categorycode = categorycode;
		this.usedBoardTitle = usedBoardTitle;
		this.usedBoardWriter = usedBoardWriter;
		this.nickName = nickName;
		this.usedBoardContent = usedBoardContent;
		this.comprice = comprice;
		this.saleStatus = saleStatus;
		this.itemCondition = itemCondition;
		this.likeCount = likeCount;
		this.paymentOne = paymentOne;
		this.paymentTwo = paymentTwo;
		this.paymentStatus = paymentStatus;
	}


	public UsedItemsBoard(int usedBoardNo, String categorycode, String usedBoardTitle, String usedBoardWriter,
			String nickName, String usedBoardContent, String comprice, String saleStatus, String itemCondition,
			int likeCount, String paymentOne, String paymentTwo, String paymentStatus) {
		super();
		this.usedBoardNo = usedBoardNo;
		this.categorycode = categorycode;
		this.usedBoardTitle = usedBoardTitle;
		this.usedBoardWriter = usedBoardWriter;
		this.nickName = nickName;
		this.usedBoardContent = usedBoardContent;
		this.comprice = comprice;
		this.saleStatus = saleStatus;
		this.itemCondition = itemCondition;
		this.likeCount = likeCount;
		this.paymentOne = paymentOne;
		this.paymentTwo = paymentTwo;
		this.paymentStatus = paymentStatus;
	}


	public UsedItemsBoard(int usedBoardNo, int category, String categorycode, String usedBoardTitle,
			String usedBoardWriter, String nickName, String usedBoardContent, int price, String saleStatus,
			String itemCondition, int likeCount, Date createDate, String paymentOne, String paymentTwo,
			String paymentStatus, String status, String titleImg) {
		super();
		this.usedBoardNo = usedBoardNo;
		this.category = category;
		this.categorycode = categorycode;
		this.usedBoardTitle = usedBoardTitle;
		this.usedBoardWriter = usedBoardWriter;
		this.nickName = nickName;
		this.usedBoardContent = usedBoardContent;
		this.price = price;
		this.saleStatus = saleStatus;
		this.itemCondition = itemCondition;
		this.likeCount = likeCount;
		this.createDate = createDate;
		this.paymentOne = paymentOne;
		this.paymentTwo = paymentTwo;
		this.paymentStatus = paymentStatus;
		this.status = status;
		this.titleImg = titleImg;
	}

	
	


	public int getBuyer() {
		return buyer;
	}


	public void setBuyer(int buyer) {
		this.buyer = buyer;
	}


	public String getComprice() {
		return comprice;
	}


	public void setComprice(String comprice) {
		this.comprice = comprice;
	}


	public String getPaymentStatus() {
		return paymentStatus;
	}



	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}



	public String getNickName() {
		return nickName;
	}



	public void setNickName(String nickName) {
		this.nickName = nickName;
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


	public String getCategorycode() {
		return categorycode;
	}


	public void setCategorycode(String categorycode) {
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
				+ ", nickName=" + nickName + ", usedBoardContent=" + usedBoardContent + ", price=" + price
				+ ", comprice=" + comprice + ", saleStatus=" + saleStatus + ", itemCondition=" + itemCondition
				+ ", likeCount=" + likeCount + ", createDate=" + createDate + ", paymentOne=" + paymentOne
				+ ", paymentTwo=" + paymentTwo + ", paymentStatus=" + paymentStatus + ", status=" + status
				+ ", titleImg=" + titleImg + ", getComprice()=" + getComprice() + ", getPaymentStatus()="
				+ getPaymentStatus() + ", getNickName()=" + getNickName() + ", getUsedBoardNo()=" + getUsedBoardNo()
				+ ", getCategory()=" + getCategory() + ", getCategorycode()=" + getCategorycode()
				+ ", getUsedBoardTitle()=" + getUsedBoardTitle() + ", getUsedBoardWriter()=" + getUsedBoardWriter()
				+ ", getUsedBoardContent()=" + getUsedBoardContent() + ", getPrice()=" + getPrice()
				+ ", getSaleStatus()=" + getSaleStatus() + ", getItemCondition()=" + getItemCondition()
				+ ", getLikeCount()=" + getLikeCount() + ", getCreateDate()=" + getCreateDate() + ", getPaymentOne()="
				+ getPaymentOne() + ", getPaymentTwo()=" + getPaymentTwo() + ", getStatus()=" + getStatus()
				+ ", getTitleImg()=" + getTitleImg() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	


}

