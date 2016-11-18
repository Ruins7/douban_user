<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>日志</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="lsr/css/douban_loginAfter.css" rel="stylesheet"type="text/css">
<link href="lsr/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="lsr/css/bootstrap-theme.css" rel="stylesheet" type="text/css">
<link href="lsr/css/jquery-ui.css" rel="stylesheet">
<link href="lsr/css/douban_common.css" rel="stylesheet">
<link href="lsr/css/douban_myalbum.css" rel="stylesheet">
<script src="lsr/js/jquery-2.1.1.js"></script>
<script src="lsr/js/bootstrap.min.js"></script>
<script src="lsr/js/douban_loginAfter.js"></script>
<script src="lsr/js/jquery-ui.js"></script>
<script src="lsr/js/douban_myalbum.js"></script>
<script>
$(function(){
	 
	
});
</script>

</head>
<body>
  <jsp:include page="/lsr/douban_head.jsp"></jsp:include>
   
<div id="left">
  <div id="myfocuspeople">
      <img src="<%=basePath%>${current_user.imgs}" width="80px" height="80px">
              ${current_user.username}的日记
              <div style="height: 12px; width: 500px;">
    	        <ul id="left_menu">
                	<li><a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${requestScope.current_user.user_id}">${requestScope.current_user.username}的主页</a></li>
                    <li><a href="<%=basePath%>user/userinfo?method=searchMyBroByPage&user_id=${requestScope.current_user.user_id}&typepage=ul">广播</a></li>
                    <li><a href="<%=basePath%>user/userinfo?method=showUserAllAlbums&user_id=${requestScope.current_user.user_id}&typepage=ul">相册</a></li>
                    <li><a href="<%=basePath%>user/userinfo?method=showAllUserDiarys&user_id=${requestScope.current_user.user_id}&typepage=ul">日记</a></li>
                    <li><a href="<%=basePath%>user/userinfo?method=showAllUserLike&user_id=${requestScope.current_user.user_id}">喜欢</a></li>
                    <li><a href="<%=basePath%>doulist/doulistinfo?method=showMyDouList&user_id=${requestScope.current_user.user_id}">豆列</a></li>
                    <c:if test="${sessionScope.current_user.user_id == requestScope.current_user.user_id}">
                    <li><a href="<%=basePath%>user/sandmailnotice?method=showPersonalInfo&user_id=${requestScope.current_user.user_id}">设置</a></li>
                    </c:if>
                 </ul>
              </div>    
  </div><!-- myfocuspeople -->
  
  <hr style="width: 650px;float: left;">
  <div id="friends">
  <c:forEach var="diary" items="${requestScope.mydiary }">
		   <div style="width: 630px;height: auto;margin-bottom: 60px;">
                  <div style="margin-bottom:10px;width:630px;float:left;background-color: #F2FBF2;color: #fff;border-radius:5px;text-indent: 2em;">
                    <a href="<%=basePath%>user/userinfo?method=showOneDiary&user_id=${requestScope.current_user.user_id}&diary_id=${diary.diary_id}" style="float: left;">${diary.title }</a> 
                    <span style="color: #545652;margin-right:10px; float: right;"><fmt:formatDate value="${diary.time }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                  </div>
                  <pre style="float: left;width: 630px;height: auto;"><c:out value="${fn:substring(diary.content, 0, 300)}..." /> </pre>
            </div>  
	 	    <hr> 
 </c:forEach> 
		   
  </div><!-- friends -->
  
</div><!-- left -->
 
 
   <div id="right"><!--右边-->
    
   </div><!--右边-->
  
 <jsp:include page="/lsr/douban_foot.jsp"></jsp:include>
</body>

</html>

