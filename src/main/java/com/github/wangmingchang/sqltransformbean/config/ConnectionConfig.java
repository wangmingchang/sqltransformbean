package com.github.wangmingchang.sqltransformbean.config;

import java.sql.Connection;
import java.sql.DriverManager;

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
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true";
		String userName = "root";
		String password = "wang125934";
		Connection mySQLConnection = getMySQLConnection(driverClassName, url, userName, password);
		return mySQLConnection;
	}
}
