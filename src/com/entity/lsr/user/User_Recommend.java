package com.entity.lsr.user;

import java.io.Serializable;
import java.util.Date;

public class User_Recommend implements Serializable  {
	
	private int recommend_id;
	private int user;
	private Date time;
	private String my_commons;
	private int type_table;
	private int sub_item_id;
	
	private Item item;
	private User_Info user_info;
	private String recommend2;
	
	
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getType_table() {
		return type_table;
	}
	public void setType_table(int type_table) {
		this.type_table = type_table;
	}
	public int getRecommend_id() {
		return recommend_id;
	}
	public void setRecommend_id(int recommend_id) {
		this.recommend_id = recommend_id;
	}
	 
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getSub_item_id() {
		return sub_item_id;
	}
	public void setSub_item_id(int sub_item_id) {
		this.sub_item_id = sub_item_id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getMy_commons() {
		return my_commons;
	}
	public void setMy_commons(String my_commons) {
		this.my_commons = my_commons;
	}
	 
	public User_Info getUser_info() {
		return user_info;
	}
	public void setUser_info(User_Info user_info) {
		this.user_info = user_info;
	}
	public String getRecommend2() {
		return recommend2;
	}
	public void setRecommend2(String recommend2) {
		this.recommend2 = recommend2;
	}

	
	

}
