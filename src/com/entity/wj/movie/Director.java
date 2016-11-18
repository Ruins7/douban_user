package com.entity.wj.movie;
/**
 * 导演
 * @author 汪进
 *
 */
public class Director {
		private int director_id;
		private String d_name;
		private String nationality;
		private String birthday;
		private String introduce;
		
		public int getDirector_id() {
			return director_id;
		}
		public void setDirector_id(int director_id) {
			this.director_id = director_id;
		}
		public String getD_name() {
			return d_name;
		}
		public void setD_name(String d_name) {
			this.d_name = d_name;
		}
		public String getNationality() {
			return nationality;
		}
		public void setNationality(String nationality) {
			this.nationality = nationality;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getIntroduce() {
			return introduce;
		}
		public void setIntroduce(String introduce) {
			this.introduce = introduce;
		}
		@Override
		public String toString() {
			return "Director [director_id=" + director_id + ", d_name="
					+ d_name + ", nationality=" + nationality + ", birthday="
					+ birthday + ", introduce=" + introduce + "]";
		}
		
		
}
