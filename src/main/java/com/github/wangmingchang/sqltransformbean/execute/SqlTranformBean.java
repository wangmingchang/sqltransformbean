package com.github.wangmingchang.sqltransformbean.execute;

import java.net.URL;
import java.util.Date;
import java.util.Properties;

import com.github.wangmingchang.sqltransformbean.pojo.dto.RemarkDto;
import com.github.wangmingchang.sqltransformbean.pojo.dto.TableDto;
import com.github.wangmingchang.sqltransformbean.util.DateUtil;
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
		String beanTargetProject = properties.getProperty("beanTargetProject", "src/main/java");
		String beanPackageName = properties.getProperty("beanPackageName");
		String daoTargetProject = properties.getProperty("daoTargetProject", "src/main/java");
		String daoPackageName = properties.getProperty("daoPackageName");
		String xmlTargetProject = properties.getProperty("xmlTargetProject", "src/main/resource");
		String xmlPackageName = properties.getProperty("xmlPackageName");
		String tableName = properties.getProperty("tableName");
		String beanName = properties.getProperty("beanName");
		String daoName = properties.getProperty("daoName");
		String xmlName = properties.getProperty("xmlName");
		String isToString = properties.getProperty("isToString", "false");

		String isRemark = properties.getProperty("isRemark", "false");
		String beanRemark = properties.getProperty("beanRemark", "the is javabean");
		String daoRemark = properties.getProperty("daoRemark", "the is dao");
		String author = properties.getProperty("author");
		String defaultDate = DateUtil.DateToString(new Date());
		String createDate = properties.getProperty("createDate", defaultDate);
		RemarkDto remarkDto = null;
		// 是否开启备注信息
		if ("true".equals(isRemark)) {
			remarkDto = new RemarkDto();
			remarkDto.setBeanRemark(beanRemark);
			remarkDto.setDaoRemark(daoRemark);
			if (!StringUtil.isNotBlank(author)) {
				Properties props = System.getProperties();
				author = props.getProperty("user.name");
			}
			remarkDto.setAuthor(author);
			remarkDto.setCreateDate(createDate);
		}

		String beanSavePath = rootPate + beanPackageName.replace(".", "/") + "/";
		beanSavePath = StringUtil.transform(beanSavePath, "target/classes", beanTargetProject);
		System.out.println("beanSavePath:" + beanSavePath);
		String daoSavePath = rootPate + daoPackageName.replace(".", "/") + "/";
		daoSavePath = StringUtil.transform(daoSavePath, "target/classes", daoTargetProject);
		System.out.println("daoSavePath:" + daoSavePath);
		String xmlSavePath = rootPate + xmlPackageName.replace(".", "/") + "/";
		xmlSavePath = StringUtil.transform(xmlSavePath, "target/classes", xmlTargetProject);
		System.out.println("xmlSavePath:" + xmlSavePath);

		TableDto tableDto = MySQLTableUtil.getColumnByTableName(tableName);
		if (StringUtil.isNotBlank(beanName)) {
			tableDto.setClassName(beanName);
		}
		if(StringUtil.isBlank(daoName)) {
			daoName = beanName + "Dao";
		}
		if(StringUtil.isBlank(xmlName)) {
			xmlName = beanName + "Dao";
		}
		
		tableDto.setDaoName(daoName);
		tableDto.setXmlName(xmlName);
		
		if("true".equals(isToString)) {
			tableDto.setIfToString(Boolean.valueOf(isToString));
		}
		System.out.println("tableDto:" + tableDto);
		System.out.println("remarkDto:" + remarkDto);
		JavaTemlateUtil.setJavaPoTemplate(beanSavePath, tableDto, beanPackageName, remarkDto);
		JavaTemlateUtil.setJavaXmlTemplate(xmlSavePath, tableDto, xmlPackageName, beanPackageName,daoPackageName);
		JavaTemlateUtil.setJavaDaoTemplate(daoSavePath, tableDto, daoPackageName, remarkDto, beanPackageName);
		
	}
}
