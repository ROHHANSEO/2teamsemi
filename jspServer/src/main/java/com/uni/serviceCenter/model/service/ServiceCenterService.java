package com.uni.serviceCenter.model.service;

import static com.uni.common.JDBCTemplate.close;
import static com.uni.common.JDBCTemplate.commit;
import static com.uni.common.JDBCTemplate.getConnection;
import static com.uni.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.uni.serviceCenter.model.dao.ServiceCenterDao;
import com.uni.serviceCenter.model.vo.QtoA;
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


	public int insertQtoA(QtoA qa) {
		Connection conn = getConnection();
		
		int result = new ServiceCenterDao().insertQtoA(conn,qa);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}


	public ArrayList<ServiceCenter> selectTopList() {
		Connection conn = getConnection();
		ArrayList<ServiceCenter> list = new ServiceCenterDao().selectTopList(conn);
		close(conn);
		
		return list;
	}


	public int increaseCount(int no) {
		Connection conn = getConnection();
		
		int result = new ServiceCenterDao().increaseCount(conn,no);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}


	public ServiceCenter selectServiceCenter(int scno) {//선택한 리스트 수정할거 가져오기
		Connection conn = getConnection();
		
		int result = new ServiceCenterDao().increaseCount(conn, scno);
		
		ServiceCenter sc = null;
		if(result>0) {
			commit(conn);
			sc = new ServiceCenterDao().selectServiceCenter(conn, scno);
		}else {
			rollback(conn);
		}
		close(conn);
		return sc;
	}


	public int updateSC(ServiceCenter sc) {
		Connection conn = getConnection();
		
		int result = new ServiceCenterDao().updateSC(conn,sc);
		
		if(result > 0) {
			commit(conn);
			
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}


	
	

}
