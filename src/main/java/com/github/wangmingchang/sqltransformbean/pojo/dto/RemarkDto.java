package com.github.wangmingchang.sqltransformbean.pojo.dto;

/**
 * 类的备注信息DTO
 * 
 * @author 王明昌
 * @since 2017年11月2日
 */
public class RemarkDto {
	private String beanRemark; // javabean的备注
	private String daoRemark; // dao类的备注
	private String author; // 创建人
	private String createDate; // 创建日期

	public String getBeanRemark() {
		return beanRemark;
	}

	public void setBeanRemark(String beanRemark) {
		this.beanRemark = beanRemark;
	}

	public String getDaoRemark() {
		return daoRemark;
	}

	public void setDaoRemark(String daoRemark) {
		this.daoRemark = daoRemark;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "RemarkDto [beanRemark=" + beanRemark + ", daoRemark=" + daoRemark + ", author=" + author
				+ ", createDate=" + createDate + "]";
	}

}
