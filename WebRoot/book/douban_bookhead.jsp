<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	
	<link href="lsr/css/douban_read.css" rel="stylesheet" type="text/css">
	<link href="lsr/css/douban_loginAfter.css" rel="stylesheet"type="text/css">
    <script src="lsr/js/jquery-2.1.1.js" type="text/javascript"></script>
    <script src="lsr/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="lsr/js/jquery-ui.js" type="text/jscript"></script>
    <script>
    $(function(){
    	$('.invo').click(function(e){
    		e.preventDefault();
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
			<li><a href="#" style="width:90px">${sessionScope.current_user.username}</a></li>
		</ul>
	</div>
   	</div><!--菜单栏div-->
   	
   	 <div><!--曲奇标题div-->
    	<div class="top"><!--上部分-->
            <!--曲奇读书图片位置-->
           <span style="margin-left:170px;margin-top:8px; float:left;width: 150px;height: 60px;color: #493019;font-size: 36px;font-weight: 900;font-family:YouYuan;margin-top: 15px;">曲奇读书</span>
            <!--搜索-->
            <form >
           <div id="search" style="width: 500px;height: auto;margin-left: 100px;">
		     <div id="search_input" style="float: left;"><input type="text" class="form-control" placeholder="搜索你感兴趣的人或者内容..." style="width:365px; height:34px;margin-top: 10px;float: left;"><button id="btn" type="submit" class="btn" style="float: left;width: 60px;height: 34px;margin-top: 10px;">搜索</button></div>
	       </div>
     		</form>
        </div><!--上部分-->
    	<hr color="#E9E2E2">
        <div class="bottom"><!--下部分-->
        	<div class="bottom_title" style="margin-left: 200px;">
                <ul class="title_menu">
                <li><a href="#" class="invo">我读</a></li>   <!--用户浏览记录-->
                <li><a href="#" class="invo">动态</a></li>
                <li><a href="#" class="invo">分类浏览</a></li>
                <li><a href="<%=basePath%>book/bookmanage?method=showMyWannaBuy&user_id=${sessionScope.current_user.user_id}">购书心愿单</a></li>
                </ul>
            </div>
        </div><!--下部分-->
    </div><!--曲奇标题div-->
  </body>
</html>
