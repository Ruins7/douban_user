package com.dao.wj.musicdao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dao.wj.basedao.GenericDaoImpl;
import com.entity.wj.music.MusicComment;
/**
 * MusicCommentDao
 * @author 汪进
 *音评
 */
public class MusicCommentDao extends GenericDaoImpl<MusicComment, Integer> {

	@Override
	public MusicComment findByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByConditionId(id, condition);
	}

	@Override
	public List<MusicComment> findByCondition(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByCondition(id, condition);
	}

	@Override
	public List<MusicComment> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public void save(MusicComment entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}

	@Override
	public void update(MusicComment entity) {
		// TODO Auto-generated method stub
		super.update(entity);
	}

	@Override
	public void deleteByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		super.deleteByConditionId(id, condition);
	}

	@Override
	public Map getMapEntity(MusicComment entity, String condition) {
		// TODO Auto-generated method stub
		return super.getMapEntity(entity, condition);
	}
	
	

}
