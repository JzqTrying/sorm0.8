package com.jzq.sorm.bean;

import java.util.List;
import java.util.Map;

/**
 * �洢��ṹ����Ϣ
 * @author 25689
 *
 */
public class TableInfo {
	/**
	 * ����
	 */
	private String tname;
	
	/**
	 * �����ֶε���Ϣ
	 */
	private Map<String,ColumnInfo> column;
	
	/**
	 * Ψһ������Ŀǰ����ֻ�ܴ����������ֻ��һ�������������
	 */
	private ColumnInfo onlyPriKey;
	
	/**
	 * ���������������������洢
	 */
	private List<ColumnInfo> prikeys;
	
	public List<ColumnInfo> getPrikeys() {
		return prikeys;
	}

	public void setPrikeys(List<ColumnInfo> prikeys) {
		this.prikeys = prikeys;
	}

	public TableInfo() {
		super();
	}

	public TableInfo(String tname, Map<String, ColumnInfo> column, ColumnInfo onlyPriKey) {
		super();
		this.tname = tname;
		this.column = column;
		this.onlyPriKey = onlyPriKey;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Map<String, ColumnInfo> getColumn() {
		return column;
	}

	public void setColumn(Map<String, ColumnInfo> column) {
		this.column = column;
	}

	public ColumnInfo getOnlyPriKey() {
		return onlyPriKey;
	}

	public void setOnlyPriKey(ColumnInfo onlyPriKey) {
		this.onlyPriKey = onlyPriKey;
	}

	public TableInfo(String tname,List<ColumnInfo> prikeys,Map<String, ColumnInfo> column) {
		super();
		this.tname = tname;
		this.column = column;
		this.prikeys = prikeys;
	}
	
}
