/**
 * 
 */
package com.commontools.lsr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dao.lsr.user.daoImpl.PersonalInfoImpl;
import com.entity.lsr.user.User_Info;

/**
 * @ClassName:     UserFocusAndBeenFocus.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 *                 关注我的好友和我关注的好友的工具类
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月18日 上午11:29:11 
 */
public class UserFocusAndBeenFocus {
	
	private static PersonalInfoImpl psifImpl = new PersonalInfoImpl();
	
	//用户登录时初始化其好友信息
	public static void findWhoFocusMeAndMyFocus(HttpServletRequest request){
		HttpSession session = request.getSession();
		User_Info user = (User_Info) session.getAttribute("current_user");
		List<User_Info> who_focus_me_users = psifImpl.whoFocusMe(user.getUser_id()+"");
		if(who_focus_me_users != null){
			session.setAttribute("whofocusme", who_focus_me_users.size());
		}else{
			session.setAttribute("whofocusme", 0);
		}
		List<User_Info> my_focus_users = psifImpl.MyFocus(user.getUser_id()+"");
		if(my_focus_users != null){
			session.setAttribute("allmyfocus", my_focus_users);//所有我关注的人
			if (my_focus_users.size() > 5) {
				session.setAttribute("myfocus", my_focus_users.subList(0, 5));//前5个我关注的人
			}	 
			    session.setAttribute("myfocus", my_focus_users);
			    session.setAttribute("myfocusnum", my_focus_users.size());
		}else{
			session.setAttribute("myfocus", 0);
		}
	}
	
	//查看其它用户的主页时调用
	public static void findWhoFocusMeAndMyFocusForOthers(HttpServletRequest request){
	 
		String user_id = request.getParameter("user_id");
		List<User_Info> who_focus_me_users = psifImpl.whoFocusMe(user_id);
		if(who_focus_me_users != null){
			request.setAttribute("whofocusme", who_focus_me_users.size());
		}else{
			request.setAttribute("whofocusme", 0);
		}
		List<User_Info> my_focus_users = psifImpl.MyFocus(user_id);
		if(my_focus_users != null){
			request.setAttribute("allmyfocus", my_focus_users);//所有ta关注的人
			if (my_focus_users.size() > 5) {
				request.setAttribute("myfocus", my_focus_users.subList(0, 5));//前5个ta关注的人
			}	 
			request.setAttribute("myfocus", my_focus_users);
			request.setAttribute("myfocusnum", my_focus_users.size());
		}else{
			request.setAttribute("myfocus", 0);
		}
	}

}
