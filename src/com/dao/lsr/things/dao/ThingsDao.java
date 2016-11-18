package com.dao.lsr.things.dao;

import java.util.List;

import com.entity.lsr.doulist.User_Doulist;
import com.entity.lsr.things.Things;
import com.entity.lsr.things.Things_Type;

/**
 * @ClassName:     ThingsDao.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 *                 东西模块接口
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月8日 上午9:59:55 
 */

public interface ThingsDao {

	public int publishThing(Things things);
	public int deleteThing(String things_id);
	public List<Things> showMyThings(String from_user);
	public Things showOneThing(String things_id);
	public List<Things> showThingsByType(String type);
	public List<User_Doulist> showDoulistThingsBelongto(String item_id, String content_type);
	public List<Things_Type> showAllThingsType();
	public List<Things> showNewThings();
	
}
