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

	public ArrayList<Auction> actionList(int userNo) {
		Connection conn = getConnection();

		//내가 올린 경매 리스트
		ArrayList<Auction> list = new MyPageDao().actionList(conn,userNo);
		
		//내가 입찰한 경매 리스트
		
		
		
		close(conn);
		return list;
	}

	public ArrayList<UsedItemsBoard> likeProductList(int userNo) {
		Connection conn = getConnection();

		ArrayList<UsedItemsBoard> list = new MyPageDao().likeProductList(conn,userNo);
		close(conn);
		return list;
	}

}
