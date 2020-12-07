package com.suimz.mybookshop.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.suimz.mybookshop.dao.IUserDao;
import com.suimz.mybookshop.db.Database;
import com.suimz.mybookshop.entity.Users;

public class UserDaoImpl implements IUserDao {

	//登录验证
	@Override
	public Users doLogin(String name, String pwd) {
		Connection con = Database.getConnection();
		String sql = "select * from users "
				+ "where LoginId=? and LoginPwd=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, DigestUtils.md5Hex(pwd).replace("-", "").toLowerCase());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				Users user = getUserByResultSet(rs);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeConnection(con);
		}
		return null;
	}

	/**
	 * 注册用户
	 */
	@Override
	public boolean addUser(Users u) {
		if(this.isLoginId(u.getLoginId())){
			return false;
		}
		Connection con = Database.getConnection();
		String sql;
		if(u.getAddress()!=null){
			sql = "insert into users(loginId,loginPwd,name,address,phone,mail,UserRoleId,UserStateId) value(?,?,?,?,?,?,1,1)";
		}else{
			sql = "insert into users(loginId,loginPwd,name,address,phone,mail,UserRoleId,UserStateId) value(?,?,?,?,?,?,?,?)";
		}
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, u.getLoginId());
			stmt.setString(2, DigestUtils.md5Hex(u.getLoginPwd()).replace("-", "").toLowerCase());
			stmt.setString(3, u.getName());
			stmt.setString(5, u.getPhone());
			stmt.setString(6, u.getMail());
			if(u.getAddress()!=null){
				stmt.setString(4, u.getAddress());
			}else{
				stmt.setString(4, "空");
				stmt.setInt(7, u.getUserRoleId());
				stmt.setInt(8, u.getUserStateId());
			}
			int rs = stmt.executeUpdate();
			if(rs>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeConnection(con);
		}
		return false;
	}
	
	@Override
	public boolean isLoginId(String loginId) {
		Connection con = Database.getConnection();
		String sql = "select * from users "
				+ "where LoginId=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, loginId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeConnection(con);
		}
		return false;
	}

	/**
	 * 修改用户信息
	 */
	@Override
	public boolean updateUser(Users u) {
		Connection con = Database.getConnection();
		String sql;
		if(u.getLoginId()==null){
			sql = "UPDATE users SET `Name`=?,Mail=?,Phone=?,Address=? WHERE Id=?";
		}else{
			sql = "UPDATE users SET `Name`=?,Mail=?,Phone=?,Address=?,UserRoleId=?,UserStateId=?,LoginId=? WHERE Id=?";
		}
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			//设置参数值
			stmt.setString(1,u.getName());
			stmt.setString(2,u.getMail());
			stmt.setString(3,u.getPhone());
			stmt.setString(4,u.getAddress());
			if(u.getLoginId()==null){
				stmt.setInt(5,u.getId());
			}else{
				stmt.setInt(5,u.getUserRoleId());
				stmt.setInt(6,u.getUserStateId());
				stmt.setString(7,u.getLoginId());
				stmt.setInt(8,u.getId());
			}
			
			int rs = stmt.executeUpdate();
			if(rs>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeConnection(con);
		}
		return false;
	}

	/**
	 * 根据用户Id获取用户信息
	 */
	@Override
	public Users getUserById(int id) {
		Connection con = Database.getConnection();
		String sql = "select * from users "
				+ "where Id=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				Users user = getUserByResultSet(rs);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeConnection(con);
		}
		return null;
	}

	/**
	 * 后台登录
	 */
	@Override
	public Users doLoginByAdmin(String name, String pwd) {
		Connection con = Database.getConnection();
		String sql = "select * from users "
				+ "where LoginId=? and LoginPwd=? and UserRoleId=3";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, DigestUtils.md5Hex(pwd).replace("-", "").toLowerCase());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				Users user = getUserByResultSet(rs);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeConnection(con);
		}
		return null;
	}
	/**
	 * 封装结果集
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Users getUserByResultSet(ResultSet rs) throws SQLException{
		Users user = new Users();
		user.setId(rs.getInt(1));
		user.setLoginId(rs.getString(2));
		user.setLoginPwd(rs.getString(3));
		user.setName(rs.getString(4));
		user.setAddress(rs.getString(5));
		user.setPhone(rs.getString(6));
		user.setMail(rs.getString(7));
		user.setUserRoleId(rs.getInt(8));
		user.setUserStateId(rs.getInt(9));
		return user;
	}

	/**
	 * 获取用户列表
	 */
	@Override
	public List<Users> getUserList() {
		List<Users> ulist=new ArrayList<Users>();
		//得到连接
		Connection con=Database.getConnection();
		String sql="select * from users where UserRoleId!=3  order by Id desc";
		try {
			//创建sql语句
			PreparedStatement stmt=con.prepareStatement(sql);
            //ִ执行sql语句
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				Users u = getUserByResultSet(rs);
				ulist.add(u);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeConnection(con);
		}
		return ulist;
	}

	/**
	 * 根据用户Id删除用户
	 */
	@Override
	public boolean delUserByUserId(int userId) {
		Connection con=Database.getConnection();
		String sql = "DELETE FROM users WHERE Id="+userId;
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			int rs = stmt.executeUpdate();
			if(rs>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeConnection(con);
		}
		return false;
	}

	/**
	 * 更新用户状态
	 */
	@Override
	public boolean upUserSate(int userId,int state) {
		Connection con = Database.getConnection();
		String sql = "UPDATE users SET UserStateId=? WHERE Id=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			//设置参数值
			stmt.setInt(1,state);
			stmt.setInt(2,userId);
			int rs = stmt.executeUpdate();
			if(rs>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeConnection(con);
		}
		return false;
	}
}
