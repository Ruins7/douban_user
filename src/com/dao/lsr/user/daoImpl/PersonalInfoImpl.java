/**
 * 
 */
package com.dao.lsr.user.daoImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dao.lsr.dbutils.daoImpl.DaoOperationImpl;
import com.dao.lsr.user.dao.PersonalInfo;
import com.entity.hyy.city.City;
import com.entity.lsr.user.User_Album;
import com.entity.lsr.user.User_Commons;
import com.entity.lsr.user.User_Diary;
import com.entity.lsr.user.User_Focus;
import com.entity.lsr.user.User_Info;
import com.entity.lsr.user.User_Leave_Message;
import com.entity.lsr.user.User_Photo;
import com.entity.lsr.user.User_Root;

/**
 * @ClassName:     PersonalInfoImpl.java
 * @Description:   TODO(用一句话描述该文件做什么)
 * 
 *                 用户信息实现类
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月9日 下午2:31:46 
 */
public class PersonalInfoImpl implements PersonalInfo {
	
	DaoOperationImpl doimpl = new DaoOperationImpl();

	@Override
	public int createAlbum(User_Album ua) {
		// 创建一个相册
		String sql = "insert into user_album(user, time, album_name,simple_desc,album_root,imgs) values(?,?,?,?,?,?)";
		Object[] params = {ua.getUser(), ua.getTime(), ua.getAlbum_name(), ua.getSimple_desc(),ua.getAlbum_root(),ua.getImgs()};
		int result = doimpl.qrUpdate(sql, params);
		return result;
	}

	@Override
	public int deleteAlbum(String album_id) {
		// 删除相册
		String sql = "delete from user_album where album_id = ?";
		Object[] params = {album_id};
		int result = doimpl.qrUpdate(sql, params);
		return result;		
	}

	@Override
	public User_Album showOneAlbum(String album_id) {
		// 查看一个相册
		String sql = "select * from user_album where album_id = ?";
		Object[] params = {album_id};
		User_Album album = new User_Album();
		album = doimpl.qrQueryForOne(sql, album, params);
		return album;
		
	}
	
	@Override
	public List<User_Photo> showPhotoFromOneAlbum(String album_id) {
		// 查看一个相册里的照片
		String sql = "select * from user_photo where from_album = ?";
		Object[] params = {album_id};
	 
		List<User_Photo> photo = doimpl.qrQueryForList(sql, new User_Photo(), params);
		return photo;
		
	}
	
	@Override
	public List<User_Album> showUserAllAlbums(String user_id) {
		// 查看所有相册
		String sql = "select * from user_album where user = ?";
		Object[] params = {user_id};	 
		List<User_Album> albums = doimpl.qrQueryForList(sql, new User_Album(), params);
		if(albums != null){
			for (User_Album user_Album : albums) {
				User_Info user = new User_Info();
				user = new PersonalInfoImpl().showOneByR(user_Album.getUser()+"", user);
				user_Album.setUser_info(user);
				User_Root root = new User_Root();
				root = new PersonalInfoImpl().showOneByR(user_Album.getAlbum_root()+"", root);
				user_Album.setRoot(root);
			}
			return albums;
		}else{
			return null;
		}
	}
	
	@Override
	public int modifyAlbum(User_Album ua) {
		// 更改相册
		String sql = "update user_album set album_name = ?,simple_desc = ?, album_root=?,imgs = ?  where album_id = ?";
		Object[] params = {ua.getAlbum_name(), ua.getSimple_desc(), ua.getAlbum_root(), ua.getImgs(),ua.getAlbum_id()}; 
		int result = doimpl.qrUpdate(sql, params);
		return result;
		
	}
	
	@Override
	public int[] addPhotoToAlbum(User_Photo photo) {
		// 向一个相册批量添加照片
		String sql = "insert into user_photo(photo_address,from_album,upload_time, photo_name,simple_desc) values(?,?,?,?,?)";
		Object[] par = {photo.getPhoto_address(),photo.getFrom_album(),photo.getUpload_time(),photo.getPhoto_name(),photo.getSimple_desc()};
		Object[][] photos = {par};
		int[] success_num = doimpl.qrInsertBatch(sql, photos);
		return success_num;	
	}

