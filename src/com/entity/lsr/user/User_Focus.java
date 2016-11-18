package com.entity.lsr.user;

import java.io.Serializable;
import java.util.Date;

public class User_Focus implements Serializable  {
	
	private int record_id;
	private int from_user;
	private int to_user;
	private Date time;
	private int root;
	private String userfocus1;
	private String userfocus2;
	public int getRecord_id() {
		return record_id;
	}
	public void setRecord_id(int record_id) {
		this.record_id = record_id;
	}
	 
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	 
	public int getFrom_user() {
		return from_user;
	}
	public void setFrom_user(int from_user) {
		this.from_user = from_user;
	}
	public int getTo_user() {
		return to_user;
	}
	public void setTo_user(int to_user) {
		this.to_user = to_user;
	}
	public int getRoot() {
		return root;
	}
	public void setRoot(int root) {
		this.root = root;
	}
	public String getUserfocus1() {
		return userfocus1;
	}
	public void setUserfocus1(String userfocus1) {
		this.userfocus1 = userfocus1;
	}
	public String getUserfocus2() {
		return userfocus2;
	}
	public void setUserfocus2(String userfocus2) {
		this.userfocus2 = userfocus2;
	}
	
	
	
}
