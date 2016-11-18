package com.entity.lsr.user;

import java.io.Serializable;
import java.util.Date;

public class User_Leave_Message implements Serializable  {
	
	private int message_id;
	private int from_user;
	private int to_user;
	private Date time;
	private String content;
	private User_Info f_user;
	private User_Info t_user;
	
	
	
	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}
	 
	public int getFrom_user() {
		return from_user;
	}
	public void setFrom_user(int from_user) {
		this.from_user = from_user;
	}
	public int getTo_user() {
		return to_user;
	}
	public void setTo_user(int to_user) {
		this.to_user = to_user;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User_Info getF_user() {
		return f_user;
	}
	public void setF_user(User_Info f_user) {
		this.f_user = f_user;
	}
	public User_Info getT_user() {
		return t_user;
	}
	public void setT_user(User_Info t_user) {
		this.t_user = t_user;
	}
	 
	
	
	
	

}
