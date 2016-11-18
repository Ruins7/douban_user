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
<title>相册</title>
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
<script>
$(function(){
	
	//新建相册
	$('#add_album').click(function(){
		var addarround_height = document.body.scrollHeight;
		  var width = document.body.scrollWidth;
		  $('#add_album_div_arround').css('height',addarround_height);
		  $('#add_album_div_arround').css('width',width);	 
		  $('#add_album_div_arround,#add_album_div').fadeIn(800,function(){  
			  $('#add_album_div').css('display','block');
		  });  
	});
	
	//取消新建相册
	 $('#canceladdalbum').click(function(){
		 $('#add_album_div_arround,#add_album_div').fadeOut(800,function(){  
			  $('#add_album_div').css('display','none');
		  });
	 });
	 
	 //提交新建相册请求
	 $('#addalbum').click(function(){
		 document.formaddalbum.submit();
	 });
	
});
</script>

</head>
<body>
  <jsp:include page="/lsr/douban_head.jsp"></jsp:include>
  <div id="add_album_div_arround" style="display: none;"></div>
  <div id="add_album_div" style="border-radius:10px;background-color:#48A355;z-index:5;display: none;width: 800px;height: 450px; position: absolute;top:200px;left:300px;padding-top: 10px;padding-left: 80px;">
     <h3>创建一个新的相册</h3>
     <hr style="width: 550px;">
     <form action="user/userinfo?method=createAlbum" name="formaddalbum" method="post">
        <table>
            <tr style="height: 80px;">
                <td style="width:100px;">相册名称:</td>
                <td><input type="text" name="album_name" class="form-control" placeholder="给你的相册起个名字吧..." style="width:220px; height:34px;"> </td>
                <td ></td>
            </tr>
            <tr style="height: 80px;">
                 <td>相册介绍:</td>
                 <td><textarea id="bro" class="form-control" rows="3" cols="70%" placeholder="介绍一下你的相册..." name="simple_desc" style="color:#545652; border: 2px;padding-bottom: 10px;word-wrap:break-word;resize:none;" onKeyUp="words_sum_up()"></textarea>   </td>
                 <td></td>
            </tr>
            <tr style="height: 80px;">
                 <td>相册权限:</td>
                 <td><select name="album_root">
                         <option value="1">所有人可见</option>
                         <option value="2">仅对好友可见</option>
                         <option value="3">仅自己可见</option>
                      </select>
                 </td>
                 <td></td>
            </tr>           
            <tr style="height: 80px;">
                 <td> </td>
                 <td>
                 <img id="addalbum" style="cursor: pointer;" src="<%=basePath%>/lsr/img/baocunphoto.png"> <span id="canceladdalbum" style="cursor: pointer; width: 40px;height: 30px;font-size:18px;;background-color: #fff;margin-left: 50px;">取消</span>
                 </td>
                 <td></td>
            </tr>           
        </table>
     </form>
  </div>
<div id="left">
  <div id="myfocuspeople">
      <img src="<%=basePath%>${current_user.imgs}" width="80px" height="80px">
              ${current_user.username}的相册
              <div style="height: 12px; width: 500px;">
    	        <ul id="left_menu">
    	            <li><a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${requestScope.current_user.user_id}">${requestScope.current_user.username}的主页</a></li>
                    <li><a href="<%=basePath%>user/userinfo?method=searchMyBroByPage&user_id=${requestScope.current_user.user_id}&typepage=ul">广播</a></li>
                    <li><a href="<%=basePath%>user/userinfo?method=showUserAllAlbums&user_id=${requestScope.current_user.user_id}&typepage=ul">相册</a></li>
                    <li><a href="<%=basePath%>user/userinfo?method=showAllUserDiarys&user_id=${requestScope.current_user.user_id}&typepage=ul">日记</a></li>
                    <li><a href="<%=basePath%>user/userinfo?method=showAllUserLike&user_id=${requestScope.current_user.user_id}">喜欢</a></li>
                    <li><a href="<%=basePath%>doulist/doulistinfo?method=showMyDouList&user_id=${requestScope.current_user.user_id}">豆列</a></li>
                    <c:if test="${sessionScope.current_user.user_id == requestScope.current_user.user_id}">
                    <li><a href="<%=basePath%>user/sandmailnotice?method=showPersonalInfo&user_id=${requestScope.current_user.user_id}">设置</a></li>
                    </c:if>
                 </ul>
              </div>    
  </div><!-- myfocuspeople -->
  
  <hr style="width: 650px;float: left;">
  <div id="friends">
  <c:forEach var="album" items="${requestScope.myalbum }">
		   <div id="myalbums">
                   <a href="<%=basePath%>user/userinfo?method=showOneAlbum&album_id=${album.album_id}">
                     <img src="<%=basePath%>${album.imgs}" width="auto" height="auto" style="min-height:170px;min-width:170px;max-height: 190px;max-width: 190px;"><br>
                     <span>${album.album_name}</span><br>
                  </a>
                  <span style="color: #545652;"><fmt:formatDate value="${album.time }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
            </div>  
	 	    
 </c:forEach> 
		 <%--  <div id="mybro_more" style="width: 700px;float: left;font-size: 12px;height: auto;width: auto"></div>
		   <div style="width: 700px;float: left;text-align: center;font-size: 12px;"><a id="momybro" class="item" href="">-- 加载更多 --</a><input id="mybro_pagenum" type="hidden" value="${page.currentPage }"><input id="uid" type="hidden" value="${sessionScope.current_user.user_id}"></div> 			  --%>
  </div><!-- friends -->
  
</div><!-- left -->
 
 
   <div id="right"><!--右边-->
   <c:if test="${sessionScope.current_user.user_id==requestScope.current_user.user_id}">
       <br>
       <br>
       <br>
	   <a href="<%=basePath%>user/userinfo?method=showUserAllAlbums&user_id=${requestScope.current_user.user_id}&param=fazhaopian_album"><img src="<%=basePath%>lsr/img/fazhaopian_album.png"></a>
	   <br>
	   <br>
	   <br>
	   <img id="add_album" src="<%=basePath%>lsr/img/add_album.png" style="cursor:pointer">  
   </c:if>
   </div><!--右边-->
  
 <jsp:include page="/lsr/douban_foot.jsp"></jsp:include>
</body>

</html>

