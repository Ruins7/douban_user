<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!doctype html>
<html>
<head>
<base href="<%=basePath%>">

<title>同城活动</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="hyy/css/douban_city.css" rel="stylesheet" type="text/css">
<script src="hyy/js/jquery-2.1.1.js"></script>
<script src="hyy/js/bootstrap.min.js"></script>
<script src="hyy/js/jquery.js"></script>
<script src="hyy/js/jquery-ui.js"></script>
<!--     <script src="hyy/js/hyy.js"></script> -->
<script type="text/javascript" src="hyy/js/jquery.cityselect.js"></script>
<script type="text/javascript"
	src="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js"></script>
</head>
<script>
	$(function() {
		$('#faqihuodong')
				.click(
						function() {
							window.location.href = '/douban_user/hyy/service/ActivityServlet?method=searchAllActivityType&request_type=addactivity';
						});
	});
</script>
<body>
	<jsp:include page="/hyy/activity/douban_activityhead.jsp"></jsp:include>


	<div style="background-color: #F6F5F2;float: left;width: 640px;margin-left: 200px;margin-top: 30px;border-radius:20px;border:1px solid #ccc;box-shadow: 5px 5px 5px #ccc;padding: 20px;margin-bottom: 30px;">
		<!--左边--> 
		<!--///////////////////////热门活动/////////////////////////////////-->
		 
		
		<div style="float: left;width: 600px;height:auto;">
			<p style="color: #664433;font-size: 16px;font-weight: 700;">热门线下活动······</p>
			<br>
			<c:forEach var="off" items="${offline_list }" begin="0" end="3"
				step="1">
				<div
					style="text-align:center; width:150px; height:230px; float:left; position:relative;">
					<div>
						<img src="<%=basePath%>${off.imgs}" width="120px" height="170px">
					</div>
					<div>
						<a href="<%=basePath%>hyy/service/ActivityServlet?method=searchOneOffActivity&activity_id=${off.offactivity_id}">${off.offactivity_title}</a>
					</div>
				</div>
			</c:forEach>
		</div>
		
		<div style="float: left;width: 600px;height:auto;">
		<p style="color: #664433;font-size: 16px;font-weight: 700;">热门线上活动······</p>
		<br> 
			<c:forEach var="on" items="${online_list }" begin="0" end="3"
				step="1">
				<div
					style="text-align:center;width:150px; height:220px; float:left; position:relative;">
					<div>
						<img src="<%=basePath%>${on.post}" width="120px" height="170px">
					</div>
					<div>
						<a href="#" class="invo" style="font-size: 14px;">${on.onlineActivity_title}</a>
					</div>
				</div>
			</c:forEach>
		</div>
		
		<!--///////////////////////热门活动结束/////////////////////////////////-->
        <p style="color: #664433;font-size: 16px;font-weight: 700;">热门活动分类······</p>
        <br>
		<div style="float: left;width: 600px;height:auto;line-height: 250%;margin-bottom: 20px;">
			<c:forEach var="type" items="${type_list}">
	       [<a href="<%=basePath%>hyy/service/ActivityServlet?method=searchAllOffActivityByOneType&type_id=${type.type_id }" style="font-size: 12px;color: #2A6496;">${type.type_name }</a>]
	 </c:forEach>
		</div>
		<!--   //////////////////活动分类结束////////////////-->
       <p style="color: #664433;font-size: 16px;font-weight: 700;">曲奇猜你们可能感兴趣的活动······</p>
        <br>
		<c:forEach var="map" items="${type_off_map}">
		<div style="float: left;width: 600px;height: auto;">
				<span style="color: #664433;font-size: 16px;font-weight: 700;float: left;width: 600px;">[${map.key.type_name}]</span>
				<div style="height:auto;min-height:200px;width:600px;margin-top: 20px;float: left;">
					<c:forEach var="mvalue" items="${map.value }" begin="0" end="3"
						step="1">
						<div style="width: 300px;height:auto;float: left;margin-bottom: 20px;">
						<img src="<%=basePath%>${mvalue.imgs}" width="120px"
							height="170px" style="float: left;">
						<div style="float:left; width:160px; height:170px;margin-left: 10px;">
							<a href="<%=basePath%>hyy/service/ActivityServlet?method=searchOneOffActivity&activity_id=${mvalue.offactivity_id}">${mvalue.offactivity_title }</a><br>
							<p style="font-size:12px; color:#666666">
								<fmt:formatDate value="${mvalue.start_time }"
									pattern="yyyy-MM-dd HH:mm:ss" /></p>
							<p style="font-size:12px; color:#666666">至</p>
							<p style="font-size:12px; color:#666666">
								<fmt:formatDate value="${mvalue.end_time }"
									pattern="yyyy-MM-dd HH:mm:ss" /></p>
							<p style="font-size:12px; color:#666666">${mvalue.city.city_desc } ${mvalue.position }</p>
						</div>
			            </div>
		          </c:forEach>
		       </div>
	   </div>
	   </c:forEach>

	<!--////////////////////////////////分类别显示线下活动/////////////////-->
    </div>
	<!--左边-->

	<div style="width: 400px;float: left;height: auto;margin-left: 30px;margin-top:30px;padding: 20px; ">
		<div style="height:60px; padding-top:10px;">
			<img src="<%=basePath%>/lsr/img/faqihuodong.png"
				style="cursor: pointer;" id="faqihuodong">
		</div>
		<p style="color: #664433;font-size: 12px;">官方预售</p>
		<hr>
		<tr>
			<div class="right_left">
				<img src="hyy/img/q4.png">
			</div>
			<div class="right_right">
				<a href="#" style="text-decoration:none">北纬零度无厘头感人话剧《名字没想好》9月10月@天津.曹禺剧院</a><br>09月06日
				19:30-21:30 …
			</div>

		</tr>

		<tr>
			<div class="right_left">
				<img src="hyy/img/q4.png">
			</div>
			<div class="right_right">
				<a href="#" style="text-decoration:none">北纬零度无厘头感人话剧《名字没想好》9月10月@天津.曹禺剧院</a><br>09月06日
				19:30-21:30 …
			</div>

		</tr>

		<tr>
			<div class="right_left">
				<img src="hyy/img/q4.png">
			</div>
			<div class="right_right">
				<a href="#" style="text-decoration:none">北纬零度无厘头感人话剧《名字没想好》9月10月@天津.曹禺剧院</a><br>09月06日
				19:30-21:30 …
			</div>

		</tr>
		<div>
			<img src="hyy/img/xx.png">
		</div>
		<p style="color: #664433;font-size: 12px;margin-top: 30px;">天津活跃的主办方</p>
		<hr>
		<tr>
			<div class="right_left">
				<img src="hyy/img/q4.png">
			</div>
			<div class="right_right">
				<a href="#" style="text-decoration:none">北纬零度无厘头感人话剧《名字没想好》9月10月@天津.曹禺剧院</a><br>09月06日
				19:30-21:30 …
			</div>
		</tr>
		<tr>
			<div class="right_left">
				<img src="hyy/img/q4.png">
			</div>
			<div class="right_right">
				<a href="#" style="text-decoration:none">北纬零度无厘头感人话剧《名字没想好》9月10月@天津.曹禺剧院</a><br>09月06日
				19:30-21:30 …
			</div>
		</tr>
		<tr>
			<div class="right_left">
				<img src="hyy/img/q4.png">
			</div>
			<div class="right_right">
				<a href="#" style="text-decoration:none">北纬零度无厘头感人话剧《名字没想好》9月10月@天津.曹禺剧院</a><br>09月06日
				19:30-21:30 …
			</div>
		</tr>
		<tr>
			<div class="right_left">
				<img src="hyy/img/q4.png">
			</div>
			<div class="right_right">
				<a href="#" style="text-decoration:none">北纬零度无厘头感人话剧《名字没想好》9月10月@天津.曹禺剧院</a><br>09月06日
				19:30-21:30 …
			</div>
		</tr>


		<div>
			<img src="hyy/img/city1.png">
		</div>


	</div>
	<!--///////////////////////////////////////右边////////////////////-->
</body>
</html>




<!--

//-->
</script>
