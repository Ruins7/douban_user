package com.dao.nxt.group.daoImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.dao.nxt.group.dao.GroupDao;
import com.dao.nxt.group.dbutils.daoImpl.DaoOperationImpl;
import com.entity.lsr.user.User_Info;
import com.entity.nxt.group.Group_Basicinfo;
import com.entity.nxt.group.Group_Black_List;
import com.entity.nxt.group.Group_List;
import com.entity.nxt.group.Group_Post;
import com.entity.nxt.group.Group_Role;
import com.entity.nxt.group.Reply;

public class GroupDaoImpl implements GroupDao{
	DaoOperationImpl doi = new DaoOperationImpl();
	
	@Override
	public int countAll(String sql,Class claz) {
		// TODO 统计表数目
		List list = doi.qrQueryForList(sql, claz);
		return list.size();
	}

	@Override
	public Group_Basicinfo createGroup(Group_Basicinfo gb,User_Info user) {
		// TODO 创建新的小组
		System.out.println(gb);
		String sql = "insert into group_basicinfo(group_name,group_intro,imgs,group_createtime,group_tag,admin_nick,member_nick,group_type) values(?,?,?,?,?,?,?,?)";
		Object[] params = {gb.getGroup_name(),gb.getGroup_intro(),gb.getImgs(),gb.getGroup_createtime(),gb.getGroup_tag(),gb.getAdmin_nick(), gb.getMember_nick(),gb.getGroup_type()};
		int  flag = doi.qrUpdate(sql, params);
		if(flag > 0 ){
			String sql1 = "select * from group_basicinfo where group_createtime = ?";
			Object[] params1 = {gb.getGroup_createtime()};
			Group_Basicinfo group = doi.qrQueryForOne(sql1, new Group_Basicinfo(), params1);
			System.out.println("当前的User是："+user.getUsername());
			System.out.println("dao层中createGroup方法返回的group类是："+group);
			//向group_list中插入一条数据
			String sql2 = "insert into group_list(group_id,user_id,role_id)values(?,?,?)";
			
			System.out.println("创建小组时：groupId："+group.getId()+"user:"+user.getUser_id());
			Object[] params2 = {group.getId(),user.getUser_id(),1};
			int f = doi.qrUpdate(sql2, params2);
			if(f > 0)
			return group;
		}
		return null;
	}

	@Override
	public int adminUpdateGroupInfo(Group_Basicinfo gb) {
		// TODO 更新小组信息
		String sql = "update group_basicinfo set group_name = ? , group_intro = ? , imgs = ? , group_createtime = ? , group_tag = ? , admin_nick = ? , member_nick = ? , group_type = ? where id = ?";
		Object[] params = {gb.getGroup_name(),gb.getGroup_intro(),gb.getImgs(),gb.getGroup_createtime(),gb.getGroup_tag(),gb.getAdmin_nick(), gb.getMember_nick(),gb.getGroup_type(),gb.getId()};
		int flag = doi.qrUpdate(sql, params);
		return flag;
	}

	@Override
	public List<User_Info> queryAllMembers(int group_id) {
		// TODO 查询小组所有成员信息
		String sql = "select * from group_list where group_id = ?";
		Object[] params = {group_id};
		List<Group_List> lists = doi.qrQueryForList(sql, new Group_List(), params);
		List<User_Info> user = new ArrayList<>();
		for (Group_List group_List : lists) {
			String sql1 = "select * from user_info where user_id = ?";
			Object[] params1 = {group_List.getUser_id()};
			user.add(doi.qrQueryForOne(sql1, new User_Info(), params1));
		}
		return user;
	}


	@Override
	public int adminDelMembers(int user_id,int group_id) {
		// TODO 将用户踢出小组，但可以后来再加入
		String sql = "delete from group_list where user_id = ? and group_id = ?";
		Object[] params = {user_id,group_id};
		int flag = doi.qrUpdate(sql, params);
		return flag;
	}

	@Override
	public int adminForbidMember(int group_id,int user_id) {
		// TODO 永久封禁某个用户，以后不可再加该小组
		String sql = "delete from group_list where group_id = ? and user_id = ?";
		Object[] params = {group_id,user_id};
		int flag = doi.qrUpdate(sql, params);
		//加入黑名单
		String sql1 = "insert into group_black_list values(?,?)";
		Object[] params1 = {group_id, user_id};
		int flag1 = doi.qrUpdate(sql1, params1);
		return flag1;
	}

	@Override
	public int adminPromoteMember(int group_id,int user_id) {
		// TODO//组长将普通成员设为管理员
		String sql = "update group_list set role_id = 2 where group_id = ? and user_id = ?";
		Object[] params = {group_id,user_id};
		int flag = doi.qrUpdate(sql, params);
		return flag;
	}

