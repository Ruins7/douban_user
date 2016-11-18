/**
 * 
 */
package com.commontools.lsr;

import java.util.List;

/**
 * @ClassName:     UserNoticeAndMail.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 *                 系统给用户推送通知(东西：关注的豆列有更新，小组：关注的话题有更新，同城：关注的活动有更新)
 *                 系统给用户发豆邮
 *                 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月10日 下午1:17:14 
 */
public class UserNoticeAndMail {

	public <R> void checkUpdate(Object id, List<R> list){
		//当豆列，话题，活动有变化时，拿到变化的数据
		//service层servlet添加监听器，当关注的豆列更新（add）成功时触发，拿到新数据
		//查找关注该项目的所有用户
	}
}
