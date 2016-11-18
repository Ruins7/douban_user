package com.entity.lsr.book;

import java.io.Serializable;
import java.util.Date;

public class Book_Author implements Serializable{
	
	private int author_id;
	private String author_name;
	private boolean sex;
	private String country;
	private Date birthday;
	private String simple_desc;
	private String ba1;
	private String ba2;
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSimple_desc() {
		return simple_desc;
	}
	public void setSimple_desc(String simple_desc) {
		this.simple_desc = simple_desc;
	}
	public String getBa1() {
		return ba1;
	}
	public void setBa1(String ba1) {
		this.ba1 = ba1;
	}
	public String getBa2() {
		return ba2;
	}
	public void setBa2(String ba2) {
		this.ba2 = ba2;
	}
	
	

}
