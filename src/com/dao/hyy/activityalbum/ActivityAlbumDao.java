package com.dao.hyy.activityalbum;

import java.util.List;

import com.entity.hyy.activity.ActivityAlbum;
import com.entity.hyy.activity.ActivityPhoto;
/**
 * 活动相册的dao层
 * @author 胡伊杨
 *
 */
public interface ActivityAlbumDao {

	/**
	 * 获得活动的相册
	 * @param id相册的id
	 * @return 相册
	 */
	public ActivityAlbum getActivityAlbum(int id);
	public List<ActivityPhoto> showActivityPhoto(int activity_album_id);
}
