<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>小组的基本设置</title>
<base href="<%=basePath%>">
<link rel="icon" href="nxt/images/favicon_16x16.png">
<link rel="stylesheet" href="<%=basePath%>/nxt/css/header.css" >
<link rel="stylesheet" href="<%=basePath%>/nxt/css/main_nav.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/nxt/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/nxt/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/nxt/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/nxt/css/editGroup.css">
<script src="<%=basePath%>/nxt/js/jquery.min.js"></script>
<script src="<%=basePath%>/nxt/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/nxt/js/jquery-2.1.1.js"></script>
<script src="<%=basePath%>/nxt/js/popover.js"></script>
<script>
$(function(){
	 
});
</script>
</head>


<body>
 <jsp:include page="douban_group_head.jsp"></jsp:include>

<div id="editGroup_left">
	<div id="title">小组基本设置</div><br>
    <div id="nav">
    	<a href="">基本信息</a>
        <a href="">成员管理</a>
        <a href="">数据统计</a>
        <a href="">管理日志</a>
    </div><!--end of nav-->
    <form action="/douban_user/groupServlet?method=adminEditGroup" enctype="multipart/form-data" method="post">
    <div id="main">
    	<input type="hidden" name="id" value="${requestScope.group.id }">
    	<input type="hidden" name="group_createtime" value="${requestScope.group.group_createtime}">
    	<input type="hidden" name="group_type" value="${requestScope.group.group_type }">
    	<div id="group_name">
    		<span id="field_label">小组名称</span>
        	<input type="text" name="group_name"  value="${requestScope.group.group_name }">
    	</div>
       	<div id="group_intro">
    		<span id="field_label">小组介绍</span>
        	<textarea name="group_intro" >${requestScope.group.group_intro }</textarea>
    	</div>
        <div id="group_img">
        	<span id="field_label">小组图标</span>
            <img src="${requestScope.group.imgs}" width="auto" height="auto" style="max-width: 200px;max-height: 200px;">
            <a id="img_file">
    			<input type="file" id="file_input" name="imgs" />更新
			</a><br><label id="img_label"></label>
        </div>
        <div id="group_tag">
        	<span id="field_label">小组标签</span>
        	<input type="text" name="group_tag" value="${requestScope.group.group_tag }">
            <p>标签作为关键词可以被用户搜索到(最多5个标签，多个标签之间用空格隔开) </p>
        </div>
        <div id="group_nick">
        	<div id="field">成员名称</div>
            <div id="admin">小组管理员<br><br>
            	<input type="text" name="admin_nick"> 
            </div>
            <div id="member">小组成员<br><br>
            	<input type="text" name="member_nick"> 
            </div>
    	</div>
        <div id="br_null"></div>
        <input id="sub_save" type="submit" value="保存">
    </div>
    </form>
</div><!--end of editGroup_left-->

<div id="editGroup_right">
	<div id="back_home">
		<a href="">回小组</a>
    </div><!--end of editGroup_right--> </div>
  <script type="text/javascript">
$(function(){
	
	
});
window.onload = function(){
	var input_file = document.getElementById("file_input");
	input_file.onchange = function(){
	document.getElementById("img_label").innerHTML=this.value;
	}
	
}
  </script>
</body>
</html>

