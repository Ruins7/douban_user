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
<title>我加入的小组</title>
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
<jsp:include page="/nxt/douban_group_head.jsp"></jsp:include>

<div id="personal_group_leftside">
	<div id="mygroup">我的曲奇小组</div><br>
    <!--end of mygroup-->
    <div id="mygroup_nav">
    	<a href="groupServlet?method=aheadMyHomepage"  style="background-color: #fff;color: #0E959D;">小组主页</a>
        <a href="groupServlet?method=queryMyGroupByRole" style="background-color: #6EBFC3;border-radius:3px;color: #fff;">加入的小组</a>
        <a href="groupServlet?method=queryUserPub&&user_id=${sessionScope.current_user.user_id}">发起</a>
        <a href="groupServlet?method=queryUserReply&&user_id=${sessionScope.current_user.user_id}">回应</a>
       
    </div><hr><br>
    <!--end of mygroup_nav-->
    
    <div id="joins">
    	<div>创建的小组</div>
        <div id="group_list">
        	<ul>
        	<c:forEach items="${requestScope.creator_list }" var="creator">
            	<li>
                	<div id="group_pic"><img src="${creator.imgs }" width="50px" height="50px" style="border-radius:25px;">
                    </div>
                    <div id="group_info">
                    	<div id="group_title"><a href="groupServlet?method=queryOneGroup&&group_id=${creator.id}">${creator.group_name }</a></div>
                        
                    </div>
                </li>
               </c:forEach>
          
            </ul>
        </div><!--end of group_list-->
    	<div id="null_div"><br>
    	<div>管理的小组</div>
        <div id="group_list">
        	<ul>
        	<c:forEach items="${requestScope.admin_list }" var="admin">
            	<li>
                	<div id="group_pic"><a href=""><img src="${admin.imgs }"></a>
                    </div>
                    <div id="group_info">
                    	<div id="group_title"><a href="groupServlet?method=queryOneGroup&&group_id=${admin.id}">${admin.group_name }</a></div>
                        
                    </div>
                </li>
               </c:forEach>
          
            </ul>
        </div><!--end of group_list-->
    </div><!--end of joins-->
    <div id="null_div"></div><!--空div ，让下一个div另起一行--><br><br>
    
    <div id="joins">
    	<div>加入的小组</div>
        <div id="group_list">
        	<ul>
        	<c:forEach items="${requestScope.normal_list }" var="normal">
            	<li>
                	<div id="group_pic"><a href=""><img src="${normal.imgs }"></a>
                    </div>
                    <div id="group_info">
                    	<div id="group_title"><a href="groupServlet?method=queryOneGroup&&group_id=${normal.id}">${normal.group_name }</a></div>
                        
                    </div>
                </li>
            </c:forEach>  
          
            </ul>
        </div><!--end of group_list-->
    </div><!--end of joins--><br>

    <div id="null_div"></div><!--空div ，让下一个div另起一行--><br>
    
        

        
    <br>
        
</div>
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
    <div id="album">
    	<div>相册(<a href="">0</a>)</div><hr>
        <div id="album_list">
        	<ul>
            	<li>
                	<div><img src="<%=basePath%>/nxt/images/example/p1038151914.jpg" class="thumbnail"></div>
                    <div id="album_name"><a href="">加贺谷穰大师</a></div>
                    <div id="update_time">2014-10-06 12:22:21<br>更新</div>
                    
                </li>
                <li>
                	<div><img src="<%=basePath%>/nxt/images/example/p1038165780.jpg" class="thumbnail"></div>
                    <div id="album_name"><a href="">加贺谷穰大师</a></div>
                    <div id="update_time">2014-10-06 12:22:21<br>更新</div>
                    
                </li>
                <li>
                	<div><img src="<%=basePath%>/nxt/images/example/p1039696639.jpg" class="thumbnail"></div>
                    <div id="album_name"><a href="">加贺谷穰大师</a></div>
                    <div id="update_time">2014-10-06 12:22:21<br>更新</div>
                    
                </li>
            </ul>
        </div>
    </div>
    
    
</div><!--end of rightside-->
</body>
</html>
