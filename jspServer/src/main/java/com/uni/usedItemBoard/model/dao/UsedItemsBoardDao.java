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

import com.uni.usedItemBoard.model.vo.Category;
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
		// 제너릭스를 사용한 UsedItemsBoard ArrayList 생성
		ArrayList<UsedItemsBoard> list = new ArrayList<>();
		// PreparedStatement 객체와 ResultSet 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// sql문을 getProperty() 하여 알맞는 sql 구문 가져와 생성 및 선언
		String sql = prop.getProperty("selectList");
		
		// 시작하는 행과 끝나는 행의 수를 받아옴
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
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
				list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
											rset.getString("BOARD_TITLE"),
											rset.getInt("PRICE"),
											rset.getString("SALE_STATUS"),
											rset.getInt("LIKE_COUNT"),
											rset.getString("ORIGIN_NAME")
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
		
		System.out.println("다오왔다감");
		try {
			pstmt = conn.prepareStatement(sql); // prepareStatement 메소드에 sql 문을 전달하여 prepareStatement 객체를 생성한다
			
			pstmt.setString(1, ub.getCategorycode()); // 고유코드
			pstmt.setString(2, ub.getUsedBoardTitle()); // 제목
			pstmt.setInt(3, Integer.parseInt(ub.getUsedBoardWriter())); // 회원번호
			pstmt.setString(4, ub.getUsedBoardContent()); // 내용
			pstmt.setInt(5, ub.getPrice());					// 가격
			pstmt.setString(6, ub.getSaleStatus()); // 상품상태
			pstmt.setString(7, ub.getPaymentOne());	// 직접결제(Y,N)
			pstmt.setString(8, ub.getPaymentTwo()); // 만나서결제(Y,N)
			
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

	public int insertUsedAttachment(Connection conn, ArrayList<UsedAttachment> fileList) {
		int result = 0; // 성공한 수를 반환하기 위한 값
		PreparedStatement pstmt = null; // SQL 구문을 실행하는 역할로 Statement 클래스의 기능 향상된 클래스다
		String sql = prop.getProperty("insertAttachment"); // getProperty 메소드를 사용하여 sql 구문을 String형 변수에 담는다
		
		try {
			for(int i = 0 ; i < fileList.size() ; i++) {
				UsedAttachment at = fileList.get(i);
				
				pstmt = conn.prepareStatement(sql); // prepareStatement 메소드에 sql 문을 전달하여 prepareStatement 객체를 생성한다
				
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getFilePath());
				
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

	public ArrayList<Category> selectCategory(Connection conn) {
		ArrayList<Category> cList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String sql = prop.getProperty("selectCategory");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
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

	public ArrayList<Category> selectMiddle(Connection conn, String large) {
		ArrayList<Category> cList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String sql = prop.getProperty("selectMiddle");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, large);
			
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

	public ArrayList<Category> selectSmall(Connection conn, String middle) {
		ArrayList<Category> cList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String sql = prop.getProperty("selectSmall");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, middle);
			
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

	public UsedItemsBoard selectUsedBoard(Connection conn, int bNo) {
		UsedItemsBoard ub = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String sql = prop.getProperty("selectUsedBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				ub = new UsedItemsBoard(rset.getString("CATEGORYCODE"), 
									  rset.getString("BOARD_TITLE"), 
									  rset.getString("NICK_NAME"), 
									  rset.getString("BOARD_CONTENT"), 
									  rset.getInt("PRICE"), 
									  rset.getString("SALE_STATUS"),
									  rset.getString("ITEM_CONDITION"),
									  rset.getInt("LIKE_COUNT"),
									  rset.getString("PAYMENT_ONE"),
									  rset.getString("PAYMENT_TWO"),
									  rset.getString("PAYMENT_STATUS"));
			}
			System.out.println("다오 ub => "+ub);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return ub;
	}

	public ArrayList<UsedAttachment> selectAttachment(Connection conn, int bNo) {
		ArrayList<UsedAttachment> ua = new ArrayList<>();
		UsedAttachment at = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				at = new UsedAttachment(rset.getInt("FILE_NO"), 
									  rset.getString("ORIGIN_NAME"));
				ua.add(at);
			}
			System.out.println("다오 ua => "+ua);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return ua;
	}
	
	// 리스트 뽑아오는 메소드
	public ArrayList<UsedItemsBoard> myPostList(Connection conn, int userNo) {
		ArrayList<UsedItemsBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String sql = prop.getProperty("myPostList");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				UsedItemsBoard board = (new UsedItemsBoard(rset.getInt("BOARD_NO"),
											rset.getString("BOARD_TITLE"),
											rset.getInt("PRICE"),
											rset.getString("SALE_STATUS"),
											rset.getInt("LIKE_COUNT"),
											rset.getString("ORIGIN_NAME")
											));
				board.setCreateDate(rset.getDate("CREATE_DATE"));
				list.add(board);
				
				System.out.println("다오 => "+board);
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

	public ArrayList<UsedItemsBoard> filteringList(Connection conn, String category, int minprice, int maxprice, String search,
			String except, PageInfo pi) {
		ArrayList<UsedItemsBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 시작하는 행과 끝나는 행의 수를 받아옴
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
		
			
		try {
			// 카테고리만 존재하면
			if(!category.equals("대분류") && minprice == 0 && maxprice == 0 && search.equals("null") && !except.equals("거래완료")) {
				System.out.println("카테 고리만 존재한다");
			
				String sql = prop.getProperty("filteringList1");
				// sql문 담기
				pstmt = conn.prepareStatement(sql);
				
				// sql 구문에 ?인덱스에 맞는 값 넣기
				pstmt.setString(1, category);
				pstmt.setString(2, category);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
				// sql문 실행
				rset = pstmt.executeQuery();
				
				// 여러행을 받아오기 때문에 while문
				while(rset.next()) {
					// 객체를 생성하여 list에 담는다
					list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
												rset.getString("BOARD_TITLE"),
												rset.getInt("PRICE"),
												rset.getString("SALE_STATUS"),
												rset.getInt("LIKE_COUNT"),
												rset.getString("ORIGIN_NAME")
												));
				}
				System.out.println("다오 => "+list);
			}else if(category.equals("대분류")  && minprice >= 0 && maxprice > 0 && search.equals("null") && !except.equals("거래완료")) {
				// 금액 범위만 존재하면
				System.out.println("금액범위만 존재한다");
				
				String sql = prop.getProperty("filteringList2");
				// sql문 담기
				pstmt = conn.prepareStatement(sql);
				
				// sql 구문에 ?인덱스에 맞는 값 넣기
				pstmt.setInt(1, minprice);
				pstmt.setInt(2, maxprice);
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
				// sql문 실행
				rset = pstmt.executeQuery();
				
				// 여러행을 받아오기 때문에 while문
				while(rset.next()) {
					// 객체를 생성하여 list에 담는다
					list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
												rset.getString("BOARD_TITLE"),
												rset.getInt("PRICE"),
												rset.getString("SALE_STATUS"),
												rset.getInt("LIKE_COUNT"),
												rset.getString("ORIGIN_NAME")
												));
				}
				System.out.println("다오 => "+list);
			}else if(category.equals("대분류")  && minprice == 0 && maxprice == 0 && !search.equals("null") && !except.equals("거래완료")) {
				// 검색내 검색을 한 경우
				System.out.println("검색내 검색만 존재한다");
				
				String sql = prop.getProperty("filteringList3");
				// sql문 담기
				pstmt = conn.prepareStatement(sql);
				
				// sql 구문에 ?인덱스에 맞는 값 넣기
				pstmt.setString(1, "%"+search+"%");
				pstmt.setString(2, "%"+search+"%");
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
				// sql문 실행
				rset = pstmt.executeQuery();
				
				// 여러행을 받아오기 때문에 while문
				while(rset.next()) {
					// 객체를 생성하여 list에 담는다
					list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
												rset.getString("BOARD_TITLE"),
												rset.getInt("PRICE"),
												rset.getString("SALE_STATUS"),
												rset.getInt("LIKE_COUNT"),
												rset.getString("ORIGIN_NAME")
												));
				}
				System.out.println("다오 => "+list);
			}else if(category.equals("대분류")  && minprice == 0 && maxprice == 0 && search.equals("null") && except.equals("거래완료")) {
				// 거래완료된 게시글 제외하기만 한경우
				System.out.println("게시글 제외하기만 존재한다");
				
				String sql = prop.getProperty("filteringList4");
				// sql문 담기
				pstmt = conn.prepareStatement(sql);
				
				// sql 구문에 ?인덱스에 맞는 값 넣기
				pstmt.setString(1, except);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				// sql문 실행
				rset = pstmt.executeQuery();
				
				// 여러행을 받아오기 때문에 while문
				while(rset.next()) {
					// 객체를 생성하여 list에 담는다
					list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
												rset.getString("BOARD_TITLE"),
												rset.getInt("PRICE"),
												rset.getString("SALE_STATUS"),
												rset.getInt("LIKE_COUNT"),
												rset.getString("ORIGIN_NAME")
												));
				}
				System.out.println("다오 => "+list);
			}else if(!category.equals("대분류")  && minprice >= 0 && maxprice > 0 && search.equals("null") && !except.equals("거래완료")) {
				// 카테고리와 가격 범위만 한 경우
				System.out.println("카테고리와 가격 범위만 존재한다");
				
				String sql = prop.getProperty("filteringList5");
				pstmt = conn.prepareStatement(sql);
				
				// sql 구문에 ?인덱스에 맞는 값 넣기
				pstmt.setString(1, category);
				pstmt.setString(2, category);
				pstmt.setInt(3, minprice);
				pstmt.setInt(4, maxprice);
				pstmt.setInt(5, startRow);
				pstmt.setInt(6, endRow);
				// sql문 실행
				rset = pstmt.executeQuery();
				
				// 여러행을 받아오기 때문에 while문
				while(rset.next()) {
					// 객체를 생성하여 list에 담는다
					list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
												rset.getString("BOARD_TITLE"),
												rset.getInt("PRICE"),
												rset.getString("SALE_STATUS"),
												rset.getInt("LIKE_COUNT"),
												rset.getString("ORIGIN_NAME")
												));
				}
				System.out.println("다오 => "+list);
			}else if(!category.equals("대분류") && minprice == 0 && maxprice == 0 && !search.equals("null") && !except.equals("거래완료")) {
				// 카테고리와 검색내 검색만 한 경우
				System.out.println("카테고리와 검색내 검색만 존재한다");
				
				String sql = prop.getProperty("filteringList6");
				// sql문 담기
				pstmt = conn.prepareStatement(sql);
				
				// sql 구문에 ?인덱스에 맞는 값 넣기
				pstmt.setString(1, category);
				pstmt.setString(2, category);
				pstmt.setString(3, "%"+search+"%");
				pstmt.setString(4, "%"+search+"%");
				pstmt.setInt(5, startRow);
				pstmt.setInt(6, endRow);
				// sql문 실행
				rset = pstmt.executeQuery();
				
				// 여러행을 받아오기 때문에 while문
				while(rset.next()) {
					// 객체를 생성하여 list에 담는다
					list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
												rset.getString("BOARD_TITLE"),
												rset.getInt("PRICE"),
												rset.getString("SALE_STATUS"),
												rset.getInt("LIKE_COUNT"),
												rset.getString("ORIGIN_NAME")
												));
				}
				System.out.println("다오 => "+list);
			}else if(!category.equals("대분류") && minprice == 0 && maxprice == 0 && search.equals("null") && except.equals("거래완료")) {
				// 카테고리와 거래완료된 게시글 제외하기만 한 경우
				System.out.println("카테고리와 거래완료된 게시글을 제외만 존재한다");
				
				String sql = prop.getProperty("filteringList7");
				// sql문 담기
				pstmt = conn.prepareStatement(sql);
				
				// sql 구문에 ?인덱스에 맞는 값 넣기
				pstmt.setString(1, category);
				pstmt.setString(2, category);
				pstmt.setString(3, except);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);
				// sql문 실행
				rset = pstmt.executeQuery();
				
				// 여러행을 받아오기 때문에 while문
				while(rset.next()) {
					// 객체를 생성하여 list에 담는다
					list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
												rset.getString("BOARD_TITLE"),
												rset.getInt("PRICE"),
												rset.getString("SALE_STATUS"),
												rset.getInt("LIKE_COUNT"),
												rset.getString("ORIGIN_NAME")
												));
				}
				System.out.println("다오 => "+list);
			}else if(category.equals("대분류") && minprice >= 0 && maxprice > 0 && !search.equals("null") && !except.equals("거래완료")) {
				// 가격범위와 검색내 검색만 한 경우
				System.out.println("가격범위와 검색내 검색만 존재한다");
				
				String sql = prop.getProperty("filteringList8");
				// sql문 담기
				pstmt = conn.prepareStatement(sql);
				
				// sql 구문에 ?인덱스에 맞는 값 넣기
				pstmt.setInt(1, minprice);
				pstmt.setInt(2, maxprice);
				pstmt.setString(3, "%"+search+"%");
				pstmt.setString(4, "%"+search+"%");
				pstmt.setInt(5, startRow);
				pstmt.setInt(6, endRow);
				
				// sql문 실행
				rset = pstmt.executeQuery();
				
				// 여러행을 받아오기 때문에 while문
				while(rset.next()) {
					// 객체를 생성하여 list에 담는다
					list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
												rset.getString("BOARD_TITLE"),
												rset.getInt("PRICE"),
												rset.getString("SALE_STATUS"),
												rset.getInt("LIKE_COUNT"),
												rset.getString("ORIGIN_NAME")
												));
				}
				System.out.println("다오 => "+list);
			}else if(category.equals("대분류")  && minprice >= 0 && maxprice > 0 && search.equals("null") && except.equals("거래완료")) {
				// 가격 범위와 거래 완료된 게시글 제외하기만 한 경우
				System.out.println("가격범위와 거래 완료돤 게시글 제외만 존재한다");
				
				String sql = prop.getProperty("filteringList9");
				// sql문 담기
				pstmt = conn.prepareStatement(sql);
				
				// sql 구문에 ?인덱스에 맞는 값 넣기
				pstmt.setInt(1, minprice);
				pstmt.setInt(2, maxprice);
				pstmt.setString(3, except);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);
				
				// sql문 실행
				rset = pstmt.executeQuery();
				
				// 여러행을 받아오기 때문에 while문
				while(rset.next()) {
					// 객체를 생성하여 list에 담는다
					list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
												rset.getString("BOARD_TITLE"),
												rset.getInt("PRICE"),
												rset.getString("SALE_STATUS"),
												rset.getInt("LIKE_COUNT"),
												rset.getString("ORIGIN_NAME")
												));
				}
				System.out.println("다오 => "+list);
			}else if(category.equals("대분류") && minprice == 0 && maxprice == 0 && !search.equals("null") && except.equals("거래완료")) {
				// 검색내 검색과 거래완료 게시글 제외하기만 한 경우
				System.out.println("검색 내 검색과 거래완료 게시글 제외하기만 존재한다");
				
				String sql = prop.getProperty("filteringList10");
				// sql문 담기
				pstmt = conn.prepareStatement(sql);
				
				// sql 구문에 ?인덱스에 맞는 값 넣기
				pstmt.setString(1, "%"+search+"%");
				pstmt.setString(2, "%"+search+"%");
				pstmt.setString(3, except);
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);
				
				// sql문 실행
				rset = pstmt.executeQuery();
				
				// 여러행을 받아오기 때문에 while문
				while(rset.next()) {
					// 객체를 생성하여 list에 담는다
					list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
												rset.getString("BOARD_TITLE"),
												rset.getInt("PRICE"),
												rset.getString("SALE_STATUS"),
												rset.getInt("LIKE_COUNT"),
												rset.getString("ORIGIN_NAME")
												));
				}
				System.out.println("다오 => "+list);
			}else if(!category.equals("대분류") && minprice >= 0 && maxprice > 0 && !search.equals("null") && !except.equals("거래완료")) {
				// 카테고리와 가격 범위와 검색내 검색을 한 경우
				System.out.println("카테고리와 가격 범위와 검색내 검색만 존재한다");
				
				String sql = prop.getProperty("filteringList11");
				// sql문 담기
				pstmt = conn.prepareStatement(sql);
				
				// sql 구문에 ?인덱스에 맞는 값 넣기
				pstmt.setString(1, category);
				pstmt.setString(2, category);
				pstmt.setInt(3, minprice);
				pstmt.setInt(4, maxprice);
				pstmt.setString(5, "%"+search+"%");
				pstmt.setString(6, "%"+search+"%");
				pstmt.setInt(7, startRow);
				pstmt.setInt(8, endRow);
				
				// sql문 실행
				rset = pstmt.executeQuery();
				
				// 여러행을 받아오기 때문에 while문
				while(rset.next()) {
					// 객체를 생성하여 list에 담는다
					list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
												rset.getString("BOARD_TITLE"),
												rset.getInt("PRICE"),
												rset.getString("SALE_STATUS"),
												rset.getInt("LIKE_COUNT"),
												rset.getString("ORIGIN_NAME")
												));
				}
				System.out.println("다오 => "+list);
			}else if(!category.equals("대분류") && minprice == 0 && maxprice == 0 && !search.equals("null") && except.equals("거래완료")) {
				// 카테고리와 검색내 검색과 거래완료 게시글 제외하기을 한 경우
				System.out.println("카테고리와 검색내 검색과 거래완료 게시글 제외만 존재한다");
				
				String sql = prop.getProperty("filteringList12");
				// sql문 담기
				pstmt = conn.prepareStatement(sql);
				
				// sql 구문에 ?인덱스에 맞는 값 넣기
				pstmt.setString(1, category);
				pstmt.setString(2, category);
				pstmt.setString(3, "%"+search+"%");
				pstmt.setString(4, "%"+search+"%");
				pstmt.setString(5, except);
				pstmt.setInt(6, startRow);
				pstmt.setInt(7, endRow);
				
				// sql문 실행
				rset = pstmt.executeQuery();
				
				// 여러행을 받아오기 때문에 while문
				while(rset.next()) {
					// 객체를 생성하여 list에 담는다
					list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
												rset.getString("BOARD_TITLE"),
												rset.getInt("PRICE"),
												rset.getString("SALE_STATUS"),
												rset.getInt("LIKE_COUNT"),
												rset.getString("ORIGIN_NAME")
												));
				}
				System.out.println("다오 => "+list);
			}else if(!category.equals("대분류") && minprice >= 0 && maxprice > 0 && search.equals("null") && except.equals("거래완료")) {
				// 카테고리와 가격 범위와 거래완료 게시글 제외하기을 한 경우
				System.out.println("카테고리와 가격 범위와 거래완료 게시글 제외만 존재한다");
				
				String sql = prop.getProperty("filteringList13");
				// sql문 담기
				pstmt = conn.prepareStatement(sql);
				
				// sql 구문에 ?인덱스에 맞는 값 넣기
				pstmt.setString(1, category);
				pstmt.setString(2, category);
				pstmt.setInt(3, minprice);
				pstmt.setInt(4, maxprice);
				pstmt.setString(5, except);
				pstmt.setInt(6, startRow);
				pstmt.setInt(7, endRow);
				
				// sql문 실행
				rset = pstmt.executeQuery();
				
				// 여러행을 받아오기 때문에 while문
				while(rset.next()) {
					// 객체를 생성하여 list에 담는다
					list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
												rset.getString("BOARD_TITLE"),
												rset.getInt("PRICE"),
												rset.getString("SALE_STATUS"),
												rset.getInt("LIKE_COUNT"),
												rset.getString("ORIGIN_NAME")
												));
				}
				System.out.println("다오 => "+list);
			}else if(category.equals("대분류") && minprice >= 0 && maxprice > 0 && !search.equals("null") && except.equals("거래완료")) {
				// 가격 범위와 검색내 검색과 거래완로 게시글 제외하기를 한 경우
				System.out.println("가격 범위와 검색내 검색과 거래완료 게시글 제외만 존재한다");
				
				String sql = prop.getProperty("filteringList14");
				// sql문 담기
				pstmt = conn.prepareStatement(sql);
				
				// sql 구문에 ?인덱스에 맞는 값 넣기
				pstmt.setInt(1, minprice);
				pstmt.setInt(2, maxprice);
				pstmt.setString(3, "%"+search+"%");
				pstmt.setString(4, "%"+search+"%");
				pstmt.setString(5, except);
				pstmt.setInt(6, startRow);
				pstmt.setInt(7, endRow);
				
				// sql문 실행
				rset = pstmt.executeQuery();
				
				// 여러행을 받아오기 때문에 while문
				while(rset.next()) {
					// 객체를 생성하여 list에 담는다
					list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
												rset.getString("BOARD_TITLE"),
												rset.getInt("PRICE"),
												rset.getString("SALE_STATUS"),
												rset.getInt("LIKE_COUNT"),
												rset.getString("ORIGIN_NAME")
												));
				}
				System.out.println("다오 => "+list);
			}else {
				// 모두다 적용 한 경우
				
				String sql = prop.getProperty("filteringList15");
				// sql문 담기
				pstmt = conn.prepareStatement(sql);
				
				// sql 구문에 ?인덱스에 맞는 값 넣기
				pstmt.setString(1, category);
				pstmt.setString(2, category);
				pstmt.setInt(3, minprice);
				pstmt.setInt(4, maxprice);
				pstmt.setString(5, "%"+search+"%");
				pstmt.setString(6, "%"+search+"%");
				pstmt.setString(7, except);
				pstmt.setInt(8, startRow);
				pstmt.setInt(9, endRow);
				
				// sql문 실행
				rset = pstmt.executeQuery();
				
				// 여러행을 받아오기 때문에 while문
				while(rset.next()) {
					// 객체를 생성하여 list에 담는다
					list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
												rset.getString("BOARD_TITLE"),
												rset.getInt("PRICE"),
												rset.getString("SALE_STATUS"),
												rset.getInt("LIKE_COUNT"),
												rset.getString("ORIGIN_NAME")
												));
				}
				System.out.println("다오 => "+list);
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

	public ArrayList<UsedItemsBoard> selecPriceAscList(Connection conn, PageInfo pi) {
		// 제너릭스를 사용한 UsedItemsBoard ArrayList 생성
		ArrayList<UsedItemsBoard> list = new ArrayList<>();
		// PreparedStatement 객체와 ResultSet 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// sql문을 getProperty() 하여 알맞는 sql 구문 가져와 생성 및 선언
		String sql = prop.getProperty("priceAscList");
		
		// 시작하는 행과 끝나는 행의 수를 받아옴
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
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
				list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
											rset.getString("BOARD_TITLE"),
											rset.getInt("PRICE"),
											rset.getString("SALE_STATUS"),
											rset.getInt("LIKE_COUNT"),
											rset.getString("ORIGIN_NAME")
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

	public ArrayList<UsedItemsBoard> selecPriceDescList(Connection conn, PageInfo pi) {
		// 제너릭스를 사용한 UsedItemsBoard ArrayList 생성
		ArrayList<UsedItemsBoard> list = new ArrayList<>();
		// PreparedStatement 객체와 ResultSet 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// sql문을 getProperty() 하여 알맞는 sql 구문 가져와 생성 및 선언
		String sql = prop.getProperty("priceDescList");
		
		// 시작하는 행과 끝나는 행의 수를 받아옴
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
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
				list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
											rset.getString("BOARD_TITLE"),
											rset.getInt("PRICE"),
											rset.getString("SALE_STATUS"),
											rset.getInt("LIKE_COUNT"),
											rset.getString("ORIGIN_NAME")
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

	public ArrayList<UsedItemsBoard> selecLikeDescList(Connection conn, PageInfo pi) {
		// 제너릭스를 사용한 UsedItemsBoard ArrayList 생성
		ArrayList<UsedItemsBoard> list = new ArrayList<>();
		// PreparedStatement 객체와 ResultSet 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// sql문을 getProperty() 하여 알맞는 sql 구문 가져와 생성 및 선언
		String sql = prop.getProperty("likeDescList");
		
		// 시작하는 행과 끝나는 행의 수를 받아옴
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
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
				list.add(new UsedItemsBoard(rset.getInt("BOARD_NO"),
											rset.getString("BOARD_TITLE"),
											rset.getInt("PRICE"),
											rset.getString("SALE_STATUS"),
											rset.getInt("LIKE_COUNT"),
											rset.getString("ORIGIN_NAME")
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
