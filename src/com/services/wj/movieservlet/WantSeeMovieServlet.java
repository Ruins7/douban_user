package com.services.wj.movieservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.wj.moviedao.WantSeeDao;
import com.entity.wj.movie.WantSeeMovie;
@WebServlet(name="WantSeeMovieServlet",urlPatterns="/servlet/WantSeeMovieServlet")
/**
 * 想看的电影
 * @author 汪进
 *
 */
public class WantSeeMovieServlet extends HttpServlet {
	    WantSeeDao wantSeeDao=new WantSeeDao();
	    WantSeeMovie wantSeeMovie=new WantSeeMovie();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        String method=request.getParameter("method");
        System.out.println(method);
        if("addwantSeeMovie".equalsIgnoreCase(method)){
        	addwantSeeMovie(request,response);
        }else if("delwantSeeMovie".equalsIgnoreCase(method)){
        	delwantSeeMovie(request,response);
        }else if("allfindwantSeeMovies".equalsIgnoreCase(method)){
        	allfindwantSeeMovies(request,response);
        }else if("updatewantSeeMovie".equalsIgnoreCase(method)){
        	updatewantSeeMovie(request,response);
        }

	}
	//添加想看的电影，前台
	public void addwantSeeMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		    wantSeeDao.save(wantSeeMovie);
	}
	//删除想看的电影，前台
	public void delwantSeeMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		int wantseemovie_id=Integer.parseInt(request.getParameter("wantseemovie_id"));
		   wantSeeDao.deleteByConditionId(wantseemovie_id, "wantseemovie_id");
	}
	//查询所以想看的电影，前台
	public void allfindwantSeeMovies(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
	        wantSeeDao.findAll();
	}
	//更改想看的电影
	public void updatewantSeeMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		     wantSeeDao.update(wantSeeMovie);
	}

}
