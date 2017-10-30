package com.wmc.sqltransformbean.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wmc.sqltransformbean.conf.ConnectionConfig;
import com.wmc.sqltransformbean.po.dto.ColumnDto;
import com.wmc.sqltransformbean.po.dto.TableDto;

/**
 * MySQL表信息
 * 
 * @author 王明昌
 * @since 2017年10月30日
 */
public class MySQLTableUtil {

	public static void main(String[] args) {
		getColumnByTableName("city");

	}

	/**
	 * 获取表字段信息
	 * 
	 * @param tableName
	 *            表名
	 * @return 返回表字段信息
	 */
	public static TableDto getColumnByTableName(String tableName) {
		List<String> primaryKeyList = getPrimaryKeyList(tableName);
		List<ColumnDto> columnDtoList = new ArrayList<ColumnDto>(); // 字段信息集合
		Connection mySQLConnection = ConnectionConfig.getMySQLConnection();
		Statement statement = null;
		TableDto tableDto = new TableDto(); // 表信息
		try {

			statement = mySQLConnection.createStatement();
			String sql = "select * from " + tableName;
			ResultSet rs = statement.executeQuery(sql);
			ResultSetMetaData data = rs.getMetaData();
			tableDto.setTableName(tableName);
			tableDto.setClassName(transformToBean(tableName));
			for (int i = 1; i <= data.getColumnCount(); i++) {
				// 获得指定列的列名
				String columnName = data.getColumnName(i);
				// 获得指定列的数据类型名
				String columnTypeName = data.getColumnTypeName(i);
				// 所在的Catalog名字
				String catalogName = data.getCatalogName(i);
				// 对应数据类型的类
				String fieldType = data.getColumnClassName(i);
				// 默认的列的标题
				String columnLabel = data.getColumnLabel(i);

				System.out.println("获得列" + i + "的字段名称:" + columnName);
				System.out.println("获得列" + i + "的数据类型名:" + columnTypeName);
				System.out.println("获得列" + i + "所在的Catalog名字:" + catalogName);
				System.out.println("获得列" + i + "对应数据类型的类:" + fieldType);
				System.out.println("获得列" + i + "对应的表名:" + tableName);
				System.out.println("获得列" + i + "的默认的列的标题:" + columnLabel);

				ColumnDto columnDto = new ColumnDto();
				columnDto.setColumnName(columnName);
				columnDto.setFieldName(transformToBean(columnName));
				columnDto.setColumnType(columnTypeName);
				columnDto.setFieldType(fieldType);
				// columnDto.setRemark(remark);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
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
		
		
		return null;
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
	 * @return 返回java字段名
	 */
	private static String transformToBean(String columnName) {
		String value = null;
		if (columnName != null) {
			String[] strArr = columnName.split("-");
			for (int i = 0; i < strArr.length; i++) {
				if (i == 0) {
					value = strArr[i].replace(strArr[i].substring(0, 1), strArr[i].substring(0, 1).toLowerCase());
				} else {
					value += strArr[i].replace(strArr[i].substring(0, 1), strArr[i].substring(0, 1).toUpperCase());
				}
			}
		}
		return value;
	}

}
