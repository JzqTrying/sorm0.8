package com.jzq.sorm.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 封装了JDBC查询常用的操作
 * @author 25689 jzq
 *
 */
public class JDBCUtils {
	/**
	 * 给sql设参
	 * @param ps 预编译的sql语句对象
	 * @param params 参数
	 */
	public static void handleParams(PreparedStatement ps,Object[] params) {
		//给sql传参
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
