package com.entity.hyy.activity;



import java.util.Date;
import java.util.List;

import com.entity.hyy.city.City;
import com.entity.lsr.user.User_Info;


/**
 * 线下活动
 * @author 胡伊杨
 *
 */
public class OffActivity {
	private int offactivity_id;
	private String offactivity_title;
	private Date start_time;
	private Date end_time;
	private City city;
	private String city_id;
	private String offactivity_desc;
	private List<Ticket> tickets;
	private User_Info user;
	private String user_id;
	private ActivityAlbum activityAlbum;
	private String offactivity_statue;
	private String offactivity_type;
	private Activity_type at;

	private Date submit_time;
	private String position;//活动地点
	private String everyday;
	private String imgs;
	private List<User_Info> attendPerson;
	private List<User_Info> attentionPerson;
	
	public OffActivity(){}

	
	
	
	public Activity_type getAt() {
		return at;
	}




	public void setAt(Activity_type at) {
		this.at = at;
	}




	public String getUser_id() {
		return user_id;
	}




	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}




	public String getCity_id() {
		return city_id;
	}




	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}




 



	public OffActivity(int offactivity_id, String offactivity_title,
			Date start_time, Date end_time, City city, String offactivity_desc,
			List<Ticket> tickets, User_Info user, ActivityAlbum activityAlbum,
			String offactivity_statue,
			String offActivity_type, Date submit_time, String position,
			String everyday, String imgs) {
		super();
		this.offactivity_id = offactivity_id;
		this.offactivity_title = offactivity_title;
		this.start_time = start_time;
		this.end_time = end_time;
		this.city = city;
		this.offactivity_desc = offactivity_desc;
		this.tickets = tickets;
		this.user = user;
		this.activityAlbum = activityAlbum;
		this.offactivity_statue = offactivity_statue;
		this.offactivity_type = offactivity_type;
		this.submit_time = submit_time;
		this.position = position;
		this.everyday = everyday;
		this.imgs = imgs;
	}

	public int getOffactivity_id() {
		return offactivity_id;
	}

	public void setOffactivity_id(int offactivity_id) {
		this.offactivity_id = offactivity_id;
	}

	public String getOffactivity_title() {
		return offactivity_title;
	}

	public void setOffactivity_title(String offactivity_title) {
		this.offactivity_title = offactivity_title;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getOffactivity_desc() {
		return offactivity_desc;
	}

	public void setOffactivity_desc(String offactivity_desc) {
		this.offactivity_desc = offactivity_desc;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public User_Info getUser() {
		return user;
	}

	public void setUser(User_Info user) {
		this.user = user;
	}

	public ActivityAlbum getActivityAlbum() {
		return activityAlbum;
	}

	public void setActivityAlbum(ActivityAlbum activityAlbum) {
		this.activityAlbum = activityAlbum;
	}

	public String getOffactivity_statue() {
		return offactivity_statue;
	}

	public void setOffactivity_statue(String offactivity_statue) {
		this.offactivity_statue = offactivity_statue;
	}

 

	 

	public String getOffactivity_type() {
		return offactivity_type;
	}




	public void setOffactivity_type(String offactivity_type) {
		this.offactivity_type = offactivity_type;
	}




	public Date getSubmit_time() {
		return submit_time;
	}

	public void setSubmit_time(Date submit_time) {
		this.submit_time = submit_time;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEveryday() {
		return everyday;
	}

	public void setEveryday(String everyday) {
		this.everyday = everyday;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImg(String imgs) {
		this.imgs = imgs;
	}


	public List<User_Info> getAttendPerson() {
		return attendPerson;
	}

	public void setAttendPerson(List<User_Info> attendPerson) {
		this.attendPerson = attendPerson;
	}

	public List<User_Info> getAttentionPerson() {
		return attentionPerson;
	}

	public void setAttentionPerson(List<User_Info> attentionPerson) {
		this.attentionPerson = attentionPerson;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

}
