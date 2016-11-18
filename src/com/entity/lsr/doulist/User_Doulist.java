package com.entity.lsr.doulist;

import java.io.Serializable;
import java.util.Date;

import com.entity.lsr.user.User_Info;

public class User_Doulist implements Serializable {
	
	private int doulist_id;
	private int from_user;
	private String list_name;
	private int content_type;
	private String imgs;
	private Date time;
	private User_Info f_user;
	private User_Doulist_Content_Type content_type_table;
	 
	 
	public String getList_name() {
		return list_name;
	}
	public void setList_name(String list_name) {
		this.list_name = list_name;
	}
	public int getContent_type() {
		return content_type;
	}
	public void setContent_type(int content_type) {
		this.content_type = content_type;
	}
	public int getDoulist_id() {
		return doulist_id;
	}
	public void setDoulist_id(int doulist_id) {
		this.doulist_id = doulist_id;
	}
	 
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	 
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getFrom_user() {
		return from_user;
	}
	public void setFrom_user(int from_user) {
		this.from_user = from_user;
	}
	public User_Info getF_user() {
		return f_user;
	}
	public void setF_user(User_Info f_user) {
		this.f_user = f_user;
	}
	public User_Doulist_Content_Type getContent_type_table() {
		return content_type_table;
	}
	public void setContent_type_table(User_Doulist_Content_Type content_type_table) {
		this.content_type_table = content_type_table;
	}
	 
	
	
	
}
