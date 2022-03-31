package com.uni.payment.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Properties;
import static com.uni.common.JDBCTemplate.*;

import com.uni.payment.model.vo.payment;
import com.uni.usedItemBoard.model.dao.UsedItemsBoardDao;

public class paymentDao {
	private Properties prop = new Properties();
	
	DecimalFormat dc = new DecimalFormat("###,###,###");
	
	public paymentDao() {
		String fileName = UsedItemsBoardDao.class.getResource("/sql/payment/payment.properties").getPath();
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

	public int Itempayment(Connection conn, payment pay) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("Itempayment");


		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pay.getMerchantUid());
			pstmt.setInt(2, pay.getBuyerNo());
			pstmt.setInt(3, pay.getBoardNo());
			pstmt.setInt(4, pay.getPrice());

			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
}
