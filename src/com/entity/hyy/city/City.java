package com.entity.hyy.city;

import java.io.Serializable;

 
 
public class City implements Serializable{
	private int city_id;
	private String city_desc;
	
	
	
	public City() {
	}
	public City(int city_id, String city_desc) {
		super();
		this.city_id = city_id;
		this.city_desc = city_desc;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public String getCity_desc() {
		return city_desc;
	}
	public void setCity_desc(String city_desc) {
		this.city_desc = city_desc;
	}
	
}
