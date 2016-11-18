<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>注册</title>
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<script type="text/javascript" src="lsr/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="lsr/js/register.js"></script>
<script type="text/javascript" src="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js"></script>
<script type="text/javascript" src="lsr/js/jquery.cityselect.js"></script>

</head>
<body>
<span id="11"></span>
	<div id="top">
		<div class="title">
			<div
				style="color:#279338; font-family:黑体; font-size:28px; float:left; position:relative;">
				<strong style="color:#279338; font-family:黑体; font-size:28px;">曲奇</strong>
			</div>
			<div
				style="color:#279338; font-family:黑体; font-size:32px; float:left; position:relative; padding-top:8px;">
				d</div>
			<div
				style="color:#2496CD; font-family:黑体; font-size:32px; float:left; position:relative; padding-top:8px;">
				o</div>
			<div
				style="color:#F7C690; font-family:黑体; font-size:32px; float:left; position:relative; padding-top:8px;">
				u</div>
			<div
				style="color:#279338; font-family:黑体; font-size:32px; float:left; position:relative; padding-top:8px;">
				b</div>
			<div
				style="color:#2496CD; font-family:黑体; font-size:32px; float:left; position:relative; padding-top:8px;">
				a</div>
			<div
				style="color:#F7C690; font-family:黑体; font-size:32px; float:left; position:relative; padding-top:8px;">
				n</div>
			<div
				style="color:#279338; font-family:黑体; font-size:24px; padding-top:8px; float:left; position:relative; padding-left:10px;">
				账号</div>
		</div>
	</div>
	<!---top-->

	<div id="left">
		<div class="register" style="padding-top:50px; padding-left:10px;">
			<form action="user/userinfo?method=UserSignin" method="post" name="register">
				<strong style="font-size:24px;">欢迎加入曲奇</strong><br>
				<br>
				<table style="width:400px; padding-left:20px">
					<tr>
						<td style="width:60px;">邮箱</td>
						<td><input type="email" name="email" class="form-control" placeholder="填写你的邮箱..." style="width:220px; height:34px;"></td>
					</tr>
					<tr style="height:20px;">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>密码</td>
						<td><input type="password" name="pwd" class="form-control" placeholder="你的密码..." style="width:220px; height:34px;"></td>
					</tr>
					<tr style="height:20px;">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>名号</td>
						<td><input type="text" name="username" class="form-control" placeholder="为你起一个响亮的名号吧..." style="width:220px; height:34px;"></td>
					</tr>
					<tr>
						<td></td>
						<td style="font-size:12px;color: #9E9994;">第一印象很重要，起个响亮的名号吧</td>
					</tr>
				</table>
				<br>
				<table style="width:400px; padding-left:20px" >
					<tr>
						<td style="width:60px;">常居地</td>
						<td><span style="font-size:12px;color: #9E9994;">曲奇猜你在:  </span> <span id="currentcity"></span>  
						<span style="font-size:12px;color: #9E9994;"> 没猜对？手动选择</span>
						<select name="location" id="city" style="width:100px; height:34px;text-align: center; ">	 
								<option id="sel" value=""><span></span></option>							
								<c:forEach var="c" items="${applicationScope.city }">						 
									<option value="${c.city_id }"><span>${c.city_desc }</span></option>
								</c:forEach>
						</select></td>
					</tr>
					<tr style="height:20px;">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>验证码</td>
						<td><input type="text" name="checkcode" class="form-control" placeholder="填写验证码..." style="width:220px; height:34px;"></td>
					</tr>
					<tr>
						<td></td>
						<td><img id="checkcode" src="user/validationServlet"
							height="60" width="220" /></td>
					</tr>
					<tr style="height:20px;">
						<td></td>
						<td></td>
					</tr>
					<tr style="height:20px;">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="checkbox"> 我已经认真阅读并同意曲奇《使用协议》 <br></td>
					</tr>
				</table>
				<br>
			</form>
			
			<br>
			<div align="center"
				style="width:50px; height:30px; background:#EFE1E2; margin-left:100px; padding-top:8px;">
				<a href="javascript:submit()" id="zhuce" style="text-decoration:none">注册</a>
		</div>
    </div>
	</div>
	<!--left-->
	<div id="right">
		<p style="padding-top:120px; padding-left:50px; font-size:12px;">
			> 已经拥有曲奇帐号?<a href="<%=basePath%>/lsr/douban_login.jsp" style="font-size:12px;">直接登录</a>
		</p>
		<br>
		<a style="padding-left:50px; font-size:12px;">> 点击下载曲奇移动应用</a>
	</div>
	<!--right-->

	<br>

	<!---底部栏--->
	<div id="all_bottom"
		style="height:30px; width:960px; margin-left:200px; bottom:-170px; position:absolute;">
		<hr style="border:1px dashed #000; height:1px">
		<div
			style="font-size:12px; float:left; position:relative;padding-top:5px;">©
			2005－2014 douban.com, all rights reserve</div>

		<div style="float:right;  position:relative;padding-top:5px;">
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
<link href="lsr/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="lsr/css/bootstrap-theme.css" rel="stylesheet" type="text/css">
</html>

<style>
a:link,a:visited{
 text-decoration:none;  /*超链接无下划线*/
 color:#000000;
}
a:hover{
 text-decoration:underline;  /*鼠标放上去有下划线*/
 color: #9E9994;
}
* {
	padding: 0;
	margin: 0;
	font-family: 宋体;
	font-size: 16px;
	 
}

#top {
	width: 1350px;
	height: 128px;
	background: #F8F8F8
}

.title {
	padding-left: 200px;
	padding-top: 66px;
}

#left {
	float: left;
	height: 550px;
	width: 600px;
	margin-left: 200px;
}

#right {
	width: 500px;
	float: left;
	position: relative;
	height: 450px;
}
</style>

