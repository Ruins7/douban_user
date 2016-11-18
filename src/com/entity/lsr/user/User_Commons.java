/**
 * 
 */
package com.entity.lsr.user;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName:     Commons.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月13日 下午5:42:11 
 */
public class User_Commons implements Serializable {
	private int commons_id;
	private int from_user;
	private User_Info f_user;
	private Date time;
	private String content;
	private int item;
	private int item_id;
	
	
	private int commons1;
	private int commons2;
	
	
	
	public User_Info getF_user() {
		return f_user;
	}
	public void setF_user(User_Info f_user) {
		this.f_user = f_user;
	}
	public int getCommons_id() {
		return commons_id;
	}
	public void setCommons_id(int commons_id) {
		this.commons_id = commons_id;
	}
	public int getFrom_user() {
		return from_user;
	}
	public void setFrom_user(int from_user) {
		this.from_user = from_user;
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
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getCommons1() {
		return commons1;
	}
	public void setCommons1(int commons1) {
		this.commons1 = commons1;
	}
	public int getCommons2() {
		return commons2;
	}
	public void setCommons2(int commons2) {
		this.commons2 = commons2;
	}
	
	

}
