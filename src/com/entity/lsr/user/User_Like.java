package com.entity.lsr.user;

import java.io.Serializable;
import java.util.Date;

import com.entity.hyy.activity.OffActivity;
import com.entity.lsr.book.Book;
import com.entity.lsr.things.Things;
import com.entity.nxt.group.Group_Post;
import com.entity.wj.movie.Movie;

public class User_Like implements Serializable  {
	
	private int record_id;
	private int user;
	private int item;//喜欢什么---表
	private int item_id;//喜欢的东西在那张表中的id
	private Date time;

	private User_Info User_info;
	private String like2;
	
	private String item_name;//存喜欢的项目的中文类型名称
	private String name;//喜欢的项目的名称
	private String photo_url;//如果是喜欢的照片,东西,相册,活动存图片地址
	private String diary_content;//如果是喜欢的日志存内容
	private String diary_author;//如果是日志or话题存它的创作人
	private String album_photo_owner;//如果是相册or照片存它的创作人
	private String activity_owner;//如果是线上活动室存它的举办人
	private String album_id;//喜欢的相册的id
	
	private Things thing;
	private User_Diary diary;
	private User_Album album;
	private OffActivity activity;
	private User_Photo photo;
	private Group_Post topic;
	private User_Broadcast bro;
	private Book book;
	private Movie movie;
	
	
	
	
	
	
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User_Broadcast getBro() {
		return bro;
	}
	public void setBro(User_Broadcast bro) {
		this.bro = bro;
	}
	public Things getThing() {
		return thing;
	}
	public void setThing(Things thing) {
		this.thing = thing;
	}
	public User_Diary getDiary() {
		return diary;
	}
	public void setDiary(User_Diary diary) {
		this.diary = diary;
	}
	public User_Album getAlbum() {
		return album;
	}
	public void setAlbum(User_Album album) {
		this.album = album;
	}
	 
	public OffActivity getActivity() {
		return activity;
	}
	public void setActivity(OffActivity activity) {
		this.activity = activity;
	}
	public User_Photo getPhoto() {
		return photo;
	}
	public void setPhoto(User_Photo photo) {
		this.photo = photo;
	}
	public Group_Post getTopic() {
		return topic;
	}
	public void setTopic(Group_Post topic) {
		this.topic = topic;
	}
	public String getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(String album_id) {
		this.album_id = album_id;
	}
	public String getActivity_owner() {
		return activity_owner;
	}
	public void setActivity_owner(String activity_owner) {
		this.activity_owner = activity_owner;
	}
	public String getAlbum_photo_owner() {
		return album_photo_owner;
	}
	public void setAlbum_photo_owner(String album_photo_owner) {
		this.album_photo_owner = album_photo_owner;
	}
	public String getDiary_author() {
		return diary_author;
	}
	public void setDiary_author(String diary_author) {
		this.diary_author = diary_author;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	public String getDiary_content() {
		return diary_content;
	}
	public void setDiary_content(String diary_content) {
		this.diary_content = diary_content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRecord_id() {
		return record_id;
	}
	public void setRecord_id(int record_id) {
		this.record_id = record_id;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	 
	public User_Info getUser_info() {
		return User_info;
	}
	public void setUser_info(User_Info user_info) {
		User_info = user_info;
	}
	public String getLike2() {
		return like2;
	}
	public void setLike2(String like2) {
		this.like2 = like2;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	 
	 
	

}
