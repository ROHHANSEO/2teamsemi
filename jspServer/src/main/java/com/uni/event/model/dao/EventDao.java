package com.uni.event.model.dao;

import static com.uni.common.JDBCTemplate.*;

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

import com.uni.admin.model.service.Reply;
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
	
	
	
	public ArrayList<Event> insertList(Connection conn) {
		ArrayList<Event> list = new ArrayList<Event>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("insertList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	




}
