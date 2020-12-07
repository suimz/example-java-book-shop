package com.suimz.mybookshop.dao;

import java.util.List;

import com.suimz.mybookshop.entity.OrderBook;
import com.suimz.mybookshop.entity.OrderBookCustom;
import com.suimz.mybookshop.entity.Orders;
/**
 * 订单操接口类
 * @author suimz
 *
 */
public interface IOrderDao {
	/**
	 * 添加订单
	 * @param order
	 * @return 订单Id
	 */
	public int addOrder(Orders order);
	/**
	 * 添加详细订单
	 * @param orderBook
	 * @return boolean
	 */
	public boolean addOrderBook(OrderBook orderBook);
	
	/**
	 * 根据当前登录用户Id获取对应的订单
	 * @param userId
	 * @return
	 */
	public List<OrderBookCustom> getOrder(int userId);
	
}
