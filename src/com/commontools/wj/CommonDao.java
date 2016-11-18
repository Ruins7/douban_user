package com.commontools.wj;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import com.dao.wj.dbuitil.DaoSupport;


public class CommonDao extends DaoSupport implements CommonInterface {
	QueryRunner qr=new QueryRunner(); 
	//排行榜，口碑
	@Override
	public List<Object[]> EntitySort(String sql) {
		// TODO Auto-generated method stub
		List<Object[]> objects=null;
		Object[] params={0,10};
		
		try {
			objects=qr.query(getConn(), sql, new ArrayListHandler(), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objects;
	}




}
