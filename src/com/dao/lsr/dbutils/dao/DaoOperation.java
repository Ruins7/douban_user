/**
 * 
 */
package com.dao.lsr.dbutils.dao;

import java.util.List;
import java.util.Map;

import com.entity.lsr.user.PageBean;

/**
 * @ClassName:     DaoOperation.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 *                 负责qr.update()和qr.query()方法
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月8日 上午9:59:55 
 */
public interface DaoOperation {
	
	  public int qrUpdate(String sql, Object[] params);
	  public <T> T qrQueryForOne(String sql, T t, Object[] params);
	  public List qrQueryForList(String sql, Class cla);
	  public <R> List qrQueryForList(String sql, R r, Object[] params);
	  public Map qrQueryForMap(String sql, Object[] params);
	  public Object[] qrQueryForObject(String sql, Object[] params);
	  public List<Object[]> qrQueryForResultSet(String sql, Object[] params);
	  public int[] qrInsertBatch(String sql, Object[][] params);
	  public <R> PageBean searchByPage(int currentPage, int pageSize, String table_name , R r, int user_id);
	  public <R> PageBean searchByPageF(int currentPage, int pageSize, String table_name , R r, int user_id);
	  public <R> PageBean searchByPageT(int currentPage, int pageSize, String table_name , R r, int user_id);
	  public <R> PageBean searchByPageFT(int currentPage, int pageSize, String table_name , R r, int user_id);
	  public <R> PageBean searchByPageFN(int currentPage, int pageSize, String table_name , R r);
	  public <R> PageBean searchByPageCommon(int currentPage, int pageSize, String table_name , R r,  String condition,String condition_value);
}
