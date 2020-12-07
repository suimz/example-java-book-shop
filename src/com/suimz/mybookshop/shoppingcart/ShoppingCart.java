package com.suimz.mybookshop.shoppingcart;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.suimz.mybookshop.entity.Books;
/**
 * 
 * @author Administrator
 */
public class ShoppingCart implements Serializable{
	private static final long serialVersionUID = -6120449923757970784L;
	
	private HashMap<String, ShoppingItem>items;
	private int itemAmount;
	private double total;

	/**
	 * 
	 */
	public ShoppingCart(){
		super();
		items = new HashMap<String, ShoppingItem>();
		itemAmount=0;
	}
	
	/**
	 * 添加商品到购物车
	 * @param isbn
	 * @param book
	 * @param num
	 */
	public synchronized  void add(Books book,int num){
		if(items.containsKey(book.getIsbn())){
			//获取添加之前的图书数量
//			ShoppingItem b =  items.get(book.getIsbn());
//			int amount = b.getAmount();
//			num = num + amount;
//			
			items.remove(book.getIsbn());
			ShoppingItem newItem = new ShoppingItem(book,num);
			items.put(book.getIsbn(), newItem);
			
		}else{
			ShoppingItem newItem = new ShoppingItem(book,num);
			items.put(book.getIsbn(), newItem);
			itemAmount++;
		}
	}
	
	/**
	 * 删除购物车中的商品
	 * @param isbn
	 */
	public synchronized void remove(String isbn){
		if(items.containsKey(isbn)){
			items.remove(isbn);
		}
	}
	
	/**
	 * 获取购物车中的商品列表
	 * @return
	 */
	public synchronized Collection<ShoppingItem> getItems(){
		return items.values();
	}
	
	/**
	 * 根据isbn获取购物车中对应的ShoppingItem
	 * @param isbn
	 * @return
	 */
	public synchronized ShoppingItem getItem(String isbn){
		return items.get(isbn);
	}
	
	protected void finalize(){
		items.clear();
	}
	
	/**
	 * 获取购物车中商品数量
	 * @return 
	 */
	public synchronized int getItemAmount(){
		return itemAmount;
	}
	/**
	 * 计算商品总价
	 * @return  商品总价
	 */
	public synchronized double getTotalReal(){
		double total = 0.0;
		Iterator<ShoppingItem> it = getItems().iterator();
		while(it.hasNext()){
			ShoppingItem si = it.next();
			Books book= si.getItem();
			total+=book.getUnitPrice()*si.getAmount();
		}
		this.total = total;
		return total;
	}
	
	/**
	 * 清空购物车
	 */
	public void clear(){
		items.clear();
		itemAmount=0;
	}

	public double getTotalPrice() {
		return total;
	}

	public void setItemAmount() {
		this.itemAmount--;
	}
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	
}
