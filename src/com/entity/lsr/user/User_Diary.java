package com.entity.lsr.user;

import java.io.Serializable;
import java.util.Date;

public class User_Diary implements Serializable  {
	
	private int diary_id;
	private int user;
	private Date time;	 
	private String title;
	private String content;
	private String imgs;
	private int read_root;
	private User_Info user_info;
	private User_Root root;
	
	
	public int getDiary_id() {
		return diary_id;
	}
	public void setDiary_id(int diary_id) {
		this.diary_id = diary_id;
	}
	 
	 
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	 
	 
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getRead_root() {
		return read_root;
	}
	public void setRead_root(int read_root) {
		this.read_root = read_root;
	}
	public User_Info getUser_info() {
		return user_info;
	}
	public void setUser_info(User_Info user_info) {
		this.user_info = user_info;
	}
	public User_Root getRoot() {
		return root;
	}
	public void setRoot(User_Root root) {
		this.root = root;
	}
	 
	
	
}
