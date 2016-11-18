/**
 * 
 */
package com.dao.nxt.group.dbutils.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import com.dao.nxt.group.dbutils.dao.DaoOperation;
import com.dao.nxt.group.pool.PoolService;

/**
 * @ClassName:     DaoOperationImpl.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 *                 dbutils 的方法
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月8日 上午10:02:31 
 */
public class DaoOperationImpl implements DaoOperation{
	
	private Connection conn = null;
	PoolService pool = PoolService.getInstance();
	QueryRunner qr = new QueryRunner();
	
	@Override
	public int qrUpdate(String sql, Object[] params) {
		// 负责insert、update、delete操作
		conn = pool.getConnection();
		int affectRows = 0;
		try {
			affectRows = qr.update( conn , sql , params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			pool.releaseConnection(conn);
		}
		
        return affectRows;
	}

	@Override
	public <T> T qrQueryForOne(String sql, T t, Object[] params) {
		System.out.println("SQL:" + sql);
		conn = pool.getConnection();
		// 查找一个对象
	    T obj = null;
		try {
			Class object = t.getClass();
			obj = (T) object.newInstance();
			obj = (T) qr.query( conn , sql, new BeanHandler(object) , params);			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			pool.releaseConnection(conn);
		}
		if(obj != null)return obj;
		else return null;
	}

	@Override
	public <R> List qrQueryForList(String sql, R r, Object[] params) {
		// 使用list封装查找到的对象集合	
		System.out.println(r.getClass());
		conn = pool.getConnection();
		List list = new ArrayList();
		try {
			list = (List) qr.query( conn, sql, params, new BeanListHandler(r.getClass()));		 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			pool.releaseConnection(conn);
		}
		if(list.size() > 0)return list;
		else return null;
	}
	
	@Override
	public List qrQueryForList(String sql, Class cla) {
		// 使用list封装查找到的对象集合	
		conn = pool.getConnection();
		List list = new ArrayList();
		try {
			list = (List) qr.query( conn  , sql , new BeanListHandler(cla));		 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			pool.releaseConnection(conn);
		} 
		if(list.size() > 0)return list;
		else return null;
	}

	@Override
	public Map qrQueryForMap(String sql, Object[] params) {
		// 使用map封装查找到的对象集合
		conn = pool.getConnection();
		Map map = new HashMap();
		try { 
			map = (Map) qr.query( conn , sql, new MapHandler() , params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			pool.releaseConnection(conn);
		}
		if(map.size() > 0)return map;
		else return null;
	}

	@Override
	public Object[] qrQueryForObject(String sql, Object[] params) {
		// Object[]封装查找到的集合
		conn = pool.getConnection();
		Object[] arr  = null;
		try {
			System.out.println(sql + "    "+params[0]+"  "+params[1]);
			arr = qr.query( conn , sql, new ArrayHandler() , params );		 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			pool.releaseConnection(conn);
		}
		if(arr.length > 0)return arr;
		else return null;
	}

	@Override
	public List<Object[]> qrQueryForResultSet(String sql, Object[] params) {
		// 封装ResultSet结果集
		conn = pool.getConnection();
		List<Object[]> list = new ArrayList();
		try {
		    list = (List<Object[]>)qr.query( conn , sql, new ArrayListHandler(), params);	 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			pool.releaseConnection(conn);
		}
		if(list.size() > 0)return list;
		else return null;
	}

	@Override
	public int[] qrInsertBatch(String sql, Object[][] params) {
		// 批量插入
		conn = pool.getConnection();
		int[] affectRows = null;
		try {
			affectRows = qr.batch( conn , sql, params);	 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			pool.releaseConnection(conn);
		}
		return affectRows;
	}
	 

}
