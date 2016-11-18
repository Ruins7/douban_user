package com.services.wj.movieservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.wj.moviedao.MovieCommentDao;
import com.entity.wj.movie.MovieComment;
@WebServlet(name="MovieCommentServlet",urlPatterns="/servlet/MovieCommentServlet")
/**
 * 影评
 * @author 汪进
 *
 */
public class MovieCommentServlet extends HttpServlet {
	    MovieCommentDao movieCommentDao=new MovieCommentDao();
	    MovieComment movieComment=new MovieComment();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        String method=request.getParameter("method");
        System.out.println(method);
        if("addMovieComment".equalsIgnoreCase(method)){
        	addMovieComment(request,response);
        }else if("delMovieComment".equalsIgnoreCase(method)){
        	delMovieComment(request,response);
        }else if("allfindMovieComments".equalsIgnoreCase(method)){
        	allfindMovieComments(request,response);
        }else if("updateMovieComment".equalsIgnoreCase(method)){
        	updateMovieComment(request,response);
        }

	}
	//添加影评，前台
	public void addMovieComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		movieCommentDao.save(movieComment);
	}
	//删除影评，前台
	public void delMovieComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		int moviecomment_id=Integer.parseInt(request.getParameter("moviecomment_id"));
		movieCommentDao.deleteByConditionId(moviecomment_id, "moviecomment_id");
	}
	//查看电影的所有影评
	public void allfindMovieComments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		movieCommentDao.findAll();
	}
	//修改影评
	public void updateMovieComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		movieCommentDao.update(movieComment);
	}

}
