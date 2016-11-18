package com.services.nxt.group;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.nxt.group.daoImpl.GroupDaoImpl;
import com.entity.lsr.user.User_Info;
import com.entity.nxt.group.Group_Basicinfo;
import com.entity.nxt.group.Group_Post;
import com.entity.nxt.group.Reply;
import com.entity.util.nxt.group.Item_Post;

@WebServlet(
		name = "userLoginServlet",
		urlPatterns = {"/userLoginServlet"},
		loadOnStartup = 5
		
		)
public class UserLoginServlet extends HttpServlet {


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		GroupDaoImpl gd = new GroupDaoImpl();
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		User_Info user = gd.userLogin(username, pwd);
		if(user != null){
			//把用户登录信息存在session中
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
//			System.out.println("user id~~~~~~~~`"+user.getEmail());
			//查询用户一共加入多少小组
			List<Group_Basicinfo> groups = gd.queryUserGroups(user.getUser_id());
//			System.out.println("groups's size::::"+groups.size());
			//构造Item_Post对象
			List<Item_Post> item_Post = new ArrayList<Item_Post>();
			for (Group_Basicinfo group_Basicinfo : groups) {
//				System.out.println("每一个group的id是："+group_Basicinfo.getId());
//				System.out.println("当前要查询的所有帖子所属的小组id是："+group_Basicinfo.getId());
				List<Group_Post> p = gd.queryAllPosts(group_Basicinfo.getId());
				if(p != null){
				for (Group_Post group_Post : p) {
					System.out.println("当前的groupPost是："+group_Post.getId());
					Item_Post item = new Item_Post();
					item.setPost_id(group_Post.getId());
					item.setPost_title(group_Post.getPost_title());
//					int reply_count = gd.queryAllReplies(group_Post.getId()).size();
					List<Reply> r = gd.queryAllReplies(group_Post.getId());
					if(r != null){
						int reply_count = r.size();
						item.setReply_count(reply_count);
					}else{
						item.setReply_count(0);
					}
//					String recentReply_time = gd.queryRecentReplyTime(group_Post.getId()).getReply_time();
					Reply r2 = gd.queryRecentReplyTime(group_Post.getId());
					if(r2 != null){
						String recentReply_time = r2.getReply_time();
						item.setRecentReply_time(recentReply_time);
					}else{
						item.setRecentReply_time(group_Post.getPost_pubtime());
					}
					User_Info u = gd.queryUserById(group_Post.getPost_author());
					item.setAuthor_id(group_Post.getPost_author());
					item.setAuthor_name(u.getUsername());
					item.setGroup_id(group_Basicinfo.getId());
					item.setGroup_name(group_Basicinfo.getGroup_name());
					item_Post.add(item);
				}}
			}
			//查询用户共发起多少帖子
			int pub_posts = gd.queryUserPosts(user.getUser_id()).size();
			System.out.println("发起的帖子数："+pub_posts);
			request.setAttribute("pub_posts", pub_posts);
			//查询用户共回复多少帖子
			int reply_posts = gd.queryUserReplies(user.getUser_id()).size();
			System.out.println("回复的帖子数"+reply_posts);
			request.setAttribute("reply_posts", reply_posts);
			//查询用户共回复过多少帖子
//			System.out.println("MyPosts:========"+my_posts.size());
			request.setAttribute("groups", groups);
			request.setAttribute("my_posts", item_Post);
			request.getRequestDispatcher("/douban_group_myGroupPosts.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

}
