package com.services.hyy.support;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

public class OfflineActivitySupport {
	public Object[] getOfflineActivityParams(HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		
		return null;
	}
}
