package com.github.wangmingchang.sqltransformbean.pojo.dto;

import java.util.List;

/**
 * 表的信息DTO
 * 
 * @author 王明昌
 * @since 2017年10月30日
 */
public class TableDto {
	private String tableName; // 表名
	private String className; // java对象名
	private String daoName; // dao类名
	private String xmlName; // xml映射名
	private List<ColumnDto> columnDtos; // 字段集合
	private List<String> packageNames; // JavaBean需要引入的包的集合
	private boolean ifToString; // 是否开启toString
	private List<PrimaryKeyColumnDto> primaryKeyColumnDtos; // 主键信息集合

	public String getDaoName() {
		return daoName;
	}

	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}

	public String getXmlName() {
		return xmlName;
	}

	public void setXmlName(String xmlName) {
		this.xmlName = xmlName;
	}

	public List<String> getPackageNames() {
		return packageNames;
	}

	public boolean isIfToString() {
		return ifToString;
	}

	public void setIfToString(boolean ifToString) {
		this.ifToString = ifToString;
	}

	public void setPackageNames(List<String> packageNames) {
		this.packageNames = packageNames;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<ColumnDto> getColumnDtos() {
		return columnDtos;
	}

	public void setColumnDtos(List<ColumnDto> columnDtos) {
		this.columnDtos = columnDtos;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<PrimaryKeyColumnDto> getPrimaryKeyColumnDtos() {
		return primaryKeyColumnDtos;
	}

	public void setPrimaryKeyColumnDtos(List<PrimaryKeyColumnDto> primaryKeyColumnDtos) {
		this.primaryKeyColumnDtos = primaryKeyColumnDtos;
	}

	@Override
	public String toString() {
		return "TableDto [tableName=" + tableName + ", className=" + className + ", daoName=" + daoName + ", xmlName="
				+ xmlName + ", columnDtos=" + columnDtos + ", packageNames=" + packageNames + ", ifToString="
				+ ifToString + ", primaryKeyColumnDtos=" + primaryKeyColumnDtos + "]";
	}

}
