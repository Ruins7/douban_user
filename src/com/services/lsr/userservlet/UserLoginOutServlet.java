package com.services.lsr.userservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.commontools.lsr.UserFocusAndBeenFocus;
import com.commontools.lsr.UserFootStep;
import com.commontools.lsr.UserTools;
import com.dao.hyy.activity.OffActivityDao;
import com.dao.hyy.activity.impl.OffActivityDaoImpl;
import com.dao.lsr.book.dao.BookDao;
import com.dao.lsr.book.daoImpl.BookDaoImpl;
import com.dao.lsr.dbutils.daoImpl.DaoOperationImpl;
import com.dao.lsr.user.daoImpl.BroRecoAndLikeImpl;
import com.dao.lsr.user.daoImpl.PersonalInfoImpl;
import com.dao.wj.moviedao.MovieDao;
import com.entity.hyy.city.City;
import com.entity.lsr.user.User_Info;



@WebServlet(name="UserLoginOutServlet", urlPatterns={"/user/userlogin"})
public class UserLoginOutServlet extends HttpServlet {

	UserTools tools = new UserTools();
	BroRecoAndLikeImpl bralimpl = new BroRecoAndLikeImpl();
	UserFootStep footstep = new UserFootStep();
	UserTools ut = new UserTools();
	PersonalInfoImpl psifImpl = new PersonalInfoImpl();
	DaoOperationImpl doImpl = new DaoOperationImpl();
	MovieDao md = new MovieDao();
	BookDao bd = new BookDaoImpl();
	OffActivityDao offd = new OffActivityDaoImpl(); 
	
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("UserLogin".equalsIgnoreCase(method)){
      	  UserLogin(request, response);
        }else if("UserPreLogin".equalsIgnoreCase(method)){
      	  UserPreLogin(request, response);
        }
	}
	
	public void UserLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 HttpSession session = request.getSession();
		//用户登录 user admin
		String checkcode = (String) session.getAttribute("vCode");
		//登陆验证码
	 	// if(checkcode.equalsIgnoreCase(request.getParameter("checkcode"))){
			 User_Info user = psifImpl.login(request.getParameter("username"),request.getParameter("pwd"));
				if(user != null){
					System.out.println(" 登陆成功");
					City c = new City();
				    c = psifImpl.showOneByR(user.getLocation()+"", c);
				    user.setCity(c);
					session.setAttribute("current_user", user);
					//生成cookie
					//判断是否勾选 记住我
			    	if("yes".equalsIgnoreCase(request.getParameter("freetologin"))){
			    		//创建cookie
			    		Cookie cookie = new Cookie("user_info" , request.getParameter("username")+","+request.getParameter("pwd"));
			    		cookie.setMaxAge(60*60*24);//cookie有效期：一天
			    		response.addCookie(cookie);
			    		System.out.println("cookie创建成功！");
			    	}
					//当用户登录成功时（手动登陆和cookie登陆）
					//获取用户浏览记录的所有cookie到session中
					//footstep.readFootStepFromCookie(request);
					//获取我的好友信息
					UserFocusAndBeenFocus.findWhoFocusMeAndMyFocus(request);
					//获取系统豆邮到session
					//UserGetActivity.getActOfMe(request, user.getUser_id()+"");
					//../things/thingsinfo?method=searchAllThingsByPage
					request.getRequestDispatcher("../user/userinfo?method=searchFriendsBroByPage&user_id="+user.getUser_id()).forward(request, response);
		 		}else{
					//重新登陆
					System.out.println("登陆失败");
					response.sendRedirect("../lsr/douban_login.jsp");
				} 
		  //}else{
		  // System.out.println("验证码不匹配");
		  //	 response.sendRedirect("../lsr/douban_login.jsp");
	      //}
		
		
		
	}
	
	public void UserPreLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" --------------prelogin--------------");
		//用户cookie登陆(项目上来预登陆)
	    HttpSession session = request.getSession();
	    Cookie[] cookies = request.getCookies();
	    if(cookies != null){//有cookies
	    	System.out.println(" -------------有cookie-------------");
			int i = 0;
			for (Cookie cookie : cookies) {
				if("user_info".equalsIgnoreCase(cookie.getName())){
					System.out.println("找到cookie");
					String[] value = cookie.getValue().split(",");	 			 				
					User_Info user = psifImpl.login(value[0], value[1]);
				    if(user != null){
				    	i+=1;
				    	session.setAttribute("current_user", user);
				    	//cookie登陆成功
				    	//当用户登录成功时（手动登陆和cookie登陆）
						//获取用户浏览记录的所有cookie到session中
						//footstep.readFootStepFromCookie(request);
				    	request.getRequestDispatcher("../user/userinfo?method=searchFriendsBroByPage&user_id="+user.getUser_id()).forward(request, response);
				    } 
				    break;
				}
			}
			if(i == 0){
				//没有找到cookie，登陆失败
				System.out.println(" -------------没有cookie-------------");
				response.sendRedirect("../lsr/douban_login.jsp");
			}		
		}else{
			//没有找到cookie，登陆失败
			System.out.println(" -------------没有cookie-------------");
			response.sendRedirect("../lsr/douban_login.jsp");
		} 
	
	}

}
