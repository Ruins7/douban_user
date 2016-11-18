package com.entity.lsr.user;

import java.io.Serializable;
import java.util.Date;

public class User_Broadcast implements Serializable  {

	private int broadcast_id;
	private int user;//谁原创或者转载的
	private Date time;
	private String content;
	private boolean type;//原创：1 or 转载：0
	private String imgs;
	private int type_table;//如果是转载：转载的项目的类型,原创为空
	private int sub_item_id;//如果是原创：话题(待做)，如果是转载：转载的项目的id
	
	private User_Info user_info;
	private Item item;
	
	
	private String broadcast2;
	
	
	 
	public User_Info getUser_info() {
		return user_info;
	}
	public void setUser_info(User_Info user_info) {
		this.user_info = user_info;
	}
	public int getType_table() {
		return type_table;
	}
	public void setType_table(int type_table) {
		this.type_table = type_table;
	}
	public int getBroadcast_id() {
		return broadcast_id;
	}
	public void setBroadcast_id(int broadcast_id) {
		this.broadcast_id = broadcast_id;
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
	 
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	 
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public int getSub_item_id() {
		return sub_item_id;
	}
	public void setSub_item_id(int sub_item_id) {
		this.sub_item_id = sub_item_id;
	}
	 
	 
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getBroadcast2() {
		return broadcast2;
	}
	public void setBroadcast2(String broadcast2) {
		this.broadcast2 = broadcast2;
	}
	
	
	
	
}
