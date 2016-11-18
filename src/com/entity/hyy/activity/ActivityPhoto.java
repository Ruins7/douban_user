package com.entity.hyy.activity;

import java.util.Date;

/**
 * 活动图片
 * @author 胡伊杨
 *
 */
public class ActivityPhoto {
	private int activityPhoto_id;
	private String activityPhoto_name;
	private String activityPhoto_desc;
	private String imgs;
	private int activityAlbum_id;
	private Date activityPhoto_time;
	
	public int getActivityPhoto_id() {
		return activityPhoto_id;
	}
	public void setActivityPhoto_id(int activityPhoto_id) {
		this.activityPhoto_id = activityPhoto_id;
	}
	public String getActivityPhoto_name() {
		return activityPhoto_name;
	}
	public void setActivityPhoto_name(String activityPhoto_name) {
		this.activityPhoto_name = activityPhoto_name;
	}
	public String getActivityPhoto_desc() {
		return activityPhoto_desc;
	}
	public void setActivityPhoto_desc(String activityPhoto_desc) {
		this.activityPhoto_desc = activityPhoto_desc;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public int getActivityAlbum_id() {
		return activityAlbum_id;
	}
	public void setActivityAlbum_id(int activityAlbum_id) {
		this.activityAlbum_id = activityAlbum_id;
	}
	public Date getActivityPhoto_time() {
		return activityPhoto_time;
	}
	public void setActivityPhoto_time(Date activityPhoto_time) {
		this.activityPhoto_time = activityPhoto_time;
	}
	 
}
