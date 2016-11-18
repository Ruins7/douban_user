/**
 * 
 */
package com.dao.lsr.things.daoImpl;

import java.util.List;

import com.dao.lsr.dbutils.daoImpl.DaoOperationImpl;
import com.dao.lsr.things.dao.DoulistDao;
import com.entity.lsr.doulist.User_Doulist;
import com.entity.lsr.doulist.User_Doulist_Content;
import com.entity.lsr.doulist.User_Focus_Doulist;

/**
 * @ClassName:     DoulistDaoImpl.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 *                 豆列功能实现
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月4日 下午3:18:05 
 */
public class DoulistDaoImpl implements DoulistDao{
	
	DaoOperationImpl doimpl = new DaoOperationImpl();

	@Override
	public int createMyDouList(User_Doulist doulist) {
		// 创建我的豆列      
		String sql = "insert into user_doulist (from_user , list_name,  content_type, time  ) values (?,?,?,?)";
		Object[] params = {  doulist.getFrom_user(), doulist.getList_name(), doulist.getContent_type(),doulist.getTime()};
		int resultInt = doimpl.qrUpdate(sql, params);
		return resultInt;
	}

	@Override
	public int deleteMyDouList(String doulist_id) {
		// 删除我的豆列
		String sql = "delete from user_doulist where doulist_id = ?";
		Object[] params = { doulist_id };
		int resultInt = doimpl.qrUpdate(sql, params);
		return resultInt;
	}

	@Override
	public int addItemToMyDouList(User_Doulist_Content udc) {
		// 向我的豆列添加项目,之前应该将该豆列中的sub_item_id拿到，在此append
		String sql = "insert into User_Doulist_Content(belongto_doulist, time, item_id,simple_common ) values(?,?,?,?)";
		Object[] params = { udc.getBelongto_doulist(), udc.getTime(),udc.getItem_id(), udc.getSimple_common() };
		int resultInt = doimpl.qrUpdate(sql, params);
		return resultInt;
	}

	@Override
	public int delItemFromMyDouList(String record_id) {
		// 从我的豆列删除项目,之前应该将该豆列中的sub_item_id拿到，在此substring
		String sql = "delete from User_Doulist_Content where record_id = ?";
		Object[] params = { record_id };
		int resultInt = doimpl.qrUpdate(sql, params);
		return resultInt;
	}

	@Override
	public int focusOthersDouList(User_Focus_Doulist ufd) {	 
		// 关注其他人的豆列
		String sql = "insert into user_focus_doulist (doulist, focus_user, time) values (?,?,?)";
		Object[] params = { ufd.getDoulist(), ufd.getFocus_user(), ufd.getTime()};
		int resultInt = doimpl.qrUpdate(sql, params);
		return resultInt;
	}

	@Override
	public int cancelFocusOthersDouList(String focus_id) {
		// 取消关注其他人的豆列
		String sql = "delete from user_focus_doulist where focus_id = ?";
		Object[] params = { focus_id };
		int resultInt = doimpl.qrUpdate(sql, params);
		return resultInt;
	}

	@Override
	public List<User_Doulist> showMyDouList(String create_by_user) {
		// 查找我创建的所有豆列
		String sql = "select * from user_doulist where from_user = ?";
		Object[] params = { create_by_user };
		List<User_Doulist> list = doimpl.qrQueryForList(sql, new User_Doulist(), params);
		return list;
	}
	
	@Override
	public List<User_Doulist> showMyOneTypeDouList(String create_by_user,String type) {
		// 查找我创建的所有某一类别的豆列
		String sql = "select * from user_doulist where from_user = ? and content_type = ?";
		Object[] params = { create_by_user, type };
		List<User_Doulist> list = doimpl.qrQueryForList(sql, new User_Doulist(), params);
		return list;
	}

	@Override
	public List<User_Focus_Doulist> showMyFocusDouList(String foucs_user) {
		// 查找我关注的所有豆列
		String sql = "select * from user_focus_doulist where focus_user = ?";
		Object[] params = { foucs_user };
		List<User_Focus_Doulist> list = doimpl.qrQueryForList(sql, new User_Focus_Doulist(), params);
		return list;
	}

	@Override
	public List<User_Doulist_Content> showOneDouList(String doulist_id) {
		// 查找某一个豆列
		String sql = "select * from User_Doulist_Content where belongto_doulist = ?";
		Object[] params = {doulist_id};	 
		List<User_Doulist_Content> doulist = doimpl.qrQueryForList(sql, new User_Doulist_Content(), params);
		return doulist;
	}

	@Override
	public List<User_Doulist> showSameTypeDoulist(int doulist_type, int user_id) {
		// 查找某一用户所有的相同类型的豆列
		  String sql = "select * from user_doulist where content_type = ? and from_user = ?";
		  Object[] params = {doulist_type, user_id};
		  List<User_Doulist> list = doimpl.qrQueryForList(sql, new User_Doulist(), params);
		return list;
	}

	 
}
