package com.suimz.mybookshop.shoppingcart;

import java.io.Serializable;

import com.suimz.mybookshop.entity.Books;

public class ShoppingItem implements Serializable{
	private static final long serialVersionUID = 5944270461320472455L;
	
	private  Books item;
	private int amount=0;
	
	public ShoppingItem(Books newBook,int num){
		super();
		if(num>0){
			item = newBook;
			amount = num;
		}
	}
	
	public void inAmount(){
		amount++;
	}
	public void inAmount(int num){
		amount+=num;
	}
	public void deAmount(){
		amount--;
	}
	public Books getItem(){
		return item;
	}
	public int getAmount(){
		return amount;
	}
}
