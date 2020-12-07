package com.suimz.mybookshop.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.suimz.mybookshop.dao.IOrderDao;
import com.suimz.mybookshop.db.Database;
import com.suimz.mybookshop.entity.OrderBook;
import com.suimz.mybookshop.entity.OrderBookCustom;
import com.suimz.mybookshop.entity.Orders;
/**
 * 订单操作接口实现类
 */
public class OrderDaoImpl implements IOrderDao {
	/**
	 * 添加订单
	 * @param order
	 * @return 订单Id
	 */
	@Override
	public int addOrder(Orders orders) {
		//得到连接
		Connection con = Database.getConnection();
		//创建sql语句(CURRENT_TIMESTAMP()为获取当前时间)
		String sql = "insert into orders(orderDate,userId,totalPrice)"
				+ " value(CURRENT_TIMESTAMP(),?,?)";
		PreparedStatement stmt = null;
		try {
			//封装sql语句
			stmt = con.prepareStatement(sql);
			//设置参数
			stmt.setInt(1, orders.getUserId());
			stmt.setDouble(2, orders.getTotalPrice());
			//执行sql语句(返回影响行数)
			int row = stmt.executeUpdate();
			if(row>0){
				//如果影响行数大于0,说明插入成功,执行下面的sql语句,获取插入的自增长的Id
				ResultSet rs = stmt.executeQuery("select LAST_INSERT_ID()");
				if (rs.next())
					//返回自增长的Id
					return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeConnection(con);
		}
		//返回-1表示插入失败
		return -1;
	}

	/**
	 * 添加详细订单
	 * @param orderBook
	 * @return 
	 */
	@Override
	public boolean addOrderBook(OrderBook orderBook) {
		//得到连接
		Connection con = Database.getConnection();
		//创建sql语句
		String sql = "insert into orderbook(orderId,bookId,quantity,unitPrice)"
				+ " value(?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			//封装sql语句
			stmt = con.prepareStatement(sql);
			//设置参数
			stmt.setInt(1, orderBook.getOrderId());
			stmt.setInt(2, orderBook.getBookId());
			stmt.setInt(3, orderBook.getQuantity());
			stmt.setDouble(4, orderBook.getUnitPrice());
			//执行sql语句
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
	 * 根据当前登录用户Id获取对应的订单
	 * @param userId
	 * @return
	 */
	@Override
	public List<OrderBookCustom> getOrder(int userId) {
		List<OrderBookCustom> olist=new ArrayList<OrderBookCustom>();
		//得到连接
		Connection con=Database.getConnection();
		//sql语句
		String sql="SELECT orderbook.*,orders.OrderDate,books.Title FROM orders,orderbook,books WHERE orderbook.OrderID=orders.Id AND orderbook.BookID=books.Id AND orders.UserId="+userId +" ORDER BY Id DESC";
		try {
			//封装sql语句
			PreparedStatement stmt=con.prepareStatement(sql);
            //ִ执行sql
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				//提取查询的记录进行封装,并添加到集合中
				OrderBookCustom obc = new OrderBookCustom();
				obc.setId(rs.getInt(1));
				obc.setOrderId(rs.getInt(2));
				obc.setBookId(rs.getInt(3));
				obc.setQuantity(rs.getInt(4));
				obc.setUnitPrice(rs.getDouble(5));
				obc.setOrderDate(rs.getString(6));
				obc.setTitle(rs.getString(7));
				olist.add(obc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeConnection(con);
		}
		return olist;
	}

}
