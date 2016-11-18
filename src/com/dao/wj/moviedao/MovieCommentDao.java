package com.dao.wj.moviedao;

import java.io.Serializable;
import java.util.List;

import com.dao.wj.basedao.GenericDaoImpl;
import com.entity.wj.movie.MovieComment;
/**
 * 影评
 * @author 汪进
 *
 */
public class MovieCommentDao extends GenericDaoImpl<MovieComment, Integer> {

	@Override
	public MovieComment findByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByConditionId(id, condition);
	}

	@Override
	public List<MovieComment> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public void save(MovieComment entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}

	@Override
	public void update(MovieComment entity) {
		// TODO Auto-generated method stub
		super.update(entity);
	}

	@Override
	public void deleteByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		super.deleteByConditionId(id, condition);
	}

	@Override
	public List<MovieComment> findByCondition(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByCondition(id, condition);
	}
	
		
}
