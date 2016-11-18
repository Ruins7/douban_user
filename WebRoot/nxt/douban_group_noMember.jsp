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
<title>douban group personal</title>
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
	<div id="mygroup">小组管理</div><br>
    <!--end of mygroup-->
    
    
    <div id="joins">
    	暂无成员！
    </div><!--end of joins-->
    <div id="null_div"></div><!--空div ，让下一个div另起一行--><br><br>
    
   
</div>
<!--end of personal_group_leftside-->

<div id="personal_group_rightside">
	<div id="user_card">
    	<div id="user_pic"><img src="images/ads/user_normal.jpg" class="img-circle"></div>
        <div id="user_info">
        	<div>哈密瓜</div>
            <div id="living">常居：天津</div>
            <div id="homelink"><a href=""><img src="images/favicon_16x16.png"> 曲奇主页</a></div>
        </div>
    </div><br>
    <!--end of usercard-->
   
</div><!--end of rightside-->
</body>
</html>
