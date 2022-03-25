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
				while(rset.next()) {
					list.add(new Event(rset.getInt("noticeno"),							
							   rset.getString("noticeTitle"),
							   rset.getDate("createDate")));
		}
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

	public int increaseCount(Connection conn, int nno) {
		// TODO Auto-generated method stub
		return 0;
	}


	public int deletEvent(Connection conn, int nno) {
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("deleteNotice");
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

	  public int getListCount(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("getListCount"); 
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		return listCount;
	}
	  
	  public ArrayList<Event> selectList(Connection conn, PageInfo pi) {
			ArrayList<Event> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectEventList");
			
			int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				rset = pstmt.executeQuery();
				while(rset.next()) {
					list.add(new Event(rset.getInt("noticeno"),
									rset.getString("noticeTitle"),
									rset.getString("userid"),
									rset.getString("category"),
									rset.getDate("createDate")));
				
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
