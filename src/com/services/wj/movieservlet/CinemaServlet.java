package com.services.wj.movieservlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;

import com.dao.wj.moviedao.CinemaDao;
import com.entity.wj.movie.Cinema;
/**
 * 电影院
 * @author 汪进
 *
 */
@WebServlet(name="CinemaServlet",urlPatterns="/servlet/CinemaServlet")
public class CinemaServlet extends HttpServlet {
	CinemaDao cinemaDao=new CinemaDao();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method=request.getParameter("method");

		if("findCinemasByCity".equals(method)){
			findCinemasByCity(request,response);
		}else if("findAllCinemas".equals(method)){
			findAllCinemas(request,response);
		}
	}
	//查询电影院，(前台)
	public void findAllCinemas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Cinema> cinemas=new ArrayList<Cinema>();
		 cinemas=cinemaDao.findAll();
        
	}
	
	public void findCinemasByCity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String city=request.getParameter("city");
		System.out.println(city);
		response.setCharacterEncoding("utf-8");
		List<Cinema> cinemas=cinemaDao.getCinemaByCity(city);
         JsonConfig config = new JsonConfig();
		config.registerJsonBeanProcessor(Date.class,  new JsDateJsonBeanProcessor()); 

		JSONArray json = (JSONArray) JSONSerializer.toJSON(cinemas, config);
		  String CinemasByCity=json.toString();
		  System.out.println(json.toString());
		  response.getWriter().print(CinemasByCity);
        
	}

}
