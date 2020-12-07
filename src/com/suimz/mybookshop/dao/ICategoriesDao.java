package com.suimz.mybookshop.dao;

import java.util.List;

import com.suimz.mybookshop.entity.Categories;

public interface ICategoriesDao {
	//获取分类
	public List<Categories> getCategories();
	//添加分类
	public boolean addCategory(Categories c);
	//更新分类
	public boolean updateCategory(Categories c);
}
