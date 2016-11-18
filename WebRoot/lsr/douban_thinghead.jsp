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
	$('.invo').click(function(e){
		e.preventDefault();
	});
});
</script>
</head>
<body style="background-color: #EFEFEF;">
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
			<li><a href="#" style="width:90px">${sessionScope.current_user.username}</a></li>
		</ul>
	</div>
</div><!--菜单栏div-->
 <!--东西菜单栏-->   
<div style="background:#F7F8ED; width:100%; height:55px;padding: 0px;">
	<div style="float:left;padding-left:180px;margin: 0px;"><a href="<%=basePath%>things/thingsinfo?method=searchThingsByPage&typepage=ul&user_id=${sessionScope.current_user.user_id}"><img src="lsr/img/7.png"></a></div>
    <div style="padding-left:30px;float:left;width:490px;">
    	<div class="douban_menu">
    	<ul >
        	<li><a href="#" class="invo" style="font-size:16px; width:70px;">首页</a></li>
            <li><a href="#" class="invo" style="font-size:16px; width:70px;">豆列</a></li>
            <li><a href="#" class="invo" style="font-size:16px; width:70px;">海淘</a></li>
            <li><a href="#" class="invo" style="font-size:16px; width:70px;">图文</a></li>
            <li><a href="#" class="invo" style="font-size:16px; width:70px;">APP</a></li>
        </ul>
        </div>
    </div>
     <form>
        <div id="search">
		<div id="search_input"><input type="text" class="form-control" placeholder="搜索你感兴趣的人或者内容..." style="width:220px; height:34px;"></div>
		<div id="search_btn"><button id="btn" type="submit" class="btn btn-default">搜索</button></div>
	</div>
     </form>
    <div style="float:left;margin-top:0px;"><a style="color:#D5D5D5; font-size:12px" href="<%=basePath%>user/userinfo?method=searchFriendsBroByPage&user_id=${sessionScope.current_user.user_id}"><img src="<%=basePath%>${sessionScope.current_user.imgs }" width="40px" height="40px" style="border-radius:20px;"></a><a href=""><img src="lsr/img/thing.png"></a></div>
</div>

</body>
</html>

