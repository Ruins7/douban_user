<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!doctype html>
<html>
<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta charset="utf-8">
<title>豆邮</title>
<link href="../css/douemail.css" rel="stylesheet" type="text/css">
	<link href="../css/douban_emailAll.css" rel="stylesheet" type="text/css">
</head>

<body>
<div id="title"><!--菜单栏div-->
	<div id="menua">
		<ul class="menua"><!--菜单栏内容-->
			<li><a style="color:#D5D5D5; font-size:12px" href="<%=basePath%>user/userinfo?method=searchFriendsBroByPage&user_id=${sessionScope.current_user.user_id}">豆瓣</a></li>
			<li><a href="<%=basePath%>douban_read.jsp">读书</a></li>
			<li><a href="<%=basePath%>servlet/movieServlet?method=allfindMovies">电影</a></li>
			<li><a href="#">音乐</a></li>
			<li><a href="<%=basePath%>hyy/douban_city.jsp">同城</a></li>
			<li><a href="<%=basePath %>groupServlet?method=loginGroup">小组</a></li>
			<li><a href="<%=basePath%>things/thingsinfo?method=searchThingsByPage&typepage=ul&user_id=${sessionScope.current_user.user_id}">东西</a></li>
			<li><a href="#">更多</a></li>
		</ul>
	</div>
	<div id="menub">
		<ul class="menub">
			<li><a href="#">提醒</a></li>
			<li><a href="hyy/service/DouMailServlet?method=getMyMailArray">豆邮</a></li>
			<li><a href="#" style="width:90px">${sessionScope.current_user.username}</a></li>
		</ul>
	</div>
</div><!--菜单栏div-->

<div id="douban"><!--douban-->
<!--豆瓣字母图片拼写-->
    <div class="title" style="margin-left:200px; padding-top:10px">
         <div style="color:#279338; font-family:黑体; font-size:28px; float:left; position:relative;">
            <strong style="color:#279338; font-family:黑体; font-size:28px;">豆瓣</strong>
         </div>
            <div style="color:#279338; font-family:黑体; font-size:32px; float:left; position:relative; padding-top:8px;">
                d
            </div>
            <div style="color:#2496CD; font-family:黑体; font-size:32px; float:left; position:relative; padding-top:8px;">
                o
            </div>
            <div style="color:#F7C690; font-family:黑体; font-size:32px; float:left; position:relative; padding-top:8px;">
                u
            </div>
            <div style="color:#279338; font-family:黑体; font-size:32px; float:left; position:relative; padding-top:8px;">
                b
            </div>
            <div style="color:#2496CD; font-family:黑体; font-size:32px; float:left; position:relative; padding-top:8px;">
                a
            </div>
            <div style="color:#F7C690; font-family:黑体; font-size:32px; float:left; position:relative; padding-top:8px;">
                n
            </div>
            
        </div>
<!--豆瓣字母图片拼写-->

<div class="douban_menu">
    <ul>
        <li><a href="#">首页</a></li>
        <li><a href="douban_mydouban.html">我的豆瓣</a></li>
        <li><a href="#">浏览发现</a></li>
        <li><a href="#">事情</a></li>
        <li><a href="#">线上活动</a></li>
    </ul>
</div>
<div class="seach" >
       <form>
            
            	<div id="search1">
                   <input type="text" class="form-control" placeholder="书名、作者" style="width:280px; height:26px">
                </div>
                <div id="search1">
                  <a href="#"><img src="img/search1.png"></a>
                </div>
       
     	</form>
</div>
</div><!--douban-->


<!--下部分-->
<!--左部分-->
<div id="left">
	<div id="span"><span class="span">我的豆邮</span></div>
	
    <div id="emailmenu">
        <div class="email"><a href="#">豆邮（0未读）</a></div>
        <div class="email1"><a href="#">垃圾豆邮（0未读）</a></div>
        <div class="email2"><a href="#">筛选:全部豆邮</a></div>
    </div><!--emailmenu--->
    
    <div id="null_div"></div>
    <hr>
    <div id="content_rightAll">
    	<c:forEach items="${requestScope.list }" var="mail">
    		<c:set var="user_to" value="${mail.userMail.user_id_to }"></c:set>
			<c:if test="${sessionScope.me.user_id==mail.userMail.user_id_to.user_id }">
				<c:set var="user_to" value="${mail.userMail.user_id_from }"></c:set>
			</c:if>
    		<div id="content">
                <div id="content_left">
                	<a href="#">
                		<img src="${user_to.imgs }">
                	</a>
                </div>
                <div id="content_right">
                    <span id="content_span_a"><a href="#">${user_to.username }</a></span>
                    <br>
                    <span id="content_span_b"><a href="DouMailServlet?method=meToOne&myId=${requestScope.current_user.user_id }&other_id=${user_to.user_id }">${mail.comment_content }</a></span>
                </div>
         </div>
    	</c:forEach>
     	 <hr>
         
         <div class="re"><input  type="button" value="标记为已读"></div>
         <div class="no"><input  type="button" value="垃圾豆邮"></div>
         <div class="delete"><input  type="button" value="删除"></div>
         
    </div>
    
    
</div><!--左部分-->

<!--右部分-->
<div id="right">
	<div class="right"><a href="DouMailServlet?method=searchFriend">> 给我关注的人写信</a></div>
    <div class="right"><a href="#">> 去我关注的人列表</a></div>
    <div class="right"><a href="#">> 管理黑名单</a></div>

</div>


<!---底部栏--->
<div id="all_bottom" style="height:30px; width:960px; margin-left:200px; bottom:-160px; position:absolute;">	
    <hr style="border:1px dashed #000; height:1px">
   <div style="font-size:12px; float:left; position:relative;padding-top:5px;">© 2005－2014 douban.com, all rights reserve</div>
   
    <div style="float:right;  position:relative;padding-top:5px;">
    	<a href="#" style=" text-decoration:none;font-size:11px ; color:#3377AA">关于豆瓣</a> ·
        <a href="#" style=" text-decoration:none;font-size:11px ;color:#3377AA">在豆瓣工作</a>·
        <a href="#" style=" text-decoration:none; font-size:11px;color:#3377AA">联系我们 </a>·
        <a href="#" style=" text-decoration:none;font-size:11px;color:#3377AA "> 免责声明</a>·
        <a href="#" style=" text-decoration:none;font-size:11px;color:#3377AA "> 帮助中心 </a>·
        <a href="#" style=" text-decoration:none;font-size:11px;color:#3377AA ">开发者 移动应用 · 豆瓣广告</a>·
        <a href="#" style=" text-decoration:none;font-size:11px;color:#3377AA "> 移动应用 </a>·
        <a href="#" style=" text-decoration:none;font-size:11px;color:#3377AA "> 豆瓣广告</a>
    </div>
</div>
<!---底部栏--->
</body>
</html>

