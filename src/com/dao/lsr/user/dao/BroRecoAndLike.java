/**
 * 
 */
package com.dao.lsr.user.dao;

import java.util.List;
import java.util.Map;

import com.entity.lsr.user.User_Commons;
import com.entity.lsr.user.User_Like;
import com.entity.lsr.user.User_Score;
import com.entity.lsr.user.User_Broadcast;
import com.entity.lsr.user.User_Recommend;

/**
 * @ClassName:     RecoAndLike.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 *                 推荐到我的广播、喜欢、打分、用户讨论（音乐，电影，读书，同城活动，东西，我的豆瓣）              
 *                 接口
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月4日 下午3:29:55 
 */
public interface BroRecoAndLike {
	
	public int broadcast(User_Broadcast br);
	public List<User_Broadcast> showAllMyBroadcast(String username);
	public List<User_Recommend> showAllMyRecommend(String username);
	public List<User_Broadcast> showAllMyFriendsBroadcast(String username);
	public List<User_Recommend> showAllMyFriendsRecommend(String username);
	public int delBroadcast(String broadcast_id);
	public User_Broadcast showOneBroadcast(String broadcast_id);
	public int Recommend(User_Recommend ur);
	public int delRecommend(String recommend_id);
	public int likeSomething(User_Like like);
	public List<User_Like> showAllMyLike(int user_id, int item);
	public List<User_Like> showAllMyLikeTotal(int user_id) ;
	public int cancelLike();
	public double judgeScore(User_Score s);
	public double showScore(String item_id,String item);
	public int addDiscussAbout(User_Commons c);
	public int delDiscussAbout(String commons_id);
	public List showAllCommonsAboutOneThing(String item,String item_id);

}
