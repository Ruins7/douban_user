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
    
    <title>My JSP 'allComment.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

  </head>
  
  <body>
   	<c:forEach items="${OfflineAskActivityList }" var="ask">
   		${ask.ask_id }<br>
   		<a href="#">${ask.user_from.userName }</a>
   		对
   		<a href="#">${ask.user_to.userName }</a>
   		说
   		<br>
   		${ask.ask_comment }
   		<hr>
   	</c:forEach>
  </body>
</html>
