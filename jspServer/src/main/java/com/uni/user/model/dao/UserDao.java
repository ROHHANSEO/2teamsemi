package com.uni.user.model.dao;

import static com.uni.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.uni.user.model.vo.User;

public class UserDao {
	private Properties prop = new Properties();

	public UserDao() {
		String fileName = UserDao.class.getResource("/sql/user/user.properties").getPath();
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

	public int insertMembr(Connection conn, User user) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertUser");

		System.out.println(user);

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPwd());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getCitiNo());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getNickName());
			pstmt.setString(7, user.getEmail());
			pstmt.setString(8, user.getGender());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int idCheck(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		
		return result;
		
	}

	public User loginUser(Connection conn, User user) {
		User loginUser = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginUser");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPwd());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginUser = new User(
						rset.getInt("USER_NO"),
						rset.getString("USER_ID"),
						rset.getString("USER_PWD"),
						rset.getString("USER_NAME"),
						rset.getString("CITI_NO"),
						rset.getString("PHONE"),
						rset.getString("NICK_NAME"),
						rset.getString("EMAIL"),
						rset.getString("GENDER"),
						rset.getString("ADMIN_STATUS"),
						rset.getString("STATUS"),
						rset.getInt("BAN_COUNT")
						);
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		
		return loginUser;
	}

	public User idSearch(Connection conn, User user) {
		User idSearch = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("idSearch");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getCitiNo());
			pstmt.setString(3, user.getPhone());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				idSearch = new User(
						rset.getInt("USER_NO"),
						rset.getString("USER_ID"),
						rset.getString("USER_PWD"),
						rset.getString("USER_NAME"),
						rset.getString("CITI_NO"),
						rset.getString("PHONE"),
						rset.getString("NICK_NAME"),
						rset.getString("EMAIL"),
						rset.getString("GENDER"),
						rset.getString("ADMIN_STATUS"),
						rset.getString("STATUS"),
						rset.getInt("BAN_COUNT")
						);
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		
		return idSearch;
	}

	public User pwdSearch(Connection conn, User user) {
		User pwdSearch = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("pwdSearch");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getCitiNo());
			pstmt.setString(4, user.getPhone());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				pwdSearch = new User(
						rset.getInt("USER_NO"),
						rset.getString("USER_ID"),
						rset.getString("USER_PWD"),
						rset.getString("USER_NAME"),
						rset.getString("CITI_NO"),
						rset.getString("PHONE"),
						rset.getString("NICK_NAME"),
						rset.getString("EMAIL"),
						rset.getString("GENDER"),
						rset.getString("ADMIN_STATUS"),
						rset.getString("STATUS"),
						rset.getInt("BAN_COUNT")
						);
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		
		return pwdSearch;
	}
}
