package com.entity.nxt.group;

import com.entity.lsr.user.User_Info;

public class Group_List {
	private int group_id;
	private Group_Basicinfo group;
	private int user_id;
	private User_Info user;
	private int role_id;
	
	
	
	public Group_Basicinfo getGroup() {
		return group;
	}
	public void setGroup(Group_Basicinfo group) {
		this.group = group;
	}
	public User_Info getUser() {
		return user;
	}
	public void setUser(User_Info user) {
		this.user = user;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}
