package com.dao.hyy.activity;

import java.util.List;

import com.entity.hyy.activity.OffActivity;
import com.entity.hyy.activity.OnlineActivity;

/**
 * 线上活动dao层接口
 * @author 胡伊杨
 *
 */
public interface OnlineActivityDao {
	
	/**
	 * 修改单个线上活动的信息
	 * @param id 活动的id
	 * @return 是否修改成功
	 */
	public int updateOnActivity(OnlineActivity oa);
	
	/**
	 * 查找单个线上活动的具体信息
	 * @param id 要查找的id号
	 * @return 查到的单个线上活动
	 */
	public OnlineActivity searchAOnActivity(int id);
	
	/**
	 * 添加线上活动
	 * @param oa 线上活动实体
	 * @return 操作结果
	 */
	public int addOnlinActivity(OnlineActivity oa);
	
	/**
	 * 删除线上活动
	 * @param activity_id 活动的编号
	 * @return 操作结果
	 */
	public int deleteOnlineActivity(int activity_id);
	
	/**
	 * 查找所有的线上活动
	 * @return 线上活动的集合
	 */
	public List<OnlineActivity> searcAllhActivity();
	
}
