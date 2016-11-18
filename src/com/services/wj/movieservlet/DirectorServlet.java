package com.services.wj.movieservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.wj.moviedao.DirectorDao;
import com.entity.wj.movie.Director;
/**
 * 导演
 * @author 汪进
 *
 */
@WebServlet(name="DirectorServlet",urlPatterns="/servlet/DirectorServlet")
public class DirectorServlet extends HttpServlet {
	DirectorDao directorDao=new DirectorDao();
	Director director=new Director();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
         String method=request.getParameter("method");
         System.out.println(method);
         if("saveDirector".equalsIgnoreCase(method)){
        	 saveDirector(request,response);
         }else if("delDirector".equalsIgnoreCase(method)){
        	 delDirector(request,response);
         }else if("allfindDirectors".equalsIgnoreCase(method)){
        	 allfindDirectors(request,response);
         }else if("updateDirector".equalsIgnoreCase(method)){
        	 updateDirector(request,response);
         }else if("findOneDirector".equalsIgnoreCase(method)){
        	 findOneDirector(request,response);
         }


	}
	//添加导演、后台
	public void saveDirector(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		     directorDao.save(director);
	}
	//删除导演，后台
	public void delDirector(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		        int director_id=Integer.parseInt(request.getParameter("director_id"));
		        directorDao.findByConditionId(director_id, "director_id");
	}
	//查看所有导演，前台
	public void allfindDirectors(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		    List<Director> directors= directorDao.findAll();
		    request.setAttribute("directors", directors);
		    request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	//更新导演，后台
	public void updateDirector(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		 
		    directorDao.update(director);
		    
	}
	//查看一个导演信息，前台
	public void findOneDirector(HttpServletRequest request, HttpServletResponse response){
		  int director_id=Integer.parseInt(request.getParameter("director_id"));
		  Director director=directorDao.findByConditionId(director_id, "director_id");
		  System.out.println(director);
	}

	

}
