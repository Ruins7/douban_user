package com.entity.wj.movie;
/**
 * 看过的电影
 * @author 汪进
 *
 */
public class SawMovie {
	    private int sawmovie_id;
		private int user_id;
		private int movie_id;
		private String comment;
		private String labber;
		public int getSawmovie_id() {
			return sawmovie_id;
		}
		public void setSawmovie_id(int sawmovie_id) {
			this.sawmovie_id = sawmovie_id;
		}
		public int getUser_id() {
			return user_id;
		}
		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}

		public int getMovie_id() {
			return movie_id;
		}
		public void setMovie_id(int movie_id) {
			this.movie_id = movie_id;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public String getLabber() {
			return labber;
		}
		public void setLabber(String labber) {
			this.labber = labber;
		}

       
		
		
}
