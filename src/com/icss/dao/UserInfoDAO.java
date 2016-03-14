package com.icss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.icss.commons.DBUtil;
import com.icss.vo.UserInfoVO;

/**
 * 
 * @author J.L.Zhou
 *
 */
public class UserInfoDAO implements DAOSupper<UserInfoVO> {

	@Override
	public void add(UserInfoVO t) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "insert into user_info(user_name,user_pwd,user_real_name,user_lock) values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getUserName());
			pstmt.setString(2, t.getUserPwd());
			pstmt.setString(3, t.getUserRealName());
//			pstmt.setTimestamp(1,new java.sql.Timestamp(new java.util.Date().getTime()));
			pstmt.setBoolean(4, t.getUserLock());
			pstmt.executeUpdate();
		}catch (Exception e) {
			throw new RuntimeException("插入用户错误！",e);
		}finally{
			DBUtil.close(conn, pstmt);
		}
		
	}

	@Override
	public void modify(UserInfoVO t) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "update user_info set user_name=?,user_pwd=?,user_real_name=?,user_lock=? where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getUserName());
			pstmt.setString(2, t.getUserPwd());
			pstmt.setString(3, t.getUserRealName());
			pstmt.setBoolean(4, t.getUserLock());
			pstmt.setLong(5, t.getUserId());
			pstmt.executeUpdate();
		}catch (Exception e) {
			throw new RuntimeException("修改用户错误！",e);
		}finally{
			DBUtil.close(conn, pstmt);
		}
	}

	@Override
	public void delete(UserInfoVO t) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "delete from user_info where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, t.getUserId());
			pstmt.executeUpdate();
		}catch (Exception e) {
			throw new RuntimeException("插入用户错误！",e);
		}finally{
			DBUtil.close(conn, pstmt);
		}
		
	}

	@Override
	public UserInfoVO findById(UserInfoVO t) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from user_info where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, t.getUserId());
			rs = pstmt.executeQuery();
			if(rs.next()){
				return load(rs);
			}else{
				throw new RuntimeException("用户不存在");
			}
			
		}catch(RuntimeException e1){
			throw e1;
		}catch (Exception e) {
			throw new RuntimeException("获取用户信息错误",e);
			
		}finally{
			DBUtil.close(conn, pstmt, rs);
		}
	}
	
	public UserInfoVO findByUserName(String userName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from user_info where user_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return load(rs);
			}else{
				throw new RuntimeException("用户不存在");
			}
			
		}catch(RuntimeException e1){
			throw e1;
		}catch (Exception e) {
			throw new RuntimeException("获取用户信息错误",e);
		}finally{
			DBUtil.close(conn, pstmt, rs);
		}
	}

	@Override
	public List<UserInfoVO> findAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			List<UserInfoVO> list = new ArrayList<UserInfoVO>();
			conn = DBUtil.getConnection();
			String sql = "select * from user_info";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				list.add(load(rs));
			}
			
			return list;
		}catch (Exception e) {
			throw new RuntimeException("获取用户信息列表错误",e);
		}finally{
			DBUtil.close(conn, pstmt, rs);
		}
	}
	
	
	
	
	public List<UserInfoVO> findAll(int page,int rowMax) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			List<UserInfoVO> list = new ArrayList<UserInfoVO>();
			conn = DBUtil.getConnection();
			String sql = "select * from user_info limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*rowMax);
			pstmt.setInt(2, rowMax);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(load(rs));
			}
			
			return list;
		}catch (Exception e) {
			throw new RuntimeException("获取用户信息列表错误",e);
		}finally{
			DBUtil.close(conn, pstmt, rs);
		}
	}
	
	public long countAll(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select count(*) from user_info";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getLong(1);
			}else{
				return 0l;
			}
			
		}catch (Exception e) {
			throw new RuntimeException("获取用户信息列表错误",e);
		}finally{
			DBUtil.close(conn, pstmt, rs);
		}
	}
	private UserInfoVO load(ResultSet rs) throws SQLException{
		UserInfoVO vo = new UserInfoVO();
		vo.setUserId(rs.getLong("user_id"));
		vo.setUserName(rs.getString("user_name"));
		vo.setUserPwd(rs.getString("user_pwd"));
		vo.setUserRealName(rs.getString("user_real_name"));
		vo.setUserLock(rs.getBoolean("user_lock"));
//		java.util.Date d = new java.util.Date(rs.getTimestamp("adsfsad").getTime());
		return vo;
	}

}
