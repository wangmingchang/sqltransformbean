package com.github.wangmingchang.sqltransformbean.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.wangmingchang.sqltransformbean.pojo.dto.ColumnDto;
import com.github.wangmingchang.sqltransformbean.pojo.dto.PrimaryKeyColumnDto;
import com.github.wangmingchang.sqltransformbean.pojo.dto.RemarkDto;
import com.github.wangmingchang.sqltransformbean.pojo.dto.TableDto;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * java对象模板工具
 * 
 * @author 王明昌
 * @since 2017年10月31日
 */
public class JavaTemlateUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JavaTemlateUtil.class);
	private static String wordPackageName = "/apiDocs/api-doc/";// 生成word保存的包名
	private static String javaScanPath = "/templates/java"; //freemarke扫描java模板包
	private static String xmlScanPath = "/templates/xml"; //freemarke扫描xml模板包
	

	/**
	 * 输出po模版
	 * @param savePath 保存路径
	 * @param tableDto 输出数据
	 * @param beanPackageName 包名
	 * @param remarkDto 备注信息
	 */
	public static void setJavaPoTemplate(String savePath, TableDto tableDto, String beanPackageName, RemarkDto remarkDto) {
		//String dirPath = "/templates/java";
		//URL classpath = Thread.currentThread().getContextClassLoader().getResource("");
		//String path = classpath.getPath();
		if (StringUtil.isNotBlank(savePath)) {
			FileUtil.createFolder(savePath);
		}
		File file = new File(savePath + tableDto.getClassName() + ".java");
		// 创建一个freemarker.template.Configuration实例，它是存储 FreeMarker 应用级设置的核心部分
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
		try {
			//File dir = new File(path + "/templates/word");
			// 设置模板目录
			// configuration.setDirectoryForTemplateLoading(dir);
			configuration.setClassForTemplateLoading(JavaTemlateUtil.class, javaScanPath);
			configuration.setDefaultEncoding("UTF-8");
			// 从设置的目录中获得模板
			Template template = configuration.getTemplate("po.ftl");
			// 将数据与模板渲染的结果写入文件中
			Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("tableDto", tableDto);
			map.put("beanPackageName", beanPackageName);
			map.put("remarkDto", remarkDto);
			template.process(map, writer);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 输出xml模版
	 * @param savePath 保存路径
	 * @param tableDto 输出数据
	 * @param xmlPackageName xml保存的包名 
	 * @param beanPackageName javabean保存的包名
	 * @param daoPackageName dao保存的包名
	 */
	public static void setJavaXmlTemplate(String savePath, TableDto tableDto, String xmlPackageName, String beanPackageName, String daoPackageName) {
		if (StringUtil.isNotBlank(savePath)) {
			FileUtil.createFolder(savePath);
		}
		File file = new File(savePath + tableDto.getXmlName() + ".xml");
		// 创建一个freemarker.template.Configuration实例，它是存储 FreeMarker 应用级设置的核心部分
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
		try {
			//File dir = new File(path + "/templates/word");
			// 设置模板目录
			// configuration.setDirectoryForTemplateLoading(dir);
			configuration.setClassForTemplateLoading(JavaTemlateUtil.class, xmlScanPath);
			configuration.setDefaultEncoding("UTF-8");
			// 从设置的目录中获得模板
			Template template = configuration.getTemplate("xml.ftl");
			// 将数据与模板渲染的结果写入文件中
			Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			List<String> columnNames = new ArrayList<String>();
			StringBuffer columnNamSb = new StringBuffer();
			int size = tableDto.getColumnDtos().size();
			for (int i = 0; i < size; i++) {
				ColumnDto columnDto = tableDto.getColumnDtos().get(i);
				if(i % 3 == 0 && i != 0) {
					columnNames.add(columnNamSb.toString());
					//消除已存在的数据
					columnNamSb = new StringBuffer();
					columnNamSb.append(columnDto.getColumnName());
				}else {
					columnNamSb.append(columnDto.getColumnName());
				}
				if(i != (size - 1)) {
					columnNamSb.append(", ");
				}else {
					logger.info("i---->"+i+"*******长度："+size);
				}
				logger.info("columnNamSb---->"+columnNamSb);
				if((size - i +1) < 3 && i == (size - 1)) {
					//最后一个不能整除的个数，有可能是2或者是1个
					columnNames.add(columnNamSb.toString());
				}
			}
			
			map.put("columnNames", columnNames);
			map.put("tableDto", tableDto);
			map.put("xmlPackageName", xmlPackageName);
			map.put("beanPackageName", beanPackageName);
			map.put("daoPackageName", daoPackageName);
			template.process(map, writer);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 输出dao模版
	 * @param savePath 保存路径
	 * @param tableDto 输出数据
	 * @param daoPackageName dao保存的包名
	 * @param remarkDto 备注的信息
	 * @param beanPackageName po包名
	 */
	public static void setJavaDaoTemplate(String savePath, TableDto tableDto, String daoPackageName,
			RemarkDto remarkDto, String beanPackageName) {
		if (StringUtil.isNotBlank(savePath)) {
			FileUtil.createFolder(savePath);
		}
		File file = new File(savePath + tableDto.getDaoName() + ".java");
		// 创建一个freemarker.template.Configuration实例，它是存储 FreeMarker 应用级设置的核心部分
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
		try {
			//File dir = new File(path + "/templates/word");
			// 设置模板目录
			// configuration.setDirectoryForTemplateLoading(dir);
			configuration.setClassForTemplateLoading(JavaTemlateUtil.class, javaScanPath);
			configuration.setDefaultEncoding("UTF-8");
			// 从设置的目录中获得模板
			Template template = configuration.getTemplate("dao.ftl");
			// 将数据与模板渲染的结果写入文件中
			Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			HashMap<String, Object> map = new HashMap<String, Object>();
			for (PrimaryKeyColumnDto primaryKeyColumnDto : tableDto.getPrimaryKeyColumnDtos()) {
				primaryKeyColumnDto.setFieldType(StringUtil.subStringByLastChar(primaryKeyColumnDto.getFieldType(), "."));
			}
			map.put("tableDto", tableDto);
			map.put("daoPackageName", daoPackageName);
			map.put("remarkDto", remarkDto);
			map.put("beanPackageName", beanPackageName);
			template.process(map, writer);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
