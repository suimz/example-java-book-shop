package com.suimz.mybookshop.entity;

import java.io.Serializable;

/**
 * 订单表实体类
 * @author suimz
 *
 */
public class Orders implements Serializable{
	private int id;
	private String orderDate;
	private int userId;
	private double totalPrice;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
