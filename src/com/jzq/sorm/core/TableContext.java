package com.jzq.sorm.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jzq.sorm.bean.ColumnInfo;
import com.jzq.sorm.bean.TableInfo;
import com.jzq.sorm.utils.JavaFileUtils;
import com.jzq.sorm.utils.StringUtils;

/**
 * ����������ݿ����б�ṹ����ṹ�Ĺ�ϵ�������Ը��ݱ�ṹ������ṹ
 * @author 25689 jzq
 *
 */
public class TableContext {
	
	/**
	 * ����Ϊkey������Ϣ����Ϊvalue
	 */	
	public static Map<String,TableInfo> tables=new HashMap<String,TableInfo>();
	
	/**
	 * ��po��class����ͱ���Ϣ���������������������
	 */
	public static Map<Class,TableInfo> poClassTableMap=new HashMap<Class,TableInfo>();
	
	private TableContext() {
		
	}
	
	static {
		try {
			//��ʼ����ñ����Ϣ
			Connection con=DBManager.getConn();
			DatabaseMetaData dbmd=con.getMetaData();
			
			ResultSet tableRet=dbmd.getTables(null, "%", "%", new String[] {"TABLE"});
			
			while(tableRet.next()) {
				String tableName=(String)tableRet.getObject("TABLE_NAME");
				
				TableInfo ti=new TableInfo(tableName,new ArrayList<ColumnInfo>()
						,new HashMap<String,ColumnInfo>());
				tables.put(tableName, ti);
				
				ResultSet set=dbmd.getColumns(null, "%", tableName, "%");//��ѯ���������ֶ�
				while(set.next()) {
					ColumnInfo ci=new ColumnInfo(set.getString("COLUMN_NAME"),
							set.getString("TYPE_NAME"),0);
					ti.getColumn().put(set.getString("COLUMN_NAME"), ci);
				}
				
				ResultSet set2=dbmd.getPrimaryKeys(null, "%d", tableName);//��ѯuser���е�����
				while(set2.next()) {
					ColumnInfo ci2=(ColumnInfo)ti.getColumn().get(set2.getObject("COLUMN_NAME"));
					ci2.setKeyType(1);//����Ϊ��������
					ti.getPrikeys().add(ci2);
				}
				
				if(ti.getPrikeys().size()>0) {//ȡΨһ����������ʹ�á������Ψһ��������Ϊ��
					ti.setOnlyPriKey(ti.getPrikeys().get(0));
				}
			}
		}catch(SQLException e) {
				e.printStackTrace();
			}
		
		//������ṹ
		//updateJavaPOFile();
		
		//����po���������е��࣬�������ã����Ч��
		loadPOTables();
		}
	
	public static Map<String,TableInfo> getTableInfos(){
		return tables;
	}
	
	/**
	 * ���ݱ�ṹ,�������õ�po���µ�java��
	 */
	public static void updateJavaPOFile() {
		Map<String,TableInfo> map=TableContext.tables;
		for(TableInfo tableInfo:map.values()) {
		JavaFileUtils.createJavaPOFile(tableInfo,new MySqlTypeConvertor());
		}
	}
	
	/**
	 * ����po���������
	 * @param args
	 */
	public static void loadPOTables() {
		for(TableInfo tableInfo:tables.values()) {
			Class c;
			try {
				c = Class.forName(DBManager.getConf().getPoPackage()
						+"."+StringUtils.firstChar2UpperCase(tableInfo.getTname()));
				poClassTableMap.put(c, tableInfo);
			} catch (ClassNotFoundException e) {
				System.out.println("po����δ����");
			}			
		}
	}
	public static void main(String[] args) {
		Map<String,TableInfo> tables=TableContext.tables;
		System.out.println(tables);
	}
}
