<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title></title>
<link rel="icon" href="lsr/img/quqi.ico">
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
<script type="text/javascript" src="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js"></script>
<script type="text/javascript" src="lsr/js/jquery.cityselect.js"></script>
<script>
 
$(function() {
	 
	var mycity = remote_ip_info['city'];
	$('#currentcity').val(mycity);
	
	//同城跳转
	$('.samecity').click(function(e){
		e.preventDefault();
		var url = "/douban_user/hyy/service/ActivityServlet?method=searchAllOnLineActivity&city_desc="+mycity+"";
	    window.location.href = url;
	});
	
	//搜索用户
	$('#btn').click(function(){
		var username = $('#username').val();
		if(username.trim() != ""){
			var url = "/douban_user/user/userinfo?method=searchUser&username="+username.trim()+"";
		    window.location.href = url;
		}else{
			alert("请输入要查找的用户名称");
			return false;
		}
	});
	
	//退出登陆
	$('#logout').click(function(e){
		e.preventDefault();
		if(confirm("您确定要退出登陆么？！")){
			 window.location = "/douban_user/user/userinfo?method=UserLogout";
		}else{
			 return false;
		}
	});

});
</script>

</head>
<body>
 <div id="title"><!--菜单栏div-->
	<div id="menua">
		<ul ><!--菜单栏内容-->
			<li><a style="color:#D5D5D5; font-size:12px" href="<%=basePath%>user/userinfo?method=searchFriendsBroByPage&user_id=${sessionScope.current_user.user_id}">曲奇</a></li>
			<li><a href="<%=basePath%>book/bookinfo?method=searchBookByTimeDecs">读书</a></li>
			<li><a href="<%=basePath%>servlet/movieServlet?method=allfindMovies">电影</a></li>
			<li><a href="#" class="samecity">同城</a></li>
			<li><a href="<%=basePath %>groupServlet?method=loginGroup">小组</a></li>
			<li><a href="<%=basePath%>things/thingsinfo?method=searchThingsByPage&typepage=ul&user_id=${sessionScope.current_user.user_id}">东西</a></li>
			<li><a href="#">更多</a></li>
		</ul>
	</div>
	<div id="menub">
		<ul >
			<li><a href="#">提醒</a></li>
			<li><a href="<%=basePath%>douban_douemail.html">豆邮</a></li>
			<li><a href="#" id="logout" style="width:90px">退出登陆</a></li>
		</ul>
	</div>
</div><!--菜单栏div-->
<div id="douban"><!--douban-->
	<span style="margin-left:170px;margin-top:8px; float:left;width: 150px;height: 60px;color: #259039;font-size: 36px;font-weight: 900;font-family:YouYuan;margin-top: 15px;">曲奇QuQi</span>
	<div class="douban_menu" style="margin-top: 10px;">
		<ul>
			<li><a href="<%=basePath%>user/userinfo?method=searchFriendsBroByPage&user_id=${sessionScope.current_user.user_id}">首页</a></li>
			<li><a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${sessionScope.current_user.user_id}">我的曲奇</a></li>
			<li><a href="#">浏览发现</a></li>
			<li><a href="#">事情</a></li>
			<li><a href="#">线上活动</a></li>
		</ul>
	</div>
	<div id="search"  style="padding-top: 10px;">
		<div id="search_input"><input type="text" id="username" class="form-control" placeholder="搜索你感兴趣的人或者内容..." style="width:220px; height:34px;"></div>
		<div id="search_btn"><button id="btn" type="submit" class="btn btn-default">搜索</button></div>
	</div>
</div><!--douban-->
</body>
</html>

