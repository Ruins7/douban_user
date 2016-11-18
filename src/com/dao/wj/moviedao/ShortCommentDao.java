package com.dao.wj.moviedao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dao.lsr.dbutils.daoImpl.DaoOperationImpl;
import com.dao.wj.basedao.GenericDaoImpl;
import com.entity.wj.movie.ShortMovieComment;
/**
 * 短评、看过的评分
 * @author 汪进
 *
 */
public class ShortCommentDao extends GenericDaoImpl<ShortMovieComment, Integer> {

	DaoOperationImpl doimpl = new DaoOperationImpl();
	
	@Override
	public ShortMovieComment findByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByConditionId(id, condition);
	}

	@Override
	public List<ShortMovieComment> findByCondition(Serializable id,
			String condition) {
		// TODO Auto-generated method stub
		return super.findByCondition(id, condition);
	}

	@Override
	public List<ShortMovieComment> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public void save(ShortMovieComment entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}

	@Override
	public void update(ShortMovieComment entity) {
		// TODO Auto-generated method stub
		super.update(entity);
	}

	@Override
	public void deleteByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		super.deleteByConditionId(id, condition);
	}

	@Override
	public Map getMapEntity(ShortMovieComment entity, String condition) {
		// TODO Auto-generated method stub
		return super.getMapEntity(entity, condition);
	}
	
	
	
	
			
}
