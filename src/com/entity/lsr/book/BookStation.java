package com.entity.lsr.book;

import java.util.Date;

import com.entity.lsr.user.User_Info;

/**
 * 书短评，想看的(station->will_do)，正在看的(station->doing)，看过的(station->did)
 * 用户读书状态：正在读，已读，想读，以及打分，用户对某一本书的打分来计算该书的平均分---得出某一类型书的排行榜
 * @author Ruins7
 *
 */
public class BookStation {
		private int bookstation_id;
		private int book_id;
		private Book book;
		private int user_id;
		private User_Info user;
		private String comment;
		private String laber;
		private String station;//状态：想读，在读，已读
		private double score;//用户打分
		private Date date;
		
		
		
		public Book getBook() {
			return book;
		}
		public void setBook(Book book) {
			this.book = book;
		}
		public void setScore(double score) {
			this.score = score;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public User_Info getUser() {
			return user;
		}
		public void setUser(User_Info user) {
			this.user = user;
		}
		public int getBookstation_id() {
			return bookstation_id;
		}
		public void setBookstation_id(int bookstation_id) {
			this.bookstation_id = bookstation_id;
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
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public String getLaber() {
			return laber;
		}
		public void setLaber(String laber) {
			this.laber = laber;
		}
		public String getStation() {
			return station;
		}
		public void setStation(String station) {
			this.station = station;
		}
		public Double getScore() {
			return score;
		}
		public void setScore(Double score) {
			this.score = score;
		}

		
		
		
}

