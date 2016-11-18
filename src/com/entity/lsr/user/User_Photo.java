package com.entity.lsr.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User_Photo implements Serializable  {
	
	private int photo_id;
	private String photo_address;
	private int from_album;
	private User_Album album;
	private Date upload_time;
	private String photo_name;
	private String simple_desc;	 
	private String photo1;
	private String photo2;
	
	private List<User_Commons> pc;
	
	
	 
	public List<User_Commons> getPc() {
		return pc;
	}
	public void setPc(List<User_Commons> pc) {
		this.pc = pc;
	}
	public User_Album getAlbum() {
		return album;
	}
	public void setAlbum(User_Album album) {
		this.album = album;
	}
	public String getPhoto_address() {
		return photo_address;
	}
	public void setPhoto_address(String photo_address) {
		this.photo_address = photo_address;
	}
	public int getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}
	 
	public int getFrom_album() {
		return from_album;
	}
	public void setFrom_album(int from_album) {
		this.from_album = from_album;
	}
	 
	 
	public Date getUpload_time() {
		return upload_time;
	}
	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}
	public String getPhoto_name() {
		return photo_name;
	}
	public void setPhoto_name(String photo_name) {
		this.photo_name = photo_name;
	}
	public String getSimple_desc() {
		return simple_desc;
	}
	public void setSimple_desc(String simple_desc) {
		this.simple_desc = simple_desc;
	}
	 
	public String getPhoto1() {
		return photo1;
	}
	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}
	public String getPhoto2() {
		return photo2;
	}
	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}

	
	
}
