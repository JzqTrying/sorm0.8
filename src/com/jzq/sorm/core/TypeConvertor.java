package com.jzq.sorm.core;
/**
 * ����java�������ͺ����ݿ��������͵Ļ���ת��
 * @author 25689 jzq
 *
 */
public interface TypeConvertor {
	
	/**
	 * �����ݿ���������ת����java����������
	 * @param columnType ���ݿ��ֶε���������
	 * @return java����ֵ����
	 */
	public String databaseType2JavaType(String columnType);
	
	/**
	 * ����java��ֵ����ת�������ݿ���������
	 * @param javaDateType java����ֵ����
	 * @return ���ݿ��ֶε���������
	 */
	public String javaType2DatabaseType(String javaDateType);
		
}
