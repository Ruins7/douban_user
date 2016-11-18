package com.services.lsr.userservlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;

import com.commontools.lsr.EntityAddClass;
import com.commontools.lsr.EntityClass;
import com.commontools.lsr.JsonUtil;
import com.commontools.lsr.UserFocusAndBeenFocus;
import com.commontools.lsr.UserFootStep;
import com.commontools.lsr.UserRecommend;
import com.commontools.lsr.UserTools;
import com.dao.hyy.activity.OffActivityDao;
import com.dao.hyy.activity.impl.OffActivityDaoImpl;
import com.dao.lsr.book.dao.BookDao;
import com.dao.lsr.book.daoImpl.BookDaoImpl;
import com.dao.lsr.dbutils.daoImpl.DaoOperationImpl;
import com.dao.lsr.user.daoImpl.BroRecoAndLikeImpl;
import com.dao.lsr.user.daoImpl.PersonalInfoImpl;
import com.dao.wj.moviedao.MovieDao;
import com.entity.hyy.activity.OffActivity;
import com.entity.hyy.city.City;
import com.entity.lsr.book.Book;
import com.entity.lsr.book.BookStation;
import com.entity.lsr.book.Book_Author;
import com.entity.lsr.book.Book_Publisher;
import com.entity.lsr.book.Book_Type;
import com.entity.lsr.things.Things;
import com.entity.lsr.user.Br_type_table;
import com.entity.lsr.user.Item;
import com.entity.lsr.user.PageBean;
import com.entity.lsr.user.User_Album;
import com.entity.lsr.user.User_Broadcast;
import com.entity.lsr.user.User_Commons;
import com.entity.lsr.user.User_Diary;
import com.entity.lsr.user.User_Focus;
import com.entity.lsr.user.User_Info;
import com.entity.lsr.user.User_Leave_Message;
import com.entity.lsr.user.User_Like;
import com.entity.lsr.user.User_Photo;
import com.entity.lsr.user.User_Recommend;
import com.entity.lsr.user.User_Score;
import com.entity.lsr.user.User_Status;
import com.entity.nxt.group.Group_Post;
import com.entity.wj.movie.Director;
import com.entity.wj.movie.Movie;
import com.entity.wj.movie.Movie_type;
import com.entity.wj.movie.ShortMovieComment;

//我的豆瓣模块servlet

@WebServlet(name="UserPersonalServlet", urlPatterns={"/user/userinfo"})
public class UserPersonalServlet extends HttpServlet {

