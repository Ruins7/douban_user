package com.dao.hyy.activity;



import java.util.List;
import java.util.Map;

import com.entity.hyy.activity.Activity_type;
import com.entity.hyy.activity.OffActivity;
import com.entity.hyy.activity.OfflineAskActivity;
import com.entity.hyy.city.City;
import com.entity.lsr.user.User_Info;

/**
 * 线下活动dao层接口
 * @author 胡伊杨
 *
 */
public interface OffActivityDao {
	/**
	 * 查找所有活动类型   ----R
	 * @return 
	 */
	public List<Activity_type> getAllType();
	
	/**
	 * 根据活动类型查找线下活动   ----R
	 * @return 
	 */
	public Map<Activity_type,List<OffActivity>> getOffActivityByAllType(List<Activity_type> list);
	
	
	
	/**
	 * 根据活动类型查找该类别的线下活动   ----R
	 * @return 
	 */
	public List<OffActivity> getOffActivityByOneType(String type_id) ;
	
	
	/**
	 * 查找单个线下活动  -----R
	 */
	public OffActivity getActivity(String act_id);
	
	/**
	 * 查找单个线下活动 对的用户交流 -----R
	 */
	public List<OfflineAskActivity> getActivityAsk(String act_id);
	
	/**
	 * 修改线下活动的基本信息
	 * @param 修改后的线下活动
	 * @return 修改结果
	 */
	public int updateOffActivity(OffActivity oa);
	
	/**
	 * 查找参加同一线下活动的用户   ----R
	 * @return 集合
	 */
	public List<Object[]> getSameAttendUsersArray(String off_id);
	
	/**
	 * 我要参加活动   -----R
	 */
	public int attendActivity(String offActivity_id,String user_id);
	
	/**
	 * 取消参加活动   -----R
	 */
	public int cancelAttendActivity(String offActivity_id,String user_id);
	
	/**
	 * 某用户参与的所有线下活动-----R
	 */
	public List<OffActivity> OneUserAttendAllOffActivity(String user_id);
	
	/**
	 * 获得参加同一线下活动的用户
	 * @param offActivity_id 线下活动的编号
	 * @return user集合
	 */
	public List<User_Info> getActivityUsers(int offActivity_id);
	
	/**
	 * 获得所有同城线下活动
	 * @return 所有线下活动编号的集合
	 */
	public List<OffActivity> getSameCityOffActivity(String city_id);
	
	/**
	 * 增加线下活动
	 * @param oa 线下活动对象
	 * @return 操作的结果
	 */
	public int addOfflineActivity(OffActivity oa);
	
	/**
	 * 线下活动用户提问发起者
	 * @param oaa 一条问题的对象
	 * @return 执行结果
	 */
	public int insertaskActivity(OfflineAskActivity oaa);
	
	/**
	 * 根据id查找线下活动
	 * @param activity_id 活动的id
	 * @return 查找的线下活动
	 */
	public OffActivity findActivity(int activity_id); 
	
	/**
	 * 获得所有的用户想发起者的提问
	 * @param activity_id 活动的id
	 * @return 问题的list
	 */
	public List<OfflineAskActivity> getAllContent(int activity_id);
	
	/**
	 * 获得所有的线下活动
	 * @return 线下活动的集合
	 */
	public List<OffActivity> getAllOffActivity(City city,String bigType,String smallType);
	
	/**
	 * 删除线下活动
	 * @param id 要删除的线下活动
	 * @return 操作结果
	 */
	public int deleteOffActivity(int id);
	
	/**
	 * 获得线下活动的json
	 * @param offList
	 * @return
	 */
	public String getExcludePropertyJson(List<OffActivity> offList);
	
	/**
	 * 获得提问的json
	 * @param offList
	 * @return
	 */
	public String offlineAskActivityJson(List<OfflineAskActivity> offList);
	
	/**
	 * 获得某用户的线下活动
	 * @param user
	 * @return
	 */
	public List<OffActivity> getMyOffActivity(User_Info user);
	
	/**
	 * 根据类型和时间查询下线活动
	 * @param type 要查找的类型
	 * @param time 时间
	 * @param city 城市
	 * @return
	 */
	public List<OffActivity> getIndexOffActivity(String type,String time,City city);
	
}
