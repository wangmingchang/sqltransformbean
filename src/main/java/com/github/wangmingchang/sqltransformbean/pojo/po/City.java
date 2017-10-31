package com.github.wangmingchang.sqltransformbean.pojo.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * 测试返回类
 * 
 * @author 王明昌
 * @since 2017年9月9日
 */
public class City {

	private String haoName; // 名称
	private String state; // 状态
	private Long id; // 主键
	private Date date; // 日期
	private BigDecimal money; // 数量
	private String accountType; // 帐户类型
	private Date createDate; // 创建日期
	private String orderId; // 主键
	private BigInteger abc;

	public String getHaoName() {
		return haoName;
	}

	public void setHaoName(String haoName) {
		this.haoName = haoName;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
