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
<meta charset="utf-8">
   <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

</head>
	<link href="hyy/css/douban_emailAll.css" rel="stylesheet" type="text/css">
	<link href="hyy/css/douban_emaila.css" rel="stylesheet" type="text/css">
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

<!--//////////////////////////////////////////-->

<div id="left"><!--left--->
	<div id="user">
    	<span>选择收件人</span>
    </div>
	<!--////////-->
    <div id="people">
    
    	<div>
    		<c:forEach items="${requestScope.friendList }" var="user">
    			<div style="position:relative; float:left; margin-left:20px; margin-top:10px;">
    				<div class="pe_a" >
    					<a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${user.user_id}">
    						<img style="height:54px; width:54px;" src="${user.imgs} }">
    					</a>
    				</div>
                <div class="pe_b" style="margin-top:5px;"><a href="#">${user.username }</a></div>
    		</div>
    		</c:forEach>
    
    	</div>
    </div> 
    
</div><!--left--->

<div id="right"><!--right--->
	<div class="se">
    	<span>成员搜索  · · · · · ·</span>
    </div>
	<div id="input">
        <form>
            <input placeholder="名号，username，常住地址，email" class="input_a"><br>
            <input type="button" value="搜索成员" class="input_b">
        </form>
    </div>
    <br>
    <div id="back">
    	<span><a href="douban_douemail.html">> 回到我的豆邮箱</a></span>
    </div>
    
</div><!--right--->


<!---底部栏--->
<div id="all_bottom" style="height:30px; width:960px; margin-left:200px; bottom:-130px; position:absolute;">	
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
