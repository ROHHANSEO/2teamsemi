package com.uni.chatting.model.dao;

import static com.uni.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.uni.admin.model.service.Reply;
import com.uni.chatting.model.vo.Chatting;
import com.uni.chatting.model.vo.ChattingLog;

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
		
		//SELECT * FROM CHAT_ATTEND WHERE (SENDPERSON_NO=1 OR ANSPERSON_NO = 1) AND (SENDPERSON_NO=2 OR ANSPERSON_NO = 2)
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ct.getSendP());//보낸사람
			pstmt.setInt(2, ct.getSendP());//보낸사람
			pstmt.setInt(3, ct.getAnswP());//받는사람
			pstmt.setInt(4, ct.getAnswP());//받는사람
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
			
			pstmt.setInt(1, ct.getSendP());//보낸사람
			pstmt.setInt(2, ct.getAnswP());//받는 사람
			pstmt.setString(3, ct.getSendNick());//보낸사람
			pstmt.setString(4, ct.getAnswNick());//받는 사람
			
			result = pstmt.executeUpdate(); 
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	
	public int findChattingNo(Connection conn, int sendp, int ansp) {
		int chatNo = 0; 
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findChattingNo");
		
		//SELECT CHAT_NO FROM CHAT_ATTEND WHERE BOARD_NO = ?
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sendp);//보낸사람
			pstmt.setInt(2, sendp);//보낸사람
			pstmt.setInt(3, ansp);//받는사람
			pstmt.setInt(4, ansp);//받는사람
			
			rset = pstmt.executeQuery(); 
			if(rset.next()) {
				chatNo = rset.getInt("CHAT_NO");
			}
			System.out.println("chatNo"+chatNo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return chatNo;
	}
	//채팅 내용 추가 
	public int insertChat(Connection conn, ChattingLog cl) {
		int result = 0;
		PreparedStatement pstmt = null;
		System.out.println(cl + "댓글 값 들어왓는지 확인");
		String sql = prop.getProperty("insertChat");
		//INSERT INTO CHAR_LOG VALUES(SEQ_CL.NEXTVAL, ?, SYSDATE, ?, DEFAULT, DEFAULT, ?)
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cl.getCahtNo());//채팅방 넘버 
			pstmt.setString(2, cl.getChatCont());//채팅 내용
			pstmt.setInt(3, Integer.parseInt(cl.getUserNo()));

			result = pstmt.executeUpdate(); 
			System.out.println("댓글 추가해주고 성공?"+result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<ChattingLog> selectCList(Connection conn, int chatNo) {
		ArrayList<ChattingLog> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectCList");
		//SELECT CHAT_NO, TIME, CHAT_CONTENT, USER_NO FROM CHAR_LOG WHERE CHAT_NO =189
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, chatNo);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {//list는 한개가 아닌 여러개이기 때문에 while문을 돌려줘야 한다.
				
				list.add(new ChattingLog(rset.getInt("CHAT_NO"),
											rset.getString(2), 
											rset.getString("CHAT_CONTENT"), 
											rset.getString("USER_NO")));
				System.out.println("댓글 dao list" + list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}



}
