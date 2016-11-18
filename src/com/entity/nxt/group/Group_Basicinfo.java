package com.entity.nxt.group;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Group_Basicinfo {
	
	
	private int id;
	private String  group_name;
	private String group_intro;
	private String imgs;
	private String group_createtime;
	private String group_tag;
	private String admin_nick;
	private String member_nick;
	private int group_type;
	
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getGroup_intro() {
		return group_intro;
	}
	public void setGroup_intro(String group_intro) {
		this.group_intro = group_intro;
	}
	
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	 
	public String getGroup_createtime() {
		return group_createtime;
	}
	public void setGroup_createtime(String group_createtime) {
		this.group_createtime = group_createtime;
	}
	public String getGroup_tag() {
		return group_tag;
	}
	public void setGroup_tag(String group_tag) {
		this.group_tag = group_tag;
	}
	public String getAdmin_nick() {
		return admin_nick;
	}
	public void setAdmin_nick(String admin_nick) {
		this.admin_nick = admin_nick;
	}
	public String getMember_nick() {
		return member_nick;
	}
	public void setMember_nick(String member_nick) {
		this.member_nick = member_nick;
	}
	public int getGroup_type() {
		return group_type;
	}
	public void setGroup_type(int group_type) {
		this.group_type = group_type;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "group_id:"+id+"groupName: "+group_name+"group_img: "+imgs+"group_createtime: "+group_createtime+"group_type: "+group_type+"memberNick: "+member_nick;
	}
	
}
