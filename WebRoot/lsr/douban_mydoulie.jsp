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
<title>豆列</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="lsr/css/douban_loginAfter.css" rel="stylesheet"type="text/css">
<link href="lsr/css/douban_doulie.css" rel="stylesheet"type="text/css">
<link href="lsr/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="lsr/css/bootstrap-theme.css" rel="stylesheet" type="text/css">
<link href="lsr/css/jquery-ui.css" rel="stylesheet">
<link href="lsr/css/douban_common.css" rel="stylesheet">
<link href="lsr/css/douban_mydoulie.css" rel="stylesheet">
<script src="lsr/js/jquery-2.1.1.js"></script>
<script src="lsr/js/bootstrap.min.js"></script>
<script src="lsr/js/douban_loginAfter.js"></script>
<script src="lsr/js/jquery-ui.js"></script>
<script src="lsr/js/douban_myalbum.js"></script>
<script>
$(function(){
 
	//分别显示 创建的豆列  和 关注的豆列
	 $('#a_create').click(function(e){
		 e.preventDefault(); 
		 $('#friends').removeAttr('style');
		 $('#focus').removeAttr('style');
		 $('#friends').css('display','black');
		 $('#focus').css('display','none');
		 $('#makenewdoulie').css('display','block');
	 });
	 $('#a_focus').click(function(e){
		 e.preventDefault(); 
		 $('#friends').removeAttr('style');
		 $('#focus').removeAttr('style');
		 $('#friends').css('display','none');
		 $('#focus').css('display','black');
		 $('#makenewdoulie').css('display','none');
	 });
	 
	   //新建豆列
		$('#clicktomakedoulie').click(function(){
			var addarround_height = document.body.scrollHeight;
			  var width = document.body.scrollWidth;
			  $('#add_doulie_div_arround').css('height',addarround_height);
			  $('#add_doulie_div_arround').css('width',width);	 
			  $('#add_doulie_div_arround,#add_doulie_div').fadeIn(800,function(){  
				  $('#add_doulie_div').css('display','block');
			  });  
		});
		
		//取消新建豆列
		 $('#canceladddoulie').click(function(){
			 $('#add_doulie_div_arround,#add_doulie_div').fadeOut(800,function(){  
				  $('#add_doulie_div').css('display','none');
			  });
		 });
		 
		 //提交新建豆列请求
		 $('#adddoulie').click(function(){
			 document.formadddoulie.submit();
		 });
	
});
</script>

</head>
<body>
  <jsp:include page="/lsr/douban_head.jsp"></jsp:include>
    <div id="add_doulie_div_arround" style="display: none;"></div>
    <div id="add_doulie_div" style="border-radius:10px;background-color:#48A355;z-index:5;display: none;width: 800px;height: 400px; position: absolute;top:200px;left:300px;padding-top: 20px;padding-left: 80px;">
    <h3>创建一个新的豆列</h3>
    <hr style="width:600px;">
    <form action="doulist/doulistinfo?method=createMyDouList" name="formadddoulie" method="post">
        <table>
            <tr style="height: 80px;">
                <td style="width:100px;">豆列名称:</td>
                <td><input type="text" name="list_name" class="form-control" placeholder="给你的豆列起个名字吧..." style="width:220px; height:34px;"> </td>
                <td ></td>
            </tr>
            <tr style="height: 80px;">
                 <td>豆列类型:</td>
                 <td><select name="content_type" class="form-control">
                         <option value="1">活动</option>
                         <option value="2">书籍</option>
                         <option value="3">电影</option>
                         <option value="4">东西</option>
                 </select>
                 </td>
                 <td></td>
            </tr>           
            <tr style="height: 80px;">
                 <td> </td>
                 <td>
                 <img id="adddoulie" style="cursor: pointer;" src="<%=basePath%>/lsr/img/makedoulie.png"> <span id="canceladddoulie" style="cursor: pointer; width: 40px;height: 30px;font-size:18px;;background-color: #fff;margin-left: 50px;">取消</span>
                 </td>
                 <td></td>
            </tr>           
        </table>
     </form>
  </div>
<div id="left">
  <div id="myfocuspeople">
      <img src="<%=basePath%>${current_user.imgs}" width="80px" height="80px">
              ${current_user.username}的豆列
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
  <ul id="doulieul" style="margin-bottom: 30px;">
     <li><a href="#" id="a_create">创建的豆列</a></li>
     <li><a href="#" id="a_focus">关注的豆列</a></li> 
  </ul>  
  <div id="friends" style="display: block;">
  <c:forEach var="mycreated" items="${requestScope.my_created_doulie }">	    
	<div style="width: 600px;height: auto;margin-bottom: 30px;">
          <p style="background-color: #F2FBF2;color: #3377AA;border-radius:5px;text-indent: 2em;">[${mycreated.content_type_table.type_name }] : <a href="<%=basePath%>doulist/doulistinfo?method=showOneDouList&doulie_id=${mycreated.doulist_id}&doulie_type=${mycreated.content_type_table.content_type_id}" style="color: #3377AA;">${mycreated.list_name }</a> </p>
          <span> <fmt:formatDate value="${mycreated.time }" pattern="yyyy-MM-dd HH:mm:ss"/></span>           
    </div>  
 <hr> 
 </c:forEach> 
  </div><!-- friends -->
  
  <div id="focus" style="display: none;" >
   <c:forEach var="myfocused" items="${requestScope.my_focused_doulie }">	    
		   <div style="width: 600px;height: auto;margin-bottom: 50px;">
                  <img src="<%=basePath%>${myfocused.belong_who.imgs}" width="50px" height="50px" style="border-radius:25px;float: left;margin-bottom: 20px;">
                  <div style="float:right;width:530px;margin-bottom: 20px;">
                  <p style="float:right;width:530px;background-color: #F2FBF2;color: #3377AA;border-radius:5px;text-indent: 2em;"> ${myfocused.belong_who.username } 的 [${myfocused.content_type_table.type_name }] : <a href="<%=basePath%>doulist/doulistinfo?method=showOneDouList&doulie_id=${myfocused.doulist}&doulie_type=${myfocused.content_type_table.content_type_id}" style="color: #3377AA;">${myfocused.u_doulist.list_name } </a></p>
                  <p> <fmt:formatDate value="${myfocused.time }" pattern="yyyy-MM-dd HH:mm:ss"/></p>           
                  </div> 
           </div>
	 	  
 </c:forEach> 
  </div><!-- focus -->
</div><!-- left -->
 
 
   <div id="right"><!--右边-->
        <div id="makenewdoulie" style="display: block;margin-top:200px;">
            <img src="<%=basePath%>/lsr/img/makenewdoulie.png" style="cursor: pointer;" id="clicktomakedoulie">
        </div> 
   </div><!--右边-->
  
 <jsp:include page="/lsr/douban_foot.jsp"></jsp:include>
</body>

</html>

