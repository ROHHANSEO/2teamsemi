package com.uni.usedItemBoard.model.service;

import static com.uni.common.JDBCTemplate.close;
import static com.uni.common.JDBCTemplate.commit;
import static com.uni.common.JDBCTemplate.getConnection;
import static com.uni.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.usedItemBoard.model.dao.UsedItemsBoardDao;
import com.uni.usedItemBoard.model.vo.Category;
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

	
}
