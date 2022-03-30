package com.uni.admin.model.dao;

import static com.uni.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import com.uni.admin.model.service.Reply;
import com.uni.admin.model.vo.BlockBoard;
import com.uni.serviceCenter.model.vo.QtoA;
import com.uni.user.model.vo.User;

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

	public ArrayList<BlockBoard> BlockBoardList(Connection conn) {
		ArrayList<BlockBoard> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("BlockBoardList");

		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				
				list.add(new BlockBoard(rset.getString("CLASS_NAME"),
									rset.getString("BOARD_TITLE"),
									rset.getInt("BOARD_NO"),
									rset.getInt("COUNT")));
				
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

	public ArrayList<BlockBoard> BlockBoardDetailList(Connection conn, String boardNo) {
		ArrayList<BlockBoard> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("BlockBoardDetailList");
		System.out.println(boardNo);
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(boardNo));
			System.out.println(pstmt);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				
				BlockBoard block = new BlockBoard(rset.getInt("BLOCK_NO"),
									rset.getString("BLOCK_TITLE"),
									rset.getString("BLOCK_CATEGORY"));
				
				DateFormat sdFormat = new SimpleDateFormat("yy-MM-dd");
				String day = sdFormat.format(rset.getDate("BLOCK_T"));
				
				block.setUpTime(day);
				list.add(block);
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

	public BlockBoard reportBoardDetail(Connection conn, String blockNo) {
		BlockBoard block = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("reportBoardDetail");
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(blockNo));
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				block = new BlockBoard(rset.getString("BLOCK_TITLE"),
									rset.getString("BLOCK_CON"));
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return block;
	}

	public int[] reportSanctionsCategoryNo(Connection conn, String blockNo) {
		int[] result = new int[2];
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("reportSanctionsCategoryNo");
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(blockNo));

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result[0] = rset.getInt("CATEGORY_NO");
				result[1] = Integer.parseInt(rset.getString("BOARD_NO"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int reportSanctionsUserNo(Connection conn, int[] categoryNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "";
		switch(categoryNo[0]) {
			case 1:
				sql = prop.getProperty("usedBoardUserNo");
				break;
			case 2:
				sql = prop.getProperty("actionBoardUserNo");
				break;
			case 3:
				sql = prop.getProperty("communityBoardUserNo");
				break;
		}
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo[1]);

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("WRITER_NO");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int reportSanctions(Connection conn, int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("reportSanctions");
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int boardBlock(Connection conn, int[] categoryNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "";
		switch(categoryNo[0]) {
			case 1:
				sql = prop.getProperty("usedBoardBlock");
				break;
			case 2:
				sql = prop.getProperty("actionBoardBlock");
				break;
			case 3:
				sql = prop.getProperty("communityBoardBlock");
				break;
		}
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, categoryNo[1]);
			
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int reportDelete(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("reportDelete");
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int[] SanctionsUser(Connection conn, String[] boN) {
		int result[] = new int [boN.length];
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "";
		
		for(int i = 0; i < boN.length; i++) {
			
			int boardNo = Integer.parseInt(boN[i]);
			
			if(boardNo > 1000000 && boardNo < 1999999) {
				sql = prop.getProperty("usedboarduser");
			} else if(boardNo > 2000000 && boardNo < 2999999) {
				sql = prop.getProperty("actionuser");
			}
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, boardNo);

				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					result[i] = rset.getInt("WRITER_NO");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		
		}
		
		return result;
	}

	public int selectreportSanctions(Connection conn, int[] userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("reportSanctions");
		try {
			
			for(int i = 0 ; i < userNo.length; i++) {
				pstmt = conn.prepareStatement(sql);
			
				pstmt.setInt(1, userNo[i]);
			
				result += pstmt.executeUpdate(); 
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectreportDelete(Connection conn, String[] boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("reportDelete");
		try {
			for(int i = 0; i < boardNo.length; i++) {
			
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, Integer.parseInt(boardNo[i]));
				
				result += pstmt.executeUpdate(); 
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int serviceDelete(Connection conn, String[] serviceNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("serviceDelete");
		try {
			for(int i = 0; i < serviceNo.length; i++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(serviceNo[i]));
				result = pstmt.executeUpdate(); 
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<User> AllUserList(Connection conn) {
		ArrayList<User> user = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("AllUserList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				user.add(new User(
						rset.getInt("USER_NO"),
						rset.getString("USER_ID"),
						rset.getString("USER_PWD"),
						rset.getString("USER_NAME"),
						rset.getString("CITI_NO"),
						rset.getString("PHONE"),
						rset.getString("NICK_NAME"),
						rset.getString("EMAIL"),
						rset.getString("GENDER"),
						rset.getString("ADMIN_STATUS"),
						rset.getString("STATUS"),
						rset.getInt("BAN_COUNT")
						));
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		
		return user;
	}

	public ArrayList<User> BanUserList(Connection conn) {
		ArrayList<User> user = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("BanUserList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				user.add(new User(
						rset.getInt("USER_NO"),
						rset.getString("USER_ID"),
						rset.getString("USER_PWD"),
						rset.getString("USER_NAME"),
						rset.getString("CITI_NO"),
						rset.getString("PHONE"),
						rset.getString("NICK_NAME"),
						rset.getString("EMAIL"),
						rset.getString("GENDER"),
						rset.getString("ADMIN_STATUS"),
						rset.getString("STATUS"),
						rset.getInt("BAN_COUNT")
						));
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		
		return user;
	}



}
