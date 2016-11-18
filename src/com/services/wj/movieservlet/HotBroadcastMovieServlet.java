package com.services.wj.movieservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.wj.moviedao.HotBroadcastMovieDao;
import com.entity.wj.movie.HotBroadcast;
@WebServlet(name="HotBroadcastMovieServlet",urlPatterns="/servlet/HotBroadcastMovieServlet")
/**
 * 热播
 * @author 汪进
 *
 */
public class HotBroadcastMovieServlet extends HttpServlet {
	 HotBroadcastMovieDao hotmovieDao=new HotBroadcastMovieDao();
	 HotBroadcast hotmovie=new HotBroadcast();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String method=request.getParameter("method");
         System.out.println(method);
         if("addHotMovie".equalsIgnoreCase(method)){
        	 addHotMovie(request,response);
         }else if("delMovie".equalsIgnoreCase(method)){
        	 delHotMovie(request,response);
         }else if("findAllHotMovies".equalsIgnoreCase(method)){
        	 findAllHotMovies(request,response);
         }else if("findHotmovie".equalsIgnoreCase(method)){
        	 findHotmovie(request,response);
         }else if("updateHotMovie".equalsIgnoreCase(method)){
        	 updateHotMovie(request,response);
         }
     
	}
	//添加热播剧、后台
	public void addHotMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		 
		    hotmovieDao.save(hotmovie);
		    
	}
	//删除热播剧、后台
	public void delHotMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    int hotbroadcast_id=Integer.parseInt(request.getParameter("hotbroadcast_id"));
		    hotmovieDao.deleteByConditionId(hotbroadcast_id, "hotbroadcast_id");
		    
	}
	//查询热播剧，前台
	public void findHotmovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    int hotbroadcast_id=Integer.parseInt(request.getParameter("hotbroadcast_id"));
		    hotmovieDao.findByConditionId(hotbroadcast_id, "hotbroadcast_id");
		    
	}
	//查询热播剧，前台
	public void findAllHotMovies(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    hotmovieDao.findAll();
	    
}
	//更新热播剧
	public void updateHotMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    hotmovieDao.update(hotmovie);
		    
	}

}
