package com.entity.nxt.group;

public class Group_Message {
	private int id;
	private int msg_from;
	private int msg_to;
	private String msg_content;
	private int msg_status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMsg_from() {
		return msg_from;
	}
	public void setMsg_from(int msg_from) {
		this.msg_from = msg_from;
	}
	public int getMsg_to() {
		return msg_to;
	}
	public void setMsg_to(int msg_to) {
		this.msg_to = msg_to;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public int getMsg_status() {
		return msg_status;
	}
	public void setMsg_status(int msg_status) {
		this.msg_status = msg_status;
	}
	
	
}
