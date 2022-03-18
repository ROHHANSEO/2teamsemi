package com.uni.serviceCenter.model.vo;

import java.sql.Date;

public class ServiceCenter {

	private int serviceNo;//고객센터 번호
	private String category;//고객센터 카테고리
	private String serviceTitle;//고객센터 제목
	private String serviceContent; //고객센터 내용
	private Date createDate;//고객센터 글 작성일
	private String status;//고객센터 상태값(Y,N)
	private String serviceWriter;//고객센터 작성자(번호)
	private int numberCount;//조회카운트 번호
	
	public ServiceCenter() {
	
	}

	
	
	public ServiceCenter(int serviceNo, String serviceTitle, String serviceContent) {
		super();
		this.serviceNo = serviceNo;
		this.serviceTitle = serviceTitle;
		this.serviceContent = serviceContent;
	}



	public ServiceCenter(int serviceNo, String category, String serviceTitle, String serviceContent, Date createDate,
			int count, String serviceWriter) {
		super();
		this.serviceNo = serviceNo;
		this.category = category;
		this.serviceTitle = serviceTitle;
		this.serviceContent = serviceContent;
		this.createDate = createDate;
		this.serviceWriter = serviceWriter;
	}

	public ServiceCenter(int serviceNo, String category, String serviceTitle, Date createDate,
			String serviceWriter) {
		super();
		this.serviceNo = serviceNo;
		this.category = category;
		this.serviceTitle = serviceTitle;
		this.createDate = createDate;
		this.serviceWriter = serviceWriter;
	}

	public ServiceCenter(int serviceNo, String category, String serviceTitle, String serviceContent, Date createDate,
			String status, String serviceWriter) {
		super();
		this.serviceNo = serviceNo;
		this.category = category;
		this.serviceTitle = serviceTitle;
		this.serviceContent = serviceContent;
		this.createDate = createDate;
		this.status = status;
		this.serviceWriter = serviceWriter;
	}

	public int getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(int serviceNo) {
		this.serviceNo = serviceNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getServiceTitle() {
		return serviceTitle;
	}

	public void setServiceTitle(String serviceTitle) {
		this.serviceTitle = serviceTitle;
	}

	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
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


	public String getServiceWriter() {
		return serviceWriter;
	}

	public void setServiceWriter(String serviceWriter) {
		this.serviceWriter = serviceWriter;
	}

	@Override
	public String toString() {
		return "ServiceCenter [serviceNo=" + serviceNo + ", category=" + category + ", serviceTitle=" + serviceTitle
				+ ", serviceContent=" + serviceContent + ", createDate=" + createDate + ", status=" + status
				+ ", serviceWriter=" + serviceWriter + "]";
	}
	
	
}
