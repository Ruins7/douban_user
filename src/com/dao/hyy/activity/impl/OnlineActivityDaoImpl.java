package com.dao.hyy.activity.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.lang.ArrayUtils;

import com.dao.hyy.activity.OnlineActivityDao;
import com.dao.hyy.user.impl.UserDaoImpl;
import com.dao.lsr.pool.PoolService;
import com.entity.hyy.activity.ActivityAlbum;
import com.entity.hyy.activity.OnlineActivity;
import com.entity.lsr.user.User_Info;


/**
 * 线上活动的dao层实现类
 * @author 胡伊杨
 *
 */
public class OnlineActivityDaoImpl implements OnlineActivityDao{
 
	Connection conn = null;
	QueryRunner qr = null;
	
	public OnlineActivityDaoImpl() throws SQLException{
		 
		PoolService pool = PoolService.getInstance();
		conn = pool.getConnection();
		qr = new QueryRunner();
	}	
	
	@Override
	/**
	 * 添加线上活动
	 * @param oa 线上活动实体
	 * @return 操作结果
	 */
	public int addOnlinActivity(OnlineActivity oa) {
		String sql = "insert into onlineActivity(onlineActivity_title,start_time,end_time,onlineactivity_desc,user_id,onlineActivity_type,album_id,onlineactivity_statue) values(?,?,?,?,?,?,?,?)";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("----------------"+oa);
		System.out.println();
		Object[] params={oa.getOnlineActivity_title(),oa.getStart_time(),oa.getEnd_time(),oa.getOnlineactivity_desc(),1,oa.getOnlineActivity_type(),1,"δ���"};
		System.out.println(ArrayUtils.toString(params));
		try {
			int affectRows = qr.update(conn, sql, params);
			System.out.println(affectRows+"行记录受影响");
			return affectRows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	/**
	 * 删除线上活动
	 * @param activity_id 活动的编号
	 * @return 操作结果
	 */
	public int deleteOnlineActivity(int activity_id) {
		String sql ="delete from onlineActivity where onlineActivity_id=?";
		try {
			int affectRows = qr.update(conn, sql, activity_id);
			System.out.println(affectRows+"行数据受影响");
			return affectRows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	/**
	 * 查找所有的线上活动
	 * @return 线上活动的集合
	 */
	public List<OnlineActivity> searcAllhActivity() {
		String sql ="select * from onlineActivity";
		List<OnlineActivity> list = new ArrayList<OnlineActivity>();
		try {
			List<Object[]> objects = qr.query(conn, sql, new ArrayListHandler());
			for (int i = 0; i < objects.size(); i++) {
				Object[] obj = objects.get(i);
				OnlineActivity oa = searchAOnActivity(Integer.parseInt(obj[0].toString()));
				list.add(oa);				
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * 查找单个线上活动的具体信息
	 * @param id 要查找的id号
	 * @return 查到的单个线上活动
	 */
	public OnlineActivity searchAOnActivity(int id) {
		String sql = "select * from onlineActivity where onlineActivity_id=?";
		try {
			Object[] obj = qr.query(conn, sql, new ArrayHandler(), id);			
			UserDaoImpl udi = new UserDaoImpl();
			User_Info user = udi.getUser(Integer.parseInt(obj[8].toString()));
			
			ActivityAlbum aa = new ActivityAlbum();
			aa.setActivityAlbum_id(1);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			OnlineActivity oa = new OnlineActivity(Integer.parseInt(obj[0].toString()),obj[1].toString(),sdf.parse(obj[2].toString()),sdf.parse(obj[3].toString()),obj[4].toString(),obj[5].toString(),aa,obj[7].toString(),user);
			
			return oa;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * 修改单个线上活动的信息
	 * @param id 活动的id
	 * @return 是否修改成功
	 */
	public int updateOnActivity(OnlineActivity oa) {
		String sql ="update onlineActivity set onlineActivity_title=?,start_time=?,end_time=?,onlineActivity_desc=?,onlineActivity_type=?,onlineactivity_statue=? where onlineActivity_id=?";
		try {
			int affectRows = qr.update(conn, sql, oa.getOnlineActivity_title(),oa.getStart_time(),oa.getEnd_time(),oa.getOnlineactivity_desc(),oa.getOnlineActivity_type(),oa.getOnlineactivity_statue(),oa.getOnlineActivity_id());
			return affectRows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
