package com.entity.lsr.user;

import java.io.Serializable;

public class User_Status implements Serializable {
	
	private int user_status_id;
	private String status_name;
	private int parent_id;
	private int userstatus1;
	private int userstatus2;
	public int getUser_status_id() {
		return user_status_id;
	}
	public void setUser_status_id(int user_status_id) {
		this.user_status_id = user_status_id;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getUserstatus1() {
		return userstatus1;
	}
	public void setUserstatus1(int userstatus1) {
		this.userstatus1 = userstatus1;
	}
	public int getUserstatus2() {
		return userstatus2;
	}
	public void setUserstatus2(int userstatus2) {
		this.userstatus2 = userstatus2;
	}
	
	
	

}
