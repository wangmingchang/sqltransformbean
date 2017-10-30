package com.wmc.sqltransformbean.po.dto;

/**
 * 字段信息DTO
 * 
 * @author 王明昌
 * @since 2017年10月30日
 */
public class ColumnDto {

	private String columnName; // 字段名
	private String fieldName; // java字段名
	private String columnType; // 类型
	private String fieldType; // java字段类型
	private boolean ifKey; // 是不主键
	private String remark; // 备注

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public boolean isIfKey() {
		return ifKey;
	}

	public void setIfKey(boolean ifKey) {
		this.ifKey = ifKey;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	@Override
	public String toString() {
		return "ColumnDto [columnName=" + columnName + ", fieldName=" + fieldName + ", columnType=" + columnType
				+ ", fieldType=" + fieldType + ", ifKey=" + ifKey + ", remark=" + remark + "]";
	}

}
