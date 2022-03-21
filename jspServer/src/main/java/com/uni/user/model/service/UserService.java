package com.uni.user.model.service;

import static com.uni.common.JDBCTemplate.*;


import java.sql.Connection;

import com.uni.user.model.dao.UserDao;
import com.uni.user.model.vo.User;

public class UserService {

	public int insertUser(User user) {
		Connection conn = getConnection();
		
		int result = new UserDao().insertMembr(conn,user);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;	
		
	}

	public int idCheck(String userId) {
		Connection conn = getConnection();
		
		int result = new UserDao().idCheck(conn,userId);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;	
	}

	public User loginUser(User user) {
		Connection conn = getConnection();
		
		User loginUser = new UserDao().loginUser(conn, user);
		close(conn);
		return loginUser;
	}

}
