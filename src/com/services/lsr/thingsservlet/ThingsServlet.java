package com.services.lsr.thingsservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;

import com.commontools.lsr.EntityAddClass;
import com.commontools.lsr.JsonUtil;
import com.commontools.lsr.UserTools;
import com.dao.lsr.dbutils.daoImpl.DaoOperationImpl;
import com.dao.lsr.things.dao.DoulistDao;
import com.dao.lsr.things.daoImpl.DoulistDaoImpl;
import com.dao.lsr.things.daoImpl.ThingDaoImpl;
import com.dao.lsr.user.daoImpl.BroRecoAndLikeImpl;
import com.dao.lsr.user.daoImpl.PersonalInfoImpl;
import com.entity.lsr.doulist.User_Doulist;
import com.entity.lsr.doulist.User_Doulist_Content_Type;
import com.entity.lsr.things.Things;
import com.entity.lsr.things.Things_Type;
import com.entity.lsr.user.PageBean;
import com.entity.lsr.user.User_Commons;
import com.entity.lsr.user.User_Info;

@WebServlet(name="ThingsServlet", urlPatterns={"/things/thingsinfo"})
public class ThingsServlet extends HttpServlet {

	UserTools ut = new UserTools();
	ThingDaoImpl tdimpl = new ThingDaoImpl();
	DaoOperationImpl doImpl = new DaoOperationImpl();
	PersonalInfoImpl psifImpl = new PersonalInfoImpl();
	BroRecoAndLikeImpl bralimpl = new BroRecoAndLikeImpl();
	DoulistDao dd = new DoulistDaoImpl();
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  String method = request.getParameter("method");
          if("searchThingsByPage".equalsIgnoreCase(method)){
        	  searchThingsByPage(request, response);
          }else if("publishThings".equalsIgnoreCase(method)){
        	  try {
				publishThings(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("deleteThings".equalsIgnoreCase(method)){
        	  deleteThings(request, response);
          }else if("showOneThing".equalsIgnoreCase(method)){
        	  showOneThing(request, response);
          }else if("showThingsByType".equalsIgnoreCase(method)){
        	  showThingsByType(request, response);
          }else if("searchAllThingsByPage".equalsIgnoreCase(method)){
        	  searchAllThingsByPage(request, response);
          }
	 
	}
	public void publishThings(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//发布东西 user
		HttpSession session = request.getSession();
		User_Info user = (User_Info) session.getAttribute("current_user");
		Things things = new Things();
		things = EntityAddClass.upLoadAddressAndReturnEntity(things, request);
		things.setTime(ut.getCurrentAccurateTime());
		things.setFrom_user(user.getUser_id());
		int result = tdimpl.publishThing(things);
		if(result == 1){
			request.getRequestDispatcher("../user/userinfo?method=searchFriendsBroByPage&user_id="+user.getUser_id()).forward(request, response);
		}
	}
	public void deleteThings(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//删除东西  user
		int result = tdimpl.deleteThing(request.getParameter("things_id"));
	}
	public void showOneThing(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//查看某件东西的详细信息 user
		Things thing = new Things();
		thing = tdimpl.showOneThing(request.getParameter("things_id"));	 
		//查询发布者的信息
		User_Info user = new User_Info();
		user = psifImpl.showOneByR(thing.getFrom_user()+"", user);
		thing.setF_user(user);
		//查询东西的类型
		Things_Type type = new Things_Type();
		type = psifImpl.showOneByR(thing.getType()+"", type);
		thing.setType_table(type);
		//该东西被加入到了哪些豆列中
		List<User_Doulist> re = tdimpl.showDoulistThingsBelongto(thing.getThings_id()+"", "4");//4:东西
		if(re != null){
			for (Iterator iterator = re.iterator(); iterator.hasNext();) {
				User_Doulist user_Doulist = (User_Doulist) iterator.next();
				User_Info cuser = new User_Info();
				cuser = psifImpl.showOneByR(user_Doulist.getFrom_user()+"", cuser);
				user_Doulist.setF_user(cuser);
				User_Doulist_Content_Type ct = new User_Doulist_Content_Type();
				ct = psifImpl.showOneByR(user_Doulist.getContent_type()+"", ct);
				user_Doulist.setContent_type_table(ct);
			}
			System.out.println("共被加入:"+re.size()+"个豆列");
			request.setAttribute("belongdoulist", re);
		}else{
			request.setAttribute("belongdoulist", null);
		}
		//查询该东西的评论
		List<User_Commons> result = new ArrayList<User_Commons>();
		result = bralimpl.showAllCommonsAboutOneThing("5", request.getParameter("things_id"));
		request.setAttribute("thing", thing);
		if(result != null){	 
			//迭代查询评论的人的信息
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				User_Commons user_Commons = (User_Commons) iterator.next();
				User_Info common_user = new User_Info();
				common_user = psifImpl.showOneByR(user_Commons.getFrom_user()+"", common_user);
				user_Commons.setF_user(common_user);
			}
			request.setAttribute("thing_commons", result);
		}else{
			request.setAttribute("thing_commons", null);
		}
		request.getRequestDispatcher("/lsr/douban_onething.jsp").forward(request, response);
	}
	
	public void showThingsByType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//按分类查找东西 user   
	    List<Things> list = tdimpl.showThingsByType(request.getParameter("type"));
		PrintWriter out = response.getWriter();
		if(list != null){
			System.out.println(list.size()+"   size");
			//填充
			for (Things user_t : list) {
		    User_Info f_user = new User_Info();
		    f_user = psifImpl.showOneByR(user_t.getFrom_user()+"", f_user);
			user_t.setF_user(f_user);
			Things_Type type = new Things_Type();
			type = psifImpl.showOneByR(user_t.getType()+"", type);
			user_t.setType_table(type);
			}
			response.setContentType("text/plain");
			JsonConfig config = new JsonConfig();
			config.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());
			String s = JsonUtil.getJsonString4JavaPOJO(list, "yyyy-MM-dd");
			out.print(s); 
			
		}else{
			out.print(false);//没有数据了 
		}
	
	
	}
	public void thingsBelongToDoulist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//这件东西所属豆列 user
		List<User_Doulist> doulist_ids = tdimpl.showDoulistThingsBelongto(request.getParameter("things_id"), "4");
	
	}
	public void showMyThings(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//查找我发布的所有东西 user
		HttpSession session = request.getSession();
		User_Info user = (User_Info) session.getAttribute("current_user");
		List<Things> list_result = tdimpl.showMyThings(user.getUser_id()+"");
	}
	 
	// 分页查找  某人发的  所有的东西 user
	public void searchAllThingsByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	  
		
		String typepage = request.getParameter("typepage");
				String currentPage = request.getParameter("currentPage");
				if(currentPage == null){
					currentPage = "1";
				}
				PageBean pageBean = doImpl.searchByPageF(Integer.parseInt(currentPage), 5, "things", new Things(), Integer.parseInt(request.getParameter("user_id")));
				PrintWriter out = response.getWriter();
				if(pageBean != null){
				//填充bean
					List<Things> mythings = pageBean.getData();
					for (Things user_t : mythings) {
				    User_Info f_user = new User_Info();
				    f_user = psifImpl.showOneByR(user_t.getFrom_user()+"", f_user);
					user_t.setF_user(f_user);
					Things_Type type = new Things_Type();
					type = psifImpl.showOneByR(user_t.getType()+"", type);
					user_t.setType_table(type);
					}
					if(currentPage == "1"){//首次请求
						request.setAttribute("mythings", pageBean);
					}else{//ajax请求
						response.setContentType("text/plain");
						JsonConfig config = new JsonConfig();
						config.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());
						String s = JsonUtil.getJsonString4JavaPOJOObj(pageBean, "yyyy-MM-dd");
						out.print(s); 
					}	
				}else{
					if(currentPage == "1"){
						request.setAttribute("mythings", null);
					}else{
						out.print(false);//没有数据了 
					}	
				}
				if(currentPage == "1" && !"ul".equalsIgnoreCase(typepage)){//mydouban首次请求
			    	request.getRequestDispatcher("../doulist/doulistinfo?method=searchDoulistByPage").forward(request, response);
			    }else if(currentPage == "1" && "ul".equalsIgnoreCase(typepage)){//ul中的请求
			    	request.getRequestDispatcher("/lsr/douban_thing.jsp").forward(request, response);
			    }else{
			    	out.flush();
					out.close();
			    }				 	
	}
	
	// 分页查找所有的东西 user
	public void searchThingsByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		 
		String typepage = request.getParameter("typepage");
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null){
			currentPage = "1";
		}
		PageBean pageBean = doImpl.searchByPageFN(Integer.parseInt(currentPage), 5, "things", new Things());
		PrintWriter out = response.getWriter();
		if(pageBean != null){
			//填充bean
			List<Things> mythings = pageBean.getData();
			for (Things user_t : mythings) {
				User_Info f_user = new User_Info();
				f_user = psifImpl.showOneByR(user_t.getFrom_user()+"", f_user);
				user_t.setF_user(f_user);
				Things_Type type = new Things_Type();
				type = psifImpl.showOneByR(user_t.getType()+"", type);
				user_t.setType_table(type);
			}
			if(currentPage == "1"){//首次请求
				request.setAttribute("mythings", pageBean);
			}else{//ajax请求
				response.setContentType("text/plain");
				JsonConfig config = new JsonConfig();
				config.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());
				String s = JsonUtil.getJsonString4JavaPOJOObj(pageBean, "yyyy-MM-dd");
				out.print(s); 
			}	
		}else{
			if(currentPage == "1"){
				request.setAttribute("mythings", null);
			}else{
				out.print(false);//没有数据了 
			}	
		}
		if(currentPage == "1" && !"ul".equalsIgnoreCase(typepage)){//mydouban首次请求
			request.getRequestDispatcher("../doulist/doulistinfo?method=searchDoulistByPage").forward(request, response);
		}else if(currentPage == "1" && "ul".equalsIgnoreCase(typepage)){//ul中的请求
			//查询当前用户的所有喜欢的东西
			HttpSession session = request.getSession();
			User_Info user = (User_Info) session.getAttribute("current_user");
			//当前用户的信息
			request.setAttribute("cuser", user);
			/*List<User_Like> result = bralimpl.showAllMyLike(user.getUser_id(),5);//5:东西
			if(result != null){
				request.setAttribute("mylike", result);
			}else{
				request.setAttribute("mylike", null);
			}*/
			
			//查询所有东西的种类
			List<Things_Type> tt = new ArrayList<Things_Type>();
			tt = tdimpl.showAllThingsType();
			if(tt != null){
				request.setAttribute("types", tt);
			}else{
				request.setAttribute("types", null);
			}
			//查询最近发布东西的三个用户,以及他们发布的所有东西
			List<Things> newthings = tdimpl.showNewThings();
			if(newthings != null){
				//三个活跃用户
				List<User_Info> threeuser = new ArrayList<User_Info>();
				for (Iterator iterator = newthings.iterator(); iterator.hasNext();) {
					Things things = (Things) iterator.next();
					User_Info thuser = new User_Info();
					thuser = psifImpl.showOneByR(things.getFrom_user()+"", thuser);
					threeuser.add(thuser);			
				}
				//用户去重---重写equals()
				List<User_Info> arryListNew = new ArrayList<User_Info>();
				for (User_Info thisuser : threeuser) {
					if(!arryListNew.contains(thisuser)){
						arryListNew.add(thisuser);
					}
				}		
				//查找他们发布的东西
				List<Things> onething = tdimpl.showMyThings(arryListNew.get(0).getUser_id()+"");
				if(onething != null){
					if(onething.size() > 4){
						request.setAttribute("oneuserthings", onething.subList(0, 4));
					}else{
						request.setAttribute("oneuserthings", onething);
					}	
				}else{
					request.setAttribute("oneuserthings", null);
				}
				
				List<Things> twothing = tdimpl.showMyThings(arryListNew.get(1).getUser_id()+"");
				if(twothing != null){
					if(twothing.size() > 4){
						request.setAttribute("twouserthings", twothing.subList(0, 4));
					}else{
						request.setAttribute("twouserthings", twothing);
					}	
				}else{
					request.setAttribute("twouserthings", null);
				}
				
				List<Things> threething = tdimpl.showMyThings(arryListNew.get(2).getUser_id()+"");
				if(threething != null){
					if(threething.size() > 4){
						request.setAttribute("threeuserthings", threething.subList(0, 4));
					}else{
						request.setAttribute("threeuserthings", threething);
					}		
				}else{
					request.setAttribute("threeuserthings", null);
				}
				request.setAttribute("threeuser", arryListNew.subList(0, 3));
			}else{
				request.setAttribute("threeuser", null);
				request.setAttribute("oneuserthings", null);
				request.setAttribute("twouserthings", null);
				request.setAttribute("threeuserthings", null);
			}
			request.getRequestDispatcher("/lsr/douban_thing.jsp").forward(request, response);
		}else{
			out.flush();
			out.close();
		}				 	
	}

}
