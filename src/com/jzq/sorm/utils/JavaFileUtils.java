package com.jzq.sorm.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jzq.sorm.bean.ColumnInfo;
import com.jzq.sorm.bean.JavaFieldGetSet;
import com.jzq.sorm.bean.TableInfo;
import com.jzq.sorm.core.DBManager;
import com.jzq.sorm.core.MySqlTypeConvertor;
import com.jzq.sorm.core.TableContext;
import com.jzq.sorm.core.TypeConvertor;

/**
 * ��װ������java�ļ���Դ���룩���õĲ���
 * @author 25689
 *
 */
public class JavaFileUtils {
	/**
	 * �����ֶ���Ϣ����java������Ϣ���磺varchar-->private String username;�Լ���Ӧ��set��get����Դ��
	 * @param column �ֶ���Ϣ
	 * @param convertor ����ת����
	 * @return java���Ժ�set/get����
	 */
	public static JavaFieldGetSet createFieldGetSetSRC(ColumnInfo column,TypeConvertor convertor) {
		JavaFieldGetSet jfgs=new JavaFieldGetSet();
		
		String javaFieldType=convertor.databaseType2JavaType(column.getDataType());
		
		jfgs.setFieldInfo("\tprivate"+" "+javaFieldType+" "+column.getName()+";\n");
		
		//public String getUsername(){return username;}
		StringBuilder getSrc=new StringBuilder();
		getSrc.append("\tpublic "+javaFieldType+" get"+StringUtils.firstChar2UpperCase(column.getName())+"(){\n");
		getSrc.append("\treturn "+column.getName()+";\n");
		getSrc.append("\t}\n");
		jfgs.setGetInfo(getSrc.toString());
		
		//public void setUsername(String username){this.username=username;}
		StringBuilder setSrc=new StringBuilder();
		setSrc.append("\tpublic void"+" set"+StringUtils.firstChar2UpperCase(column.getName())+"(");
		setSrc.append(javaFieldType+" "+column.getName()+"){\n");
		setSrc.append("\tthis."+column.getName()+"="+column.getName()+";\n");
		setSrc.append("\t}\n");
		jfgs.setSetInfo(setSrc.toString());
		
		return jfgs;
	}
	
	/**
	 * ���ݱ���Ϣ����java���Դ����
	 * @param tableInfo ����Ϣ
	 * @param convertor ��������ת����
	 * @return java���Դ����
	 */
	public static String createJavaSrc(TableInfo tableInfo,TypeConvertor convertor) {
		Map<String,ColumnInfo> column=tableInfo.getColumn();
		List<JavaFieldGetSet> javaFields=new ArrayList<JavaFieldGetSet>();
				
		for(ColumnInfo c:column.values()) {
			javaFields.add(createFieldGetSetSRC(c,convertor));
		}
		
		StringBuilder src=new StringBuilder();
		//����package���
		src.append("package "+DBManager.getConf().getPoPackage()+";\n\n");
		//����import���
		src.append("import java.sql.*;\n");
		src.append("import java.util.*;\n\n");
		//�����������
		src.append("public class "+StringUtils.firstChar2UpperCase(tableInfo.getTname())+" {\n\n");		
		//���������б�
		for(JavaFieldGetSet f:javaFields) {
			src.append(f.getFieldInfo());
		}
		src.append("\n\n");
		//����get�����б�
		for(JavaFieldGetSet f:javaFields) {
			src.append(f.getGetInfo());
		}
		//����set�����б�
		for(JavaFieldGetSet f:javaFields) {
			src.append(f.getSetInfo());
		}
		//���������
		src.append("}\n");
		return src.toString();
	}
	
	public static void createJavaPOFile(TableInfo tableInfo,TypeConvertor convertor) {
		String src=createJavaSrc(tableInfo,convertor);
		
		String srcPath=DBManager.getConf().getSrcPath()+"\\";
		
		String packagePath=DBManager.getConf().getPoPackage().replaceAll("\\.", "\\\\");
		
		File f=new File(srcPath+packagePath);

		
		if(!f.exists()) {//���ָ��Ŀ¼�����ڣ�������û�����
			f.mkdirs();
		}
		
		BufferedWriter bw=null;
		try {
			bw=new BufferedWriter(new FileWriter(f.getPath()+"/"+StringUtils.firstChar2UpperCase(tableInfo.getTname()+".java")));
			bw.write(src);
			System.out.println("������"+tableInfo.getTname()+"��Ӧ��java��:"+StringUtils.firstChar2UpperCase(tableInfo.getTname()+".java"));
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(bw!=null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
//		ColumnInfo ci=new ColumnInfo("id","int",0);
//		JavaFieldGetSet f=createFieldGetSetSRC(ci,new MySqlTypeConvertor());
//		System.out.println(f);
		
		Map<String,TableInfo> map=TableContext.tables;
		for(TableInfo tableInfo:map.values()) {
		createJavaPOFile(tableInfo,new MySqlTypeConvertor());
		}
		System.out.println("����pojo�ɹ�");
	}
}
