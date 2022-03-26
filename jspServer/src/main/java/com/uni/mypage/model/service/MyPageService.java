package com.uni.mypage.model.service;

import static com.uni.common.JDBCTemplate.*;

import java.sql.Connection;

import com.uni.mypage.model.dao.MyPageDao;

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

}
