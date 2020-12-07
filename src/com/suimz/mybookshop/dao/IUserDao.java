package com.suimz.mybookshop.dao;

import java.util.List;

import com.suimz.mybookshop.entity.Users;

public interface IUserDao {
	//用户登录
	public Users doLogin(String name,String pwd);
	//注册用户
	public boolean addUser(Users u);
	//用户更新
	public boolean updateUser(Users u);
	//判断ID是否存在
	public boolean isLoginId(String loginId);
	//根据用户Id获取用户信息
	public Users getUserById(int id);
	//管理员登录
	public Users doLoginByAdmin(String name,String pwd);
	//获取用户列表
	public List<Users> getUserList();
	//删除用户
	public boolean delUserByUserId(int userId);
	//更新用户状态
	public boolean upUserSate(int userId,int state);
}
