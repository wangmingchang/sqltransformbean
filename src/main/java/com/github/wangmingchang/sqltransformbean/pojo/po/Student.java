package com.github.wangmingchang.sqltransformbean.pojo.po;
/**
 * 学生类
 * 
 * @author 王明昌
 * @since 2017-11-05
 */

public class Student {

	private Long id; // 主键
	private String name; // 姓名
	private Integer sex; 
	private String fatherName; // 父亲名

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + "name=" + name + "sex=" + sex + "fatherName=" + fatherName + "]";
	}
}
