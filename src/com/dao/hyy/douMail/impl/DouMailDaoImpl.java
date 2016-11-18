package com.dao.hyy.douMail.impl;


import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.dao.hyy.douMail.DouMailDao;
import com.dao.hyy.user.UserDao;
import com.dao.hyy.user.impl.UserDaoImpl;
import com.dao.lsr.pool.PoolService;
import com.entity.hyy.doumail.MailComment;
import com.entity.hyy.doumail.User_Mail;
import com.entity.lsr.user.User_Info;

/**
 * 豆邮的dao层的实现类
 * @author 胡伊杨
 *
 */
public class DouMailDaoImpl implements DouMailDao{

	private static final String URL = "jdbc:mysql://localhost:3306/douban_db_new";
	PoolService pool = PoolService.getInstance();
	QueryRunner qr = new QueryRunner();
	/**
	 * 获得我的豆邮
	 * @param myUser_id
	 * @return
	 */
	public List<MailComment> getMyMailArray(int myUser_id){
		Connection conn = pool.getConnection();
		String sql_string = "SELECT * FROM ((SELECT um1.um_id,um1.user_id_to FROM user_mail um1 WHERE um1.user_id_from=?) UNION (SELECT um2.um_id,um2.user_id_from FROM user_mail um2 WHERE um2.user_id_to=?))result,mailCOMMENT comm,(SELECT um_id FROM mailcanread WHERE user_id=?) um WHERE comm.um_id=result.um_id AND um.um_id=comm.um_id GROUP BY comm.um_id ORDER BY comment_time DESC";
		System.out.println(sql_string);
		List<MailComment> totalMailComment = new ArrayList<MailComment>();
		try {
			List<Object[]> userMailList = (List<Object[]>)qr.query(conn, sql_string, new ArrayListHandler(),myUser_id,myUser_id,myUser_id);
			List<MailComment> mailCommentList = new ArrayList<MailComment>();
			for (Iterator iterator = userMailList.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				UserDao ud = new UserDaoImpl();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				User_Mail userMail = getUserMail(Integer.parseInt(objects[5].toString()));
				userMail.setUserMail_id(Integer.parseInt(objects[0].toString()));
				System.out.println("邮件编号："+Integer.parseInt(objects[0].toString()));
				User_Info user = ud.getUser(Integer.parseInt(objects[7].toString()));
				System.out.println("图片地址是："+user.getImgs());
				MailComment mailComment = new MailComment(Integer.parseInt(objects[2].toString()),sdf.parse(objects[3].toString()),objects[4].toString(),userMail,ud.getUser(Integer.parseInt(objects[6].toString())),ud.getUser(Integer.parseInt(objects[7].toString())),1,"");
				mailCommentList.add(mailComment); 
			}
			System.out.println(mailCommentList.size());
			pool.releaseConnection(conn);
			return mailCommentList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据user的id获得user
	 * @param user_id
	 * @return
	 */
	public User_Info getUser(int user_id){
		Connection conn = pool.getConnection();
		String sql = "select * from User_info where user_id=?";
		try {
			User_Info user = qr.query(conn, sql, new BeanHandler(User_Info.class), user_id);
			pool.releaseConnection(conn);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User_Mail getUserMail(int userMail_id){
		Connection conn = pool.getConnection();
		String sql = "select * from User_mail where um_id=?";	
		try {			
			Object[] user_from_to = qr.query(conn, sql, new ArrayHandler(),userMail_id);
			User_Info user1 = this.getUser(Integer.parseInt(user_from_to[1].toString()));
			User_Info user2 = this.getUser(Integer.parseInt(user_from_to[2].toString()));
			User_Mail user_Mail = new User_Mail(Integer.parseInt(user_from_to[0].toString()),user1,user2);
			pool.releaseConnection(conn);
			return user_Mail;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * 获得我和某一用户的所有豆邮
	 * @param myUser_id
	 * @param otherUser_id
	 * @return
	 */
	public List<MailComment> getUserToUserMail(int myUser_id, int otherUser_id) {
		Connection conn = pool.getConnection();
		String sql_string = "SELECT * FROM ((SELECT um1.um_id,um1.user_id_to FROM user_mail um1 WHERE um1.user_id_from=?) UNION (SELECT um2.um_id,um2.user_id_from FROM user_mail um2 WHERE um2.user_id_to=?))result,mailCOMMENT comm WHERE comm.um_id=result.um_id AND user_id_to=? ORDER BY comment_time desc";
		System.out.println(sql_string);
		try {
			List<MailComment> objList = new ArrayList<MailComment>();
			System.out.println("myuser_id:"+myUser_id+",ohterUser_id:"+otherUser_id);
			List<Object[]> userToMailList = (List<Object[]>)qr.query(conn, sql_string, new ArrayListHandler(),myUser_id,myUser_id,otherUser_id);
			String sql1 = "select mailComment_id from mailcommentcanread where user_id = ?";
			List<Integer> mailCommentList = (List<Integer>)qr.query(conn , sql1 , new ColumnListHandler<Integer>("mailcomment_id"),myUser_id );
			System.out.println("mailCommentList"+mailCommentList);
			for (Iterator iterator = userToMailList.iterator(); iterator.hasNext();) {
				System.out.println("进来了");
				Object[] mc = (Object[]) iterator.next();				
				if(!mailCommentList.contains(mc[2])){
					System.out.println("不包含"+mc[2]);					
				}else{
					System.out.println("包含"+mc[2]);
					String sql2 = "select um_id from user_mail where um_id=?";
					String sql3 = "select * from user_info where user_id=?";
					User_Mail userMail = qr.query(conn, sql2, new BeanHandler(User_Mail.class),Integer.parseInt(mc[0].toString()));
					User_Info user_from = qr.query(conn, sql3, new BeanHandler(User_Info.class),Integer.parseInt(mc[6].toString()));
					User_Info user_to = qr.query(conn, sql3, new BeanHandler(User_Info.class),Integer.parseInt(mc[7].toString()));
					userMail.setUserMail_id(Integer.parseInt(mc[0].toString()));
					userMail.setUser_id_from(user_from);
					userMail.setUser_id_to(user_to);
					MailComment mailComment = new MailComment(Integer.parseInt(mc[2].toString()),(Date)mc[3],mc[4].toString(),userMail,user_from,user_to,1,mc[8].toString());
					mailComment.setUserMail(userMail);
				
					objList.add(mailComment);
				}
			}
			System.out.println(objList);
			pool.releaseConnection(conn);
			System.out.println("大小是："+objList.size());
			return objList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}

	@Override
	/**
	 * 发送豆邮
	 * @param mailComment
	 * @return
	 */
	public int saveMailComment(MailComment mailComment) {
		Connection conn = pool.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = sdf.format(mailComment.getComment_time());
		
		String sql = "insert into mailcomment (comment_time , comment_content , send_user_id,receive_user_id ,link) values ( ? , ? , ? , ?,'')";
		Object[] params = { nowTime,mailComment.getComment_content(),mailComment.getSend_user_id().getUser_id(),mailComment.getRecive_user_id().getUser_id()};
		try {
			int affectRows = qr.update(conn , sql , params);
			System.out.println(affectRows + "条记录受影响 ");
			if(affectRows!=0){
				String sql1 = "select um_id from user_mail where user_id_from=? and user_id_to =?";
				List<Object[]> list = qr.query(conn, sql1, new ArrayListHandler(),mailComment.getSend_user_id().getUser_id(),mailComment.getRecive_user_id().getUser_id());
				if(list.size()==0){
					list = qr.query(conn, sql1, new ArrayListHandler(),mailComment.getRecive_user_id().getUser_id(),mailComment.getSend_user_id().getUser_id());
				}
				String sq = "update mailcomment set um_id = ? where send_user_id =? and receive_user_id=?";
				Object[] param = {list.get(0)[0],mailComment.getSend_user_id().getUser_id(),mailComment.getRecive_user_id().getUser_id()};
				QueryRunner qr1 = new QueryRunner();
				affectRows = qr1.update(conn, sq,param);
				System.out.println(affectRows + " ����¼��¼��");
				pool.releaseConnection(conn);
				return affectRows;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	/**
	 * 单向删除豆邮
	 * @param mailComment_id
	 * @param user_id
	 * @return
	 */
	public int deleteComment(int mailComment_id, int user_id) {
		Connection conn = pool.getConnection();
		String sql = "delete from mailcommentcanread where mailcomment_id=? and user_id=?";
		Object[] params = {mailComment_id,user_id};
		try {
			int affectRows = qr.update(conn , sql , params);
			System.out.println(affectRows + "行记录受影响");
			pool.releaseConnection(conn);
			return affectRows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	/**
	 * 双向删除豆邮
	 * @param myUser_id
	 * @param otherUser_id
	 * @return
	 */
	public int deleteMail(int myUser_id, int um_id) {
		Connection conn = pool.getConnection();
		String sql = "delete from mailcanread where user_id_from=? and um_id=?";
		QueryRunner qr = new QueryRunner();
		int affectRows = 0;
		try {
			affectRows = qr.update(conn, sql, myUser_id,um_id);
			System.out.println(affectRows + " 行记录受影响");
			pool.releaseConnection(conn);
			return affectRows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	/**
	 * 删除我和其他用户的豆邮
	 * @param my_id 我的id
	 * @param other_id 对方的id
	 * @return 返回的结果
	 */
	public int deleteOtherMail(int my_id, int um_id) {
		Connection conn = pool.getConnection();
		String sql = "delete from mailcanread where user_id=? and um_id=?";
		try {
			int affectRows = qr.update(conn, sql, my_id,um_id);
			System.out.println(affectRows+"条记录受影响");
			if(affectRows!=0){
				String deleteMailcommentCanRead = "delete from mailcommentcanread where user_id=? and um_id=?";
				affectRows = qr.update(conn, deleteMailcommentCanRead, my_id,um_id);
				System.out.println(affectRows+"条记录受影响");
			}
			pool.releaseConnection(conn);
			return affectRows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	/**
	 * 获取json数组
	 * @param offList
	 * @return
	 */
	public String mailCommentJson(List<MailComment> offList){

		JsonConfig config = new JsonConfig();

		config.setExcludes(new String[]{"comm"}); 
		config.registerJsonBeanProcessor(Date.class,  new JsDateJsonBeanProcessor());
		JSONArray json = (JSONArray) JSONSerializer.toJSON(offList, config);
//		JSONObject jsonObject = JSONObject.fromObject(offList);
		System.out.println("============"+json.toString());
		return json.toString();
	}
/*	@Override
	*//**
	 * ����ĳ����ĳ�˵����ж���
	 * @param myUser_id �ҵ�user_id
	 * @param otherUser_id �Է���user_id
	 *//*
	public int insertMail(int myUser_id, int otherUser_id,String content) {
		String sql = "insert into mailcomment(comment_time,comment_content,send_user_id,receive_user_id) values(?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		Object[] object = {date,content,myUser_id,otherUser_id};
		try {
			int affectRows = qr.update(conn, sql,object);
			return affectRows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}*/
}
