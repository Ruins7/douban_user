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
<title>增加话题</title>
<base href="<%=basePath%>">
<link rel="icon" href="<%=basePath%>/nxt/images/favicon_16x16.png">
<link rel="stylesheet" href="<%=basePath%>/nxt/css/header.css" >
<link rel="stylesheet" href="<%=basePath%>/nxt/css/main_nav.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/nxt/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/nxt/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/nxt/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/nxt/css/editGroup.css">
<script src="<%=basePath%>/nxt/js/jquery.min.js"></script>
<script src="<%=basePath%>/nxt/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/nxt/js/jquery-2.1.1.js"></script>
<script src="<%=basePath%>/nxt/js/popover.js"></script>
</head>


<body>
<jsp:include page="douban_group_head.jsp"></jsp:include>
<div id="editGroup_left">
	<div id="title">发言</div><br>
   
    <form action="groupServlet?method=userAddPost" method="post">
    <input type="hidden" name="post_group" value="<%=request.getParameter("group_id")%>">
    <div id="main">
    	<div id="group_name">
    		<span id="field_label">标题</span>
        	<input type="text" name="post_title">
    	</div>
       	<div id="group_intro">
    		<span id="field_label">内容</span>
        	<textarea name="post_content"></textarea>
    	</div>
       
       
      
        <div id="br_null"></div>
        <input id="sub_save" type="submit" value="好了，发言">
    </div>
    </form>
</div><!--end of editGroup_left-->
 
  
</body>
</html>

