package com.uni.event.model.service;

import static com.uni.common.JDBCTemplate.*;

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

}
