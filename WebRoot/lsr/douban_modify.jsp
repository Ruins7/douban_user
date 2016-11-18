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
<title>修改个人信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="lsr/css/douban_loginAfter.css" rel="stylesheet"type="text/css">
<link href="lsr/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="lsr/css/bootstrap-theme.css" rel="stylesheet" type="text/css">
<link href="lsr/css/jquery-ui.css" rel="stylesheet">
<script src="lsr/js/jquery-2.1.1.js"></script>
<script src="lsr/js/bootstrap.min.js"></script>
<script src="lsr/js/jquery-ui.js"></script>
<script src="lsr/js/douban_modify.js"></script>
<script src="lsr/js/jquery.cityselect.js"></script>
<script type="text/javascript" src="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js"></script>
<script>
$(function(){
	$('.invo').click(function(e){
		e.preventDefault();
	});
});
</script>

</head>
<body>
  <jsp:include page="/lsr/douban_head.jsp"></jsp:include>
   <div id="left" style="margin-left: 300px;margin-top: 70px;">
      <span id="mess"></span>
      <form action="user/userinfo?method=UserModifyInfo" enctype="multipart/form-data" method="post" name="modify">			 
				<br>
				<table style="width:400px; padding-left:20px">
					<tr>
						<td style="width:80px;">邮箱</td>
						<td><input type="email" name="email" class="form-control" placeholder="${this_people.email }" value="${this_people.email }" style="width:220px; height:34px;"></td>
				    </tr>
					<tr style="height:20px;">
						<td><input type="hidden" id="this_id" name="user_id" value="${this_people.user_id }"></td>
						<td><input type="hidden" id="curr_id" name="user_id" value="${sessionScope.current_user.user_id }"></td>
						<td></td>
					</tr>
					<tr>
						<td>名号</td>
						<td><input type="text" name="username" class="form-control" placeholder="${this_people.username}" value="${this_people.username}" style="width:220px; height:34px;"></td>	
					</tr>
					<tr style="height:20px;">
						<td></td>
						<td></td>
					</tr>
				</table>
				<br>
				<table style="width:400px; padding-left:20px" >
					<tr>
						<td style="width:80px;">常居地</td>
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
						<td>修改头像</td>
						<td id="preview2"><img src="<%=basePath%>${this_people.imgs}" width="auto" height="auto" style="max-height: 220px;max-width: 220px;"></td>
						<td></td>	
					</tr>
					<tr>
						<td></td>	
						<td><input type="file" id="uploadbroimg" name="imgs"> </td>	
					</tr>
					<tr style="height:20px;">
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>验证码</td>
						<td>
						<input type="text" name="checkcode" class="form-control" placeholder="输入验证码..." style="width:220px; height:34px;">
						</td>
					</tr>
					<tr>
						<td></td>
						<td><img id="checkcode" src="user/validationServlet"
							height="60" width="220" style="border-radius:5px;"/></td>
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
				<div align="center"
				style="width:80px; height:40px; background:#EFE1E2; margin-left:100px; padding-top:8px;">
				<a href="javascript:submit()" id="confirm" style="text-decoration:none">确认提交</a>
		</div>
			</form>
   </div>
	
   
   
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
		           <p style=" color:#007722; padding-left:5px; font-size:14px">我的关注···(成员：<a class="item" href="<%=basePath%>user/userinfo?method=MyFocus&user_id=${sessionScope.current_user.user_id}">${sessionScope.myfocusnum}</a>)</p>
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
		  
      </div><!--右边-->
 
</body>
</html>

