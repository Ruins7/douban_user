/**
 * 
 */
package com.commontools.lsr;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.entity.hyy.activity.OffActivity;
import com.entity.lsr.user.User_Info;

/**
 * @ClassName:     UserGetActivity.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 *                 登陆用户查询自己参加过的线下活动
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月17日 上午9:44:17 
 */
public class UserGetActivity {
	
	public static void getActOfMe(HttpServletRequest request,String user_id){
		HttpSession session = request.getSession();
		Map<OffActivity,User_Info> new_map = new HashMap<OffActivity, User_Info>();
		List<Map<OffActivity,List<User_Info>>> act = (List<Map<OffActivity, List<User_Info>>>) session.getAttribute("xxxxxx");
		//得到当前用户参加所有活动
		for (Iterator iterator = act.iterator(); iterator.hasNext();) {
			Map<OffActivity, List<User_Info>> map = (Map<OffActivity, List<User_Info>>) iterator.next();
			for (OffActivity a : map.keySet()) {
				   //参加a活动的所用用户
				   List<User_Info> users = map.get(a);
				   for (Iterator iterator2 = users.iterator(); iterator2.hasNext();) {
					User_Info user_Info = (User_Info) iterator2.next();
					if(user_Info.getUser_id() == Integer.parseInt(user_id)){
						new_map.put(a, user_Info);
					}
				}
			}
		}
		Map<OffActivity,String> map1 = getMailAct(new_map);
		session.setAttribute("current_user_sys_mail", map1);
	}
	
	//获得需要发送豆邮的活动
	private static Map<OffActivity,String> getMailAct(Map<OffActivity,User_Info> map){
		Map<OffActivity,String> map1 = new HashMap<OffActivity, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		Long new_time = Long.parseLong(sdf.format(new Date()));
		for (OffActivity act : map.keySet()) {
			//判断活动时间	
			Long start_time = Long.parseLong(sdf.format(act.getStart_time()));
		    Long end_time  =  Long.parseLong(sdf.format(act.getEnd_time()));
		    if(start_time - new_time < 259200){//活动时间小于三天
		    	map1.put(act, "活动还有不到三天开始.");
		    }
		    if(start_time > new_time & end_time < new_time){//正在进行
		    	map1.put(act, "活动正在进行中...");
		    }  
		}
		return map1;
	}

}
