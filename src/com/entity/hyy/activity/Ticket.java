package com.entity.hyy.activity;

/**
 * 线下活动的票
 * @author 胡伊杨
 *
 */
public class Ticket {

	private int ticket_id;
	private double ticket_price;
	private String ticket_desc;
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
	public double getTicket_price() {
		return ticket_price;
	}
	public void setTicket_price(double ticket_price) {
		this.ticket_price = ticket_price;
	}
	public String getTicket_desc() {
		return ticket_desc;
	}
	public void setTicket_desc(String ticket_desc) {
		this.ticket_desc = ticket_desc;
	}
	
	
}