	@Override
	public int adminDemoteMember(int group_id,int user_id) {
		// TODO组长将管理员降为普通成员
		String sql = "update group_list set role_id = 3 where group_id = ? and user_id = ?";
		Object[] params = {group_id,user_id};
		int flag = doi.qrUpdate(sql, params);
		return flag;
	}

	@Override
	public int adminDelGroupPost(int post_id) {
		// TODO 管理员,或者作者删除小组帖子
		String sql = "delete from group_post where id = ? ";
		Object[] params = {post_id};
		int flag = doi.qrUpdate(sql, params);
		//并删除对应的回复
		String sql1 = "delete from reply where reply_post = ?";
		Object[] params1 = {post_id};
		int flag1 = doi.qrUpdate(sql1, params1);
		return flag1;
	}

	@Override
	public int adminAssessMember(int group_id,int user_id) {
		// TODO 审核新成员入组
		String sql1 = "select * from group_black_list where user_id = ?";
		Object[] params1 = {user_id};
		List<Group_Black_List> gbl = doi.qrQueryForList(sql1, new Group_Black_List(), params1);
		if(gbl.size() == 0){
		String sql = "insert into group_list values(?,?)";
		Object[] params = {group_id,user_id};
		int flag = doi.qrUpdate(sql, params);
		return flag;
		}
		return -1;
	}

	@Override
	public int createPost(Group_Post post) {
		// TODO 成员发帖
		String sql = "insert into group_post(post_title,post_pubtime,post_author,post_group,group_content) values(?,?,?,?,?)";
		Object[] params = {post.getPost_title(),post.getPost_pubtime(),post.getPost_author(),post.getPost_group(),post.getGroup_content()};
		int flag = doi.qrUpdate(sql, params);
		return flag;
	}

	@Override
	public int userUpdatePost(Group_Post post) {
		// TODO 用户更细自己的帖子
		String sql = "update group_post set post_title = ? and post_pubtime = ? and post_author = ? and post_group = ? and post_content = ? and readcount = ? and set_toptime = ? where id = ?";
		Object[] params = {post.getPost_title(),post.getPost_pubtime(),post.getPost_author(),post.getPost_group(),post.getGroup_content(),post.getReadcount(),post.getSet_toptime(),post.getId()};
		int flag = doi.qrUpdate(sql, params);
		return flag;
	}

	@Override
	public List<Group_Post> queryAllPosts(int group_id) {
		// TODO 查询所有帖子
		String sql = "select * from group_post where post_group = ?";
		Object[] params = {group_id};
		List<Group_Post> posts = doi.qrQueryForList(sql, new Group_Post(), params);
		if(posts != null){
		return posts;}
		return null;
	}

	@Override
	public Group_Post querySinglePost(int post_id) {
		// TODO 查询单个帖子信息
		String sql = "select * from group_post where id = ?";
		Object[] params = {post_id};
		Group_Post post = doi.qrQueryForOne(sql, new Group_Post(), params);
		System.out.println("OldReadCount is :~~~~~~~~"+post.getReadcount());
		post.setReadcount(post.getReadcount()+1);
		System.out.println("NewReadCount is :~~~~~~~~"+post.getReadcount());
		String sql2 = "update group_post set readcount = ? where id = ?";
		Object[] params2 = {post.getReadcount(),post.getId()};
		int flag = doi.qrUpdate(sql2, params2);
		return post;
	}

	@Override
	public List<Reply> queryAllReplies(int post_id) {
		// TODO 查询帖子的所有回复
		String sql = "select * from reply where reply_post = ?";
		Object[] params  = {post_id};
		List<Reply> replies = doi.qrQueryForList(sql, new Reply(), params);
		return replies;
	}

	@Override
	public int userAddReply(Reply reply) {
		// TODO用户新增回复
		String sql = "insert into reply(reply_author,reply_post,reply_time,reply_content) values(?,?,?,?)";
		Object[] params = {reply.getReply_author(),reply.getReply_post(),reply.getReply_time(),reply.getReply_content()};
		int flag = doi.qrUpdate(sql, params);
		return flag;
	}

	@Override
	public int adminSetTopPost(int post_id) {
		// TODO 组长，管理员把帖子置顶
		String sql = "update group_post set set_toptime = ? where id = ?";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String curTime = sdf.format(date);
		Object[] params = {curTime,post_id};
		int flag = doi.qrUpdate(sql, params);
		return flag;
	}

	@Override
	public List<Group_Post> queryPostsByHot() {
		// TODO 按热度查询
		String sql = "select * from group_post order by readcount desc";
		List<Group_Post> posts = doi.qrQueryForList(sql, Group_Post.class);
		return posts;
	}

