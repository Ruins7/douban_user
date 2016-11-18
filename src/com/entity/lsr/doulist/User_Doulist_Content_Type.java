package com.entity.lsr.doulist;

import java.io.Serializable;

public class User_Doulist_Content_Type implements Serializable {

	private int content_type_id;
	private String type_name;
	private int parent_id;
	private String dct1;
	private String dct2;
	
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getContent_type_id() {
		return content_type_id;
	}
	public void setContent_type_id(int content_type_id) {
		this.content_type_id = content_type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getDct1() {
		return dct1;
	}
	public void setDct1(String dct1) {
		this.dct1 = dct1;
	}
	public String getDct2() {
		return dct2;
	}
	public void setDct2(String dct2) {
		this.dct2 = dct2;
	}
	 
	
	
}
