package com.listener.lsr;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.dao.lsr.user.daoImpl.PersonalInfoImpl;
import com.entity.hyy.city.City;

@WebListener
public class CityAppListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("app_listener销毁了");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("app_listener创建了");
		PersonalInfoImpl p = new PersonalInfoImpl();
		 List<City> cities = p.searchCity();
		event.getServletContext().setAttribute("city", cities);
		 
	}

}
