package com.entity.wj.movie;
import java.util.Date;

import com.entity.lsr.book.BookStation;
/**
 * 电影
 * @author 汪进
 *
 */
public class Movie {
		private int movie_id;
		private String m_name;
		private String m_describe;
		private Date m_screentime;
		private int director_id;
		private Director m_d;
		private String m_actors;
		private int type_id;
		private Movie_type m_type;
		private String m_district;
		private String m_language;
		private String imgs;
		
		private double avgscore;
		private ShortMovieComment current_user_movie_station;
		
		
		
		public double getAvgscore() {
			return avgscore;
		}
		public void setAvgscore(double avgscore) {
			this.avgscore = avgscore;
		}
		public ShortMovieComment getCurrent_user_movie_station() {
			return current_user_movie_station;
		}
		public void setCurrent_user_movie_station(
				ShortMovieComment current_user_movie_station) {
			this.current_user_movie_station = current_user_movie_station;
		}
		public Director getM_d() {
			return m_d;
		}
		public void setM_d(Director m_d) {
			this.m_d = m_d;
		}
		public Movie_type getM_type() {
			return m_type;
		}
		public void setM_type(Movie_type m_type) {
			this.m_type = m_type;
		}
		public int getMovie_id() {
			return movie_id;
		}
		public void setMovie_id(int movie_id) {
			this.movie_id = movie_id;
		}
		public String getM_name() {
			return m_name;
		}
		public void setM_name(String m_name) {
			this.m_name = m_name;
		}
		public String getM_describe() {
			return m_describe;
		}
		public void setM_describe(String m_describe) {
			this.m_describe = m_describe;
		}
		public Date getM_screentime() {
			return m_screentime;
		}
		public void setM_screentime(Date m_screentime) {
			this.m_screentime = m_screentime;
		}
		
		public int getDirector_id() {
			return director_id;
		}
		public void setDirector_id(int director_id) {
			this.director_id = director_id;
		}
		public String getM_actors() {
			return m_actors;
		}
		public void setM_actors(String m_actors) {
			this.m_actors = m_actors;
		}
	
		
		public int getType_id() {
			return type_id;
		}
		public void setType_id(int type_id) {
			this.type_id = type_id;
		}
		public String getM_district() {
			return m_district;
		}
		public void setM_district(String m_district) {
			this.m_district = m_district;
		}
		public String getM_language() {
			return m_language;
		}
		public void setM_language(String m_language) {
			this.m_language = m_language;
		}
		
		public String getImgs() {
			return imgs;
		}
		public void setImgs(String imgs) {
			this.imgs = imgs;
		}
		@Override
		public String toString() {
			return "Movie [movie_id=" + movie_id + ", m_name=" + m_name
					+ ", m_describe=" + m_describe + ", m_screentime="
					+ m_screentime + ", director_id=" + director_id
					+ ", m_actors=" + m_actors + ", type_id=" + type_id
					+ ", m_district=" + m_district + ", m_language="
					+ m_language + ", imgs=" + imgs + "]";
		}
		
		
       
	
		
}
