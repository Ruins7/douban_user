package com.dao.wj.commondao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import com.dao.wj.dbuitil.DaoSupport;
/**
 * 
 * @author 汪进
 *电影、图书、音乐排行榜，口碑 (接口实现)
 * @param <T>
 * @param <PK>
 */
public class GenericCommonDaoImpl <T , PK extends Serializable> extends DaoSupport implements GenericCommonDao<T, Serializable> {
	public Class<T> clazz;  
	private T entity;
    QueryRunner qr=new QueryRunner();
	@SuppressWarnings("unchecked")
	public GenericCommonDaoImpl() { 
		clazz = (Class<T>)( (ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		try {
			entity = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Object[]> getRankingByCountEntity(T entity) {
		// TODO Auto-generated method stub
		List<Object[]> objects=null;
		String name=clazz.getSimpleName();
		Object[] params ={0,10};
		String sql="SELECT*FROM "+name+","+name+"list WHERE "+name+"."+name+"_id="+name+"list."+name+"_id"+"  ORDER BY "+name+"list."+name+"_count DESC limit ? , ?";
	  try {
		  objects=qr.query(getConn(), sql, new ArrayListHandler(), params);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  System.out.println(sql);
		return objects;
	}
	
	
	@Override
	public List<Object[]> getRankingByScoreEntity(T entity) {
		// TODO Auto-generated method stub
		List<Object[]> objects=null;
		String name=clazz.getSimpleName();
		Object[] params ={0,10};
		String sql="SELECT "+name+"."+name+"_id,avg("+name+"station.score)from "+name+"station,"+name+" where "+name+"station."+name+"_id="+name+"."+name+"_id and station like 'did' or station like 'doing' group by "+name+"."+name+"_id ORDER BY avg("+name+"station.score)desc limit ? , ?";
		
		try {
			objects=qr.query(getConn(), sql, new ArrayListHandler(), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objects;
	}

}
