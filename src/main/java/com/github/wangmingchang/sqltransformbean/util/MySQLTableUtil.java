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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.wangmingchang.sqltransformbean.config.ConnectionConfig;
import com.github.wangmingchang.sqltransformbean.pojo.dto.ColumnDto;
import com.github.wangmingchang.sqltransformbean.pojo.dto.PrimaryKeyColumnDto;
import com.github.wangmingchang.sqltransformbean.pojo.dto.TableDto;

/**
 * MySQL表信息
 *
 * @author 王明昌
 * @since 2017年10月30日
 */
public class MySQLTableUtil {

    private static final Logger logger = LoggerFactory.getLogger(MySQLTableUtil.class);

    public static void main(String[] args) {
        getColumnByTableName("t_zw_test_stystem_user");

    }

    /**
     * 获取表字段信息
     *
     * @param tableName 表名
     * @return 返回表字段信息
     */
    public static TableDto getColumnByTableName(String tableName) {
        List<String> primaryKeyList = getPrimaryKeyList(tableName); // 主键名信息
        List<PrimaryKeyColumnDto> primaryKeyColumnDtoList = new ArrayList<PrimaryKeyColumnDto>(); //主键集合信息
        Map<String, String> columnRemarkMap = getColumnRemark(tableName); // 备注信息
        TableDto tableDto = new TableDto(); // 表信息
        List<ColumnDto> columnDtoList = new ArrayList<ColumnDto>(); // 字段信息集合
        List<String> packageList = new ArrayList<String>(); // 在生成JavaBean时需要引入的包
        Connection mySQLConnection = ConnectionConfig.getMySQLConnection();
        ResultSet resultSet = null;
        try {

            Statement statement = mySQLConnection.createStatement();
            String sql = "select * from " + tableName;
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData data = resultSet.getMetaData();
            tableDto.setTableName(tableName);
            tableDto.setClassName(transformToBean(tableName, true));
            for (int i = 1; i <= data.getColumnCount(); i++) {
                // 获得指定列的列名
                String columnName = data.getColumnName(i);
                // 获得指定列的数据类型名
                String columnTypeName = data.getColumnTypeName(i);
                int precision = data.getPrecision(i);
                if (columnTypeName.equals("INT") || columnTypeName.equals("BIGINT")) {
                    if (precision > 9){
                        columnTypeName = "Long";
                    }else {
                        columnTypeName = "INTEGER";
                    }
                } else if (columnTypeName.equals("DATETIME")) {
                    columnTypeName = "TIMESTAMP";
                }
                // 对应数据类型的类
                String fieldType = data.getColumnClassName(i);

                ColumnDto columnDto = new ColumnDto();
                columnDto.setColumnName(columnName);
                String fieldName = transformToBean(columnName, false);
                columnDto.setFieldName(fieldName);
                columnDto.setColumnType(columnTypeName);
                fieldType = transformToType(fieldType);
                columnDto.setFieldType(StringUtil.subStringByLastChar(fieldType, "."));
                if (isContain(fieldType)) {
                    if (!packageList.contains(fieldType)) {
                        packageList.add(fieldType);
                    }
                }

                String remark = columnRemarkMap.get(columnName) == null ? "" : columnRemarkMap.get(columnName);
                columnDto.setRemark(remark);
                boolean ifKey = primaryKeyList.contains(columnName);
                //是主键
                if (ifKey) {
                    PrimaryKeyColumnDto primaryKeyColumnDto = new PrimaryKeyColumnDto();
                    primaryKeyColumnDto.setColumnName(columnName);
                    primaryKeyColumnDto.setFieldName(fieldName);
                    primaryKeyColumnDto.setColumnType(columnTypeName);
                    primaryKeyColumnDto.setFieldType(fieldType);
                    primaryKeyColumnDto.setIfKey(ifKey);
                    primaryKeyColumnDto.setRemark(remark);
                    primaryKeyColumnDtoList.add(primaryKeyColumnDto);
                }
                columnDto.setIfKey(ifKey);
                columnDtoList.add(columnDto);
            }
            tableDto.setColumnDtos(columnDtoList);
            tableDto.setPackageNames(packageList);
            tableDto.setPrimaryKeyColumnDtos(primaryKeyColumnDtoList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(mySQLConnection, resultSet);
        }

        return tableDto;
    }

    /**
     * 获取表的主键
     *
     * @param tableName 表名
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
                logger.info("primaryKeyName:" + primaryKeyName);
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
     * @param tableName 表名
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
     * @param connection 链接源
     * @param resultSet  结果集
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
     * @param columnName 字段名
     * @param flag       第一个字符是否大写
     * @return 返回java字段名
     */
    private static String transformToBean(String columnName, boolean flag) {
        String value = null;
        if (columnName != null) {
            columnName = columnName.toLowerCase();
            String[] strArr = columnName.split("_");
            for (int i = 0; i < strArr.length; i++) {
                if (i == 0) {
                    if (flag) {
                        value = strArr[i].replaceFirst(strArr[i].substring(0, 1),
                                strArr[i].substring(0, 1).toUpperCase());
                    } else {
                        value = strArr[i].replaceFirst(strArr[i].substring(0, 1),
                                strArr[i].substring(0, 1).toLowerCase());
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
     * @param type 类型
     * @return 返回转换后的类型
     */
    private static String transformToType(String type) {
        String value = type;
        if ("java.sql.Timestamp".equals(value) || "java.sql.Date".equals(value) || "java.sql.Timestamp".equals(value)) {
            value = "java.util.Date";
        }
        return value;
    }

    /**
     * 是否包含数组中的某个字符串
     *
     * @param flag 传入字符串
     * @return 如果传入falg存在集合中，就返回true,否则false
     */
    private static boolean isContain(String flag) {
        boolean value = false;
        List<String> strList = new ArrayList<String>();
        strList.add("java.math.BigInteger");
        strList.add("java.math.BigDecimal");
        strList.add("java.util.Date");
        if (strList.contains(flag)) {
            value = true;
        }
        return value;
    }

}
