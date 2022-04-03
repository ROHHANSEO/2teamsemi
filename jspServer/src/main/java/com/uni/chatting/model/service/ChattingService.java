package com.uni.chatting.model.service;
import static com.uni.common.JDBCTemplate.close;
import static com.uni.common.JDBCTemplate.commit;
import static com.uni.common.JDBCTemplate.getConnection;
import static com.uni.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.admin.model.dao.QtoADao;
import com.uni.admin.model.service.Reply;
import com.uni.chatting.model.dao.ChattingDao;
import com.uni.chatting.model.vo.Chatting;
import com.uni.chatting.model.vo.ChattingLog;
public class ChattingService {

	public int checkChatting(Chatting ct) {//채팅 체크 
		Connection conn = getConnection();
		
		int result = new ChattingDao().checkChatting(conn,ct);
		close(conn);
		return result;
	}

	public int addNewChatting(Chatting ct) {//채팅 생성
		Connection conn = getConnection();
		
		int result = new ChattingDao().addNewChatting(conn,ct);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}


	public int findChattingNo(int sendp, int ansp) {
		Connection conn = getConnection();
		int result = new ChattingDao().findChattingNo(conn,sendp, ansp);
		close(conn);
		return result;
	}

	public int insertChat(ChattingLog cl) {
		Connection conn = getConnection();
		
		int result = new ChattingDao().insertChat(conn,cl);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<ChattingLog> selectCList(int chatNo) {
		Connection conn = getConnection();
		
		ArrayList<ChattingLog> list = new ChattingDao().selectCList(conn, chatNo);
		close(conn);
		return list;
	}


}
