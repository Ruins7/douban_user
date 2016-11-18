package com.dao.hyy.activity.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JsonConfig;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.lang.ArrayUtils;

import com.dao.hyy.activity.OffActivityDao;
import com.dao.hyy.activity.ticket.TicketDao;
import com.dao.hyy.activity.ticket.impl.TicketDaoImpl;
import com.dao.hyy.activityalbum.ActivityAlbumDao;
import com.dao.hyy.activityalbum.impl.ActivityAlbumDaoImpl;
import com.dao.hyy.attentionThings.AttentionThingsDao;
import com.dao.hyy.attentionThings.impl.AttentionThingsDaoImpl;
import com.dao.hyy.city.CityDao;
import com.dao.hyy.city.impl.CityDaoImpl;
import com.dao.hyy.user.UserDao;
import com.dao.hyy.user.impl.UserDaoImpl;
import com.dao.lsr.dbutils.dao.DaoOperation;
import com.dao.lsr.dbutils.daoImpl.DaoOperationImpl;
import com.dao.lsr.pool.PoolService;
import com.entity.hyy.activity.ActivityAlbum;
import com.entity.hyy.activity.Activity_type;
import com.entity.hyy.activity.OffActivity;
import com.entity.hyy.activity.OfflineAskActivity;
import com.entity.hyy.activity.Ticket;
import com.entity.hyy.city.City;
import com.entity.lsr.user.User_Info;

/**
 * 线下活动dao层实现类
 * @author 胡伊杨
 *
 */

public class OffActivityDaoImpl implements OffActivityDao{
	
	private static final String URL = "jdbc:mysql://localhost:3306/douban_db";
	PoolService pool = PoolService.getInstance();
	QueryRunner qr = new QueryRunner();
	DaoOperation doimpl = new DaoOperationImpl();
	
	
	@Override
	/**
	 * 查找所有活动类型   ----R
	 * @return 
	 */
	public List<Activity_type> getAllType() {
		String sql = "select * from activity_type";   
		List<Activity_type> list = doimpl.qrQueryForList(sql, Activity_type.class);
		return list;
	}
	
