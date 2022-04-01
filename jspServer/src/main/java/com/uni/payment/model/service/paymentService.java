package com.uni.payment.model.service;

import static com.uni.common.JDBCTemplate.*;

import java.sql.Connection;

import com.uni.payment.model.dao.paymentDao;
import com.uni.payment.model.vo.payment;

public class paymentService {

	public int Itempayment(payment pay) {
		Connection conn = getConnection();
		System.out.println("서비스------");
		int result = new paymentDao().Itempayment(conn,pay);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
