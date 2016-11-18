package com.entity.hyy.activity;

import java.util.List;

/**
 * 活动的相册
 * @author 胡伊杨
 *
 */
public class ActivityAlbum {
	private int activityAlbum_id;
	private String activityAlbum_name;
	private ActivityPhoto photo;
	private List<ActivityPhoto> activityPhotos;
	
	
	
	
	public ActivityAlbum() {}
	public ActivityAlbum(int activityAlbum_id, String activityAlbum_name,
			ActivityPhoto photo, List<ActivityPhoto> activityPhotos) {
		super();
		this.activityAlbum_id = activityAlbum_id;
		this.activityAlbum_name = activityAlbum_name;
		this.photo = photo;
		this.activityPhotos = activityPhotos;
	}
	public int getActivityAlbum_id() {
		return activityAlbum_id;
	}
	public void setActivityAlbum_id(int activityAlbum_id) {
		this.activityAlbum_id = activityAlbum_id;
	}
	public String getActivityAlbum_name() {
		return activityAlbum_name;
	}
	public void setActivityAlbum_name(String activityAlbum_name) {
		this.activityAlbum_name = activityAlbum_name;
	}
	public List<ActivityPhoto> getActivityPhotos() {
		return activityPhotos;
	}
	public void setActivityPhotos(List<ActivityPhoto> activityPhotos) {
		this.activityPhotos = activityPhotos;
	}
	public ActivityPhoto getPhoto() {
		return photo;
	}
	public void setPhoto(ActivityPhoto photo) {
		this.photo = photo;
	}
	
	
}
