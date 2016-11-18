package com.dao.wj.moviedao;

import java.util.List;

import com.dao.lsr.dbutils.daoImpl.DaoOperationImpl;
import com.entity.lsr.book.BookStation;
import com.entity.wj.movie.Director;
import com.entity.wj.movie.ShortMovieComment;
/**
 * 短评、看过的评分
 * @author 汪进
 *
 */
public class RuinsShortCommentDao {

	DaoOperationImpl doimpl = new DaoOperationImpl();
	
	
	public int addShortComment(ShortMovieComment entity) {
		// 添加短评
		String sql = "insert into ShortMovieComment (movie_id,user_id,date,short_c_masg,score,station) values(?,?,?,?,?,?)";
		Object[] params = {entity.getMovie_id(),entity.getUser_id(),entity.getDate(),entity.getShort_c_masg(),entity.getScore(),entity.getStation()};
		int result = doimpl.qrUpdate(sql, params);
		return result;
	}
	
	public Double showOneMovieScore(String movie_id) {
		// 查询某影片的评分
		String sql = "select avg(score) from shortmoviecomment where movie_id = ?";
		Object[] params = {movie_id};
		List<Object[]> list = doimpl.qrQueryForResultSet(sql, params);
		return (Double) list.get(0)[0];
	}
	
	public ShortMovieComment showMovieShortCommentOfAUserToAMovie(String mid, String uid) {
		// 查询当前用户是否对某影片打分以及选择观看状态
		String sql = "select * from shortmoviecomment where movie_id = ? and user_id = ?";
		Object[] params = {mid,uid};
		ShortMovieComment re = doimpl.qrQueryForOne(sql, new ShortMovieComment(), params);
		return re;
	
	}
	
	public List<ShortMovieComment> showAllMovieStationToOneMovie(String movieid) {
		// 查询看某电影的所有用户（2种观看状态）
		String sql = "select * from shortmoviecomment where movie_id = ?" ;
		Object[] params = {movieid};
		List<ShortMovieComment> result = doimpl.qrQueryForList(sql, new ShortMovieComment(), params);
		return result;
		
	}
	
	public List<Director> showAllDirectors() {
		// 查询所有导演
		String sql = "select * from director" ;	 
		List<Director> result = doimpl.qrQueryForList(sql, Director.class);
		return result;
		
	}
			
}
