package com.services.wj.musicservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.wj.musicdao.MusicCommentDao;
import com.entity.wj.music.MusicComment;
@WebServlet(name="MusicCommentServlet",urlPatterns="/servlet/MusicCommentServlet")
/**
 * MusicCommentServlet
 * @author 汪进
 *
 */
public class MusicCommentServlet extends HttpServlet {
	   MusicCommentDao musicCommentDao=new MusicCommentDao();
	   MusicComment musicComment=new MusicComment();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        String method=request.getParameter("method");
        System.out.println(method);
        if("addmusicComment".equalsIgnoreCase(method)){
        	addmusicComment(request,response);
        }else if("delmusicComment".equalsIgnoreCase(method)){
        	delmusicComment(request,response);
        }else if("allfindmusicComments".equalsIgnoreCase(method)){
        	allfindmusicComments(request,response);
        }else if("updatemusicComment".equalsIgnoreCase(method)){
        	updatemusicComment(request,response);
        }

	}
	//添加乐评，前台
	public void addmusicComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		musicCommentDao.save(musicComment);
	}
	//删除乐评，前台
	public void delmusicComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		int musiccomment_id=Integer.parseInt(request.getParameter("musiccomment_id"));
		musicCommentDao.deleteByConditionId(musiccomment_id, "musiccomment_id");
	}
	//查询所有乐评
	public void allfindmusicComments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		musicCommentDao.findAll();
	}
	//更改乐评
	public void updatemusicComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		musicCommentDao.update(musicComment);
	}

}
