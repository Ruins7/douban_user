package com.services.hyy.activity;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.hyy.user.UserDao;
import com.dao.hyy.user.impl.UserDaoImpl;
import com.entity.lsr.user.User_Info;
/**
 * 向朋友发起邀请
 * @author 胡伊杨
 *
 */
@WebServlet(
		name="friendAttend",
		urlPatterns={"/hyy/service/FriendAttendServlet"}
		)
public class FriendAttendServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String method= request.getParameter("method");
		
		if("searchFriend".equals(method)){
			searchFriend(request,response);
		}
		
	}
	
	/**
	 * 查找我的好友
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchFriend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			UserDao ud = new UserDaoImpl();
			HttpSession session = request.getSession();
			User_Info user = (User_Info)session.getAttribute("current_user");
			List<User_Info> friendList = ud.searchFriend(user.getUser_id());
			request.setAttribute("friendList", friendList);
			request.getRequestDispatcher("activity/friendAttend.jsp").forward(request, response);
	}
}
