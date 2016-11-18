package com.services.hyy.activity;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.commontools.lsr.EntityClass;
import com.commontools.lsr.UserTools;
import com.dao.hyy.activity.OffActivityDao;
import com.dao.hyy.activity.OfflineAskActivityDao;
import com.dao.hyy.activity.impl.OffActivityDaoImpl;
import com.dao.hyy.activity.impl.OfflineAskActivityDaoImpl;
import com.dao.hyy.doActivity.DoActivityDao;
import com.dao.hyy.doActivity.impl.DoActivityDaoImpl;
import com.dao.hyy.entity.EntityDao;
import com.dao.hyy.entity.impl.EntityDaoImpl;
import com.dao.lsr.user.daoImpl.PersonalInfoImpl;
import com.entity.hyy.activity.Activity_type;
import com.entity.hyy.activity.OffActivity;
import com.entity.hyy.activity.OfflineAskActivity;
import com.entity.hyy.activity.OnlineActivity;
import com.entity.hyy.city.City;
import com.entity.lsr.user.User_Info;

/**
 * 线下活动用户与发起者之间的交流
 * @author 胡伊杨
 *
 */
@WebServlet(name = "ActivityServlet", urlPatterns = { "/hyy/service/ActivityServlet" })
public class ActivityServlet extends HttpServlet {

	HttpSession session;
	int myId;
	String table_name;
	int inTable_id;
	PersonalInfoImpl psifImpl = new PersonalInfoImpl();
	OffActivityDao oad = new OffActivityDaoImpl();
	UserTools ut = new UserTools();
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	 
		request.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("method");
		if("searchSameCityOffActivity".equals(method)){
			searchSameCityOffActivity(request,response);			
		}else if("offActivity".equals(table_name)){
			attendOffActivity(request,response);
		}else if ("searchAllOnLineActivity".equals(method)) {
			searchAllOnLineActivity(request, response);
		} else if ("attendActivity".equals(method)) {
			attendActivity(request, response);
		}else if("findEntity".equals(method)){
			findEntity(request,response);
		}else if("askOff".equals(method)){
			askOff(request,response);
		}else if("askOffContent".equals(method)){
			askOffContent(request,response);
		}else if("addActivityComment".equals(method)){
			try {
				addActivityComment(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("searchAllActivityType".equals(method)){
			searchAllActivityType(request,response);
		}else if("searchAllOffActivityByType".equals(method)){
			searchAllOffActivityByType(request,response);
		}else if("searchAllOffActivityByOneType".equals(method)){
			searchAllOffActivityByOneType(request,response);
		}else if("searchOneOffActivity".equals(method)){
			searchOneOffActivity(request,response);
		}else if("searchOneOffActivityAsk".equals(method)){
			searchOneOffActivityAsk(request,response);
		}else if("searchOneOffActivityAttendUser".equals(method)){
			searchOneOffActivityAttendUser(request,response);
		}else if("cancleAttendActivity".equals(method)){
			cancleAttendActivity(request,response);
		}
	}
	
	/**
	 * 查询近期的线上活动 user   ----R
	 */
	public void searchAllOnLineActivity(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		
			DoActivityDaoImpl dad = new DoActivityDaoImpl();			
			List<OnlineActivity> list = dad.searchOnLineActivity("onlineactivity");
			if(list != null){
				request.setAttribute("online_list", list);
			}else{
				request.setAttribute("online_list", null);
			}
			request.getRequestDispatcher("./ActivityServlet?method=searchSameCityOffActivity").forward(request, response);
	}
	
	/**
	 * 查找所有同城线下活动 user----R
	 */
	public void searchSameCityOffActivity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
	 
			OffActivityDao oad = new OffActivityDaoImpl();
			String city_name = request.getParameter("city_desc");
			ServletContext app = request.getServletContext();
			List<City> c_list =  (List<City>) app.getAttribute("city");
		 
			List<OffActivity> list = new ArrayList<OffActivity>();
			for (Iterator iterator = c_list.iterator(); iterator.hasNext();) {
				City city = (City) iterator.next();				 
				if(city.getCity_desc().equalsIgnoreCase(city_name)){
					request.setAttribute("h_city", city);
					 list = oad.getSameCityOffActivity(city.getCity_id()+"");
				}
			}
			if(list != null){
				request.setAttribute("offline_list", list);
			}else{			 
				request.setAttribute("offline_list", null);
			}
			request.getRequestDispatcher("./ActivityServlet?method=searchAllActivityType").forward(request, response);
		
	}
	
	/**
	 * 查找所有活动类型 user----R
	 */
	public void searchAllActivityType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		   String request_type = request.getParameter("request_type");
			OffActivityDao oad = new OffActivityDaoImpl();
			List<Activity_type> list = oad.getAllType();
			if(list != null){
				request.setAttribute("type_list", list);
			}else{
				request.setAttribute("type_list", null);
			}
			if("addactivity".equalsIgnoreCase(request_type)){//创建活动
				request.getRequestDispatcher("/hyy/activity/addOfflineActivity.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("./ActivityServlet?method=searchAllOffActivityByType").forward(request, response);
			}
			
		
	}
	
	/**
	 * 分类别查询线下活动 user----R
	 */
	public void searchAllOffActivityByType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
			OffActivityDao oad = new OffActivityDaoImpl();
			List<Activity_type> type_list = (List<Activity_type>) request.getAttribute("type_list");
			if(type_list != null){
				Map<Activity_type,List<OffActivity>> map = oad.getOffActivityByAllType(type_list);			 
					request.setAttribute("type_off_map", map);		 
			}else{
				request.setAttribute("type_off_map", null);	
			}
			request.getRequestDispatcher("/hyy/activity/douban_city.jsp").forward(request, response);
		
	}
	
