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
<title>添加照片</title>
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
<script src="lsr/js/douban_addphoto.js"></script>
<script>
$(function(){
	 
	$('#addphoto').click(function(){
		document.formaddphoto.submit();
	});
	
});
</script>
</head>
<body>
  <jsp:include page="/lsr/douban_head.jsp"></jsp:include>
<div id="left">
  <div id="myfocuspeople">
      上传照片
  </div><!-- myfocuspeople -->
  <hr style="width: 650px;float: left;">
  <form action="user/userinfo?method=uploadPhoto" name="formaddphoto" method="post" enctype="multipart/form-data">
  <br>
  <img id="add" style="cursor:pointer;" src="<%=basePath%>/lsr/img/image_of_add_img.png" Onclick="uploadbroimg.click();this.value=uploadbroimg.value;"> 
  <input id="uploadbroimg" name="photo_address" type="file" style="display:none">
  <div id="add_preview" style="background-color: #f8f8f8;width: 800px;min-height: 300px;height:auto;margin-left: auto;margin-right: auto;margin-top:20px;padding-left: 80px;padding-top: 20px;">
  
  </div><!-- add_preview -->
  <table style="width: 600px;height: 160px;">
     <tr>
         <td style="width:200px;font-size: 16px;font-weight: 800;">选择相册:</td>
         <td style="width: 800px;">
         <select name="from_album" style="width:150px; height:34px;text-align: center;border-radius:5px 5px 5px 5px; ">	 								 						
				<c:forEach var="album" items="${requestScope.myalbum}">						 
					 <option value="${album.album_id }">${album.album_name}</option>
				</c:forEach>
         </select>
         </td>
           <td style="height: 100px;"></td>
     </tr>
     <tr>
         <td style="font-size: 16px;font-weight: 800;">照片名称:</td>
         <td><input type="text" name="photo_name" class="form-control" placeholder="给你的照片起个名字吧..." style="width:220px; height:34px;"> </td>
         <td style="height: 100px;"></td>
     </tr>
     <tr>
         <td style="font-size: 16px;font-weight: 800;">照片描述:</td>
         <td><textarea id="bro" class="form-control" rows="3" cols="90%" name="simple_desc" style="color:#545652; border: 2px;padding-bottom: 10px;word-wrap:break-word;resize:none;" onKeyUp="words_sum_up()"></textarea>   </td> 
         <td  style="height: 100px;"></td>   
     </tr>
     <tr>
         <td style="font-size: 16px;font-weight: 800;"><img id="addphoto" style="cursor: pointer;" src="<%=basePath%>/lsr/img/baocunphoto.png"></td>
         <td></td> 
         <td  style="height: 100px;"></td>   
     </tr>
     </form>
  </table>  
     
  <br>
     
    
  
  
</div><!-- left -->
 <jsp:include page="/lsr/douban_foot.jsp"></jsp:include>
</body>

</html>

