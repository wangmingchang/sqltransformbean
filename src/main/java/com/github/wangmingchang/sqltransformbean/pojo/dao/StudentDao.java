package com.github.wangmingchang.sqltransformbean.pojo.dao;

import java.util.List;
import java.util.Map;
import com.github.wangmingchang.sqltransformbean.pojo.po.Student;

/**
 * 学生dao
 * @author 王明昌
 * @date 2017-11-05
 */
public interface StudentDao {
	
	Student selectByPrimaryKey(java.lang.Long id);
	
	List<Student> selectBySelective(Map paramMap);
	
	int deleteByPrimaryKey(java.lang.Long id);
	
	int insert(Student student);
	
	int insertSelective(Student student);
	
	int updateByPrimaryKeySelective(Student student);
	
	int updateByPrimaryKey(Student student);
}
