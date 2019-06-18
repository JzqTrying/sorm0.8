package com.jzq.sorm.core;
/**
 * mysql�������ͺ�java�������͵�ת��
 * @author 25689 jzq
 *
 */
public class MySqlTypeConvertor implements TypeConvertor{

	@Override
	public String databaseType2JavaType(String columnType) {
		//varchar-->String
		if("varchar".equalsIgnoreCase(columnType)||"char".equalsIgnoreCase(columnType)) {
			return "String";
		}else if("int".equalsIgnoreCase(columnType)||
				"tinyint".equalsIgnoreCase(columnType)||
				"smallint".equalsIgnoreCase(columnType)||
				"integer".equalsIgnoreCase(columnType)) {
			return "Integer";
		}else if("bigint".equalsIgnoreCase(columnType)) {
			return "long";
		}else if("double".equalsIgnoreCase(columnType)||"float".equalsIgnoreCase(columnType)) {
			return "Double";
		}else if("clob".equalsIgnoreCase(columnType)) {
			return "java.sql.Clob";
		}else if("blob".equalsIgnoreCase(columnType)) {
			return "java.sql.Blob";
		}else if("date".equalsIgnoreCase(columnType)) {
			return "java.sql.Date";
		}else if("time".equalsIgnoreCase(columnType)) {
			return "java.sql.Time";
		}else if("timastamp".equalsIgnoreCase(columnType)) {
			return "java.sql.Timestamp";
		}
		
		return null;
	}

	@Override
	public String javaType2DatabaseType(String javaDateType) {
		return null;
	}
	
}
