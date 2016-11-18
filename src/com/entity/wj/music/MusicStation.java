package com.entity.wj.music;
/**
 * 音乐短评
 * @author 汪进
 *
 */
public class MusicStation {
		private int musicstation_id;
		private int music_id;
		private int user_id;
		private String comment;
		private String laber;
		private String station;
		private int score;
		public int getMusicstation_id() {
			return musicstation_id;
		}
		public void setMusicstation_id(int musicstation_id) {
			this.musicstation_id = musicstation_id;
		}
		public int getMusic_id() {
			return music_id;
		}
		public void setMusic_id(int music_id) {
			this.music_id = music_id;
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
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		
		
		
}

