package com.uni.event.model.service;

import static com.uni.common.JDBCTemplate.close;
import static com.uni.common.JDBCTemplate.commit;
import static com.uni.common.JDBCTemplate.getConnection;
import static com.uni.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.event.model.dao.EventDao;
import com.uni.event.model.vo.Event;



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

	


	public int insertNotice(Event ev) {
		Connection conn = getConnection();//connection 연결
		
		int result = new EventDao().insertNotice(conn, ev);
		System.out.println("ev====" + ev);
		
		
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



	
	
	
	
}