	@Override
	public Map<Integer, Integer> delPhotoToAlbum(String[] photo_ids) {
		// 从一个相册批量删除照片
		String sql = "delete from user_photo where photo_id = ?";
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < photo_ids.length; i++) {
			Object[] id = { photo_ids[i] };
			int resultNum = doimpl.qrUpdate(sql, id);
			map.put(i, resultNum);
		}
		return map;		
	}
	
	@Override
	public int editPhoto(User_Photo photo) {
		//编辑照片
		    String sql = "update user_photo set photo_address = ? , from_album = ? , upload_time = ? , photo_name = ? , simple_desc = ? where photo_id = ?";
			Object[] params = { photo.getPhoto_address() , photo.getFrom_album(), photo.getUpload_time(), photo.getPhoto_name(), photo.getSimple_desc(), photo.getPhoto_id()};
			int resultNum = doimpl.qrUpdate(sql, params);
		return resultNum;		
	}

	@Override
	public User_Photo showOnePhoto() {
		// 显示一张照片的详细信息
		String sql = "select * from user_photo where photo_id = ?";
		Object[] params = {};
		User_Photo photo = new User_Photo();
		photo = doimpl.qrQueryForOne(sql, photo, params);
		return photo;		
	}

	@Override
	public int writeDiary(User_Diary diary) {
		// 写日志
		String sql = "insert into user_diary(user, time, title ,content, imgs, read_root) values(?,?,?,?,?,?)";
		Object[] params = {diary.getUser(),diary.getTime(),diary.getTitle(),diary.getContent(),null,diary.getRead_root()};	 
		int result = doimpl.qrUpdate(sql, params);
		return result;		
	}

	@Override
	public int delDiary(String diary_id) {
		// 删除日志
		String sql = "delete from user_diary where diary_id = ?";
		Object[] params = {diary_id};
		int result = doimpl.qrUpdate(sql, params);
		return result;		
	}
	
	@Override
	public List<User_Diary> showUserAllDiarys(String user_id) {
		// 查看用户所有日志
		String sql = "select * from user_diary where user = ?";
		Object[] params = {user_id};
		List<User_Diary> diarys = doimpl.qrQueryForList(sql, new User_Diary(), params);
		return diarys;	
	}
	
	@Override
	public User_Diary showOneDiary(String diary_id) {
		// 查看一篇日志的详细信息
		String sql = "select * from user_diary where diary_id = ?";
		Object[] params = {diary_id};
		User_Diary diary = new User_Diary();
		diary = doimpl.qrQueryForOne(sql, diary, params);
		return diary;	
	}

	@Override
	public User_Leave_Message leaveMessage(User_Leave_Message mess) {
		// 给好友留言
		String sql = "insert into user_leave_message(from_user,to_user,time,content) values(?,?,?,?)";
		Object[] params = {mess.getFrom_user(), mess.getTo_user(), mess.getTime(), mess.getContent()};
		int result = doimpl.qrUpdate(sql, params);
		if(result == 1){
			//查找刚才添加的数据
			String sql1 = "select * from user_leave_message where from_user = ? and to_user = ? order by time desc limit 1";
			Object[] params1 = {mess.getFrom_user(), mess.getTo_user()};
			User_Leave_Message message = doimpl.qrQueryForOne(sql1, new User_Leave_Message(), params1);
			return message;
		}else{
			return null;
		}	
	}

	@Override
	public int delMessage(String message_id) {
		// 删除好友留言
		String sql = "delete from user_leave_message where message_id = ?";
		Object[] params = {message_id};
		int result = doimpl.qrUpdate(sql, params);
		return result;		
	}

	@Override
	public int foucsOthers(User_Focus uf) {
		// 关注其他用户
		String sql = "insert into user_focus(from_user,to_user,time) values(?,?,?)";
		Object[] params = {uf.getFrom_user(), uf.getTo_user(), uf.getTime()};
		int result = doimpl.qrUpdate(sql, params);
		return result;			
	}

	@Override
	public int cancelFocus(String from_user, String to_user) {
		// 取消关注
		String sql = "delete from user_focus where from_user = ? and to_user = ?";
		Object[] params = {from_user, to_user};
		int result = doimpl.qrUpdate(sql, params);
		return result;			
	}

	@Override
	public User_Info signin(User_Info user) {
		// 新用户注册
		String sql = "insert into user_info(username,pwd,email,location,join_in_time) values(?,?,?,?,?)";
		Object[] params = {user.getUsername(), user.getPwd(), user.getEmail(), user.getLocation(),user.getJoin_in_time()};
		int result = doimpl.qrUpdate(sql, params);
		if(result == 1){
			System.out.println("注册成功");
			String sql1 = "select * from user_info where username = ? and email = ?";
			Object[] params1 = {user.getUsername(), user.getEmail()};
			User_Info new_user = doimpl.qrQueryForOne(sql1, new User_Info(), params1);
			return new_user;
		}else{
			return null;
		}	
	}

	@Override
	public int modifyUserStatus(String user_id, String status) {
		// 用户注销，管理员拉黑用户,用户状态改变
		String sql = "update user_info set status = ? where user_id = ?";
		Object[] params = {status, user_id};
		int result = doimpl.qrUpdate(sql, params);
		return result;	
	}

	@Override
	public User_Info login(String username , String pwd) {
		// 用户登录，管理员登录
		String sql = "select * from user_info where username = ? and pwd = ?";
		Object[] params = {username, pwd};
		User_Info user = doimpl.qrQueryForOne(sql, new User_Info(), params);
		if(user == null)return null;
		else return user;	
	}
	
	@Override
	public User_Info searchUserByEmail(String username,String email) {
		// 通过Email找到用户，修改密码
		String sql = "select user_id from user_info where username = ? and email = ?";
		Object[] params = {username, email};
		List<Object[]> list = new ArrayList<Object[]>();
		list = doimpl.qrQueryForResultSet(sql, params);
		return (User_Info) list.get(0)[0];			
	}
	@Override
	public int modifyPwd(String pwd, String user_id) {
		// 忘记密码，更改密码	
		String sql = "update user_info set pwd = ? where user_id = ?";
		Object[] params = {pwd, user_id};
		int result = doimpl.qrUpdate(sql, params);
		return result;	
	}

	@Override
	public int modifyUserInfo(User_Info user) {
		// 用户更改个人信息
		String sql = "update user_info set username = ?, email = ? ,imgs = ? , location = ? where user_id = ?";
		Object[] params = {user.getUsername(),user.getEmail(), user.getImgs(), user.getLocation(), user.getUser_id()};
		int result = doimpl.qrUpdate(sql, params);
		return result;			
	}

	@Override
	public <R> List<R> searchDBBaseOnAlgorithm(List<Map<Integer, Integer>> list, R r) {
		// 根据用户浏览次数最多的项目的type查找DB
		// 随机查询记录
		System.out.println("searchDBBaseOnAlgorithm:"+list.size());
		List<R> result_list = new ArrayList<R>();
		int num = 5;
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<Integer, Integer> map = (Map<Integer, Integer>) iterator.next();
			for(int type_id: map.keySet()){  
			    //System.out.println("key:" + type_id+ "   value:" + map.get(type_id)); 
				String sql = "select * from " + r.getClass().getSimpleName() + " where type = ? order by rand() limit ?";
				Object[] params = {type_id, num--};
			    List<R> list_l = doimpl.qrQueryForList(sql, r, params);
			    for (R r_l : list_l) {
			    	result_list.add(r_l);
				} 
			   }  
		}
		return result_list;	
	}

	@Override
	public int modifyDiary(User_Diary diary) {
		// 修改日志
		String sql = "update user_diary set title = ? , content = ? , imgs = ? ,read_root = ? , write_time = ? where diary_id = ?";
		Object[] params = {diary.getTitle(), diary.getContent(), diary.getImgs(), diary.getRead_root(), diary.getTime(), diary.getDiary_id()};
		int result = doimpl.qrUpdate(sql, params);
		return result;
	}

	@Override
	public List<User_Info> whoFocusMe(String user_id) {
		// 谁关注了此用户
		String sql = "select * from user_focus where to_user = ?";
		Object[] params = {user_id};
		List<User_Info> result_list = doimpl.qrQueryForList(sql, new User_Info(), params);
		return result_list;
	}

	@Override
	public List<User_Info> MyFocus(String user_id) {
		// 该用户关注了谁
		List<User_Info> return_list = new ArrayList<User_Info>();
		String sql = "select to_user from user_focus where from_user = ?";
		Object[] params = {user_id};
		List<Object[]> result_list = doimpl.qrQueryForResultSet(sql, params);
		if(result_list != null){		 
			for (Object[] objects : result_list) {
				String sql1 = "select * from user_info where user_id = ?";
				Object[] params1 = {objects[0]};
				User_Info user = doimpl.qrQueryForOne(sql1, new User_Info(), params1);
				return_list.add(user);
			}		
		}
		return return_list;
	}
	
	@Override
	public int modifyUserImg(String user_id , String imgs) {
		// 用户修改头像
		String sql = "update user_info set imgs = ? where user_id = ?";
		Object[] params = {imgs, user_id};
		int result_list = doimpl.qrUpdate(sql, params);
		return result_list;
	}
	
	@Override
	public List<Object[]> adminCheckInvalidFiels() {
		// 管理员整理失效上传文件
		//检索所有表的imgs地址
		String sql = "select imgs from user_info";	 
		List<Object[]> result = doimpl.qrQueryForResultSet(sql, null);
		return result;
	}

	@Override
	public List<City> searchCity() {
		// 查找所有城市
		String sql = "select * from city";
		List<City> cities = doimpl.qrQueryForList(sql, City.class);
		return cities;	
	}
	
	@Override
	public <R> R showOneByR(String id, R r) {
	    //主键id必须是第一个属性
		Field f = r.getClass().getDeclaredFields()[0];
		// 通过id和类型查找某个对象
		String sql = "select * from " + r.getClass().getSimpleName() + " where " + f.getName() + " = ?";
		Object[] params = {id};
	    r = doimpl.qrQueryForOne(sql, r, params);
		return r;	
	}
	
	@Override
	public int modifyUserDesc(String user_id,String user_desc) {
		// 修改desc
		String sql = "update user_info set user_desc = ? where user_id = ?";
		Object[] params = {user_desc, user_id};
		int result = doimpl.qrUpdate(sql, params);
		return result;	
	}
	
	@Override
	public String modifyUserSimpleDesc(String user_id,String simple_desc) {
		// 修改simple_desc
		String sql = "update user_info set simple_intro = ? where user_id = ?";
		Object[] params = {simple_desc, user_id};
		int result = doimpl.qrUpdate(sql, params);
		if(result == 1){
			return simple_desc;
		}else{
			return null;	
		}	
	}

	@Override
	public List<User_Commons> queryOneDiaryComment(String diary_id) {
		// 查询谋篇日志的回应  user
		String sql = "select * from user_commons where item = 3 and item_id = ? order by time DESC";
		Object[] params = {diary_id};
		List<User_Commons> list = doimpl.qrQueryForList(sql, new User_Commons(), params);	 
		return list;	
	}
	
	@Override
	public List<User_Commons> queryOnePhotoComment(String photo_id) {
		// 查询谋张照片的回应  user
		String sql = "select * from user_commons where item = 1 and item_id = ? order by time DESC";
		Object[] params = {photo_id};
		List<User_Commons> list = doimpl.qrQueryForList(sql, new User_Commons(), params);	 
		return list;	
	}
	
	@Override
	public List<User_Info> queryUser(String username) {
		// 查询谋张照片的回应  user
		String sql = "select * from user_info where username = ? order by join_in_time DESC";
		Object[] params = {username};
		List<User_Info> list = doimpl.qrQueryForList(sql, new User_Info(), params);	 
		return list;	
	}

	

}
