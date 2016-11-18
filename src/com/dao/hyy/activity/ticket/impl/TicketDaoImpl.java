package com.dao.hyy.activity.ticket.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dao.hyy.activity.ticket.TicketDao;
import com.dao.lsr.pool.PoolService;
import com.entity.hyy.activity.Ticket;

public class TicketDaoImpl implements TicketDao {
 
	PoolService pool = PoolService.getInstance();
	QueryRunner qr = new QueryRunner();

	
	@Override
	/**
	 * 查找票
	 * @param id 活动的id
	 * @return
	 */
	public List<Ticket> searchTikects(int id) {
		Connection conn = pool.getConnection();
		String sql ="select * from ticket where Activity_id=?";
		try {
			List<Ticket> tickets = qr.query(conn, sql, new BeanListHandler(Ticket.class), id);
			System.out.println("票务情况"+tickets.get(0).getTicket_desc());
			pool.releaseConnection(conn);
			return tickets;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
