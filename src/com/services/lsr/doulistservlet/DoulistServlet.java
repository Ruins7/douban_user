package com.services.lsr.doulistservlet;

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

import com.commontools.lsr.EntityClass;
import com.commontools.lsr.JsonUtil;
import com.commontools.lsr.UserTools;
import com.dao.hyy.activityalbum.impl.ActivityAlbumDaoImpl;
import com.dao.lsr.dbutils.daoImpl.DaoOperationImpl;
import com.dao.lsr.things.daoImpl.DoulistDaoImpl;
import com.dao.lsr.user.daoImpl.PersonalInfoImpl;
import com.entity.hyy.activity.Activity_type;
import com.entity.hyy.activity.OffActivity;
import com.entity.hyy.city.City;
import com.entity.lsr.book.Book;
import com.entity.lsr.book.Book_Author;
import com.entity.lsr.book.Book_Publisher;
import com.entity.lsr.book.Book_Type;
import com.entity.lsr.doulist.User_Doulist;
import com.entity.lsr.doulist.User_Doulist_Content;
import com.entity.lsr.doulist.User_Doulist_Content_Type;
import com.entity.lsr.doulist.User_Focus_Doulist;
import com.entity.lsr.things.Things;
import com.entity.lsr.things.Things_Type;
import com.entity.lsr.user.PageBean;
import com.entity.lsr.user.User_Info;
import com.entity.wj.movie.Director;
import com.entity.wj.movie.Movie;
import com.entity.wj.movie.Movie_type;

@WebServlet(name = "DoulistServlet", urlPatterns = { "/doulist/doulistinfo" })
public class DoulistServlet extends HttpServlet {

