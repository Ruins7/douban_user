<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <script src="<%=basePath%>lsr/js/jquery-2.1.1.js"></script>
	 <script src="<%=basePath%>lsr/js/test.js"></script>
   
  </head>
  
  <body>
   <button onclick="workerhandler()">Async Invoke</button>
   <hr>
   <fieldset>
   <legend>好友留言</legend>
      <form action="user/userinfo?method=sendLeaveMessage" method="post">
           <input type="hidden" name="to_user" value="2">
           <textarea rows="3" cols="40" name="content"></textarea>
           <input type="submit" value="留言">
      </form>
   </fieldset>
   <button onclick="getSSEData()">receive server send event message</button>
  	<button onclick="cleardata()">clear html</button>
  	<button onclick="stopSSE()">cancel sse event</button>
  	
  	<div id="loading"></div>
  	<hr>
  	<div id="msg"></div>
  </body>
</html>
