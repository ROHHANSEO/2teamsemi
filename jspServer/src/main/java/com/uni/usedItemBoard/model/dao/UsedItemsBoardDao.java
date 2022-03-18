package com.uni.usedItemBoard.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.uni.usedItemBoard.model.vo.PageInfo;
import com.uni.usedItemBoard.model.vo.UsedItemsBoard;

import static com.uni.common.JDBCTemplate.*;

public class UsedItemsBoardDao {
	
	private Properties prop = new Properties();
	
	public UsedItemsBoardDao() { // DB 연결
		String fileName = UsedItemsBoardDao.class.getResource("/sql/usedItemsBoard/usedItemsBoard-query.properties").getPath();
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

	// 리스트 갯수 받아오기
	public int getCountList(Connection conn) {
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
	
		
		String sql = prop.getProperty("getCountList");
		
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

	// 리스트 뽑아오는 메소드
	public ArrayList<UsedItemsBoard> selectList(Connection conn, PageInfo pi) {
		ArrayList<UsedItemsBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String sql = prop.getProperty("selectList");
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();

			while(rset.next()) {
				list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
									rset.getString("BOARD_TITLE"),
									rset.getInt("PRICE"),
									rset.getString("SALE_STATUS"),
									rset.getInt("LIKE_COUNT"),
									rset.getString("STATUS")
									));
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

}
