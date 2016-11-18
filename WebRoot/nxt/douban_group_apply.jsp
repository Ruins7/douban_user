<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>申请创建小组</title>
<base href="<%=basePath%>">
<link rel="icon" href="nxt/images/favicon_16x16.png">
<link rel="stylesheet" href="<%=basePath%>/nxt/css/header.css" >
<link rel="stylesheet" href="<%=basePath%>/nxt/css/main_nav.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/nxt/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/nxt/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/nxt/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/nxt/css/apply.css">
<script src="<%=basePath%>/nxt/js/jquery.min.js"></script>
<script src="<%=basePath%>/nxt/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/nxt/js/jquery-2.1.1.js"></script>
<script src="<%=basePath%>/nxt/js/popover.js"></script>
</head>


<body>
 <jsp:include page="douban_group_head.jsp"></jsp:include>

<div id="apply_left">
	<div id="title">申请创建小组</div>
    <div id="main">
    <form action="<%=basePath%>groupServlet?method=createGroup" method="post" >
    <div id="group_type">
    	<div id="field_label"> 小组类型:</div>
        <div id="p_group">
        	<div id="hd"><input type="radio" name="group_type" value="1">  公开小组</div>
            <div id="bd">
                曲奇的任何成员都可见。<br/>
                可以设置成员的加入方式。<br/>
                以后可以变为私密小组。<br/>            
            </div>
        </div><!--end of p_group--> 
        <div id="s_group">
        	<div id="hd"><input type="radio" name="group_type" value="2">  私密小组</div>
            <div id="bd">
                只有接受组员邀请才能加入小组。<br/>
                <em>以后永远不能变为公开的小组。</em>           
            </div>
        </div><!--end of s_group--> 
    </div><!--end of group_type-->
    <div id="br_null"></div>
    <div id="group_name">
    	<div id="field_label"> 小组名称:</div>
    	<input type="text" name="group_name">
    </div><!--end of grouop_name-->
    <div id="br_null"></div>
    <div id="group_intro">
    	<div id="field_label"> 小组介绍:</div>
    	<textarea name="group_intro"></textarea>
    </div><!--en of group_intro-->
    <div id="group_tag">
    	<div id="field_label"> 小组标签:</div>
    	<input type="text" name="group_tag">
        <div id="remark">标签作为关键词可以被用户搜索到，多个标签之间用空格分隔开。</div>
    </div><!--en of group_tag-->
    <input id="apply_submit" type="submit" value="提交申请">
    </form>
    </div>
</div><!--end of apply_left-->

<div id="apply_right">
	<div id="explain">
	<div id="field_label">小组创建 ······</div> <br><br>
    <div>
小组需要审核通过后才能完成创建,管理员会在3日内审核 申请, 审核结果会用豆邮通知你,请耐心等待。</div> <br><br><br>
	<div id="field_label">小组标签 ······</div> <br><br>
     <div> 
小组可以有不超过5个的标签，用来描述小组的目的。标签作为关键词可以被用户搜索到。 多个标签之间用空格分隔开。 
<br><br>
比如，"Philip K. Dick小组"可以用 "作者 作家 科幻 科学幻想 迪克"， "关中曲奇" 可以用 "本地 同城 西北 陕西 西安"。小组名称本身可以被搜索，就不用再加在标签里了。 小组的名称、介绍、标签在创立后都可以随时更改。 <div>
	</div> 
</div><!--end of apply_right-->
  
</body>
</html>
