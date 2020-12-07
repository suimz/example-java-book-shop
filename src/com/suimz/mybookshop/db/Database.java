package com.suimz.mybookshop.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

/**
 * 数据源
 * - dbcp数据库连接池
 */
public class Database {

	private static DataSource ds;
	
	private Database() {}

	/**
	 * 初始化数据源
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws Exception 
	 */
	public static void load(ServletContext ctx) throws Exception {
		if(ds == null){
			String path = ctx.getRealPath("/WEB-INF/db.properties");
	        Properties properties = new Properties();
            properties.load(new InputStreamReader(new FileInputStream(new File(path))));
            ds = new BasicDataSourceFactory().createDataSource(properties);
		}
	}
	
	/**
	 * 获取连接对象
	 * @return
	 */
	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取数据源
	 * @return
	 */
	public static DataSource getDataSource(){
		return ds;
	}

}
