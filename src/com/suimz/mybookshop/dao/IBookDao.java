package com.suimz.mybookshop.dao;

import java.util.List;

import com.suimz.mybookshop.entity.Books;

public interface IBookDao {
	//根据书名获取图书
	public List<Books> getBooksByTitle(String title);
	//根据分类Id获取图书
	public List<Books> getBooksByCategoryId(int categoryId);
	//根据Isbn获取图书
	public Books getBooksByIsbn(String isbn);
	//按照clicks降序排列获取所有图书
	public List<Books> getHotBooksByClicks();
	//根据图书Id获取图书
	public Books getBooksById(int id);
	//根据出版社获取图书
	public List<Books> getBooksByPublishName(String name);
	//根据图书分类获取图书
	public List<Books> getBooksByCategoryName(String name);
	//获取所有图书
	public List<Books> getBookList();
	//删除图书
	public boolean delBookBookId(int bookId);
	//修改图书信息
	public boolean upBook(Books book);
	//添加图书
	public boolean addBook(Books book);
}

