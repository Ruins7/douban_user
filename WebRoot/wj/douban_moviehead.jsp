<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="icon" href="lsr/img/quqi.ico">
    <script src="wj/js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="wj/js/jquery.cityselect.js"></script>
    <script type="text/javascript" src="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js"></script>
    <script src="wj/js/jquery-ui.js"></script>
    <script src="wj/js/Myjs.js"></script>
    <script src="wj/js/Myjs.js"></script>
   <script src="wj/js/bootstrap.min.js"></script>
   <script src="wj/js/jquery.js"></script>
   <link href="wj/js/jquery-ui.css" rel="stylesheet" >
   <link href="wj/js/bootstrap.min.css" rel="stylesheet" type="text/css">
   <link href="wj/js/bootstrap-theme.css" rel="stylesheet" type="text/css">
   <link href="wj/css/douban_movies.css" rel="stylesheet" type="text/css">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

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
			<li><a href="#" style="width:90px">${sessionScope.current_user.username}</a></li>
		</ul>
	</div>
   	</div><!--菜单栏div-->
    
    <div id="douban"><!--曲奇标题div-->
    	<div class="top"><!--上部分-->
            <!--曲奇读书图片位置-->
           <span style="margin-left:170px;margin-top:8px; float:left;width: 150px;height: 60px;color: #1E88C7;font-size: 36px;font-weight: 900;font-family:YouYuan;margin-top: 15px;">曲奇电影</span>
            <!--搜索-->
            <form>
                 <input type="text" class="seach" placeholder="电影、影人、影院、电视剧" style="width:480px; height:30px">           
           		 <a href="#"><img src="wj/img/search1.png" style="padding-top:25px;"></a>
     		</form>
        </div><!--上部分-->
        <!--下部分-->
        <div class="bottom">
        	<div class="bottom_title">
                <ul class="title_menu">
                <li><a href="#">影讯&购票 </a></li>
                <li><a href="/douban_user/servlet/movieServlet?method=allfindMovies">选电影</a></li>
                <li><a href="/douban_user/servlet/movieServlet?method=getListJsonByCount">佳片排行榜</a></li> 
                </ul>
            </div>
        </div><!--下部分-->
   </div><!--曲奇标题div-->
    
     
  </body>
</html>

