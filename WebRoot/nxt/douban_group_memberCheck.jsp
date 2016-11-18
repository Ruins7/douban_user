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
<title>douban group personal</title>
<base href="<%=basePath%>">
<link rel="icon" href="nxt/images/favicon_16x16.png">
<link rel="stylesheet" href="nxt/css/header.css" >
<link rel="stylesheet" href="nxt/css/main_nav.css">
<link rel="stylesheet" type="text/css" href="nxt/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="nxt/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="nxt/css/personal_group_leftside.css">
<link rel="stylesheet" type="text/css" href="nxt/css/personal_group_rightside.css">
<script src="nxt/js/jquery.min.js"></script>
<script src="nxt/js/bootstrap.min.js"></script>
<script src="nxt/js/jquery-2.1.1.js"></script>
</head>


<body>
 <jsp:include page="douban_group_head.jsp"></jsp:include>

<div id="personal_group_leftside">
	<div id="mygroup">小组管理</div><br>
    <!--end of mygroup-->
   
    <!--end of mygroup_nav-->
    
    <div id="joins">
    	<div>组长</div>
        <div id="group_list">
        	<ul>
        	<c:forEach items="${requestScope.creator }" var="c">
            	<li>
                	<div id="group_pic"><a href=""><img src="${c.imgs }" width="50px" height="50px" style="border-radius:25px;"></a>
                    </div>
                    <div id="group_info">
                    	<div id="group_title"><a href="">${c.username }</a></div>
                      	
                    </div>
                </li>
          </c:forEach>
            </ul>
        </div><!--end of group_list-->
    </div><!--end of joins-->
    <div id="null_div"></div><!--空div ，让下一个div另起一行--><br><br>
    
     <div id="joins">
    	<div>管理员</div>
        <div id="group_list">
        <ul>
        	<c:forEach items="${requestScope.admin }" var="a">
            	<li>
                	<div id="group_pic"><a href=""><img src="${a.imgs }"></a>
                    </div>
                    <div id="group_info">
                    	<div id="group_title"><a href="">${a.username }</a></div>
                      	<span><a href="groupServlet?method=demoteUser&&user_id=${a.user_id}&&group_id=${requestScope.group_id}" title="降为普通成员">v</a>
                      	</span> <span><a href="groupServlet?method=delUser&&user_id=${a.user_id}&&group_id=${requestScope.group_id }" title="踢出小组">k</a></span>
                    </div>
                </li>
          </c:forEach>
            </ul>
        </div><!--end of group_list-->
    </div><!--end of joins-->
    <div id="null_div"></div><!--空div ，让下一个div另起一行--><br><br>
    
    <div id="joins">
    	<div>成员</div>
        <div id="group_list">
        	<ul>
        	<c:forEach items="${requestScope.normal }" var="n">
            	<li>
                	<div id="group_pic"><a href=""><img src="${n.imgs }"></a>
                    </div>
                    <div id="group_info">
                    	<div id="group_title"><a href="">${n.username }</a></div>
                      	<span><a href="groupServlet?method=promteUser&&user_id=${n.user_id}&&group_id=${requestScope.group_id}" title="升为管理员">^</a></span>
                      	 <span><a href="groupServlet?method=delUser&&user_id=${n.user_id}&&group_id=${requestScope.group_id}" title="踢出小组">k</a></span> 
                      	 
                    </div>
                </li>
          </c:forEach>
            </ul>
        </div><!--end of group_list-->
    </div><!--end of joins--><br>
    
   
</div>
<!--end of personal_group_leftside-->

<div id="personal_group_rightside">
	<div id="user_card">
    	<div id="user_pic"><img src="<%=basePath%>/nxt/images/ads/user_normal.jpg" class="img-circle"></div>
        <div id="user_info">
        	<div>哈密瓜</div>
            <div id="living">常居：天津</div>
            <div id="homelink"><a href=""><img src="<%=basePath%>/nxt/images/favicon_16x16.png"> 曲奇主页</a></div>
        </div>
    </div><br>
    <!--end of usercard-->
    <div id="back_home" style="margin: 10px 0 0 20px;">
   <a href="groupServlet?method=queryOneGroup&&group_id=${requestScope.group_id}">返回小组</a>
   </div>
</div><!--end of rightside-->
</body>
</html>
