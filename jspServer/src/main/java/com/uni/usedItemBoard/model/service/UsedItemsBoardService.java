package com.uni.usedItemBoard.model.service;

import static com.uni.common.JDBCTemplate.close; 
import static com.uni.common.JDBCTemplate.commit;
import static com.uni.common.JDBCTemplate.getConnection;
import static com.uni.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.admin.model.vo.BlockBoard;
import com.uni.usedItemBoard.model.dao.UsedItemsBoardDao;
import com.uni.usedItemBoard.model.vo.Category;
import com.uni.usedItemBoard.model.vo.LikeProduct;
import com.uni.usedItemBoard.model.vo.MonthlyMarketPrice;
import com.uni.usedItemBoard.model.vo.PageInfo;
import com.uni.usedItemBoard.model.vo.UsedAttachment;
import com.uni.usedItemBoard.model.vo.UsedItemsBoard;

public class UsedItemsBoardService {

	public int getCountList() { // 리스트 수 받기
		Connection conn = getConnection(); // 커넥션
		
		// 리스트 수
		int listCount = new UsedItemsBoardDao().getCountList(conn);

		System.out.println("서비스 listCount => " + listCount);
		close(conn);
		return listCount;
	}

	public ArrayList<UsedItemsBoard> selectList(PageInfo pi) { // 페이지 list 가져오기
		Connection conn = getConnection(); // 커넥션
		
		// 리스트 받기
		ArrayList<UsedItemsBoard> list = new UsedItemsBoardDao().selectList(conn, pi);
		

		System.out.println("서비스 => "+list);
		close(conn);
		return list;
		
	}

	public int insertUsedBoard(UsedItemsBoard ub, ArrayList<UsedAttachment> fileList) {
		Connection conn = getConnection();
		
		int result1 = new UsedItemsBoardDao().insertUsedBoard(conn, ub);
		int result2 = new UsedItemsBoardDao().insertUsedAttachment(conn, fileList);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result1*result2;
	}

	public ArrayList<Category> selectCategory() {
		Connection conn = getConnection();
		
		ArrayList<Category> cList = new UsedItemsBoardDao().selectCategory(conn);
		System.out.println("서비스 category => "+cList);
		close(conn);
		
		return cList;
	}

	public ArrayList<Category> selectMiddle(String large) {
		Connection conn = getConnection();
		
		ArrayList<Category> cList = new UsedItemsBoardDao().selectMiddle(conn, large);
		System.out.println("서비스 middle => " + cList);
		close(conn);
		
		return cList;
	}

	public ArrayList<Category> selectSmall(String middle) {
		Connection conn = getConnection();
		
		ArrayList<Category> cList = new UsedItemsBoardDao().selectSmall(conn, middle);
		System.out.println("서비스 small => " + cList);
		close(conn);
		
		return cList;
	}

	public UsedItemsBoard selectUsedBoard(int bNo) {
		Connection conn = getConnection();
		
		UsedItemsBoard ub = new UsedItemsBoardDao().selectUsedBoard(conn, bNo);
		close(conn);
		
		return ub;
	}

	public ArrayList<UsedAttachment> selectAttachment(int bNo) {
		Connection conn = getConnection();
		
		ArrayList<UsedAttachment> ua = new UsedItemsBoardDao().selectAttachment(conn, bNo);
		close(conn);
		
		return ua;
	}
	
	
	//마이페이지 판매기록 리스트 받아오기
	public ArrayList<UsedItemsBoard> myPostList(int userNo) {
		Connection conn = getConnection();

		ArrayList<UsedItemsBoard> list = new UsedItemsBoardDao().myPostList(conn, userNo);
		

		close(conn);
		return list;
	}

	public ArrayList<UsedItemsBoard> filteringList(String category, int minprice, int maxprice, String search,
			String except, PageInfo pi) {
		Connection conn = getConnection();

		ArrayList<UsedItemsBoard> list = new UsedItemsBoardDao().filteringList(conn, category, minprice, maxprice, search, except, pi);

		close(conn);
		return list;
	}

