<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>My JSP 'aOnActivity.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">


  </head>
  
  <body>
  活动信息:<hr>
        id: ${onlineActivity.onlineActivity_id }
    标题:${onlineActivity.onlineActivity_id }
    开始时间:<fmt:formatDate value="${onlineActivity.start_time }" pattern="yyyy年MM月dd日  HH:mm:ss E"/>
    结束时间:<fmt:formatDate value="${onlineActivity.end_time }" pattern="yyyy年MM月dd日  HH:mm:ss E"/>
    相册:${onlineActivity.album.activityAlbum_id }
    创办者:${ onlineActivity.user.userName}
  <hr>
  <form action="ActivityManageServlet">
 <input type="hidden" value="updateOnActivity" name="method">
  <input type="hidden" value="${onlineActivity.onlineActivity_id }" name="onlineActivity_id">
     标题: <input type="text" value="${onlineActivity.onlineActivity_title }" name="onlineActivity_title"> 
     <br>
    开始时间:<input type="datetime" value="${onlineActivity.start_time }" name="start_time"><br>
  结束时间:<input type="datetime" value="${onlineActivity.end_time }" name="end_time"><br>
描述:<input type="text" value="${onlineActivity.onlineactivity_desc }" name="onlineactivity_desc">
类型:<input type="text" value="${onlineActivity.onlineActivity_type }" name="onlineActivity_type">
状态:<input type="text" value="${onlineActivity.onlineactivity_statue }" name="onlineactivity_statue">
    
    <input type="submit" value="提交"><input type="reset" value="重置">
  </form>

    <br>
    
  </body>
</html>
