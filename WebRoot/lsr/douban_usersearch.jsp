<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>查询用户</title>
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
<script src="lsr/js/jquery-2.1.1.js"></script>
<script src="lsr/js/bootstrap.min.js"></script>
<script src="lsr/js/douban_loginAfter.js"></script>
<script src="lsr/js/jquery-ui.js"></script>
<script>
$(function(){
	 $('.invo').click(function(e){
		e.preventDefault();
	 });
	 
	 //取消关注某用户
	 $('.cancelfocus').click(function(e){
		 e.preventDefault();
		 var user_id = $(this).prev().val();
		 var url = '/douban_user/user/userinfo?method=cancelFocusOtherUser';
			$.ajax({
				type : 'post',
				url : url,
				dataType : 'json',
				data : {to_user:user_id},
				success : function(json, status, xhr) {
					if(json == true){
	                	   alert("取消关注成功！");
	                   }else{
	                	   alert("取消关注失败！");
	                   }
				},
				error : function(responseText, status, xhr) {
					//alert(responseText + "   " + status + "   " + xhr);
					alert("取消关注失败！");
				}
			});
	 });
	 
	 //关注某人
	 $('.focus').click(function(e){
		 e.preventDefault();
		 var user_id = $(this).prev().val();
		 var url = '/douban_user/user/userinfo?method=FocusOtherUser';
			$.ajax({
				type : 'post',
				url : url,
				dataType : 'json',
				data : {to_user:user_id},
				success : function(json, status, xhr) {
					if(json == true){
	                	   alert("关注成功！");
	                   }else{
	                	   alert("关注失败！");
	                   }
				},
				error : function(responseText, status, xhr) {
					//alert(responseText + "   " + status + "   " + xhr);
					alert("关注失败！");
				}
			});
	 });
});
</script>
</head>
<body>
  <jsp:include page="/lsr/douban_head.jsp"></jsp:include>
<div id="left">
  <div id="myfocuspeople">查询到的用户</div>
  <div id="group"> 
     <ul>
        <li><a href="#" class="invo">全部</a></li>
        <li><a href="#" class="invo">朋友</a></li>
        <li><a href="#" class="invo">未分组</a></li>
     </ul>
 </div>
  <hr style="width: 650px;float: left;">
  <div id="friends">
  
     <c:forEach var="list" items="${requestScope.friendslist }">  
     <div id="friend">
         <a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${list.user_id }"><img src="<%=basePath%>${list.imgs }" width="60px" height="60px" style="border-radius:30px;"><span id="name">${list.username }</a><span style="font-size: 12px;color: #545652;"> ${list.city.city_desc }</span> <span style="font-size: 12px;color: #545652;">(${list.user_desc })</span></span>
         <span style="float: right;margin-top: 20px;margin-right: 10px;">
        <input type="hidden" value="${list.user_id }">
        <c:if test="${list.gaunzhu == true }">
        <a href="#" class="cancelfocus">取消关注</a></span>
        </c:if>
        <input type="hidden" value="${list.user_id }">
        <c:if test="${list.gaunzhu == false }">
        <a href="#" class="focus">关注</a></span>
        </c:if>
         
     </div>
     </c:forEach>
  </div>
  

</div><!-- left -->
   <div id="right"><!--右边-->
	  <div class="fix_1">
	      <div style="padding-top:15px; color:#007722; padding-left:15px; font-size:14px">曲奇正在发生 · · · · · ·</div>
		  <br>
		  <ul id="fix_1_ul" style="padding-left:15px; font-size:14px;list-style-type: circle;margin-left: 20px;color: #3377AA;">
		     <li><a href="#">金羊毛开听：给欧巴赚钱</a></li>
			 <br>
			 <li><a href="#">金羊毛开听：给欧巴赚钱</a></li>
			 <br>
			 <li><a href="#">金羊毛开听：给欧巴赚钱</a></li>
			 <br>
			 <li><a href="#">金羊毛开听：给欧巴赚钱</a></li>
		  </ul>
		  <div style="margin-top:20px;padding-top: 20px;font-size: 12px;">
		           <p style=" color:#007722; padding-left:5px; font-size:14px">我的关注···(成员：<a class="item" href="<%=basePath%>user/userinfo?method=MyFocus&user_id=${sessionScope.current_user.user_id}"> ${sessionScope.myfocusnum }  </a>)</p>
		    <div id="myFocus">
		       <ul>
		       <c:forEach items="${sessionScope.myfocus}" var="myfriend">
		            <li><a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${myfriend.user_id }">
		                <img src=" ${myfriend.imgs }" width="40px;" height="40px;" style="border-radius:20px;">
		                </a>
		            </li> 
		       </c:forEach>    
               </ul>
		    </div>
		    <p style="color:#007722;">→ 我被  <a class="item invo" href="#">${sessionScope.whofocusme }</a>人关注</p>
		  </div>
		  <div style="padding-top:20px;">
		     <img src="lsr/img/af2.png">
		  </div>
			 <img src="lsr/img/af1.png">
		  </div> 
      </div><!--右边-->
 <jsp:include page="/lsr/douban_foot.jsp"></jsp:include>
</body>

</html>

