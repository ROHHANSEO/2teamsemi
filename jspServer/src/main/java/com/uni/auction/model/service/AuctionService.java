package com.uni.auction.model.service;

import static com.uni.common.JDBCTemplate.close;
import static com.uni.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.auction.model.dao.AuctionDao;
import com.uni.usedItemBoard.model.vo.Category;

public class AuctionService {

	public ArrayList<Category> categoryList() {
		Connection conn = getConnection();
		
		ArrayList<Category> list = new AuctionDao().categoryList(conn);
		close(conn);
		
		return list;
	}

}
