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
<title>douban group</title>
<link rel="icon" href="nxt/images/favicon_16x16.png">
<link rel="stylesheet" href="nxt/css/header.css" >
<link rel="stylesheet" href="nxt/css/main_nav.css">
<link rel="stylesheet" type="text/css" href="nxt/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="nxt/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="nxt/css/mygroup_left_topic.css">
<link rel="stylesheet" type="text/css" href="nxt/css/mygroup_right.css">
<script src="nxt/js/jquery.min.js"></script>
<script src="nxt/js/bootstrap.min.js"></script>
<script src="nxt/js/jquery-2.1.1.js"></script>
</head>


<body>
 <jsp:include page="douban_group_head.jsp"></jsp:include>

<div id="mygroup_left_topic">
	<div id="my_subject">我的小组话题</div>
    <!--end of mysubject-->
    <div id="close_icon">
    	<span class="glyphicon glyphicon-remove"></span>
    </div>
    <!--end of close icon-->
    <div class="pro-banner" id="banner">
    	<div><img src="images/ads/file-1409712321.png"></div>
	</div>
    <!--end of pro_banner-->
	<hr>
	  <div id="topic_title">
    	<div id="topic_nav"><a href="">最近话题 </a>/<a href=""> 最热话题</a></div>
        
        <div id="speak"><a href="nxt/douban_group_addPost.jsp?group_id=${requestScope.group.id }">+发言</a></div>
    </div><!--end of topic_title-->
    <div id="mygroup_topics">
    	<table id="mygroup_table">
        	<tbody>
        	暂无帖子！
                  
                  
                 
            </tbody>
        </table>
</div><!--end of the table div-->


</div>
<!--end of mygroup_left_topic-->

<div id="mygroup_right">
	<div class="profile_entry">
  	<div class="pic">
  		<a href=""><img src="${sessionScope.user.imgs }" title="${sessionScope.user.username }"></a>
  	</div>
  	<div class="info">
    	<a href="groupServlet?method=aheadMyHomepage">我的小组主页</a>
    	<div class="info_detail">
        	<a href="groupServlet?method=queryUserPub&&user_id=${sessionScope.user.user_id}">发起（${requestScope.pub_posts}）</a> &nbsp;| &nbsp; <a href="groupServlet?method=queryUserReply&&user_id=${sessionScope.user.user_id}">回应（${requestScope.reply_posts}）</a>
        </div>
  	</div>
    
	</div>
    <!--end of profile_entry-->
    <br>
    <div>常去的小组</div>
    <hr>
    <div id="footprint_group">
   
    </div><!--end of footprint_group-->
    <br>
	<div id="topic_ad">
    	<img src="images/ads/ad2.png">
    </div><br>
    <!--end of topic_ad-->
    <c:if test="${! empty sessionScope.user }">
    <div id="application"><a href="douban_group_apply.jsp">+申请创建小组</a></div>
    </c:if>
    </div>
    <!--end of right_side-->
    <script type="text/javascript">
   $(function(){
   		$('#close_icon').click(function(){
   			$('.pro-banner').css("display","none");
   			$('#close_icon').css("display","none");
   		});
   		
   });
    </script>
</body>
</html>
