package com.uni.serviceCenter.model.service;

import static com.uni.common.JDBCTemplate.close;
import static com.uni.common.JDBCTemplate.commit;
import static com.uni.common.JDBCTemplate.getConnection;
import static com.uni.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.serviceCenter.model.dao.ServiceCenterDao;
import com.uni.serviceCenter.model.vo.ServiceCenter;

public class ServiceCenterService {

	/*public ArrayList<ServiceCenter> selectList() {
		Connection conn = getConnection();
		
		ArrayList<ServiceCenter> list = new ServiceCenterDao().selectList(conn);
		close(conn);
		return list;
	}*/

	public ArrayList<ServiceCenter> selectCList(int result) {
		Connection conn = getConnection();
		
		ArrayList<ServiceCenter> list = new ServiceCenterDao().selectCList(conn, result);
		close(conn);
		
		return list;
	}


	public ArrayList<ServiceCenter> findCList(String selectinput, String input) {
		Connection conn = getConnection();
		
		ArrayList<ServiceCenter> list = new ServiceCenterDao().findCList(conn, selectinput, input);
		close(conn);
		
		return list;
	}


	public int insertServiceCenter(ServiceCenter sc) {//고객센터 글 등록
		Connection conn = getConnection();
		
		int result = new ServiceCenterDao().insertServiceCenter(conn,sc);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}


	

}
