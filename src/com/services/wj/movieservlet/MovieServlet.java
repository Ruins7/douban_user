package com.services.wj.movieservlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.lsr.user.daoImpl.PersonalInfoImpl;
import com.dao.wj.moviedao.DirectorDao;
import com.dao.wj.moviedao.MovieCommentDao;
import com.dao.wj.moviedao.MovieDao;
import com.dao.wj.moviedao.MovieRankingDao;
import com.dao.wj.moviedao.Movie_typeDao;
import com.dao.wj.moviedao.RuinsShortCommentDao;
import com.dao.wj.moviedao.ShortCommentDao;
import com.dao.wj.utildao.JsonUtil;
import com.entity.lsr.user.User_Info;
import com.entity.wj.movie.Director;
import com.entity.wj.movie.Movie;
import com.entity.wj.movie.MovieComment;
import com.entity.wj.movie.Movie_type;
import com.entity.wj.movie.ShortMovieComment;

@WebServlet(name="movieServlet",urlPatterns="/servlet/movieServlet")
/**
 * 电影
 * @author 汪进
 *
 */
public class MovieServlet extends HttpServlet {
	 MovieDao movieDao=new MovieDao();
	   Movie movie=new Movie();
	   PersonalInfoImpl psifImpl = new PersonalInfoImpl();
	   MovieCommentDao movieCommentDao=new MovieCommentDao();
	   RuinsShortCommentDao rs = new RuinsShortCommentDao();
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
         String method=request.getParameter("method");
         System.out.println(method);
         if("saveMovie".equalsIgnoreCase(method)){
        	 saveMovie(request,response);
         }else if("delMovie".equalsIgnoreCase(method)){
        	 delMovie(request,response);
         }else if("allfindMovies".equalsIgnoreCase(method)){
        	 allfindMovies(request,response);
         }else if("updateMovie".equalsIgnoreCase(method)){
        	 updateMovie(request,response);
         }else if("setPageObjects".equalsIgnoreCase(method)){
        	 setPageObjects(request,response);
         }else if("findOneMovie".equalsIgnoreCase(method)){
        	 findOneMovie(request,response);
         }else if("getMovieJsonByDate".equalsIgnoreCase(method)){
        	 getMovieJsonByDate(request,response);
         }else if("getMovieJsonByCount".equalsIgnoreCase(method)){
        	 getMovieJsonByCount(request,response);
         }else if("getMovieJsonType".equalsIgnoreCase(method)){
        	 getMovieJsonType(request,response);
         }else if("getMovieJsonDistrict".equalsIgnoreCase(method)){
        	 getMovieJsonDistrict(request,response);
         }else if("getListJsonByCount".equalsIgnoreCase(method)){
        	 getListJsonByCount(request,response);
         }else if("showMovieByDirector".equalsIgnoreCase(method)){
        	 showMovieByDirector(request,response);
	}
         


	}
	//添加电影，后台
	public void saveMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		      movieDao.save(movie);
	}
	//删除电影，后台
	public void delMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		        int id=Integer.parseInt(request.getParameter("movie_id"));
				movieDao.deleteByConditionId(id, "Movie_id");  
	}
	//查询所有电影，前台
	public void allfindMovies(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		   
		    List<Movie> movies = movieDao.findAll();
		    double s = 4.7d;
		    if(movies != null){
		    	for (Iterator iterator = movies.iterator(); iterator.hasNext();) {
					Movie movie = (Movie) iterator.next();
					movie.setAvgscore(s+1.0);
					s=++s;
				}
		    	 request.setAttribute("movies", movies);
		    }else{
		    	 request.setAttribute("movies", null);
		    }	
		    //精彩影评
		    List<MovieComment> list = movieCommentDao.findAll();
		    if(list != null){
		    	for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					MovieComment movieComment = (MovieComment) iterator.next();
					User_Info user = new User_Info();
					user = psifImpl.showOneByR(movieComment.getUser_id()+"", user);
					movieComment.setUser(user);
					Movie movie = new Movie();
					movie = psifImpl.showOneByR(movieComment.getMovie_id()+"", movie);
					movieComment.setMovie(movie);
				}
		    	 request.setAttribute("comment", list);
		    }else{
		    	 request.setAttribute("comment", null);
		    }
		    request.getRequestDispatcher("/wj/douban_movies.jsp").forward(request, response);
	}
	//更改电影信息，后台
	public void updateMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		 
		    movieDao.update(movie);
		    
	}
	
	
	//查找某一部电影
	public void findOneMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int movie_id=Integer.parseInt(request.getParameter("movie_id"));
	    System.out.println(movieDao.findByConditionId(movie_id, "movie_id"));
	    Movie movie = movieDao.findByConditionId(movie_id, "movie_id");
	    request.setAttribute("movie", movie);
	        //导演
	        DirectorDao directorDao=new DirectorDao();
	        Director director=directorDao.findByConditionId(movie.getDirector_id(), "director_id");
	        request.setAttribute("director", director);
	        //类型
	        Movie_typeDao typeDao=new Movie_typeDao();
	        Movie_type movie_type=typeDao.findByConditionId(movie.getType_id(), "type_id");
	        request.setAttribute("movie_type", movie_type);
	        //影评
	        MovieCommentDao movieCommentDao=new MovieCommentDao();
	        List<MovieComment> movieComments=movieCommentDao.findByCondition(movie_id, "movie_id");
	        if(movieComments != null){
	        	for (Iterator iterator = movieComments.iterator(); iterator.hasNext();) {
					MovieComment movieComment = (MovieComment) iterator.next();
					User_Info user = new User_Info();
					user = psifImpl.showOneByR(movieComment.getUser_id()+"", user);
					movieComment.setUser(user);
				}
	        	  request.setAttribute("movieComments", movieComments);
	        }else{
	        	  request.setAttribute("movieComments", null);
	        }
	        //短评
	        ShortCommentDao shortCommentDao=new ShortCommentDao();
	        List<ShortMovieComment> shortMovieComments=shortCommentDao.findByCondition(movie_id, "movie_id");
	        if(shortMovieComments != null){
	        	for (Iterator iterator = shortMovieComments.iterator(); iterator.hasNext();) {
					ShortMovieComment shortMovieComment = (ShortMovieComment) iterator.next();
					User_Info user = new User_Info();
					user = psifImpl.showOneByR(shortMovieComment.getUser_id()+"", user);
					shortMovieComment.setUser(user);
				}
	        	request.setAttribute("shortMovieComments", shortMovieComments);
	        }else{
	        	request.setAttribute("shortMovieComments", null);
	        }
	        //计算平均分 
	        movie.setAvgscore(rs.showOneMovieScore(movie.getMovie_id()+""));
	        //查看当前用户是否有观看状态打分，短评
	        HttpSession session = request.getSession();
			User_Info cuser = (User_Info) session.getAttribute("current_user");
			ShortMovieComment ss = rs.showMovieShortCommentOfAUserToAMovie(movie.getMovie_id()+"", cuser.getUser_id()+"");
	        if(ss != null){
	        	movie.setCurrent_user_movie_station(ss);
	        }else{
	        	movie.setCurrent_user_movie_station(null);
	        }
	        //查询该电影的所有观众（2个观看状态）
	        List<ShortMovieComment> listsss = rs.showAllMovieStationToOneMovie(movie.getMovie_id()+"");
	        if(listsss != null){
	        	for (Iterator iterator = listsss.iterator(); iterator.hasNext();) {
					ShortMovieComment shortMovieComment = (ShortMovieComment) iterator.next();
					User_Info user = new User_Info();
					user = psifImpl.showOneByR(shortMovieComment.getUser_id()+"", user);
					shortMovieComment.setUser(user);
				}
	        	request.setAttribute("peopleread", listsss);
	        }else{
	        	request.setAttribute("peopleread", null);
	        }

	        request.getRequestDispatcher("/wj/movie.jsp").forward(request, response);
	    
}
	//向页面传值，前台
	public void setPageObjects(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		 
		List<Object[]> objects=movieDao.getByTypeMovies();
		
		System.out.println(objects.size());
		for (Iterator iterator = objects.iterator(); iterator.hasNext();) {
			Object[] objects2 = (Object[]) iterator.next();
			for (int i = 0; i < objects2.length; i++) {
				System.out.println(objects2[i]);
			}
		}
		request.setAttribute("objects", objects);
		 request.getRequestDispatcher("/index.jsp").forward(request, response);
		    
	}
	//按时间排序
	public void getMovieJsonByDate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		    response.setCharacterEncoding("utf-8");
		    String jsonData=movieDao.getMoviesJson();
		    System.out.println(jsonData);
		    response.getWriter().print(jsonData);
		    
	}
	//按点击量排序
	public void getMovieJsonByCount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		    response.setCharacterEncoding("utf-8");
		    MovieRankingDao movieRankingDao=new MovieRankingDao();
		    String jsonDataByCount=movieRankingDao.getObjectsJson();
		    response.getWriter().print(jsonDataByCount);
	}
	public void getListJsonByCount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		    response.setCharacterEncoding("utf-8");
		    MovieRankingDao movieRankingDao=new MovieRankingDao();
		    List<Object[]> listObjects=movieRankingDao.getRankingByCountEntity(movie);
		    request.setAttribute("listObjects", listObjects);
		    request.getRequestDispatcher("/wj/MovieCharts.jsp").forward(request, response);
		    
	}
	//按评分排序
	public void getMovieJsonByAvgScore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		    response.setCharacterEncoding("utf-8");
		    MovieRankingDao movieRankingDao=new MovieRankingDao();
		    String jsonDataByCount=movieRankingDao.getObjectsJsonByAvgScore();
		    System.out.println(jsonDataByCount);
		    response.getWriter().print(jsonDataByCount);
		 
	}
	
	//按类型返回
	public void getMovieJsonType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		    response.setCharacterEncoding("utf-8");
		   int type_id=Integer.parseInt(request.getParameter("type_id"));
		   List<Movie> movies= movieDao.findByCondition(type_id, "type_id");
		    String jsonByType= JsonUtil.getJsonString4JavaPOJO(movies, "yyyy-MM-dd");
		    response.getWriter().print(jsonByType);
		    System.out.println(jsonByType);
		   
	}
	public void getMovieJsonDistrict(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		    response.setCharacterEncoding("utf-8");
		    String m_district=request.getParameter("m_district");
		    System.out.println(m_district);
		    List<Movie> movies=movieDao.getMovieByDistrict(m_district);
		    String jsonByDistrict=JsonUtil.getJsonString4JavaPOJO(movies,"yyyy-MM-dd");
		    response.getWriter().print(jsonByDistrict);
		    System.out.println(jsonByDistrict);
		    }
	
	//查看某个导演的所有作品
	public void showMovieByDirector(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		List<Movie> movies = movieDao.getMovieByDirector(request.getParameter("director_id"));
	    if(movies != null){
	    	for (Iterator iterator = movies.iterator(); iterator.hasNext();) {
				Movie movie = (Movie) iterator.next();
				Movie_type Movie_type = new Movie_type();
				Movie_type = psifImpl.showOneByR(movie.getType_id()+"", Movie_type);
				movie.setM_type(Movie_type);
			}
	        request.setAttribute("movies", movies);
	    }else{
	    	request.setAttribute("movies", null);
	    }
	    //查询该导演
	    Director d = new Director();
	    d = psifImpl.showOneByR(request.getParameter("director_id"), d);
	    request.setAttribute("director", d);
	    //查询所有导演
	    List<Director> dlist = rs.showAllDirectors();
	    if(dlist != null){
	    	 request.setAttribute("alldirector", dlist);
	    }else{
	    	request.setAttribute("alldirector", null);
	    }
	    request.getRequestDispatcher("/wj/kindofmovie.jsp").forward(request, response);
	}
}
