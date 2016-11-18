package com.entity.hyy.activity;

import java.util.Date;

import com.entity.lsr.user.User_Info;

/**
 * 线下活动用户提问发起者
 * @author 胡伊杨
 *
 */
public class OfflineAskActivity {
	private int ask_id;
	private String user_from;
	private User_Info user_f;
	private String user_to;
	private User_Info user_t;
	private String ask_comment;
	private Date time;
	private OffActivity offlineActivity;
	private String activity_id;
	
	
	
	
	 
 
	public String getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(String activity_id) {
		this.activity_id = activity_id;
	}

	public OfflineAskActivity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OfflineAskActivity(int ask_id, User_Info user_f,
			String ask_comment, Date time,
			OffActivity offlineActivity) {
		super();
		this.ask_id = ask_id;
		this.user_f = user_f;
		this.ask_comment = ask_comment;
		this.time = time;
		this.offlineActivity = offlineActivity;
	}

	public int getAsk_id() {
		return ask_id;
	}
	public void setAsk_id(int ask_id) {
		this.ask_id = ask_id;
	}
	 

	public String getUser_from() {
		return user_from;
	}

	public void setUser_from(String user_from) {
		this.user_from = user_from;
	}

	public User_Info getUser_f() {
		return user_f;
	}

	public void setUser_f(User_Info user_f) {
		this.user_f = user_f;
	}

	public String getUser_to() {
		return user_to;
	}

	public void setUser_to(String user_to) {
		this.user_to = user_to;
	}

	public User_Info getUser_t() {
		return user_t;
	}

	public void setUser_t(User_Info user_t) {
		this.user_t = user_t;
	}

	public String getAsk_comment() {
		return ask_comment;
	}
	public void setAsk_comment(String ask_comment) {
		this.ask_comment = ask_comment;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public OffActivity getOfflineActivity() {
		return offlineActivity;
	}
	public void setOfflineActivity(OffActivity offlineActivity) {
		this.offlineActivity = offlineActivity;
	}

}
