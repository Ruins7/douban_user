package com.dao.wj.moviedao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dao.wj.basedao.GenericDaoImpl;
import com.entity.wj.movie.WantSeeMovie;
/**
 * WantSeeDao
 * @author 汪进
 *想看的，不评分
 */
public class WantSeeDao extends GenericDaoImpl<WantSeeMovie, Integer> {

	@Override
	public WantSeeMovie findByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByConditionId(id, condition);
	}

	@Override
	public List<WantSeeMovie> findByCondition(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByCondition(id, condition);
	}

	@Override
	public List<WantSeeMovie> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public void save(WantSeeMovie entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}

	@Override
	public void update(WantSeeMovie entity) {
		// TODO Auto-generated method stub
		super.update(entity);
	}

	@Override
	public void deleteByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		super.deleteByConditionId(id, condition);
	}

	@Override
	public Map getMapEntity(WantSeeMovie entity, String condition) {
		// TODO Auto-generated method stub
		return super.getMapEntity(entity, condition);
	}
		
}
