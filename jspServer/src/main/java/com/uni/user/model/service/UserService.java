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
		close(conn);
		return result;	
	}

	public User loginUser(User user) {
		Connection conn = getConnection();
		
		User loginUser = new UserDao().loginUser(conn, user);
		close(conn);
		return loginUser;
	}

	public User idSearch(User user) {
		Connection conn = getConnection();
		
		User idSearch = new UserDao().idSearch(conn, user);
		close(conn);
		return idSearch;
	}

	public User pwdSearch(User user) {
		Connection conn = getConnection();
		
		User pwdSearch = new UserDao().pwdSearch(conn, user);
		close(conn);
		return pwdSearch;
	}

	public int pwdChange(User user) {
		Connection conn = getConnection();
		
		int result = new UserDao().pwdChange(conn,user);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;	
	}

	public int deleteUser(int userNo) {
		Connection conn = getConnection();
		
		int result = new UserDao().deleteUser(conn,userNo);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;	
	}

	public int updateUser(User user) {
		Connection conn = getConnection();
		
		int result = new UserDao().updateUser(conn,user);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;	
	}

	public int adminUpdate(int userNo) {
		Connection conn = getConnection();
		
		int result = new UserDao().adminUpdate(conn,userNo);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;	
	}

	public User selectUser(int userNo) {
		Connection conn = getConnection();
		
		User user = new UserDao().selectUser(conn,userNo);
		close(conn);

		return user;	
	}

	public void autoLogin(String id, int userNo) {
		Connection conn = getConnection();
		int result = 0;


		result = new UserDao().autoLogin(conn,id,userNo);

		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
	}
	
	public User autoLoginUser(String cookie) {
		Connection conn = getConnection();
		System.out.println(cookie);
		
		int userNo = new UserDao().searchCookie(conn,cookie);
		
		User user = new UserDao().selectUser(conn,userNo);
		
		close(conn);
		System.out.println(user);
		return user;
	}

}
