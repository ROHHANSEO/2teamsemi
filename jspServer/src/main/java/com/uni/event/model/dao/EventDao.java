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

	
	  
	 

	public int insertNotice(Connection conn, Event ev) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNotice");
		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ev.getNoticeTitle());
			pstmt.setString(2, ev.getCategory());
			pstmt.setString(3, ev.getNoticeContent());
			pstmt.setInt(4, Integer.parseInt(ev.getUserid()));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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

	public int insertEvent(Connection conn, Event sc) {
		int result = 0;
		PreparedStatement pstmt = null;
		System.out.println(sc +"==dao에서 sc값 잘 받나 확인");
		String sql = prop.getProperty("insertEvent");
		//category, title, content, writer
		//INSERT INTO SERVICE_CENTER VALUES(SEQ_NNO.NEXTVAL, ?, ?, ?, SYSDATE, DEFAULT, DEFAULT,?)
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sc.getCategory());//카테고리
			pstmt.setString(2, sc.getNoticeTitle());//제목
			pstmt.setString(3, sc.getNoticeContent());//내용		
			pstmt.setInt(4, Integer.parseInt(sc.getUserid()));//작성자 번호는 perseint해줘야 한다.
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
		

	




}
