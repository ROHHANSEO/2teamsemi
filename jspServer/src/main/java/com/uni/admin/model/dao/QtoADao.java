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

import com.uni.admin.model.service.Reply;
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
		
		//SELECT SQ.QUESTION_NO, PREVNO, NEXTNO, M.USER_NO, M.USER_ID, QUESTION_TITLE, QUESTION_CONTENT, WRITE_DATE, CATEGORY_NO \
		//FROM(SELECT QUESTION_NO, LAG(QUESTION_NO) OVER (ORDER BY QUESTION_NO) PREVNO, LEAD(QUESTION_NO) OVER(ORDER BY QUESTION_NO) NEXTNO \
		//FROM SERVICE_Q) SSQ JOIN SERVICE_Q SQ ON (SQ.QUESTION_NO = SSQ.QUESTION_NO)\
		//JOIN MEMBER M ON (SQ.USER_NO=M.USER_NO) WHERE SQ.QUESTION_NO = ?
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				qa = new QtoA(rset.getInt("QUESTION_NO"),
						rset.getInt(2),
						rset.getInt(3),
						rset.getString("USER_NO"),
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

	public ArrayList<Reply> selectRlist(Connection conn, int scno) {
		ArrayList<Reply> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectRlist");
		//selectRlist=SELECT ANSWER_NO, ANSWER_CONTENT, USER_NO, CREATE_DATE\
		//FROM REPLY R JOIN MEMBER M ON(R.USER_NO = M.USER_NO)\
		//WHERE R.QUESTION_NO = ?\
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scno);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {//list는 한개가 아닌 여러개이기 때문에 while문을 돌려줘야 한다.
				
				list.add(new Reply(rset.getInt("ANSWER_NO"),
											rset.getString("ANSWER_CONTENT"), 
											rset.getString("USER_ID"), 
											rset.getDate("CREATE_DATE")));
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

	public int insertReply(Connection conn, Reply r) {
		int result = 0;
		PreparedStatement pstmt = null;
		System.out.println(r + "댓글 값 들어왓는지 확인");
		String sql = prop.getProperty("insertReply");
		//INSERT INTO SERVICE_QANSWER VALUES(SEQ_QR.NEXTVAL, ?,?,?,SYSDATE,DEFAULT)
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getRefQuestion());
			pstmt.setString(2, r.getReplyWriter());
			pstmt.setString(3, r.getReplyContent());
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

}
