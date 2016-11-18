/**
 * 
 */
package com.dao.lsr.things.daoImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.dao.lsr.dbutils.daoImpl.DaoOperationImpl;
import com.dao.lsr.things.dao.ThingsDao;
import com.entity.lsr.doulist.User_Doulist;
import com.entity.lsr.things.Things;
import com.entity.lsr.things.Things_Type;

/**
 * @ClassName:     ThingDaoImpl.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 *                 东西功能实现
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月9日 下午3:38:25 
 */
public class ThingDaoImpl implements ThingsDao{
	
	DaoOperationImpl doimpl = new DaoOperationImpl();
	
	@Override
	public int publishThing(Things things) {
		// 发布东西
		String sql = "insert into things(things_name,from_user,time,imgs,type,simple_desc) values(?,?,?,?,?,?)";
		Object[] params = {things.getThings_name(),things.getFrom_user(), things.getTime(),things.getImgs(),things.getType(), things.getSimple_desc()};	 
		int result = doimpl.qrUpdate(sql, params);
		return result;		
	}

	@Override
	public int deleteThing(String things_id) {
		// 删除发布的东西，用户，管理员
		String sql = "delete from things where things_id = ?";
		Object[] params = {things_id};	 
		int result = doimpl.qrUpdate(sql, params);
		return result;	
	}

	@Override
	public List<Things> showMyThings(String from_user) {
		// 查看我发布的东西
		String sql = "select * from things where from_user = ?";
		Object[] params = {from_user};	 
		List<Things> list = doimpl.qrQueryForList(sql, new Things(), params);
		return list;			
	}

	@Override
	public Things showOneThing(String things_id) {
		// 查看某一样东西的详细信息
		String sql = "select * from things where things_id = ?";
		Object[] params = {things_id};	
		Things things = new Things();
		things = doimpl.qrQueryForOne(sql, things, params);
		return things;
	}

	@Override
	public List<Things> showThingsByType(String type) {
		// 按照分类查找东西
		String sql = "select * from things where type = ? order by time DESC";
		Object[] params = {type};	
		List<Things> things = doimpl.qrQueryForList(sql, new Things(), params);
		return things;
	}

	@Override
	public List<User_Doulist> showDoulistThingsBelongto(String item_id, String content_type) {
		//这个项目所属豆列
		String sql = "select belongto_doulist from user_doulist_content where belongto_doulist in (select doulist_id from user_doulist where content_type = ?) and item_id = ?";
		Object[] params = {content_type, item_id};	
		List<Object[]> doulist = doimpl.qrQueryForResultSet(sql, params);
		if(doulist != null){
			//循环查出每一个豆列
			String sql3 = "select * from user_doulist where doulist_id = ? order by time DESC";
			List<User_Doulist> result = new ArrayList<User_Doulist>();
			for (Iterator iterator = doulist.iterator(); iterator.hasNext();) {
				Object[] objects = (Object[]) iterator.next();
				User_Doulist dd = doimpl.qrQueryForOne(sql3, new User_Doulist(), objects);
				result.add(dd);
			}
			return result;
		}else{
			return null;
		}
	
	}
	
	@Override
	public List<Things_Type> showAllThingsType() {
		//查找所有东西分类
		String sql = "select * from Things_Type";
		List<Things_Type> list = doimpl.qrQueryForList(sql, Things_Type.class);
		return list;
	}
	
	@Override
	public List<Things> showNewThings() {
		//查找最近发布的东西
		String sql = "select * from Things order by time DESC";
		List<Things> list = doimpl.qrQueryForList(sql, Things.class);
		return list;
	}

}
