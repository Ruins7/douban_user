package com.services.wj.musicservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.wj.musicdao.MusicRankingDao;
import com.entity.wj.music.Music;
@WebServlet(name="MusicRankingServlet",urlPatterns="/servlet/MusicRankingServlet")
/**
 *音乐排行榜、前台页面
 * @汪进
 *
 */
public class MusicRankingServlet extends HttpServlet {
	MusicRankingDao musicRankingDao=new MusicRankingDao();
	Music music=new Music();
	public void servie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method=request.getParameter("method");
		if("getRankingByCountEntity".equals(method)){
			getRankingByCountEntitys(request,response);
		}else if("getRankingByScoreEntitys".equals(method)){
			getRankingByScoreEntitys(request,response);
		}
	}
	public void getRankingByCountEntitys(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		musicRankingDao.getRankingByCountEntity(music);
           
	}
	public void getRankingByScoreEntitys(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		musicRankingDao.getRankingByCountEntity(music);
           
	}
}
