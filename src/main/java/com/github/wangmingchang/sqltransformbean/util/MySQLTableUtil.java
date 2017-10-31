package com.github.wangmingchang.sqltransformbean.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.wangmingchang.sqltransformbean.config.ConnectionConfig;
import com.github.wangmingchang.sqltransformbean.pojo.dto.ColumnDto;
import com.github.wangmingchang.sqltransformbean.pojo.dto.TableDto;

/**
 * MySQL表信息
 * 
 * @author 王明昌
 * @since 2017年10月30日
 */
public class MySQLTableUtil {

	public static void main(String[] args) {
		getColumnByTableName("t_zw_test_stystem_user");

	}

	/**
	 * 获取表字段信息
	 * 
	 * @param tableName
	 *            表名
	 * @return 返回表字段信息
	 */
	public static TableDto getColumnByTableName(String tableName) {
		List<String> primaryKeyList = getPrimaryKeyList(tableName); // 主键信息
		Map<String, String> columnRemarkMap = getColumnRemark(tableName); // 备注信息
		TableDto tableDto = new TableDto(); // 表信息
		List<ColumnDto> columnDtoList = new ArrayList<ColumnDto>(); // 字段信息集合
		Connection mySQLConnection = ConnectionConfig.getMySQLConnection();
		ResultSet resultSet = null;
		try {

			Statement statement = mySQLConnection.createStatement();
			String sql = "select * from " + tableName;
			resultSet = statement.executeQuery(sql);
			ResultSetMetaData data = resultSet.getMetaData();
			tableDto.setTableName(tableName);
			tableDto.setClassName(transformToBean(tableName,true));
			for (int i = 1; i <= data.getColumnCount(); i++) {
				// 获得指定列的列名
				String columnName = data.getColumnName(i);
				// 获得指定列的数据类型名
				String columnTypeName = data.getColumnTypeName(i);
				// 对应数据类型的类
				String fieldType = data.getColumnClassName(i);

				/*
				 * System.out.println("获得列" + i + "的字段名称:" + columnName);
				 * System.out.println("获得列" + i + "的数据类型名:" + columnTypeName);
				 * System.out.println("获得列" + i + "所在的Catalog名字:" + catalogName);
				 * System.out.println("获得列" + i + "对应数据类型的类:" + fieldType);
				 * System.out.println("获得列" + i + "对应的表名:" + tableName);
				 * System.out.println("获得列" + i + "的默认的列的标题:" + columnLabel);
				 */

				ColumnDto columnDto = new ColumnDto();
				columnDto.setColumnName(columnName);
				columnDto.setFieldName(transformToBean(columnName,false));
				columnDto.setColumnType(columnTypeName);
				columnDto.setFieldType(StringUtil.subStringByLastChar(transformToType(fieldType),"."));
				String remark = columnRemarkMap.get(columnName) == null ? "" : columnRemarkMap.get(columnName);
				columnDto.setRemark(remark);
				boolean ifKey = primaryKeyList.contains(columnName);
				columnDto.setIfKey(ifKey);
				columnDtoList.add(columnDto);
			}
			tableDto.setColumnDtos(columnDtoList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(mySQLConnection, resultSet);
			System.out.println("tableDto:" + tableDto);
		}

		return tableDto;
	}

	/**
	 * 获取表的主键
	 * 
	 * @param tableName
	 *            表名
	 * @return 返回表的主键list
	 */
	public static List<String> getPrimaryKeyList(String tableName) {
		List<String> primaryKeyList = new ArrayList<String>();
		Connection mySQLConnection = ConnectionConfig.getMySQLConnection();
		ResultSet primaryKeys = null;
		try {
			primaryKeys = mySQLConnection.getMetaData().getPrimaryKeys(mySQLConnection.getCatalog().toUpperCase(),
					mySQLConnection.getMetaData().getUserName().toUpperCase(), tableName.toUpperCase());
			while (primaryKeys.next()) {
				String primaryKeyName = primaryKeys.getString("COLUMN_NAME"); // 主键名
				primaryKeyList.add(primaryKeyName);
				System.out.println("primaryKeyName:" + primaryKeyName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(mySQLConnection, primaryKeys);
		}
		return primaryKeyList;
	}

	/**
	 * 获取字段的备注
	 * 
	 * @param tableName
	 *            表名
	 * @return 返回 K为字段名，V为字段说明
	 */
	public static Map<String, String> getColumnRemark(String tableName) {
		Map<String, String> map = new HashMap<String, String>();
		Connection mySQLConnection = ConnectionConfig.getMySQLConnection();
		ResultSet resultSet = null;
		try {
			String sql = "SHOW FULL COLUMNS FROM " + tableName;
			Statement statement = mySQLConnection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String field = resultSet.getString("field"); // 字段
				String remark = resultSet.getString("comment"); // 备注
				map.put(field, remark);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(mySQLConnection, resultSet);
		}

		return map;
	}

	/**
	 * 关闭链接
	 * 
	 * @param connection
	 *            链接源
	 * @param resultSet
	 *            结果集
	 */
	private static void closeConnection(Connection connection, ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将数据库中的字段转换成java字段格式
	 * 
	 * @param columnName
	 *            字段名
	 * @param flag
	 *            第一个字符是否大写
	 * @return 返回java字段名
	 */
	private static String transformToBean(String columnName, boolean flag) {
		String value = null;
		if (columnName != null) {
			String[] strArr = columnName.split("_");
			for (int i = 0; i < strArr.length; i++) {
				if (i == 0) {
					if(flag) {
						value = strArr[i].replaceFirst(strArr[i].substring(0, 1), strArr[i].substring(0, 1).toUpperCase());
					}else {
						value = strArr[i].replaceFirst(strArr[i].substring(0, 1), strArr[i].substring(0, 1).toLowerCase());
					}
				} else {
					value += strArr[i].replaceFirst(strArr[i].substring(0, 1), strArr[i].substring(0, 1).toUpperCase());
				}
			}
		}
		return value;
	}

	/**
	 * java特殊类型的转换
	 * 
	 * @param type
	 *            类型
	 * @return 返回转换后的类型
	 */
	private static String transformToType(String type) {
		String value = type;
		if ("java.sql.Timestamp".equals(value) || "java.sql.Date".equals(value)) {
			value = "java.util.Date";
		}
		return value;
	}
	
	/**
	 * 是否包含数组中的某个字符串
	 * @param flag
	 * @return
	 */
	private static boolean isContain(String flag) {
		String[] strArr = {"java.math.BigInteger"};
		return true;
	}

}
