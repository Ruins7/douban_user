package com.dao.wj.bookdao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dao.wj.basedao.GenericDaoImpl;
import com.entity.lsr.book.BookStation;
/**
 * 想读(不需评分)、正在读、读过(并打分)
 * @author 汪进
 *
 */
public class BookStationDao extends GenericDaoImpl<BookStation, Integer> {
	//按id查询BookStation，并返回BookStation
	 QueryRunner qr=new QueryRunner();
	@Override
	
	public BookStation findByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByConditionId(id, condition);
	}
	
	//按id查询BookStation，并返回List<BookStation>
	@Override
	public List<BookStation> findByCondition(Serializable id, String condition) {
		// TODO Auto-generated method stub
		return super.findByCondition(id, condition);
	}
   //查询BookStation所有数据
	@Override
	public List<BookStation> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
    //保存
	@Override
	public void save(BookStation entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}
    //更新
	@Override
	public void update(BookStation entity) {
		// TODO Auto-generated method stub
		super.update(entity);
	}
   //删除
	@Override
	public void deleteByConditionId(Serializable id, String condition) {
		// TODO Auto-generated method stub
		super.deleteByConditionId(id, condition);
	}

	@Override
	public Map getMapEntity(BookStation entity, String condition) {
		// TODO Auto-generated method stub
		return super.getMapEntity(entity, condition);
	}
   public List<BookStation> getByStation(String station,int user_id){
	   List<BookStation> bookStations=null;
	   String sql="SELECT*from bookstation where station like ? and user_id=?";
	   Object[] params ={station,user_id};
	   try {
		    bookStations=qr.query(getConn(), sql, new BeanListHandler<BookStation>(BookStation.class),params);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return bookStations;
   }
	

}
