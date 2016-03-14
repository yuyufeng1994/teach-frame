package com.icss.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库基本工具类
 * @author J.L.Zhou
 *
 */
public class DBUtil {
	
	private static String url = null;
	private static String userName = null;
	private static String userPwd = null;
	
	static{
		try {
			
			java.util.Properties config = new java.util.Properties();
			config.load(DBUtil.class.getClassLoader().getResourceAsStream("conn.properties"));
			
			url = config.getProperty("url");
			userName = config.getProperty("name");
			userPwd = config.getProperty("pwd");
			Class.forName(config.getProperty("driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,userName,userPwd);
	}
	
	public static void close(Connection conn,PreparedStatement pstmt,ResultSet rs){
		try {
			rs.close();
		} catch (Exception e2) {
		}

		close(conn, pstmt);
	}
	
	public static void close(Connection conn,PreparedStatement pstmt){
		try {
			pstmt.close();
		} catch (Exception e1) {}
		
		try {
			conn.close();
		} catch (Exception e) {
		}
	}
	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());
	}
	
}
