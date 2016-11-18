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
    
    <title>My JSP 'getAllOffActivity.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

  </head>
  
  <body>
   	<c:forEach items="${list }" var="offActivity">
   		${offActivity.offactivity_id }
   		${offActivity.offactivity_title }
   		<a href="ActivityManageServlet?method=searchAOffActivity&id=${offActivity.offactivity_id }">查看</a>
   		<a href="ActivityManageServlet?method=deleteOff&id=${offActivity.offactivity_id }">删除</a>
   		<br>
   	</c:forEach>
  </body>
</html>
