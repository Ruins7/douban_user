<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script src="wj/js/jquery-2.1.1.js"></script>
<script src="wj/js/Myjs1.js"></script>
<base href="<%=basePath%>">

<title>佳片有约</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link type="text/css" href="wj/css/movieStyle1.css" rel="stylesheet">
<link href="wj/css/movieaInfo.css" rel="stylesheet" type="text/css">
<script src="wj/js/jquery-2.1.1.js"></script>
<script src="wj/js/Myjs1.js"></script>
</head>

<body>
<body style="width: 100%;height: auto;">
	<jsp:include page="/wj/douban_moviehead.jsp"></jsp:include>


	<p style="font-size: 24px;color: #2F92D5;font-weight: 900;margin-left: 200px;margin-top: 30px;">曲奇电影排行榜</p>
	 
	<div style="margin-left: 200px;">
		<div style="min-height:800px; width:600px;position:relative; float:left;"
			id="the_charts">
			<c:forEach var="object" items="${requestScope.listObjects}"
				varStatus="i" begin="0" end="10" step="1">
				<div style="width:590px;position:relative; float:left; margin-bottom:10px; border-top-style:dashed; border-top-width:1px;">
					<div style="width:130px; height:150px; position:relative; float:left;">
						<a href="/douban_user/servlet/movieServlet?method=findOneMovie&movie_id=${object[0]}"
							style="text-decoration:none; color:#208ACC"><img
							src=${object[9]}; style="height:140px; width:100px; margin:10px;"></a>
					</div>
					<div
						style="width:450px;position:relative; float:left; margin-top:10px;">
						<div style="font-size:18px">
							<a
								href="/douban_user/servlet/movieServlet?method=findOneMovie&movie_id=${object[0]}"
								style="text-decoration:none; color:#208ACC;font-size: 14px;">${object[1]}</a>
						</div>
						<div style="margin-top:5px;font-size:12px;height:110px">${object[3]}(${object[7]})/${object[5]}<br>${object[2]}……</div>
						<div style="margin-left:300;color:#208ACC;font-size:13px">点击次数${object[12]}</div>
					</div>
				</div>
			</c:forEach>



		</div>
		<div
			style="width:335px;position:relative; float:left;margin-left:5px;">
			<div style=" width:335px; position:relative; float:left">
				<div style=" width:335px; height:335px;">
					<img src="wj/img/douban4.png"
						style="width:310px; height:310px; margin:20px;">
				</div>
			</div>
			<div style=" margin-bottom:10px;margin-top:10px;margin-left: 20px;">
				<span style="font-size: 22px;color: #2F92CF;font-weight: 900;">曲奇电影TOP250</span> 
				<a
					href="javascript:void(0);"
					style="font-size:12px;text-decoration:none;margin-left:10px;color:#208ACC;"
					id="love">爱情</a> <a href="javascript:void(0);"
					style="font-size:12px;text-decoration:none;margin-left:10px;color:#208ACC;"
					id="movement">动作</a> <a href="javascript:void(0);"
					style="font-size:12px;text-decoration:none;margin-left:10px;color:#208ACC;"
					id="comedy">喜剧</a>
			</div>
			<div id="top250" style="margin-left: 50px;">
				<!--       <div style="width:80px; height:120px; position:relative; float:left;">
           <div style="width:80px; height:90px"><img src="wj/img/3.png" style=" width:75px; height:90px"></div>
           <div align="center">name</div>
         </div>
         
                  <div style="width:80px; height:120px; position:relative; float:left;">
           <div style="width:80px; height:90px"><img src="wj/img/3.png" style=" width:75px; height:90px"></div>
           <div align="center">name</div>
         </div>
                  <div style="width:80px; height:120px; position:relative; float:left;">
           <div style="width:80px; height:90px"><img src="wj/img/3.png" style=" width:75px; height:90px"></div>
           <div align="center" style="font-size:10px; margin:5px;">电影天堂通</div>
         </div>
                  <div style="width:80px; height:120px; position:relative; float:left;">
           <div style="width:80px; height:90px"><img src="wj/img/3.png" style=" width:75px; height:90px"></div>
           <div align="center">name</div>
         </div>
                  <div style="width:80px; height:120px; position:relative; float:left;">
           <div style="width:80px; height:90px"><img src="wj/img/3.png" style=" width:75px; height:90px"></div>
           <div align="center">name</div>
         </div>
                  <div style="width:80px; height:120px; position:relative; float:left;">
           <div style="width:80px; height:90px"><img src="wj/img/3.png" style=" width:75px; height:90px"></div>
           <div align="center">name</div>
         </div>
                           <div style="width:80px; height:120px; position:relative; float:left;">
           <div style="width:80px; height:90px"><img src="wj/img/3.png" style=" width:75px; height:90px"></div>
           <div align="center">name</div>
         </div>
                           <div style="width:80px; height:120px; position:relative; float:left;">
           <div style="width:80px; height:90px"><img src="wj/img/3.png" style=" width:75px; height:90px"></div>
           <div align="center">name</div>
         </div>
                           <div style="width:80px; height:120px; position:relative; float:left;">
           <div style="width:80px; height:90px"><img src="wj/img/3.png" style=" width:75px; height:90px"></div>
           <div align="center">name</div>
         </div> -->
			</div>

		</div>
		<div
			style="width:940px; position:relative; float:left;border-top-style:dashed; border-top-width:1px;margin-top:50px;">
			<div align="center">
				<a href="#"
					style=" text-decoration:none;font-size:11px ; color:#3377AA">关于曲奇</a>
				· <a href="#"
					style=" text-decoration:none; font-size:11px;color:#3377AA">联系我们
				</a>· <a href="#"
					style=" text-decoration:none;font-size:11px;color:#3377AA ">
					帮助中心 </a>· <a href="#"
					style=" text-decoration:none;font-size:11px;color:#3377AA ">开发者
					移动应用 · 曲奇广告</a>· <a href="#"
					style=" text-decoration:none;font-size:11px;color:#3377AA ">
					曲奇广告</a>
			</div>
		</div>
	</div>
	</div>

</body>
</body>
</html>

