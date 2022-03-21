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
import com.uni.usedItemBoard.model.vo.UsedAttachment;
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

	public int insertUsedBoard(Connection conn, UsedItemsBoard ub) {
		int result = 0; // 성공한 수를 반환하기 위한 값
		PreparedStatement pstmt = null; // SQL 구문을 실행하는 역할로 Statement 클래스의 기능 향상된 클래스다
		String sql = prop.getProperty("insertUsedBoard"); // getProperty 메소드를 사용하여 sql 구문을 String형 변수에 담는다
		
		try {
			pstmt = conn.prepareStatement(sql); // prepareStatement 메소드에 sql 문을 전달하여 prepareStatement 객체를 생성한다
			
			pstmt.setString(1, ub.getUsedBoardTitle()); // 쿼리에 담긴 물음표의 순서에 맞게 제목을 담는다
			pstmt.setString(2, ub.getUsedBoardContent()); // 위와 같이 하여 내용을 담는다
			pstmt.setInt(3, Integer.parseInt(ub.getUsedBoardWriter())); 
			
			result = pstmt.executeUpdate(); // update sql 실행 -> 성공한 행 만큼의 수를 result에 담는다
			System.out.println("Dao result => " + result); // 임의 확인
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt); // pstmt를 닫는다
		}
		
		return result; // int로 반환
	}

	public int insertUsedAttachment(Connection conn, ArrayList<UsedAttachment> fileList) {
		int result = 0; // 성공한 수를 반환하기 위한 값
		PreparedStatement pstmt = null; // SQL 구문을 실행하는 역할로 Statement 클래스의 기능 향상된 클래스다
		String sql = prop.getProperty("insertAttachment"); // getProperty 메소드를 사용하여 sql 구문을 String형 변수에 담는다
		
		try {
			for(int i = 0 ; i < fileList.size() ; i++) {
				UsedAttachment at = fileList.get(i);
				
				pstmt = conn.prepareStatement(sql); // prepareStatement 메소드에 sql 문을 전달하여 prepareStatement 객체를 생성한다
				
				pstmt.setString(1, at.getOriginName()); 
				pstmt.setString(2, at.getChangeName()); 
				pstmt.setString(3, at.getFilePath());
				
				result += pstmt.executeUpdate(); // update sql 실행 -> 성공한 행 만큼의 수를 result에 더하며 담는다
				System.out.println("Dao result => " + result); // 임의 확인
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
