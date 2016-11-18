package com.entity.lsr.doulist;

import java.io.Serializable;
import java.util.Date;

import com.entity.lsr.user.User_Info;

public class User_Focus_Doulist implements Serializable {

	
	private int focus_id;
	private int doulist;
	private User_Doulist u_doulist;
	private int focus_user;
	private User_Info f_user;//谁关注的此豆列
	private User_Info belong_who;//此豆列是谁创建的
	private Date time;
	private User_Doulist_Content_Type content_type_table;
	
	private String focusdoulist1;
	private String focusdoulist2;
	
	
	
	public User_Info getBelong_who() {
		return belong_who;
	}
	public void setBelong_who(User_Info belong_who) {
		this.belong_who = belong_who;
	}
	public User_Doulist_Content_Type getContent_type_table() {
		return content_type_table;
	}
	public void setContent_type_table(User_Doulist_Content_Type content_type_table) {
		this.content_type_table = content_type_table;
	}
	public User_Doulist getU_doulist() {
		return u_doulist;
	}
	public void setU_doulist(User_Doulist u_doulist) {
		this.u_doulist = u_doulist;
	}
	public User_Info getF_user() {
		return f_user;
	}
	public void setF_user(User_Info f_user) {
		this.f_user = f_user;
	}
	public int getFocus_id() {
		return focus_id;
	}
	public void setFocus_id(int focus_id) {
		this.focus_id = focus_id;
	}
	 
	public int getDoulist() {
		return doulist;
	}
	public void setDoulist(int doulist) {
		this.doulist = doulist;
	}
	public int getFocus_user() {
		return focus_user;
	}
	public void setFocus_user(int focus_user) {
		this.focus_user = focus_user;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getFocusdoulist1() {
		return focusdoulist1;
	}
	public void setFocusdoulist1(String focusdoulist1) {
		this.focusdoulist1 = focusdoulist1;
	}
	public String getFocusdoulist2() {
		return focusdoulist2;
	}
	public void setFocusdoulist2(String focusdoulist2) {
		this.focusdoulist2 = focusdoulist2;
	}
	
	
	
}