	@Override
	/**
	 * 根据活动类型查找线下活动   ----R
	 * @return 
	 */
	public Map<Activity_type,List<OffActivity>> getOffActivityByAllType(List<Activity_type> list) {
		Map<Activity_type,List<OffActivity>> map = new HashMap<Activity_type,List<OffActivity>>();	
		String sql = "select * from offactivity where offactivity_type = ?";
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Activity_type activity_type = (Activity_type) iterator.next();
			Object[] params = {activity_type.getType_id()};
			List<OffActivity> off_list = doimpl.qrQueryForList(sql, new OffActivity(), params);
			map.put(activity_type, off_list);
		}
		return map;
	}
	
	@Override
	/**
	 * 根据活动类型查找该类别的线下活动   ----R
	 * @return 
	 */
	public List<OffActivity> getOffActivityByOneType(String type_id) {
		String sql = "select * from offactivity where offactivity_type = ?";
		Object[] params = {type_id};
		List<OffActivity> list = doimpl.qrQueryForList(sql, new OffActivity(), params);
		return list;
	}
	@Override
	/**
	 * 查找单个线下活动   ----R
	 */
	public OffActivity getActivity(String act_id){
	      String sql = "select * from offactivity where offactivity_id = ?";
	      Object[] params = {act_id};
	      OffActivity off = doimpl.qrQueryForOne(sql, new OffActivity(), params);
		return off;      
	}
	@Override
	/**
	 * 查找单个线下活动的用户交流   ----R
	 */
	public List<OfflineAskActivity> getActivityAsk(String act_id){
	      String sql = "select * from offlineaskactivity where activity_id = ? order by time DESC";
	      Object[] params = {act_id};
	      List<OfflineAskActivity> offask = doimpl.qrQueryForList(sql, new OfflineAskActivity(), params);
		return offask;      
	}
	
	
	
	@Override
	/**
	 * 查找参加同一线下活动的用户 -----R
	 */
	public List<Object[]> getSameAttendUsersArray(String off_id) {
		String sql = "SELECT DISTINCT user_id FROM offattend where offActivity_id = ?";
		Object[] params = {off_id};
		List<Object[]> list = doimpl.qrQueryForResultSet(sql, params);
		return list;
	}

	@Override
	/**
	 * 我要参加活动	----R
	 */
	public int attendActivity(String offActivity_id,String user_id) {
	 String sql = "insert into offattend(offActivity_id,user_id) values(?,?)";
	 Object[] params = {offActivity_id, user_id};
	 int result = doimpl.qrUpdate(sql, params);
	 return result;
	}
	
	@Override
	/**
	 * 取消参加活动	----R
	 */
	public int cancelAttendActivity(String offActivity_id,String user_id) {
	 String sql = "delete from offattend where offActivity_id = ? and user_id = ?";
	 Object[] params = {offActivity_id, user_id};
	 int result = doimpl.qrUpdate(sql, params);
	 return result;
	}
	
	
	@Override
	/**
	 * 线下活动用户讨论-----R
	 */
	public int insertaskActivity(OfflineAskActivity oaa) {
		 String sql = "insert into offlineaskactivity(user_from,ask_comment,time,activity_id) values(?,?,?,?)";
		 Object[] params = {oaa.getUser_from(),oaa.getAsk_comment(),oaa.getTime(),oaa.getActivity_id()};
		 int result = doimpl.qrUpdate(sql, params);
		 return result;
	}
	
	@Override
	/**
	 * 某用户参与的所有线下活动-----R
	 */
	public List<OffActivity> OneUserAttendAllOffActivity(String user_id) {
		 String sql = "select * from offactivity where user_id = ?";
		 Object[] params = {user_id};
		 List<OffActivity> off = doimpl.qrQueryForList(sql, new OffActivity(), params);
		 return off;
	}
	
	/**
	 * 获得参加同一线下活动的用户
	 * @param offActivity_id 线下活动的编号
	 * @return user集合
	 */
	public List<User_Info> getActivityUsers(int offActivity_id){
		Connection conn = pool.getConnection();
/*		System.out.println(offActivity_id);*/
		String sql = "SELECT user_id FROM offattend WHERE offactivity_id=?";
		List<User_Info> users = new ArrayList<User_Info>();
		try {
			List<Integer> saus = (List<Integer>)qr.query(conn, sql,new ColumnListHandler<Integer>("user_id"),offActivity_id);
			for (Integer integer : saus) {
				users.add(getUser(new Object[]{integer}));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.releaseConnection(conn);
		return users;
	}
	
	/**
	 * 获得用户
	 * @param params 用户id
	 * @return
	 */
	public User_Info getUser(Object[] params){
		Connection conn = pool.getConnection();
		String sql = "SELECT * FROM USER_info WHERE user_id=?";
		try {
			User_Info user = qr.query(conn, sql,new BeanHandler(User_Info.class), params);
//			System.out.println("user_id:"+user.getUser_id());
			pool.releaseConnection(conn);
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

	
	
	/**
	 * 获得所有线下活动的编号
	 * @return 所有线下活动编号的集合
	 */
	public List<OffActivity> getSameCityOffActivity(String city_id){
		String sql = "select * from offActivity where city_id = ? order by start_time DESC";
		Object[] params = {city_id};
		List<OffActivity> list = doimpl.qrQueryForList(sql, new OffActivity(), params);	
		return list;
	}
	
	

	@Override
	/**
	 * 根据id查找线下活动
	 * @param activity_id 活动的id
	 * @return 查找的线下活动
	 */
	public OffActivity findActivity(int activity_id) {
		Connection conn = pool.getConnection();
		String sql = "select * from offactivity where offactivity_id=?";
		try {
			OffActivity oa = qr.query(conn, sql, new BeanHandler(OffActivity.class), activity_id);
			Object[] object = qr.query(conn, sql, new ArrayHandler(), activity_id);
			OffActivity offActivity = new OffActivity();
			UserDaoImpl ud = new UserDaoImpl();
			System.out.println(object[5].toString());
			oa.setUser(ud.getUser(Integer.parseInt(object[6].toString())));
			pool.releaseConnection(conn);
			return oa;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * 获得所有的用户想发起者的提问
	 * @param activity_id 活动的id
	 * @return 问题的list
	 */
	public List<OfflineAskActivity> getAllContent(int activity_id) {
		Connection conn = pool.getConnection();
		String sql ="select * from offlineaskActivity where activity_id=?";
		UserDaoImpl ud;
		try {
			ud = new UserDaoImpl();
			System.out.println("查找所有提问");
			List<OfflineAskActivity> oaas = new ArrayList<OfflineAskActivity>();
			List<Object[]> objs = qr.query(conn, sql, new ArrayListHandler(), activity_id);
			for (Iterator iterator = objs.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				System.out.println(Integer.parseInt(objects[0].toString()));
				System.out.println(ud.getUser(Integer.parseInt(objects[1].toString())));
				System.out.println(objects[3].toString());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println(findActivity(activity_id));
				OfflineAskActivity oaa = new OfflineAskActivity(Integer.parseInt(objects[0].toString()),ud.getUser(Integer.parseInt(objects[1].toString())),objects[3].toString(),sdf.parse(objects[4].toString()),findActivity(activity_id));
				System.out.println("编号是:"+oaa.getAsk_id());
				oaas.add(oaa);
			}			
			List<User_Info> user_from_list = new ArrayList<User_Info>();
			List<User_Info> user_to_list = new ArrayList<User_Info>();
			List<Integer> user_from = qr.query(conn , sql, new ColumnListHandler<Integer>("user_from") , activity_id);
			List<Integer> user_to = qr.query(conn , sql, new ColumnListHandler<Integer>("user_to") , activity_id);
		/*	for (int i = 0; i < oaas.size(); i++) {
				System.out.println("-------------user_from"+user_from.get(i).getClass());
				int userFrom = user_from.get(i);
				User_Info userfrom = ud.getUser(userFrom);
				oaas.get(i).setUser_from(ud.getUser(userFrom));
				
			}
			pool.releaseConnection(conn);
			return oaas;*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * 增加线下活动
	 * @param oa 线下活动对象
	 * @return 操作的结果
	 */
	public int addOfflineActivity(OffActivity oa) {
		String sql = "insert into offActivity(offactivity_title,start_time,end_time,city_id,offactivity_desc,user_id,offActivity_type,position,imgs) values(?,?,?,?,?,?,?,?,?)";
		Object[] params={oa.getOffactivity_title(),oa.getStart_time(),oa.getEnd_time(),oa.getCity_id(),oa.getOffactivity_desc(),oa.getUser_id(),oa.getOffactivity_type(),oa.getPosition(),oa.getImgs()};;
		int result = doimpl.qrUpdate(sql, params);
		return result;
	}

	@Override
	/**
	 * 获得所有的线下活动
	 * @params 查找的条件
	 * @return 线下活动的集合
	 */
	public List<OffActivity> getAllOffActivity(City city,String bigType,String smallType) {
		Connection conn = pool.getConnection();
		String sql;
		Object[] params ;
		if(null==smallType||"".equals(smallType)||"hot".equals(smallType)){
			sql = "select * from offactivity where offactivity_type=? and city_id=? and offActivity_statue='通过审核' AND end_time>NOW() order by rand() limit 0,4;";
			params=new Object[]{bigType,city.getCity_id()};
		}else{
			sql ="select * from offactivity where offactivity_type=? and city_id=? and offActivity_statue='通过审核' AND end_time>NOW() order by rand() limit 0,4;";
			params = new Object[]{smallType,city.getCity_id()};
			System.out.println(params);
		}
		System.out.println("========"+sql);
		try {
			List<Object[]> object = qr.query(conn, sql, new ArrayListHandler(),params);
			List<OffActivity> list = new ArrayList<OffActivity>();
			for (int i = 0; i < object.size(); i++) {
				Object obj[] = object.get(i);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				List<Ticket> tickets =new ArrayList<Ticket>();
				User_Info user = new User_Info();
				
				ActivityAlbum aa = new ActivityAlbum();
				//OffActivity oa = new OffActivity(Integer.parseInt(obj[0].toString()),obj[1].toString(),sdf.parse(obj[2].toString()),sdf.parse(obj[3].toString()),city,obj[4].toString(),tickets,user,aa,obj[8].toString(),obj[9].toString(),obj[10].toString(),sdf.parse(obj[11].toString()),obj[12].toString(),obj[13].toString(),obj[14].toString());
				List userList = getActivityUsers(Integer.parseInt(obj[0].toString()));
				//oa.setAttentionPerson(userList);
				//list.add(oa);
			}
			System.out.println("list的大小是:"+list.size());
			pool.releaseConnection(conn);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteOffActivity(int id) {
		Connection conn = pool.getConnection();
		String sql ="delete from offactivity where offactivity_id=?";
		
		try {
			System.out.println("id的值是:"+id);
			int affectRows = qr.update(conn, sql,id);
			pool.releaseConnection(conn);
			return affectRows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	/**
	 * 修改线下活动的基本信息
	 * @param 修改后的线下活动
	 * @return 修改结果
	 */
	public int updateOffActivity(OffActivity oa) {
		Connection conn = pool.getConnection();
		String sql ="update offactivity set offactivity_title=?,start_time=?,end_time=?,offactivity_desc=?,offactivity_statue=?,offActivity_smalltype=?,offActivity_bigtype=? where offActivity_id=?";
		try {
			System.out.println(sql);
			int affectRows = qr.update(conn, sql, oa.getOffactivity_title(),oa.getStart_time(),oa.getEnd_time(),oa.getOffactivity_desc(),oa.getOffactivity_statue(),oa.getOffactivity_type(),oa.getOffactivity_id());
			System.out.println(affectRows+"行记录受影响");
			pool.releaseConnection(conn);
			return affectRows;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 获得json数组
	 */
	public String getExcludePropertyJson(List<OffActivity> offList) {
		

		JsonConfig jsonConfig = new JsonConfig();
			String json1 = JsonUtil.getJsonString4JavaPOJO(offList, "yyyy-MM-dd HH:mm:ss");
			System.out.println(json1);
		  return json1.toString();

	}
	
	public String offlineAskActivityJson(List<OfflineAskActivity> offList) {

		JsonConfig jsonConfig = new JsonConfig();
		String json1 = JsonUtil.getJsonString4JavaPOJO(offList, "yyyy-MM-dd HH:mm:ss");
		return json1.toString();
	}

	@Override
	/**
	 * 获得某用户的线下活动
	 * @param user
	 * @return
	 */
	public List<OffActivity> getMyOffActivity(User_Info user) {
		Connection conn = pool.getConnection();
		List<OffActivity> offActivityList = new ArrayList<OffActivity>();
		String sql ="select * from offAttend where user_id=?";
		try {
			List<Integer> activityList = qr.query(conn, sql, new ColumnListHandler<Integer>("offActivity_id") ,user.getUser_id());
			System.out.println(activityList);
			for (Integer integer : activityList) {
			//	offActivityList.add(getActivity(new Object[]{integer}));
			}
			pool.releaseConnection(conn);
			return offActivityList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * 根据类型和时间查询下线活动
	 * @param type 要查找的类型
	 * @param time 时间
	 * @return
	 */
	public List<OffActivity> getIndexOffActivity(String type, String time,City city) {
		String sql = null;
		Connection conn = pool.getConnection();
		Object[] params = null;
		if("今天".equals(time)){
			if("全部".equals(type)){
				sql = "SELECT * FROM offactivity WHERE DATE(start_time) <= CURDATE() AND Date(end_time)>=CURDATE() and city_id=?";
				params = new Object[]{city.getCity_id()};
			}else{
				sql = "SELECT * FROM offactivity WHERE DATE(start_time) <= CURDATE() AND Date(end_time)>=CURDATE() and city_id=? and offActivity_bigtype=?;";
				params = new Object[]{city.getCity_id(),type};
			}
			
		}else if("明天".equals(time)){
			if("全部".equals(type)){
				sql = "(SELECT * FROM offactivity WHERE DATE(start_time)<=CURDATE() AND DATE(end_time)>=(SELECT DATE_ADD(CURDATE(), INTERVAL 1 DAY)) AND city_id=?) UNION (SELECT * FROM offactivity WHERE DATE(start_time)=(SELECT DATE_ADD(CURDATE(), INTERVAL 1 DAY)) AND city_id=?)";
				params = new Object[]{city.getCity_id(),city.getCity_id()};
			}else{
				sql ="(SELECT * FROM offactivity WHERE DATE(start_time)<=CURDATE() AND DATE(end_time)>=(SELECT DATE_ADD(CURDATE(), INTERVAL 1 DAY)) AND city_id=? and offActivity_bigtype=? ) UNION (SELECT * FROM offactivity WHERE DATE(start_time)=(SELECT DATE_ADD(CURDATE(), INTERVAL 1 DAY)) AND city_id=? and offActivity_bigtype=?)";
				params = new Object[]{city.getCity_id(),type,city.getCity_id(),type};
			}	
		}else if("最近一周".equals(time)){
			if("全部".equals(type)){
				sql = "(SELECT * FROM offactivity WHERE DATE(start_time)<=CURDATE() AND DATE(end_time)>=(SELECT DATE_ADD(CURDATE(), INTERVAL 7 DAY)) AND city_id=?) UNION (SELECT * FROM offactivity WHERE DATE(start_time)<=(SELECT DATE_ADD(CURDATE(), INTERVAL 7 DAY)) AND DATE(end_time)>=(SELECT DATE_ADD(CURDATE(), INTERVAL 7 DAY)) AND city_id=?) UNION (SELECT * FROM offactivity WHERE DATE(start_time)>CURDATE() AND DATE(end_time)<=(SELECT DATE_ADD(CURDATE(), INTERVAL 7 DAY)) AND city_id=?)";
				params = new Object[]{city.getCity_id(),city.getCity_id(),city.getCity_id()};
			}else{
				sql = "(SELECT * FROM offactivity WHERE DATE(start_time)<=CURDATE() AND DATE(end_time)>=(SELECT DATE_ADD(CURDATE(), INTERVAL 7 DAY)) AND city_id=? and offActivity_bigtype=?) UNION (SELECT * FROM offactivity WHERE DATE(start_time)<=(SELECT DATE_ADD(CURDATE(), INTERVAL 7 DAY)) AND DATE(end_time)>=(SELECT DATE_ADD(CURDATE(), INTERVAL 7 DAY)) AND city_id=? and offActivity_bigtype=?) UNION (SELECT * FROM offactivity WHERE DATE(start_time)>CURDATE() AND DATE(end_time)<=(SELECT DATE_ADD(CURDATE(), INTERVAL 7 DAY)) AND city_id=? and offActivity_bigtype=?)";
				params = new Object[]{city.getCity_id(),type,city.getCity_id(),type,city.getCity_id(),type};
			}
		}else if("全部".equals(time)){
			if("全部".equals(type)){
				sql = "SELECT * FROM offactivity WHERE DATE(end_time) >= now() and city_id=?;";
				params = new Object[]{city.getCity_id()};
			}else{
				sql = "SELECT * FROM offactivity WHERE DATE(end_time) >= now() and offActivity_bigtype=? and city_id=?;";
				params = new Object[]{type,city.getCity_id()};
			}
		}
		List<Integer> list = new ArrayList();
		List<OffActivity> offList = new ArrayList();
		System.out.println("sql>>"+sql);
		try {
			list = qr.query(conn, sql, new ColumnListHandler<Integer>("offActivity_id"), params);
			for (Integer integer : list) {
			//	offList.add(getActivity(new Object[]{integer}));
			}
//			System.out.println("offList的大小是："+offList.size());
			pool.releaseConnection(conn);
			return offList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
