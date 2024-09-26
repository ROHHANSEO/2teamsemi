package com.uni.usedItemBoard.model.vo;

public class MonthlyMarketPrice {

	private int month;		// 웗
	private int minPrice;	// 최소값
	private int maxPrice;	// 최대값
	private int avgPrice;	// 평균값
	
	public MonthlyMarketPrice() {
		// TODO Auto-generated constructor stub
	}
	
	public MonthlyMarketPrice(int month, int minPrice, int maxPrice, int avgPrice) {
		super();
		this.month = month;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.avgPrice = avgPrice;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(int avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String toString() {
		return "MonthlyMarketPrice [month=" + month + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice
				+ ", avgPrice=" + avgPrice + "]";
	}
	
	
}
