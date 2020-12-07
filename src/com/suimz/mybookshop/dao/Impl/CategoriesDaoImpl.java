package com.suimz.mybookshop.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.suimz.mybookshop.dao.ICategoriesDao;
import com.suimz.mybookshop.db.Database;
import com.suimz.mybookshop.entity.Categories;

public class CategoriesDaoImpl implements ICategoriesDao {
	//获取出版社
	@Override
	public List<Categories> getCategories() {
		//创建集合
		List<Categories> clist = new ArrayList<Categories>();
		//得到连接
		Connection con=Database.getConnection();
		String sql="select * from categories order by Id desc";
		try {
			//sql语句
			PreparedStatement stmt=con.prepareStatement(sql);
            //ִ执行sql
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				Categories c = new Categories();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				clist.add(c);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeConnection(con);
		}
		return clist;
	}

	
	//添加分类
	@Override
	public boolean addCategory(Categories c) {
		Connection con = Database.getConnection();
		String sql = "insert into categories(Name) value(?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			//设置参数
			stmt.setString(1, c.getName());
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

	
	//更新分类
	@Override
	public boolean updateCategory(Categories c) {
		Connection con = Database.getConnection();
		String sql = "update categories set Name=? where Id=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			//设置参数
			stmt.setString(1, c.getName());
			stmt.setInt(2, c.getId());
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
