package com.uni.mypage.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import static com.uni.common.JDBCTemplate.*;


import com.uni.usedItemBoard.model.dao.UsedItemsBoardDao;

public class MyPageDao {
	private Properties prop = new Properties();
	
	public MyPageDao() {
		String fileName = UsedItemsBoardDao.class.getResource("/sql/mypage/mypage.properties").getPath();
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


	public int statusSwap(Connection conn, String status, String bno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("statusSwap");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, Integer.parseInt(bno));
			
			result = pstmt.executeUpdate();
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}


	public int RecordDelete(Connection conn, String bno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("RecordDelete");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(bno));
			
			result = pstmt.executeUpdate();
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}


	public int selectDelete(Connection conn, String[] bnoArr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("RecordDelete");

		try {
			pstmt = conn.prepareStatement(sql);
			
			for(int i = 0; i < bnoArr.length; i++) {
			
				pstmt.setInt(1, Integer.parseInt(bnoArr[i]));
				
				result += pstmt.executeUpdate();
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}


	public int AllDelete(Connection conn, int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("AllDelete");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			result = pstmt.executeUpdate();
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	
}
