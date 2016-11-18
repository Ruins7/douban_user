package com.entity.wj.music;
/**
 * 音评
 * @author 汪进
 *
 */
public class MusicComment {
	private int musiccomment_id;
    private int music_id;
    private int user_id;
    private String date;
    private String music_c_masg;
	public int getMusiccomment_id() {
		return musiccomment_id;
	}
	public void setMusiccomment_id(int musiccomment_id) {
		this.musiccomment_id = musiccomment_id;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMusic_c_masg() {
		return music_c_masg;
	}
	public void setMusic_c_masg(String music_c_masg) {
		this.music_c_masg = music_c_masg;
	}

    
    
    
}
