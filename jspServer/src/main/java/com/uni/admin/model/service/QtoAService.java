package com.uni.admin.model.service;

import static com.uni.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.admin.model.dao.QtoADao;
import com.uni.serviceCenter.model.vo.QtoA;

public class QtoAService {

	public ArrayList<QtoA> selectList() {
		Connection conn = getConnection();
		
		ArrayList<QtoA> list = new QtoADao().selectList(conn);
		System.out.println("service부분 ======"+ list);
		close(conn);
		return list;
	}

	public QtoA selectQtoA(int scno) {
		Connection conn = getConnection();
		
		QtoA qa = new QtoADao().selectQtoA(conn, scno);
		if(qa != null) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return qa;
	}

	public ArrayList<Reply> selectRList(int scno) {
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new QtoADao().selectRlist(conn, scno);
		close(conn);
		return list;
	}

	public int insertReply(Reply r) {
		Connection conn = getConnection();
		
		int result1 = new QtoADao().insertReply(conn, r);
		
		if(result1 > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result1;
	}

}
