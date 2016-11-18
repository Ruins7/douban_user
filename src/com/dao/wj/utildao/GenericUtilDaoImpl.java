package com.dao.wj.utildao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import com.dao.wj.dbuitil.DaoSupport;

public class GenericUtilDaoImpl <T , PK extends Serializable> extends DaoSupport implements GenericUtilDao<T, Serializable> {
	public Class<T> clazz;  
	private T entity;
    QueryRunner qr=new QueryRunner();
	@SuppressWarnings("unchecked")
	public GenericUtilDaoImpl() { 
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
	public List<Object[]> setpageEntity(T entity) {
		// TODO Auto-generated method stub
		List<Object[]> objects=null;
		String name=clazz.getSimpleName();
		String sql="SELECT*FROM "+name+" RIGHT JOIN "+name+"_type ON "+name+".type="+name+"_type.type";
	  try {
		  objects=qr.query(getConn(), sql, new ArrayListHandler());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  System.out.println(sql);
		return objects;
	}

}
