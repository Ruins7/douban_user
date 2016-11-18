package com.dao.hyy.entity.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import com.dao.hyy.entity.EntityDao;
import com.dao.lsr.pool.PoolService;
/**
 * 获得一个实体类
 * @author 胡伊杨
 *
 * @param <T>
 */
public class EntityDaoImpl<T> implements EntityDao{

 
	PoolService pool = PoolService.getInstance();
	QueryRunner qr = new QueryRunner();
	
	@Override
	public Object[] getEntity(int mailComment_id) {
		Connection conn = pool.getConnection();
		String sql = "select * from link where mailComment_id=? ";
		System.out.println(sql+":::"+mailComment_id);
		 try {
			List<Object[]> object = qr.query(conn, sql, new ArrayListHandler(), mailComment_id);
			System.out.println("-------------"+object);
			Object[] obj = object.get(0);
			pool.releaseConnection(conn);
			return findEntity(obj[1].toString(),Integer.parseInt(obj[2].toString()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object[] findEntity(String table_name,int inTable_id){
		Connection conn = pool.getConnection();
		String sql = "select * from "+table_name+" where "+table_name+"_id=?";
		try {
			Object[] obj = qr.query(conn, sql, new ArrayHandler(), inTable_id);
			pool.releaseConnection(conn);
			return obj;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
