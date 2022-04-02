package com.uni.chatting.model.dao;

import static com.uni.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.uni.chatting.model.vo.Chatting;

public class ChattingDao {
	private Properties prop = new Properties();

	public ChattingDao() {
		String fileName = ChattingDao.class.getResource("/sql/chatting/Chatting-query.properties").getPath();
		System.out.println("fileName   " + fileName);
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//채팅 있는지 체크
	public int checkChatting(Connection conn, Chatting ct) {
		int result = 0; 
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("checkChatting");
		
		//SELECT * FROM CHAT_ATTEND WHERE BOARD_NO = 1000008 AND SENDPERSON_NO = 1
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ct.getBoardNo());//보드 넘버
			pstmt.setInt(2, ct.getSendP());//보낸사람
			
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	//채팅 넣어주기 
	public int addNewChatting(Connection conn, Chatting ct) {
		int result = 0; 
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("addNewChatting");
		
		//INSERT INTO CHAT_ATTEND VALUES(SEQ_CT.NEXTVAL, 1000008, 1, 2)
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ct.getBoardNo());//보드 넘버
			pstmt.setInt(2, ct.getSendP());//보낸사람
			pstmt.setInt(3, ct.getAnswP());//받는 사람
			
			result = pstmt.executeUpdate(); 
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public String findChatting(Connection conn, int sendp) {
		String Nickno = null; 
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findChatting");
		
		//SELECT USER_ID FROM MEMBER WHERE USER_NO = ?
		try {
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, sendp);//보낸사람
			
			rset = pstmt.executeQuery(); 
			if(rset.next()) {
				Nickno = rset.getString("USER_ID");
			}
			System.out.println("Nickno"+Nickno);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return Nickno;
	}

}
