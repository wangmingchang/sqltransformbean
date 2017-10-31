package com.github.wangmingchang.sqltransformbean.execute;

import java.net.URL;
import java.util.Properties;

import com.github.wangmingchang.sqltransformbean.pojo.dto.TableDto;
import com.github.wangmingchang.sqltransformbean.util.JavaTemlateUtil;
import com.github.wangmingchang.sqltransformbean.util.MySQLTableUtil;
import com.github.wangmingchang.sqltransformbean.util.PropertiesUtil;
import com.github.wangmingchang.sqltransformbean.util.StringUtil;

/**
 * 生成类
 * 
 * @author 王明昌
 * @since 2017年10月31日
 */
public class SqlTranformBean {

	public static void main(String[] args) {
		SqlTranformBean.init();
	}

	public static void init() {
		URL classpath = Thread.currentThread().getContextClassLoader().getResource("");
		String rootPate = classpath.getPath();
		Properties properties = PropertiesUtil.loadProps(rootPate + "sqlTransformBean.properties");
		String beanPackageName = properties.getProperty("beanPackageName");
		String daoPackageName = properties.getProperty("daoPackageName");
		String xmlPackageName = properties.getProperty("xmlPackageName");
		String tableName = properties.getProperty("tableName");
		String beanName = properties.getProperty("beanName");
		String daoName = properties.getProperty("daoName");
		String xmlName = properties.getProperty("xmlName");

		TableDto tableDto = MySQLTableUtil.getColumnByTableName(tableName);
		String savePath = rootPate + beanPackageName.replace(".", "/") + "/";
		savePath = StringUtil.transform(savePath, "/target/classes", "/src/main/java");
		System.out.println("savePath:" + savePath);
		JavaTemlateUtil.setJavaPoTemplate(savePath, tableDto, beanPackageName);

	}
}
