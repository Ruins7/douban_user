<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>我回应过的话题</title>
<base href="<%=basePath%>">
<link rel="icon" href="nxt/images/favicon_16x16.png">
<link rel="stylesheet" href="nxt/css/header.css" >
<link rel="stylesheet" href="nxt/css/main_nav.css">
<link rel="stylesheet" type="text/css" href="nxt/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="nxt/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="nxt/css/personal_group_leftside.css">
<link rel="stylesheet" type="text/css" href="nxt/css/personal_group_rightside.css">
<script src="nxt/js/jquery.min.js"></script>
<script src="nxt/js/bootstrap.min.js"></script>
<script src="nxt/js/jquery-2.1.1.js"></script>
</head>


<body>
 <jsp:include page="douban_group_head.jsp"></jsp:include>

<div id="personal_group_leftside">
	<div id="mygroup">我的曲奇小组</div><br>
    <!--end of mygroup-->
    <div id="mygroup_nav">
    	<a href="groupServlet?method=aheadMyHomepage"   style="background-color: #fff;color: #0E959D;">小组主页</a>
        <a href="groupServlet?method=queryMyGroupByRole">加入的小组</a>
        <a href="groupServlet?method=queryUserPub&&user_id=${sessionScope.current_user.user_id}">发起</a>
        <a href="groupServlet?method=queryUserReply&&user_id=${sessionScope.current_user.user_id}"  style="background-color: #6EBFC3;border-radius:3px;color: #fff;">回应</a>
    </div><hr><br>
    <!--end of mygroup_nav-->
    
  
    <div id="space">回应的话题</div><hr>
    	<div class="mod_topic">
        	<table class="olt">
        	<tr class="pl">
        			<td class="title">
						标题
        			</td>
        			<td class="td-reply">作者</td>
        
        			<td class="td-time">发布时间</td>
        			<td class="td-group">
         				<a href="">阅读</a>
        			</td>
      			</tr>
    			<c:forEach items="${requestScope.reply_posts }" var="post">
    			<tr class="pl">
        			<td class="title">
          				<a href="groupServlet?method=queryOnePost&&post_id=${post.id }" title="${post.post_title}">${post.post_title}</a>
        			</td>
        			<td class="td-reply">${post.post_author}</td>
        
        			<td class="td-time">${post.post_pubtime} </td>
        			<td class="td-group">
         				<a href="">${post.readcount}</a>
        			</td>
      			</tr>
    		</c:forEach>
    
      			
			</table>
    	</div><!--end of topic1--><br>
  
</div>
<!--end of personal_group_leftside-->
<div id="personal_group_rightside">
	<div id="user_card">
    	<div id="user_pic"><img src="<%=basePath%>/nxt/images/ads/user_normal.jpg" class="img-circle"></div>
        <div id="user_info">
        	<div>哈密瓜</div>
            <div id="living">常居：天津</div>
            <div id="homelink"><a href=""><img src="<%=basePath%>/nxt/images/favicon_16x16.png"> 曲奇主页</a></div>
        </div>
    </div><br>
    <!--end of usercard-->
   
    
    
</div><!--end of rightside-->
</body>
</html>
