package com.dao.hyy.douMail;


import java.util.List;

import com.entity.hyy.doumail.MailComment;



/**
 * 豆邮的dao层
 * @author 胡伊杨
 *
 */
public interface DouMailDao {
	/**
	 * 获得我的豆邮
	 * @param myUser_id
	 * @return
	 */
	public List<MailComment>getMyMailArray(int myUser_id);
	

	
//	public List<Object[]> getUserMailArray(int myUser_id);
	
	/**
	 * 获得我和某一用户的所有豆邮
	 * @param myUser_id
	 * @param otherUser_id
	 * @return
	 */
	public List<MailComment> getUserToUserMail(int myUser_id,int otherUser_id);
	
	/**
	 * 发送豆邮
	 * @param mailComment
	 * @return
	 */
	public int saveMailComment(MailComment mailComment);
	
	/**
	 * 单向删除豆邮
	 * @param mailComment_id
	 * @param user_id
	 * @return
	 */
	public int deleteComment(int mailComment_id,int user_id);
	
	/**
	 * 双向删除豆邮
	 * @param myUser_id
	 * @param otherUser_id
	 * @return
	 */
	public int deleteMail(int myUser_id,int otherUser_id);
/*	
	*//**
	 * ����˷��Ͷ���
	 * @param myUser_id �ҵ�user_id
	 * @param otherUser_id �Է���user_id
	 *//*
	public int insertMail(int myUser_id, int otherUser_id,String content);*/
	
	/**
	 * 删除我和其他用户的豆邮
	 * @param my_id 我的id
	 * @param um_id 交流的编号
	 * @return 返回的结果
	 */
	public int deleteOtherMail(int my_id,int um_id);
	
	/**
	 * 获取json数组
	 * @param offList
	 * @return
	 */
	public String mailCommentJson(List<MailComment> offList);
}
