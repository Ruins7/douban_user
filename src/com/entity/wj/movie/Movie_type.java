package com.entity.wj.movie;
/**
 * 电影类型
 * @author 汪进
 *
 */
public class Movie_type {
		private int type_id;
		private String type_name;
		private String parent_id;
		public int getType_id() {
			return type_id;
		}
		public void setType_id(int type_id) {
			this.type_id = type_id;
		}
		public String getType_name() {
			return type_name;
		}
		public void setType_name(String type_name) {
			this.type_name = type_name;
		}
		public String getParent_id() {
			return parent_id;
		}
		public void setParent_id(String parent_id) {
			this.parent_id = parent_id;
		}
		@Override
		public String toString() {
			return "Movie_type [type_id=" + type_id + ", type_name="
					+ type_name + ", parent_id=" + parent_id + "]";
		}
		
		
}
