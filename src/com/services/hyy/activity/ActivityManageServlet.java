package com.services.hyy.activity;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.commontools.lsr.EntityAddClass;
import com.commontools.lsr.EntityClass;
import com.dao.hyy.activity.OffActivityDao;
import com.dao.hyy.activity.OnlineActivityDao;
import com.dao.hyy.activity.impl.OffActivityDaoImpl;
import com.dao.hyy.activity.impl.OnlineActivityDaoImpl;
import com.dao.lsr.user.daoImpl.PersonalInfoImpl;
import com.entity.hyy.activity.ActivityAlbum;
import com.entity.hyy.activity.OffActivity;
import com.entity.hyy.activity.OnlineActivity;
import com.entity.hyy.city.City;
import com.entity.lsr.user.User_Info;
/**
 * 管理活动
 * @author 胡伊杨
 *
 */
@WebServlet(
		name="ActivityManageServlet",
		urlPatterns={"/hyy/service/ActivityManageServlet"}
		)
public class ActivityManageServlet extends HttpServlet {
	City city = null;
	PersonalInfoImpl psifImpl = new PersonalInfoImpl();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//模拟city是天津
		HttpSession session = request.getSession();
		city = new City(2,"天津");		
		
		
		
		session.setAttribute("city", city);
		String method = request.getParameter("method");
		System.out.println("method:"+method);
		if("addOnlineActivity".equals(method)){
			addOnlineActivity(request,response);
		}else if("searchOnlineActivity".equals(method)){
			searchOnlineActivity(request,response);
		}else if("addOffActivity".equals(method)){
			addOffActivity(request,response);
		}else if("searchOffActivity".equals(method)){
			searchOffActivity(request,response);
		}else if("deleteOn".equals(method)){
			deleteOn(request,response);
		}else if("deleteOff".equals(method)){
			deleteOff(request,response);
		}else if("selectAOnlineActivity".equals(method)){
			selectAOnlineActivity(request,response);
		}else if("updateOnActivity".equals(method)){
			updateOnActivity(request,response);
		}else if("searchAOffActivity".equals(method)){
			searchAOffActivity(request,response);
		}else if("updateOffActivity".equals(method)){
			updateOffActivity(request,response);
		}else if("searchAll".equals(method)){
			searchAll(request,response);
		}else if("searchIndex".equals(method)){
			searchIndex(request,response);
		}else if("getIndexOffActivity".equals(method)){
			getIndexOffActivity(request,response);
		}
	}

	public void getIndexOffActivity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			OffActivityDao oad = new OffActivityDaoImpl();
			String type = request.getParameter("type");
			String time = request.getParameter("time");
			List<OffActivity> list = oad.getIndexOffActivity(type, time, city);
			String json = oad.getExcludePropertyJson(list);
			
			System.out.println(json);
			response.getWriter().print(json);
		
	}
	
	public void searchIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			List<OffActivity> musicList = new ArrayList();
			OffActivityDao oad = new OffActivityDaoImpl();
			musicList = oad.getAllOffActivity(city, "音乐", "");
			System.out.println("！！！！！！！！！！！！！！！！！！！！！！！：：：：：：（）"+musicList.get(0).getStart_time());
			for (Iterator iterator = musicList.iterator(); iterator.hasNext();) {
				OffActivity offActivity = (OffActivity) iterator.next();
				System.out.println(offActivity.getStart_time()+"ooo"+offActivity.getOffactivity_title());
			}
			request.setAttribute("musicList", musicList);
			request.getRequestDispatcher("activity/aOffActivity.jsp").forward(request, response);
		
	}
	
	/**
	 * 点击时ajax的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			System.out.println("进来了");
			String bigType = request.getParameter("bigType");
			String smallType= request.getParameter("smallType");
			
			OffActivityDao oad = new OffActivityDaoImpl();
			List<OffActivity> offList = oad.getAllOffActivity(city,bigType,smallType);
			System.out.println("=========="+offList.size());
			String json = oad.getExcludePropertyJson(offList);
			response.getWriter().print(json);
		
	}
	
	/**
	 * 更新线下活动 user、admin
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateOffActivity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("=============="+request.getParameter("offactivity_statue"));

			int id = Integer.parseInt(request.getParameter("offactivity_id"));
			OffActivityDao oad = new OffActivityDaoImpl();
			OffActivity oa = new OffActivity();
			oa = EntityClass.returnEntity(oa, request);
//			OffActivity oa = oad.getActivity(new Object[]{id});
			int affectRows = oad.updateOffActivity(oa);
//			System.out.println(affectRows+"行记录受影响");
		
	}
	
	/**
	 * 查找线下活动 user、admin
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchAOffActivity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


			int offActivity_id = Integer.parseInt(request.getParameter("id"));
			OffActivityDao oad = new OffActivityDaoImpl();
			//OffActivity oa= oad.getActivity(new Object[]{offActivity_id});
			//request.setAttribute("offActivity", oa);
			
			request.setAttribute("ask_total", oad.getAllContent(offActivity_id).size());
			
			
		 
			List<User_Info> userList = oad.getActivityUsers(offActivity_id);
			request.setAttribute("userList", userList);
			System.out.println("页面跳转");
			request.getRequestDispatcher("../offlineActivity.jsp").forward(request, response);
			
	}
	
	/**
	 * 更新线上活动 user、admin
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateOnActivity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			OnlineActivityDao oad = new OnlineActivityDaoImpl();
			OnlineActivity oa = new OnlineActivity();
			oa = EntityClass.returnEntity(oa, request);
			int affectRows = oad.updateOnActivity(oa);
			System.out.println(affectRows+"==========条记录受影响");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 查找单个线上活动 user、admin
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectAOnlineActivity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			OnlineActivityDao oad = new OnlineActivityDaoImpl();
			int id = Integer.parseInt(request.getParameter("id"));
			OnlineActivity oa = oad.searchAOnActivity(id);
			request.setAttribute("onlineActivity", oa);
			request.getRequestDispatcher("activity/aOnActivity.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除一个线下活动 user
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteOff(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			OffActivityDao oad = new OffActivityDaoImpl();
			int id = Integer.parseInt(request.getParameter("id"));
			int affectRows = oad.deleteOffActivity(id);
			System.out.println(affectRows+"�м�¼��Ӱ��");
		
		
	}
	
	/**
	 * 删除一个线上活动
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteOn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			OnlineActivityDao oad = new OnlineActivityDaoImpl();
			int id = Integer.parseInt(request.getParameter("id"));
			int affectRows = oad.deleteOnlineActivity(id);
			System.out.println(affectRows+"行记录受影响");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 查找所有线上活动
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchOnlineActivity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			OnlineActivityDao oad = new OnlineActivityDaoImpl();
			List<OnlineActivity> list = oad.searcAllhActivity();
			request.setAttribute("list", list);
			request.getRequestDispatcher("activity/allOnlineActivity.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 增加线下活动----R
	 */
	public void addOffActivity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OffActivity offActivity = new OffActivity();
		offActivity = EntityAddClass.upLoadAddressAndReturnEntity(offActivity, request);
		 HttpSession session = request.getSession();
		 User_Info user = (User_Info) session.getAttribute("current_user");
		 offActivity.setUser_id(user.getUser_id()+"");
		 OffActivityDao oad = new OffActivityDaoImpl();
		 int affectRows = oad.addOfflineActivity(offActivity);
		 request.getRequestDispatcher("./ActivityServlet?method=searchAllOnLineActivity&city_desc=天津").forward(request, response);
		
	}
	
	/**
	 * 增加一个线上活动
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addOnlineActivity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OnlineActivity onlineActivity = new OnlineActivity();
		onlineActivity = EntityClass.returnEntity(onlineActivity, request);
		System.out.println(onlineActivity.getStart_time());
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			System.out.println("start_time"+start_time);
			onlineActivity.setStart_time(sdf.parse(start_time));
			onlineActivity.setEnd_time(sdf.parse(end_time));
			ActivityAlbum aa = new ActivityAlbum();
			aa.setActivityAlbum_id(1);
			System.out.println("С����"+onlineActivity.getOnlineActivity_type());
			try {
				OnlineActivityDao oad = new OnlineActivityDaoImpl();
				onlineActivity.setAlbum(aa);;
				int affectRows = oad.addOnlinActivity(onlineActivity);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 查找所有线下活动
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchOffActivity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			String offActivity_smalltype = request.getParameter("offActivity_smalltype");
			OffActivityDao oad = new OffActivityDaoImpl();
//			List<OffActivity> list = oad.getAllOffActivity(city,"小型现场");
//			request.setAttribute("list", list);
			request.getRequestDispatcher("activity/getAllOffActivity.jsp").forward(request, response);
		
	}
}
