package com.suimz.mybookshop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suimz.mybookshop.dao.Impl.BookDaoImpl;
import com.suimz.mybookshop.entity.Books;
import com.suimz.mybookshop.entity.PageBean;
import com.suimz.mybookshop.entity.Users;
import com.suimz.mybookshop.utils.StringUtil;

/**
 * Book操作servlet类
 *
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookServlet() {
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
		//操作判断
		if(opt.equals("byTitle")){
			queryByTitle(request, response);
		}else if(opt.equals("byIsbn")){
			queryByIsbn(request, response);
		}else if(opt.equals("byCategory")){
			queryByCategory(request, response);
		}else if(opt.equals("buyBook")){
			buyBook(request, response);
		}else if(opt.equals("search")){
			search(request, response);
		}else if(opt.equals("byPublish")){
			queryByPublish(request, response);
		}else if(opt.equals("byCategoryName")){
			queryByCategoryName(request, response);
		}
		
		else{
			response.sendRedirect("index.jsp");
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
		if(opt.equals("booklist")){
			bookListByAdmin(request, response);
		}
		else if(opt.equals("del")){
			delBookByAdmin(request, response);
		}
		else if(opt.equals("show")){
			showBookByAdmin(request, response);
		}
		else if(opt.equals("edit")){
			editBookByAdmin(request, response);
		}
		else if(opt.equals("add")){
			addBookByAdmin(request, response);
		}

	}
	/**
	 * 根据书名获取图书,并且跳转到图书显示页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryByTitle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bname = request.getParameter("value");
		BookDaoImpl dao = new BookDaoImpl();
		List<Books> searchBList = new ArrayList<Books>();
		searchBList = dao.getBooksByTitle(bname);
		//封装成分页对象
		PageBean<Books> Pager = new PageBean<Books>(1,5,searchBList);
		request.getSession().setAttribute("searchBooks", Pager);
		response.sendRedirect("search.jsp");
	}
	/**
	 * 根据出版社名称获取图书,并且跳转到图书显示页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryByPublish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bname = request.getParameter("value");
		BookDaoImpl dao = new BookDaoImpl();
		List<Books> searchBList = new ArrayList<Books>();
		searchBList = dao.getBooksByPublishName(bname);
		//封装成分页对象
		PageBean<Books> Pager = new PageBean<Books>(1,5,searchBList);
		request.getSession().setAttribute("searchBooks", Pager);
		response.sendRedirect("search.jsp");
	}
	/**
	 * 根据分类名称获取图书,并且跳转到图书显示页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryByCategoryName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bname = request.getParameter("value");
		BookDaoImpl dao = new BookDaoImpl();
		List<Books> searchBList = new ArrayList<Books>();
		searchBList = dao.getBooksByCategoryName(bname);
		//封装成分页对象
		PageBean<Books> Pager = new PageBean<Books>(1,5,searchBList);
		request.getSession().setAttribute("searchBooks", Pager);
		response.sendRedirect("search.jsp");
	}
	/**
	 * 根据图书Isbn获取图书,并且跳转到图书显示页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryByIsbn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bname = request.getParameter("value");
		BookDaoImpl dao = new BookDaoImpl();
		List<Books> searchBList = new ArrayList<>();
		Books book = dao.getBooksByIsbn(bname);
		searchBList.add(book);
		//封装成分页对象
		PageBean<Books> Pager = new PageBean<Books>(1,5,searchBList);
		request.getSession().setAttribute("searchBooks", Pager);
		response.sendRedirect("search.jsp");
	}
	
	/**
	 * 根据图书Id获取图书,并且跳转到购买页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void buyBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		///获取图书Id
		int id =Integer.parseInt(request.getParameter("bid"));
		BookDaoImpl dao = new BookDaoImpl();
		Books book = dao.getBooksById(id);
		
		request.getSession().setAttribute("buyBook", book);
		response.sendRedirect("buy.jsp");
	}

	/**
	 * 根据分类Id获取图书
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		///获取分类Id
		int cId =Integer.parseInt(request.getParameter("cid"));
		BookDaoImpl dao = new BookDaoImpl();
		List<Books> searchBList = dao.getBooksByCategoryId(cId);
		Integer page=1;
		//获取分页
		String strPage = request.getParameter("page");
		if(strPage==null || "".equals(strPage)){
		}else{
			page =Integer.parseInt(strPage);
		}
		//封装成分页对象
		PageBean<Books> Pager = new PageBean<Books>(page,5,searchBList);
		
		request.getSession().setAttribute("searchBooks", Pager);
		response.sendRedirect("search.jsp");
	}
	
	/**
	 * 查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		@SuppressWarnings("unchecked")
		PageBean<Books> pageBean = (PageBean<Books>) request.getSession().getAttribute("searchBooks");
		if(pageBean!=null){
			List<Books> searchBList = pageBean.getSourceList();
			if(searchBList!=null){
				//获取分页
				Integer page =Integer.parseInt(request.getParameter("page"));
				if(page==null || page<1) page=1;
				//封装成分页对象
				PageBean<Books> Pager = new PageBean<Books>(page,5,searchBList);
				
				request.getSession().setAttribute("searchBooks", Pager);
				response.sendRedirect("search.jsp");
				return;
			}
		}
		response.sendRedirect("index.jsp");
	}
	
	/**
	 * 获取图书列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void bookListByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDaoImpl dao = new BookDaoImpl();
		List<Books> blist = dao.getBookList();
		//获取分页
		Integer page=1;
		String strPage = request.getParameter("page");
		if(StringUtil.isNumber(strPage)==false){
		}else{
			page =Integer.parseInt(strPage);
		}
		//封装成分页对象
		PageBean<Books> Pager = new PageBean<Books>(page,20,blist);
		request.getSession().setAttribute("blist", Pager);
		response.sendRedirect("admin/booklist.jsp");
	}
	/**
	 * 删除图书
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delBookByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(StringUtil.isNumber(request.getParameter("bid"))==false){
			response.sendRedirect("admin/booklist.jsp");
			return;
		}
		int bookId = Integer.parseInt(request.getParameter("bid"));
		BookDaoImpl dao = new BookDaoImpl();
		boolean b = dao.delBookBookId(bookId);
		//存入操作信息
		if(b==true){
			request.getSession().setAttribute("ADMIN_MSG_BOOK","删除成功");
		}else{
			request.getSession().setAttribute("ADMIN_MSG_BOOK","删除失败");
		}
		bookListByAdmin(request, response);
	}
	/**
	 * 进入编辑图书页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showBookByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(StringUtil.isNumber(request.getParameter("bid"))==false){
			response.sendRedirect("admin/booklist.jsp");
			return;
		}
		int bookId = Integer.parseInt(request.getParameter("bid"));
		BookDaoImpl dao = new BookDaoImpl();
		Books b = dao.getBooksById(bookId);
		if(b!=null){
			//存入需要编辑用户
			request.getSession().setAttribute("editBook",b);
			response.sendRedirect("admin/bookedit.jsp");
		}else{
			response.sendRedirect("admin/booklist.jsp");
		}
		
	}
	/**
	 * 编辑图书
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void editBookByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(StringUtil.isNumber(request.getParameter("bid"))==false){
			response.sendRedirect("admin/booklist.jsp");
			return;
		}
		int bookId = Integer.parseInt(request.getParameter("bid"));
		//提取参数
		String title = request.getParameter("title");
		Double unitPrice =  Double.parseDouble(request.getParameter("unitPrice"));
		String author =  request.getParameter("author");
		int categoryId =  Integer.parseInt(request.getParameter("categoryId"));
		int publisherId =  Integer.parseInt(request.getParameter("publisherId"));
		String publishDate = request.getParameter("publishDate");
		String isbn =  request.getParameter("ISBN");
		int wordsCount =Integer.parseInt( request.getParameter("wordsCount"));
		String contentDescription =  request.getParameter("contentDescription");
		String authorDescription =  request.getParameter("authorDescription");
		//封装对象
		Books book = new Books();
		book.setId(bookId);
		book.setTitle(title);
		book.setUnitPrice(unitPrice);
		book.setAuthor(author);
		book.setCategoryId(categoryId);
		book.setPublisherId(publisherId);
		book.setPublishDate(publishDate);
		book.setIsbn(isbn);
		book.setWordsCount(wordsCount);
		book.setContentDescription(contentDescription);
		book.setAuthorDescription(authorDescription);
		//调用dao层方法
		BookDaoImpl dao = new BookDaoImpl();
		Boolean b = dao.upBook(book);
		//设置修改信息结果字符串
		if(b)
			request.getSession().setAttribute("ADMIN_MSG_BOOK", "信息编辑成功,BID:"+bookId);
		else
			request.getSession().setAttribute("ADMIN_MSG_BOOK", "信息编辑失败,BID:"+bookId);
			
		showBookByAdmin(request, response);
	}
	/**
	 * 添加图书
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addBookByAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取提交的参数
		String title = request.getParameter("title");
		Double unitPrice =  Double.parseDouble(request.getParameter("unitPrice"));
		String author =  request.getParameter("author");
		int categoryId =  Integer.parseInt(request.getParameter("categoryId"));
		int publisherId =  Integer.parseInt(request.getParameter("publisherId"));
		String publishDate = request.getParameter("publishDate");
		String isbn =  request.getParameter("ISBN");
		int wordsCount =Integer.parseInt( request.getParameter("wordsCount"));
		String contentDescription =  request.getParameter("contentDescription");
		String authorDescription =  request.getParameter("authorDescription");
		
		//信息完整性校验
		if(title==null 
				|| title.equals("") 
				|| unitPrice==null 
				|| unitPrice<0){
			request.getSession().setAttribute("ADMIN_MSG_BOOK", "添加失败,请检查图书信息是否正确");
			response.sendRedirect("admin/useradd.jsp");
			return;
		}
		//封装对象
		Books book = new Books();
		book.setTitle(title);
		book.setUnitPrice(unitPrice);
		book.setAuthor(author);
		book.setCategoryId(categoryId);
		book.setPublisherId(publisherId);
		book.setPublishDate(publishDate);
		book.setIsbn(isbn);
		book.setWordsCount(wordsCount);
		book.setContentDescription(contentDescription);
		book.setAuthorDescription(authorDescription);
		//调用dao层方法
		BookDaoImpl dao = new BookDaoImpl();
		//页面跳转
		if(dao.addBook(book)){
			request.getSession().setAttribute("ADMIN_MSG_BOOK", "添加成功,图书:"+title);
			bookListByAdmin(request, response);
		}else{
			request.getSession().setAttribute("ADMIN_MSG_BOOK", "添加失败！");
			response.sendRedirect("admin/bookadd.jsp");
		}
		
	}
}
