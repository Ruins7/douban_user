package com.entity.lsr.user;

import java.io.Serializable;

public class Br_type_table implements Serializable{
	
	private int type_id;
	private String type_table_name;
	
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getType_table_name() {
		return type_table_name;
	}
	public void setType_table_name(String type_table_name) {
		this.type_table_name = type_table_name;
	}
	
	

}
