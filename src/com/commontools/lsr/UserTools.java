/**
 * 
 */
package com.commontools.lsr;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.iterators.UniqueFilterIterator;

import com.dao.lsr.user.daoImpl.PersonalInfoImpl;
import com.entity.lsr.things.Things;

/**
 * @ClassName: UserTools.java
 * @Description: TODO(用一句话描述该文件做什么)
 * 
 *               书，电影，音乐打分的类 获取当前时间 音乐，电影，书，活动，小组话题，东西，根据用户浏览记录系统推荐
 * 
 * @author Ruins7
 * @version V1.0
 * @Date 2014年10月3日 下午2:17:46
 */
public class UserTools {
	
	PersonalInfoImpl piImpl = new PersonalInfoImpl();

	// 对不同项目的通用的计算分数的函数
	public static float giveTheAvgScore(List<Integer> scores) {
		float result = 0f;
		for (int i = 0; i < scores.size(); i++) {
			result += i;
		}
		return result / scores.size();
	}

	//获取当前准确时间
	public Date getCurrentAccurateTime() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		return sdf.parse(time);
	}
	//获取当前年月日
	public Date getCurrentTimeYMD() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(new Date());
		return sdf.parse(time);
	}

	//判断类型，并且返回
	public <R> List<R> getLinkedListBaseOnFootStep(Map<String, LinkedList<Things>> map, R r) {
		//HttpSession session = request.getSession();
		List<R> system_recommend_itmes = new ArrayList<R>();
		// 从session中拿到当前登陆用户的6种浏览记录
		// .............其他5种
		if(r.getClass().getSimpleName().equalsIgnoreCase("Things")){
			Map<String, LinkedList<Things>> cookiemap_things = map;
					//(Map<String, LinkedList<Things>>) session.getAttribute("ThingslinkedList");
			LinkedList<Things> things_list = cookiemap_things.get("123456");
			//最终要得到的系统推荐的项目的集合
			try {
				System.out.println("getLinkedListBaseOnFootStep之后:"+things_list.size());
				system_recommend_itmes = (List<R>) culculateFlavorBaseOnFootStep(things_list);
				return system_recommend_itmes;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return system_recommend_itmes;

	}

	// 最终返回系统推荐的对象集合
	private <R> List<R> culculateFlavorBaseOnFootStep(LinkedList<R> r_list)
			throws Exception {
		System.out.println("r_list:"+r_list.size());
		// 用来存放用户浏览记录数据（得到浏览的type）
		List<Integer> footstep_id = new ArrayList<Integer>();
		for (Iterator iterator = r_list.iterator(); iterator.hasNext();) {
			R r = (R) iterator.next();
			Field[] field = r.getClass().getDeclaredFields();
			for (Field d : field) {
				System.out.println(d.getName());
				d.setAccessible(true);
				if (d.getName().equalsIgnoreCase("type")) {
					System.out.println("。。。。。。。");
					System.out.println(d.get(r));
					footstep_id.add((Integer) d.get(r));
				}			 
			}
		}
		System.out.println("culculateFlavorBaseOnFootStep之后:"+footstep_id.size());
		// 将footstep_id抛入下一个算法，求出用户的访问数量(排序算法)
		List<R> list = (List<R>) howManyTimesUserInterview(footstep_id, new Things());
		//在这返回最终推荐的结果
		return list;
	}

	private <R> List<R> howManyTimesUserInterview(List<Integer> footstep_id, R r) {
	    //得到用户浏览次数最多的项目的排序（从多到少）
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		List<Map<Integer, Integer>> list = new ArrayList<Map<Integer, Integer>>();
		// 去重
		Iterator<Integer> uniqueIterator = new UniqueFilterIterator(footstep_id.iterator());
		while (uniqueIterator.hasNext()) {
			int i = uniqueIterator.next();
			int num = 0;
			for (Integer integer : footstep_id) {
				if (i == integer) {
					num += 1;
				}
			}
			map.put(i, num); 
		}
		//根据value对Map排序
		list = sortMapByValue(map);
		System.out.println("sortMapByValue之后:"+list.size());
		//根据算法求出的用户浏览次数最多的前5个类型为用户推荐(调用DB)
		List<R> list_l = new ArrayList<R>();
		if(list.size() > 5){
			list_l = (List<R>) piImpl.searchDBBaseOnAlgorithm(list.subList(0, 5), new Things());
		}else{
			list_l = (List<R>) piImpl.searchDBBaseOnAlgorithm(list, new Things());
		}
		return list_l;
		
	}
	
	 private List<Map<Integer, Integer>> sortMapByValue(Map<Integer, Integer> map){
		 
	        //这里将map.entrySet()转换成list
	        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(map.entrySet());
	        //然后通过比较器来实现排序
	        Collections.sort(list,new Comparator<Map.Entry<Integer,Integer>>() {
	            //降序排序
				@Override
				public int compare(java.util.Map.Entry<Integer, Integer> o1,
						java.util.Map.Entry<Integer, Integer> o2) {
					// TODO Auto-generated method stub
					   return o2.getValue().compareTo(o1.getValue());
				}
	        });
	        List<Map<Integer, Integer>> list_l = new ArrayList<Map<Integer, Integer>>();
	        for(Map.Entry<Integer,Integer> mapping:list){
	               System.out.println(mapping.getKey()+":"+mapping.getValue());
	               Map<Integer, Integer> map1 = new TreeMap<Integer, Integer>();
	               map1.put(mapping.getKey(), mapping.getValue());
	               list_l.add(map1);
	          }
			return list_l;
	 }
}
