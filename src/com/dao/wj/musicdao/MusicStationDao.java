package com.dao.wj.musicdao;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dao.wj.basedao.GenericDaoImpl;
import com.entity.wj.music.MusicStation;
/**
 *MusicStationDao
 * @author 汪进
 * 想听的，听过的并打分
 */
public class MusicStationDao extends GenericDaoImpl<MusicStation, Integer> {

	@Override
	public MusicStation findByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByConditionId(id, condition);
	}

	@Override
	public List<MusicStation> findByCondition(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByCondition(id, condition);
	}

	@Override
	public List<MusicStation> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public void save(MusicStation entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}

	@Override
	public void update(MusicStation entity) {
		// TODO Auto-generated method stub
		super.update(entity);
	}

	@Override
	public void deleteByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		super.deleteByConditionId(id, condition);
	}

	@Override
	public Map getMapEntity(MusicStation entity, String condition) {
		// TODO Auto-generated method stub
		return super.getMapEntity(entity, condition);
	}

	@Override
	public List<MusicStation> findSessionCondtionAll(LinkedList<Object> object) {
		// TODO Auto-generated method stub
		return super.findSessionCondtionAll(object);
	}
		
}
