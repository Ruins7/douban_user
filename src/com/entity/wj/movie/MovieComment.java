package com.entity.wj.movie;

import java.util.Date;

import com.entity.lsr.user.User_Info;

/**
 * 影评
 * @author 汪进
 *
 */
public class MovieComment {
		private int moviecomment_id;
		private int movie_id;
		private Movie movie;		
		private int user_id;
		private User_Info user;
		private Date c_date;
		private String c_masg;
		
		
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
		public int getMoviecomment_id() {
			return moviecomment_id;
		}
		public void setMoviecomment_id(int moviecomment_id) {
			this.moviecomment_id = moviecomment_id;
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
		public Date getC_date() {
			return c_date;
		}
		public void setC_date(Date c_date) {
			this.c_date = c_date;
		}
		public String getC_masg() {
			return c_masg;
		}
		public void setC_masg(String c_masg) {
			this.c_masg = c_masg;
		}
		@Override
		public String toString() {
			return "MovieComment [moviecomment_id=" + moviecomment_id
					+ ", movie_id=" + movie_id + ", user_id=" + user_id
					+ ", c_date=" + c_date + ", c_masg=" + c_masg + "]";
		}

     
		
  	
}
