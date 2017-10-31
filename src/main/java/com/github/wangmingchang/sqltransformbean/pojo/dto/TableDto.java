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
	private List<ColumnDto> columnDtos; // 字段集合
	private List<String> specialTypes; // java特殊的类型集合

	public List<String> getSpecialTypes() {
		return specialTypes;
	}

	public void setSpecialTypes(List<String> specialTypes) {
		this.specialTypes = specialTypes;
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

	@Override
	public String toString() {
		return "TableDto [tableName=" + tableName + ", className=" + className + ", columnDtos=" + columnDtos
				+ ", specialTypes=" + specialTypes + "]";
	}

}