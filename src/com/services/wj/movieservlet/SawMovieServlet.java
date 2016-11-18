package com.services.wj.movieservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.wj.moviedao.SawMovieDao;
import com.entity.wj.movie.SawMovie;

@WebServlet(name="SawMovieServlet",urlPatterns="/servlet/SawMovieServlet")
/**
 * 看过的电影
 * @author汪进
 *
 */
public class SawMovieServlet extends HttpServlet {
	    SawMovieDao sawMovieDao=new SawMovieDao();
	    SawMovie sawMovie=new SawMovie();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        String method=request.getParameter("method");
        System.out.println(method);
        if("addsawMovie".equalsIgnoreCase(method)){
        	addsawMovie(request,response);
        }else if("delsawMovie".equalsIgnoreCase(method)){
        	delsawMovie(request,response);
        }else if("allfindsawMovie".equalsIgnoreCase(method)){
        	allfindsawMovie(request,response);
        }else if("updatesawMovie".equalsIgnoreCase(method)){
        	updatesawMovie(request,response);
        }

	}
	//添加看过的电影，前台
	public void addsawMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		   sawMovieDao.save(sawMovie);
	}
	//删除看过的电影，前台
	public void delsawMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		int sawmovie_id=Integer.parseInt(request.getParameter("sawmovie_id"));
		sawMovieDao.findByCondition(sawmovie_id, "sawmovie_id");
	}
	//查看所以看过的电影
	public void allfindsawMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
	        sawMovieDao.findAll();
	}
	//修改看过的电影
	public void updatesawMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		     sawMovieDao.update(sawMovie);
	}

}
