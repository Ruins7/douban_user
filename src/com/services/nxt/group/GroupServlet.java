package com.services.nxt.group;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.commontools.lsr.EntityAddClass;
import com.commontools.lsr.EntityClass;
import com.commontools.lsr.UserTools;
import com.dao.lsr.user.daoImpl.PersonalInfoImpl;
import com.dao.nxt.group.daoImpl.GroupDaoImpl;
import com.entity.lsr.user.User_Info;
import com.entity.nxt.group.Group_Basicinfo;
import com.entity.nxt.group.Group_List;
import com.entity.nxt.group.Group_Post;
import com.entity.nxt.group.Group_Role;
import com.entity.nxt.group.Reply;
import com.entity.util.nxt.group.Item_Post;
import com.entity.util.nxt.group.Item_Reply;
@WebServlet(
		name = "groupServlet",
		urlPatterns = {"/groupServlet"},
		loadOnStartup = 5
		)
public class GroupServlet extends HttpServlet {
	UserTools ut = new UserTools();
	GroupDaoImpl gdi = new GroupDaoImpl(); 
	PersonalInfoImpl psifImpl = new PersonalInfoImpl();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		
		if("createGroup".equals(method)){
			createGroup(request,response);
		}else if("adminEditGroup".equals(method)){
			try {
				adminEditGroup(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("queryOneGroup".equals(method)){
			queryOneGroup(request,response);
		}else if("queryOnePost".equals(method)){
			queryOnePost(request,response);
		}else if("queryUserPub".endsWith(method)){
			queryUserPub(request,response);
		}else if("queryUserReply".equals(method)){
			queryUserReply(request,response);
		}else if("userAddReply".equals(method)){
			userAddReply(request,response);
		}else if("userAddGroup".equals(method)){
			userAddGroup(request,response);
		}else if("queryPostByCondition".equals(method)){
			queryPostByCondition(request,response);
		}else if("queryAllPostsByHot".equals(method)){
			queryAllPostsByHot(request,response);
		}else if("queryMyPosts".equals(method)){
			queryMyPosts(request,response);
		}else if("aheadMyHomepage".equals(method)){
			aheadMyHomepage(request,response);
		}else if("queryMyGroupByRole".equals(method)){
			queryMyGroupByRole(request,response);
		}else if("userAddPost".equals(method)){
			userAddPost(request,response);
		}else if("editGroup1".equals(method)){
			editGroup1(request,response);
		}else if("memberCheck".equals(method)){
			memberCheck(request,response);
		}else if("delUser".equals(method)){
			delUser(request,response);
		}else if("forbidUser".equals(method)){
			forbidUser(request,response);
		}
		else if("promteUser".equals(method)){
			promteUser(request,response);
		}else if("demoteUser".equals(method)){
			demoteUser(request,response);
		}else if("loginGroup".equals(method)){
			loginGroup(request,response);
		}else if("UserOftenJoinGroup".equals(method)){
			UserOftenJoinGroup(request,response);
		}
	}

	private void loginGroup(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 登陆后初始化信息
		HttpSession session = request.getSession();
		User_Info user = (User_Info)session.getAttribute("current_user");
		if(user != null){
		
			
//			System.out.println("user id~~~~~~~~`"+user.getEmail());
			//查询用户一共加入多少小组---我常去的小组(我加入的小组)
			List<Group_Basicinfo> groups = gdi.queryUserGroups(user.getUser_id());
//			System.out.println("groups's size::::"+groups.size());
			//构造Item_Post对象----我的小组话题
			List<Item_Post> item_Post = new ArrayList<Item_Post>();
			for (Group_Basicinfo group_Basicinfo : groups) {
//				System.out.println("每一个group的id是："+group_Basicinfo.getId());
//				System.out.println("当前要查询的所有帖子所属的小组id是："+group_Basicinfo.getId());
				List<Group_Post> p = gdi.queryAllPosts(group_Basicinfo.getId());
				if(p != null){
				for (Group_Post group_Post : p) {
					System.out.println("当前的groupPost是："+group_Post.getId());
					Item_Post item = new Item_Post();
					item.setPost_id(group_Post.getId());
					item.setPost_title(group_Post.getPost_title());
//					int reply_count = gd.queryAllReplies(group_Post.getId()).size();
					List<Reply> r = gdi.queryAllReplies(group_Post.getId());
					if(r != null){
						int reply_count = r.size();
						item.setReply_count(reply_count);
					}else{
						item.setReply_count(0);
					}
//					String recentReply_time = gd.queryRecentReplyTime(group_Post.getId()).getReply_time();
					Reply r2 = gdi.queryRecentReplyTime(group_Post.getId());
					if(r2 != null){
						String recentReply_time = r2.getReply_time();
						item.setRecentReply_time(recentReply_time);
					}else{
						item.setRecentReply_time(group_Post.getPost_pubtime());
					}
					User_Info u = gdi.queryUserById(group_Post.getPost_author());
					item.setAuthor_id(group_Post.getPost_author());
					item.setAuthor_name(u.getUsername());
					item.setGroup_id(group_Basicinfo.getId());
					item.setGroup_name(group_Basicinfo.getGroup_name());
					item_Post.add(item);
				}}
			}
			//查询用户共发起多少帖子
			int pub_posts = gdi.queryUserPosts(user.getUser_id()).size();
			System.out.println("发起的帖子数："+pub_posts);
			request.setAttribute("pub_posts", pub_posts);
			//查询用户共回复多少帖子
			int reply_posts = gdi.queryUserReplies(user.getUser_id()).size();
			System.out.println("回复的帖子数"+reply_posts);
			request.setAttribute("reply_posts", reply_posts);
			//查询用户共回复过多少帖子
//			System.out.println("MyPosts:========"+my_posts.size());
			request.setAttribute("groups", groups);
			request.setAttribute("my_posts", item_Post);
			request.getRequestDispatcher("/nxt/douban_group_myGroupPosts.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/nxt/index.jsp").forward(request, response);
		}
		
	}

	private void demoteUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 降为普通用户
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		int flag = gdi.adminDemoteMember(group_id, user_id);
		memberCheck(request, response);
	}

	private void promteUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 升为管理员
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		int flag = gdi.adminPromoteMember(group_id,user_id);
		memberCheck(request, response);
		
	}

