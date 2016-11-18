package com.entity.wj.movie;

import java.util.Date;

import com.entity.lsr.user.User_Info;

/**
 * 电影短评
 * @author 汪进
 *
 */
public class ShortMovieComment {
		 private int shortmoviecomment_id;
		 private int movie_id;
		 private Movie movie;
		 private int user_id;
		 private User_Info user;
		 private Date date;
		 private String short_c_masg;
		 private double score;
		 private String station;
		 
		 
		public String getStation() {
			return station;
		}
		public void setStation(String station) {
			this.station = station;
		}
		 
		public Movie getMovie() {
			return movie;
		}
		public void setMovie(Movie movie) {
			this.movie = movie;
		}
		public User_Info getUser() {
			return user;
		}
		public void setUser(User_Info user) {
			this.user = user;
		}
		public int getShortmoviecomment_id() {
			return shortmoviecomment_id;
		}
		public void setShortmoviecomment_id(int shortmoviecomment_id) {
			this.shortmoviecomment_id = shortmoviecomment_id;
		}
		
		public int getMovie_id() {
			return movie_id;
		}
		public void setMovie_id(int movie_id) {
			this.movie_id = movie_id;
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
		public String getShort_c_masg() {
			return short_c_masg;
		}
		public void setShort_c_masg(String short_c_masg) {
			this.short_c_masg = short_c_masg;
		}
		public double getScore() {
			return score;
		}
		public void setScore(double score) {
			this.score = score;
		}
		 


		 
		 
		 
}
