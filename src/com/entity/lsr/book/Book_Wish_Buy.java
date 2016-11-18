package com.entity.lsr.book;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.entity.lsr.user.User_Info;

public class Book_Wish_Buy implements Serializable {
	
	private int wish_id;
	private int user;
	private int book;
	private Book b;
	private Date time;
	private String bwb1;
	private String bwb2;
	
	
	
	public Book getB() {
		return b;
	}
	public void setB(Book b) {
		this.b = b;
	}
	public int getWish_id() {
		return wish_id;
	}
	public void setWish_id(int wish_id) {
		this.wish_id = wish_id;
	}
	 
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getBook() {
		return book;
	}
	public void setBook(int book) {
		this.book = book;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getBwb1() {
		return bwb1;
	}
	public void setBwb1(String bwb1) {
		this.bwb1 = bwb1;
	}
	public String getBwb2() {
		return bwb2;
	}
	public void setBwb2(String bwb2) {
		this.bwb2 = bwb2;
	}

	
	
}
