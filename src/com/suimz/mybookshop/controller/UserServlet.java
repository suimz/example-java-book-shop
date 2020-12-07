package com.suimz.mybookshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suimz.mybookshop.dao.Impl.UserDaoImpl;
import com.suimz.mybookshop.entity.PageBean;
import com.suimz.mybookshop.entity.Users;
import com.suimz.mybookshop.shoppingcart.ShoppingCart;
import com.suimz.mybookshop.utils.StringUtil;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opt = request.getParameter("opt");
		String role = request.getParameter("role");
		//判断是否为管理员操作
		if(role!=null && role.equals("3")){
			optByAdmin(request, response);
			return;
		}
		//登录
		if(opt.equals("login")){
			login(request, response);
			return;
		}
		//注册
		else if(opt.equals("register")){
			register(request, response);
			return;
		}
		//是否登录验证
		if(request.getSession().getAttribute("user")==null){
			response.sendRedirect("login.jsp");
			return;
		}
		//注销
		if(opt.equals("logout")){
			logout(request, response);
		}
		//修改信息
		else if(opt.equals("updateUser")){
			updateUser(request, response);
		}
		//进入个人中心
		else if(opt.equals("show")){
			show(request, response);
		}
	}
	
	/**
	 * 管理员操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void optByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opt = request.getParameter("opt");
		//登录
		if(opt.equals("login")){
			loginByAdmin(request, response);
			return;
		}
		//判断是否登录
		Users user = (Users) request.getSession().getAttribute("user");
		if(user==null || user.getUserRoleId()!=3){
			response.sendRedirect("admin/login.jsp");
			return;
		}
		//获取用户列表
		if(opt.equals("userlist")){
			userListByAdmin(request, response);
		}
		//退出
		else if(opt.equals("logout")){
			logoutByAdmin(request, response);
		}
		//删除用户
		else if(opt.equals("del")){
			delUserByAdmin(request, response);
		}
		//锁定用户
		else if(opt.equals("lock")){
			lockUserByAdmin(request, response);
		}
		//解锁用户
		else if(opt.equals("unlock")){
			unLockUserByAdmin(request, response);
		}
		//获取用户信息
		else if(opt.equals("show")){
			showUserByAdmin(request, response);
		}
		//编辑用户
		else if(opt.equals("edit")){
			editUserByAdmin(request, response);
		}
		//注册
		else if(opt.equals("add")){
			addUserByAdmin(request, response);
			return;
		}
	}
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		if(cart==null){
			cart = new ShoppingCart();
		}
		//获取参数
		String name = request.getParameter("loginId");
		String pwd = request.getParameter("loginPwd");
		//调用登录验证
		UserDaoImpl dao = new UserDaoImpl();
		Users user = dao.doLogin(name, pwd);
		 if(user!=null){
			 //将user对象存入session中,便于前台使用
			 request.getSession().setAttribute("user", user);
			 response.sendRedirect("index.jsp");
			 return;
		 }else{
			 response.sendRedirect("login.jsp");
			 return;
		 }	
		
	}
	/**
	 * 管理员登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void loginByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		if(cart==null){
			cart = new ShoppingCart();
		}
		//获取参数
		String name = request.getParameter("loginId");
		String pwd = request.getParameter("loginPwd");
		//调用登录验证
		UserDaoImpl dao = new UserDaoImpl();
		Users user = dao.doLoginByAdmin(name, pwd);
		 if(user!=null){
			 //将user对象存入session中,便于前台使用
			 request.getSession().setAttribute("user", user);
			 response.sendRedirect("admin/index.jsp");
			 return;
		 }else{
			 response.sendRedirect("admin/login.jsp");
			 return;
		 }	
		
	}
	/**
	 * 用户注销退出
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从session中获取用户信息
		Users user = (Users) request.getSession().getAttribute("user");
		if(user!=null){
			request.getSession().removeAttribute("user");
		}
		response.sendRedirect("login.jsp");
	}
	
	protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("user/user.jsp");
	}

	/**
	 * 管理员注销退出
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void logoutByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从session中获取用户信息
		Users user = (Users) request.getSession().getAttribute("user");
		if(user!=null){
			request.getSession().removeAttribute("user");
		}
		response.sendRedirect("admin/login.jsp");
	}
	/**
	 * 修改用户信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取提交的参数
		String id = (String) request.getParameter("id");
		if(id==null || "".equals(id)){
			response.sendRedirect("login.jsp");
			return;
		}
		String name = (String) request.getParameter("name");
		String mail = (String) request.getParameter("mail");
		String phone = (String) request.getParameter("phone");
		String address = (String) request.getParameter("address");
		//封装成Users对象
		Users user = new Users();
		user.setId(Integer.parseInt(id));
		user.setName(name);
		user.setMail(mail);
		user.setPhone(phone);
		user.setAddress(address);
		//调用dao层方法
		UserDaoImpl dao = new UserDaoImpl();
		Boolean b = dao.updateUser(user);
		//重置session的user信息
		initUser(request, response, Integer.parseInt(id));
		
		//设置修改信息结果字符串
		if(b)
			request.getSession().setAttribute("MSG_USER_UPDATE_RESULT", "个人资料修改成功");
		else
			request.getSession().setAttribute("MSG_USER_UPDATE_RESULT", "个人资料修改失败");
			
		response.sendRedirect("user/userinfo.jsp");	
	}

	/**
	 * 注册用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取提交的参数
		String loginId = request.getParameter("loginId");
		String loginPwd = request.getParameter("loginPwd");
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		//信息完整性校验
		if(loginId==null 
				|| loginId.equals("") 
				|| loginPwd==null 
				|| loginPwd.equals("") 
				|| mail==null 
				|| mail.equals("")
				|| address==null
				|| address.equals("")){
			request.getSession().setAttribute("MSG_USER_REGISTER_RESULT", "注册失败:请检查注册信息是否正确");
			response.sendRedirect("register.jsp");
			return;
		}
		
		//封装用户信息成Users对象
		Users user = new Users(loginId,loginPwd,name,address,phone,mail,1,1);
		//调用dao层方法
		UserDaoImpl dao = new UserDaoImpl();
		//页面跳转
		if(dao.addUser(user)){
			response.sendRedirect("registerok.jsp");
		}else{
			request.getSession().setAttribute("MSG_USER_REGISTER_RESULT", "注册失败！");
			response.sendRedirect("register.jsp");
		}
		
	}
	
	/**
	 * 初始化用户信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void initUser(HttpServletRequest request, HttpServletResponse response,int userId) throws ServletException, IOException {
		UserDaoImpl dao = new UserDaoImpl();
		Users user = dao.getUserById(userId);
		//将user设置到session中
		request.getSession().setAttribute("user", user);
	}

	/**
	 * 获取用户列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void userListByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDaoImpl dao = new UserDaoImpl();
		List<Users> ulist = dao.getUserList();
		//获取分页
		Integer page=1;
		String strPage = request.getParameter("page");
		if(StringUtil.isNumber(strPage)==false){
		}else{
			page =Integer.parseInt(strPage);
		}
		//封装成分页对象
		PageBean<Users> Pager = new PageBean<Users>(page,10,ulist);
		request.getSession().setAttribute("ulist", Pager);
		response.sendRedirect("admin/userlist.jsp");
	}
	/**
	 * 删除用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delUserByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(StringUtil.isNumber(request.getParameter("uid"))==false){
			response.sendRedirect("admin/userlist.jsp");
			return;
		}
		int userId = Integer.parseInt(request.getParameter("uid"));
		UserDaoImpl dao = new UserDaoImpl();
		boolean b = dao.delUserByUserId(userId);
		//存入操作信息
		if(b==true){
			request.getSession().setAttribute("ADMIN_MSG_USER","删除成功");
		}else{
			request.getSession().setAttribute("ADMIN_MSG_USER","删除失败");
		}
		userListByAdmin(request, response);
	}
	/**
	 * 锁定用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void lockUserByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(StringUtil.isNumber(request.getParameter("uid"))==false){
			response.sendRedirect("admin/userlist.jsp");
			return;
		}
		int userId = Integer.parseInt(request.getParameter("uid"));
		UserDaoImpl dao = new UserDaoImpl();
		dao.upUserSate(userId, 2);
		userListByAdmin(request, response);
	}
	/**
	 * 解锁用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void unLockUserByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(StringUtil.isNumber(request.getParameter("uid"))==false){
			response.sendRedirect("admin/userlist.jsp");
			return;
		}
		int userId = Integer.parseInt(request.getParameter("uid"));
		UserDaoImpl dao = new UserDaoImpl();
		dao.upUserSate(userId, 1);
		userListByAdmin(request, response);
	}
	
	/**
	 * 进入编辑用户页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showUserByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(StringUtil.isNumber(request.getParameter("uid"))==false){
			response.sendRedirect("admin/userlist.jsp");
			return;
		}
		int userId = Integer.parseInt(request.getParameter("uid"));
		UserDaoImpl dao = new UserDaoImpl();
		Users u = dao.getUserById(userId);
		if(u!=null){
			//存入需要编辑用户
			request.getSession().setAttribute("editUser",u);
			response.sendRedirect("admin/useredit.jsp");
		}else{
			response.sendRedirect("admin/userlist.jsp");
		}
		
	}
	/**
	 * 编辑用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void editUserByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(StringUtil.isNumber(request.getParameter("uid"))==false){
			response.sendRedirect("admin/userlist.jsp");
			return;
		}
		int userId = Integer.parseInt(request.getParameter("uid"));
		//提取参数
		String loginId = (String) request.getParameter("loginId");
		String name = (String) request.getParameter("name");
		String mail = (String) request.getParameter("mail");
		String phone = (String) request.getParameter("phone");
		String address = (String) request.getParameter("address");
		int userRoleId =  Integer.parseInt(request.getParameter("userRoleId"));
		int userStateId =  Integer.parseInt(request.getParameter("userStateId"));
		//封装成Users对象
		Users user = new Users();
		user.setId(userId);
		user.setLoginId(loginId);
		user.setName(name);
		user.setMail(mail);
		user.setPhone(phone);
		user.setAddress(address);
		user.setUserRoleId(userRoleId);
		user.setUserStateId(userStateId);
		//调用dao层方法
		UserDaoImpl dao = new UserDaoImpl();
		Boolean b = dao.updateUser(user);
		//设置修改信息结果字符串
		if(b)
			request.getSession().setAttribute("ADMIN_MSG_USER", "信息编辑成功,UID:"+userId);
		else
			request.getSession().setAttribute("ADMIN_MSG_USER", "信息编辑失败,UID:"+userId);
			
		userListByAdmin(request, response);
	}
	/**
	 * 添加用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addUserByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取提交的参数
		String loginId = request.getParameter("loginId");
		String loginPwd = request.getParameter("loginPwd");
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String phone = request.getParameter("phone");
		int userRoleId = Integer.parseInt(request.getParameter("userRoleId"));
		int userStateId = Integer.parseInt(request.getParameter("userStateId"));
		
		//信息完整性校验
		if(loginId==null 
				|| loginId.equals("") 
				|| loginPwd==null 
				|| loginPwd.equals("") 
				|| mail==null 
				|| mail.equals("")){
			request.getSession().setAttribute("ADMIN_MSG_USER", "添加失败,请检查注册信息是否正确");
			response.sendRedirect("admin/useradd.jsp");
			return;
		}
		
		//封装用户信息成Users对象
		Users user = new Users(loginId,loginPwd,name,null,phone,mail,userRoleId,userStateId);
		//调用dao层方法
		UserDaoImpl dao = new UserDaoImpl();
		//页面跳转
		if(dao.addUser(user)){
			request.getSession().setAttribute("ADMIN_MSG_USER", "添加成功,用户名:"+loginId);
			userListByAdmin(request, response);
		}else{
			request.getSession().setAttribute("ADMIN_MSG_USER", "添加失败！");
			response.sendRedirect("admin/useradd.jsp");
		}
		
	}
}
