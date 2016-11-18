<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>线下活动</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<link href="hyy/css/douban_city.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
   	 <jsp:include page="/hyy/activity/douban_activityhead.jsp"></jsp:include>
   	 <div style="background-color: #F6F5F2;float: left;width: 640px;margin-left: 200px;margin-top: 30px;border-radius:20px;border:1px solid #ccc;box-shadow: 5px 5px 5px #ccc;padding: 20px;margin-bottom: 30px;">
   	 <p style="color: #664433;font-size: 22px;font-weight: 700;">关于  ${typetype.type_name } 的活动</p>
   	 
   	 <c:forEach var="off" items="${off_act }">
   	 <div style="float: left;width: 600px;height: auto;margin-bottom: 30px;">
   	     <img src="<%=basePath%>${off.imgs}" width="120px" height="170px" style="float: left;margin-right: 30px;">
   	     <p style="font-size: 16px;color: #664433;margin-top: 20px;font-weight: 700;">${off.offactivity_title }</p>
   	     <p style="font-size: 12px;color: #666;">时间：<fmt:formatDate value="${off.start_time }" pattern="yyyy年MM月dd日 HH:mm:ss"/>---<fmt:formatDate value="${off.end_time }" pattern="yyyy年MM月dd日 HH:mm:ss"/></p>
   	     <p style="font-size: 12px;color: #666;">地点：${off.position }</p>
   	     <p style="font-size: 12px;color: #666;">发起人：${off.user.username }</p>
   	     <a href="<%=basePath%>hyy/service/ActivityServlet?method=searchOneOffActivity&activity_id=${off.offactivity_id}" style="width: auto;"><p style="font-size: 12px;color: #666;">详情>></p></a>
   	     </div>
   	 </c:forEach>
   	 </div>
   	 <div style="float: left; width: 300px;height:auto;margin-left: 60px;margin-top: 40px;min-height: 150px">
  	      <p style="color: #664433;font-size: 14px;margin-bottom: 20px;">发起这些活动的人</p>
  	     <c:forEach var="user" items="${faqiusers}">
  	         <a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${user.user_id }"><img src="<%=basePath%>${user.imgs}" width="50px" height="50px" style="border-radius:25px;margin: 5px;"></a>
  	     </c:forEach>
  	 </div>
  </body>
</html>
