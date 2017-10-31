package com.github.wangmingchang.sqltransformbean.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;

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
	private static String wordPackageName = "/apiDocs/api-doc/";// 生成word保存的包名

	/**
	 * 输出po模版
	 * @param savePath 保存路径
	 * @param tableDto 输出数据
	 * @param beanPackageName 包名
	 */
	public static void setJavaPoTemplate(String savePath, TableDto tableDto, String beanPackageName) {
		String dirPath = "/templates/java";
		URL classpath = Thread.currentThread().getContextClassLoader().getResource("");
		String path = classpath.getPath();
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
			configuration.setClassForTemplateLoading(JavaTemlateUtil.class, dirPath);
			configuration.setDefaultEncoding("UTF-8");
			// 从设置的目录中获得模板
			Template template = configuration.getTemplate("po.ftl");
			// 将数据与模板渲染的结果写入文件中
			Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("tableDto", tableDto);
			map.put("beanPackageName", beanPackageName);
			template.process(map, writer);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
