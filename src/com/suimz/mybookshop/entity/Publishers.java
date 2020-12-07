package com.suimz.mybookshop.entity;

import java.io.Serializable;

public class Publishers implements Serializable {
	private static final long serialVersionUID = -233765057547124028L;
	
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
