package com.uni.admin.model.service;

import static com.uni.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.admin.model.dao.QtoADao;
import com.uni.admin.model.vo.BlockBoard;
import com.uni.serviceCenter.model.vo.QtoA;
import com.uni.user.model.dao.UserDao;
import com.uni.user.model.vo.User;

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

	public ArrayList<BlockBoard> BlockBoardList() {
		Connection conn = getConnection();
		
		ArrayList<BlockBoard> list = new QtoADao().BlockBoardList(conn);
		close(conn);
		return list;
	}

	public ArrayList<BlockBoard> BlockBoardDetailList(String boardNo) {
		Connection conn = getConnection();
		
		ArrayList<BlockBoard> list = new QtoADao().BlockBoardDetailList(conn, boardNo);
		close(conn);
		return list;
	}

	public BlockBoard reportBoardDetail(String blockNo) {
		Connection conn = getConnection();
		
		BlockBoard block = new QtoADao().reportBoardDetail(conn, blockNo);
		close(conn);
		return block;
	}

	public int reportSanctions(String blockNo) {
		Connection conn = getConnection();
		
		
		int[] categoryNo = new QtoADao().reportSanctionsCategoryNo(conn, blockNo);
		int userNo = new QtoADao().reportSanctionsUserNo(conn,categoryNo);
		int result1 = new QtoADao().boardBlock(conn,categoryNo);
		int result2 = new QtoADao().reportSanctions(conn,userNo);
		int result3 = new QtoADao().reportDelete(conn,categoryNo[1]);
		
		if(result1 > 0 && result2 > 0 && result3 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2 * result3;
	}

	public int selectSanctions(String[] boardNo) {
		Connection conn = getConnection();
		
		int userNo[] = new QtoADao().SanctionsUser(conn,boardNo);
		
		int result1 = new QtoADao().selectreportSanctions(conn,userNo);
		int result2 = new QtoADao().selectreportDelete(conn,boardNo);
		
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2;
	}

	public int serviceDelete(String[] serviceNo) {
		Connection conn = getConnection();
		
		int result = new QtoADao().serviceDelete(conn, serviceNo);
		
		if(result > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<User> AllUserList() {
		Connection conn = getConnection();
		
		ArrayList<User> user = new QtoADao().AllUserList(conn);
		close(conn);
		return user;
	}

	public ArrayList<User> BanUserList() {
		Connection conn = getConnection();
		
		ArrayList<User> user = new QtoADao().BanUserList(conn);
		close(conn);
		return user;
	}

}
