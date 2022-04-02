package com.uni.auction.model.service;

import static com.uni.common.JDBCTemplate.close;
import static com.uni.common.JDBCTemplate.commit;
import static com.uni.common.JDBCTemplate.getConnection;
import static com.uni.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.admin.model.vo.BlockBoard;
import com.uni.auction.model.dao.AuctionDao;
import com.uni.auction.model.vo.Auction;
import com.uni.auction.model.vo.AuctionAttachment;
import com.uni.auction.model.vo.PageInfo;
import com.uni.auction.model.vo.sellRecord;
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
	

	public int getCaListCount(String li) {//카테고리 별 리스트 개수 받아오기
		Connection conn = getConnection();
		
		//int로 리스트 갯수 받아오기 
		int listCount = new AuctionDao().getCaListCount(conn, li);
		System.out.println("service listCount ===" + listCount);
		close(conn);
		
		return listCount;//리스트 카운트 다시 서블렛으로 보내주기
	}

	//검색을 눌렀을 시에 가져오는 리스트 갯수
 	public int getFdListCount(String input) {
 		Connection conn = getConnection();
		
		//int로 리스트 갯수 받아오기 
		int listCount = new AuctionDao().getFdListCount(conn, input);
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
	//카테고리 눌렀을시에 가져오는 리스트
	public ArrayList<Auction> selectCList(PageInfo pi, String li) {
		Connection conn = getConnection();
		//pi는 페이지 정보
		
		//리스트 받아오기 
		ArrayList<Auction> list = new AuctionDao().selectCList(conn, pi, li);
		
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

	public int insertAuctionItem(Auction ub, ArrayList<AuctionAttachment> fileList) {
		Connection conn = getConnection();
		//게시글 내용
		int result1 = new AuctionDao().insertAuctionItem(conn, ub);
		//사진
		int result2 = new AuctionDao().insertAuctionAttachment(conn, fileList);
		//가격 정보 처음에 넣어줘야 한다. 
		int result3 = new AuctionDao().insertAuctionDeal(conn,ub);
		
		if(result1 > 0 && result2 > 0 && result3>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result1*result2*result3;
	}

	public Auction selectAuction(int scno) {//디테일 옥션 정보들 받아오는 것
		Connection conn = getConnection();
		Auction ac = new AuctionDao().selectAuction(conn, scno);
		close(conn);
		
		return ac;
	}

	public ArrayList<AuctionAttachment> selectAttachment(int scno) {//디테일 페이지에서 사진들 받아오는 것 
		Connection conn = getConnection();
		
		ArrayList<AuctionAttachment> at = new AuctionDao().selectAttachment(conn, scno);
		close(conn);
		
		return at;
	}

	public int deleteAuction(int scno) {//옥션 게시글 삭제
		Connection conn = getConnection();
		
		int result = new AuctionDao().deleteAuction(conn, scno);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updateStatus(String status, int scno) {
		Connection conn = getConnection();
		
		int result = new AuctionDao().updateStatus(conn, status, scno);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}



	public int changePrice(Auction a) {
		Connection conn = getConnection();
		
		int result = new AuctionDao().changePrice(conn,a);

		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public ArrayList<sellRecord> selectSellRecord(int scno) {
		Connection conn = getConnection();
		ArrayList<sellRecord> sr = new AuctionDao().selectSellRecord(conn, scno);

		close(conn);
		
		return sr;
	}


	public ArrayList<Auction> SearchfiveList(String search) {
		Connection conn = getConnection();
		
		// 성공한 행 리스트담음
		ArrayList<Auction> acList = new AuctionDao().SearchfiveList(conn, search);
		System.out.println("서비스 acList =>" + acList);
		close(conn);
		
		return acList;// 리스트 반환
	}

	public int insertBlock(BlockBoard bb) {
		Connection conn = getConnection();
		
		int result = new AuctionDao().insertBlock(conn,bb);
		

		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	//더보기 해서 들어갈때 보이는 리스트들
	public ArrayList<Auction> searchAuctionList(String search, PageInfo pi) {
		Connection conn = getConnection();
		
		// 성공한 행 리스트담음
		ArrayList<Auction> acList = new AuctionDao().searchAuctionList(conn, search, pi);
		System.out.println("서비스 acList =>" + acList);
		close(conn);
		
		return acList;// 리스트 반환
	}
	
	public int getListSearchCount(String search) {
		Connection conn = getConnection();
		
		//int로 리스트 갯수 받아오기 
		int listCount = new AuctionDao().getListSearchCount(conn, search);
		System.out.println("service listCount ===" + listCount);
		close(conn);
		
		return listCount;//리스트 카운트 다시 서블렛으로 보내주기
  }

	public String selectAllcategory(String categorycode) {
		Connection conn = getConnection();

		String category = new AuctionDao().selectAllcategory(conn, categorycode);
		System.out.println("서비스 allcategory ==> "+ category);
		close(conn);
		return category;
	}


	public int deleteAttachment(int scno) {
		Connection conn = getConnection();
		
		int result = new AuctionDao().deleteAttachment(conn, scno);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updateAuctionItem(Auction at, ArrayList<AuctionAttachment> fileList) {
		Connection conn = getConnection();
		
		int result1 = new AuctionDao().updateAuction(conn, at);
		int result2 = new AuctionDao().insertNewAuctionAttachment(conn, at.getAuctionNo(), fileList);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result1*result2;
	}

	public int increaseCount(int scno) {
		Connection conn = getConnection();
		
		int result = new AuctionDao().increaseCount(conn,scno);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	//조회수가 많은 리스트대로
	public ArrayList<Auction> selectPopList(PageInfo pi) {
		Connection conn = getConnection();
		//pi는 페이지 정보
		
		//리스트 받아오기 
		ArrayList<Auction> list = new AuctionDao().selectPopList(conn, pi);
		
		//System.out.println("service selectList ==="+list);
		close(conn);
		return list;
	}
	//저가순으로 리스트 가져오기
	public ArrayList<Auction> selectlowList(PageInfo pi) {
		Connection conn = getConnection();
		//pi는 페이지 정보
		
		//리스트 받아오기 
		ArrayList<Auction> list = new AuctionDao().selectlowList(conn, pi);
		
		//System.out.println("service selectList ==="+list);
		close(conn);
		return list;
	}

	public ArrayList<Auction> selectHighList(PageInfo pi) {
		Connection conn = getConnection();
		//pi는 페이지 정보
		
		//리스트 받아오기 selectHighList
		ArrayList<Auction> list = new AuctionDao().selectHighList(conn, pi);
		
		//System.out.println("service selectList ==="+list);
		close(conn);
		return list;
	}

	public int statusUpdate(int boardNo) {
		Connection conn = getConnection();
		
		int result = new AuctionDao().statusUpdate(conn, boardNo);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}



}
