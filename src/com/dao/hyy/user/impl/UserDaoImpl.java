package com.dao.hyy.user.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.dao.hyy.user.UserDao;
import com.dao.lsr.pool.PoolService;
import com.entity.lsr.user.User_Info;
/**
 * userdao层
 * @author 胡伊杨
 *
 */
public class UserDaoImpl implements UserDao{

	private static final String URL = "jdbc:mysql://localhost:3306/douban_db_new";
	PoolService pool = PoolService.getInstance();
	QueryRunner qr = new QueryRunner();
	
	@Override
	/**
	 * 查找我的好友
	 * @param my_id
	 * @return
	 */
	public List<User_Info> searchFriend(int my_id) {
		Connection conn = pool.getConnection();
		String sql = "select to_uid from user_focus where from_uid=?";
		try {
			List<Integer> list = qr.query(conn, sql, new ColumnListHandler<Integer>("to_uid") , my_id);
			List<User_Info> user_list = new ArrayList<User_Info>();
			User_Info user = null;
			for (Integer integer : list) {
				user = getUser(integer);
				user_list.add(user);
			}
			pool.releaseConnection(conn);
			return user_list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查找一个user对象
	 * @param user_id
	 * @return
	 */
	public User_Info getUser(int user_id){
		Connection conn = pool.getConnection();
		String sql = "select * from user_info where user_id=?";
		try {
			User_Info user = qr.query(conn, sql, new BeanHandler(User_Info.class), user_id);
			pool.releaseConnection(conn);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
