package com.uni.event.model.dao;

import static com.uni.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;


import com.uni.event.model.vo.*;
import com.uni.usedItemBoard.model.vo.UsedAttachment;

public class EventDao {
	
	private Properties prop = new Properties();

	public EventDao() {
		String fileName = EventDao.class.getResource("/sql/Event/Event-query.properties").getPath();
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

	public ArrayList<Event> selectList(Connection conn) {
		ArrayList<Event> list = new ArrayList<Event>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectList");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Event(rset.getInt("NOTICE_NO"),							
								   rset.getString("NOTICE_TITLE"),
								   rset.getString("USER_ID"),
								   rset.getString("CATEGORY"),
								   rset.getInt("COUNT"),
								   rset.getDate("CREATE_DATE")));
			}
			System.out.println( " Dao list=====> " + list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
	
	
	
	public int increaseCount(Connection conn, int nno) {
		int result = 0; 
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public Event selectEvent(Connection conn, int nno) {
		Event event = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectEvent");
		System.out.println( " nnoda0da========== " + nno);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				event = new Event(rset.getInt("NOTICE_NO"),							
						   rset.getString("NOTICE_TITLE"),
						   rset.getString("USER_ID"),
						   rset.getString("CATEGORY"),
						   rset.getString("NOTICE_CONTENT"),
						   rset.getInt("COUNT"),
						   rset.getDate("CREATE_DATE"));
			}
			System.out.println( " Dao Event=== " + event);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return event;
	}

	


	public int deletEvent(Connection conn, int nno) {
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("deleteNotice");
			
			System.out.println("nno=====>"+ nno);
			
			try {
				pstmt = conn.prepareStatement (sql);
				pstmt.setInt (1, nno);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}finally{
				close(pstmt);
			}
			
			return result;

	

			}

	
	  
	 

	

	public int updateNotice(Connection conn, Event event) {
		int result = 0; 
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, event.getNoticeTitle());
			pstmt.setString(2, event.getNoticeContent());
			pstmt.setInt(3, event.getNoticeno());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Event> selectList(Connection conn, PageInfo pi) {
		ArrayList<Event> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectEventlist");
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
		
		/* currentPage = 1 startRow =  1 endRow =  10;
		/* currentPage = 2 startRow = 11 endRow =  20;
		/* currentPage = 3 startRow = 21 endRow =  30;
		 *  */
		
		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Event(rset.getInt("NOTICE_NO"),
									rset.getString("NOTICE_TITLE"),
									rset.getString("NOTICE_CONTENT"),
									rset.getInt("USER_NO"),
									rset.getInt("COUNT"),
									rset.getDate("CREATE_DATE")));
			}
			System.out.println("다오 => "+list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("getEventListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			System.out.println("다오 listCount => " + listCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}

	

	public int insertNoticeAttachment(Connection conn, ArrayList<NoticeAttachment> fileList) {
		int result = 0; // 성공한 수를 반환하기 위한 값
		PreparedStatement pstmt = null; // SQL 구문을 실행하는 역할로 Statement 클래스의 기능 향상된 클래스다
		String sql = prop.getProperty("insertNoticeAttachment"); // getProperty 메소드를 사용하여 sql 구문을 String형 변수에 담는다
		
		try {
			for(int i = 0 ; i < fileList.size() ; i++) {
				NoticeAttachment at = fileList.get(i);
				
				pstmt = conn.prepareStatement(sql); // prepareStatement 메소드에 sql 문을 전달하여 prepareStatement 객체를 생성한다
				
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getFilePath());
				
				result += pstmt.executeUpdate(); // update sql 실행 -> 성공한 행 만큼의 수를 result에 더하며 담는다
				System.out.println("Dao event result => " + result); // 임의 확인
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt); // pstmt를 닫는다
		}
		
		return result; // int로 반환
	}

	




}
