package com.dao.hyy.activity;

public interface OfflineAskActivityDao {
	/**
	 * 保存一条提问记录
	 * @param activity_id
	 * @param ask_from_id
	 * @param ask_to_id
	 * @param ask_content
	 * @return
	 */
	public int saveAsk(int activity_id,int ask_from_id,String ask_content);
}
