package com.dao.hyy.user;

import java.util.List;

import com.entity.lsr.user.User_Info;


/**
 * 获得user的dao层
 * @author Administrator
 *
 */
public interface UserDao {
	
	/**
	 * 查找我的好友
	 * @param my_id
	 * @return
	 */
	public List<User_Info> searchFriend(int my_id);
	
	/**
	 * 查找一个user对象
	 * @param user_id
	 * @return
	 */
	public User_Info getUser(int user_id);
}
