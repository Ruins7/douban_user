<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<link href="hyy/js/jquery-ui.css" rel="stylesheet" >
    <link href="hyy/js/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="hyy/js/bootstrap-theme.css" rel="stylesheet" type="text/css">
    <link href="hyy/css/douban_activityhead.css" rel="stylesheet" type="text/css">
   <script>
   $(function(){
	   //设置select 中的当前城市
	  $('#city').text($('#h_city').val());
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
    
    <div id="city_title">
    	<span style="margin-left:170px;margin-top:8px; float:left;width: 150px;height: 60px;color: #E54401;font-size: 36px;font-weight: 900;font-family:YouYuan;margin-top: 10px;">曲奇同城</span>
    	<form>
            <div style="margin-top:23px; float:left; position:relative; padding-left:10px;">
            <!-- 下拉菜单 -->
                <span style="color: #664433;">当前城市:</span> <span id="city" style="color: #664433;"></span>
                 <input type="hidden" id="h_city" value="${h_city.city_desc}">
             </div>
             <div style="padding-top:23px; float:left; position:relative; padding-left:30px;">
             	<div class="amenu" ><a href="<%=basePath%>hyy/indexOffActivity.jsp" style="text-decoration:none;color: #664433;">同城活动</a></div>
                <div class="amenu"><a href="#" style="text-decoration:none;color: #664433;">主办方</a></div>
                <div class="amenu"><a href="#" style="text-decoration:none;color: #664433;">舞台剧</a></div>
                <div class="amenu"><a href="#"style="text-decoration:none;color: #664433;" >我的同城</a></div>
             </div>
             
             <!--搜索-->
            <div class="seach">
                   <input type="text" class="form-control" placeholder="活动名称，地点，介绍，舞台剧" style=" float:left; position:relative;width:220px; height:35px">
                  <button type="submit" class="btn btn-default"  style="float:left; position:relative">搜索</button>
            </div>
        </form>
    </div>
  </body>
</html>
