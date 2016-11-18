package com.services.wj.movieservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.commontools.lsr.EntityClass;
import com.commontools.lsr.UserTools;
import com.dao.wj.moviedao.RuinsShortCommentDao;
import com.dao.wj.moviedao.ShortCommentDao;
import com.entity.lsr.user.User_Info;
import com.entity.wj.movie.ShortMovieComment;
/**
 * 电影短评，打分
 * @author 汪进
 *
 */
@WebServlet(name="ShortMovieCommentServlet",urlPatterns="/servlet/ShortMovieCommentServlet")
public class ShortMovieCommentServlet extends HttpServlet {
	 ShortCommentDao shortCommentDao=new ShortCommentDao();
	 RuinsShortCommentDao rs = new RuinsShortCommentDao();
	   ShortMovieComment shortComment= new ShortMovieComment();
	   UserTools ut = new UserTools();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
         String method=request.getParameter("method");
         System.out.println(method);
         if("addshortComment".equalsIgnoreCase(method)){
        	 try {
				addshortComment(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }else if("delshortComment".equalsIgnoreCase(method)){
        	 delshortComment(request,response);
         }else if("allshortComments".equalsIgnoreCase(method)){
        	 allfindshortComments(request,response);
         }else if("updateshortComment".equalsIgnoreCase(method)){
        	 updateshortComment(request,response);
         }
         


	}
	//添加短评，前台
	public void addshortComment(HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		 ShortMovieComment shortComment= new ShortMovieComment();
		 shortComment = EntityClass.returnEntity(shortComment, request);
		 shortComment.setDate(ut.getCurrentAccurateTime());
		 HttpSession session = request.getSession();
		 User_Info user = (User_Info) session.getAttribute("current_user");
		 shortComment.setUser_id(user.getUser_id());
		 int result = rs.addShortComment(shortComment);
		 PrintWriter out = response. getWriter();
         if(result > 0) {
             out.print(true );
         } else {
             out.print(false ); 
         }
         out.flush();
         out.close();
	}
	//删除短评，前台
	public void delshortComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  int shortmoviecomment_id=Integer.parseInt(request.getParameter("shortmoviecomment_id"));
				shortCommentDao.deleteByConditionId(shortmoviecomment_id, "shortmoviecomment_id");  
	}
	//查询所以短评
	public void allfindshortComments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		   
		    List<ShortMovieComment> shortComments= shortCommentDao.findAll();
		    request.setAttribute("shortComments", shortComments);
		    request.getRequestDispatcher("/##").forward(request, response);
	}
	//更新短评
	public void updateshortComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		 
		    shortCommentDao.update(shortComment);
		    
	}
	
	
	

}
