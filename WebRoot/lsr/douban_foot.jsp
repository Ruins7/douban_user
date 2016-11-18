<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="lsr/css/douban_loginAfter.css" rel="stylesheet"type="text/css">
<link href="lsr/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="lsr/css/bootstrap-theme.css" rel="stylesheet" type="text/css">
<link href="lsr/css/jquery-ui.css" rel="stylesheet">
<link href="lsr/css/douban_common.css" rel="stylesheet">
<link href="lsr/css/douban_myalbum.css" rel="stylesheet">
<script src="lsr/js/jquery-2.1.1.js"></script>
<script src="lsr/js/bootstrap.min.js"></script>
<script src="lsr/js/douban_loginAfter.js"></script>
<script src="lsr/js/jquery-ui.js"></script>
<script src="lsr/js/douban_myalbum.js"></script>
</head>
<body>
<!---底部栏--->
	<div id="all_bottom" style="height:30px; width:960px; margin-left:150px;float: left;">
		<hr style="margin: 0px;">
		<div style="font-size:12px; float:left; position:relative;padding-top:5px;color: #3377AA;">©
			2005－2014 Ruins7.com, all rights reserve</div>
		<div style="float:right;  position:relative;">
			<a href="#"
				style=" text-decoration:none;font-size:11px ; color:#3377AA">关于曲奇</a>
			· <a href="#"
				style=" text-decoration:none;font-size:11px ;color:#3377AA">在曲奇工作</a>·
			<a href="#"
				style=" text-decoration:none; font-size:11px;color:#3377AA">联系我们
			</a>· <a href="#"
				style=" text-decoration:none;font-size:11px;color:#3377AA ">
				免责声明</a>· <a href="#"
				style=" text-decoration:none;font-size:11px;color:#3377AA ">
				帮助中心 </a>· <a href="#"
				style=" text-decoration:none;font-size:11px;color:#3377AA ">开发者
				移动应用 · 曲奇广告</a>· <a href="#"
				style=" text-decoration:none;font-size:11px;color:#3377AA ">
				移动应用 </a>· <a href="#"
				style=" text-decoration:none;font-size:11px;color:#3377AA ">
				曲奇广告</a>
		</div>
	</div>
	<!---底部栏--->
</body>
</html>

