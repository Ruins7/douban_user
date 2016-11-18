<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
	<link type="text/css" href="wj/css/movieStyle.css" rel="stylesheet">	
		<title>电影</title>
    <meta charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
   <link href="wj/css/movieaInfo.css" rel="stylesheet" type="text/css">
    <script src="wj/js/jquery-2.1.1.js"></script>
    <script type="text/javascript">
    $(function(){
    	$('.div1').mouseover(function(){
    		$('#div2').css('visibility','visible');
    		});
        $('.div1').mouseout(function(){
    		$('#div2').css('visibility','hidden');	
        });
    });
    </script>
  </head>
<body>
 
	 <jsp:include page="/wj/douban_moviehead.jsp"></jsp:include>
     <div style="margin-left: 200px;float: left;margin-top: 20px;width: 600px;height: auto;">
         <p style="font-size: 24px;color: #2F92D5;font-weight: 900;">${director.d_name } 的作品集</p>
         <c:forEach var="movie" items="${movies}">
          <hr>
          <div style="width: 600px;height: auto;float: left;margin-top: 20px;margin-bottom: 20px;">
              <img alt="" src="<%=basePath%>${movie.imgs}" width="110px" height="150px" style="float:left;margin-right: 20px;">
              <p>片名：<a href="/douban_user/servlet/movieServlet?method=findOneMovie&movie_id=${movie.movie_id}">${movie.m_name}</a></p>
              <p>主演：${movie.m_actors}</p>
              <p>上映时间：<fmt:formatDate value="${movie.m_screentime }" pattern="yyyy-MM-dd"/> </p>
              <p>类型：[${movie.m_type.type_name }]</p>
              <p>地区：${movie.m_district }</p>
              <p>语言：${movie.m_language }</p>
          </div>
          </c:forEach>
      </div>
       
      <div style="width: 330px;height: auto;min-height:180px;float: left;margin-top: 90px;margin-left: 50px;padding: 15px;border:1px solid #ccc;background-color: #F0F3F5;border-radius:15px;box-shadow: 5px 5px 5px #ccc;">
          <p style="font-size: 16px;color: #2F92D5;font-weight: 500; ">曲奇猜你喜欢的导演</p>
          <c:forEach var="d" items="${alldirector }">
            [<span style="font-size: 12px;color: #2F92D5;"><a href="<%=basePath%>servlet/movieServlet?method=showMovieByDirector&director_id=${d.director_id}">${d.d_name }</a></span>]
          </c:forEach>
      </div>
      <img src="<%=basePath%>/lsr/img/chengtuo.png" style="margin-top: 30px;margin-left: 60px;">
    
</body>
</html>
