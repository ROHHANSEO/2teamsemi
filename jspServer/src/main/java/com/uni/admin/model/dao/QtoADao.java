package com.uni.admin.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import static com.uni.common.JDBCTemplate.*;

import com.uni.serviceCenter.model.vo.QtoA;

public class QtoADao {
	private Properties prop = new Properties();

	public QtoADao() {
		String fileName = QtoADao.class.getResource("/sql/admin/QtoA-query.properties").getPath();
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

	public ArrayList<QtoA> selectList(Connection conn) {
		ArrayList<QtoA> list = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectList");
		//SELECT QUESTION_NO, M.USER_ID, QUESTION_TITLE, QUESTION_CONTENT, WRITE_DATE, CATEGORY_NO 
		//FROM SERVICE_Q SQ JOIN MEMBER M ON (SQ.USER_NO=M.USER_NO) ORDER BY SQ.QUESTION_NO DESC
		
		list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new QtoA(rset.getInt("QUESTION_NO"),
									rset.getString("USER_ID"),
									rset.getString("QUESTION_TITLE"),
									rset.getString("QUESTION_CONTENT"),
									rset.getDate("WRITE_DATE")));
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

	public QtoA selectQtoA(Connection conn, int scno) {
		QtoA qa = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectQtoA");
		
		//SELECT QUESTION_NO, M.USER_ID, QUESTION_TITLE, QUESTION_CONTENT, WRITE_DATE, CATEGORY_NO 
		//FROM SERVICE_Q SQ JOIN MEMBER M ON (SQ.USER_NO=M.USER_NO) WHERE QUESTION_NO = ? ORDER BY SQ.QUESTION_NO DESC
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				qa = new QtoA(rset.getInt("QUESTION_NO"),
						rset.getString("USER_ID"),
						rset.getString("QUESTION_TITLE"),
						rset.getString("QUESTION_CONTENT"),
						rset.getDate("WRITE_DATE"),
						rset.getString("CATEGORY_NO"));
				
				System.out.println("QtoADao====="+qa);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return qa;
	}

}
