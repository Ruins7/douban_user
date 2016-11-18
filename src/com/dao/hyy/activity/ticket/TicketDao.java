package com.dao.hyy.activity.ticket;

import java.util.List;

import com.entity.hyy.activity.Ticket;

/**
 * 线下活动的票
 * @author 胡伊杨
 *
 */
public interface TicketDao {
	/**
	 * 查找票
	 * @param id 活动的id
	 * @return
	 */
	public List<Ticket> searchTikects(int id);
}
