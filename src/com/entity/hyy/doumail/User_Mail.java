package com.entity.hyy.doumail;

import com.entity.lsr.user.User_Info;


/**
 * 用户和用户的信息编号
 * @author 胡伊杨
 *
 */

public class User_Mail {
	private int userMail_id;
	private User_Info user_id_from;
	private User_Info user_id_to;
	
	public User_Mail(){
		
	}

	
	public User_Mail(int userMail_id, User_Info user_id_from,
			User_Info user_id_to) {
		super();
		this.userMail_id = userMail_id;
		this.user_id_from = user_id_from;
		this.user_id_to = user_id_to;
	}


	public int getUserMail_id() {
		return userMail_id;
	}

	public void setUserMail_id(int userMail_id) {
		this.userMail_id = userMail_id;
	}

	public User_Info getUser_id_from() {
		return user_id_from;
	}

	public void setUser_id_from(User_Info user_id_from) {
		this.user_id_from = user_id_from;
	}

	public User_Info getUser_id_to() {
		return user_id_to;
	}

	public void setUser_id_to(User_Info user_id_to) {
		this.user_id_to = user_id_to;
	}

}