	public ArrayList<UsedItemsBoard> selecPriceAscList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<UsedItemsBoard> list = new UsedItemsBoardDao().selecPriceAscList(conn, pi);
		close(conn);
		return list;
	}

	public ArrayList<UsedItemsBoard> selecPriceDescList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<UsedItemsBoard> list = new UsedItemsBoardDao().selecPriceDescList(conn, pi);
		close(conn);
		return list;
	}

	public ArrayList<UsedItemsBoard> selecLikeDescList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<UsedItemsBoard> list = new UsedItemsBoardDao().selecLikeDescList(conn, pi);
		close(conn);
		return list;
	}

	public ArrayList<UsedItemsBoard> relationBoard(String category, String title) {
		Connection conn = getConnection();
		
		ArrayList<UsedItemsBoard> bList = new UsedItemsBoardDao().relationBoard(conn, category, title);
		close(conn);
		return bList;
	}

	public ArrayList<UsedItemsBoard> popularList() {
		Connection conn = getConnection();
		
		ArrayList<UsedItemsBoard> bList = new UsedItemsBoardDao().popularList(conn);
		close(conn);
		return bList;
	}

	public int statusUpdate(String status, int bNo) {
		Connection conn = getConnection();
		
		int result = new UsedItemsBoardDao().statusUpdate(conn, status, bNo);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int plusLike(int bNo, int uNo) {
		Connection conn = getConnection();
		
		int result1 = new UsedItemsBoardDao().plusLike(conn, bNo);
		int result2 = new UsedItemsBoardDao().createLike(conn, bNo, uNo);
		
		if(result1*result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result1*result2;
	}

	public int minusLike(int bNo, int uNo) {
		Connection conn = getConnection();
		
		int result1 = new UsedItemsBoardDao().minusLike(conn, bNo);
		int result2 = new UsedItemsBoardDao().deleteLike(conn, bNo, uNo);
		if(result1*result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result1*result2;
	}

	public int delateUsedItemBoard(int bNo) {
		Connection conn = getConnection();
		
		int result1 = new UsedItemsBoardDao().delateUsedItemBoard(conn, bNo);
		int result2 = new UsedItemsBoardDao().deleteLikeAll(conn, bNo);
		
		if(result1*result2 > 0 || result1 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result1;
	}

	public ArrayList<LikeProduct> selectLike(int bNo) {
		Connection conn = getConnection();
		
		ArrayList<LikeProduct> like = new UsedItemsBoardDao().selectLike(conn, bNo);
		close(conn);
		return like;
	}

	public String selectAllCategory(String code) {
		Connection conn = getConnection();
		
		String category = new UsedItemsBoardDao().selectAllCategory(conn, code);
		System.out.println("서비스 allcategory ==> "+ category);
		close(conn);
		return category;
	}

	public int deleteAttachment(int bNo) {
		Connection conn = getConnection();
		
		int result = new UsedItemsBoardDao().deleteAttachment(conn, bNo);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updateUsedBoard(UsedItemsBoard ub, ArrayList<UsedAttachment> fileList) {
		Connection conn = getConnection();
		
		int result1 = new UsedItemsBoardDao().updateUsedBoard(conn, ub);
		int result2 = new UsedItemsBoardDao().insertNewUsedAttachment(conn, ub.getUsedBoardNo(), fileList);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result1*result2;
	}

	public int reportUsedItemBoard(BlockBoard bb) {
		Connection conn = getConnection();
		
		
		int result1 = new UsedItemsBoardDao().reportUsedItemBoard(conn, bb);
		
		if(result1 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result1;
	}

	public ArrayList<UsedItemsBoard> SearchfiveList(String search) {
		Connection conn = getConnection();
		
		// 성공한 행 리스트담음
		ArrayList<UsedItemsBoard> ubList = new UsedItemsBoardDao().SearchfiveList(conn, search);
		System.out.println("서비스 ubList =>" + ubList);
		close(conn);
		
		return ubList;// 리스트 반환
	}

	public int getListSearchCount(String search) {
		Connection conn = getConnection();
		
		//int로 리스트 갯수 받아오기 
		int listCount = new UsedItemsBoardDao().getListSearchCount(conn, search);
		System.out.println("service listCount ===" + listCount);
		close(conn);
		
		return listCount;//리스트 카운트 다시 서블렛으로 보내주기
	}

	public ArrayList<UsedItemsBoard> searchUsedItemsList(String search, PageInfo pi) {
		Connection conn = getConnection();
		
		// 성공한 행 리스트담음
		ArrayList<UsedItemsBoard> ubList = new UsedItemsBoardDao().searchUsedItemsList(conn, search, pi);
		System.out.println("서비스 ubList =>" + ubList);
		close(conn);
		
		return ubList;// 리스트 반환
	}

	public ArrayList<UsedItemsBoard> searchUsedItemsLikeDescList(String search, PageInfo pi) {
		Connection conn = getConnection();
		
		// 성공한 행 리스트담음
		ArrayList<UsedItemsBoard> ubList = new UsedItemsBoardDao().searchUsedItemsLikeDescList(conn, search, pi);
		System.out.println("서비스 ubList =>" + ubList);
		close(conn);
		
		return ubList;// 리스트 반환
	}

	public ArrayList<UsedItemsBoard> searchUsedItemsPriceDescList(String search, PageInfo pi) {
		Connection conn = getConnection();
		
		// 성공한 행 리스트담음
		ArrayList<UsedItemsBoard> ubList = new UsedItemsBoardDao().searchUsedItemsPriceDescList(conn, search, pi);
		System.out.println("서비스 ubList =>" + ubList);
		close(conn);
		
		return ubList;// 리스트 반환
	}

	public ArrayList<UsedItemsBoard> searchUsedItemsPriceAscList(String search, PageInfo pi) {
		Connection conn = getConnection();
		
		// 성공한 행 리스트담음
		ArrayList<UsedItemsBoard> ubList = new UsedItemsBoardDao().searchUsedItemsPriceAscList(conn, search, pi);
		System.out.println("서비스 ubList =>" + ubList);
		close(conn);
		
		return ubList;// 리스트 반환
	}

	public ArrayList<MonthlyMarketPrice> goingrates(String search) {
		Connection conn = getConnection();
		
		// 성공한 행 리스트담음
		ArrayList<MonthlyMarketPrice> list = new UsedItemsBoardDao().goingrates(conn, search);
		System.out.println("서비스 시세 list =>" + list);
		close(conn);
		
		return list;// 리스트 반환
	}


	
}
