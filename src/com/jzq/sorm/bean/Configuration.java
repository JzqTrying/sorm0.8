package com.jzq.sorm.bean;
/**
 * ����������Ϣ
 * @author 25689 jzq
 *
 */
public class Configuration {
	/**
	 * ������
	 */
	private String driver;
	/**
	 * JDBC��URL
	 */
	private String url;
	/**
	 * ���ݿ���û���
	 */
	private String user;
	/**
	 * ���ݿ�����
	 */
	private String pwd;
	/**
	 * ʹ���ĸ����ݿ�
	 */
	private String usingDB;
	/**
	 * ��Ŀ��Դ��·��
	 */
	private String srcPath;
	/**
	 * ɨ������java��İ���po����˼�ǣ�Persistence object�־û�����
	 */
	private String poPackage;
	/**
	 * ��Ŀʹ�õĲ�ѯ������һ����
	 */
	private String queryClass;
	/**
	 * ���ӳ�����С��������
	 */
	private int poolMinSize;
	/**
	 * ���ӳ�������������
	 */
	private int poolMaxSize;
	
	public Configuration(String driver, String url, String user, String pwd, String usingDB, String srcPath,
			String poPackage, String queryClass, int poolMinSize, int poolMaxSize) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pwd = pwd;
		this.usingDB = usingDB;
		this.srcPath = srcPath;
		this.poPackage = poPackage;
		this.queryClass = queryClass;
		this.poolMinSize = poolMinSize;
		this.poolMaxSize = poolMaxSize;
	}
	public int getPoolMinSize() {
		return poolMinSize;
	}
	public void setPoolMinSize(int poolMinSize) {
		this.poolMinSize = poolMinSize;
	}
	public int getPoolMaxSize() {
		return poolMaxSize;
	}
	public void setPoolMaxSize(int poolMaxSize) {
		this.poolMaxSize = poolMaxSize;
	}
	public String getQueryClass() {
		return queryClass;
	}
	public void setQueryClass(String queryClass) {
		this.queryClass = queryClass;
	}
	public Configuration() {
		super();
	}
	
	public Configuration(String driver, String url, String user, String pwd, String usingDB, String srcPath,
			String poPackage, String queryClass) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pwd = pwd;
		this.usingDB = usingDB;
		this.srcPath = srcPath;
		this.poPackage = poPackage;
		this.queryClass = queryClass;
	}
	public Configuration(String driver, String url, String user, String pwd, String usingDB, String srcPath,
			String poPackage) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pwd = pwd;
		this.usingDB = usingDB;
		this.srcPath = srcPath;
		this.poPackage = poPackage;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUsingDB() {
		return usingDB;
	}
	public void setUsingDB(String usingDB) {
		this.usingDB = usingDB;
	}
	public String getSrcPath() {
		return srcPath;
	}
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}
	public String getPoPackage() {
		return poPackage;
	}
	public void setPoPackage(String poPackage) {
		this.poPackage = poPackage;
	}
	
	
}