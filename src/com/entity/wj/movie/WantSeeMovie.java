package com.entity.wj.movie;
/**
 * 想看的电影
 * @author 汪进
 *
 */
public class WantSeeMovie {
	    private int wantseemovie_id;
		private int user_id;
		private int movie_id;
		private String comment;
		private String laber;
		public int getWantseemovie_id() {
			return wantseemovie_id;
		}
		public void setWantseemovie_id(int wantseemovie_id) {
			this.wantseemovie_id = wantseemovie_id;
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
		public String getLaber() {
			return laber;
		}
		public void setLaber(String laber) {
			this.laber = laber;
		}


		
		
		
		
}
