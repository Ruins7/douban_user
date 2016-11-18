package com.dao.hyy.doActivity.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.dao.hyy.doActivity.DoActivityDao;
import com.dao.lsr.dbutils.dao.DaoOperation;
import com.dao.lsr.dbutils.daoImpl.DaoOperationImpl;
import com.dao.lsr.pool.PoolService;
import com.entity.hyy.activity.OnlineActivity;
import com.entity.lsr.user.User_Info;

/**
 * 同一活动的dao层的实现类
 * @author 胡伊杨
 *
 */
public class DoActivityDaoImpl implements DoActivityDao {

	DaoOperation doimpl = new DaoOperationImpl();
	PoolService pool = PoolService.getInstance();
	QueryRunner qr = new QueryRunner();
	 

	/**
	 * 参加统一活动的用户
	 * @param inTable_id
	 * @param table_name
	 * @param from_table
	 * @return 用户的集合
	 */
	
	public List<User_Info> getSameAttendUsersArray(int inTable_id, String table_name,String from_table) {
		Connection conn = pool.getConnection();
		QueryRunner qr = new QueryRunner();
		List<User_Info> users = new ArrayList<User_Info>();
		String user_sql;
		Object[] params;
		if("offActivity".equals(from_table)){
			user_sql = "select u.user_id from offAttend sa,user_info u where sa.user_id = u.user_id and offActivity_id = ?";
			params=new Object[]{inTable_id};
		}else{
			user_sql = "select u.user_id from sameAttend sa,user_info u where sa.user_id = u.user_id and inTable_id = ? and table_name=?";
			params=new Object[]{inTable_id,table_name};
		}
		
		String users_sql = "select * from user_info where user_id=?";
		try {
			List<Integer> users_id = (List<Integer>) qr.query(conn, user_sql,
					new ColumnListHandler<Integer>("user_id"), params);
			for (Iterator iterator = users_id.iterator(); iterator.hasNext();) {
				Integer integer = (Integer) iterator.next();
				User_Info user = qr.query(conn, users_sql, new BeanHandler(User_Info.class), integer);
				users.add(user);
			}
			pool.releaseConnection(conn);
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * 参加活动(取消参加参加)
	 * @param user
	 * @param table_name
	 * @param inTable_id
	 */
	public void attendActivity(User_Info user, String table_name, int inTable_id) {
		Connection conn = pool.getConnection();
		String sql = "insert into sameAttend(user_id,table_name,inTable_id) values(?,?,?) ";
		System.out.println("---------------");
		try {
			System.out.println("user_id�ǣ�"+user.getUser_id());
			System.out.println("table�ǣ�"+table_name);
			System.out.println("inTable_id�ǣ�"+inTable_id);
			int affectRows = qr.update(conn, sql, user.getUser_id(),table_name,inTable_id);
			System.out.println(affectRows + "行记录受影响");
			pool.releaseConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<OnlineActivity> searchOnLineActivity(String table_name) {	 
		String sql = "select * from " + table_name + " order by start_time DESC";
		List<OnlineActivity> list = doimpl.qrQueryForList(sql, OnlineActivity.class);		 
		return list;
	}

}
