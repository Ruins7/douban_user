/**
 * 
 */
package com.commontools.lsr;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.lsr.user.daoImpl.PersonalInfoImpl;
import com.entity.lsr.book.Book;
import com.entity.lsr.things.Things;

/**
 * @ClassName:     UserFootStep.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 *                 用户浏览记录
 *                 音乐，电影，读书，同城活动，小组，东西
 * 
 * @author         Ruins7
 * @version        V1.0  
 * @Date           2014年10月9日 下午7:19:18 
 */
public class UserFootStep<T extends Serializable> {
	
	public void readFootStepFromCookie(HttpServletRequest request) {
		 //得到用户cookie
		 HttpSession session = request.getSession();
		 Cookie[] cookies= request.getCookies();
		 if(cookies != null){//有cookies
				int thing = 0;
				int moive = 0;
				
				for (Cookie cookie : cookies) {
					//浏览过的东西
					if("user_things_footstep".equalsIgnoreCase(cookie.getName())){
						//拿到cookie读入LikedList
						Map<String , LinkedList<Things>> cookiemap = createLinkedListForCookie(cookie, session.getId(), new Things());
						session.setAttribute("ThingslinkedList", cookiemap);			 
						thing += 1;						
					}
					//浏览过的电影
					if("user_books_footstep".equalsIgnoreCase(cookie.getName())){						 
						//拿到cookie读入LikedList
						Map<String , LinkedList<Book>> cookiemap = createLinkedListForCookie(cookie, session.getId(), new Book());
						session.setAttribute("BookslinkedList", cookiemap);	
						moive += 1;						 
					}
					
					//..........................................
					
				}
				if(thing == 0){//没有找到  user_things_footstep---- cookie
				   //创建一个LikedList来暂存cookie数据，当用户退出(session消失时)写回cookie
					Map<String , LinkedList<Things>> cookiemap = createLinkedListForCookie(null, session.getId(), new Things());
					session.setAttribute("ThingslinkedList", cookiemap);	
				}
				if(moive == 0){//没有找到  user_moives_footstep---- cookie
					//创建一个LikedList来暂存cookie数据，当用户退出(session消失时)写回cookie
					Map<String , LinkedList<Book>> cookiemap = createLinkedListForCookie(null, session.getId(), new Book());
					session.setAttribute("BookslinkedList", cookiemap);	
				}
				
				//.......................................
				
		 }else{
		  //没有找到任何cookie,创建全部cookie镜像
		  Map<String , LinkedList<Things>> cookiemap = createLinkedListForCookie(null, session.getId(), new Things());
		  session.setAttribute("ThingslinkedList", cookiemap);			 
		  Map<String , LinkedList<Book>> cookiemap_1 = createLinkedListForCookie(null, session.getId(), new Book());
		  session.setAttribute("BookslinkedList", cookiemap_1);			 
		 }
		 
		 //...............................................
		 
	}
	
	//obj为当前访问的事物对象
	public void changeLinkedListInSession(HttpSession session, Object obj ){ 
		if(obj.getClass().equals("Book")){
			 Map<String , LinkedList<Book>> cookiemap = (Map<String, LinkedList<Book>>) session.getAttribute("BookslinkedList");
			 LinkedList<Book> list = insertFootStep((Book)obj, cookiemap.get(session.getId()));
			 cookiemap.clear();
			 cookiemap.put(session.getId(), list);
			 session.setAttribute("BookslinkedList", cookiemap);		
		}
		
		//..............................其他类型的
	}
	
	private <T> LinkedList<T> insertFootStep(T t, LinkedList<T> linkedlist){
		//用户记录数
		final int capacity = 5;
		//list操作用迭代器！！！！ 		
		//先判断是否重复	
		T repeat = null;
		T norepeat = null;
		if(linkedlist.size() > 0){
			for (T u : linkedlist) {	
				if(t.equals(u)){//需要重写equals方法
					//System.out.println(user.getId()+"重复了");
					repeat = t;					 
				}else{
					//System.out.println(user.getId()+"不重复");
					norepeat = t;
				}
			}
			//删除重复的
			if(repeat != null){
				linkedlist.remove(repeat);				 
			}
			//判断容量
			if(linkedlist.size() >= capacity){
				linkedlist.remove(0);
				//最后添加重复和不重复的
				linkedlist.add(norepeat);
			}else{//<
				linkedlist.add(t);		 
		}		
		}else{
			linkedlist.add(t);
		}
		return linkedlist;
	}

