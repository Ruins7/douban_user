package com.dao.hyy.activity.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.dao.hyy.activity.OfflineAskActivityDao;
import com.dao.lsr.pool.PoolService;

public class OfflineAskActivityDaoImpl implements OfflineAskActivityDao{
	private static final String URL = "jdbc:mysql://localhost:3306/douban_db";
	Connection conn = null;
	QueryRunner qr = null;
	
	public OfflineAskActivityDaoImpl() throws SQLException{
		
		PoolService pool = PoolService.getInstance();
		conn = pool.getConnection();
		qr = new QueryRunner();
	}
	@Override
	/**
	 * 保存一条提问记录
	 * @param activity_id
	 * @param ask_from_id
	 * @param ask_to_id
	 * @param ask_content
	 * @return
	 */
	public int saveAsk(int activity_id, int ask_from_id, String ask_content) {
		String sql = "insert into offlineAskActivity(user_from,ask_Comment,time,activity_id) values(?,?,?,?)";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int affectRows = qr.update(conn, sql, ask_from_id,ask_content,sdf.format(new Date()),activity_id);
			return affectRows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
