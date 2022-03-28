package com.uni.auction.model.dao;

import static com.uni.common.JDBCTemplate.close;

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

import com.uni.auction.model.vo.Auction;
import com.uni.auction.model.vo.PageInfo;
import com.uni.serviceCenter.model.vo.ServiceCenter;
import com.uni.usedItemBoard.model.vo.Category;
import com.uni.usedItemBoard.model.vo.UsedItemsBoard;

public class AuctionDao {
	private Properties prop = new Properties();

	public AuctionDao() {
		String fileName = AuctionDao.class.getResource("/sql/auction/auction-query.properties").getPath();
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
	
	public ArrayList<Category> categoryList(Connection conn) {//카테고리 리스트 값 받아오는 것
		ArrayList<Category> list = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("categoryList");
		//SELECT CATEGORYCODE, CATEGORYNAME FROM CATEGORY 
		//WHERE CATEGORY_NO=2 AND CAT_LEVEL = 1;
		try {
			
			pstmt= conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();


			list = new ArrayList<>();
		
			while(rset.next()) {
				Category c =new Category();
				c.setCode(rset.getString("CATEGORYCODE"));
				c.setName(rset.getString("CATEGORYNAME"));
				
				list.add(c);
				System.out.println(c + "dao 카테고리 받아오자 ");

			}
			
			System.out.println(list +"   auctiondao에서  받아온 list 값");
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			return list;
		}

	public int getListCount(Connection conn) {//리스트 갯수 가져오기 
		int listCount = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("getListCount");
		
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

	//맞는 페이지의 리스트를 받아오는 것
	public ArrayList<Auction> selectList(Connection conn, PageInfo pi) {
		ArrayList<Auction> list = new ArrayList<>();
		
		// PreparedStatement 객체와 ResultSet 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		// 시작하는 행과 끝나는 행의 수를 받아옴
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
		
		//selectList=SELECT * FROM(SELECT ROWNUM RNUM, A.* \
		//FROM(SELECT BOARD_NO, AUCTION_TITLE, COUNT, ITEM_DIRECT,SELL_STATUS, CHANGE_NAME \
		//FROM AUCTION_SELL JOIN(SELECT * FROM AUCTION_ATT WHERE FILE_NO IN(SELECT MIN(FILE_NO) FILE_NO \
		//FROM AUCTION_ATT WHERE STATUS='Y' GROUP BY BOARD_NO)) USING (BOARD_NO) \
		//WHERE AUCTION_SELL.STATUS='Y' ORDER BY BOARD_NO DESC) A) WHERE RNUM BETWEEN ? AND ?\
		
		try {
			// sql문 담기
			pstmt = conn.prepareStatement(sql);
			
			// sql 구문에 ?인덱스에 맞는 값 넣기
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			// sql문 실행
			rset = pstmt.executeQuery();
			
			// 여러행을 받아오기 때문에 while문
			while(rset.next()) {
				// 객체를 생성하여 list에 담는다
				list.add(new Auction(rset.getInt("BOARD_NO"),
											rset.getString("AUCTION_TITLE"),
											rset.getInt("ITEM_DIRECT"),
											rset.getString("SELL_STATUS"),
											rset.getInt("COUNT"),
											rset.getString("CHANGE_NAME")
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

	public ArrayList<Category> totCategoryList(Connection conn) {
		ArrayList<Category> list = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("totCategoryList");
		//SELECT CATEGORYCODE, CAT_LEVEL, CATEGORYNAME, CATEGORYDEREF, CATEGORYDEREF2
		try {
			
			pstmt= conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();


			list = new ArrayList<>();
		
			while(rset.next()) {
				Category c =new Category();
				c.setCode(rset.getString("CATEGORYCODE"));//고유코드
				c.setLevel(rset.getString("CAT_LEVEL"));//분류
				c.setName(rset.getString("CATEGORYNAME"));//카테고리명
				c.setRef1(rset.getString("CATEGORYDEREF"));//대분류 관련 코드
				c.setRef2(rset.getString("CATEGORYDEREF2"));//중분류 관련 코드
				
				list.add(c);
				System.out.println(c + "dao 카테고리 받아오자 ");

			}
			
			System.out.println(list +"   auctiondao에서  받아온 list 값");
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			return list;
	}

	public ArrayList<Category> selectCategoryetc(Connection conn, String category) {
		ArrayList<Category> cList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String sql = prop.getProperty("selectSmall");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, category);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				cList.add(new Category(rset.getString("CATEGORYCODE"), 
									  Integer.parseInt(rset.getString("CATEGORY_NO")), 
									  rset.getString("CAT_LEVEL"), 
									  rset.getString("CATEGORYNAME"), 
									  rset.getString("CATEGORYDEREF"), 
									  rset.getString("CATEGORYDEREF2")));
			}
			System.out.println("다오 category => "+cList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return cList;
	}

	public ArrayList<Category> selectMiddle(Connection conn, String category) {
		ArrayList<Category> cList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String sql = prop.getProperty("selectMiddle");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, category);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				cList.add(new Category(rset.getString("CATEGORYCODE"), 
						  Integer.parseInt(rset.getString("CATEGORY_NO")), 
						  rset.getString("CAT_LEVEL"), 
						  rset.getString("CATEGORYNAME"), 
						  rset.getString("CATEGORYDEREF"), 
						  rset.getString("CATEGORYDEREF2")));
			}
			System.out.println("다오 category => "+cList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return cList;
	}
	


}