	/**
	 * 按某一类别查询线下活动 user----R
	 */
	public void searchAllOffActivityByOneType(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {	
			OffActivityDao oad = new OffActivityDaoImpl();
			List<OffActivity> list = oad.getOffActivityByOneType(request.getParameter("type_id"));
			Activity_type type = new Activity_type();
			List<User_Info> faqiusers = new ArrayList<User_Info>();
			type = psifImpl.showOneByR(request.getParameter("type_id"), type);
			request.setAttribute("typetype", type);
			if(list != null){
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					OffActivity offActivity = (OffActivity) iterator.next();
					User_Info user = new User_Info();
					user = psifImpl.showOneByR(offActivity.getUser_id(), user);
					offActivity.setUser(user);
					faqiusers.add(user);
					City c = new City();
					c = psifImpl.showOneByR(offActivity.getCity_id(), c);
					offActivity.setCity(c);
				}
				//发起者去重
				List<User_Info> quchongfaqiusers = new ArrayList<User_Info>();
				for (User_Info user_Info : faqiusers) {
					if(!quchongfaqiusers.contains(user_Info)){
						quchongfaqiusers.add(user_Info);
					}
				}
				request.setAttribute("off_act", list);
				request.setAttribute("faqiusers", quchongfaqiusers);
			}else{
				request.setAttribute("off_act", null);
			}
			request.getRequestDispatcher("/hyy/activity/allActivity.jsp").forward(request, response);	
	}
	
	
	/**
	 * 查询某一个线下活动 user    ----R
	 */
	public void searchOneOffActivity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	 
		OffActivity off = oad.getActivity(request.getParameter("activity_id"));
		if(off != null){
			City c = new City();
			c = psifImpl.showOneByR(off.getCity_id(), c);
			off.setCity(c);
			User_Info user = new User_Info();
			user = psifImpl.showOneByR(off.getUser_id(), user);
			off.setUser(user);
			Activity_type at = new Activity_type();
			at = psifImpl.showOneByR(off.getOffactivity_type(), at);
			off.setAt(at);
			request.setAttribute("off", off);
		}else{
			request.setAttribute("off", null);
		}
         request.getRequestDispatcher("./ActivityServlet?method=searchOneOffActivityAsk").forward(request, response);
	}
	
	/**
	 * 查询某一个线下活动的用户交流 user    ----R
	 */
	public void searchOneOffActivityAsk(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	    List<OfflineAskActivity> list = oad.getActivityAsk(request.getParameter("activity_id"));
        if(list != null){
        	for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				OfflineAskActivity offlineAskActivity = (OfflineAskActivity) iterator.next();
				User_Info f = new User_Info();
				f = psifImpl.showOneByR(offlineAskActivity.getUser_from(), f);
				offlineAskActivity.setUser_f(f);
				/*User_Info t = new User_Info();
				t = psifImpl.showOneByR(offlineAskActivity.getUser_t(), t);
				offlineAskActivity.setUser_to(t);
				OffActivity off = new OffActivity();
				off = psifImpl.showOneByR(offlineAskActivity.getActivity_id(), off);
				offlineAskActivity.setOfflineActivity(off);*/
			}
        	request.setAttribute("ask", list);
        }else{
        	request.setAttribute("ask", null);
        }
	    request.getRequestDispatcher("./ActivityServlet?method=searchOneOffActivityAttendUser").forward(request, response);
	}
	
	/**
	 * 查询参加某一个活动的所有用户 user    ----R
	 */
	public void searchOneOffActivityAttendUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	    List<Object[]> list = oad.getSameAttendUsersArray(request.getParameter("activity_id"));
	    List<User_Info> users = new ArrayList<User_Info>();
	    HttpSession session = request.getSession();
		User_Info cuser = (User_Info) session.getAttribute("current_user");	
	    if(list != null){
        	 for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				User_Info user = new User_Info();
				user = psifImpl.showOneByR((int)objects[0]+"", user);
				users.add(user);				
			 }
        	//判断是否为当前登陆的用户---检查当前登陆用户是否参加了这个活动
        	 for (Iterator iterator = users.iterator(); iterator.hasNext();) {
				User_Info user_Info = (User_Info) iterator.next();
				if(user_Info.getUser_id() == cuser.getUser_id()){
					request.setAttribute("current_user_attend", "yes");
					break;
				}else{
					request.setAttribute("current_user_attend", "no");
				}
			}
				
        	 request.setAttribute("users", users);
	    }else{
	    	 request.setAttribute("current_user_attend", "no");
        	 request.setAttribute("users", null);
        }
	    request.getRequestDispatcher("/hyy/activity/oneActivity.jsp").forward(request, response);
	}
	
	/**
	 * 参加活动 user  ------R
	 */
	public void attendActivity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 User_Info user = (User_Info) session.getAttribute("current_user");
		 int result = oad.attendActivity(request.getParameter("act_id"), user.getUser_id()+"");
		 PrintWriter out = response. getWriter();
         if(result > 0) {
             out.print(true );
         } else {
             out.print(false ); 
         }
         out.flush();
         out.close();
	}
	
	/**
	 * 取消参加活动 user  ------R
	 */
	public void cancleAttendActivity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 User_Info user = (User_Info) session.getAttribute("current_user");
		 int result = oad.cancelAttendActivity(request.getParameter("act_id"), user.getUser_id()+"");
		 PrintWriter out = response. getWriter();
         if(result > 0) {
             out.print(true );
         } else {
             out.print(false ); 
         }
         out.flush();
         out.close();
	}

	

	/**
	 * 用户讨论活动  ---Rs
	 * @throws Exception 
	 */
	private void addActivityComment(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		OfflineAskActivity oaa = new OfflineAskActivity();
		oaa = EntityClass.returnEntity(oaa, request);
		oaa.setTime(ut.getCurrentAccurateTime());
		HttpSession session = request.getSession();
		User_Info cuser = (User_Info) session.getAttribute("current_user");	
		oaa.setUser_from(cuser.getUser_id()+"");
		int result = oad.insertaskActivity(oaa);
		PrintWriter out = response. getWriter();
        if(result > 0) {
            out.print(true );
        } else {
            out.print(false ); 
        }
        out.flush();
        out.close();

	}
	
	/**
	 * 发起提问、回答提问 user
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void askOffContent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
			System.out.println("进入askOffContent");
			String activity_id = request.getParameter("activity_id");
			OffActivityDao oad = new OffActivityDaoImpl();
			System.out.println("=========="+request.getParameter("activity_id"));
			List<OfflineAskActivity> oaa = oad.getAllContent(Integer.parseInt(activity_id));
			response.getWriter().print(oad.offlineAskActivityJson(oaa));
		
	}

	

	
	/**
	 * 展示参加相同活动的用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showSameAttend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DoActivityDao dad;
		System.out.println("������");
		System.out.println("table_name:"+table_name);
		try {
			dad = new DoActivityDaoImpl();
			List<User_Info> users = dad.getSameAttendUsersArray(inTable_id, table_name,table_name);
			System.out.println("user:"+users);
			System.out.println("inTable_id:"+inTable_id+","+"table_name:"+table_name);
			request.setAttribute("users", users);
			request.getRequestDispatcher("activity/sameAttendUser.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 获得参加统一活动、读同一本书等的活动
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getOffActivityUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			OffActivityDao oad = new OffActivityDaoImpl();
			oad.getActivityUsers(inTable_id);
		
	}
	
	/**
	 * 参加、取消参加线下活动 user
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void attendOffActivity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("table:"+table_name);
			OffActivityDao oad = new OffActivityDaoImpl();	
			User_Info user = (User_Info) session.getAttribute("me");
		//	oad.attendActivity(inTable_id, user, "insert");
		} catch (Exception e) {
			e.printStackTrace();
		}
		showSameAttend(request, response);
	}
	
	/**
	 * 根据类型查找实体 user、admin
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findEntity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int mailComment_id = Integer.parseInt(request.getParameter("mailComment_id"));
			EntityDao ed = new EntityDaoImpl();
			Object[] obj = ed.getEntity(mailComment_id);
			request.setAttribute("entityArray", obj);
			request.getRequestDispatcher("activity/oneActivity.jsp").forward(request, response);	
	}
	
	/**
	 * 发起线下活动询问 user
	 */
	public void askOff(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("============进来了");
		int activity_id = Integer.parseInt(request.getParameter("activity_id"));
		int ask_from_id=Integer.parseInt(request.getParameter("ask_from_id"));
		String ask_content = request.getParameter("ask_content");
		OffActivityDao oad = null;
		try {
			OfflineAskActivityDao oaa = new OfflineAskActivityDaoImpl();
			int affectRows = oaa.saveAsk(activity_id, ask_from_id, ask_content);
			response.getWriter().print(affectRows);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
}
