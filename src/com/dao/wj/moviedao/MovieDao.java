package com.dao.wj.moviedao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dao.lsr.dbutils.daoImpl.DaoOperationImpl;
import com.dao.wj.basedao.GenericDaoImpl;
import com.dao.wj.utildao.JsonUtil;
import com.entity.wj.movie.Movie;
import com.entity.wj.movie.ShortMovieComment;

/**
 * MovieDao
 * @author 汪进
 *
 */
public class MovieDao extends GenericDaoImpl<Movie, Integer> {
      
	 QueryRunner qr=new QueryRunner();
       
       DaoOperationImpl doimpl = new DaoOperationImpl();
	@Override
	public Movie findByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByConditionId(id, condition);
	}

	@Override
	public List<Movie> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public void save(Movie entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}

	@Override
	public void update(Movie entity) {
		// TODO Auto-generated method stub
		super.update(entity);
	}

	@Override
	public void deleteByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		super.deleteByConditionId(id, condition);
	}

	@Override
	public Map getMapEntity(Movie entity, String condition) {
		// TODO Auto-generated method stub
		return super.getMapEntity(entity, condition);
	}
	@Override
	public List<Movie> findByCondition(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByCondition(id, condition);
	}
	
	/**
	 * 根据类型查询Movie
	 * @return List<Object[]>
	 */
	public List<Object[]> getByTypeMovies() {
		// TODO Auto-generated method stub
		List<Object[]> objects=null;
		String sql="SELECT*FROM movie RIGHT JOIN movie_type ON movie.type=movie_type.type";
	  try {
		  objects=qr.query(getConn(), sql, new ArrayListHandler());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  System.out.println(sql);
		return objects;
	}
	
	/**
	 * 根据导演查询Movie
	 * @return List<Object[]>
	 */
	public List<Object[]> getByDirectorMovies(){
		List<Object[]> objects=null;
		String sql="SELECT*FROM movie RIGHT JOIN director ON movie.director_id=director.director_id";
		
		try {
			objects=qr.query(getConn(), sql, new ArrayListHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objects;
	}
	
	public List<Movie> getMoviesSortByDate(){
		List<Movie> movies=new ArrayList<Movie>();
		String sql="SELECT * FROM movie ORDER BY m_screentime DESC";
		try {
			movies=qr.query(getConn(), sql, new BeanListHandler<Movie>(Movie.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}
	
	public String getMoviesJson() {
		List<Movie> movies = getMoviesSortByDate();
		return JsonUtil.getJsonString4JavaPOJO(movies, "yyyy-MM-dd");
	}


	public List<Movie> getMovieByDistrict(String m_district){
		List<Movie> movies=null;
		String sql="SELECT * FROM movie WHERE m_district=?";
		System.out.println(sql);
		try {
			movies=qr.query(getConn(), sql, new BeanListHandler<Movie>(Movie.class),m_district);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}
	
	//查看某一导员的所有电影
	public List<Movie> getMovieByDirector(String director_id){ 
		String sql="SELECT * FROM movie WHERE director_id = ?"; 
		 Object[] params = {director_id};
		 List<Movie> list = doimpl.qrQueryForList(sql, new Movie(), params);
		return list;
	}
	
	//查看某人的所有看过的电影
		public List<ShortMovieComment> getUserAllSawMovies(String user_id){ 
			String sql="SELECT * FROM shortmoviecomment WHERE user_id = ? and station = '已看'"; 
			 Object[] params = {user_id};
			 List<ShortMovieComment> list = doimpl.qrQueryForList(sql, new ShortMovieComment(), params);
			return list;
		}

	//查看某人的所有想看的电影
		public List<ShortMovieComment> getUserAllWantSawMovies(String user_id){ 
			String sql="SELECT * FROM shortmoviecomment WHERE user_id = ? and station = '想看'"; 
			Object[] params = {user_id};
			List<ShortMovieComment> list = doimpl.qrQueryForList(sql, new ShortMovieComment(), params);
			return list;
		}

	
}
