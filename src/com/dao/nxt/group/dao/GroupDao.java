package com.dao.nxt.group.dao;

import java.util.List;

import com.entity.lsr.user.User_Info;
import com.entity.nxt.group.Group_Basicinfo;
import com.entity.nxt.group.Group_List;
import com.entity.nxt.group.Group_Post;
import com.entity.nxt.group.Group_Role;
import com.entity.nxt.group.Reply;

public interface GroupDao {
	public User_Info userLogin(String username,String pwd);//用户登录
	public int countAll(String sql,Class claz);//统计总数
	public Group_Basicinfo createGroup(Group_Basicinfo gb,User_Info user);//创建新的小组
	public Group_Basicinfo queryGroup(int group_id);//管理员查看或更新小组
	public int adminUpdateGroupInfo(Group_Basicinfo gb);//更新小组基本信息
	public int adminDelGroupPost(int post_id);//管理员,或者作者删除小组帖子
	public int adminDelMembers(int user_id,int group_id);//管理员踢人
	public int adminForbidMember(int group_id,int user_id);//管理员封禁成员，以后不可再加
	public int adminPromoteMember(int group_id,int user_id);//组长将普通成员设为管理员
	public int adminDemoteMember(int group_id,int user_id);//组长将管理员降为普通成员
	public int adminAssessMember(int group_id,int user_id);//组长，管理员审核成员进入小组
	public int adminSetTopPost(int post_id);//组长，管理员把帖子置顶
	
	
	public List<User_Info> queryAllMembers(int group_id);//查询小组所有成员信息
	public List<User_Info> queryClassifyMembers(List<User_Info> users,int role_id);//按角色查找小组成员
	public int createPost(Group_Post post);//成员发帖
	public int userUpdatePost(Group_Post post);//用户更新帖子
	public List<Group_Post> queryAllPosts(int group_id);//查询所有帖子--默认排序
	public List<Group_Post> queryPostsByHot(); //最热话题--排序
	public Group_Post querySinglePost(int post_id);//查询单个帖子信息
	public List<Reply> queryAllReplies(int post_id);//查询帖子的所有回复
	public int userAddReply(Reply reply);//用户新增回复
	public List<Group_Basicinfo> queryUserGroups(int user_id);//查询用户加入的小组
	public User_Info queryUserById(int user_id);//根据用户id查询用户信息
	public Reply queryRecentReplyTime(int post_id);//查询某个帖子最近回复的时间
	public List<Group_Post> queryUserReplies(int user_id);//查询某个用户共回复的帖子
	public List<Group_Post> queryUserPosts(int user_id);//查询某个用户共发起的帖子
	public Group_Role queryUserRole(int group_id,int user_id);//查询用户角色
	public List<Group_Post> queryPostsByCondition(String cond);//按条件查询帖子
	public List<Group_List> queryGroupList(int user_id);//按用户id查询用户所属小组
}