	DaoOperationImpl doImpl = new DaoOperationImpl();
	PersonalInfoImpl psifImpl = new PersonalInfoImpl();
	UserTools ut = new UserTools();
	DoulistDaoImpl ddimpl = new DoulistDaoImpl();
	ActivityAlbumDaoImpl aadi = new ActivityAlbumDaoImpl();

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("searchDoulistByPage".equalsIgnoreCase(method)) {
			searchDoulistByPage(request, response);
		} else if ("showMyDouList".equalsIgnoreCase(method)) {
			showMyDouList(request, response);
		} else if ("createMyDouList".equalsIgnoreCase(method)) {
			try {
				createMyDouList(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("deleteMyDouList".equalsIgnoreCase(method)) {
			deleteMyDouList(request, response);
		} else if ("addItemToMyDouList".equalsIgnoreCase(method)) {
			try {
				addItemToMyDouList(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("delItemFromMyDouList".equalsIgnoreCase(method)) {
			delItemFromMyDouList(request, response);
		} else if ("focusOthersDouList".equalsIgnoreCase(method)) {
			try {
				focusOthersDouList(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("cancelFocusOthersDouList".equalsIgnoreCase(method)) {
			cancelFocusOthersDouList(request, response);
		} else if ("showMyFocusDouList".equalsIgnoreCase(method)) {
			showMyFocusDouList(request, response);
		} else if ("showOneDouList".equalsIgnoreCase(method)) {
			showOneDouList(request, response);
		} else if("showSameTypeDoulist".equalsIgnoreCase(method)){
			showSameTypeDoulist(request, response);
		}
	}

	public void createMyDouList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 创建豆列（活动，书籍，电影，东西） user
		HttpSession session = request.getSession();
		User_Info currentuser = (User_Info) session
				.getAttribute("current_user");
		User_Doulist doulist = new User_Doulist();
		doulist = EntityClass.returnEntity(doulist, request);// 页面提供：list_name,content_type
		doulist.setFrom_user(currentuser.getUser_id());
		doulist.setTime(ut.getCurrentAccurateTime());
		int result = ddimpl.createMyDouList(doulist);

		request.getRequestDispatcher("/lsr/douban_loginAfter.jsp").forward(
				request, response);

	}

	public void deleteMyDouList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 删除豆列 user
		int result = ddimpl.deleteMyDouList(request.getParameter("doulist_id"));
	}

	public void addItemToMyDouList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 向我的某个豆列添加项目 user
		User_Doulist_Content udc = new User_Doulist_Content();
		udc = EntityClass.returnEntity(udc, request);
		udc.setTime(ut.getCurrentAccurateTime());
		int result = ddimpl.addItemToMyDouList(udc);
		PrintWriter out = response.getWriter();
		 if(result > 0) {
             out.print(true);
         } else {
             out.print(false); 
         }
         out.flush();
         out.close();
 
	}

	public void delItemFromMyDouList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 从我的某个豆列删除项目 user
		int result = ddimpl.delItemFromMyDouList(request
				.getParameter("record_id"));
	}

	public void focusOthersDouList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 关注他人的豆列 user
		User_Focus_Doulist ufd = new User_Focus_Doulist();
		ufd = EntityClass.returnEntity(ufd, request);
		HttpSession session = request.getSession();
		User_Info currentuser = (User_Info) session.getAttribute("current_user");
		ufd.setFocus_user(currentuser.getUser_id());
		ufd.setTime(ut.getCurrentAccurateTime());
		int result = ddimpl.focusOthersDouList(ufd);
	}

	public void cancelFocusOthersDouList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 取消关注豆列 user
		int result = ddimpl.cancelFocusOthersDouList(request
				.getParameter("focus_id"));
	}

	public void showMyDouList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 查找用户创建的豆列 user
		String params = request.getParameter("params");
		if("created".equalsIgnoreCase(params)){
			List<User_Doulist> list_result = ddimpl.showMyDouList(request
					.getParameter("user_id"));
			// 查询当前页面查看用户信息
			User_Info curr_user = new User_Info();
			curr_user = psifImpl.showOneByR(request.getParameter("user_id"),
					curr_user);
			request.setAttribute("current_user", curr_user);
			if (list_result != null) {
				// 对应豆列内容类型
				for (Iterator iterator = list_result.iterator(); iterator.hasNext();) {
					User_Doulist user_Doulist = (User_Doulist) iterator.next();
					User_Doulist_Content_Type type = new User_Doulist_Content_Type();
					type = psifImpl.showOneByR(user_Doulist.getContent_type() + "",
							type);
					user_Doulist.setContent_type_table(type);
				}
				request.setAttribute("my_created_doulie", list_result);
				request.setAttribute("my_created_doulie_size", list_result.size());
			} else {
				request.setAttribute("my_created_doulie", null);
			}
			//我常去的小组
			request.getRequestDispatcher("../groupServlet?method=UserOftenJoinGroup")
					.forward(request, response);
		}else{
			List<User_Doulist> list_result = ddimpl.showMyDouList(request
					.getParameter("user_id"));
			// 查询当前页面查看用户信息
			User_Info curr_user = new User_Info();
			curr_user = psifImpl.showOneByR(request.getParameter("user_id"),
					curr_user);
			request.setAttribute("current_user", curr_user);
			if (list_result != null) {
				// 对应豆列内容类型
				for (Iterator iterator = list_result.iterator(); iterator.hasNext();) {
					User_Doulist user_Doulist = (User_Doulist) iterator.next();
					User_Doulist_Content_Type type = new User_Doulist_Content_Type();
					type = psifImpl.showOneByR(user_Doulist.getContent_type() + "",
							type);
					user_Doulist.setContent_type_table(type);
				}
				request.setAttribute("my_created_doulie", list_result);
			} else {
				request.setAttribute("my_created_doulie", null);
			}
			request.getRequestDispatcher("./doulistinfo?method=showMyFocusDouList")
					.forward(request, response);
		}
		
	}

	public void showMyFocusDouList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 查找用户关注的所有豆列 user
		 
		List<User_Focus_Doulist> list_result = ddimpl.showMyFocusDouList(request.getParameter("user_id"));
		if (list_result != null) {
			// 对应豆列所属用户信息和豆列本身
			for (Iterator iterator = list_result.iterator(); iterator.hasNext();) {
				User_Focus_Doulist user_Focus_Doulist = (User_Focus_Doulist) iterator.next();
				// 该豆列所属用户
				User_Doulist dlist = new User_Doulist();
				dlist = psifImpl.showOneByR(user_Focus_Doulist.getDoulist()+"", dlist);
				User_Info user = new User_Info();
				user = psifImpl.showOneByR(dlist.getFrom_user()+ "", user);
				user_Focus_Doulist.setBelong_who(user);
				// 豆列本身
				User_Doulist doulist = new User_Doulist();
				doulist = psifImpl.showOneByR(user_Focus_Doulist.getDoulist()
						+ "", doulist);
				user_Focus_Doulist.setU_doulist(doulist);
				// 豆列的类型
				User_Doulist_Content_Type type = new User_Doulist_Content_Type();
				type = psifImpl
						.showOneByR(doulist.getContent_type() + "", type);
				user_Focus_Doulist.setContent_type_table(type);
			}
			request.setAttribute("my_focused_doulie", list_result);
		} else {
			request.setAttribute("my_focused_doulie", null);
		}
		request.getRequestDispatcher("/lsr/douban_mydoulie.jsp").forward(
				request, response);
	}

