package com.dao.hyy.doActivity;



import java.util.List;

import com.entity.lsr.user.User_Info;



/**
 * 同一活动的dao层
 * @author 胡伊杨
 *
 */
public interface DoActivityDao {
	/**
	 * 参加统一活动的用户
	 * @param inTable_id
	 * @param table_name
	 * @param from_table
	 * @return 用户的集合
	 */
	public List<User_Info> getSameAttendUsersArray(int inTable_id,String table_name,String from_table);

	/**
	 * 参加活动(取消参加参加)
	 * @param user
	 * @param table_name
	 * @param inTable_id
	 */
	public void attendActivity(User_Info user, String table_name,int inTable_id);
	
}
