package com.entity.hyy.activity;

import java.util.Date;
import java.util.List;

import com.entity.lsr.user.User_Info;

/**
 * 线上活动
 * @author 胡伊杨
 *
 */
public class OnlineActivity {
	private int onlineActivity_id;
	private String onlineActivity_title;
	private Date start_time;
	private Date end_time;
	private String onlineactivity_desc;
	private String onlineActivity_type;
	private ActivityAlbum album;
	private List<ActivityPhoto> activityPhotos;
	private String album_id;
	private String onlineactivity_statue;
	private User_Info user;
	private String user_id;
	private String post;
	
	
	
	public List<ActivityPhoto> getActivityPhotos() {
		return activityPhotos;
	}
	public void setActivityPhotos(List<ActivityPhoto> activityPhotos) {
		this.activityPhotos = activityPhotos;
	}
	public String getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(String album_id) {
		this.album_id = album_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public OnlineActivity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OnlineActivity(int onlineActivity_id, String onlineActivity_title,
			Date start_time, Date end_time, String onlineactivity_desc,
			String onlineActivity_type, ActivityAlbum album,
			String onlineactivity_statue, User_Info user) {
		super();
		this.onlineActivity_id = onlineActivity_id;
		this.onlineActivity_title = onlineActivity_title;
		this.start_time = start_time;
		this.end_time = end_time;
		this.onlineactivity_desc = onlineactivity_desc;
		this.onlineActivity_type = onlineActivity_type;
		this.album = album;
		this.onlineactivity_statue = onlineactivity_statue;
		this.user = user;
	}
	public int getOnlineActivity_id() {
		return onlineActivity_id;
	}
	public void setOnlineActivity_id(int onlineActivity_id) {
		this.onlineActivity_id = onlineActivity_id;
	}
	public String getOnlineActivity_title() {
		return onlineActivity_title;
	}
	public void setOnlineActivity_title(String onlineActivity_title) {
		this.onlineActivity_title = onlineActivity_title;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getOnlineactivity_desc() {
		return onlineactivity_desc;
	}
	public void setOnlineactivity_desc(String onlineactivity_desc) {
		this.onlineactivity_desc = onlineactivity_desc;
	}
	public String getOnlineActivity_type() {
		return onlineActivity_type;
	}
	public void setOnlineActivity_type(String onlineActivity_type) {
		this.onlineActivity_type = onlineActivity_type;
	}
	public ActivityAlbum getAlbum() {
		return album;
	}
	public void setAlbum(ActivityAlbum album) {
		this.album = album;
	}
	public String getOnlineactivity_statue() {
		return onlineactivity_statue;
	}
	public void setOnlineactivity_statue(String onlineactivity_statue) {
		this.onlineactivity_statue = onlineactivity_statue;
	}
	public User_Info getUser() {
		return user;
	}
	public void setUser(User_Info user) {
		this.user = user;
	}

}
