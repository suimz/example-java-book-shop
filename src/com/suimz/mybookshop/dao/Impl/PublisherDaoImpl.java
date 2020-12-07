package com.suimz.mybookshop.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.suimz.mybookshop.dao.IPublisherDao;
import com.suimz.mybookshop.db.Database;
import com.suimz.mybookshop.entity.Publishers;

public class PublisherDaoImpl implements IPublisherDao {

	/**
	 * 获取所有出版社
	 */
	@Override
	public List<Publishers> getPublisher() {
		//创建集合
		List<Publishers> plist = new ArrayList<Publishers>();
		//得到连接
		Connection con=Database.getConnection();
		String sql="select * from publishers  order by Id desc";
		try {
			//sql语句
			PreparedStatement stmt=con.prepareStatement(sql);
            //ִ执行sql
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				Publishers p = new Publishers();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				plist.add(p);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeConnection(con);
		}
		return plist;
	}

	/**
	 * 添加出版社
	 */
	@Override
	public boolean addPublisher(Publishers p) {
		Connection con = Database.getConnection();
		String sql = "insert into publishers(Name) value(?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			//设置参数
			stmt.setString(1, p.getName());
			//ִ执行
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
	 * 更新出版社
	 * @param p
	 * @return
	 */
	@Override
	public boolean upPublisher(Publishers p) {
		Connection con = Database.getConnection();
		String sql = "update publishers set Name=? where Id=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			//设置参数
			stmt.setString(1, p.getName());
			stmt.setInt(2, p.getId());
			//ִ执行
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
