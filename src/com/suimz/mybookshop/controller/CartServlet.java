package com.suimz.mybookshop.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suimz.mybookshop.dao.Impl.BookDaoImpl;
import com.suimz.mybookshop.entity.Books;
import com.suimz.mybookshop.entity.Users;
import com.suimz.mybookshop.shoppingcart.ShoppingCart;
import com.suimz.mybookshop.shoppingcart.ShoppingItem;
import com.suimz.mybookshop.utils.StringUtil;

/**
 * ShopingCart操作实体类
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opt = request.getParameter("opt");
		//判断操作
		if(opt.equals("buy")){
			buyCart(request, response);
		}else if(opt.equals("del")){
			delCart(request, response);
		}else if(opt.equals("clear")){
			clearCart(request, response);
		}else if(opt.equals("show")){
			showCart(request, response);
		}else if(opt.equals("inAmount")){
			inAmount(request, response);
		}else if(opt.equals("deAmount")){
			deAmount(request, response);
		}else{
			response.sendRedirect("index.jsp");
		}
		
	}

	/**
	 * 添加商品到购物车
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void buyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		if(cart==null){
			cart = new ShoppingCart();
		}
		//获取购买数量
		String strNum = request.getParameter("num");
		if(!StringUtil.isNumber(strNum))
			strNum="1";
		
		int num = Integer.parseInt(strNum);
		//获取图书信息
		String isbn = request.getParameter("isbn");
		BookDaoImpl dao = new BookDaoImpl();
		Books book = dao.getBooksByIsbn(isbn);
		//把商品添加到购物车
		cart.add(book, num);
		
		request.getSession().setAttribute("cart",cart);
		//跳转页面
		response.sendRedirect("addcartok.jsp");
	}
	protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		if(cart!=null){
			cart.clear();
			request.getSession().setAttribute("cart", cart);
		}
		//页面跳转
		showCart(request, response);
	}
	
	/**
	 * 删除购物车中的商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从session中获取ShoppingCart
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		//判空
		if(cart!=null){
			//获取要删除图书的isbn
			String isbn = request.getParameter("isbn");
			//删除这个图书
			if(isbn!=null && !isbn.equals("")){
				cart.remove(isbn);
				//商品种类减1
				cart.setItemAmount();
				
				//计算商品总价
				cart.getTotalReal();
				request.getSession().setAttribute("cart", cart);

			}
		}
		showCart(request, response);
	}
	
	/**
	 * 显示购物车
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		if(cart!=null){
			Collection<ShoppingItem> silist = cart.getItems();
			request.getSession().setAttribute("silist", silist);
			//计算商品总价
			cart.getTotalReal();
		}else{
			cart = new ShoppingCart();
		}
		request.getSession().setAttribute("cart", cart);
		
		Users user = (Users) request.getSession().getAttribute("user");
		if(user!=null){
			//页面跳转
			response.sendRedirect("user/usercart.jsp");
		}else{
			//页面跳转
			response.sendRedirect("showcart.jsp");
		}
	}
	

	/**
	 * 增加购物车商品数量
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void inAmount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从session中获取ShoppingCart
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		if(cart!=null){
			String isbn =  request.getParameter("isbn");
			//信息完整性校验
			if(isbn!=null && !isbn.equals("")){
				//获取ShoppingItem
				ShoppingItem si =  cart.getItem(isbn);
				if(si!=null){
					si.inAmount();
				}
				//计算商品总价
				cart.getTotalReal();
				request.getSession().setAttribute("cart", cart);
			}
		}
		
		//页面跳转
		showCart(request, response);
	}
	
	/**
	 * 减少购物车商品数量
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deAmount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从session中获取ShoppingCart
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		if(cart!=null){
			String isbn = request.getParameter("isbn");
			//信息完整性校验
			if(isbn!=null && !isbn.equals("")){
				//获取ShoppingItem
				ShoppingItem si =  cart.getItem(isbn);
				if(si!=null && si.getAmount()>1)
					si.deAmount();
				//计算商品总价
				cart.getTotalReal();
				request.getSession().setAttribute("cart", cart);
			}
		}
		showCart(request, response);
	}
}
