package com.filter.lsr;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.lsr.user.User_Info;

@WebFilter(filterName="UserLoginFilter", urlPatterns="/*")
public class UserLoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	 
	}

	@Override
	public void doFilter(ServletRequest req , ServletResponse resp ,
			FilterChain chain) throws IOException, ServletException {
		  HttpServletRequest request = (HttpServletRequest) req ;  
		  HttpServletResponse response = (HttpServletResponse) resp;  
		  HttpSession session = request.getSession(); 
		  String uri = request.getRequestURI();
		  System.out.println("uri:    "+uri);
		  User_Info user = (User_Info) session.getAttribute("current_user");
			  if(user != null){ 
				  chain.doFilter(request, response);
			  }else if(uri.equalsIgnoreCase("/douban_user/user/userlogin") || uri.equalsIgnoreCase("/douban_user/user/validationServlet") || uri.contains("/douban_user/lsr/")){
				  //user登陆login,预登陆UserPreLogin,登陆、注册验证码,注册页面 登陆页面的所有js,css,img  不被过滤      
				  chain.doFilter(request, response);
			  }else{
				  request.getRequestDispatcher("./lsr/douban_login.jsp").forward(request, response);
			  }
		  
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	 
	}

}
