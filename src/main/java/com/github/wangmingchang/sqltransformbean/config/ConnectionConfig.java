package com.github.wangmingchang.sqltransformbean.config;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.github.wangmingchang.sqltransformbean.util.PropertiesUtil;

/**
 * 链接配置
 * 
 * @author 王明昌
 * @since 2017年10月30日
 */
public class ConnectionConfig {
	
	/**
	 * 获取链接对象
	 * @param driverClassName 驱动器
	 * @param url url路径
	 * @param userName 用户名
	 * @param password 密码
	 * @return 返回链接对象
	 */
	public static Connection getMySQLConnection(String driverClassName, String url, String userName, String password) {
		Connection connection = null;
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, userName, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	
	/**
	 * 默认返回链接对象
	 * @return 返回链接对象
	 */
	public static Connection getMySQLConnection() {
		URL classpath = Thread.currentThread().getContextClassLoader().getResource("");
		String rootPate = classpath.getPath();
		System.out.println("链接对象-root:" + rootPate);
		Properties properties = PropertiesUtil.loadProps(rootPate + "sqlTransformBean.properties");
		
		String driverClassName = properties.getProperty("jdbc.driverClassName");
		String url = properties.getProperty("jdbc.url");
		String userName = properties.getProperty("jdbc.userName");
		String password = properties.getProperty("jdbc.password");
		Connection mySQLConnection = getMySQLConnection(driverClassName, url, userName, password);
		return mySQLConnection;
	}
}
