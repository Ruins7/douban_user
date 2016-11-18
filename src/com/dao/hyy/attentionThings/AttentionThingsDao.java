package com.dao.hyy.attentionThings;

import java.util.List;

import com.entity.lsr.user.User_Info;


/**
 * 关注的dao层
 * @author 胡伊杨
 *
 */
public interface AttentionThingsDao {
	
	/**
	 * 获得参加相同活动的用户
	 * @param user 用户
	 * @return Map<String,List<Object[]>> 以user的id为key的Map
	 */
	public List<User_Info> getSameAttentionUsersArray(String table_name,int inTable_id);
	
	/**
	 * 关注
	 * @param user
	 * @param table_name
	 * @param inTable_id
	 * @param type 操作类型(增加、删除)
	 * @return
	 */
	public int attentionThings(User_Info user, String table_name,int inTable_id,String type);
}
