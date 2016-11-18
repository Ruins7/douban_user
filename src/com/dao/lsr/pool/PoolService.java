package com.dao.lsr.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @ClassName:     PoolService.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 *                 连接池的 懒汉单例模式
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月8日 上午10:02:31 
 */

public class PoolService {
	
	private ConnectionFactory connectionFactory;
	private PoolableConnectionFactory poolableConnectionFactory;
	private ObjectPool<PoolableConnection> connectionPool;
	private GenericObjectPoolConfig config = new GenericObjectPoolConfig();
	private PoolingDriver driver;
	
    private static PoolService poolService = null;
	//private static PoolService poolService = new PoolService();
	
	public static synchronized PoolService getInstance(){
		if(poolService == null){
			poolService = new PoolService();
		}
		return poolService;	 
	}

	private PoolService() {
		 
		config.setMaxIdle(Integer.parseInt(PoolConfig.MAX_IDLE));
		config.setMaxTotal(Integer.parseInt(PoolConfig.POOL_SIZE));	
		config.setMinIdle(Integer.parseInt(PoolConfig.MIN_IDEL));	
		config.setMaxWaitMillis(Integer.parseInt(PoolConfig.MAX_WAIT));	
		connectionFactory = new DriverManagerConnectionFactory(PoolConfig.DATABASE_URL, PoolConfig.USERNAME, PoolConfig.PASSWORD);
		poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
		connectionPool = new GenericObjectPool<>(poolableConnectionFactory, config);	
		try {
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool("databasePool", connectionPool);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		  /*  System.out.println("create : "+connectionPool.getNumActive());
		    System.out.println("create : "+connectionPool.getNumIdle());*/
	}
	
	public synchronized Connection getConnection() {	
		try {		
			GenericObjectPool<Connection>objectPool = (GenericObjectPool<Connection>) driver.getConnectionPool("databasePool");	
			Connection conn = objectPool.borrowObject();		
			/*System.out.println("get : "+connectionPool.getNumActive());
			System.out.println("get : "+connectionPool.getNumIdle());	*/
			return conn;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public synchronized void releaseConnection(Connection connection) {
		try {
			GenericObjectPool<Connection>objectPool = (GenericObjectPool<Connection>) driver.getConnectionPool("databasePool");
			objectPool.returnObject(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		/*System.out.println("release : "+connectionPool.getNumActive());
		System.out.println("release : "+connectionPool.getNumIdle());	*/
	}
	
	public synchronized void releasePool() {
		try {
			ObjectPool<? extends Connection> objectPool = driver.getConnectionPool("databasePool");
			objectPool.clear();
		} catch (Exception e) {
			e.printStackTrace();		
		}
	}

}
