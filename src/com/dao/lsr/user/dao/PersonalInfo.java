/**
 * 
 */
package com.dao.lsr.user.dao;

import java.util.List;
import java.util.Map;

import com.entity.hyy.city.City;
import com.entity.lsr.user.User_Album;
import com.entity.lsr.user.User_Commons;
import com.entity.lsr.user.User_Diary;
import com.entity.lsr.user.User_Focus;
import com.entity.lsr.user.User_Info;
import com.entity.lsr.user.User_Leave_Message;
import com.entity.lsr.user.User_Photo;

/**
 * @ClassName:     PersonalInfo.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 *                 用户个人信息接口
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月9日 下午2:22:04 
 */
public interface PersonalInfo {

	public int createAlbum(User_Album ua);
	public int deleteAlbum(String album_id);
	public User_Album showOneAlbum(String album_id);
	public List<User_Album> showUserAllAlbums(String user_id);
	public int modifyAlbum(User_Album ua);
	public int[] addPhotoToAlbum(User_Photo photo);
	public Map<Integer, Integer> delPhotoToAlbum(String[] photo_ids);
	public int editPhoto(User_Photo photo);
	public List<User_Photo> showPhotoFromOneAlbum(String album_id);
	public User_Photo showOnePhoto();
	public int writeDiary(User_Diary diary);
	public int delDiary(String diary_id);
	public int modifyDiary(User_Diary diary);
	public List<User_Diary> showUserAllDiarys(String user_id);
	public User_Diary showOneDiary(String diary_id);
	public User_Leave_Message leaveMessage(User_Leave_Message mess);
	public int delMessage(String message_id);
	public int foucsOthers(User_Focus uf);
	public int cancelFocus(String from_user, String to_user);
	public List<User_Info> whoFocusMe(String user_id);
	public List<User_Info> MyFocus(String user_id);
	public User_Info signin(User_Info user);
	public int modifyUserStatus(String user_id, String status);
	public User_Info login(String username , String pwd);
	public int modifyPwd(String pwd, String user_id);
	public User_Info searchUserByEmail(String username,String email);
	public int modifyUserInfo(User_Info user);
	public <R> R showOneByR(String id , R r);
	public <R> List<R> searchDBBaseOnAlgorithm(List<Map<Integer, Integer>> list, R r);
	public int modifyUserImg(String user_id , String imgs);
	public List<Object[]> adminCheckInvalidFiels();
	public List<City> searchCity();
	public int modifyUserDesc(String user_id,String user_desc);
	public String modifyUserSimpleDesc(String user_id,String simple_desc);
	public List<User_Commons> queryOneDiaryComment(String diary_id);
	public List<User_Commons> queryOnePhotoComment(String photo_id);
	public List<User_Info> queryUser(String username);
	
}
