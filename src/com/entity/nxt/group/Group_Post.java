package com.entity.nxt.group;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.entity.lsr.user.User_Info;

public class Group_Post {
	
	
	private int id;
	private String post_title;
	private String post_pubtime;
	private int post_author;
	private User_Info user_info;
	private int post_group;
	private String group_content;
	private int readcount;
	private String set_toptime;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public User_Info getUser_info() {
		return user_info;
	}
	public void setUser_info(User_Info user_info) {
		this.user_info = user_info;
	}
	public String getSet_toptime() {
		try {
			Date date = sdf.parse(set_toptime);
			set_toptime = sdf.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return set_toptime;
	}
	public void setSet_toptime(String set_toptime) {
		this.set_toptime = set_toptime;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_pubtime() {
		try {
			Date date = sdf.parse(post_pubtime);
			post_pubtime = sdf.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return post_pubtime;
	}
	public void setPost_pubtime(String post_pubtime) {
		this.post_pubtime = post_pubtime;
	}
	public int getPost_author() {
		return post_author;
	}
	public void setPost_author(int post_author) {
		this.post_author = post_author;
	}
	public int getPost_group() {
		return post_group;
	}
	public void setPost_group(int post_group) {
		this.post_group = post_group;
	}
	public String getGroup_content() {
		return group_content;
	}
	public void setGroup_content(String group_content) {
		this.group_content = group_content;
	}
	public SimpleDateFormat getSdf() {
		return sdf;
	}
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	 
	
	
}
