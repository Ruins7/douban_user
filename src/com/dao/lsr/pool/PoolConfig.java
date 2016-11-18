package com.dao.lsr.pool;

import java.io.IOException;
import java.util.Properties;

public class PoolConfig {

	public static Properties prop = new Properties();
	static{
		try {
			prop.load(PoolConfig.class.getResourceAsStream("/mysql_poolconfig.properties"));
		} catch (IOException e) {
			System.out.println("DBCP配置文件读取失败！");
			e.printStackTrace();
		}
	}
	public static final String DRIVER_CLASS_NAME = prop.getProperty("driverClassName"); 
	public static final String DATABASE_URL = prop.getProperty("url");
	public static final String USERNAME = prop.getProperty("username");
	public static final String PASSWORD = prop.getProperty("password");
	public static final String MAX_ACTIVE = prop.getProperty("maxActive");
	public static final String MAX_IDLE = prop.getProperty("maxIdle");
	public static final String MAX_WAIT = prop.getProperty("maxWait");
	public static final String CONNECTION_PROPERTIES = prop.getProperty("connectionProperties");
	public static final String DEFAULT_AUTO_COMMIT = prop.getProperty("defaultAutoCommit");
	public static final String POOL_SIZE = prop.getProperty("poolSize");
	public static final String MIN_IDEL = prop.getProperty("minIdle");
 
}
