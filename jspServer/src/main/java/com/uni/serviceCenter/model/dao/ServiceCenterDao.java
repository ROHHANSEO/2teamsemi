package com.uni.serviceCenter.model.dao;

import static com.uni.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.uni.serviceCenter.model.vo.ServiceCenter;

public class ServiceCenterDao {
	private Properties prop = new Properties();

	public ServiceCenterDao() {
		String fileName = ServiceCenterDao.class.getResource("/sql/serviceCenter/service-query.properties").getPath();
		System.out.println("fileName   " + fileName);
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
//	public ArrayList<ServiceCenter> selectList(Connection conn) {
//		ArrayList<ServiceCenter> list = new ArrayList<ServiceCenter>();
//		
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		String sql = prop.getProperty("selectList");
//		//selectList=SELECT SERVICE_NO, SERVICE_TITLE, USER_ID, COUNT, CREATE_DATE FROM SERVICE_CENTER SC JOIN MEMBER ON (SERVICE_WRITER=USER_NO) WHERE SC.STATUS='Y' ORDER BY SERVICE_NO DESC
//		
//		try {
//			pstmt= conn.prepareStatement(sql);
//			rset = pstmt.executeQuery();
//		
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//	}

	public ArrayList<ServiceCenter> selectCList(Connection conn, int result) {
		ArrayList<ServiceCenter> list = null;
		
		System.out.println(result + "     serviceDao!!!!!!list값 받아오는 것 ");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCList");
		//SELECT SERVICE_NO, SERVICE_TITLE, COUNT FROM SERVICE_CENTER SC  JOIN MEMBER ON (SERVICE_WRITER=USER_NO) WHERE SC.STATUS='Y' AND CATEGORY =? ORDER BY SERVICE_NO DESC
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, result);
			rset = pstmt.executeQuery();
			 
			System.out.println(pstmt+ "이건 pstmt");
			System.out.println(rset + "이건 rset");
			System.out.println(sql +"이건 sql문");

			list = new ArrayList<>();
		
			while(rset.next()) {
				list.add(new ServiceCenter(
										rset.getInt("SERVICE_NO"),
										rset.getString("SERVICE_TITLE"), 
										rset.getString("SERVICE_CONTENT")));
				
				
			}
			
			System.out.println(list +"     serviceCenter 받아온 list 값");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

}
