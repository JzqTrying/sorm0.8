package com.jzq.sorm.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ��װ��JDBC��ѯ���õĲ���
 * @author 25689 jzq
 *
 */
public class JDBCUtils {
	/**
	 * ��sql���
	 * @param ps Ԥ�����sql������
	 * @param params ����
	 */
	public static void handleParams(PreparedStatement ps,Object[] params) {
		//��sql����
		if(params!=null) {
			for(int i=0;i<params.length;i++) {
				try {
					ps.setObject(1+i, params[i]);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
