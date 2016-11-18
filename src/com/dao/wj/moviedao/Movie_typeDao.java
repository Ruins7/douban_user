package com.dao.wj.moviedao;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dao.wj.basedao.GenericDaoImpl;
import com.entity.wj.movie.Movie_type;
/**
 * Movie_typeDao
 * @author 汪进
 *对电影类型进行操作
 */
public class Movie_typeDao extends GenericDaoImpl<Movie_type, Integer> {
	@Override
	public Movie_type findByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByConditionId(id, condition);
	}

	@Override
	public List<Movie_type> findByCondition(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByCondition(id, condition);
	}

	@Override
	public List<Movie_type> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public void save(Movie_type entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}

	@Override
	public void update(Movie_type entity) {
		// TODO Auto-generated method stub
		super.update(entity);
	}

	@Override
	public void deleteByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		super.deleteByConditionId(id, condition);
	}

	@Override
	public List<Movie_type> findByIds(List<Integer> Ids) {
		// TODO Auto-generated method stub
		return super.findByIds(Ids);
	}


		
}
