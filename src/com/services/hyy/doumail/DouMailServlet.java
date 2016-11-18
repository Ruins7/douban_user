package com.services.hyy.doumail;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.hyy.douMail.DouMailDao;
import com.dao.hyy.douMail.impl.DouMailDaoImpl;
import com.dao.hyy.user.UserDao;
import com.dao.hyy.user.impl.UserDaoImpl;
import com.entity.hyy.doumail.MailComment;
import com.entity.lsr.user.User_Info;
/**
 * 豆邮
 * @author 胡伊杨
 *
 */
@WebServlet(
		name="DouMailServlet",
		urlPatterns={"/hyy/service/DouMailServlet"}
		)
public class DouMailServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		User_Info user = (User_Info)session.getAttribute("current_user");
		
		String method = request.getParameter("method");
		if("getMyMailArray".equals(method)){
			getMyMailArray(request,response);
		}
		else if("meToOne".equals(method)){
			meToOne(request,response);
		}else if("sendDouMail".equals(method)){
			saveMailComment(request,response);
		}else if("deleteMailComment".equals(method)){
			deleteMailComment(request,response);
		}else if("deleteOther".equals(method)){
			deleteOther(request,response);
		}else if("saveMailComment".equals(method)){
			saveMailComment(request,response);
		}else if("searchFriend".equals(method)){
			searchFriend(request,response);
		}
	}

	/**
	 * 删除我和某一用户的所有豆邮
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteOther(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			int my_id = Integer.parseInt(request.getParameter("my_id"));
			int other_id = Integer.parseInt(request.getParameter("other_id"));
			int um_id = Integer.parseInt(request.getParameter("um_id"));
			DouMailDao dmd = new DouMailDaoImpl();
			dmd.deleteOtherMail(my_id, um_id);
			getMyMailArray(request, response);
	}
	
	/**
	 * 获得我的豆邮列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getMyMailArray(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		int myUser_id = Integer.parseInt(request.getParameter("myUser_id"));
//			System.out.println(myUser_id);
			HttpSession session = request.getSession();
			User_Info user = (User_Info)session.getAttribute("current_user");
			DouMailDao dmd = new DouMailDaoImpl();
			List<MailComment> list = dmd.getMyMailArray(user.getUser_id());
			System.out.println("============="+list.get(0).getUserMail().getUserMail_id());
			request.setAttribute("list", list);
			System.out.println("大小是:"+list.size());
			System.out.println("图片信息："+list.get(0).getUserMail().getUser_id_to().getImgs());
			request.getRequestDispatcher("/douban_myDoumail.jsp").forward(request, response);
	}
	
	/**
	 * 获得我和某一用户的所有豆邮信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void meToOne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DouMailDao dmd;
			dmd = new DouMailDaoImpl();
			UserDao ud = new UserDaoImpl();
			HttpSession session = request.getSession();
			User_Info me = (User_Info)session.getAttribute("current_user");
			int other = Integer.parseInt(request.getParameter("other_id"));
			System.out.println("myId:"+me.getUser_id()+",other_id:"+other);
			List<MailComment> mailCommentList = dmd.getUserToUserMail(me.getUser_id(), other);
			System.out.println("id是=========="+mailCommentList.get(0).getUserMail().getUserMail_id());
			request.setAttribute("mailCommentList", mailCommentList);
			request.setAttribute("other", ud.getUser(other));
			request.getRequestDispatcher("/douban_meToOneEmail.jsp").forward(request, response);
	}
	
	/**
	 * 发送一条豆邮
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void saveMailComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DouMailDao dmd;
			dmd = new DouMailDaoImpl();
			HttpSession session = request.getSession();
			User_Info me = (User_Info)session.getAttribute("current_user");
			int other = Integer.parseInt(request.getParameter("other_id"));
			MailComment mc = new MailComment();
			String content = request.getParameter("content");
			System.out.println("=========="+content);
			mc.setComment_content(content);
			mc.setComment_time(new Date());
			mc.setSend_user_id(me);
			UserDaoImpl ud = new UserDaoImpl();			
			
			mc.setRecive_user_id(ud.getUser(other));
			int affectRows = dmd.saveMailComment(mc);
			System.out.println(affectRows+"行记录受影响");
			meToOne(request,response);
	}
	
	/**
	 * 删除单条豆邮
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteMailComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	 
		int mailComment_id = Integer.parseInt(request.getParameter("mailComment_id"));
			HttpSession session = request.getSession();
			User_Info me = (User_Info)session.getAttribute("current_user");
			DouMailDao dmd = new DouMailDaoImpl();
			dmd.deleteComment(mailComment_id, me.getUser_id());
			getMyMailArray(request, response);
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
			request.getRequestDispatcher("/douban_selectFrient.jsp").forward(request, response);
	}
}
