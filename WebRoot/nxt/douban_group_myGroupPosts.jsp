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
<title>我的小组</title>
<base href="<%=basePath%>">
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
 <jsp:include page="/nxt/douban_group_head.jsp"></jsp:include>

<div id="mygroup_left_topic">
	<div id="my_subject">我的小组话题</div>
    <!--end of mysubject-->
   <!--  <div id="close_icon">
    	<span class="glyphicon glyphicon-remove"></span>
    </div> -->
    <!--end of close icon-->
   <!--  <div class="pro-banner" id="banner">
    	<div><img src="images/ads/file-1409712321.png"></div>
	</div> -->
    <!--end of pro_banner-->
	<hr>
    <div id="mygroup_topics">
    	<table id="mygroup_table">
        	<tbody>
        	<c:forEach items="${requestScope.my_posts }" var="post">
            	<tr class="pl">
                      <td class="td-subject">
                        <a href="groupServlet?method=queryOnePost&&post_id=${post.post_id}" class="title">${post.post_title}</a>
                      </td>
                      <td class="td-reply" nowrap="nowrap">${post.reply_count}回应</td>
                      <td class="td-time" title="2014-10-05 14:50:06" nowrap="nowrap">${post.recentReply_time }</td>
                      <td class="td-group"><a href="groupServlet?method=queryOneGroup&&group_id=${post.group_id}" class="">${post.group_name }</a></td>
                  </tr>
        	</c:forEach>   
                  
                  
                 
            </tbody>
        </table>
</div><!--end of the table div-->


</div>
<!--end of mygroup_left_topic-->

<div id="mygroup_right">
	<div class="profile_entry">
  	<div class="pic">
  		<a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${sessionScope.current_user.user_id }"><img src="${sessionScope.current_user.imgs }" title="${sessionScope.current_user.username }" width="50px" height="50px" style="border-radius:25px;"></a>
  	</div>
  	<div class="info">
    	<a href="groupServlet?method=aheadMyHomepage">我的小组主页</a>
    	<div class="info_detail">
        	<a href="groupServlet?method=queryUserPub&&user_id=${sessionScope.current_user.user_id}">发起（${requestScope.pub_posts}）</a> &nbsp;| &nbsp; <a href="groupServlet?method=queryUserReply&&user_id=${sessionScope.current_user.user_id}">回应（${requestScope.reply_posts}）</a>
        </div>
  	</div>
    
	</div>
    <!--end of profile_entry-->
    <br>
    <div>常去的小组</div>
    <hr>
    <div style="width:300px;height: auto;">
    <c:forEach items="${requestScope.groups }" var="group">
   		<a href="groupServlet?method=queryOneGroup&&group_id=${group.id}"><span><img src="${group.imgs }" title="${group.group_name }" width="50px" height="50px" style="border-radius:25px;margin: 5px;"></span></a>
   	</c:forEach>	
    </div><!--end of footprint_group-->
    <br>
     <c:if test="${! empty sessionScope.current_user }">
    <div id="application"><a href="/douban_user/nxt/douban_group_apply.jsp">+申请创建小组</a></div>
    </c:if>
	<div id="topic_ad">
    	<img src="<%=basePath%>/nxt/images/ads/ad2.png">
    </div><br>
    <!--end of topic_ad-->
   
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
