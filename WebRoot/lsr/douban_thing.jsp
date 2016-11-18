<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>东西</title>
<link rel="icon" href="lsr/img/quqi.ico">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<link href="lsr/css/douban_thing.css" rel="stylesheet" type="text/css">
<link href="lsr/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="lsr/css/bootstrap-theme.css" rel="stylesheet"
	type="text/css">
<link href="lsr/css/jquery-ui.css" rel="stylesheet">
<script src="lsr/js/jquery-2.1.1.js"></script>
<script src="lsr/js/bootstrap.min.js"></script>

<script src="lsr/js/jquery-ui.js"></script>
<script src="lsr/js/douban_thing.js"></script>
<script>
$(function(){
	
});
</script>
</head>


<body>
	<jsp:include page="/lsr/douban_thinghead.jsp"></jsp:include>
 <div style="margin-left:200px;width:970px;height: auto;margin-top: 10px;float: left;">	
    <div style="width: 150px;text-align: center;float: left;margin-top: 10px;background-color: #fff;border-radius:5px;box-shadow: 3px 6px 10px #ccc;padding-top: 7px;">
      <c:forEach var="thingtype" items="${types }">  
      <p style="border-bottom: 1px solid #EFEFEF;height: 30px;"><input type="hidden" value="${thingtype.things_type_id }"><a href="#" class="things_type" style="font-size:16px;color: #985D3E;">${thingtype.type_name}</a></p> 
      </c:forEach>  
    </div>
    
    <div style="width: 80%;float: left;height: auto;background-color: #fff;margin-left: 30px;margin-top: 10px;box-shadow: 3px 6px 10px #ccc;border-radius:5px;">
	     <div style="width: 95%;height:auto;margin-left: auto;margin-right: auto;"><!--right_top-->
			<p style="padding-top:20px; padding-left:50px;">
				<strong>他们活跃在东西...</strong>
			</p>
			<c:forEach var="threeuser" items="${threeuser }">
			<div style="float: left;width: 220px;height:auto;margin-left: auto;margin-right: auto;">
				<div style=" padding-top:30px;float:left;width:230px; height:130px;text-align: center;">
					<a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${threeuser.user_id }"><img src="<%=basePath%>${threeuser.imgs}" width="70px" height="70px" style="border-radius:35px;"></a>
					<p style="font-size: 13px;color: #333333;">${threeuser.username }</p>
				</div>	 
			</div>
			</c:forEach>
		</div> <!--right_top-->
		
		
		<div style="float: left;width: 100%;height:auto;margin-left: auto;margin-right: auto;margin-bottom: 75px;">
			<div style="padding-top:30px;float:left;width:255px; height:270px;text-align: center;">
				<div style="border: 1px solid #EFEFEF;float: left;margin-left: 10px;box-shadow: 3px 3px 6px #ccc;">
				   <c:forEach var="one" items="${oneuserthings }">
				    <a href="<%=basePath%>things/thingsinfo?method=showOneThing&things_id=${one.things_id}"><img src="<%=basePath%>${one.imgs}" width="110px" height="110px" style="float: left;margin: 5px;"></a>
				   </c:forEach>
				</div>
				<p style="float: left;margin-left: 100px;margin-top: 10px;font-size: 14px;font-weight: 900;">Ta的东西</p>
			</div>
			 <div style="padding-top:30px;float:left;width:255px; height:270px;text-align: center;">
				<div style="border: 1px solid #EFEFEF;float: left;margin-left: 10px;box-shadow: 3px 3px 6px #ccc;">
				   <c:forEach var="two" items="${twouserthings }">
				    <a href="<%=basePath%>things/thingsinfo?method=showOneThing&things_id=${two.things_id}"><img src="<%=basePath%>${two.imgs}" width="110px" height="110px" style="float: left;margin: 5px;"></a>
				   </c:forEach>
				</div>
				<p style="float: left;margin-left: 100px;margin-top: 10px;font-size: 14px;font-weight: 900;">Ta的东西</p>
			</div>
			<div style="padding-top:30px;float:left;width:255px; height:270px;text-align: center;">
				<div style="border: 1px solid #EFEFEF;float: left;margin-left: 10px;box-shadow: 3px 3px 6px #ccc;">
				    <c:forEach var="three" items="${threeuserthings }">
				    <a href="<%=basePath%>things/thingsinfo?method=showOneThing&things_id=${three.things_id}"><img src="<%=basePath%>${three.imgs}" width="110px" height="110px" style="float: left;margin: 5px;"></a>
				   </c:forEach>
				</div>
				<p style="float: left;margin-left: 100px;margin-top: 10px;font-size: 14px;font-weight: 900;">Ta的东西</p>
			</div>
		</div>
	</div>
	
</div>

<div id="thing_side">
	 
		<c:set var="page" value="${requestScope.mythings }"></c:set>
		<c:forEach var="things" items="${page.data }">
			<div class="th">
				<div class="thingsimg">
					<a href="<%=basePath%>things/thingsinfo?method=showOneThing&things_id=${things.things_id}">
						<img src="<%=basePath%>${things.imgs}" width="300xp" height="300px">
					</a>
				</div>
				<div class="thingsname">${things.things_name }</div>
				<div class="thingsuser">
					<img src="<%=basePath%>${things.f_user.imgs }" width="40px" height="40px" style="border-radius:20px;"> ${things.f_user.username } 发布:
				</div>
				<div class="thingscommon">
					<img src="lsr/img/frontyinhao.png"> ${things.simple_desc } 
					<img src="lsr/img/afteryinhao.png">
				</div>
				<div class="thingsbottom">
					<span style="float: left;"><fmt:formatDate value="${things.time }" pattern="yyyy-MM-dd" /></span>
					<input type="hidden" value="${things.things_id }">
					<a href="#" class="likeit" style="float: left;margin-left: 150px;color: #7D4B2F;">喜欢</a>
				</div>
			</div>
		</c:forEach>

	</div>
	<input id="pagenum" type="hidden" value="1">
	 <jsp:include page="/lsr/douban_foot.jsp"></jsp:include>
</body>

</html>
