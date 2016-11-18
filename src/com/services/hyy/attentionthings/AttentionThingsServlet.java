package com.services.hyy.attentionthings;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.hyy.attentionThings.AttentionThingsDao;
import com.dao.hyy.attentionThings.impl.AttentionThingsDaoImpl;
import com.entity.lsr.user.User_Info;
/**
 * 关注
 * @author 胡伊杨
 *
 */
@WebServlet(
		name="AttentionThingsServlet",
		urlPatterns={"/hyy/service/AttentionThingsServlet"}
		)
public class AttentionThingsServlet extends HttpServlet {
	String table_name;
	int inTable_id;
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		table_name = request.getParameter("table_name");
		inTable_id = Integer.parseInt(request.getParameter("inTable_id"));
		
		HttpSession session = request.getSession();
		User_Info user = (User_Info)session.getAttribute("current_user");
		
		if("add".equals(method)){
			add(request,response);
		}else if("cancle".equals(method)){
			cancle(request,response);
		}
	}
	
	/**
	 * 加关注
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			AttentionThingsDao atd = new AttentionThingsDaoImpl();
			HttpSession session = request.getSession();
			User_Info user = (User_Info)session.getAttribute("current_user");
			int affectRows = atd.attentionThings(user, table_name, inTable_id, "insert");
			if(affectRows!=0){
				System.out.println("��ϲ,��ע�ɹ�");
			}else{
				System.out.println("��Ǹ,��עʧ��");
			}
	}
	
	/**
	 * 取消关注
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void cancle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			AttentionThingsDao atd = new AttentionThingsDaoImpl();
			HttpSession session = request.getSession();
			User_Info user = (User_Info)session.getAttribute("current_user");
			int affectRows = atd.attentionThings(user, table_name, inTable_id, "delete");
			if(affectRows!=0){
				System.out.println("��ϲ,ȡ���ע�ɹ�");
			}else{
				System.out.println("��Ǹ,ȡ���עʧ��");
			}
	}
}
