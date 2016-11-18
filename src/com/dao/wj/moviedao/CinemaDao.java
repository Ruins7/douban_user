package com.dao.wj.moviedao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dao.wj.basedao.GenericDaoImpl;
import com.entity.wj.movie.Cinema;
/**
 * CinemaDao
 * @author 汪进
 *
 */
public class CinemaDao extends GenericDaoImpl<Cinema, Integer> {
   //按cinema_id查询并返回Cinema
	QueryRunner qr=new QueryRunner();
	@Override
	public Cinema findByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByConditionId(id, condition);
	}
	   //按cinema_id查询并返回List<Cinema>
	@Override
	public List<Cinema> findByCondition(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByCondition(id, condition);
	}
    //查询所有Cinema数据
	@Override
	public List<Cinema> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
    //保存Cinema
	@Override
	public void save(Cinema entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}
    //更新
	@Override
	public void update(Cinema entity) {
		// TODO Auto-generated method stub
		super.update(entity);
	}
   //删除
	@Override
	public void deleteByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		super.deleteByConditionId(id, condition);
	}

	//批量查询
	@Override
	public List<Cinema> findByIds(List<Integer> Ids) {
		// TODO Auto-generated method stub
		return super.findByIds(Ids);
	}
   //批量删除
	@Override
	public void delByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		super.delByIds(ids);
	}
    //返回map，用于影院查询
	@Override
	public Map getMapEntity(Cinema entity, String condition) {
		// TODO Auto-generated method stub
		return super.getMapEntity(entity, condition);
	}

    public List<Cinema> getCinemaByCity(String city){
    	List<Cinema> cinemas=null;
    	String sql="SELECT*FROM cinema  WHERE city=?";
    	try {
    		cinemas=qr.query(getConn(), sql, new BeanListHandler<Cinema>(Cinema.class), city);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return cinemas;
    }
		
}
