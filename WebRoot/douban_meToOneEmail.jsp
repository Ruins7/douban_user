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
<title>无标题文档</title>
<link href="../css/douban_emailAll.css" rel="stylesheet" type="text/css">
<link href="../css/douban_emailb.css" rel="stylesheet" type="text/css">
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
			<li><a href="<%=basePath%>douban_douemail.html">豆邮</a></li>
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


<div id="left">
	<div id="name">
    	<span>与${requestScope.other.username }的豆邮</span>
    </div>
	<div id="time">
    	<div id="time_span"><span></span></div>
    </div>
    <c:forEach items="${requestScope.mailCommentList }" var="mailcomment">
    <div class="hr_con"><!---hr_con--->
            <div id="hr">
                <hr style="float:left;width:294px; margin-top:7px; "/>
                <font style="float:left;"></font>
                <hr style="float:left;width:294px;margin-top:7px;"/>
            </div>
               
            <div class="p_user"><!--p_user--->
                <div class="p_left">
                	<a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${mailcomment.send_user_id.user_id}"><img src="${mailcomment.send_user_id.imgs }"></a>
                </div>
                <div class="p_con">
                    <span><a href="#">${mailcomment.send_user_id.username }</a></span><br>
                    <span><a href="#">${mailcomment.comment_content }</a></span>
                    <a href="DouMailServlet?method=deleteMailComment&mailComment_id=${mailcomment.comment_id }">删除</a>
                </div>
                <div class="p_time">
                    <span>${mailcomment.comment_time }</span>
                </div>
            </div><!--p_user--->
    	</div>
    </c:forEach><!---hr_con--->
        
        
        
        <div id="publish">
            <div id="start">
            	<form action="DouMailServlet">
            		<input type="hidden" name="method" value="saveMailComment">
            		<input type="hidden" name="other_id" value="${requestScope.other.user_id }">
                	<input type="text" id="sia" name="content"><br>
                	<input type="submit" value="回应" id="sib">
                </form>
            </div>
            
            <hr style="border:1px dashed #334455" >
        
            <div id="button">
                <form>
                    <input type="button" value="垃圾豆邮"> 
                    <a href="hyy/service/DouMailServlet?method=deleteOther&other_id=${requestScope.other.user_id }&um_id=${requestScope.mailCommentList[0].userMail.userMail_id}&my_id=${sessionScope.current_user.user_id}"><input type="button" value="删除" id="deleteMail"></a>
                </form>
            </div>
        
    </div><!--publish--->
    
    
    
</div>
<div id="right">

	<div class="ba">
    	<span><a href="douban_douemail.html">> 返回我的豆邮</a></span>
        <BR><BR>
        <span><a href="douban_douemail.html">> 返回我的豆邮</a></span>
    </div>
	<div class="ba">
    	<span><a href="douban_douemail.html">> 将‘用户名’列入我的黑名单</a></span>
    </div>
</div>



<!---底部栏--->
<div id="all_bottom" style="height:30px; width:960px; margin-left:200px; bottom:-330px; position:absolute;">	
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
<script src="../js/jquery-2.1.1.js"></script>

