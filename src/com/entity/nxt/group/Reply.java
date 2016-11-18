package com.entity.nxt.group;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reply {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private int id;
	private int reply_author;
	private int reply_post;
	private String reply_time;
	private String reply_content;
	private int reply_parent;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getReply_author() {
		return reply_author;
	}
	public void setReply_author(int reply_author) {
		this.reply_author = reply_author;
	}
	public int getReply_post() {
		return reply_post;
	}
	public void setReply_post(int reply_post) {
		this.reply_post = reply_post;
	}
	public String getReply_time() {
		try {
			Date date = sdf.parse(reply_time);
			reply_time = sdf.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reply_time;
	}
	public void setReply_time(String reply_time) {
		this.reply_time = reply_time;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public int getReply_parent() {
		return reply_parent;
	}
	public void setReply_parent(int reply_parent) {
		this.reply_parent = reply_parent;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "reply author is~"+reply_author+"reply_post is:"+reply_post+"reply_content is:"+reply_content;
	}
}
