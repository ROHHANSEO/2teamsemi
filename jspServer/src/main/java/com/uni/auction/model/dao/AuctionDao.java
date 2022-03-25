package com.uni.auction.model.dao;

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
import com.uni.usedItemBoard.model.vo.Category;

public class AuctionDao {
	private Properties prop = new Properties();

	public AuctionDao() {
		String fileName = AuctionDao.class.getResource("/sql/auction/auction-query.properties").getPath();
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
	
	public ArrayList<Category> categoryList(Connection conn) {//카테고리 리스트 값 받아오는 것
		ArrayList<Category> list = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("categoryList");
		//SELECT CATEGORYCODE, CATEGORYNAME FROM CATEGORY 
		//WHERE CATEGORY_NO=2 AND CAT_LEVEL = 1;
		try {
			
			pstmt= conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();


			list = new ArrayList<>();
		
			while(rset.next()) {
				Category c =new Category();
				c.setCode(rset.getString("CATEGORYCODE"));
				c.setName(rset.getString("CATEGORYNAME"));
				
				list.add(c);
				System.out.println(c + "dao 카테고리 받아오자 ");

			}
			
			System.out.println(list +"   auctiondao에서  받아온 list 값");
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
