package com.entity.lsr.book;

import java.io.Serializable;

public class Book_Publisher implements Serializable{
	
	private int book_publisher_id;
	private String publisher_name;
	private String location;
	private String bp1;
	private String bp2;
	public int getBook_publisher_id() {
		return book_publisher_id;
	}
	public void setBook_publisher_id(int book_publisher_id) {
		this.book_publisher_id = book_publisher_id;
	}
	public String getPublisher_name() {
		return publisher_name;
	}
	public void setPublisher_name(String publisher_name) {
		this.publisher_name = publisher_name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getBp1() {
		return bp1;
	}
	public void setBp1(String bp1) {
		this.bp1 = bp1;
	}
	public String getBp2() {
		return bp2;
	}
	public void setBp2(String bp2) {
		this.bp2 = bp2;
	}
	
	

}
