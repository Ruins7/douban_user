package com.entity.lsr.book;

import java.util.Date;

import com.entity.lsr.user.User_Info;

/**
 * 书评
 * @author 汪进
 *
 */
public class BookComment {
	private int bookcomment_id;
	private int book_id;
	private Book book;
	private int user_id;
	private User_Info user;
	private Date date;
	private String masg;
	
	public int getBookcomment_id() {
		return bookcomment_id;
	}
	public void setBookcomment_id(int bookcomment_id) {
		this.bookcomment_id = bookcomment_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	 
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User_Info getUser() {
		return user;
	}
	public void setUser(User_Info user) {
		this.user = user;
	}
	public String getMasg() {
		return masg;
	}
	public void setMasg(String masg) {
		this.masg = masg;
	}
	

	
	
	
}
