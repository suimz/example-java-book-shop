package com.suimz.mybookshop.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suimz.mybookshop.dao.Impl.OrderDaoImpl;
import com.suimz.mybookshop.entity.OrderBook;
import com.suimz.mybookshop.entity.OrderBookCustom;
import com.suimz.mybookshop.entity.Orders;
import com.suimz.mybookshop.entity.Users;
import com.suimz.mybookshop.shoppingcart.ShoppingCart;
import com.suimz.mybookshop.shoppingcart.ShoppingItem;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//判断是否登录
		if(request.getSession().getAttribute("user")==null){
			//跳转页面
			response.sendRedirect("login.jsp");
			return;
		}
		//判断操作
		String opt = request.getParameter("opt");
		if(opt.equals("add")){
			addOrder(request, response);
		}
		if(opt.equals("show")){
			showOrder(request, response);
		}
		
	}
	
	/**
	 * 添加订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从session中的cart(购物车)对象
		ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
		//获取从jsp页面传过来的userId
		Users user = (Users) request.getSession().getAttribute("user");
		//将获取的数据封装成Orders对象
		Orders order = new Orders();
		order.setUserId(user.getId());
		order.setTotalPrice(cart.getTotal());
		//调用dao的添加订单方法
		OrderDaoImpl dao = new OrderDaoImpl();
		int orderId = dao.addOrder(order);
		//如果返回的订单Id不为-1,则表示添加订单成功
		if(orderId!=-1){
			//获取购物车中的所有图书种类
			Collection<ShoppingItem> silist = cart.getItems();
			//遍历每一项图书
			for(ShoppingItem item:silist){
				//封装成OrderBook对象
				OrderBook ob = new OrderBook();
				ob.setOrderId(orderId);
				ob.setBookId(item.getItem().getId());
				ob.setQuantity(item.getAmount());
				ob.setUnitPrice(item.getItem().getUnitPrice());
				//添加到明细表
				dao.addOrderBook(ob);
			}
			//清空购物车
			 request.getSession().removeAttribute("cart");
			
			//页面跳转
			response.sendRedirect("payok.jsp");
		}else{
			//页面跳转
			response.sendRedirect("user/usercart.jsp");
		}
		
	}
	
	/**
	 * 查看订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void showOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取session中的user对象
		Users user = (Users) request.getSession().getAttribute("user");
		//调用dao层的获取订单方法
		OrderDaoImpl dao = new OrderDaoImpl();
		List<OrderBookCustom> olist = dao.getOrder(user.getId());
		//添加到session中
		request.getSession().setAttribute("olist",olist);
		//页面跳转
		response.sendRedirect("user/userorder.jsp");
	}
	

}
