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
<title>小组</title>
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
<script>
$(function(){
	
});
</script>
</head>


<body>
 <jsp:include page="douban_group_head.jsp"></jsp:include>

 


<div id="mygroup_left_topic">
	<div id="title">
		<div id="group_pic"><img src="${requestScope.group.imgs }" width="50px" height="50px" style="border-radius:25px;"></div>
		<div id="group_name">${requestScope.group.group_name }</div>
		<c:if test="${! empty sessionScope.current_user }">
		<c:if test="${sessionScope.role.group_role_id == 1}">
    	<div id="join">我是小组组长</div>
    	<span id="join"><a href="groupServlet?group_id=${requestScope.group.id}&&method=editGroup1">修改小组基本信息</a></span>
    	<span id="join"><a href="groupServlet?group_id=${requestScope.group.id}&&method=memberCheck">成员管理</a></span>
    	</c:if>
    	<c:if test="${sessionScope.role.group_role_id == 2}">
    	<div id="join">我是小组管理员</div>
    	</c:if>
    	<c:if test="${sessionScope.role.group_role_id == 3}">
    	<div id="join">我是小组成员</div>
    	</c:if>
    	<c:if test="${sessionScope.role.group_role_id == 0}">
    	<div id="join_group"><a href="">加入小组</a></div>
    	</c:if>
    	</c:if>
    	<c:if test="${ empty sessionScope.current_user }">
    	<div id="join_group1" ><a href="">加入小组</a></div>
    	</c:if>
    </div>
    <!--end of title-->
    <div id="group_intro">
    	${requestScope.group.group_intro }
    </div>
    <!--end of group_intro-->
	
    <div id="topic_title">
    	 
        
        <div id="speak"><a href="nxt/douban_group_addPost.jsp?group_id=${requestScope.group.id }">+发言</a></div>
    </div><!--end of topic_title-->
    <div id="mygroup_topics">
    	<table id="mygroup_table">
        	<tbody>
            <tr class="pl">
                      <td class="td-subject">话题</td>
                      <td class="td-author">作者</td>
                      <td class="td-reply">回应</td>
                      <td class="td-lastreply">最后回应</td>
             </tr>
   			<c:forEach items="${requestScope.posts }" var="post">		        	   
            	<tr class="pl">
                      <td class="td-subject">
                        <a href="groupServlet?method=queryOnePost&&post_id=${post.post_id}"  class="title">${post.post_title }</a>
                      </td>
                      <td class="td-reply" nowrap="nowrap">${post.author_name }</td>
                      <td class="td-time" title="" nowrap="nowrap">${post.reply_count}</td>
                      <td class="td-group">${post.recentReply_time}</td>
                  </tr>
         	</c:forEach>  
            </tbody>
        </table>
</div><!--end of the table div-->


</div>
<!--end of mygroup_left_topic-->
 
<div id="mygroup_right">

	<div id="friend_group">
    	<div>友情小组</div>
        <div id="friendgroup_list">
    		<ul>
            	<li style="text-align: center;">
                	<span><img src="<%=basePath%>/nxt/images/favicon/g95836-1.jpg" width="50px" height="50px" style="border-radius:25px;"></span>
                    <br>
                    <span><a href="">野花</a><br>(2240)</span>
                </li>
               
            </ul>
        </div><!--end of friendgroup_list-->
    	
    </div><!--end of friend_group-->
    
    <div id="br_null"></div>
    <br><br>
    
    
    
	<div id="topic_ad">
    	<img src="<%=basePath%>/nxt/images/ads/ad2.png" width="auto" height=""auto"" style="">
    </div><br>
    <!--end of topic_ad-->
    
   
    <!--end of right_side-->
</body>
</html>
