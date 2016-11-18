<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>申请曲奇小组</title>
<link rel="icon" href="nxt/images/favicon_16x16.png">
<link rel="stylesheet" href="nxt/css/header.css" >
<link rel="stylesheet" href="nxt/css/main_nav.css">
<link rel="stylesheet" type="text/css" href="nxt/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="nxt/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="nxt/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="nxt/css/apply.css">
<script src="nxt/js/jquery.min.js"></script>
<script src="nxt/js/bootstrap.min.js"></script>
<script src="nxt/js/jquery-2.1.1.js"></script>
<script src="nxt/js/popover.js"></script>
</head>


<body>
 <jsp:include page="douban_group_head.jsp"></jsp:include>

<div id="apply_left">
	<div id="title">申请曲奇小组</div>
    <p id="content">
    如果想就某一类话题跟别人交流，可以创建一个小组。<br> 
	小组是对同一个话题感兴趣的人的聚集地。
    </p>
    <div id="start"><a href="nxt/douban_group_apply.jsp">开始创建小组</a></div>
</div>
<!--end of apply_left-->

<div id="apply_right">
<p>考虑到中国法律法规和相关政策的要求,曲奇不欢迎色情、激进话题、意识形态方面的讨论, 并保留解散这类主题小组的权利。</p> 
</div><!--end of apply_right-->
  
</body>
</html>
