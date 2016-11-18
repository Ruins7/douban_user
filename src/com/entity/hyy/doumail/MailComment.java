package com.entity.hyy.doumail;



import java.util.Date;

import com.entity.lsr.user.User_Info;


/**
 * 单条豆邮信息
 * @author 胡伊杨
 *
 */
public class MailComment {
	private int comment_id;
	private Date comment_time;
	private String comment_content;
	private User_Mail userMail;
	private User_Info send_user_id;
	private User_Info recive_user_id;
	private int recive_user_read;
	private String link;
	
	public MailComment(){
		
	}

	
	public MailComment(int comment_id, Date comment_time,
			String comment_content, User_Mail userMail, User_Info send_user_id,
			User_Info recive_user_id, int recive_user_read, String link) {
		super();
		this.comment_id = comment_id;
		this.comment_time = comment_time;
		this.comment_content = comment_content;
		this.userMail = userMail;
		this.send_user_id = send_user_id;
		this.recive_user_id = recive_user_id;
		this.recive_user_read = recive_user_read;
		this.link = link;
	}


	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public Date getComment_time() {
		return comment_time;
	}

	public void setComment_time(Date comment_time) {
		this.comment_time = comment_time;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public User_Mail getUserMail() {
		return userMail;
	}

	public void setUserMail(User_Mail userMail) {
		this.userMail = userMail;
	}

	public User_Info getSend_user_id() {
		return send_user_id;
	}

	public void setSend_user_id(User_Info send_user_id) {
		this.send_user_id = send_user_id;
	}

	public User_Info getRecive_user_id() {
		return recive_user_id;
	}

	public void setRecive_user_id(User_Info recive_user_id) {
		this.recive_user_id = recive_user_id;
	}

	public int getRecive_user_read() {
		return recive_user_read;
	}

	public void setRecive_user_read(int recive_user_read) {
		this.recive_user_read = recive_user_read;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
}
