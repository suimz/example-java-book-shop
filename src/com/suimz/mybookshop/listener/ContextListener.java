package com.suimz.mybookshop.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.suimz.mybookshop.db.Database;

public class ContextListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			// 初始化数据源
			Database.load(arg0.getServletContext());
			System.out.println("数据库连接池初始化完毕！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	
	}
}
