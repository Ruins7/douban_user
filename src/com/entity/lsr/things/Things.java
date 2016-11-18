package com.entity.lsr.things;

import java.io.Serializable;
import java.util.Date;

import com.entity.lsr.user.User_Info;

public class Things implements Serializable {

	private int things_id;
	private String things_name;
	private int from_user;
	private Date time;
	private String imgs;
	private int type;
	private String simple_desc;
	
	private User_Info f_user;
	private Things_Type type_table;
	 
	 
	public int getThings_id() {
		return things_id;
	}
	public void setThings_id(int things_id) {
		this.things_id = things_id;
	}
	public String getThings_name() {
		return things_name;
	}
	public void setThings_name(String things_name) {
		this.things_name = things_name;
	}
	public int getFrom_user() {
		return from_user;
	}
	public void setFrom_user(int from_user) {
		this.from_user = from_user;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	 
	public int getType() {
		return type;
	}
	public String getSimple_desc() {
		return simple_desc;
	}
	public void setSimple_desc(String simple_desc) {
		this.simple_desc = simple_desc;
	}
	public User_Info getF_user() {
		return f_user;
	}
	public void setF_user(User_Info f_user) {
		this.f_user = f_user;
	}
	public Things_Type getType_table() {
		return type_table;
	}
	public void setType_table(Things_Type type_table) {
		this.type_table = type_table;
	}
	 
	
	
	
}
