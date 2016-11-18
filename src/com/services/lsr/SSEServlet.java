package com.services.lsr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.lsr.user.User_Leave_Message;

@WebServlet(name = "sse", urlPatterns = { "/servlet/sse" })
public class SSEServlet extends HttpServlet {

	private static final long serialVersionUID = 6221249583672404522L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		// 这里必须设置 Content-Type 为 text/event-stream
		response.setHeader("Content-Type", "text/event-stream");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("utf-8");
 
		HttpSession session = request.getSession();
		User_Leave_Message new_message = (User_Leave_Message) session.getAttribute("new_leave_message");
		if(new_message != null){
			out.println("date:" + "您的好友给您留言了！去看看吧！");
			//data:value
			out.println("data:信息id:"+new_message.getMessage_id());		 
			out.println();
			out.flush();
		}
			
		}
	

}
