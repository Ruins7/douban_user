<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>

<head>
<meta charset="utf-8">
<title>申请曲奇小组</title>
<link rel="icon" href="lsr/img/quqi.ico">
<link rel="stylesheet" href="nxt/css/header.css" >
<link rel="stylesheet" href="nxt/css/main_nav.css">
<link rel="stylesheet" type="text/css" href="nxt/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="nxt/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="nxt/css/font-awesome.min.css">
<script src="nxt/js/jquery.min.js"></script>
<script src="nxt/js/bootstrap.min.js"></script>
<script src="nxt/js/jquery-2.1.1.js"></script>

</head>



<div id="header">
	<div id="header_left_div">
    	<ul id="header_left_ul">
        	<li><a href="<%=basePath%>user/userinfo?method=searchFriendsBroByPage&user_id=${sessionScope.current_user.user_id}">曲奇</a></li>
			<li><a href="<%=basePath%>douban_read.jsp">读书</a></li>
			<li><a href="<%=basePath%>servlet/movieServlet?method=allfindMovies">电影</a></li> 
			<li><a href="<%=basePath%>hyy/douban_city.jsp">同城</a></li>
			<li><a href="<%=basePath%>things/thingsinfo?method=searchThingsByPage&typepage=ul&user_id=${sessionScope.current_user.user_id}">东西</a></li>
            <c:if test="${! empty sessionScope.current_user }">
            <li><a href="<%=basePath %>groupServlet?method=loginGroup">小组</a></li>
             </c:if>
            <c:if test="${empty sessionScope.current_user }"> 
                <li><a href="groupServlet?method=queryAllPostsByHot">小组</a></li>
             </c:if>
             			<li><a href="#">更多</a></li>
        </ul>
    </div>
    <!--end of header_left-->
    <div id="header_right_div">
    <c:if test="${! empty sessionScope.current_user }">
    	<ul id="header_right_ul">
        	<li><a href="">提醒</a></li>
            <li><a href="">豆邮</a></li>
             <li><a href="">${sessionScope.current_user.username}的账号</a></li>
            
        </ul>
    </c:if>
    <c:if test="${empty sessionScope.current_user }">
    	<ul id="header_right_ul">
        	<li><a href="/douban_user/lsr/douban_login.jsp">登录</a></li>
            <li><a href="">注册</a></li>
        </ul>
    </c:if>
    </div>
    <!--end of header_right-->
</div>
<!--end of header-->
<div id="main_nav">
	<div id="group_logo">
    曲奇小组
    </div>
    <!--end of main_nav's group_logo-->
    <div id="classify_search">
      	<c:if test="${! empty sessionScope.current_user }">
    		<a href="<%=basePath %>groupServlet?method=queryMyPosts">我的小组</a>
    	</c:if>
        <a href="<%=basePath %>groupServlet?method=queryAllPostsByHot">精选</a>
        <a href="<%=basePath %>groupServlet?method=queryPostByCondition&&cond=culture">文化</a>
        <a href="<%=basePath %>groupServlet?method=queryPostByCondition&&cond=photo">行摄</a>
        <a href="<%=basePath %>groupServlet?method=queryPostByCondition&&cond=fun">娱乐</a>
        <a href="<%=basePath %>groupServlet?method=queryPostByCondition&&cond=fashion">时尚</a>
        <a href="<%=basePath %>groupServlet?method=queryPostByCondition&&cond=life">生活</a>
        <a href="<%=basePath %>groupServlet?method=queryPostByCondition&&cond=tech">科技</a>
    </div>
    <!--end of classify_search-->
    <div id="global_search">
    	<input type="text" name="name" id="input_search"  maxlength="25" placeholder="小组、话题"/>
        <button type="button" id="button_search">
  			<span class="glyphicon glyphicon-search"></span>
		</button>
    </div>
    <!--end of global_search-->
</div>
<!--end of main_nav-->




  

