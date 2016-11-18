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
    
    <title>My JSP 'myDouMailList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">


  </head>
  
  <body>
  	已经进入了我的豆邮页面<br>
  	
	<c:forEach items="${requestScope.list }" var="comment">
		<c:set var="user_to" value="${comment.userMail.user_id_to.user_id }"></c:set>
		<c:if test="${sessionScope.me.user_id==comment.userMail.user_id_to.user_id }">
			<c:set var="user_to" value="${comment.userMail.user_id_from.user_id }"></c:set>
		</c:if>
		<fmt:formatDate value="${comment.comment_time }" pattern="yyyy年MM月dd日  HH:mm:ss E"/><br>
			<a href="DouMailServlet?method=meToOne&myId=${sessionScope.me.user_id }&other=${user_to}  ">${comment.comment_content }<br></a>		
		${comment.userMail.user_id_to.user_id }
		<a href="DouMailServlet?method=deleteOther&my_id=${sessionScope.me.user_id }&other_id=${user_to}&um_id=${comment.userMail.userMail_id}">删除</a>
		<a href="DouMailServlet?method=saveMailComment?my_id=&my_id=${sessionScope.me.user_id }&other_id=${user_to}&um_id=${comment.userMail.userMail_id}">发送</a>
		<hr>
	</c:forEach>
  	
  </body>
</html>
