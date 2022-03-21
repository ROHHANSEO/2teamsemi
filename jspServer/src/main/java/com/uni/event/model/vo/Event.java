package com.uni.event.model.vo;

import java.sql.Date;

public class Event {
	
	private int NOTICE_NO;
	private int USER_NO;
	private String NOTICE_TITLE;
	private String NOTICE_CONTENT;
	private String CATEGORY;
	private Date CREATE_DATE;
	private String STATUS;
	
	public Event() {
		
	}
	public Event(int NOTICE_NO, String NOTICE_TITLE, Date CREATE_DATE ) {
		this.NOTICE_NO = NOTICE_NO;
		this.NOTICE_TITLE = NOTICE_TITLE;
		this.CREATE_DATE = CREATE_DATE;
	}

	public int getNOTICE_NO() {
		return NOTICE_NO;
	}

	public void setNOTICE_NO(int nOTICE_NO) {
		NOTICE_NO = nOTICE_NO;
	}

	public int getUSER_NO() {
		return USER_NO;
	}

	public void setUSER_NO(int uSER_NO) {
		USER_NO = uSER_NO;
	}

	public String getNOTICE_TITLE() {
		return NOTICE_TITLE;
	}

	public void setNOTICE_TITLE(String nOTICE_TITLE) {
		NOTICE_TITLE = nOTICE_TITLE;
	}

	public String getNOTICE_CONTENT() {
		return NOTICE_CONTENT;
	}

	public void setNOTICE_CONTENT(String nOTICE_CONTENT) {
		NOTICE_CONTENT = nOTICE_CONTENT;
	}

	public String getCATEGORY() {
		return CATEGORY;
	}

	public void setCATEGORY(String cATEGORY) {
		CATEGORY = cATEGORY;
	}

	public Date getCREATE_DATE() {
		return CREATE_DATE;
	}

	public void setCREATE_DATE(Date cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	@Override
	public String toString() {
		return "Event [NOTICE_NO=" + NOTICE_NO + ", USER_NO=" + USER_NO + ", NOTICE_TITLE=" + NOTICE_TITLE
				+ ", NOTICE_CONTENT=" + NOTICE_CONTENT + ", CATEGORY=" + CATEGORY + ", CREATE_DATE=" + CREATE_DATE
				+ ", STATUS=" + STATUS + "]";
	}
	
	
	
	
	
	
}
