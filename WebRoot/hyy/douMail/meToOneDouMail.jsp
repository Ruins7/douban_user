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
    
    <title>My JSP 'meToOneDouMail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  </head>
  	
  <body>
    <c:forEach items="${requestScope.mailCommentList }" var="comment">
  		${comment.comment_id }<br>
  		<fmt:formatDate value="${comment.comment_time }" pattern="yyyy年MM月dd日  HH:mm:ss E"/><br>
  		<c:out value="${comment.link==null||comment.link=='' }"></c:out>
  		<c:if test="${comment.link==null||comment.link=='' }">
  			${comment.comment_content }<br>  			
  		</c:if>
  		<c:if test="${not empty comment.link }">
  			<a href="ActivityServlet?method=findEntity&mailComment_id=${comment.comment_id }">${comment.comment_content }</a>
  		</c:if>
  		
  		发送者：${comment.send_user_id.userName}<br>
  		接受者：${comment.recive_user_id.userName} 
  		<a href="DouMailServlet?method=deleteMailComment&mailComment_id=${comment.comment_id }">删除</a>
  		<hr>
  	</c:forEach>
  </body>
</html>
