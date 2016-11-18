package com.dao.lsr.things.dao;

import java.util.List;

import com.entity.lsr.doulist.User_Doulist;
import com.entity.lsr.doulist.User_Doulist_Content;
import com.entity.lsr.doulist.User_Focus_Doulist;

/**
 * @ClassName:     RecommendToMyBroadcast.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 *                 豆列功能接口
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月4日 下午3:27:10 
 */

public interface DoulistDao {
	
	public int createMyDouList(User_Doulist doulist);
	public int deleteMyDouList(String doulist_id);
	public List<User_Doulist> showMyDouList(String create_by_user);
	public List<User_Focus_Doulist> showMyFocusDouList(String foucs_user);
	public List<User_Doulist_Content> showOneDouList(String doulist_id);
	public int addItemToMyDouList(User_Doulist_Content udc);
	public int delItemFromMyDouList(String record_id);
	public int focusOthersDouList(User_Focus_Doulist ufd);
	public int cancelFocusOthersDouList(String focus_id);
	public List<User_Doulist> showSameTypeDoulist(int doulist_type, int user_id);
	public List<User_Doulist> showMyOneTypeDouList(String create_by_user,String type);

}
