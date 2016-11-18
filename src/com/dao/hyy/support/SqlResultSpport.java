package com.dao.hyy.support;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dao.lsr.pool.PoolService;

/**
 * 获得增删改查的结果
 * @author 胡伊杨
 *
 */
public class SqlResultSpport<T> {
	 PoolService pool = PoolService.getInstance();
 
	Connection conn = pool.getConnection();
	QueryRunner qr = new QueryRunner();;
	
	 
	/**
	 * 查询结果
	 * @return
	 */
	public List<T> selectResultByArray(String table_name,T t,List<Map<String,Object>> list){
		String sql ="select * from "+table_name +" where 1=1";
		System.out.println("list的size:"+list.size());
		List paramList = new ArrayList();
		for (Map<String, Object> map : list) {
			Set<String> key = map.keySet();
			System.out.println("ke_set:"+key);
			for (String string : key) {
				System.out.println("=========进来了");
//				System.out.println("string:"+map.get(string));
				sql = sql + " and "+string +"=? ";
				
				paramList.add(map.get(string));				
			}
		}
		Class cla = t.getClass();
		System.out.println(sql);
		try {
			System.out.println(paramList.toArray());
			List<T> beanList = qr.query(conn, sql, new BeanListHandler(t.getClass()), paramList.toArray());
			System.out.println("beanList的size是"+beanList.size());
			return beanList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
