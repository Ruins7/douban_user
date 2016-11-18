/**
 * 
 */
package com.dao.lsr.user.daoImpl;

import java.util.List;
import java.util.Map;

import com.dao.lsr.dbutils.daoImpl.DaoOperationImpl;
import com.dao.lsr.user.dao.BroRecoAndLike;
import com.entity.lsr.user.User_Broadcast;
import com.entity.lsr.user.User_Commons;
import com.entity.lsr.user.User_Like;
import com.entity.lsr.user.User_Recommend;
import com.entity.lsr.user.User_Score;

/**
 * @ClassName: RecoAndLike.java
 * @Description: TODO(用一句话描述该文件做什么)
 * 
 *               推荐到我的广播、喜欢、打分实现
 * 
 * @author Ruins7
 * @version V1.0
 * @Date 2014年10月4日 下午3:27:10
 */
public class BroRecoAndLikeImpl implements BroRecoAndLike {

	DaoOperationImpl doimpl = new DaoOperationImpl();

	@Override
	public int broadcast(User_Broadcast br) {
		// 发送我的广播
		String sql = "insert into user_broadcast (user , time, content, type, imgs, sub_item_id) values (?,?,?,?,?,?)";
		Object[] params = {br.getUser(), br.getTime(), br.getContent() ,br.isType() ,br.getImgs(),br.getSub_item_id()};
		int resultInt = doimpl.qrUpdate(sql, params);
		return resultInt;
	}

	@Override
	public int delBroadcast(String broadcast_id) {
		// 删除我的广播
		String sql = "delete from user_broadcast where broadcast_id = ?";
		Object[] params = {broadcast_id};
		int resultInt = doimpl.qrUpdate(sql, params);
        return resultInt;
	}

	@Override
	public int Recommend(User_Recommend ur) {
		// 推荐到我的广播
		String sql = "insert into user_recommend (user, type_table, sub_item_id, time, my_commons) values(?,?,?,?,?)";
		Object[] params = {ur.getUser(), ur.getType_table(), ur.getSub_item_id(),ur.getTime(), ur.getMy_commons()};
		int resultInt = doimpl.qrUpdate(sql, params);
		return resultInt;
	}

	@Override
	public int delRecommend(String recommend_id) {
		// 从我的推荐删除
		String sql = "delete from user_recommend where recommend_id = ?";
		Object[] params = {recommend_id};
		int resultInt = doimpl.qrUpdate(sql, params);
		return resultInt;
	}

	@Override
	public int likeSomething(User_Like like) {
		// 添加到我的喜欢
		//type:线上活动onlineactivity，话题topic，东西things，日志diary，相册album，照片photo,广播
		String sql = "insert into user_like(user,item,item_id,time) values(?,?,?,?)";
		Object[] params = {like.getUser(), like.getItem(),like.getItem_id(),like.getTime()};
		int resultInt = doimpl.qrUpdate(sql, params);
		return resultInt;
	}

	@Override
	public int cancelLike() {
		// 取消我的喜欢
		String sql = "delete from ............?";
		Object[] params = {};
		int resultInt = doimpl.qrUpdate(sql, params);
		return resultInt;
	}

	@Override
	public double judgeScore(User_Score s) {
		// 打分
		String sql_insert = "insert into score(user,item,item_id,time,score) values(?,?,?,?,?)";
		Object[] params = {s.getUser(),s.getItem(),s.getItem_id(),s.getTime(),s.getScore()}; 
		int resultInt = doimpl.qrUpdate(sql_insert, params);
	    return resultInt;
	}
	
	@Override
	public double showScore(String item_id,String item) {
		// 显示分数 
		String sql = "select avg(score) from score where item_id ? and item = ?";
		Object[] params = {item_id, item};
		List<Object[]> list = doimpl.qrQueryForResultSet(sql, params); 
		return (double)list.get(0)[0];
	}

	@Override
	public List<User_Broadcast> showAllMyBroadcast(String username) {
		// 查找所有我的广播
		String sql = "select * from user_broadcast b, (select user_id from user_info where username = ?) a where b.user = a.user_id order by time DESC";
		Object[] params = {username};
		List<User_Broadcast> list = doimpl.qrQueryForList(sql, new User_Broadcast(), params);
		return list;
	}
	
	@Override
	public List<User_Broadcast> showAllMyFriendsBroadcast(String user_id) {
		// 查找所有我关注的好友的广播
		String sql = "select * from user_broadcast b,(select to_user from user_focus where from_user = (select user_id from user_info where user_id = ?)) a where b.user = a.to_user order by time DESC";
		Object[] params = {user_id};
		List<User_Broadcast> list = doimpl.qrQueryForList(sql, new User_Broadcast(), params);
		return list;
	}

	@Override
	public List<User_Like> showAllMyLike(int user_id, int item) {
		// 查找所有关于某项目的我的喜欢
		String sql = "select * from user_like where user = ? and item = ? order by time DESC";
		Object[] params = {user_id,item};
		List<User_Like> list = doimpl.qrQueryForList(sql, new User_Like(), params);
		return list;
	}
	@Override
	public List<User_Like> showAllMyLikeTotal(int user_id) {
		// 查找所有我的喜欢
		String sql = "select * from user_like where user = ?  order by time DESC";
		Object[] params = {user_id};
		List<User_Like> list = doimpl.qrQueryForList(sql, new User_Like(), params);
		return list;
	}

	@Override
	public int addDiscussAbout(User_Commons c) {
		 // 增加用户关于某一个事物的讨论
		 String sql = "insert into user_commons(from_user,time,content,item,item_id) values(?,?,?,?,?)";
		 Object[] params = {c.getFrom_user(),c.getTime(),c.getContent(),c.getItem(),c.getItem_id()};
		 int result = doimpl.qrUpdate(sql, params);
		 return result;
	}
	
	@Override
	public int delDiscussAbout(String commons_id) {
		// 删除用户关于某一个事物的讨论
		String sql = "delete from commons where commons_id = ?";
		Object[] params = {commons_id};
		int result = doimpl.qrUpdate(sql, params);
		return result;
	}

	@Override
	public List showAllCommonsAboutOneThing(String item,String item_id) {
		// 查看某一个事物下的所有用户讨论
		String sql = "select * from user_commons where item = ? and item_id = ?";
		Object[] params = {item, item_id};
		User_Commons commons = new User_Commons();
		List<User_Commons> list = doimpl.qrQueryForList(sql, commons, params);
		return list;		
	}

	@Override
	public User_Broadcast showOneBroadcast(String broadcast_id) {
		// 查看某一条广播
		String sql = "select * from user_broadcast where broadcast_id = ?";
		Object[] params = {broadcast_id};
		User_Broadcast result = doimpl.qrQueryForOne(sql, new User_Broadcast(), params);
		return result;
	}

	@Override
	public List<User_Recommend> showAllMyRecommend(String username) {
		// 查询所有此用户的推荐
		String sql = "select * from user_recommend b, (select user_id from user_info where username = ?) a where b.user = a.user_id order by time desc";
		Object[] params = {username};
		List<User_Recommend> list = doimpl.qrQueryForList(sql, new User_Recommend(), params);
		return list;
	}

	@Override
	public List<User_Recommend> showAllMyFriendsRecommend(String username) {
		// 查询所有此用户关注的人的推荐
		String sql = "select * from user_recommend b,(select to_user from user_focus where from_user = (select user_id from user_info where username = ?)) a where b.user = a.to_user order by time DESC";
		Object[] params = {username};
		List<User_Recommend> list = doimpl.qrQueryForList(sql, new User_Recommend(), params);
		return list;
	}

}
