package com.uni.auction.model.service;

import static com.uni.common.JDBCTemplate.close;
import static com.uni.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.auction.model.dao.AuctionDao;
import com.uni.auction.model.vo.Auction;
import com.uni.auction.model.vo.PageInfo;
import com.uni.usedItemBoard.model.dao.UsedItemsBoardDao;
import com.uni.usedItemBoard.model.vo.Category;

public class AuctionService {

	public ArrayList<Category> categoryList() {
		Connection conn = getConnection();
		
		ArrayList<Category> list = new AuctionDao().categoryList(conn);
		close(conn);
		
		return list;
	}

	public int getListCount() {// 리스트 갯수
		Connection conn = getConnection();
		
		//int로 리스트 갯수 받아오기 
		int listCount = new AuctionDao().getListCount(conn);
		System.out.println("service listCount ===" + listCount);
		close(conn);
		
		return listCount;//리스트 카운트 다시 서블렛으로 보내주기
	}

	public ArrayList<Auction> selectList(PageInfo pi) {//맞는 페이지의 LIST받아오기
		Connection conn = getConnection();
		//pi는 페이지 정보
		
		//리스트 받아오기 
		ArrayList<Auction> list = new AuctionDao().selectList(conn, pi);
		
		System.out.println("service selectList ==="+list);
		close(conn);
		return list;
	}

	public ArrayList<Category> totCategoryList() {
		Connection conn = getConnection();
		
		ArrayList<Category> cList = new AuctionDao().totCategoryList(conn);
		close(conn);
		
		return cList;
	}

	public ArrayList<Category> selectCategoryetc(String category) {
		Connection conn = getConnection();
		
		ArrayList<Category> cList = new AuctionDao().selectCategoryetc(conn, category);
		System.out.println("서비스 middle => " + cList);
		close(conn);
		
		return cList;
	}

	public ArrayList<Category> selectMiddle(String category) {
		Connection conn = getConnection();
		
		ArrayList<Category> cList = new AuctionDao().selectMiddle(conn, category);
		System.out.println("서비스 middle => " + cList);
		close(conn);
		
		return cList;
	}

	


}
