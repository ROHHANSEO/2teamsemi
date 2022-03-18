package com.uni.serviceCenter.model.service;

import static com.uni.common.JDBCTemplate.*;

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

}
