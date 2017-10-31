package com.github.wangmingchang.sqltransformbean.util;

/**
 * 文件工具
 * 
 * @author 王明昌
 * @since 2017年10月31日
 */
public class FileUtil {
	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            目录
	 * @return 返回目录创建后的路径
	 */
	public static String createFolder(String folderPath) {
		String txt = folderPath;
		try {
			java.io.File myFilePath = new java.io.File(txt);
			txt = folderPath;
			if (!myFilePath.exists()) {
				System.out.println("====================目录不存在,重新创建====================");
				myFilePath.mkdirs();
			} else {
				// System.out.println("====================目录存在====================");
			}
		} catch (Exception e) {
			System.out.println("创建目录操作出错");
		}
		return txt;
	}
}