	public void showOneDouList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 查看某一个豆列的详细内容 user
		// 先获取该豆列的类型
		String doulie_type = request.getParameter("doulie_type");
		request.setAttribute("d_type", doulie_type);
		// 创建用于存储该豆列内容的list
		List<Object> list = new ArrayList<Object>();
		List<User_Doulist_Content> list_result = ddimpl.showOneDouList(request.getParameter("doulie_id"));
		// 循环查询每一个内容
		if (list_result != null) {
			for (Iterator iterator = list_result.iterator(); iterator.hasNext();) {
				User_Doulist_Content user_Doulist_Content = (User_Doulist_Content) iterator.next();
				System.out.println("user_Doulist_Content.getItem_id():    "+user_Doulist_Content.getItem_id());
				if ("1".equalsIgnoreCase(doulie_type)) {// 线下活动
					OffActivity act = new OffActivity();
					act = psifImpl.showOneByR(user_Doulist_Content.getItem_id()+ "", act);
					// 查询线上活动的详情
					// 活动创办人
					User_Info user = new User_Info();
					user = psifImpl.showOneByR(act.getUser_id(), user);
					act.setUser(user);
					//活动类型
					Activity_type at = new Activity_type();
					at = psifImpl.showOneByR(act.getOffactivity_type(), at);
					act.setAt(at);
					//城市
					City c = new City();
					c = psifImpl.showOneByR(act.getCity_id(), c);
					act.setCity(c);
					list.add(act);
				} else if ("2".equalsIgnoreCase(doulie_type)) {// 书籍
					Book book = new Book();
					book = psifImpl.showOneByR(
							user_Doulist_Content.getItem_id() + "", book);
					// 查询作者
					Book_Author author = new Book_Author();
					author = psifImpl.showOneByR(book.getBook_author() + "",
							author);
					book.setB_author(author);
					// 查询出版社
					Book_Publisher bp = new Book_Publisher();
					bp = psifImpl.showOneByR(book.getBook_publisher() + "", bp);
					book.setB_publisher(bp);
					// 查询翻译者
					Book_Author trans = new Book_Author();
					trans = psifImpl.showOneByR(book.getTranslator() + "",
							trans);
					book.setB_translator(trans);
					// 查询书籍类型
					Book_Type b_type = new Book_Type();
					b_type = psifImpl.showOneByR(book.getType() + "", b_type);
					book.setB_type(b_type);
					list.add(book);
				} else if ("3".equalsIgnoreCase(doulie_type)) {// 电影
					Movie movie = new Movie();
					movie = psifImpl.showOneByR(
							user_Doulist_Content.getItem_id() + "", movie);
					// 查询导演
					Director d = new Director();
					d = psifImpl.showOneByR(movie.getDirector_id() + "", d);
					movie.setM_d(d);
					// 查询电影类型
					Movie_type m_t = new Movie_type();
					m_t = psifImpl.showOneByR(movie.getType_id() + "", m_t);
					movie.setM_type(m_t);
					list.add(movie);
				} else if ("4".equalsIgnoreCase(doulie_type)) {// 东西
					Things thing = new Things();
					thing = psifImpl.showOneByR(
							user_Doulist_Content.getItem_id() + "", thing);
					// 查询东西的类型
					Things_Type t_t = new Things_Type();
					t_t = psifImpl.showOneByR(thing.getType() + "", t_t);
					thing.setType_table(t_t);
					// 查询东西的发布者
					User_Info f_user = new User_Info();
					f_user = psifImpl.showOneByR(thing.getFrom_user() + "",
							f_user);
					thing.setF_user(f_user);
					list.add(thing);
				}
			}
			System.out.println("list:    "+list);
			request.setAttribute("doulie_content", list);
		}else{
			request.setAttribute("doulie_content", null);
		}
		// 查询豆列的主人信息
		User_Doulist doulie = new User_Doulist();
		doulie = psifImpl.showOneByR(request.getParameter("doulie_id"), doulie);
		User_Info user = new User_Info();
		user = psifImpl.showOneByR(doulie.getFrom_user() + "", user);
		System.out.println("user::::   "+user);
		request.setAttribute("doulie_user", user);
		// 查询该豆列的信息
		User_Doulist thisdoulie = new User_Doulist();
		thisdoulie = psifImpl.showOneByR(request.getParameter("doulie_id"),thisdoulie);
		User_Doulist_Content_Type dd_t = new User_Doulist_Content_Type();
		dd_t = psifImpl.showOneByR(request.getParameter("doulie_type"), dd_t);
		thisdoulie.setContent_type_table(dd_t);
		System.out.println("thisdoulie:::::     "+thisdoulie);
		request.setAttribute("thisdoulie", thisdoulie);
		request.getRequestDispatcher("/lsr/douban_onedoulie.jsp").forward(request, response);
	}

	public void searchDoulistByPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 分页查找某人的豆列 user
		String currentPage = request.getParameter("currentPage");
		if (currentPage == null) {
			currentPage = "1";
		}
		PageBean pageBean = doImpl.searchByPageF(Integer.parseInt(currentPage),
				5, "user_doulist", new User_Doulist(),
				Integer.parseInt(request.getParameter("user_id")));
		if (pageBean != null) {
			// 填充bean
			User_Info f_user = new User_Info();
			f_user = psifImpl.showOneByR(request.getParameter("user_id"),
					f_user);
			List<User_Doulist> mydoulist = pageBean.getData();
			for (User_Doulist list : mydoulist) {
				list.setF_user(f_user);
				User_Doulist_Content_Type type = new User_Doulist_Content_Type();
				type = psifImpl.showOneByR(list.getContent_type() + "", type);
				list.setContent_type_table(type);
			}
			request.setAttribute("mydoulist", pageBean);
		} else {
			request.setAttribute("mydoulist", null);
		}
		// request.getRequestDispatcher("./userinfo?method=searchDiaryByPage").forward(request,
		// response);

		//下一个：我看过的电影
		request.getRequestDispatcher("../user/userinfo?method=searchAllSawMovies").forward(
				request, response);
	}
	
	
	public void showSameTypeDoulist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查询当前用户的所有相同类型的豆列
		HttpSession session = request.getSession();
		User_Info user = (User_Info) session.getAttribute("current_user");
		List<User_Doulist> list = ddimpl.showSameTypeDoulist(Integer.parseInt(request.getParameter("d_type")), user.getUser_id());
		PrintWriter out = response. getWriter ();
		if(list != null){
			response.setContentType("text/plain");
			JsonConfig config = new JsonConfig();
			config.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());
			String s = JsonUtil.getJsonString4JavaPOJO(list, "yyyy-MM-dd");
			out.print(s); 
	    }else{
	    	out.print(false); 
	    }
		 out .flush();
         out .close();
	}
}
