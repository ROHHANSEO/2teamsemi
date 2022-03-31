package com.uni.event.model.service;

import static com.uni.common.JDBCTemplate.close;
import static com.uni.common.JDBCTemplate.commit;
import static com.uni.common.JDBCTemplate.getConnection;
import static com.uni.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.event.model.dao.EventDao;
import com.uni.event.model.vo.Event;
import com.uni.event.model.vo.PageInfo;



public class EventService {

	public ArrayList<Event> selectList() {
		Connection conn = getConnection();
		
		ArrayList<Event> list = new EventDao().selectList(conn);
		close(conn);
		return list;
	}

	

	public Event selectEvent(int nno) {
		Connection conn = getConnection();
		
		int result = new EventDao().increaseCount(conn, nno);
		
		Event e = null;
		
		if(result > 0) {
			commit(conn);
			e = new EventDao().selectEvent(conn, nno);
			
		}else {
			rollback(conn);
		}
		close(conn);
		return e;
	}
	
	public int deleteEvent(int nno) {
		Connection conn = getConnection();
		
		int result = new EventDao().deletEvent(conn, nno);
		
		System.out.println("nno삭제?====>" + nno);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	



	public int updateNotice(Event event) {
		Connection conn = getConnection();
		int result = new EventDao().updateNotice(conn, event);//NoticeDao 에 보낸다.
		
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}



	public ArrayList<Event> selectList(PageInfo pi) {
		Connection conn = getConnection();
		//pi는 페이지 정보
		
		//리스트 받아오기 
		ArrayList<Event> list = new EventDao().selectList(conn, pi);
		
		System.out.println("service selectList eventservice ==="+list);
		close(conn);
		return list;
	}



	public int getListCount() {
		Connection conn = getConnection();
		
		//int로 리스트 갯수 받아오기 
		int listCount = new EventDao().getListCount(conn);
		System.out.println("service listCount =====>" + listCount);
		close(conn);
		
		return listCount;//리스트 카운트 다시 서블렛으로 보내주기
	}



	public int insertnotice(Event sc) {
		Connection conn = getConnection();
		
		int result = new EventDao().insertEvent(conn,sc);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}



	
	
	
	
}