	@Override
	public User_Info userLogin(String username,String pwd) {
		// TODO 用户登录了
		String sql = "select * from user_info where username = ? and pwd = ?";
		Object[] params = {username,pwd};
		User_Info user = doi.qrQueryForOne(sql, new User_Info(), params);
		return user;
	}

	@Override
	public Group_Basicinfo queryGroup(int group_id) {
		// TODO 根据小组id查看小组基本信息
		String sql = "select * from group_basicinfo where id = ?";
		Object[] params = {group_id};
		Group_Basicinfo group = doi.qrQueryForOne(sql, new Group_Basicinfo(), params);
		return group;
	}

	@Override
	public List<Group_Basicinfo> queryUserGroups(int user_id) {
		// TODO 根据当前登陆的用户id查询用户加入的小组
		String sql = "select * from group_basicinfo where id in (select group_id from group_list where user_id = ?)";
		Object[] params = {user_id};
		List<Group_Basicinfo> groups = doi.qrQueryForList(sql, new Group_Basicinfo(), params);
		return groups;
	}

	@Override
	public User_Info queryUserById(int user_id) {
		// TODO 根据用户id查询用户信息
		String sql = "select * from user_info where user_id = ?";
		Object[] params = {user_id};
		User_Info user = doi.qrQueryForOne(sql, new User_Info(), params);
		return user;
	}

	@Override
	public Reply queryRecentReplyTime(int post_id) {
		// TODO 查询某个帖子最近被回复的时间
		String sql = "select * from reply where reply_post = ? order by reply_time desc";
		Object[] params = {post_id};
		List<Reply> r = doi.qrQueryForList(sql, new Reply(), params);
		if(r!=null){
		return r.get(0);
		}return null;
	}

	@Override
	public List<Group_Post> queryUserReplies(int user_id) {
		// TODO 查询某个用户共回复的帖子
		String sql = "select * from reply where reply_author = ?";
		Object[] params = {user_id};
		List<Reply> r = doi.qrQueryForList(sql, new Reply(), params);
		
		List<Integer> post_id = new ArrayList<Integer>();
		
		Set<Integer> post_ids = new HashSet<Integer>();
		for (Reply reply : r) {
			post_ids.add(reply.getId());
		}
		Iterator i = post_ids.iterator();
		while(i.hasNext()){
			post_id.add((Integer) i.next());
		}
		List<Group_Post> posts = new ArrayList<Group_Post>();
		for (Integer integ : post_id) {
			Group_Post p = new Group_Post();
			String sql1 = "select * from group_post where post_group = ?";
			Object[] params2 = {integ};
			p = doi.qrQueryForOne(sql1, new Group_Post(), params2);
			posts.add(p);
		}
		return posts;
	}

	@Override
	public List<Group_Post> queryUserPosts(int user_id) {
		// TODO 查询某个用户共发起的帖子
		String sql = "select * from group_post where post_author = ?";
		Object[] params = {user_id};
		List<Group_Post> posts = doi.qrQueryForList(sql, new Group_Post(), params);
		return posts;
	}

	@Override
	public List<User_Info> queryClassifyMembers(List<User_Info> users,
			int role_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group_Role queryUserRole(int group_id, int user_id) {
		// TODO 查询用户角色
		String sql ="select * from group_list where group_id = ? and user_id = ?";
		Object[] params = {group_id,user_id};
		Group_List list = doi.qrQueryForOne(sql, new Group_List(), params);
		if(list != null){
			String sql1 = "select * from group_role where group_role_id = ?";
			Object[] params1 = {list.getRole_id()};
			Group_Role role = doi.qrQueryForOne(sql1, new Group_Role(), params1);
			return role;
		}
		return null;
	}

	@Override
	public List<Group_Post> queryPostsByCondition(String cond) {
		// TODO 按条件查询帖子
		String sql = "select * from group_basicinfo where group_tag = ?";
		Object[] params = {cond};
		List<Group_Basicinfo> groups = doi.qrQueryForList(sql, new Group_Basicinfo(), params);
		List<Group_Post> posts = new ArrayList<Group_Post>();
		if(groups != null){
		for (Group_Basicinfo group : groups) {
			List<Group_Post> p = queryAllPosts(group.getId());
			if(p != null){
			for (Group_Post group_Post : p) {
				posts.add(group_Post);
			}
			}
			
		}
		}
		return posts;
	}

	@Override
	public List<Group_List> queryGroupList(int user_id) {
		// TODO Auto-generated method stub
		String sql = "select * from group_list where user_id = ?";
		Object[] params = {user_id};
		List<Group_List> group_list = doi.qrQueryForList(sql, new Group_List(), params);
		return group_list;
	}
	
	
	

	
	

	

	
	
	
	
	
	
	
	
	
	
	

}
