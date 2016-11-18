<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ActivityManager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

  </head>
  
  <body>
  <h3>线上活动</h3>
   	<a href="ActivityManageServlet">增加线上活动</a>   	
   	<form action="ActivityManageServlet">
    	    活动名称:<input type="text" name="onlineActivity_title"><br>
   		 活动开始时间：<input type="text" name="start_time">截止时间:<input type="text" name="end_time"><br>
  		 活动的描述:<input type="text" name="onlineactivity_desc"><br>
  		
		   活动的大类型:<input type="text" name="onlineActivity_type"><br>
		    活动的照片地址:<input type="text" name="pictures"><br>
		   <input type="hidden" value="addOnlineActivity" name="method">
		  <input type="submit" value="提交"><input type="reset" value="重置">
    </form>
   	<a href="ActivityManageServlet?method=searchOnlineActivity">查看所有线上活动</a>
   	<hr>
   <h3>线下活动</h3>
   	<a href="ActivityManageServlet?method=addOfflineActivity">增加线下活动</a>
   	
   	<form action="ActivityManageServlet">
    	    活动名称:<input type="text" name="offactivity_title"><br>
   		 活动开始时间：<input type="text" name="start_time">截止时间:<input type="text" name="end_time"><br>
    	活动的国家:<input type="text" name="country">
 		  活动的城市:<input type="text" name="city"><br>
  		 活动的描述:<input type="text" name="offactivity_desc"><br>
  		 活动的照片地址:<input type="text" name="pictures"><br>
		   活动的大类型:<input type="text" name="offactivity_bigtype"><br>
		  活动的小类型:<input type="text" name="offActivity_smalltype"><br>
		  <input type="hidden" value="addOfflineActivity" name="method"> 
		  <input type="submit" value="提交"><input type="reset" value="重置">
    </form>
   	<a href="ActivityManageServlet?method=searchOffActivity">查看所有线下活动</a>
  </body>
</html>
