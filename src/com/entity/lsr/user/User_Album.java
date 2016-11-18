package com.entity.lsr.user;

import java.io.Serializable;
import java.util.Date;

public class User_Album implements Serializable  {
	
	private int album_id;
	private int user;
	private Date time;
	private String album_name;
	private String simple_desc;
	private int album_root;
	private User_Info user_info;
	private User_Root root;
	private String imgs;
	

	public int getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	 
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getAlbum_name() {
		return album_name;
	}
	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}
	public String getSimple_desc() {
		return simple_desc;
	}
	public void setSimple_desc(String simple_desc) {
		this.simple_desc = simple_desc;
	}
	public int getAlbum_root() {
		return album_root;
	}
	public void setAlbum_root(int album_root) {
		this.album_root = album_root;
	}
	public User_Info getUser_info() {
		return user_info;
	}
	public void setUser_info(User_Info user_info) {
		this.user_info = user_info;
	}
	public User_Root getRoot() {
		return root;
	}
	public void setRoot(User_Root root) {
		this.root = root;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	 
	
	
 

}
