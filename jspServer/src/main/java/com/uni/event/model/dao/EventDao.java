	package com.uni.event.model.dao;

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

import com.uni.event.model.vo.Event;

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
									rset.getDate("CREATE_DATE")));
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
	
	public ArrayList<Event> list(String pt) throws Exception{
		ArrayList<Event> list = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectListType");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			switch (pt) {
			case "now":
				sql += "WHERE CREATE_DATE <= SYSDATE";
				break;
			case "old":
				sql += "WHERE CREATE_DATE < TRUNC(SYSDATE)";
				break;
			case "all":
				sql += " ";
				break;
				
			default:
				System.out.println("잘못된 정보가 넘어 왔습니다.");
				break;
			}
			
			sql += "ORDER BY CREATE_DATE DESC";
			
			System.out.println("EventDao.list().sql : " + sql );
			
			
			while(rset.next()) {
				if(list == null) list = new ArrayList<Event>();
				Event ev = new Event();
				ev.setNOTICE_NO(rset.getInt("NOTICE_NO"));
				ev.setNOTICE_TITLE(rset.getString("NOTICE_TITLE"));
				ev.setCREATE_DATE(rset.getDate("CREATE_DATE"));
				
				list.add(ev);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn);
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
	
	public ArrayList<Event> getSearch(String searchField, String searchText){
	      ArrayList<Event> list = new ArrayList<Event>();
	      Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
	      String sql = prop.getProperty("selectList");
	      
	      try {
	            if(searchText != null && !searchText.equals("") ){
	            	sql +=" LIKE '%"+searchText.trim()+"%' order by bbsID desc limit 10";
	            }
	           
				rset=pstmt.executeQuery();
	         while(rset.next()) {
	            
	          
	         }         
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	      return list;

	}
}
