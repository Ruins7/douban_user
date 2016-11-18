package com.dao.wj.musicdao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dao.wj.basedao.GenericDaoImpl;
import com.entity.wj.music.ListenMusic;
/**
 * 暂留
 * @author 汪进
 *
 */
public class ListenMusicDao extends GenericDaoImpl<ListenMusic, Integer> {

	@Override
	public ListenMusic findByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByConditionId(id, condition);
	}

	@Override
	public List<ListenMusic> findByCondition(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByCondition(id, condition);
	}

	@Override
	public List<ListenMusic> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public void save(ListenMusic entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}

	@Override
	public void update(ListenMusic entity) {
		// TODO Auto-generated method stub
		super.update(entity);
	}

	@Override
	public void deleteByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		super.deleteByConditionId(id, condition);
	}

	@Override
	public Map getMapEntity(ListenMusic entity, String condition) {
		// TODO Auto-generated method stub
		return super.getMapEntity(entity, condition);
	}
		
}