    //当session消失时将内存中的Linkedlist写回cookie
	public void writeFootStepToCookie(HttpServletRequest request, HttpServletResponse respons){
		
		HttpSession session = request.getSession();
		Map<String , LinkedList<Book>> map_book = (Map<String, LinkedList<Book>>) session.getAttribute("BookslinkedList");
		Map<String , LinkedList<Things>> map_thing = (Map<String, LinkedList<Things>>) session.getAttribute("ThingslinkedList");
		//..................拿到其他的用户记录(里面都是id)
		
		 Cookie[] cookies= request.getCookies();
		 if(cookies != null){//有cookies
				int thing = 0;
				int moive = 0;
				
				for (Cookie cookie : cookies) {
					//浏览过的东西
					if("user_things_footstep".equalsIgnoreCase(cookie.getName())){
						//拿到map写回cookie
						cookie = createCookieForLinkedList(cookie, map_thing, session.getId());
						respons.addCookie(cookie);
						thing += 1;				 
					}
					//浏览过的电影
					if("user_books_footstep".equalsIgnoreCase(cookie.getName())){						 
						//拿到LikedList写回cookie
						cookie = createCookieForLinkedList(cookie, map_book, session.getId());
						respons.addCookie(cookie);
						moive += 1;		 
					}
					
					//...........................
					
				}
				if(thing == 0){//没有找到  user_things_footstep---- cookie
				   //创建一个cookie来保存LinkedList
				   Cookie cookie = createCookieForLinkedList(null, map_thing, session.getId());
				   respons.addCookie(cookie);
				}
				if(moive == 0){//没有找到  user_moives_footstep---- cookie
					//创建一个cookie来保存LinkedList
					Cookie cookie = createCookieForLinkedList(null, map_book, session.getId());
					respons.addCookie(cookie);
				}
				
				//...........................................
				
		 }else{
		  //没有找到任何cookie,创建全部cookie镜像
		  Map<String , LinkedList<Things>> cookiemap = createLinkedListForCookie(null, session.getId(), new Things());
		  session.setAttribute("ThingslinkedList", cookiemap);			 
		  Map<String , LinkedList<Book>> cookiemap_1 = createLinkedListForCookie(null, session.getId(), new Book());
		  session.setAttribute("BookslinkedList", cookiemap_1);			 
		  
		  //.........其他对象的cookie.............
		 
		 }		
		
	}
	
	//创建LinkedList来在内存中存储cookie的镜像
	private <R> Map<String , LinkedList<R>> createLinkedListForCookie(Cookie cookie, String sessionid, R r){
		String[] cookie_value = null;
		LinkedList<String> list_id = new LinkedList<String>(); 
		if(cookie != null){
			cookie_value = cookie.getValue().split(",");//拿到的是对象的id
			//将cookie_value中的值存入list
			for (int i = 0; i < cookie_value.length; i++) {
				String id = cookie_value[i];
				list_id.add(id);
			}
		}else{
			list_id = null;
		}
		//根据R类型不同调用DAO层，填充对象
		LinkedList<R> list_obj = new LinkedList<R>();
		if(list_id != null){
			list_obj = fillUpObjectFromCookie(list_id,r);
		}else{
			list_obj = null;
		}
		Map<String , LinkedList<R>> cookiemap = new HashMap<String , LinkedList<R>>();
		cookiemap.put(sessionid, list_obj);
		return cookiemap;
	}
	
	private <R> Cookie createCookieForLinkedList(Cookie cookie, Map<String , LinkedList<R>> map, String sessionid){
		LinkedList<R> list_obj = new LinkedList<R>();
		list_obj = map.get(sessionid);
		String value = null;
		   for (Iterator iterator = list_obj.iterator(); iterator.hasNext();) {
			R obj = (R) iterator.next();
			value += (obj.toString()+",");//需要重写toString方法，使之返回该对象的id
	   	   }
		   if(cookie != null){//有cookie
			  cookie.setValue(value);		  	   
		   }else{
			   String cookie_name = null;
			   if(list_obj.get(0).getClass() == Things.class){
				   cookie_name = "user_things_footstep";
			   }
			   if(list_obj.get(0).getClass() == Book.class){
				   cookie_name = "user_books_footstep";
			   }
			   //判断类型来创建cookie的名字
			   //。。。。。。。。。。。。。。。。。。。
			   
			   
			  cookie = new Cookie(cookie_name, value);
		   }  
		   cookie.setMaxAge(60*60*24*30);//有效期1个月
		   return cookie;
	}
	
	private <R> LinkedList<R> fillUpObjectFromCookie(LinkedList<String> list_id, R r){
		LinkedList<R> list_obj = new LinkedList<R>();
		for (Iterator iterator = list_id.iterator(); iterator.hasNext();) {
			String id = (String) iterator.next();
			PersonalInfoImpl pi = new PersonalInfoImpl();
			R obj = pi.showOneByR(id, r);
			list_obj.add(obj);
		}
		return list_obj;	
	}
}
