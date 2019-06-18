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
 * 根据配置信息，维持连接对象的管理（增加连接池功能）
 * @author 25689 jzq
 *
 */
public class DBManager {
	/**
	 * 配置信息
	 */
	private static Configuration conf;
	/**
	 * 连接池对象
	 */
	private static DBConnPool pool;
	
	static {//静态代码块
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
		//加载TableContext
		System.out.println(TableContext.class);
	}
		
	/**
	 * 创建新的connection连接
	 * @return
	 */
	public static Connection createConn() {
		try {
			Class.forName(conf.getDriver()/*"com.mysql.jdbc.Driver"*/);
			return DriverManager.getConnection(conf.getUrl(),
					conf.getUser(),conf.getPwd());//目前直接添加连接，后期添加连接池处理，提高效率
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
		/**
		 * 获得connection对象
		 * @return
		 */
		public static Connection getConn() {
			if(pool==null) {
				pool=new DBConnPool();
			}
			return pool.getConnection();
		}
		
		/**
		 * 关闭传入的ResultSet，PreparedStatement，Connection
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
		 * 关闭传入的Statement，Connection
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
		 * 关闭传入的ResultSet，Connection
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
		 * 获得Configuration对象
		 * @return
		 */
		public static Configuration getConf() {
			return conf;
		}
	}
