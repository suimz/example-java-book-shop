package com.suimz.mybookshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suimz.mybookshop.dao.Impl.CategoriesDaoImpl;
import com.suimz.mybookshop.entity.Categories;
import com.suimz.mybookshop.entity.Users;
import com.suimz.mybookshop.utils.StringUtil;

/**
 * Category操作servlet类
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CategoryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//String opt = request.getParameter("opt");
		String role = request.getParameter("role");
		//判断是否为管理员操作
		if(role!=null && role.equals("3")){
			optByAdmin(request, response);
			return;
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
		//判断是否登录
		Users user = (Users) request.getSession().getAttribute("user");
		if(user==null || user.getUserRoleId()!=3){
			response.sendRedirect("admin/login.jsp");
			return;
		}
		//添加分类
		if(opt.equals("add")){
			addCategoryByAdmin(request, response);
		}
		//编辑分类
		if(opt.equals("edit")){
			editCategoryByAdmin(request, response);
		}
	}
	
	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addCategoryByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取提交的参数
		String name = request.getParameter("name");
		//信息完整性校验
		if(name==null || name.equals("")){
			request.getSession().setAttribute("ADMIN_MSG_CATEGORY", "添加失败,请检查分类是否合法输入");
			response.sendRedirect("admin/categorylist.jsp");
			return;
		}
		//封装对象
		Categories cate = new Categories();
		cate.setName(name);
		//调用dao层方法
		CategoriesDaoImpl dao = new CategoriesDaoImpl();
		if(dao.addCategory(cate)){
			request.getSession().setAttribute("ADMIN_MSG_CATEGORY", "添加成功");
			//初始化分类集合
			initCategory(request, response);
		}else{
			request.getSession().setAttribute("ADMIN_MSG_CATEGORY", "添加失败");
		}
		//页面跳转
		response.sendRedirect("admin/categorylist.jsp");
	}
	/**
	 * 编辑分类
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void editCategoryByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(StringUtil.isNumber(request.getParameter("cid"))==false){
			response.sendRedirect("admin/categorylist.jsp");
			return;
		}
		//提取参数
		int cId = Integer.parseInt(request.getParameter("cid"));
		String name = request.getParameter("name");
		if(name==null || name.equals("")){
			response.sendRedirect("admin/categorylist.jsp");
			return;
		}
		
		//封装对象
		Categories c = new Categories();
		c.setId(cId);
		c.setName(name);
		//调用dao层方法
		CategoriesDaoImpl dao = new CategoriesDaoImpl();
		Boolean b = dao.updateCategory(c);
		if(b){
			initCategory(request, response);
		}
		response.sendRedirect("admin/categorylist.jsp");
	}
	/**
	 * 初始化分类集合
	 * @param request
	 * @param response
	 */
	private void initCategory(HttpServletRequest request, HttpServletResponse response){
		//获取图书分类
		CategoriesDaoImpl dao = new CategoriesDaoImpl();
		List<Categories> clist = dao.getCategories();
		request.getServletContext().setAttribute("clist", clist);
	}
}
