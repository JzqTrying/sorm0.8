package com.jzq.sorm.utils;

import java.lang.reflect.Method;

/**
 * 封装了反射常用的操作
 * @author 25689
 *
 */
public class ReflectUtils {
	
	/**
	 * 调用obj对象对应属性fieldName的get方法
	 * @param c
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public static Object invokeGet(String fieldName,Object obj) {
		try {
			Class c=obj.getClass();
			Method m=c.getDeclaredMethod("get"+StringUtils.firstChar2UpperCase(fieldName), null);
			return m.invoke(obj, null);			
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	public static void invokeSet(Object obj,String columnName,Object columnValue) {
		Method m;
		try {
			if(columnValue!=null) {
			m = obj.getClass().getDeclaredMethod("set"+StringUtils.firstChar2UpperCase(columnName),
					columnValue.getClass());
			m.invoke(obj, columnValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
}
