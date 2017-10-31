package com.github.wangmingchang.sqltransformbean.util;

/**
 * String工具
 * 
 * @author 王明昌
 * @since 2017年10月31日
 */
public class StringUtil {
	/**
	 * 判断String不为空
	 * 
	 * @param str
	 *            字符串
	 * @return 不是空返回ture,否则false
	 */
	public static boolean isNotBlank(String str) {
		return !StringUtil.isBlank(str);
	}

	/**
	 * 判断String是否为空
	 * 
	 * @param str
	 *            字符串
	 * @return 空返回ture,否则false
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 替换指定的字符串
	 * 
	 * @param flag
	 *            目标字符串
	 * @param replace
	 *            要替换字符串
	 * @param out
	 *            替换后的字符串
	 * @return 返回替换后的字符串
	 */
	public static String transform(String flag, String replace, String out) {
		String[] strArr = flag.split(replace);
		for (int i = 0; i < strArr.length; i++) {
			if (i == 0) {
				flag = strArr[i] + out;
			} else {
				flag += strArr[i];
			}
		}
		return flag;
	}

	/**
	 * 截取某个字符最后一个出现位之后的字符串
	 * 
	 * @param flag
	 *            目标str
	 * @param c
	 *            截取的字符
	 * @return 返回如”a.b.c“，则“c”
	 */
	public static String subStringByLastChar(String flag, String c) {
		int lastIndexOf = flag.lastIndexOf(".");
		return flag.substring(lastIndexOf + 1, flag.length());
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(subStringByLastChar("a.b.c", "."));
	}
	
	
}
