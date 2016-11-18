/**
 * 
 */
package com.commontools.lsr;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.lsr.user.daoImpl.PersonalInfoImpl;
import com.entity.lsr.book.Book;
import com.entity.lsr.user.Br_type_table;
import com.entity.lsr.user.User_Broadcast;
import com.entity.lsr.user.User_Diary;
import com.entity.lsr.user.User_Like;
import com.entity.lsr.user.User_Recommend;

/**
 * @ClassName:     UserRecommend.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 *                 查询好友推荐,转载的广播，喜欢，打分中的项目的详细信息
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月19日 下午7:09:33 
 */
public class UserRecommend<R extends Serializable> {
	
	private static PersonalInfoImpl psImpl = new PersonalInfoImpl();
	
	public static String getItemTypeOFRecommend(User_Recommend rec){
		 Br_type_table br = new Br_type_table();
		 br = psImpl.showOneByR(rec.getType_table()+"", br);
		 String type = br.getType_table_name();
		 return type;
	}
	public static String getItemTypeOFBroadcast(User_Broadcast rec){
		Br_type_table br = new Br_type_table();
		if(rec.getType_table() != 0){//原创广播.没有type_table
			br = psImpl.showOneByR(rec.getType_table()+"", br);
			String type = br.getType_table_name();
			return type;
		}else{//转载广播
			return null;
		}	
	}
	public static String getItemTypeOFLike(User_Like rec){ 
		Br_type_table br = new Br_type_table();
		br = psImpl.showOneByR(rec.getItem()+"", br);
		String type = br.getType_table_name();
		return type;
	}
	 

}
