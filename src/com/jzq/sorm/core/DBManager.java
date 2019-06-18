package com.jzq.sorm.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.jzq.sorm.bean.Configuration;
import com.jzq.sorm.pool.DBConnPool;

/**
 * ����������Ϣ��ά�����Ӷ���Ĺ����������ӳع��ܣ�
 * @author 25689 jzq
 *
 */
public class DBManager {
	/**
	 * ������Ϣ
	 */
	private static Configuration conf;
	/**
	 * ���ӳض���
	 */
	private static DBConnPool pool;
	
	static {//��̬�����
		Properties pros=new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		conf=new Configuration();
		conf.setDriver(pros.getProperty("driver"));
		conf.setPoPackage(pros.getProperty("poPackage"));
		conf.setUser(pros.getProperty("user"));
		conf.setPwd(pros.getProperty("pwd"));
		conf.setSrcPath(pros.getProperty("srcPath"));
		conf.setUrl(pros.getProperty("url"));
		conf.setUsingDB(pros.getProperty("usingDB"));
		conf.setQueryClass(pros.getProperty("queryClass"));
		conf.setPoolMinSize(Integer.parseInt(pros.getProperty("poolMinSize")));
		conf.setPoolMaxSize(Integer.parseInt(pros.getProperty("poolMaxSize")));
		//����TableContext
		System.out.println(TableContext.class);
	}
		
	/**
	 * �����µ�connection����
	 * @return
	 */
	public static Connection createConn() {
		try {
			Class.forName(conf.getDriver()/*"com.mysql.jdbc.Driver"*/);
			return DriverManager.getConnection(conf.getUrl(),
					conf.getUser(),conf.getPwd());//Ŀǰֱ��������ӣ�����������ӳش������Ч��
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
		/**
		 * ���connection����
		 * @return
		 */
		public static Connection getConn() {
			if(pool==null) {
				pool=new DBConnPool();
			}
			return pool.getConnection();
		}
		
		/**
		 * �رմ����ResultSet��PreparedStatement��Connection
		 * @param rs
		 * @param ps
		 * @param conn
		 */
		public static void close(ResultSet rs,Statement ps,Connection conn) {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if(ps!=null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//			try {
//				if(conn!=null) {
//					conn.close();
//				}
//			}catch (SQLException e) {
//				e.printStackTrace();
//			}
			pool.close(conn);
		}
		
		/**
		 * �رմ����Statement��Connection
		 * @param ps
		 * @param conn
		 */
		public static void close(Statement ps,Connection conn) {
			try {
				if(ps!=null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//			try {
//				if(conn!=null) {
//					conn.close();
//				}
//			}catch (SQLException e) {
//				e.printStackTrace();
//			}
			pool.close(conn);
		}
		
		public static void close(ResultSet rs,Statement ps) {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if(ps!=null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * �رմ����ResultSet��Connection
		 * @param rs
		 * @param conn
		 */
		public static void close(ResultSet rs,Connection conn) {
			try {
				if(rs!=null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
//			try {
//				if(conn!=null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			pool.close(conn);
		}
		
		/**
		 * ���Configuration����
		 * @return
		 */
		public static Configuration getConf() {
			return conf;
		}
	}
