package com.entity.lsr.book;

import java.io.Serializable;

public class Book_Type implements Serializable{
	
	private int book_type_id;
	private String type_name;
	private int parent_id;
	private String class1;
	private String class2;
	

	public int getBook_type_id() {
		return book_type_id;
	}
	public void setBook_type_id(int book_type_id) {
		this.book_type_id = book_type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getClass1() {
		return class1;
	}
	public void setClass1(String class1) {
		this.class1 = class1;
	}
	public String getClass2() {
		return class2;
	}
	public void setClass2(String class2) {
		this.class2 = class2;
	}
	
    
	
}
