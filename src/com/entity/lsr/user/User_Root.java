package com.entity.lsr.user;

import java.io.Serializable;

public class User_Root implements Serializable  {
	
	private int root_id;
	private String root_name;
	private int parent_root_id;
	private int userroot1;
	private int suerroot2;
	
	
	public int getUserroot1() {
		return userroot1;
	}
	public void setUserroot1(int userroot1) {
		this.userroot1 = userroot1;
	}
	public int getSuerroot2() {
		return suerroot2;
	}
	public void setSuerroot2(int suerroot2) {
		this.suerroot2 = suerroot2;
	}
	public int getRoot_id() {
		return root_id;
	}
	public void setRoot_id(int root_id) {
		this.root_id = root_id;
	}
	 
	public String getRoot_name() {
		return root_name;
	}
	public void setRoot_name(String root_name) {
		this.root_name = root_name;
	}
	public int getParent_root_id() {
		return parent_root_id;
	}
	public void setParent_root_id(int parent_root_id) {
		this.parent_root_id = parent_root_id;
	}
	
	

}
