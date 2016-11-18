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
<title>喜欢</title>
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
              ${current_user.username}的喜欢
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
  <!-- <ul>
     <li>全部</li>
     <li>日记</li>
     <li>相册</li>
     <li>照片</li>
     <li>活动</li>
     <li>话题</li>
     <li>东西</li>
  </ul> -->
  <div id="friends">
  <c:forEach var="like" items="${requestScope.mylike }">
           <c:if test="${like.item_name=='书籍'}">
		    <div style="width: 600px;height: auto;margin-bottom: 20px;margin-top: 20px;">
                <p style="width:600px;float:left;background-color: #F2FBF2;color:#3377AA;border-radius:5px;text-indent: 2em;">[书籍]:  ${like.book.book_name } 
                <span style="float: right;margin-right: 20px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd HH:mm:ss"/></span>         
                </p>
                <img alt="" src="<%=basePath%>${like.book.book_name.imgs}" width="auto" height="auto" style="max-height: 200px;max-width: 200px;">
           </div>  
		   </c:if>
		   <c:if test="${like.item_name=='电影'}">
		    <div style="width: 600px;height: auto;margin-bottom: 20px;margin-top: 20px;">
                <p style="width:600px;float:left;background-color: #F2FBF2;color:#3377AA;border-radius:5px;text-indent: 2em;">[电影]:  ${like.movie.m_name } 
                <span style="float: right;margin-right: 20px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd HH:mm:ss"/></span>         
                </p>
                <img alt="" src="<%=basePath%>${like.moive.imgs}" width="auto" height="auto" style="max-height: 200px;max-width: 200px;">
           </div>  
		   </c:if>
		   <c:if test="${like.item_name=='东西'}">
		   <div style="width: 600px;height: auto;margin-bottom: 20px;margin-top: 20px;">
                <p style="width:600px;float:left;background-color: #F2FBF2;color:#3377AA;border-radius:5px;text-indent: 2em;">[东西]:  ${like.thing.things_name } 
                <span style="float: right;margin-right: 20px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd HH:mm:ss"/></span>         
                </p>
                <img alt="" src="<%=basePath%>${like.thing.imgs}" width="auto" height="auto" style="max-height: 200px;max-width: 200px;">
           </div>  	 	
		   </c:if>
		   <c:if test="${like.item_name=='日志'}">
		   <div style="width: 600px;height: auto;margin-bottom: 20px;margin-top: 20px;">
                  <p style="width:600px;float:left;background-color: #F2FBF2;color:#3377AA;border-radius:5px;text-indent: 2em;">${like.diary.user_info.username } 的[日志]:  ${like.diary.title }
                  <span style="float: right;margin-right: 20px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd HH:mm:ss"/></span> 
                  </p>        
           </div>  
		   </c:if>
		   <c:if test="${like.item_name=='相册'}">
		   <div style="width: 600px;height: auto;margin-bottom: 20px;margin-top: 20px;">
                 <p style="width:600px;float:left;background-color: #F2FBF2;color:#3377AA;border-radius:5px;text-indent: 2em;">${like.album.user_info.username } 的[相册]:  ${like.album.album_name }
                  <span style="float: right;margin-right: 20px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                 </p>
                 <a href="<%=basePath%>user/userinfo?method=showOneAlbum&album_id=${like.album.album_id}">
                     <img src="<%=basePath%>${like.album.imgs}" width="auto" height="auto" style="max-height: 200px;max-width: 200px;"><br>
                  </a>
           </div> 
		   </c:if>
		   <c:if test="${like.item_name=='线下活动'}">
		   <div style="width: 600px;height: auto;margin-bottom: 20px;margin-top: 20px;">
                 <p style="width:600px;float:left;background-color: #F2FBF2;color:#3377AA;border-radius:5px;text-indent: 2em;">${like.activity.user.username } 举办的[活动]:  ${like.activity.offactivity_title } 
                   <span style="float: right;margin-right: 20px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                 </p>
                 <a href="<%=basePath%>user/userinfo?method=${like.activity.onlineActivity_id}">
                    <img src="<%=basePath%>${like.activity.album.photo.activityPhoto_position}" width="auto" height="auto" style="max-height: 200px;max-width: 200px;"><br>
                 </a>
            </div>   
		   </c:if>
		   <c:if test="${like.item_name=='照片'}">
		   <div style="width: 600px;height: auto;margin-bottom: 20px;margin-top: 20px;">
                  <p style="width:600px;float:left;background-color: #F2FBF2;color:#3377AA;border-radius:5px;text-indent: 2em;">${like.photo.album.user_info.username } 的[照片]:  ${like.photo.photo_name } 
                      <span style="float: right;margin-right: 20px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                  </p>
                  <a href="<%=basePath%>user/userinfo?method=${like.photo.photo_id}">
                      <img src="<%=basePath%>${like.photo.photo_address}" width="auto" height="auto" style="max-height: 200px;max-width: 200px;"><br>
                  </a>          
           </div> 
		   </c:if>
		   <c:if test="${like.item_name=='话题'}">
		   <div style="width: 600px;height: auto;margin-bottom: 20px;margin-top: 20px;">
                  <p style="width:600px;float:left;background-color: #F2FBF2;color: #3377AA;border-radius:5px;text-indent: 2em;">${like.topic.user_info.username } 发起的[话题]:  ${like.topic.post_title } 
                      <span style="float: right;margin-right: 20px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                  </p>
            </div>   
		   </c:if> 
		   <c:if test="${like.item_name=='广播'}">
		   <div style="width: 600px;height: auto;margin-bottom: 20px;margin-top: 20px;">
                  <p style="width:600px;float:left;background-color: #F2FBF2;color: #3377AA;border-radius:5px;text-indent: 2em;">${like.bro.user_info.username } 发表的[广播]:  <c:out value="${fn:substring(like.bro.content, 0, 10)}..." />
                      <span style="float: right;margin-right: 20px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                  </p>
            </div>  
		   </c:if> 
 </c:forEach> 	   
  </div><!-- friends --> 
</div><!-- left -->
<div id="right"><!--右边-->
</div><!--右边-->  
 <jsp:include page="/lsr/douban_foot.jsp"></jsp:include>
</body>

</html>

