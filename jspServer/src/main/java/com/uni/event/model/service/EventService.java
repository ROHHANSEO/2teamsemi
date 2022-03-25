package com.uni.event.model.service;

import static com.uni.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.event.model.dao.EventDao;
import com.uni.event.model.vo.Event;
import com.uni.event.model.vo.PageInfo;



public class EventService {

	public ArrayList<Event> selectList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Event> list = new EventDao().selectList(conn);
		close(conn);
		return list;
	}

	public ArrayList<Event> service(String pt) throws Exception{
		EventDao dao = new EventDao();
		return dao.list(pt);
	}

	public Event selectEvent(int nno) {
		Connection conn = getConnection();
		Event e = null;
		//int result = new EventDao().increaseCount(conn,nno);
		e = new EventDao().selectEvent(conn, nno);
		
		
		if(e != null) {
			commit(conn);
			
		}else {
			rollback(conn);
		}
		close(conn);
		return e;
	}
	
	public int deleteEvent(int nno) {
		Connection conn = getConnection();
		
		int result = new EventDao().deletEvent(conn, nno);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int getListCount() {
		Connection conn = getConnection();
		
		int lisCount = new EventDao().getListCount(conn);
		close(conn);
		return lisCount;
	}		

	public ArrayList<Event> selectList1(PageInfo pi) {
		Connection conn = getConnection();//connection 연결
		 
		ArrayList<Event> list = new EventDao().selectList(conn,pi);
		
		close(conn);
		return list;
	}
	
	
	
}
