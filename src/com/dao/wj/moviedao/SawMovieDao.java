package com.dao.wj.moviedao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dao.wj.basedao.GenericDaoImpl;
import com.entity.wj.movie.SawMovie;
/**
 * 想看的电影  SawMovieDao
 * @author 汪进
 *
 */
public class SawMovieDao extends GenericDaoImpl<SawMovie, Integer> {

	@Override
	public SawMovie findByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByConditionId(id, condition);
	}

	//查询所有SawMovie
	@Override
	public List<SawMovie> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
    //保存SawMovie
	@Override
	public void save(SawMovie entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}
    //更新SawMovie
	@Override
	public void update(SawMovie entity) {
		// TODO Auto-generated method stub
		super.update(entity);
	}
    //删除
	@Override
	public void deleteByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		super.deleteByConditionId(id, condition);
	}


		
}
