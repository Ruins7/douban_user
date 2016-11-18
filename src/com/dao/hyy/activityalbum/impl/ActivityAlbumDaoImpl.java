package com.dao.hyy.activityalbum.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dao.hyy.activityalbum.ActivityAlbumDao;
import com.dao.lsr.dbutils.daoImpl.DaoOperationImpl;
import com.dao.lsr.pool.PoolService;
import com.entity.hyy.activity.ActivityAlbum;
import com.entity.hyy.activity.ActivityPhoto;
/**
 * 活动相册dao层的实现类
 * @author 胡伊杨
 *
 */
public class ActivityAlbumDaoImpl implements ActivityAlbumDao{

 
	PoolService pool = PoolService.getInstance();
	QueryRunner qr = new QueryRunner();
	DaoOperationImpl doimpl = new DaoOperationImpl();
	
	@Override
	/**
	 * 获得活动的相册
	 * @param id相册的id
	 * @return 相册
	 */
	public ActivityAlbum getActivityAlbum(int id) {
		Connection conn = pool.getConnection();
//		String sql_album = "select * from offActivity where offActivity_id=?";
		String sql ="select * from activityPhoto where activityAlbum_id=?";
		try {
			System.out.println(id);
			List<ActivityPhoto> list = qr.query(conn, sql, new BeanListHandler(ActivityPhoto.class), id);
			System.out.println("size的值是:"+list.size());
			ActivityAlbum aa = new ActivityAlbum();
			aa.setActivityAlbum_id(id);
			aa.setActivityPhotos(list);
			pool.releaseConnection(conn);
			return aa;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<ActivityPhoto> showActivityPhoto(int activity_album_id) {
		 String sql = "select * from activityphoto where activityAlbum_id = ?";
		 Object[] params = {activity_album_id};
		 ActivityPhoto ap = new ActivityPhoto();
		 List<ActivityPhoto> list = doimpl.qrQueryForList(sql, ap, params);
		 return list;
	}

}
