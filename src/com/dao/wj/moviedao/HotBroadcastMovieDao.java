package com.dao.wj.moviedao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dao.wj.basedao.GenericDaoImpl;
import com.dao.wj.dbuitil.DaoSupport;
import com.entity.wj.movie.HotBroadcast;
import com.entity.wj.movie.Movie;

/**
 * 热播电影
 * @author 汪进
 *
 */
public class HotBroadcastMovieDao extends GenericDaoImpl<HotBroadcast, Integer> {
	QueryRunner qr=new QueryRunner();
	DaoSupport daoSupport=new DaoSupport();
	@Override
	public HotBroadcast findByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByConditionId(id, condition);
	}

	@Override
	public List<HotBroadcast> findByCondition(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByCondition(id, condition);
	}

	@Override
	public List<HotBroadcast> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public void save(HotBroadcast entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}

	@Override
	public void update(HotBroadcast entity) {
		// TODO Auto-generated method stub
		super.update(entity);
	}

	@Override
	public void deleteByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		super.deleteByConditionId(id, condition);
	}
    //查询热播电影信息
    public List<Movie> hotBroadcasting(){
    	List<Movie> movies=null;
		String sql="select*from movie,hotbroadcast where hotbroadcast.movie_id=movie.movie_id;";
		try {
			movies=qr.query(getConn(), sql,new BeanListHandler<Movie>(Movie.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return movies;
    }
}
