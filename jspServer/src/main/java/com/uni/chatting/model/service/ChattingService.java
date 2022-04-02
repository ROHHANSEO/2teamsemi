package com.uni.chatting.model.service;
import static com.uni.common.JDBCTemplate.close;
import static com.uni.common.JDBCTemplate.commit;
import static com.uni.common.JDBCTemplate.getConnection;
import static com.uni.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.uni.chatting.model.dao.ChattingDao;
import com.uni.chatting.model.vo.Chatting;
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

	public Chatting findChatting(int sendp, int ansp) {//채팅 닉네임
		Connection conn = getConnection();
		
		String sendNick = new ChattingDao().findChatting(conn,sendp);
		String ansNick = new ChattingDao().findChatting(conn,ansp);
		
		Chatting ctt = new Chatting(sendNick, ansNick);
		close(conn);
		return ctt;
	}


}
