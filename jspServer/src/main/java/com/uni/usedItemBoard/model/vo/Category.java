package com.uni.usedItemBoard.model.vo;

public class Category {
	
	private String code; 	// 고유코드
	private int cateNo; 	// 카테고리 분류번호
	private String level; 	// 분류
	private String name; 	// 카테고리 명
	private String ref1;	// 대분류 관련코드
	private String ref2;	// 중분류 관련코드
	
	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Category(String name) {
		super();
		this.name = name;
	}



	public Category(String code, int cateNo, String level, String name, String ref1, String ref2) {
		super();
		this.code = code;
		this.cateNo = cateNo;
		this.level = level;
		this.name = name;
		this.ref1 = ref1;
		this.ref2 = ref2;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRef1() {
		return ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	public String getRef2() {
		return ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}

	@Override
	public String toString() {
		return "Category [code=" + code + ", cateNo=" + cateNo + ", level=" + level + ", name=" + name + ", ref1="
				+ ref1 + ", ref2=" + ref2 + "]";
	}
	
	
	
}
