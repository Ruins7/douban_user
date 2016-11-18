package com.entity.lsr.doulist;

import java.io.Serializable;
import java.util.Date;

public class User_Doulist_Content implements Serializable {
	
	private int record_id;
	private int belongto_doulist;
	private Date time;
	private int item_id;
	private String simple_common;
	private int content2;
	
	
	
	public String getSimple_common() {
		return simple_common;
	}
	public void setSimple_common(String simple_common) {
		this.simple_common = simple_common;
	}
	public int getRecord_id() {
		return record_id;
	}
	public void setRecord_id(int record_id) {
		this.record_id = record_id;
	}
	public int getBelongto_doulist() {
		return belongto_doulist;
	}
	public void setBelongto_doulist(int belongto_doulist) {
		this.belongto_doulist = belongto_doulist;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	 
	public int getContent2() {
		return content2;
	}
	public void setContent2(int content2) {
		this.content2 = content2;
	}
	
	

}