	UserTools tools = new UserTools();
	BroRecoAndLikeImpl bralimpl = new BroRecoAndLikeImpl();
	UserFootStep footstep = new UserFootStep();
	UserTools ut = new UserTools();
	PersonalInfoImpl psifImpl = new PersonalInfoImpl();
	DaoOperationImpl doImpl = new DaoOperationImpl();
	MovieDao md = new MovieDao();
	BookDao bd = new BookDaoImpl();
	OffActivityDao offd = new OffActivityDaoImpl();
	
	
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	      String method = request.getParameter("method");
          if("sendBroadcast".equalsIgnoreCase(method)){
        	  try {
				sendBroadcast(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("delBroadcast".equalsIgnoreCase(method)){
        	  try {
				delBroadcast(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("searchMyBroByPage".equalsIgnoreCase(method)){
        	  try {
        		  searchMyBroByPage(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("showAllMyBroadcast".equalsIgnoreCase(method)){
        	  try {
        		  showAllMyBroadcast(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
          }else if("searchMyRecByPage".equalsIgnoreCase(method)){
        	  try {
        		  searchMyRecByPage(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
          }else if("showAllMyRecommend".equalsIgnoreCase(method)){
        	  try {
        		  showAllMyRecommend(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
          }else if("showAllMyFriendsBroadcast".equalsIgnoreCase(method)){
        	  try {
        		  showAllMyFriendsBroadcast(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
          }else if("searchFriendsBroByPage".equalsIgnoreCase(method)){
        	  try {
        		  searchFriendsBroByPage(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
          }else if("showAllMyFriendsRecommend".equalsIgnoreCase(method)){
        	  try {
        		  showAllMyFriendsRecommend(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
          }else if("searchFriendsRecByPage".equalsIgnoreCase(method)){
        	  try {
        		  searchFriendsRecByPage(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
          }else if("showOneBroadcast".equalsIgnoreCase(method)){ 	  
        		  try {
					showOneBroadcast(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
          }else if("createAlbum".equalsIgnoreCase(method)){
        	  try {
				createAlbum(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("deleteAlbum".equalsIgnoreCase(method)){
        	  try {
				deleteAlbum(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("modifyAlbum".equalsIgnoreCase(method)){
        	  try {
				modifyAlbum(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("showUserAllAlbums".equalsIgnoreCase(method)){
        	  try {
				showUserAllAlbums(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("setAlbumCover".equalsIgnoreCase(method)){
        	  try {
				setAlbumCover(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("searchAlbumsByPage".equalsIgnoreCase(method)){
        	  try {
        		  searchAlbumsByPage(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("showOneAlbum".equalsIgnoreCase(method)){
				try {
					showOneAlbum(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if("uploadPhoto".equalsIgnoreCase(method)){
        	  try {
				uploadPhoto(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("deletePhoto".equalsIgnoreCase(method)){
        	  deletePhoto(request, response);
          }else if("editPhoto".equalsIgnoreCase(method)){
        	  editPhoto(request, response);
          }else if("writeDiary".equalsIgnoreCase(method)){
        	  try {
				writeDiary(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("deleteDiary".equalsIgnoreCase(method)){
        	  deleteDiary(request, response);
          }else if("modifyDiary".equalsIgnoreCase(method)){
        	  modifyDiary(request, response);
          }else if("showAllUserDiarys".equalsIgnoreCase(method)){
        	  showAllUserDiarys(request, response);
          }else if("searchDiaryByPage".equalsIgnoreCase(method)){
        	  searchDiaryByPage(request, response);
          }else if("showOneDiary".equalsIgnoreCase(method)){
        	  showOneDiary(request, response);
          }else if("sendLeaveMessage".equalsIgnoreCase(method)){
        	  try {
				sendLeaveMessage(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("delLeaveMessage".equalsIgnoreCase(method)){
        	  delLeaveMessage(request, response);
          }else if("searchLeaveMessageByPage".equalsIgnoreCase(method)){
              searchLeaveMessageByPage(request, response);
          }else if("whoFocusMe".equalsIgnoreCase(method)){
        	  try {
				whoFocusMe(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("MyFocus".equalsIgnoreCase(method)){
        	  try {
				MyFocus(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("focusOtherUser".equalsIgnoreCase(method)){
        	  try {
				focusOtherUser(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("cancelFocusOtherUser".equalsIgnoreCase(method)){
        	  cancelFocusOtherUser(request, response);
          }else if("recommendToMyBroadcast".equalsIgnoreCase(method)){
        	  try {
				recommendToMyBroadcast(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("delMyRecommend".equalsIgnoreCase(method)){
        	  delMyRecommend(request, response);
          }else if("UserSignin".equalsIgnoreCase(method)){
        	  try {
				UserSignin(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("adminBlackWhiteUser".equalsIgnoreCase(method)){
        	  adminBlackWhiteUser(request, response);
          }else if("UserBlackSelf".equalsIgnoreCase(method)){
        	  UserBlackSelf(request, response);
          }else if("UserLogin".equalsIgnoreCase(method)){
        	  UserLogin(request, response);
          }else if("UserPreLogin".equalsIgnoreCase(method)){
        	  UserPreLogin(request, response);
          }else if("UserLogout".equalsIgnoreCase(method)){
        	  UserLogout(request, response);
          }else if("UserModifyInfo".equalsIgnoreCase(method)){
        	  UserModifyInfo(request, response);
          }else if("UserFindPwd".equalsIgnoreCase(method)){
        	  UserFindPwd(request, response);
          }else if("UserModifyPwd".equalsIgnoreCase(method)){
        	  UserModifyPwd(request, response);
          }else if("UserUploadImg".equalsIgnoreCase(method)){
        	  UserUploadImg(request, response);
          }else if("UserBlackSelf".equalsIgnoreCase(method)){
        	  UserBlackSelf(request, response);
          }else if("likeSomething".equalsIgnoreCase(method)){
        	  try {
				likeSomething(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("showAllUserLike".equalsIgnoreCase(method)){
        	  try {
				showAllUserLike(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("searchPeople".equalsIgnoreCase(method)){
        	    try {
					searchPeople(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
          }else if("searchFocusAndBeen".equalsIgnoreCase(method)){
      	    try {
					searchFocusAndBeen(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		 		}
         }else if("searchLikeByPage".equalsIgnoreCase(method)){
        	  try {
        		  searchLikeByPage(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("giveSomethingCommons".equalsIgnoreCase(method)){
        	  try {
				giveSomethingCommons(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("delOneCommons".equalsIgnoreCase(method)){
        	  try {
				delOneCommons(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("showAllCommonsAboutOneThing".equalsIgnoreCase(method)){
        	  try {
				showAllCommonsAboutOneThing(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("giveSomethingScore".equalsIgnoreCase(method)){
        	  try {
				giveSomethingScore(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          }else if("Score".equalsIgnoreCase(method)){
        	  Score(request, response);
          }else if("adminCheckInvalidFiles".equalsIgnoreCase(method)){
        	  adminCheckInvalidFiles(request, response);
          }else if("modifyUserDesc".equalsIgnoreCase(method)){
        	  modifyUserDesc(request, response);
          }else if("modifyUserSimpleDesc".equalsIgnoreCase(method)){
        	  modifyUserSimpleDesc(request, response);
          }else if("searchAllSawMovies".equalsIgnoreCase(method)){
        	  searchAllSawMovies(request, response);
          }else if("searchAllReadBook".equalsIgnoreCase(method)){
        	  searchAllReadBook(request, response);
          }else if("searchAllUserAct".equalsIgnoreCase(method)){
        	  searchAllUserAct(request, response);
          }else if("searchUser".equalsIgnoreCase(method)){
        	  searchUser(request, response);
          }
	}
	
	//发送广播  user
	public void sendBroadcast(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user");
		User_Broadcast br = new User_Broadcast();
		br = EntityAddClass.upLoadAddressAndReturnEntity(br, request);
		br.setUser(current_user.getUser_id());
		br.setTime(tools.getCurrentAccurateTime());
		br.setType(true);;//原创
		int result = bralimpl.broadcast(br);
		System.out.println(result);
		if(result == 1){
			request.getRequestDispatcher("./userinfo?method=searchFriendsBroByPage&user_id="+current_user.getUser_id()).forward(request, response);
		}else{
			System.out.println("发布广播失败");
		}
	}
	public void delBroadcast(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//删除广播 user
		HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user"); 
		int result = bralimpl.delBroadcast(request.getParameter("broadcast_id"));
		if(result == 1);//成功
	}
	public void searchMyBroByPage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 分页某人的广播 user
		/*HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user"); */
		String typepage = request.getParameter("typepage");
		String currentPage = request.getParameter("currentPage");	
		PrintWriter out = response.getWriter();	
		User_Info this_people = null;
		if(currentPage == null){
			currentPage = "1";
		}
		PageBean pagebean = doImpl.searchByPage(Integer.parseInt(currentPage), 5, "user_broadcast", new User_Broadcast(), Integer.parseInt(request.getParameter("user_id")));
		if(pagebean != null){
		User_Info user = new User_Info();
		user = psifImpl.showOneByR(request.getParameter("user_id"), user);
		//填充bean
			List<User_Broadcast> mybro = pagebean.getData();
			for (User_Broadcast user_bro : mybro) {
				user_bro.setUser_info(user);
				String type = UserRecommend.getItemTypeOFBroadcast(user_bro);
				if(type != null){
					 if(type.equalsIgnoreCase("book")){
					    Book book = new Book();
					    book = psifImpl.showOneByR(user_bro.getSub_item_id()+"", book);
					    Item item = new Item();
					    item.setId(book.getBook_id());
					    item.setName(book.getBook_name());
					    item.setType_table("书籍");
					    item.setImgs(book.getImgs());
					    user_bro.setItem(item);
				     }if(type.equalsIgnoreCase("movie")){
					    Movie movie = new Movie();
					    movie = psifImpl.showOneByR(user_bro.getSub_item_id()+"", movie);
					    Item item = new Item();
					    item.setId(movie.getMovie_id());
					    item.setName(movie.getM_name());
					    item.setImgs(movie.getImgs());
					    item.setType_table("电影");
					    user_bro.setItem(item);
				     }
				}
			}
			if(currentPage == "1"){//首次请求
				request.setAttribute("mybro", pagebean);
				request.setAttribute("this_people", user);
			}else{//ajax请求
				response.setContentType("text/plain");
				JsonConfig config = new JsonConfig();
				config.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());
				String s = JsonUtil.getJsonString4JavaPOJOObj(pagebean, "yyyy-MM-dd");
				out.print(s); 
			}	
		}else{
			if(currentPage == "1"){
				request.setAttribute("mybro", null);
				User_Info user1 = new User_Info();
				user1 = psifImpl.showOneByR(request.getParameter("user_id"), user1);
				System.out.println("this_people::    "+user1);
				request.setAttribute("this_people", user1);
			}else{
				out.print(false);//没有数据了 
			}	
		}//pagebean是否为空
		if(currentPage == "1" && !"ul".equalsIgnoreCase(typepage)){//loginAfter首次请求
			request.getRequestDispatcher("./userinfo?method=searchMyRecByPage").forward(request, response);
		}else if(currentPage == "1" && "ul".equalsIgnoreCase(typepage)){//ul中的请求
	    	request.getRequestDispatcher("/lsr/douban_mybro.jsp").forward(request, response);
	    }else{
	    	out.flush();
			out.close();
	    }	
	}	 
	public void showAllMyBroadcast(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//查看某人的广播 user
		HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user");
		List<User_Broadcast> result_list_b = bralimpl.showAllMyBroadcast(current_user.getUsername()); 
		if(result_list_b != null){
			for (User_Broadcast user_Broadcast : result_list_b) {
				user_Broadcast.setUser_info(current_user);	 
			}
				request.setAttribute("mybro", result_list_b);
		}else{
			System.out.println("没有我的广播");
		}	
		request.getRequestDispatcher("").forward(request, response);
	}
	public void searchMyRecByPage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 分页某人的推荐 user
		/*HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user");*/ 
		String currentPage = request.getParameter("currentPage");
		PrintWriter out = response.getWriter();
		if(currentPage == null){
			currentPage = "1";
		}
		PageBean pagebean = doImpl.searchByPage(Integer.parseInt(currentPage), 5, "user_recommend", new User_Recommend(), Integer.parseInt(request.getParameter("user_id")));
		if(pagebean != null){
		User_Info user = new User_Info();
		user = psifImpl.showOneByR(request.getParameter("user_id"), user);
		//填充bean
			List<User_Recommend> myrec = pagebean.getData();
			for (User_Recommend user_rec : myrec) {
				user_rec.setUser_info(user);
				String type = UserRecommend.getItemTypeOFRecommend(user_rec);
				if(type != null){
					 if(type.equalsIgnoreCase("book")){
					    Book book = new Book();
					    book = psifImpl.showOneByR(user_rec.getSub_item_id()+"", book);
					    Item item = new Item();
					    item.setId(book.getBook_id());
					    item.setName(book.getBook_name());
					    item.setType_table("书籍");
					    item.setImgs(book.getImgs());
					    user_rec.setItem(item);
				     }if(type.equalsIgnoreCase("movie")){
					    Movie movie = new Movie();
					    movie = psifImpl.showOneByR(user_rec.getSub_item_id()+"", movie);
					    Item item = new Item();
					    item.setId(movie.getMovie_id());
					    item.setName(movie.getM_name());
					    item.setImgs(movie.getImgs());
					    item.setType_table("电影");
					    user_rec.setItem(item);
				     }
				}
			}
			if(currentPage == "1"){//首次请求
				request.setAttribute("myrec", pagebean);
			}else{//ajax请求
				response.setContentType("text/plain");
				JsonConfig config = new JsonConfig();
				config.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());
				String s = JsonUtil.getJsonString4JavaPOJOObj(pagebean, "yyyy-MM-dd");
				out.print(s); 
			}	
		}else{
			if(currentPage == "1"){
				request.setAttribute("myrec", null);
			}else{
				out.print(false);//没有数据了 
			}	
		}
		 if(currentPage == "1"){
		    	request.getRequestDispatcher("/lsr/douban_loginAfter.jsp").forward(request, response);
		    }else{
		    	out.flush();
				out.close();
		    }	
	}
	public void showAllMyRecommend(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//查看我自己的推荐 user
		HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user");  
		List<User_Recommend> result_list_r = bralimpl.showAllMyRecommend(current_user.getUsername());
		if(result_list_r != null && result_list_r.size() > 0){
			for (User_Recommend user_Recommend : result_list_r) {
				user_Recommend.setUser_info(current_user);
			}
			//判断推荐东西的类型
			for (User_Recommend user_Recommend : result_list_r) {
				String type = UserRecommend.getItemTypeOFRecommend(user_Recommend);
				if(type.equalsIgnoreCase("book")){
					 Book book = new Book();
			    	 book = psifImpl.showOneByR(user_Recommend.getSub_item_id()+"", book);
					 System.out.println(user_Recommend.getSub_item_id()+ "   mmm");
			    	 System.out.println(book.getBook_id()+"  "+book.getImgs()+"  "+book.getBook_name());
			    	 //设置到通用项目
			    	 Item item = new Item();
			    	 item.setId(book.getBook_id());
			    	 item.setName(book.getBook_name());
			    	 item.setImgs(book.getImgs());
			    	 item.setType_table("图书");
			    	 user_Recommend.setItem(item);	 
				}
			}		
				request.setAttribute("myrec", result_list_r);		
		}else{
			System.out.println("没有我的推荐");
		}
		request.getRequestDispatcher("").forward(request, response);
		
	}
	public void searchFriendsBroByPage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 分页查询某人的好友的广播 user
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null){
			currentPage = "1";
		}
		PageBean pagebean = doImpl.searchByPageFT(Integer.parseInt(currentPage), 5, "user_broadcast", new User_Broadcast(), Integer.parseInt(request.getParameter("user_id")));
		PrintWriter out = response.getWriter();
		if(pagebean != null){
		//填充bean
			List<User_Broadcast> mybro = pagebean.getData();
			for (User_Broadcast user_bro : mybro) {
				User_Info user = new User_Info();
				user = psifImpl.showOneByR(user_bro.getUser()+"", user);
				user_bro.setUser_info(user);
				String type = UserRecommend.getItemTypeOFBroadcast(user_bro);
				if(type != null){//转载广播
					 if(type.equalsIgnoreCase("book")){
					    Book book = new Book();
					    book = psifImpl.showOneByR(user_bro.getSub_item_id()+"", book);
					    Item item = new Item();
					    item.setId(book.getBook_id());
					    item.setName(book.getBook_name());
					    item.setType_table("书籍");
					    item.setImgs(book.getImgs());
					    user_bro.setItem(item);
				     }if(type.equalsIgnoreCase("movie")){
					    Movie movie = new Movie();
					    movie = psifImpl.showOneByR(user_bro.getSub_item_id()+"", movie);
					    Item item = new Item();
					    item.setId(movie.getMovie_id());
					    item.setName(movie.getM_name());
					    item.setImgs(movie.getImgs());
					    item.setType_table("电影");
					    user_bro.setItem(item);
				     }
				}
			}
			if(currentPage == "1"){//首次请求
				request.setAttribute("myfriendsBro", pagebean);
			}else{//ajax请求
				response.setContentType("text/plain");
				JsonConfig config = new JsonConfig();
				config.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());
				String s = JsonUtil.getJsonString4JavaPOJOObj(pagebean, "yyyy-MM-dd");
				out.print(s); 
			}	
		}else{
			if(currentPage == "1"){
				request.setAttribute("myfriendsBro", null);
			}else{
				out.print(false);//没有数据了 
			}	
		}
		if(currentPage == "1"){
	    	request.getRequestDispatcher("./userinfo?method=searchFriendsRecByPage").forward(request, response);
	    }else{
	    	out.flush();
			out.close();
	    }	
	}	 
	public void showAllMyFriendsBroadcast(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//查看某人的好友的广播 user
		List<User_Broadcast> result_list = bralimpl.showAllMyFriendsBroadcast(request.getParameter("user_id"));
		if(result_list != null){
		    if(result_list.size() > 0){
		    	//将广播和好友匹配
		    	for (User_Broadcast user_Broadcast : result_list) {
		    		User_Info user = new User_Info();
		    		user = psifImpl.showOneByR(user_Broadcast.getUser()+"", user);
		    		user_Broadcast.setUser_info(user);
				}
		    	request.setAttribute("myfriendsBro", result_list);
		    }
		}else{
			System.out.println("当前无结果");
		}
		request.getRequestDispatcher("").forward(request, response);
	}
	public void searchFriendsRecByPage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 分页查询某人的好友的推荐  user
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null){
			currentPage = "1";
		}
		PageBean pagebean = doImpl.searchByPageFT(Integer.parseInt(currentPage), 5, "user_recommend", new User_Recommend(), Integer.parseInt(request.getParameter("user_id")));
		PrintWriter out = response.getWriter();
		if(pagebean != null){
		//填充bean
			List<User_Recommend> myrec = pagebean.getData();
			for (User_Recommend user_rec : myrec) {
				User_Info user = new User_Info();
				user = psifImpl.showOneByR(user_rec.getUser()+"", user);
				user_rec.setUser_info(user);
				String type = UserRecommend.getItemTypeOFRecommend(user_rec);
				if(type != null){
					 if(type.equalsIgnoreCase("book")){
					    Book book = new Book();
					    book = psifImpl.showOneByR(user_rec.getSub_item_id()+"", book);
					    Item item = new Item();
					    item.setId(book.getBook_id());
					    item.setName(book.getBook_name());
					    item.setType_table("书籍");
					    item.setImgs(book.getImgs());
					    user_rec.setItem(item);
				     }if(type.equalsIgnoreCase("movie")){
					    Movie movie = new Movie();
					    movie = psifImpl.showOneByR(user_rec.getSub_item_id()+"", movie);
					    Item item = new Item();
					    item.setId(movie.getMovie_id());
					    item.setName(movie.getM_name());
					    item.setImgs(movie.getImgs());
					    item.setType_table("电影");
					    user_rec.setItem(item);
				     }
				}
			}
			if(currentPage == "1"){//首次请求
				request.setAttribute("myfriendsRec", pagebean);
			}else{//ajax请求
				response.setContentType("text/plain");
				JsonConfig config = new JsonConfig();
				config.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());
				String s = JsonUtil.getJsonString4JavaPOJOObj(pagebean, "yyyy-MM-dd");
				out.print(s); 
			}
		}else{
			if(currentPage == "1"){
				request.setAttribute("myfriendsRec", null);
			}else{
				out.print(false);//没有数据了 
			}	
		}
		if(currentPage == "1"){
	    	request.getRequestDispatcher("./userinfo?method=searchMyBroByPage").forward(request, response);
	    }else{
	    	out.flush();
			out.close();
	    }	
		 
	}	 
	public void showAllMyFriendsRecommend(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//查看某人的好友的推荐 user 
		List<User_Recommend> result_list = bralimpl.showAllMyFriendsRecommend(request.getParameter("user_id"));
		if(result_list != null){
			if(result_list.size() > 0){
				//拿到推荐对应的项目之后还需要用户信息
				for (User_Recommend user_Broadcast : result_list) {
		    		User_Info user = new User_Info();
		    		user = psifImpl.showOneByR(user_Broadcast.getUser()+"", user);
		    		user_Broadcast.setUser_info(user);
				}
				//判断推荐东西的类型
				for (User_Recommend user_Recommend : result_list) {
					String type = UserRecommend.getItemTypeOFRecommend(user_Recommend);
					if(type.equalsIgnoreCase("book")){
						 Book book = new Book();
				    	 book = psifImpl.showOneByR(user_Recommend.getSub_item_id()+"", book);
				    	 //设置到通用项目
				    	 Item item = new Item();
				    	 item.setId(book.getBook_id());
				    	 item.setName(book.getBook_name());
				    	 item.setImgs(book.getImgs());
				    	 item.setType_table("书籍");
				    	 user_Recommend.setItem(item);	 
					}
					if(type.equalsIgnoreCase("movie")){
					    Movie movie = new Movie();
					    movie = psifImpl.showOneByR(user_Recommend.getSub_item_id()+"", movie);
					    Item item = new Item();
					    item.setId(movie.getMovie_id());
					    item.setName(movie.getM_name());
					    item.setImgs(movie.getImgs());
					    item.setType_table("电影");
					    user_Recommend.setItem(item);
				     }
				}				 
					request.setAttribute("myfriendsRec", result_list);			
			}
		}else{
			System.out.println("当前无结果");
		}	 
		request.getRequestDispatcher("").forward(request, response);
	}
	public void showOneBroadcast(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//查看某个广播的详细信息 user
		 User_Broadcast result = bralimpl.showOneBroadcast(request.getParameter("broadcast_id"));
		if(result == null){
			System.out.println("当前无结果");
		}else{
			System.out.println(result.getBroadcast_id());
			 
		}
	}
	public void createAlbum(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//用户创建相册 user
		HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user");
		User_Album ua = new User_Album();
		ua = EntityClass.returnEntity(ua, request);
		ua.setUser(current_user.getUser_id());
		ua.setTime(tools.getCurrentAccurateTime());  
		ua.setImgs("/lsr/img/null_album.png");
		int result = psifImpl.createAlbum(ua);
		 
			request.getRequestDispatcher("/lsr/douban_loginAfter.jsp").forward(request, response);
	 
		
	}
	public void deleteAlbum(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//用户删除相册 user
		HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user");
		int result = psifImpl.deleteAlbum(request.getParameter("album_id"));
		System.out.println(result);
		if(result == 1);//成功
	}
	public void modifyAlbum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//用户修改相册 user
		HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user");
		User_Album ua = new User_Album();
		ua = EntityClass.returnEntity(ua, request);
		int result = psifImpl.createAlbum(ua);
		System.out.println(result);
		if(result == 1);//成功
	}
	public void showUserAllAlbums(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String param = request.getParameter("param");
		//查找用户所有相册 user
		List<User_Album> result_list = psifImpl.showUserAllAlbums(request.getParameter("user_id"));
		//查询当前点击用户信息	 
		User_Info user = new User_Info();
		user = psifImpl.showOneByR(request.getParameter("user_id")+"", user);
		//将当前点击的用户信息保存
		request.setAttribute("current_user", user); 
		if(result_list != null){
			//保存当前点击用户的所有相册的 
			request.setAttribute("myalbum", result_list);
			if("fazhaopian_album".equalsIgnoreCase(param)){//中转参数：跳转为添加照片页面
				request.getRequestDispatcher("/lsr/douban_addphoto.jsp").forward(request, response);
			}else if(param == null){
				request.getRequestDispatcher("/lsr/douban_myalbum.jsp").forward(request, response);
			}
		}else{//如果相册为空 
			request.setAttribute("myalbum", null);
			if("fazhaopian_album".equalsIgnoreCase(param)){//中转参数：跳转为添加照片页面
				request.getRequestDispatcher("/lsr/douban_addphoto.jsp").forward(request, response);
			}else if(param == null){
				request.getRequestDispatcher("/lsr/douban_myalbum.jsp").forward(request, response);
			}
		}		
	}
	
	public void setAlbumCover(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		// 设置相册封面图片 user
		User_Photo photo = new User_Photo();
		photo = psifImpl.showOneByR(request.getParameter("photo_id"), photo);
		User_Album album = new User_Album();
		album = psifImpl.showOneByR(request.getParameter("album_id"), album);
		album.setImgs(photo.getPhoto_address());
		int result = psifImpl.modifyAlbum(album);
		PrintWriter out = response.getWriter();
		 if(result > 0) {
			 out.print(true); 
		 }else {
			 out.print(false);	
		 }
		 out.flush();
		 out.close();	
	}
	
	
	public void searchAlbumsByPage(HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		// 分页查找某人相册 user
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null){
			currentPage = "1";
		}
		PageBean pageBean = doImpl.searchByPage(Integer.parseInt(currentPage), 4, "user_album", new User_Album(), Integer.parseInt(request.getParameter("user_id")));
		if(pageBean != null){
		//填充bean
		    User_Info user = new User_Info();
		    user = psifImpl.showOneByR(request.getParameter("user_id"), user);
			List<User_Album> myalbum = pageBean.getData();
			for (User_Album user_Album : myalbum) {
				user_Album.setUser_info(user);
			}
			request.setAttribute("myalbum", pageBean);
		}else{
			request.setAttribute("myalbum", null);
		}
	 	request.getRequestDispatcher("./userinfo?method=searchLeaveMessageByPage").forward(request, response);	
	}
	
	//查看某个相册 user
	public void showOneAlbum(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//查找该相册
		User_Album resule = psifImpl.showOneAlbum(request.getParameter("album_id"));
		if(resule != null){//该相册存在
			//查询该相册所属用户
			User_Info user = new User_Info();
			user = psifImpl.showOneByR(resule.getUser()+"", user); 
			//实体合并
			resule.setUser_info(user); 
			request.setAttribute("thisalbum", resule);
			//查找该相册下的所有照片
			List<User_Photo> photo_result = psifImpl.showPhotoFromOneAlbum(request.getParameter("album_id"));
		    if(photo_result != null){//相册中有照片    	
		    	//查找每张照片的评论
		        for (Iterator iterator = photo_result.iterator(); iterator.hasNext();) {
					User_Photo user_Photo = (User_Photo) iterator.next();
					List<User_Commons> list = psifImpl.queryOnePhotoComment(user_Photo.getPhoto_id()+"");
				    if(list != null){
				    	for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
							User_Commons user_Commons = (User_Commons) iterator2.next();
							User_Info fuser = new User_Info();
							fuser = psifImpl.showOneByR(user_Commons.getFrom_user()+"", fuser);
							user_Commons.setF_user(fuser);
						}
				    }
				    user_Photo.setPc(list);
		        }
				request.setAttribute("albumphotos", photo_result);
				request.setAttribute("album_id", request.getParameter("album_id"));
				//跳转
				request.getRequestDispatcher("/lsr/douban_one_album.jsp").forward(request, response);
		    }else{//相册中无照片
		    	System.out.println("相册中无照片");
		    	request.setAttribute("albumphotos", null);
		    	//跳转
		    	request.getRequestDispatcher("/lsr/douban_one_album.jsp").forward(request, response); 
		    }
		}else{//相册不存在
			System.out.println("相册不存在");
			request.setAttribute("thisalbum", null);
			request.setAttribute("albumphotos", null);
			//跳转
			request.getRequestDispatcher("/lsr/douban_one_album.jsp").forward(request, response);	
		}
		
	}
	
	
	public void uploadPhoto(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//用户上传照片(可单张，可批量) user
		System.out.println("uploadPhoto...");
		User_Photo photo = new User_Photo();
		photo = EntityAddClass.upLoadAddressAndReturnEntity(photo, request);
		photo.setUpload_time(tools.getCurrentAccurateTime());
		int[] result = psifImpl.addPhotoToAlbum(photo);
		 for (int i = 0; i < result.length; i++) {
			 if(result[i] >0 ){
				 //添加成功
				 request.getRequestDispatcher("/lsr/douban_loginAfter.jsp").forward(request, response);
			 };
		}
	}
	public void deletePhoto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//用户删除照片 user
		HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user");
		String[] photo_ids = request.getParameterValues("photo_ids");
		Map<Integer, Integer> map_result = psifImpl.delPhotoToAlbum(photo_ids);
		for (int photo_id : map_result.keySet()) {
			System.out.println("id为：" + photo_id+"的照片删除结果为：" + map_result.get(photo_id));
		} 
	}
	public void editPhoto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//用户编辑照片 user
		User_Photo photo = new User_Photo();
		photo = EntityClass.returnEntity(photo, request);
		int result = psifImpl.editPhoto(photo);
		 System.out.println(result);
	}
	public void writeDiary(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//用户写日志 user
		HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user");
		User_Diary diary = new User_Diary();
		diary = EntityAddClass.upLoadAddressAndReturnEntity(diary, request);
		diary.setTime(tools.getCurrentAccurateTime());
		diary.setUser(current_user.getUser_id());
		int result = psifImpl.writeDiary(diary);
		if(result > 0 ){
			request.getRequestDispatcher("/lsr/douban_loginAfter.jsp").forward(request, response);
		}
		
	}
	public void deleteDiary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//删除日志 user
		HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user");
		int result = psifImpl.delDiary(request.getParameter("diary_id"));
		System.out.println(result);
		if(result == 1);//成功
	}
	public void modifyDiary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//用户修改日志 user
		User_Diary diary = new User_Diary();
		diary = EntityAddClass.upLoadAddressAndReturnEntity(diary, request);
		int result = psifImpl.modifyDiary(diary);
		System.out.println(result);
		if(result == 1);//成功
	}
	public void showAllUserDiarys(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//查找某用户所有日志 user
		List<User_Diary> result_list = psifImpl.showUserAllDiarys(request.getParameter("user_id"));
		//查询该用户
		User_Info user = new User_Info();
		user = psifImpl.showOneByR(request.getParameter("user_id"), user); 
		request.setAttribute("current_user", user);
		if(result_list != null){
			 request.setAttribute("mydiary", result_list);
		}else{
			request.setAttribute("mydiary", null);
		}
		request.getRequestDispatcher("/lsr/douban_mydiary.jsp").forward(request, response);
	}
	public void searchDiaryByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 分页查找某人日志
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null){
			currentPage = "1";
		}
		PageBean pageBean = doImpl.searchByPage(Integer.parseInt(currentPage), 5, "user_diary", new User_Diary(),Integer.parseInt(request.getParameter("user_id")));
		if(pageBean != null){
		//填充bean
		    User_Info user = new User_Info();
		    user = psifImpl.showOneByR(request.getParameter("user_id"), user);
			List<User_Diary> mydiary = pageBean.getData();
			for (User_Diary user_Diary : mydiary) {
				user_Diary.setUser_info(user);
			}
			request.setAttribute("mydiary", pageBean);
		}else{
			request.setAttribute("mydiary", null);
		}
	    request.getRequestDispatcher("./userinfo?method=searchAlbumsByPage").forward(request, response);
    }
	public void showOneDiary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//查看日志详细信息 user	
		User_Diary result = psifImpl.showOneDiary(request.getParameter("diary_id"));
		//用户信息
		User_Info user = new User_Info();
		user = psifImpl.showOneByR(request.getParameter("user_id"), user);
		request.setAttribute("current_user", user);
		if(result != null){
			//该日志的回应
			List<User_Commons> list = psifImpl.queryOneDiaryComment(result.getDiary_id()+"");
			if(list != null){
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					User_Commons user_Commons = (User_Commons) iterator.next();
					User_Info commentuser = new User_Info();
					commentuser = psifImpl.showOneByR(user_Commons.getFrom_user()+"", commentuser);
					user_Commons.setF_user(commentuser);
				}
				request.setAttribute("diary_comment", list);
			}else{
				request.setAttribute("diary_comment", null);
			}
			request.setAttribute("current_diary", result);
		}
		request.getRequestDispatcher("/lsr/douban_onediary.jsp").forward(request, response);
	}
	public void sendLeaveMessage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//给好友留言 user
		HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user");
		User_Leave_Message mess = new User_Leave_Message();
		mess = EntityClass.returnEntity(mess, request);
		mess.setFrom_user(current_user.getUser_id());//主动留言者
		mess.setTime(ut.getCurrentAccurateTime());
		User_Leave_Message result_message = psifImpl.leaveMessage(mess);
		PrintWriter out = response.getWriter();   
		if(result_message != null){//留言成功
			System.out.println("成功！");
			 out.print("true"); 	   
		}else{
			 //留言失败
			System.out.println("失败！");
			out.print("false"); 	 
		}
		out.flush();
		out.close(); 
	}
	public void delLeaveMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//删除好友留言 user	 
		System.out.println(request.getParameter("message_id"));
		int result = psifImpl.delMessage(request.getParameter("message_id"));
		PrintWriter out = response.getWriter();   
		if(result == 1){
			out.print(true);
		}else{
			out.print(false);
		}
		out.flush();
		out.close();
	}
	public void searchLeaveMessageByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		 
		// 分页查找某人的留言 user
				String currentPage = request.getParameter("currentPage");
				if(currentPage == null){
					currentPage = "1";
				}
				PageBean pageBean = doImpl.searchByPageT(Integer.parseInt(currentPage), 4, "user_leave_message", new User_Leave_Message(), Integer.parseInt(request.getParameter("user_id")));
				if(pageBean != null){
				//填充bean
				    User_Info t_user = new User_Info();
				    t_user = psifImpl.showOneByR(request.getParameter("user_id"), t_user);
					List<User_Leave_Message> myleamess = pageBean.getData();
					for (User_Leave_Message user_mess : myleamess) {
						user_mess.setT_user(t_user);
						User_Info f_user = new User_Info();
						f_user = psifImpl.showOneByR(user_mess.getFrom_user()+"", f_user);
						user_mess.setF_user(f_user);
					}
					request.setAttribute("myleavemessage", pageBean);
				}else{
					request.setAttribute("myleavemessage", null);
				}				
			    request.getRequestDispatcher("../things/thingsinfo?method=searchAllThingsByPage").forward(request, response);			 
	}
	
	public void focusOtherUser(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//关注用户 user
		HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user");
		User_Focus uf = new User_Focus();
        uf = EntityClass.returnEntity(uf, request);
		uf.setFrom_user(current_user.getUser_id());
		uf.setTime(ut.getCurrentAccurateTime());
		int result = psifImpl.foucsOthers(uf);
		PrintWriter out = response. getWriter();
        if(result > 0) {
            out.print(true );
        } else {
            out.print(false ); 
        }
        out.flush();
        out.close();
	}
	public void cancelFocusOtherUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//取消关注某用户 user
		HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user");
		int result = psifImpl.cancelFocus(current_user.getUser_id()+"",request.getParameter("to_user"));
		PrintWriter out = response. getWriter();
        if(result > 0) {
            out.print(true );
        } else {
            out.print(false ); 
        }
        out.flush();
        out.close();

	}
	public void recommendToMyBroadcast(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//推荐到我的广播 user
		HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user");
		User_Recommend ur = new User_Recommend();
		ur = EntityClass.returnEntity(ur, request);
		ur.setUser(current_user.getUser_id());
		ur.setTime(ut.getCurrentAccurateTime());
		int result = bralimpl.Recommend(ur);
		PrintWriter out = response.getWriter();
		 if(result > 0) {
            out.print(true);
        } else {
            out.print(false); 
        }
        out.flush();
        out.close();
	}
	public void delMyRecommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//删除我的推荐 user
		HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user");
		int result = bralimpl.delRecommend(request.getParameter("recommend_id"));
		System.out.println(result);
		if(result == 1);//成功
	}
	public void UserSignin(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//用户注册 user
		HttpSession session = request.getSession();
		String check_code = (String) session.getAttribute("vCode");
		//if(check_code.equalsIgnoreCase(request.getParameter("checkcode"))){
			User_Info user = new User_Info();
			user = EntityClass.returnEntity(user, request);
			user.setJoin_in_time(ut.getCurrentAccurateTime());
			user = psifImpl.signin(user);
			City c = new City();
		    c = psifImpl.showOneByR(user.getLocation()+"", c);
		    user.setCity(c);
			if(user != null){
				session.setAttribute("current_user", user);
				request.getRequestDispatcher("/lsr/douban_loginAfter.jsp").forward(request, response);
		    }
		  //}else{
		  //  System.out.println("验证码错误");
			//response.sendRedirect("/lsr/register.jsp");
	      //}
		
		
	}
	public void adminBlackWhiteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	        //管理员拉黑用户/恢复用户账号 admin
		int result = psifImpl.modifyUserStatus(request.getParameter("user_id"), request.getParameter("status"));
		System.out.println(result);
		if(result == 1);//成功     
	}
	public void UserBlackSelf(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    //用户主动注销账号 user
		HttpSession session = request.getSession();
		User_Info currentuser = (User_Info) session.getAttribute("curent_user");
		int result = psifImpl.modifyUserStatus(currentuser.getUser_id()+"" , 1+"");//冻结
		System.out.println(result);
		if(result == 1);//成功     
	}
	public void UserLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 HttpSession session = request.getSession();
		//用户登录 user admin
		String checkcode = (String) session.getAttribute("vCode");
		//登陆验证码
	 	// if(checkcode.equalsIgnoreCase(request.getParameter("checkcode"))){
			 User_Info user = psifImpl.login(request.getParameter("username"),request.getParameter("pwd"));
				if(user != null){
					System.out.println(" 登陆成功");
					City c = new City();
				    c = psifImpl.showOneByR(user.getLocation()+"", c);
				    user.setCity(c);
					session.setAttribute("current_user", user);
					//生成cookie
					//判断是否勾选 记住我
			    	if("yes".equalsIgnoreCase(request.getParameter("freetologin"))){
			    		//创建cookie
			    		Cookie cookie = new Cookie("user_info" , request.getParameter("username")+","+request.getParameter("pwd"));
			    		cookie.setMaxAge(60*60*24);//cookie有效期：一天
			    		response.addCookie(cookie);
			    		System.out.println("cookie创建成功！");
			    	}
					//当用户登录成功时（手动登陆和cookie登陆）
					//获取用户浏览记录的所有cookie到session中
					//footstep.readFootStepFromCookie(request);
					//获取我的好友信息
					UserFocusAndBeenFocus.findWhoFocusMeAndMyFocus(request);
					//获取系统豆邮到session
					//UserGetActivity.getActOfMe(request, user.getUser_id()+"");
					request.getRequestDispatcher("./userinfo?method=searchFriendsBroByPage&user_id="+user.getUser_id()).forward(request, response);
		 		}else{
					//重新登陆
					System.out.println("登陆失败");
					response.sendRedirect("../lsr/douban_login.jsp");
				} 
		  //}else{
		  // System.out.println("验证码不匹配");
		  //	 response.sendRedirect("../lsr/douban_login.jsp");
	      //}
		
		
		
	}
	public void UserPreLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" --------------prelogin--------------");
		//用户cookie登陆(项目上来预登陆)
	    HttpSession session = request.getSession();
	    Cookie[] cookies = request.getCookies();
	    if(cookies != null){//有cookies
	    	System.out.println(" -------------有cookie-------------");
			int i = 0;
			for (Cookie cookie : cookies) {
				if("user_info".equalsIgnoreCase(cookie.getName())){
					System.out.println("找到cookie");
					String[] value = cookie.getValue().split(",");	 			 				
					User_Info user = psifImpl.login(value[0], value[1]);
				    if(user != null){
				    	i+=1;
				    	session.setAttribute("current_user", user);
				    	//cookie登陆成功
				    	//当用户登录成功时（手动登陆和cookie登陆）
						//获取用户浏览记录的所有cookie到session中
						//footstep.readFootStepFromCookie(request);
				    	request.getRequestDispatcher("./userinfo?method=searchFriendsBroByPage&user_id="+user.getUser_id()).forward(request, response);
				    } 
				    break;
				}
			}
			if(i == 0){
				//没有找到cookie，登陆失败
				System.out.println(" -------------没有cookie-------------");
				response.sendRedirect("../lsr/douban_login.jsp");
			}		
		}else{
			//没有找到cookie，登陆失败
			System.out.println(" -------------没有cookie-------------");
			response.sendRedirect("../lsr/douban_login.jsp");
		} 
	
	}
	public void UserLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//用户退出登录 user, admin
		HttpSession session = request.getSession();
		session.invalidate();
        //用户手动退出时删除登陆cookie
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for (Cookie cookie : cookies) {
				if("user_info".equalsIgnoreCase(cookie.getName())){
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}	
		//当用户退出时，将内存中的浏览记录写回cookie
		//footstep.writeFootStepToCookie(request, response);
		request.getRequestDispatcher("/lsr/douban_login.jsp").forward(request, response);
	}
	public void UserModifyInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    //用户更改个人信息 user	
		    User_Info user = new User_Info();
		    user = EntityAddClass.upLoadAddressAndReturnEntity(user, request);
		    int result = psifImpl.modifyUserInfo(user);
			if(result == 1){
				HttpSession session = request.getSession();		 
 				//将刚才修改的用户信息更新入session
 				User_Info n_user = new User_Info();
 				n_user = psifImpl.showOneByR(user.getUser_id()+"", n_user);
 				City c = new City();
 				c = psifImpl.showOneByR(user.getLocation()+"", c);
 				n_user.setCity(c);
 				User_Status state = new User_Status();
 				state = psifImpl.showOneByR(user.getStatus()+"", state);
 				n_user.setUser_state(state);
 				session.setAttribute("current_user", n_user);
				request.getRequestDispatcher("./userinfo?method=searchFriendsBroByPage&user_id="+ user.getUser_id()).forward(request, response);
			}else{//修改失败
				request.getRequestDispatcher("./user/sandmailnotice?method=showPersonalInfo&user_id="+ user.getUser_id()).forward(request, response);
			}	   
	}
	public void UserFindPwd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		//用户找回密码 user
		User_Info user = new User_Info();
		user = psifImpl.searchUserByEmail(request.getParameter("username"), request.getParameter("email"));
	    if(user != null){
	    	//找到该用户
	    	//发邮件。。。
	    }else{
	    	//无此用户
	    }
	}
	public void UserModifyPwd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//用户更改密码 user
		int result = psifImpl.modifyPwd(request.getParameter("pwd"), request.getParameter("user_id"));
	    System.out.println(result);
	    if(result > 0);//成功
	}
	public void UserUploadImg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 用户上传头像 user,admin
		 int result = psifImpl.modifyUserImg("1", request.getParameter("imgs"));
		 
	}
	public void likeSomething(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 喜欢 user
		HttpSession session = request.getSession();
		User_Info current_user = (User_Info) session.getAttribute("current_user");
		User_Like like = new User_Like();
		like = EntityClass.returnEntity(like, request);//需要user, item(类型：线上活动onlineactivity，话题topic，东西things，日志diary，相册album，照片photo)
		like.setTime(ut.getCurrentAccurateTime());
		like.setUser(current_user.getUser_id());
		int result = bralimpl.likeSomething(like);
		PrintWriter out = response. getWriter();
        if(result > 0) {
            out.print(true );
        } else {
            out.print(false ); 
        }
        out.flush();
        out.close();

	}	 
	public void showAllUserLike(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//  该用户所有喜欢 user
		List<User_Like> like_list = bralimpl.showAllMyLikeTotal(Integer.parseInt(request.getParameter("user_id")));
		User_Info user = new User_Info();
		user = psifImpl.showOneByR(request.getParameter("user_id"), user);
		request.setAttribute("current_user", user);
		if(like_list != null){
			System.out.println("喜欢的项目个数： "+ like_list.size());
			//详细查询喜欢的每一个项目
			for (Iterator iterator = like_list.iterator(); iterator.hasNext();) {
				User_Like user_Like = (User_Like) iterator.next();
				Br_type_table type = new Br_type_table();
				//1.先查询喜欢的类型
				type = psifImpl.showOneByR(user_Like.getItem()+"", type);
				
				//2.再根据喜欢的类型和id查询喜欢的具体项目
				String typeName = type.getType_table_name();
				System.out.println("喜欢的类型:  "+ typeName);
				//判断类型  
				if("东西".equalsIgnoreCase(typeName)){//东西4
					Things thing = new Things();
					thing = psifImpl.showOneByR(user_Like.getItem_id()+"", thing);
					user_Like.setItem_name("东西");
					//东西名称
					//东西图片地址
				    user_Like.setThing(thing);
				}else if("日志".equalsIgnoreCase(typeName)){//日志5
					User_Diary diary = new User_Diary();
					diary = psifImpl.showOneByR(user_Like.getItem_id()+"", diary);
					user_Like.setItem_name("日志");
					//日志内容
					 
					//日志所属用户
					User_Info diary_author = new User_Info();
					diary_author = psifImpl.showOneByR(diary.getUser()+"", diary_author);
					diary.setUser_info(diary_author);
					//日志名称
					 
					user_Like.setDiary(diary);
				 
					
				}else if("相册".equalsIgnoreCase(typeName)){//相册6
			        User_Album album = new User_Album();
			        album = psifImpl.showOneByR(user_Like.getItem_id()+"", album);
			        user_Like.setItem_name("相册");
			    	//相册所属用户
			        User_Info album_author = new User_Info();
			    	album_author = psifImpl.showOneByR(album.getUser()+"", album_author);
			    	album.setUser_info(album_author);
			    	//相册封面图片地址
			    	//相册名称
			    	//相册id
			     
			        user_Like.setAlbum(album);
			    }else if("线下活动".equalsIgnoreCase(typeName)){//线下活动7
			        OffActivity activity = new OffActivity();
			        activity = psifImpl.showOneByR(user_Like.getItem_id()+"", activity);
			        user_Like.setItem_name("线下活动");
			        //活动举办人
			        User_Info make_user = new User_Info();
			        make_user = psifImpl.showOneByR(activity.getUser_id(), make_user);
			        activity.setUser(make_user);
			        //活动海报
			        //活动名称
			        user_Like.setActivity(activity);
			    }else if("照片".equalsIgnoreCase(typeName)){//照片8
			        User_Photo photo = new User_Photo();
			        photo = psifImpl.showOneByR(user_Like.getItem_id()+"", photo);
			        user_Like.setItem_name("照片");
			        //照片名称
			        //照片地址
			        //照片所属相册
			        User_Album album = new User_Album();
			        album = psifImpl.showOneByR(photo.getFrom_album()+"", album);
			        photo.setAlbum(album);
			        //照片所属用户
			        User_Info photo_owner = new User_Info();
			        photo_owner = psifImpl.showOneByR(album.getUser()+"", photo_owner);
			        album.setUser_info(photo_owner);
			        
			        user_Like.setPhoto(photo);
			             
			    }else if("话题".equalsIgnoreCase(typeName)){//话题9
			        Group_Post topic = new Group_Post();
			        topic = psifImpl.showOneByR(user_Like.getItem_id()+"", topic);
			        user_Like.setItem_name("话题");
			        //发起人
			        User_Info topic_owner = new User_Info();
			        topic_owner = psifImpl.showOneByR(topic.getPost_author()+"", topic_owner);
			        topic.setUser_info(topic_owner);
			        
			        //话题名称
			        user_Like.setTopic(topic);
			    }else if("书籍".equalsIgnoreCase(typeName)){//书籍1
			    	user_Like.setItem_name("书籍");
			    	Book b = new Book();
			    	b = psifImpl.showOneByR(user_Like.getItem_id()+"", b);
			    	//作者
			    	Book_Author ba = new Book_Author();
			    	ba = psifImpl.showOneByR(b.getBook_author()+"", ba);
			    	b.setB_author(ba);
			    	//出版社
			    	Book_Publisher bp = new Book_Publisher();
			    	bp = psifImpl.showOneByR(b.getBook_publisher()+"", bp);
			    	b.setB_publisher(bp);
			    	//书籍类型
			    	Book_Type bt  = new Book_Type();
			    	bt = psifImpl.showOneByR(b.getType()+"", bt);
			    	b.setB_type(bt);
			    	
			    	user_Like.setBook(b);
			    }else if("电影".equalsIgnoreCase(typeName)){//电影3
			    	user_Like.setItem_name("电影");
			    	Movie m = new Movie();
			    	m = psifImpl.showOneByR(user_Like.getItem_id()+"", m);
			    	//导演
			    	Director d = new Director();
			    	d = psifImpl.showOneByR(m.getDirector_id()+"", d);
			    	m.setM_d(d);
			    	//电影类型
			    	Movie_type mt = new Movie_type();
			    	mt = psifImpl.showOneByR(m.getType_id()+"", mt);
			    	m.setM_type(mt);
			    	user_Like.setMovie(m);
			    }else if("广播".equalsIgnoreCase(typeName)){//广播10
			    	user_Like.setItem_name("广播");
			    	User_Broadcast ub = new User_Broadcast();
			    	ub = psifImpl.showOneByR(user_Like.getItem_id()+"", ub);
			    	User_Info cuser = new User_Info();
			    	cuser = psifImpl.showOneByR(ub.getUser()+"", cuser);
			    	ub.setUser_info(cuser);
			    	user_Like.setBro(ub);
			    	
			    }
			}
			request.setAttribute("mylike", like_list);
		}else{
			request.setAttribute("mylike", null);
		}
     	request.getRequestDispatcher("/lsr/douban_mylike.jsp").forward(request, response);
	}	 
	public void searchPeople(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 查找当前要看的这个用户 user 
		User_Info user = new User_Info();
		user = psifImpl.showOneByR(request.getParameter("user_id"), user);
		City c = new City();
		c = psifImpl.showOneByR(user.getLocation()+"", c);
		user.setCity(c);
		//查询当前登陆用户是否关注了TA
		//查询我关注的所有用户
		boolean guanzhu = false;
        HttpSession session = request.getSession();
        User_Info cuser = (User_Info) session.getAttribute("current_user");
        List<User_Info> myf_list = psifImpl.MyFocus(cuser.getUser_id()+"");
        if(myf_list != null){
        	for (Iterator iterator = myf_list.iterator(); iterator.hasNext();) {
    			User_Info user_Info = (User_Info) iterator.next();
    			if(user_Info.getUser_id() == user.getUser_id()){
    				System.out.println("已关注此人");
    				guanzhu = true;
    			}
    		}
        }else{
        	guanzhu = false;
        }
        request.setAttribute("guanzhu", guanzhu);
        request.setAttribute("current_user", user);
		request.getRequestDispatcher("./userinfo?method=searchFocusAndBeen").forward(request, response);
	}	
	public void whoFocusMe(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//查找关注我的用户 user
		System.out.println();
		HttpSession session = request.getSession();
		User_Info user = (User_Info) session.getAttribute("current_user");
		List<User_Info> users = psifImpl.whoFocusMe(user.getUser_id()+"");
		if(users != null){
			System.out.println("当前关注我的人数:"+users.size());
			session.setAttribute("whofocusme", users.size());
		}else{
			session.setAttribute("whofocusme", 0);
		}
	}
	public void MyFocus(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//查找某人关注的用户 user
		List<User_Info> users = psifImpl.MyFocus(request.getParameter("user_id"));
		if(users != null){
			for (User_Info user_Info : users) {
				City c = new City();
				c = psifImpl.showOneByR(user_Info.getLocation()+"", c); 
				user_Info.setCity(c);
			}
			User_Info cuser = new User_Info();
			cuser = psifImpl.showOneByR(request.getParameter("user_id"), cuser);
			request.setAttribute("current_user", cuser);
			request.setAttribute("friendslist", users);
			request.getRequestDispatcher("/lsr/douban_common.jsp").forward(request, response);
		} 
	}
	public void searchFocusAndBeen(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 查找当前要看的这个用户的好友 user 
		UserFocusAndBeenFocus.findWhoFocusMeAndMyFocusForOthers(request);
		request.getRequestDispatcher("./userinfo?method=searchLikeByPage").forward(request, response);
	}	 
	public void searchLikeByPage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 分页查询某人的喜欢 user
		List<User_Like> like_list = bralimpl.showAllMyLikeTotal(Integer.parseInt(request.getParameter("user_id")));
		User_Info user = new User_Info();
		user = psifImpl.showOneByR(request.getParameter("user_id"), user);
		request.setAttribute("current_user", user);
		if(like_list != null){
			System.out.println("喜欢的项目个数： "+ like_list.size());
			//详细查询喜欢的每一个项目
			for (Iterator iterator = like_list.iterator(); iterator.hasNext();) {
				User_Like user_Like = (User_Like) iterator.next();
				Br_type_table type = new Br_type_table();
				//1.先查询喜欢的类型
				type = psifImpl.showOneByR(user_Like.getItem()+"", type);
				
				//2.再根据喜欢的类型和id查询喜欢的具体项目
				String typeName = type.getType_table_name();
				System.out.println("喜欢的类型:  "+ typeName);
				//判断类型  
				if("东西".equalsIgnoreCase(typeName)){//东西4
					Things thing = new Things();
					thing = psifImpl.showOneByR(user_Like.getItem_id()+"", thing);
					user_Like.setItem_name("东西");
					//东西名称
					//东西图片地址
				    user_Like.setThing(thing);
				}else if("日志".equalsIgnoreCase(typeName)){//日志5
					User_Diary diary = new User_Diary();
					diary = psifImpl.showOneByR(user_Like.getItem_id()+"", diary);
					user_Like.setItem_name("日志");
					//日志内容
					 
					//日志所属用户
					User_Info diary_author = new User_Info();
					diary_author = psifImpl.showOneByR(diary.getUser()+"", diary_author);
					diary.setUser_info(diary_author);
					//日志名称
					 
					user_Like.setDiary(diary);
				 
					
				}else if("相册".equalsIgnoreCase(typeName)){//相册6
			        User_Album album = new User_Album();
			        album = psifImpl.showOneByR(user_Like.getItem_id()+"", album);
			        user_Like.setItem_name("相册");
			    	//相册所属用户
			        User_Info album_author = new User_Info();
			    	album_author = psifImpl.showOneByR(album.getUser()+"", album_author);
			    	album.setUser_info(album_author);
			    	//相册封面图片地址
			    	//相册名称
			    	//相册id
			     
			        user_Like.setAlbum(album);
			    }else if("线下活动".equalsIgnoreCase(typeName)){//线下活动7
			        OffActivity activity = new OffActivity();
			        activity = psifImpl.showOneByR(user_Like.getItem_id()+"", activity);
			        user_Like.setItem_name("线下活动");
			        //活动举办人
			        User_Info make_user = new User_Info();
			        make_user = psifImpl.showOneByR(activity.getUser_id(), make_user);
			        activity.setUser(make_user);
			        //活动海报
			        //活动名称
			        user_Like.setActivity(activity);
			    }else if("照片".equalsIgnoreCase(typeName)){//照片8
			        User_Photo photo = new User_Photo();
			        photo = psifImpl.showOneByR(user_Like.getItem_id()+"", photo);
			        user_Like.setItem_name("照片");
			        //照片名称
			        //照片地址
			        //照片所属相册
			        User_Album album = new User_Album();
			        album = psifImpl.showOneByR(photo.getFrom_album()+"", album);
			        photo.setAlbum(album);
			        //照片所属用户
			        User_Info photo_owner = new User_Info();
			        photo_owner = psifImpl.showOneByR(album.getUser()+"", photo_owner);
			        album.setUser_info(photo_owner);
			        
			        user_Like.setPhoto(photo);
			             
			    }else if("话题".equalsIgnoreCase(typeName)){//话题9
			        Group_Post topic = new Group_Post();
			        topic = psifImpl.showOneByR(user_Like.getItem_id()+"", topic);
			        user_Like.setItem_name("话题");
			        //发起人
			        User_Info topic_owner = new User_Info();
			        topic_owner = psifImpl.showOneByR(topic.getPost_author()+"", topic_owner);
			        topic.setUser_info(topic_owner);
			        
			        //话题名称
			        user_Like.setTopic(topic);
			    }else if("书籍".equalsIgnoreCase(typeName)){//书籍1
			    	user_Like.setItem_name("书籍");
			    	Book b = new Book();
			    	b = psifImpl.showOneByR(user_Like.getItem_id()+"", b);
			    	//作者
			    	Book_Author ba = new Book_Author();
			    	ba = psifImpl.showOneByR(b.getBook_author()+"", ba);
			    	b.setB_author(ba);
			    	//出版社
			    	Book_Publisher bp = new Book_Publisher();
			    	bp = psifImpl.showOneByR(b.getBook_publisher()+"", bp);
			    	b.setB_publisher(bp);
			    	//书籍类型
			    	Book_Type bt  = new Book_Type();
			    	bt = psifImpl.showOneByR(b.getType()+"", bt);
			    	b.setB_type(bt);
			    	
			    	user_Like.setBook(b);
			    }else if("电影".equalsIgnoreCase(typeName)){//电影3
			    	user_Like.setItem_name("电影");
			    	Movie m = new Movie();
			    	m = psifImpl.showOneByR(user_Like.getItem_id()+"", m);
			    	//导演
			    	Director d = new Director();
			    	d = psifImpl.showOneByR(m.getDirector_id()+"", d);
			    	m.setM_d(d);
			    	//电影类型
			    	Movie_type mt = new Movie_type();
			    	mt = psifImpl.showOneByR(m.getType_id()+"", mt);
			    	m.setM_type(mt);
			    	user_Like.setMovie(m);
			    }else if("广播".equalsIgnoreCase(typeName)){//广播10
			    	user_Like.setItem_name("广播");
			    	User_Broadcast ub = new User_Broadcast();
			    	ub = psifImpl.showOneByR(user_Like.getItem_id()+"", ub);
			    	User_Info cuser = new User_Info();
			    	cuser = psifImpl.showOneByR(ub.getUser()+"", cuser);
			    	ub.setUser_info(cuser);
			    	user_Like.setBro(ub);
			    	
			    }
			}
			request.setAttribute("mylike", like_list);
			request.setAttribute("mylike_size", like_list.size());
		}else{
			request.setAttribute("mylike", null);
		}
		request.getRequestDispatcher("./userinfo?method=searchDiaryByPage").forward(request, response);
	}	 
	public void giveSomethingCommons(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 // 用户讨论(电影，读书，活动，东西，日志，照片，广播,推荐) user
		 User_Commons c = new User_Commons();
		 c = EntityClass.returnEntity(c, request);
		 HttpSession session = request.getSession();
	     User_Info user = (User_Info) session.getAttribute("current_user");
		 c.setTime(ut.getCurrentAccurateTime());
		 c.setFrom_user(user.getUser_id());
		 int result = bralimpl.addDiscussAbout(c);
		 System.out.println(result);
		 PrintWriter out = response.getWriter();
		 if(result > 0) {
			 out.print(true); 
		 }else {
			 out.print(false);	
		 }
		 out.flush();
		 out.close();	
	}
	public void delOneCommons(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 删除讨论(电影，读书，活动，东西，日志，照片，广播,推荐)  user
		int result = bralimpl.delDiscussAbout(request.getParameter("commons_id"));
	}
	public void showAllCommonsAboutOneThing(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 显示某一个事物的所有评论  user
		List result = bralimpl.showAllCommonsAboutOneThing(request.getParameter("item"), request.getParameter("item_id"));
	}
	public void giveSomethingScore(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 打分 user
		User_Score s = new User_Score();
		s = EntityClass.returnEntity(s, request);
		s.setTime(ut.getCurrentAccurateTime());
		bralimpl.judgeScore(s);
	}
	public void Score(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 显示分数  user
		double score = bralimpl.showScore(request.getParameter("itme_id"),request.getParameter("itme"));
		
	}
	public void adminCheckInvalidFiles(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 管理员整理已失效的用户上传的文件 admin
		List<Object[]> result = psifImpl.adminCheckInvalidFiels();
		//循环检测地址是否与文件匹配
	    for (Object[] objects : result) {
			 File file = new File((String)objects[0]);		 
		}
	}
	public void modifyUserDesc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 修改user_desc user
		int result = psifImpl.modifyUserDesc(request.getParameter("user_id"), request.getParameter("user_desc"));
		PrintWriter out = response.getWriter();
		if(result == 1){
            out.print("true"); 	    
	    }else{
	    	out.print("false"); 	 
	    }
	    out.flush();
		out.close();
	}
	public void modifyUserSimpleDesc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 修改simple_desc user
		String result = psifImpl.modifyUserSimpleDesc(request.getParameter("user_id"), request.getParameter("simple_desc"));
		PrintWriter out = response.getWriter();
		if(result != null){
			HttpSession session = request.getSession();
			User_Info user = (User_Info) session.getAttribute("current_user");
			user.setSimple_intro(result);
			session.setAttribute("current_user", user);
			out.print("true"); 	    
		}else{
			out.print("false"); 	 
		}
		out.flush();
		out.close();
	}
	
	public void searchAllSawMovies(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 查找某人看过,想看的所有电影
		//已看
		 List<ShortMovieComment> list = md.getUserAllSawMovies(request.getParameter("user_id"));
	     if(list != null){
	    	 for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				ShortMovieComment shortMovieComment = (ShortMovieComment) iterator.next();
				Movie m = new Movie();
				m = psifImpl.showOneByR(shortMovieComment.getMovie_id()+"", m);
				shortMovieComment.setMovie(m);
			}
	    	 request.setAttribute("sawmovies", list);
	    	 request.setAttribute("sawmovies_size", list.size());
	     }else{
	    	 request.setAttribute("sawmovies", null);
	     }
	   //想看
		 List<ShortMovieComment> list_want = md.getUserAllWantSawMovies(request.getParameter("user_id"));
	     if(list_want != null){
	    	 for (Iterator iterator = list_want.iterator(); iterator.hasNext();){
				ShortMovieComment shortMovieComment = (ShortMovieComment) iterator.next();
				Movie m = new Movie();
				m = psifImpl.showOneByR(shortMovieComment.getMovie_id()+"", m);
				shortMovieComment.setMovie(m);
			 }
	    	 request.setAttribute("wantsawmovies", list_want);
	    	 request.setAttribute("wantsawmovies_size", list_want.size());
	     }else{
	    	 request.setAttribute("wantsawmovies", null);
	     }
	     //我读过的所有书
	     request.getRequestDispatcher("./userinfo?method=searchAllReadBook").forward(request, response);
	}
	
	public void searchAllReadBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 查找某人读过,在读，想读的所有书
		//已读
		 List<BookStation> list_readed = bd.showUserReadAllbook(request.getParameter("user_id"));
	     if(list_readed != null){
	    	 for (Iterator iterator = list_readed.iterator(); iterator.hasNext();) {
				BookStation bookStation = (BookStation) iterator.next();
				Book b = new Book();
				b = psifImpl.showOneByR(bookStation.getBook_id()+"", b);
				bookStation.setBook(b);
			}
	    	 request.setAttribute("readedbook", list_readed);
	    	 request.setAttribute("readedbook_size", list_readed.size());
	     }else{
	    	 request.setAttribute("readedbook", null);
	     }
	     //在读
	     List<BookStation> list_reading = bd.showUserReadingAllbook(request.getParameter("user_id"));
	     if(list_reading != null){
	    	 for (Iterator iterator = list_reading.iterator(); iterator.hasNext();) {
				BookStation bookStation = (BookStation) iterator.next();
				Book b = new Book();
				b = psifImpl.showOneByR(bookStation.getBook_id()+"", b);
				bookStation.setBook(b);
			}
	    	 request.setAttribute("readingbook", list_reading);
	    	 request.setAttribute("readingbook_size", list_reading.size());
	     }else{
	    	 request.setAttribute("readingbook", null);
	     }
	    
	   //想读
	     List<BookStation> list_wantread = bd.showUserWantReadAllbook(request.getParameter("user_id"));
	     if(list_wantread != null){
	    	 for (Iterator iterator = list_wantread.iterator(); iterator.hasNext();) {
				BookStation bookStation = (BookStation) iterator.next();
				Book b = new Book();
				b = psifImpl.showOneByR(bookStation.getBook_id()+"", b);
				bookStation.setBook(b);
			}
	    	 request.setAttribute("wantreadbook", list_wantread);
	    	 request.setAttribute("wantreadbook_size", list_wantread.size());
	     }else{
	    	 request.setAttribute("wantreadbook", null);
	     }
	     //我的活动
	    request.getRequestDispatcher("./userinfo?method=searchAllUserAct").forward(request, response);
	}
	
	public void searchAllUserAct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 某用户参加的所有活动 user
		List<OffActivity> list = offd.OneUserAttendAllOffActivity(request.getParameter("user_id"));
	    if(list != null){
	    	for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				OffActivity offActivity = (OffActivity) iterator.next();
				City c = new City();
				c = psifImpl.showOneByR(offActivity.getCity_id(), c);	
			}
	    	request.setAttribute("attendact", list);
	    	request.setAttribute("attendact_size", list.size());
	    }else{
	    	request.setAttribute("attendact", null);
	    }
	    //我的豆列
	    request.getRequestDispatcher("../doulist/doulistinfo?method=showMyDouList&params=created").forward(request, response);
	}
	
	public void searchUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 查找用户 user
		List<User_Info> list = psifImpl.queryUser(request.getParameter("username"));
	    if(list != null){
	    	 for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				User_Info user_Info = (User_Info) iterator.next();
				City c = new City();
				c = psifImpl.showOneByR(user_Info.getLocation()+"", c);
				user_Info.setCity(c);
			}
	    	 //查询我关注的所有用户
	         HttpSession session = request.getSession();
	         User_Info cuser = (User_Info) session.getAttribute("current_user");
	         List<User_Info> myf_list = psifImpl.MyFocus(cuser.getUser_id()+"");
	        if(myf_list != null){
	        	//设置关注标志位
	        	for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					User_Info user_Info = (User_Info) iterator.next();
					for (Iterator iterator2 = myf_list.iterator(); iterator2.hasNext();) {
						User_Info user_Info2 = (User_Info) iterator2.next();
						if(user_Info2.getUser_id() == user_Info.getUser_id()){
							user_Info.setGaunzhu(true);
						}
					}
				}        	
	        } 
	    	request.setAttribute("friendslist", list);
	    	request.setAttribute("friendslist_size", list.size());
	    }else{
	    	request.setAttribute("friendslist", null);
	    	request.setAttribute("friendslist_size", 0);
	    }
	    //我的豆列
	    request.getRequestDispatcher("/lsr/douban_usersearch.jsp").forward(request, response);
	}
	
}
