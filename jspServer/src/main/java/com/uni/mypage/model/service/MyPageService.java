package com.uni.mypage.model.service;

import static com.uni.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.auction.model.vo.Auction;
import com.uni.mypage.model.dao.MyPageDao;
import com.uni.usedItemBoard.model.vo.LikeProduct;
import com.uni.usedItemBoard.model.vo.UsedItemsBoard;

public class MyPageService {

	public int statusSwap(String status, String bno) {
		Connection conn = getConnection();
		
		int result = new MyPageDao().statusSwap(conn,status,bno);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int RecordDelete(String bno) {
		Connection conn = getConnection();
		
		int result = new MyPageDao().RecordDelete(conn,bno);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int selectDelete(String[] bnoArr) {
		Connection conn = getConnection();
		
		int result = new MyPageDao().selectDelete(conn,bnoArr);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int AllDelete(int userNo) {
		Connection conn = getConnection();
		
		int result = new MyPageDao().AllDelete(conn,userNo);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<UsedItemsBoard> patmentList(int userNo) {
		Connection conn = getConnection();

		ArrayList<UsedItemsBoard> list = new MyPageDao().patmentList(conn,userNo);
		close(conn);
		return list;
	}

	public ArrayList<Auction> myBidActionList(int userNo) {
		Connection conn = getConnection();

		//내가 올린 경매 리스트
		ArrayList<Auction> list = new MyPageDao().myBidActionList(conn,userNo);
		
		
		
		close(conn);
		return list;
	}

	public ArrayList<UsedItemsBoard> likeProductList(int userNo) {
		Connection conn = getConnection();

		ArrayList<UsedItemsBoard> list = new MyPageDao().likeProductList(conn,userNo);
		close(conn);
		return list;
	}

	public ArrayList<Auction> mypostActionList(int userNo) {
		Connection conn = getConnection();

		ArrayList<Auction> list = new MyPageDao().mypostActionList(conn,userNo);
		close(conn);
		return list;
	}

	public int actionRecordDelete(String bno) {
		Connection conn = getConnection();
		
		int result = new MyPageDao().actionRecordDelete(conn,bno);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int actionSelectDelete(String[] bnoArr) {
		Connection conn = getConnection();
		
		int result = new MyPageDao().actionSelectDelete(conn,bnoArr);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int actionAlldelete(int userNo) {
		Connection conn = getConnection();
		
		int result = new MyPageDao().actionAlldelete(conn,userNo);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
