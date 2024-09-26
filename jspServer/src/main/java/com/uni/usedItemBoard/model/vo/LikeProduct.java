package com.uni.usedItemBoard.model.vo;

public class LikeProduct {
	private int likeNo;		// 찜 번호
	private int userNo;		// 유저 번호
	private int boardNo;	// 게시글 번호
	
	public LikeProduct() {
		// TODO Auto-generated constructor stub
	}
	
	public LikeProduct(int likeNo, int userNo, int boardNo) {
		super();
		this.likeNo = likeNo;
		this.userNo = userNo;
		this.boardNo = boardNo;
	}

	public int getLikeNo() {
		return likeNo;
	}

	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "LikeProduct [likeNo=" + likeNo + ", userNo=" + userNo + ", boardNo=" + boardNo + "]";
	}
	
	
	
}
