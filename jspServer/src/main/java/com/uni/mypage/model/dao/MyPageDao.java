package com.uni.mypage.model.dao;

import static com.uni.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;

import com.uni.auction.model.vo.Auction;
import com.uni.usedItemBoard.model.dao.UsedItemsBoardDao;
import com.uni.usedItemBoard.model.vo.LikeProduct;
import com.uni.usedItemBoard.model.vo.UsedItemsBoard;

public class MyPageDao {
	private Properties prop = new Properties();
	
	DecimalFormat dc = new DecimalFormat("###,###,###");
	
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


	public ArrayList<UsedItemsBoard> patmentList(Connection conn, int userNo) {
		ArrayList<UsedItemsBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("paymentList");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				UsedItemsBoard board = (new UsedItemsBoard(
											rset.getInt("BOARD_NO"),
											rset.getString("BOARD_TITLE"),
											dc.format(rset.getInt("PRICE")),
											rset.getString("SALE_STATUS"),
											rset.getInt("LIKE_COUNT"),
											rset.getString("ORIGIN_NAME")
											));
			board.setCreateDate(rset.getDate("CREATE_DATE"));
			list.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;

	}


	public ArrayList<Auction> myBidActionList(Connection conn, int userNo) {
		ArrayList<Auction> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("myBidActionList");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, userNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Auction board = new Auction();
				
				board.setAuctionNo(rset.getInt("BOARD_NO"));
				board.setAuctionTitle(rset.getString("AUCTION_TITLE"));
				board.setStatus(rset.getString("SELL_STATUS"));
				board.setTitleImg(rset.getString("CHANGE_NAME"));
				board.setCreateDate(rset.getDate("CREATE_DATE"));
				board.setDatenext(rset.getString("NEW_DATE"));
				board.setItemDirect(rset.getInt("ITEM_DIRECT"));
				board.setMybid(rset.getInt("AA"));
				
				

				list.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public ArrayList<UsedItemsBoard> likeProductList(Connection conn, int userNo) {
		ArrayList<UsedItemsBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("likeProductList");
		System.out.println(sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				UsedItemsBoard board = (new UsedItemsBoard(
											rset.getInt("BOARD_NO"),
											rset.getString("BOARD_TITLE"),
											dc.format(rset.getInt("PRICE")),
											rset.getString("SALE_STATUS"),
											rset.getInt("LIKE_COUNT"),
											rset.getString("ORIGIN_NAME")
											));
			board.setCreateDate(rset.getDate("CREATE_DATE"));
			board.setUsedBoardWriter(rset.getString("WRITER_NO"));
			list.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public ArrayList<Auction> mypostActionList(Connection conn, int userNo) {
		ArrayList<Auction> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("mypostActionList");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Auction board = new Auction();
				
				board.setAuctionNo(rset.getInt("BOARD_NO"));
				board.setAuctionTitle(rset.getString("AUCTION_TITLE"));
				board.setStatus(rset.getString("SELL_STATUS"));
				board.setTitleImg(rset.getString("CHANGE_NAME"));
				board.setCreateDate(rset.getDate("CREATE_DATE"));
				board.setDatenext(rset.getString("NEW_DATE"));
				board.setItemDirect(rset.getInt("ITEM_DIRECT"));
				board.setMybid(rset.getInt("AA"));
				
				

				list.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	public int actionRecordDelete(Connection conn, String bno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("actionRecordDelete");

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


	public int actionSelectDelete(Connection conn, String[] bnoArr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("actionRecordDelete");

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


	public int actionAlldelete(Connection conn, int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("actionAlldelete");

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