	private void forbidUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 封禁用户
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		int flag = gdi.adminForbidMember(group_id, user_id);
		memberCheck(request, response);
		
	}

	private void delUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 删除小组用户
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		int flag = gdi.adminDelMembers(user_id, group_id);
		memberCheck(request, response);
		
	}

	private void memberCheck(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 分类查看成员
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		List<User_Info> users = gdi.queryAllMembers(group_id);
		System.out.println("user changdu:"+users.size());
		if(users != null){
			List<User_Info> creator = new ArrayList<>();
			List<User_Info> admin = new ArrayList<>();
			List<User_Info> normal = new ArrayList<>();
			for (User_Info user : users) {
				System.out.println(user);
				Group_Role role = gdi.queryUserRole(group_id, user.getUser_id());
				if(role.getGroup_role_id() == 1){
					creator.add(user);
				}else if(role.getGroup_role_id() == 2){
					admin.add(user);
				}else{
					normal.add(user);
				}
			}
			request.setAttribute("group_id", group_id);
			request.setAttribute("creator", creator);
			System.out.println("creator 的长度是："+creator.size());
			request.setAttribute("admin", admin);
			System.err.println("admin de size is :"+admin.size());
			request.setAttribute("normal", normal);
			System.out.println("normal de size is: "+normal.size());
			request.getRequestDispatcher("/nxt/douban_group_memberCheck.jsp").forward(request, response);
		}else
			request.getRequestDispatcher("/nxt/douban_group_noMember.jsp").forward(request, response);
		
		
	}

	private void editGroup1(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		Group_Basicinfo group = gdi.queryGroup(group_id);
		System.out.println("group____:"+group);
		request.setAttribute("group", group);
		request.getRequestDispatcher("/nxt/douban_group_editGroup1.jsp").forward(request, response);
	}

	private void userAddPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 用户发帖
		String post_title = request.getParameter("post_title");
		String post_content = request.getParameter("post_title");
		HttpSession session = request.getSession();
		User_Info user =  (User_Info)session.getAttribute("current_user");
		int post_author = user.getUser_id();
		int post_group = Integer.parseInt(request.getParameter("post_group"));
		String post_pubtime = new Date().toLocaleString();
		Group_Post post = new Group_Post();
		post.setPost_title(post_title);
		post.setPost_pubtime(post_pubtime);
		post.setPost_author(post_author);
		post.setPost_group(post_group);
		post.setGroup_content(post_content);
		int flag = gdi.createPost(post);
		System.out.println("flag: =========="+flag);
		Group_Basicinfo group = gdi.queryGroup(post_group);
		request.setAttribute("group", group);
		List<Group_Post> posts = gdi.queryAllPosts(post_group);
		request.setAttribute("posts", posts);
		List<Item_Post> item_posts = new ArrayList<Item_Post>();
		if(posts != null){
			for (Group_Post group_Post : posts) {
				Item_Post item = new Item_Post();
				item.setPost_id(group_Post.getId());
				item.setPost_title(group_Post.getPost_title());
//				int reply_count = gd.queryAllReplies(group_Post.getId()).size();
				List<Reply> r = gdi.queryAllReplies(group_Post.getId());
				if(r != null){
					int reply_count = r.size();
					item.setReply_count(reply_count);
				}else{
					item.setReply_count(0);
				}
//				String recentReply_time = gd.queryRecentReplyTime(group_Post.getId()).getReply_time();
				Reply r2 = gdi.queryRecentReplyTime(group_Post.getId());
				if(r2 != null){
					String recentReply_time = r2.getReply_time();
					item.setRecentReply_time(recentReply_time);
				}else{
					item.setRecentReply_time(group_Post.getPost_pubtime());
				}
				User_Info u = gdi.queryUserById(group_Post.getPost_author());
				item.setAuthor_id(group_Post.getPost_author());
				item.setAuthor_name(u.getUsername());
				item.setGroup_id(post_group);
				item.setGroup_name(group.getGroup_name());
				item_posts.add(item);
			}
			
		}
		Group_Role role = new Group_Role();
		
		if(session.getAttribute("current_user") != null){
		
			role = gdi.queryUserRole(post_group, user.getUser_id());
		}
		System.out.println("现在查询的角色是："+role.getGroup_role_name());
		session.setAttribute("role", role);
		request.setAttribute("posts", item_posts);
		request.getRequestDispatcher("/nxt/douban_group_singleGroup.jsp").forward(request, response);
	}

	private void queryMyGroupByRole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 按角色查询所属小组
		HttpSession session = request.getSession();
		User_Info user = (User_Info)session.getAttribute("current_user");
		List<Group_List> gl = gdi.queryGroupList(user.getUser_id());
		List<Group_Basicinfo> normal_list = new ArrayList<>();
		List<Group_Basicinfo> creator_list = new ArrayList<>();
		List<Group_Basicinfo> admin_list = new ArrayList<>();
		for (Group_List group_List : gl) {
			if(group_List.getRole_id() == 1){
				creator_list.add(gdi.queryGroup(group_List.getGroup_id()));
				request.setAttribute("creator_role", 1);
			}
				else if(group_List.getRole_id() == 2){
					admin_list.add(gdi.queryGroup(group_List.getGroup_id()));
					request.setAttribute("admin_role", 2);
				}
			else {
				normal_list.add(gdi.queryGroup(group_List.getGroup_id()));
				request.setAttribute("normal_role", 3);
			}
		}
		request.setAttribute("creator_list", creator_list);
		request.setAttribute("normal_list", normal_list);
		request.setAttribute("admin_list", admin_list);
		request.getRequestDispatcher("/nxt/douban_group_myJoinGroup.jsp").forward(request, response);
		
	}

	private void aheadMyHomepage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User_Info user = (User_Info)session.getAttribute("current_user");
		if(user != null){
//			System.out.println("user id~~~~~~~~`"+user.getEmail());
			//查询用户一共加入多少小组
			List<Group_Basicinfo> groups = gdi.queryUserGroups(user.getUser_id());
//			System.out.println("groups's size::::"+groups.size());
			//构造Item_Post对象
			List<Item_Post> item_Post = new ArrayList<Item_Post>();
			for (Group_Basicinfo group_Basicinfo : groups) {
//				System.out.println("每一个group的id是："+group_Basicinfo.getId());
//				System.out.println("当前要查询的所有帖子所属的小组id是："+group_Basicinfo.getId());
				List<Group_Post> p = gdi.queryAllPosts(group_Basicinfo.getId());
				if(p != null){
				for (Group_Post group_Post : p) {
					System.out.println("当前的groupPost是："+group_Post.getId());
					Item_Post item = new Item_Post();
					item.setPost_id(group_Post.getId());
					item.setPost_title(group_Post.getPost_title());
//					int reply_count = gd.queryAllReplies(group_Post.getId()).size();
					List<Reply> r = gdi.queryAllReplies(group_Post.getId());
					if(r != null){
						int reply_count = r.size();
						item.setReply_count(reply_count);
					}else{
						item.setReply_count(0);
					}
//					String recentReply_time = gd.queryRecentReplyTime(group_Post.getId()).getReply_time();
					Reply r2 = gdi.queryRecentReplyTime(group_Post.getId());
					if(r2 != null){
						String recentReply_time = r2.getReply_time();
						item.setRecentReply_time(recentReply_time);
					}else{
						item.setRecentReply_time(group_Post.getPost_pubtime());
					}
					User_Info u = gdi.queryUserById(group_Post.getPost_author());
					item.setAuthor_id(group_Post.getPost_author());
					item.setAuthor_name(u.getUsername());
					item.setGroup_id(group_Basicinfo.getId());
					item.setGroup_name(group_Basicinfo.getGroup_name());
					item_Post.add(item);
				}}
			}
			//查询用户共发起多少帖子
			int pub_posts = gdi.queryUserPosts(user.getUser_id()).size();
			System.out.println("发起的帖子数："+pub_posts);
			List<Group_Post> pub = gdi.queryUserPosts(user.getUser_id());
			request.setAttribute("pub_posts", pub_posts);
			request.setAttribute("pub", pub);
			//查询用户共回复多少帖子
			int reply_posts = gdi.queryUserReplies(user.getUser_id()).size();
			System.out.println("回复的帖子数"+reply_posts);
			List<Group_Post> rep = gdi.queryUserReplies(user.getUser_id());
			request.setAttribute("rep", rep);
			request.setAttribute("reply_posts", reply_posts);
			//查询用户共回复过多少帖子
//			System.out.println("MyPosts:========"+my_posts.size());
			request.setAttribute("groups", groups);
			request.setAttribute("group_count", groups.size());
			request.setAttribute("my_posts", item_Post);
			request.getRequestDispatcher("/nxt/douban_group_myHomepage.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/nxt/usrLogin.jsp").forward(request, response);
		}
		
		
	}

	private void queryMyPosts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 查询我的小组话题
		HttpSession session = request.getSession();
		User_Info user = (User_Info)session.getAttribute("current_user");
		if(user != null){
//			System.out.println("user id~~~~~~~~`"+user.getEmail());
			//查询用户一共加入多少小组
			List<Group_Basicinfo> groups = gdi.queryUserGroups(user.getUser_id());
//			System.out.println("groups's size::::"+groups.size());
			//构造Item_Post对象
			List<Item_Post> item_Post = new ArrayList<Item_Post>();
			for (Group_Basicinfo group_Basicinfo : groups) {
//				System.out.println("每一个group的id是："+group_Basicinfo.getId());
//				System.out.println("当前要查询的所有帖子所属的小组id是："+group_Basicinfo.getId());
				List<Group_Post> p = gdi.queryAllPosts(group_Basicinfo.getId());
				if(p != null){
				for (Group_Post group_Post : p) {
					System.out.println("当前的groupPost是："+group_Post.getId());
					Item_Post item = new Item_Post();
					item.setPost_id(group_Post.getId());
					item.setPost_title(group_Post.getPost_title());
//					int reply_count = gd.queryAllReplies(group_Post.getId()).size();
					List<Reply> r = gdi.queryAllReplies(group_Post.getId());
					if(r != null){
						int reply_count = r.size();
						item.setReply_count(reply_count);
					}else{
						item.setReply_count(0);
					}
//					String recentReply_time = gd.queryRecentReplyTime(group_Post.getId()).getReply_time();
					Reply r2 = gdi.queryRecentReplyTime(group_Post.getId());
					if(r2 != null){
						String recentReply_time = r2.getReply_time();
						item.setRecentReply_time(recentReply_time);
					}else{
						item.setRecentReply_time(group_Post.getPost_pubtime());
					}
					User_Info u = gdi.queryUserById(group_Post.getPost_author());
					item.setAuthor_id(group_Post.getPost_author());
					item.setAuthor_name(u.getUsername());
					item.setGroup_id(group_Basicinfo.getId());
					item.setGroup_name(group_Basicinfo.getGroup_name());
					item_Post.add(item);
				}}
			}
			//查询用户共发起多少帖子
			int pub_posts = gdi.queryUserPosts(user.getUser_id()).size();
			System.out.println("发起的帖子数："+pub_posts);
			request.setAttribute("pub_posts", pub_posts);
			//查询用户共回复多少帖子
			int reply_posts = gdi.queryUserReplies(user.getUser_id()).size();
			System.out.println("回复的帖子数"+reply_posts);
			request.setAttribute("reply_posts", reply_posts);
			//查询用户共回复过多少帖子
//			System.out.println("MyPosts:========"+my_posts.size());
			request.setAttribute("groups", groups);
			request.setAttribute("my_posts", item_Post);
			request.getRequestDispatcher("/nxt/douban_group_myGroupPosts.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/nxt/index.jsp").forward(request, response);
		}
		
	}

	private void queryAllPostsByHot(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Group_Post> posts = gdi.queryPostsByHot();
		request.setAttribute("posts", posts);
		request.getRequestDispatcher("/nxt/douban_group_explore.jsp").forward(request, response);
	}

	private void queryPostByCondition(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 按条件查询帖子
		String cond = request.getParameter("cond");
		List<Group_Post> posts = gdi.queryPostsByCondition(cond);
		request.setAttribute("posts", posts);
		request.getRequestDispatcher("/nxt/douban_group_exploreByCond.jsp").forward(request, response);
		
	}

	private void userAddGroup(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void userAddReply(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 用户回复
		String i = request.getParameter("post_id");
		int post_id = Integer.parseInt(i);
		int reply_author = Integer.parseInt(request.getParameter("reply_author"));
		String reply_content = request.getParameter("reply_content");
		Reply reply1 = new Reply();
		reply1.setReply_author(reply_author);
		reply1.setReply_post(post_id);
		reply1.setReply_time(new Date().toLocaleString());
		reply1.setReply_content(reply_content);
		reply1.setReply_parent(0);
		System.out.println(reply1);
		int flag = gdi.userAddReply(reply1);
	
		//根据帖子的id查询帖子的所有内容
		Group_Post post = gdi.querySinglePost(post_id);
		//根据帖子用户外键（post_author）查询帖子作者的信息
		User_Info post_author = gdi.queryUserById(post.getPost_author());
		
		//根据帖子的id查询帖子的所有回复
		List<Reply> replies = gdi.queryAllReplies(post_id);
		List<Item_Reply> item_replies = new ArrayList<Item_Reply>();
		if(replies != null){
		//根据查询到的信息，构造Item_Reply
		
		for (Reply reply : replies) {
			User_Info u = gdi.queryUserById(reply.getReply_author());
			//构造一个回复项，item_reply
			Item_Reply item = new Item_Reply();
			item.setUsername(u.getUsername());
			item.setImgs(u.getImgs());
			item.setReply_time(reply.getReply_time());
			item.setReply_content(reply.getReply_content());
			item_replies.add(item);
		}
		}
		request.setAttribute("post", post);
		request.setAttribute("post_author", post_author);
		request.setAttribute("item_replies", item_replies);
		request.getRequestDispatcher("/nxt/douban_group_singlePost.jsp").forward(request, response);
	}

	private void queryUserReply(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO查询用户回复的帖子
		String i = request.getParameter("user_id");
		int user_id = Integer.parseInt(i);
		List<Item_Post> item_Post = new ArrayList<Item_Post>();
		
		List<Group_Post> posts = gdi.queryUserReplies(user_id);
		request.setAttribute("reply_posts", posts);
		request.setAttribute("counts", posts.size());
		request.getRequestDispatcher("/nxt/douban_group_myReplyPosts.jsp").forward(request, response);
		
	}

	private void queryUserPub(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 查询用户发起的帖子
		String i = request.getParameter("user_id");
		int user_id = Integer.parseInt(i);
		List<Item_Post> item_Post = new ArrayList<Item_Post>();
		
		List<Group_Post> posts = gdi.queryUserPosts(user_id);
		request.setAttribute("pub_posts", posts);
		request.setAttribute("counts", posts.size());
		request.getRequestDispatcher("/nxt/douban_group_myPubPosts.jsp").forward(request, response);
		
	}

	private void queryOnePost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 根据id查询每个帖子的内容和所有回复
		String i = request.getParameter("post_id");
		int post_id = Integer.parseInt(i);
		//根据帖子的id查询帖子的所有内容
		Group_Post post = gdi.querySinglePost(post_id);
		//根据帖子用户外键（post_author）查询帖子作者的信息
		User_Info post_author = gdi.queryUserById(post.getPost_author());
		
		//根据帖子的id查询帖子的所有回复
		List<Reply> replies = gdi.queryAllReplies(post_id);
		List<Item_Reply> item_replies = new ArrayList<Item_Reply>();
		if(replies != null){
		//根据查询到的信息，构造Item_Reply
		
		for (Reply reply : replies) {
			User_Info u = gdi.queryUserById(reply.getReply_author());
			//构造一个回复项，item_reply
			Item_Reply item = new Item_Reply();
			item.setUsername(u.getUsername());
			item.setImgs(u.getImgs());
			item.setReply_time(reply.getReply_time());
			item.setReply_content(reply.getReply_content());
			item_replies.add(item);
		}
		}
		request.setAttribute("post", post);
		request.setAttribute("post_author", post_author);
		request.setAttribute("item_replies", item_replies);
		request.getRequestDispatcher("/nxt/douban_group_singlePost.jsp").forward(request, response);
	}

	private void queryOneGroup(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO根据小组id查询小组信息
		String i = request.getParameter("group_id");
//		System.out.println("i-----------------------"+i);
		int group_id = Integer.parseInt(i);

		Group_Basicinfo group = gdi.queryGroup(group_id);
		request.setAttribute("group", group);
		List<Group_Post> posts = gdi.queryAllPosts(group_id);
//		request.setAttribute("posts", posts);
		if(posts != null){
		List<Item_Post> item_posts = new ArrayList<Item_Post>();
		for (Group_Post group_Post : posts) {
			Item_Post item = new Item_Post();
			item.setPost_id(group_Post.getId());
			item.setPost_title(group_Post.getPost_title());
//			int reply_count = gd.queryAllReplies(group_Post.getId()).size();
			List<Reply> r = gdi.queryAllReplies(group_Post.getId());
			if(r != null){
				int reply_count = r.size();
				item.setReply_count(reply_count);
			}else{
				item.setReply_count(0);
			}
//			String recentReply_time = gd.queryRecentReplyTime(group_Post.getId()).getReply_time();
			Reply r2 = gdi.queryRecentReplyTime(group_Post.getId());
			if(r2 != null){
				String recentReply_time = r2.getReply_time();
				item.setRecentReply_time(recentReply_time);
			}else{
				item.setRecentReply_time(group_Post.getPost_pubtime());
			}
			User_Info u = gdi.queryUserById(group_Post.getPost_author());
			item.setAuthor_id(group_Post.getPost_author());
			item.setAuthor_name(u.getUsername());
			item.setGroup_id(group_id);
			item.setGroup_name(group.getGroup_name());
			item_posts.add(item);
		}
		Group_Role role = new Group_Role();
		HttpSession session = request.getSession();
		if(session.getAttribute("current_user") != null){
			User_Info user = (User_Info)session.getAttribute("current_user");
			role = gdi.queryUserRole(group_id, user.getUser_id());
		}
		System.out.println("现在查询的角色是："+role.getGroup_role_name());
		session.setAttribute("role", role);
		request.setAttribute("posts", item_posts);
		request.getRequestDispatcher("/nxt/douban_group_singleGroup.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/nxt/douban_group_noPosts.jsp").forward(request, response);
		}
	}

	private void adminEditGroup(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO 管理员编辑小组基本信息
//		String a = request.getParameter("member_nick");
//		System.out.println("从前台传来的成员昵称为："+a);
//		String img = request.getParameter("img");
//		System.out.println("图片路径是："+img);
		Group_Basicinfo  group = new Group_Basicinfo();
		group = EntityAddClass.upLoadAddressAndReturnEntity(group, request);
        group.setGroup_createtime(ut.getCurrentAccurateTime()+"");
   
		int flag = gdi.adminUpdateGroupInfo(group);
//		System.out.println(flag);
		if(flag > 0){
			 
		request.getRequestDispatcher("/groupServlet?method=queryMyPosts").forward(request, response);
		}
	}

	private void createGroup(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 创建小组
		HttpSession session = request.getSession();
		User_Info user = (User_Info)session.getAttribute("current_user");
		Group_Basicinfo group = EntityClass.returnEntity(new Group_Basicinfo(), request);
//		System.out.println("group_dd~~~~~~~~~~~"+group.getGroup_name());
		group.setGroup_createtime(new Date().toLocaleString());
		group.setImgs("/Douban_Group/images/ads/user_normal.jpg");
		System.out.println(group);
		System.out.println("servlet层的User是："+user.getUsername());
		Group_Basicinfo g = gdi.createGroup(group,user);
		
		if (g != null) {
			request.setAttribute("group", g);
			request.getRequestDispatcher("/nxt/douban_group_editGroup.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/nxt/douban_group_apply.jsp").forward(request, response);
		}
	}
	
	private void UserOftenJoinGroup(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO 查询用户参与的所有小组
		List<Group_List> list = gdi.queryGroupList(Integer.parseInt(request.getParameter("user_id")));
		if(list != null){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Group_List group_List = (Group_List) iterator.next();
				Group_Basicinfo gb = new Group_Basicinfo();
				gb = psifImpl.showOneByR(group_List.getGroup_id()+"", gb);
				group_List.setGroup(gb);
			}
			request.setAttribute("oftengroup", list);
			request.setAttribute("oftengroup_size", list.size());
		}else{
			request.setAttribute("oftengroup", null);
		}
		request.getRequestDispatcher("/lsr/douban_mydouban.jsp").forward(request, response);
		
	}

}
