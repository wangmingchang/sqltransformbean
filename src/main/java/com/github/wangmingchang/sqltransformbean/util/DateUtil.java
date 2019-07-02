package com.github.wangmingchang.sqltransformbean.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author 王明昌
 * @since 2017年11月2日
 */
public class DateUtil {

    public static String DateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static void main(String[] args) {
        System.out.println(DateToString(new Date()));
        ;
    }
}
