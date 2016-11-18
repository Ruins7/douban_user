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
<title>douban group explore</title>
<link rel="icon" href="nxt/images/favicon_16x16.png">
<link rel="stylesheet" href="nxt/css/header.css" >
<link rel="stylesheet" href="nxt/css/main_nav.css">
<link rel="stylesheet" type="text/css" href="nxt/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="nxt/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="nxt/css/main_left.css">
<link rel="stylesheet" href="nxt/css/main_right.css">
<script src="nxt/js/jquery.min.js"></script>
<script src="nxt/js/bootstrap.min.js"></script>
<script src="nxt/js/jquery-2.1.1.js"></script>
</head>

<body>
<jsp:include page="douban_group_head.jsp"></jsp:include>


<div id="main_left">

	<div id="subject">话题精选</div>
    <!--end of subject-->
   <c:forEach items="${requestScope.posts }" var="post">
    <div id="channel_item">
    <div id="likes">${post.readcount }<br>阅读</div><!--left side-->
    <div id="item_content">
      <a href="groupServlet?method=queryOnePost&&post_id=${post.id }">${post.post_title}</a>
      <div id="block">
        <div id="pic">
        	<img src="nxt/images/example/01.jpg"class="img-thumbnail" style="height:70px; width:70px;" />
        </div>
        <p id="content">${post.post_content}</p>
      </div>
      <div id="source">
       
        <span id="pubtime">${post.post_pubtime}</span>
      </div>
      <hr>
    </div><!--right side-->
    </div><!--end of channel_item-->
    </c:forEach>
   

</div>
<!--end of main_left-->


<div id="main_right">
	<!-- douban ad begin -->
  	<div id="rec_group">
    	<div id="rec_title">甲壳虫十月推荐小组</div>
        <div id="rec_list">
        	<div><a href="">我们爱讲冷笑话</a></div>
            <div><a href="">提前上床我们一起读书(WoiDuShu）</a></div>
            <div><a href="">我爱化妆品|微信号: d10036</a></div>
            <div><a href="">冷知识小组</a></div>
            <div><a href="">买书如山倒 读书如抽丝</a></div>
        </div>
    </div>
  	<!-- douban ad end -->
    <br>
    <div>
    值得加入的小组
    </div>
    <hr>
    <div id="hot_group">
    	<div id="group_image"><img src="nxt/images/favicon/g459388-1.jpg" class="thumbnail"></div>
        <div id="group_shortcut">
        	<div id="group_name"><a href="">饮食男女</a></div>
            <div id="group_num">
            	<span>721个成员</span>
                <span><a href="">+加入小组</a></span>
            </div>
        </div>
    </div>
    <hr><!--end of hot_group-->
    
    <div id="hot_group">
    	<div id="group_image"><img src="nxt/images/favicon/g459388-1.jpg" class="thumbnail"></div>
        <div id="group_shortcut">
        	<div id="group_name"><a href="">饮食男女</a></div>
            <div id="group_num">
            	<span>721个成员</span>
                <span><a href="">+加入小组</a></span>
            </div>
        </div>
    </div>
    <hr><!--end of hot_group-->
    
    <div id="hot_group">
    	<div id="group_image"><img src="nxt/images/favicon/g459388-1.jpg" class="thumbnail"></div>
        <div id="group_shortcut">
        	<div id="group_name"><a href="">饮食男女</a></div>
            <div id="group_num">
            	<span>721个成员</span>
                <span><a href="">+加入小组</a></span>
            </div>
        </div>
    </div>
    <hr><!--end of hot_group-->
    
</div>
<!--end of main_right-->
</body>

</html>
