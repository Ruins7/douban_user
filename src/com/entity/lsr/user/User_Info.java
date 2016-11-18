package com.entity.lsr.user;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.entity.hyy.city.City;

public class User_Info implements Serializable  {
	
	private int user_id;
	private String username;
	private String pwd;
	private String email;
	private String user_desc;//个人主页名字旁边监剪短的介绍
	private String imgs;
	private int location;
	private Date join_in_time;
	private int status;
	private int role;
	private String simple_intro;//个人主页右边写的东西
	private City city;
	private User_Status user_state;
	
	private boolean gaunzhu;//true--已关注
	
	
	
	public boolean isGaunzhu() {
		return gaunzhu;
	}
	public void setGaunzhu(boolean gaunzhu) {
		this.gaunzhu = gaunzhu;
	}
	public User_Status getUser_state() {
		return user_state;
	}
	public void setUser_state(User_Status user_state) {
		this.user_state = user_state;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	 
	public String getUser_desc() {
		return user_desc;
	}
	public void setUser_desc(String user_desc) {
		this.user_desc = user_desc;
	}
	public String getSimple_intro() {
		return simple_intro;
	}
	public void setSimple_intro(String simple_intro) {
		this.simple_intro = simple_intro;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	 
	 
	 
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public Date getJoin_in_time() {
		return join_in_time;
	}
	public void setJoin_in_time(Date join_in_time) {
		this.join_in_time = join_in_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	 
	 
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	@Override
	public boolean equals(Object obj) {
		 
		 if(obj==null){
			 return false;
		 }
		 if(this == obj){
		      return true;
		 }
		 User_Info user = (User_Info) obj;
		 if(user.getUser_id() != this.getUser_id()){
		 return false;
		 }else{
			 return true;
		 }
		
	}

	
	
}
