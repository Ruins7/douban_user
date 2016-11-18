package com.dao.wj.moviedao;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dao.wj.basedao.GenericDaoImpl;
import com.entity.wj.movie.Director;
/**
 * DirectorDao
 * @author 汪进
 *
 */
public class DirectorDao extends GenericDaoImpl<Director, Integer> {
	//按id查询，并返回Director
	@Override
	public Director findByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByConditionId(id, condition);
	}
    //按id查询，并返回List<Director>
	@Override
	public List<Director> findByCondition(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByCondition(id, condition);
	}
    //查询所有
	@Override
	public List<Director> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
    //保存
	@Override
	public void save(Director entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}
    //更新
	@Override
	public void update(Director entity) {
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
