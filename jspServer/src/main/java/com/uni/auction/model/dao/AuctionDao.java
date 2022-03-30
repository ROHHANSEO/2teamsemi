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
import com.uni.auction.model.vo.AuctionAttachment;
import com.uni.auction.model.vo.PageInfo;
import com.uni.serviceCenter.model.vo.ServiceCenter;
import com.uni.usedItemBoard.model.vo.Category;
import com.uni.usedItemBoard.model.vo.UsedAttachment;
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
		//FROM(SELECT BOARD_NO, CATEGORYCODE, AUCTION_TITLE, COUNT, ITEM_DIRECT,SELL_STATUS, CHANGE_NAME, TO_CHAR(CREATE_DATE,'yyMMddhh24mi') CA\
		//FROM AUCTION_SELL JOIN(SELECT * FROM AUCTION_ATT \
		//WHERE FILE_NO IN(SELECT MIN(FILE_NO) FILE_NO FROM AUCTION_ATT WHERE STATUS='Y' GROUP BY REF_CATEGORY)) \
		//ON (BOARD_NO=REF_CATEGORY) WHERE AUCTION_SELL.STATUS='Y' ORDER BY BOARD_NO DESC) A) \
		//WHERE RNUM BETWEEN ? AND ?
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
									rset.getString("CATEGORYCODE"),
									rset.getString("AUCTION_TITLE"),
									rset.getInt("ITEM_DIRECT"),
									rset.getString("SELL_STATUS"),
									rset.getInt("COUNT"),
									rset.getString("CHANGE_NAME"),
									rset.getString(9)
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

	public int insertAuctionItem(Connection conn, Auction ub) {
		int result = 0; // 성공한 수를 반환하기 위한 값
		PreparedStatement pstmt = null; // SQL 구문을 실행하는 역할로 Statement 클래스의 기능 향상된 클래스다
		String sql = prop.getProperty("insertAuctionItem"); // getProperty 메소드를 사용하여 sql 구문을 String형 변수에 담는다
		//INSERT INTO AUCTION_SELL VALUES(SEQ_AUCTION.NEXTVAL, 2, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT, SYSDATE, DEFAULT, DEFAULT, DEFAULT)
		//고유코드, 회원번호, 제목, 내용, 상품 상태, 경매시작가, 올릴 경매가, 즉시 판매가
		System.out.println("insert 물건 다오왔다감");
		try {
			pstmt = conn.prepareStatement(sql); // prepareStatement 메소드에 sql 문을 전달하여 prepareStatement 객체를 생성한다
			
			pstmt.setString(1, ub.getCategorycode()); // 고유코드
			pstmt.setInt(2, Integer.parseInt(ub.getAuctionWriter())); // 회원번호
			pstmt.setString(3, ub.getAuctionTitle()); // 제목
			pstmt.setString(4, ub.getAuctionContent()); // 내용
			pstmt.setString(5, ub.getItemCondition());	// 상품 상태
			pstmt.setInt(6, ub.getItemPrice());	// 경매 시작가 
			pstmt.setInt(7, ub.getItemUp());	// 올릴 경매가 
			pstmt.setInt(8, ub.getItemDirect());	// 즉시판매가
	
			System.out.println("다오왔다감");
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

	public int insertAuctionAttachment(Connection conn, ArrayList<AuctionAttachment> fileList) {
		int result = 0; // 성공한 수를 반환하기 위한 값
		PreparedStatement pstmt = null; // SQL 구문을 실행하는 역할로 Statement 클래스의 기능 향상된 클래스다
		String sql = prop.getProperty("insertAuctionAttachment"); // getProperty 메소드를 사용하여 sql 구문을 String형 변수에 담는다
		try {
			for(int i = 0 ; i < fileList.size() ; i++) {
				AuctionAttachment at = fileList.get(i);
				
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
