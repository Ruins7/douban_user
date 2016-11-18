package com.dao.hyy.attentionThings.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.dao.hyy.attentionThings.AttentionThingsDao;
import com.dao.hyy.user.UserDao;
import com.dao.hyy.user.impl.UserDaoImpl;
import com.dao.lsr.pool.PoolService;
import com.entity.lsr.user.User_Info;


/**
 * 关注的dao层的实现类
 * @author 胡伊杨
 *
 */
public class AttentionThingsDaoImpl implements AttentionThingsDao{
 
	PoolService pool = PoolService.getInstance();
	QueryRunner qr = new QueryRunner();
	
	@Override
	/**
	 * 获得参加相同活动的用户
	 * 
	 *
	 */
	public List<User_Info> getSameAttentionUsersArray(String table_name,
			int inTable_id) {
		Connection conn = pool.getConnection();
		String sql = "select user_id from attentionThings where table_name=? and inTable_id=?";
		List<User_Info> user_list = new ArrayList();
		try {
			UserDao ud = new UserDaoImpl();
			List<Integer> user_idList = qr.query(conn, sql,
					new ColumnListHandler<Integer>("user_id"), table_name,
					inTable_id);
			for (Integer integer : user_idList) {
				user_list.add(ud.getUser(integer));
			}
			pool.releaseConnection(conn);
			return user_list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	/**
	 * 关注
	 * @param user
	 * @param table_name
	 * @param inTable_id
	 * @param type 操作类型(增加、删除)
	 * @return
	 */
	public int attentionThings(User_Info user, String table_name, int inTable_id,String type) {
		Connection conn = pool.getConnection();
		String sql = null;
		if("insert".equals(type)){
			sql = "insert into attentionThings(table_name,inTable_id,user_id) values(?,?,?)";
		}else{
			sql = "delete from attentionThings where table_name=? and inTable_id=? and user_id=?";
		}
		
		try {
			int affectRows = qr.update(conn, sql,table_name,inTable_id,user.getUser_id());
			System.out.println(affectRows +"行记录受影响");
			pool.releaseConnection(conn);
			return affectRows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
