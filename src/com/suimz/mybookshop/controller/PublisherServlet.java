package com.suimz.mybookshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suimz.mybookshop.dao.Impl.PublisherDaoImpl;
import com.suimz.mybookshop.entity.Publishers;
import com.suimz.mybookshop.entity.Users;
import com.suimz.mybookshop.utils.StringUtil;

/**
 * Category操作servlet类
 */
@WebServlet("/PublisherServlet")
public class PublisherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublisherServlet() {
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
			addPublisherByAdmin(request, response);
		}
		//编辑分类
		if(opt.equals("edit")){
			editPublisherByAdmin(request, response);
		}
	}
	
	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addPublisherByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取提交的参数
		String name = request.getParameter("name");
		//信息完整性校验
		if(name==null || name.equals("")){
			request.getSession().setAttribute("ADMIN_MSG_PUBLISHER", "添加失败,请检查分类是否合法输入");
			response.sendRedirect("admin/publisherlist.jsp");
			return;
		}
		//封装对象
		Publishers p = new Publishers();
		p.setName(name);
		//调用dao层方法
		PublisherDaoImpl dao = new PublisherDaoImpl();
		if(dao.addPublisher(p)){
			request.getSession().setAttribute("ADMIN_MSG_PUBLISHER", "添加成功");
			//初始化分类集合
			initPublisher(request, response);
		}else{
			request.getSession().setAttribute("ADMIN_MSG_PUBLISHER", "添加失败");
		}
		//页面跳转
		response.sendRedirect("admin/publisherlist.jsp");
	}
	/**
	 * 编辑分类
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void editPublisherByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(StringUtil.isNumber(request.getParameter("pid"))==false){
			response.sendRedirect("admin/publisherlist.jsp");
			return;
		}
		//提取参数
		int cId = Integer.parseInt(request.getParameter("pid"));
		String name = request.getParameter("name");
		if(name==null || name.equals("")){
			response.sendRedirect("admin/publisherlist.jsp");
			return;
		}
		//封装对象
		Publishers p = new Publishers();
		p.setId(cId);
		p.setName(name);
		//调用dao层方法
		PublisherDaoImpl dao = new PublisherDaoImpl();
		Boolean b = dao.upPublisher(p);
		if(b){
			initPublisher(request, response);
		}
		response.sendRedirect("admin/publisherlist.jsp");
	}
	/**
	 * 初始化分类集合
	 * @param request
	 * @param response
	 */
	private void initPublisher(HttpServletRequest request, HttpServletResponse response){
		//获取图书分类
		PublisherDaoImpl dao = new PublisherDaoImpl();
		List<Publishers> plist = dao.getPublisher();
		request.getServletContext().setAttribute("plist", plist);
	}
}
