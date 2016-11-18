package com.services.lsr.userservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.lsr.user.daoImpl.PersonalInfoImpl;
import com.entity.hyy.city.City;
import com.entity.lsr.user.User_Info;
import com.entity.lsr.user.User_Status;

 //系统向用户发豆邮或者发送通知

@WebServlet(name="UserMailNoticeServlet", urlPatterns={"/user/sandmailnotice"}, asyncSupported = true)
//@MultipartConfig(location = "c:/", maxFileSize = 1024 * 1024 * 1000)
public class UserMailNoticeServlet extends HttpServlet {

	PersonalInfoImpl plImpl = new PersonalInfoImpl();
	 
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String method = request.getParameter("method");
		 if("sysDouMail".equalsIgnoreCase(method)){
			 sysDouMail(request, response);
		 }else if("sysNotice".equalsIgnoreCase(method)){
			 sysNotice(request, response);
		 }else if("sysRecommendBaseOnUserBrowseRecord".equalsIgnoreCase(method)){
			 sysRecommendBaseOnUserBrowseRecord(request, response);
		 }else if("updateUserBrowseRecord".equalsIgnoreCase(method)){
			 updateUserBrowseRecord(request, response);
		 }else if("showPersonalInfo".equalsIgnoreCase(method)){
			 try {
				showPersonalInfo(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
	}
	public void sysDouMail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
         // 系统豆邮
		 
	}
	public void sysNotice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 // 系统通知
		
	}
	public void sysRecommendBaseOnUserBrowseRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//系统推荐
		//从session中获取用户浏览记录（全类型），计算推荐的obj,并且返回到页面上
		//东西
		//List<Things> list = ut.getLinkedListBaseOnFootStep(request, response, new Things());
	}
	public void updateUserBrowseRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 更新用户浏览记录---在session中的,每次访问都要更新（全类型）
		// 东西
		
	}
	//查找某个用户的个人信息
	public void showPersonalInfo(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException{
	    User_Info user = new User_Info();
		user = plImpl.showOneByR(request.getParameter("user_id"), user);
		if(user != null && user.getStatus() != 1){//1 为活跃
			City c = new City();
			c = plImpl.showOneByR(user.getLocation()+"", c);
			user.setCity(c);
			User_Status state = new User_Status();
			state = plImpl.showOneByR(user.getStatus()+"", state);
			user.setUser_state(state);
			request.setAttribute("this_people", user);
			request.getRequestDispatcher("/lsr/douban_modify.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/lsr/douban_mydouban.jsp").forward(request, response);
		}
	}

}
