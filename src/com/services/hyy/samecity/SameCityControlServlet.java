package com.services.hyy.samecity;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 进入同城页面前的控制页面
 * @author 胡伊杨
 *
 */
@WebServlet(
		name="SameCityControlServlet",
		urlPatterns={"/hyy/service/SameCityControlServlet"}
		)
public class SameCityControlServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if("".equals(method)){
			
		}
	}

}
