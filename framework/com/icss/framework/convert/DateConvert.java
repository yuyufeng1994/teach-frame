package com.icss.framework.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期类型转化器
 * @author yyf
 *
 */
public class DateConvert {

	private final static SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public static Date valueOf(String str){
		try{
			return f1.parse(str);
		}catch(Exception ex){
			try {
				return f2.parse(str);
			} catch (ParseException e) {
				throw new RuntimeException("日期类型转换错误",e);
			}
		}
	}
}
