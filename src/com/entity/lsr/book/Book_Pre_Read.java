package com.entity.lsr.book;

import java.io.Serializable;

public class Book_Pre_Read implements Serializable{
	
	private int bpr_id;
	private int book;
	private String pre_read_content;
	private String bpr1; 
	private String bpr2;
	public int getBpr_id() {
		return bpr_id;
	}
	public void setBpr_id(int bpr_id) {
		this.bpr_id = bpr_id;
	}
	 
	 
	public int getBook() {
		return book;
	}
	public void setBook(int book) {
		this.book = book;
	}
	public String getPre_read_content() {
		return pre_read_content;
	}
	public void setPre_read_content(String pre_read_content) {
		this.pre_read_content = pre_read_content;
	}
	public String getBpr1() {
		return bpr1;
	}
	public void setBpr1(String bpr1) {
		this.bpr1 = bpr1;
	}
	public String getBpr2() {
		return bpr2;
	}
	public void setBpr2(String bpr2) {
		this.bpr2 = bpr2;
	}
	
	

}
