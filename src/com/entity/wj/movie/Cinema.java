package com.entity.wj.movie;
/**
 * 电影院
 * @author 汪进
 * 
 */
public class Cinema {
	private int cinema_id;
	private String cinema;
	private String city;
	private int movie_id;
	private String introduce;
	private String imgs;
	public int getCinema_id() {
		return cinema_id;
	}
	public void setCinema_id(int cinema_id) {
		this.cinema_id = cinema_id;
	}
	public String getCinema() {
		return cinema;
	}
	public void setCinema(String cinema) {
		this.cinema = cinema;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	@Override
	public String toString() {
		return "Cinema [cinema_id=" + cinema_id + ", cinema=" + cinema
				+ ", city=" + city + ", movie_id=" + movie_id + ", introduce="
				+ introduce + ", imgs=" + imgs + "]";
	}
	
	

}
