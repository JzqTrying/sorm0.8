package com.jzq.sorm.core;
/**
 * 负责java数据类型和数据库数据类型的互相转化
 * @author 25689 jzq
 *
 */
public interface TypeConvertor {
	
	/**
	 * 将数据库数据类型转化成java的数据类型
	 * @param columnType 数据库字段的数据类型
	 * @return java的数值类型
	 */
	public String databaseType2JavaType(String columnType);
	
	/**
	 * 负责将java数值类型转化成数据库数据类型
	 * @param javaDateType java的数值类型
	 * @return 数据库字段的数据类型
	 */
	public String javaType2DatabaseType(String javaDateType);
		
}
