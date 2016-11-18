package com.dao.wj.moviedao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;













import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import com.dao.wj.commondao.GenericCommonDaoImpl;
import com.dao.wj.utildao.DateJsonValueProcessor;
import com.dao.wj.utildao.JsonUtil;
import com.entity.wj.movie.Movie;

public class MovieRankingDao extends GenericCommonDaoImpl<Movie, Integer> {
    QueryRunner qr=new QueryRunner();
	@Override
	public List<Object[]> getRankingByCountEntity(Movie entity) {
		// TODO Auto-generated method stub
		return super.getRankingByCountEntity(entity);
	}

	@Override
	public List<Object[]> getRankingByScoreEntity(Movie entity) {
		// TODO Auto-generated method stub
		return super.getRankingByScoreEntity(entity);
	}
	
	public List<Object[]> getRankingByAvgScoreMovies(){
		List<Object[]> objects1=null;
		Object[] params ={0,10};
		String sql="SELECT movie.movie_id,m_name, AVG(shortmoviecomment.score),COUNT(shortmoviecomment.movie_id)FROM shortmoviecomment,movie WHERE shortmoviecomment.movie_id=movie.movie_id GROUP BY movie.movie_id ORDER BY AVG(shortmoviecomment.score)DESC";
		try {
			objects1=qr.query(getConn(), sql, new ArrayListHandler(),params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objects1;
	}
	
	
	public String getObjectsJson() {
		Movie movie=new Movie();
		List<Object[]> objects = getRankingByCountEntity(movie);
		 JsonConfig jsonConfig = new JsonConfig();
		  
		  jsonConfig.setIgnoreDefaultExcludes(false);
		  
		  jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		  
		  jsonConfig.registerJsonValueProcessor(Date.class,
		  
		  new DateJsonValueProcessor("yyyy-MM-dd"));
		  JSONArray json = (JSONArray) JSONSerializer.toJSON(objects, jsonConfig);
		  return json.toString();
		
	}
	
	public String getObjectsJsonByAvgScore() {
		List<Object[]> objects1 = getRankingByAvgScoreMovies();
		 JsonConfig jsonConfig = new JsonConfig();
		  
		  jsonConfig.setIgnoreDefaultExcludes(false);
		  
		  jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		  
		  jsonConfig.registerJsonValueProcessor(Date.class,
		  
		  new DateJsonValueProcessor("yyyy-MM-dd"));
		  JSONArray json = (JSONArray) JSONSerializer.toJSON(objects1, jsonConfig);
		  return json.toString();
		
	}

      
}
