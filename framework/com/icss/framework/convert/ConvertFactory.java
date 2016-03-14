package com.icss.framework.convert;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类型转化工厂
 * 
 * @author yyf
 *
 */
public class ConvertFactory {

	private ConvertFactory() {
	}

	private static Map<Class<?>, Class<?>> convertMap = new HashMap<Class<?>, Class<?>>();

	static {
		convertMap.put(int.class, Integer.class);
		convertMap.put(short.class, Short.class);
		convertMap.put(long.class, Long.class);
		convertMap.put(float.class, Float.class);
		convertMap.put(double.class, Double.class);
		convertMap.put(boolean.class, Boolean.class);
		convertMap.put(byte.class, Byte.class);
		convertMap.put(char.class, CharConvert.class);
		convertMap.put(Character.class, CharConvert.class);
		convertMap.put(String.class, StringConvert.class);
		convertMap.put(Date.class, DateConvert.class);

	}

	public static Object convert(Class<?> cls, String str) throws Exception {
		if (convertMap.get(cls) == null) {
			return cls.getMethod("valueOf", String.class).invoke(null, str);
		} else {
			return convertMap.get(cls).getMethod("valueOf", String.class).invoke(null, str);
		}
	}
}
