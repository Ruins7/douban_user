/**
 * 
 */
package com.dao.lsr.dbutils.daoImpl;

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

import com.dao.lsr.dbutils.dao.DaoOperation;
import com.dao.lsr.pool.PoolService;
import com.entity.lsr.user.PageBean;

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

	@Override
	public <R> PageBean searchByPage(int currentPage, int pageSize, String table_name , R r, int user_id) {
		// 分页查询
		 
		 PageBean pageBean = new PageBean(); 
		 //获取了CurrentPage
		 pageBean.setCurrentPage(currentPage);
		 //获取了PageSize
		 pageBean.setPageSize(pageSize);
		 
		 String sql = "select * from "+ table_name +" where user = ? order by time DESC limit ? , ?";//limit 从第几条开始 （从0开始） ，  取多少条
		 Object[] param = {user_id,(currentPage-1) * pageSize,pageSize};
		 List<R> list =  new DaoOperationImpl().qrQueryForList(sql, r, param);
		 //Data
		 pageBean.setData(list);
		 
		 //获取了TotalRecord
		 String sql2 = "select count(*) from " + table_name + " where user = ?";
		 Object[] param2 = {user_id};
		 List<Object[]> re = new DaoOperationImpl().qrQueryForResultSet(sql2, param2);		 
		 long i = (long) re.get(0)[0];
		 
		 pageBean.setTotalRecord(i);
	     if(pageBean.getData() == null){
	    	 pageBean = null;
	     }
		 return pageBean;
	}
	
	@Override
	public <R> PageBean searchByPageF(int currentPage, int pageSize, String table_name , R r, int user_id) {
		// 分页查询
		 
		PageBean pageBean = new PageBean(); 
		//获取了CurrentPage
		pageBean.setCurrentPage(currentPage);
		//获取了PageSize
		pageBean.setPageSize(pageSize);
		
		String sql = "select * from "+ table_name +" where from_user = ? order by time DESC limit ? , ?";//limit 从第几条开始 （从0开始） ，  取多少条
		Object[] param = {user_id,(currentPage-1) * pageSize,pageSize};
		List<R> list =  new DaoOperationImpl().qrQueryForList(sql, r, param);
		//Data
		pageBean.setData(list);
		
		//获取了TotalRecord
		String sql2 = "select count(*) from " + table_name + " where from_user = ?";
		Object[] param2 = {user_id};
		List<Object[]> re = new DaoOperationImpl().qrQueryForResultSet(sql2, param2);		 
		long i = (long) re.get(0)[0];

		pageBean.setTotalRecord(i);
		if(pageBean.getData() == null){
			pageBean = null;
		}
		return pageBean;
	}
	
	@Override
	public <R> PageBean searchByPageT(int currentPage, int pageSize, String table_name , R r, int user_id) {
		// 分页查询
		 
		PageBean pageBean = new PageBean(); 
		//获取了CurrentPage
		pageBean.setCurrentPage(currentPage);
		//获取了PageSize
		pageBean.setPageSize(pageSize);
		
		String sql = "select * from "+ table_name +" where to_user = ? order by time DESC limit ? , ?";//limit 从第几条开始 （从0开始） ，  取多少条
		Object[] param = {user_id,(currentPage-1) * pageSize,pageSize};
		List<R> list =  new DaoOperationImpl().qrQueryForList(sql, r, param);
		//Data
		pageBean.setData(list);
		
		//获取了TotalRecord
		String sql2 = "select count(*) from " + table_name + " where to_user = ?";
		Object[] param2 = {user_id};
		List<Object[]> re = new DaoOperationImpl().qrQueryForResultSet(sql2, param2);		 
		long i = (long) re.get(0)[0];
	 
		pageBean.setTotalRecord(i);
		if(pageBean.getData() == null){
			pageBean = null;
		}
		return pageBean;
	}
	 
	@Override
	public <R> PageBean searchByPageFT(int currentPage, int pageSize, String table_name , R r, int user_id) {
		// 分页查询	 
		 PageBean pageBean = new PageBean(); 
		 //获取了CurrentPage
		 pageBean.setCurrentPage(currentPage);
		 //获取了PageSize
		 pageBean.setPageSize(pageSize);
		 
		 String sql = "select * from " + table_name + "  b,(select to_user from user_focus where from_user = (select user_id from user_info where user_id = ?)) a where b.user = a.to_user order by time DESC limit ? , ?";//limit 从第几条开始 （从0开始） ，  取多少条
		 Object[] param = {user_id,(currentPage-1) * pageSize,pageSize};
		 List<R> list =  new DaoOperationImpl().qrQueryForList(sql, r, param);
		 //Data
		 pageBean.setData(list);
		 
		 //获取了TotalRecord
		 String sql2 = "select count(*) from " + table_name + " where user = ?";
		 Object[] param2 = {user_id};
		 List<Object[]> re = new DaoOperationImpl().qrQueryForResultSet(sql2, param2);		 
		 long i = (long) re.get(0)[0];
		 
		 pageBean.setTotalRecord(i);
	     if(pageBean.getData() == null){
	    	 pageBean = null;
	     }
		 return pageBean;
	}
	
	@Override
	public <R> PageBean searchByPageFN(int currentPage, int pageSize, String table_name , R r) {
		// 分页查询  无条件分页查询
		 
		PageBean pageBean = new PageBean(); 
		//获取了CurrentPage
		pageBean.setCurrentPage(currentPage);
		//获取了PageSize
		pageBean.setPageSize(pageSize);
		
		String sql = "select * from "+ table_name +" order by time DESC limit ? , ?";//limit 从第几条开始 （从0开始） ，  取多少条
		Object[] param = {(currentPage-1) * pageSize,pageSize};
		List<R> list =  new DaoOperationImpl().qrQueryForList(sql, r, param);
		//Data
		pageBean.setData(list);
		
		//获取了TotalRecord
		String sql2 = "select count(*) from " + table_name;
		List<Object[]> re = new DaoOperationImpl().qrQueryForResultSet(sql2, null);		 
		long i = (long) re.get(0)[0];

		pageBean.setTotalRecord(i);
		if(pageBean.getData() == null){
			pageBean = null;
		}
		return pageBean;
	}
	
	@Override
	public <R> PageBean searchByPageCommon(int currentPage, int pageSize, String table_name , R r, String condition,String condition_value) {
		// 按某一个条件分页查询（通用）
		 
		 PageBean pageBean = new PageBean(); 
		 //获取了CurrentPage
		 pageBean.setCurrentPage(currentPage);
		 //获取了PageSize
		 pageBean.setPageSize(pageSize);
		 
		 String sql = "select * from "+ table_name +" where "+ condition +" = ? order by time DESC limit ? , ?";//limit 从第几条开始 （从0开始） ，  取多少条
		 Object[] param = {condition_value,(currentPage-1) * pageSize,pageSize};
		 List<R> list =  new DaoOperationImpl().qrQueryForList(sql, r, param);
		 //Data
		 pageBean.setData(list);
		 
		 //获取了TotalRecord
		 String sql2 = "select count(*) from " + table_name + " where "+ condition +" = ?";
		 Object[] param2 = {condition_value};
		 List<Object[]> re = new DaoOperationImpl().qrQueryForResultSet(sql2, param2);		 
		 long i = (long) re.get(0)[0];
		 
		 pageBean.setTotalRecord(i);
	     if(pageBean.getData() == null){
	    	 pageBean = null;
	     }
		 return pageBean;
	}
}
