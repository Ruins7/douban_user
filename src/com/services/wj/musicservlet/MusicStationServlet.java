package com.services.wj.musicservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.wj.musicdao.MusicStationDao;
import com.entity.wj.music.MusicStation;
@WebServlet(name="MusicStationServlet",urlPatterns="/servlet/MusicStationServlet")
/**
 * 想听的，听过的，评分
 * @author dell
 *
 */
public class MusicStationServlet extends HttpServlet {
	  MusicStationDao musicStationDao=new MusicStationDao();
	  MusicStation musicStation=new MusicStation();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        String method=request.getParameter("method");
        System.out.println(method);
        if("addmusicStation".equalsIgnoreCase(method)){
        	addmusicStation(request,response);
        }else if("delmusicStation".equalsIgnoreCase(method)){
        	delmusicStation(request,response);
        }else if("allfindmusicStations".equalsIgnoreCase(method)){
        	allfindmusicStations(request,response);
        }else if("updatemusicStation".equalsIgnoreCase(method)){
        	updatemusicStation(request,response);
        }

	}
	//添加MusicStation
	public void addmusicStation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		musicStationDao.save(musicStation);
	}
	//删除MusicStation
	public void delmusicStation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		int musicstation_id=Integer.parseInt(request.getParameter("musicstation_id"));
		musicStationDao.deleteByConditionId(musicstation_id, "musicstation_id");
	}
	//查询所有MusicStation
	public void allfindmusicStations(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		musicStationDao.findAll();
	}
	//修改MusicStation
	public void updatemusicStation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		musicStationDao.update(musicStation);
	}

}
