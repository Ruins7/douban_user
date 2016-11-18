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
<title>日志</title>
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

	//发表日志评论
	$('#send_diary_comment').click(function(){
		var content = $('#content').val();
		var from_user = $('#from_user').val();
		var item = $('#item').val();
		var item_id = $('#item_id').val();
		var url = '/douban_user/user/userinfo?method=giveSomethingCommons';				 
		 $.ajax({
		     type : 'post',
		     url : url,
		     dataType : 'json',
		     data : {content : content,from_user:from_user,item:item,item_id:item_id},
		     success : function(json, status, xhr){
		          alert("success "+json+"   "+status+"   "+xhr);	          
		          if(json == false){
		             alert("评论失败！");
		          }else{
		        	  alert("评论成功！");
	              }	       	                      
		     },
		     error : function(responseText, status, xhr){
		          //alert("fail  "+responseText+"   "+status+"   "+xhr);
		    	 alert("由于后台程序原因，评论失败！");
		     },     
		     });
	}); 
	
	//推荐到我的广播
	//构造虚层
	$('#tuijian').click(function(e){
		 var sim_comm = $('<textarea id="my_commons" name="my_commons" type="text" class="form-control" placeholder="写下你的短评..." style="width:450px; height:100px; margin-bottom: 5px;word-break:break-all;resize:none;">');
		 $('#add_to_doulie_div').append('<h3>您将要将该日志推荐至您的广播</h3><hr style="width:450px;">');
		 $('#add_to_doulie_div').append(sim_comm);
    	 $('#add_to_doulie_div').append('<img style="cursor:pointer" id="submittuijian" src="/douban_user/lsr/img/finistuijian.png">');
    	 $('#add_to_doulie_div').append('<img style="cursor:pointer" id="canceltuijian" src="/douban_user/lsr/img/canceladdtodoulist.png">');
    	 //显示虚层  
	        var addarround_height = document.body.scrollHeight;
			  var width = document.body.scrollWidth;
			  $('#add_to_doulie_div_arround').css('height',addarround_height);
			  $('#add_to_doulie_div_arround').css('width',width);	 
			  var yy = e.pageY;
           $('#add_to_doulie_div').css('top',yy-100);
			  $('#add_to_doulie_div_arround,#add_to_doulie_div').fadeIn(800,function(){  
				  $('#add_to_doulie_div').css('display','block');
			  }); 
	});
	
	//提交推荐
	 $(document).on('click','#submittuijian',function(){
		 var mycomm = $('#my_commons').val();
		 var sub_item_id = $('#item_id').val();
		 alert(sub_item_id);
		 var type_table = '5';//5：日志
		 var url = '/douban_user/user/userinfo?method=recommendToMyBroadcast';				 
		 $.ajax({
		     type : 'post',
		     url : url,
		     dataType : 'json',
		     data : {my_commons : mycomm,sub_item_id:sub_item_id,type_table:type_table},
		     success : function(json, status, xhr){
		           //alert("success "+json+"   "+status+"   "+xhr);	          
		          if(json == false){
		             alert("很遗憾!推荐失败！");
		          }else{ 
		        	  alert("推荐成功！");
		        };	
		        //隐藏虚层
		         $('#add_to_doulie_div_arround,#add_to_doulie_div').fadeOut(800,function(){  
					 $('#add_to_doulie_div').css('display','none');
				});
		        //清空add_to_doulie_div
				$('#add_to_doulie_div').empty();
		     },
		     error : function(responseText, status, xhr){
		         //alert("fail  "+responseText+"   "+status+"   "+xhr);
		    	 alert("由于后台程序原因，推荐失败！");
		    	//隐藏虚层
		         $('#add_to_doulie_div_arround,#add_to_doulie_div').fadeOut(800,function(){  
					 $('#add_to_doulie_div').css('display','none');
				});
		        //清空add_to_doulie_div
					$('#add_to_doulie_div').empty();
		     },     
		     });
	 });
	
	//取消推荐
	 $(document).on('click','#canceltuijian',function(){
		//隐藏虚层
         $('#add_to_doulie_div_arround,#add_to_doulie_div').fadeOut(800,function(){  
			 $('#add_to_doulie_div').css('display','none');
		});
        //清空add_to_doulie_div
			$('#add_to_doulie_div').empty();
	 });
	
	//喜欢
	$('#like').click(function(){
		 var id = $('#item_id').val();
		 var item = '5';//5：日志
		 var url = '/douban_user/user/userinfo?method=likeSomething';				 
		 $.ajax({
		     type : 'post',
		     url : url,
		     dataType : 'json',
		     data : {item:item,item_id:id},
		     success : function(json, status, xhr){
		           //alert("success "+json+"   "+status+"   "+xhr);	          
		          if(json == false){
		             alert("很遗憾!喜欢失败！");
		          }else{ 
		        	  alert("喜欢成功！");
		        };	
		       
		     },
		     error : function(responseText, status, xhr){
		         //alert("fail  "+responseText+"   "+status+"   "+xhr);
		    	 alert("由于后台程序原因，喜欢失败！");
		    	 
		     },     
		     });
	});
	
});
</script>

</head>
<body>
  <jsp:include page="/lsr/douban_head.jsp"></jsp:include>
   
   <div id="add_to_doulie_div_arround" style="display: none;z-index: 4;position: absolute;left: 0px;top: 0px;background-color:#CCD6DA;filter:alpha(opacity=60);-moz-opacity:0.6;-khtml-opacity: 0.6;opacity: 0.6;)"></div>
   <div id="add_to_doulie_div" style="border-radius:10px;background-color:#48A355;z-index:5;display: none;width: 600px;height: 300px; position: absolute;left:400px;padding-top: 20px;padding-left: 80px;">
   </div>
   
<div id="left">
  <div id="myfocuspeople">
      <img src="<%=basePath%>${current_user.imgs}" width="80px" height="80px">
              ${current_user.username}的日记 ${current_diary.title }
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
  <p style="font-size: 24px;font-weight: 900;float:left;width: 650px;">${current_diary.title }</p>
  <p style="float:left;font-size: 13px;color: #666;"> 写于：<fmt:formatDate value="${ current_diary.time}" pattern="yyyy年MM月dd日  HH:mm:ss"/> </p>
  <div id="friends">
     <pre>
         ${ current_diary.content}
     </pre>	   
  </div><!-- friends -->
  <input type="hidden" value="${current_diary.diary_id }" id="item_id" style="float: right;">
  <img src="<%=basePath%>/lsr/img/tuijian.png" style="float: right;cursor: pointer;margin-right: 30px;" id="tuijian">
  <input type="button" value="喜欢该日志" id="like" style="float: right;margin-right: 30px;">
  <hr style="width: 650px;float: left;">
  <p style="width: 250px;float: left;margin-bottom: 40px;">回应</p>
  <c:forEach var="diary_comment" items="${diary_comment}">
      <div style="float: left;width: 650px;height: auto;margin-bottom: 20px; ">
          <img src="<%=basePath%>${diary_comment.f_user.imgs}" width="50px" height="50px" style="border-radius:25px;float: left;">
          <p style="float: left;width: 580px;margin-left: 20px;background-color: #F2FBF2;"><fmt:formatDate value="${ current_diary.time}" pattern="yyyy年MM月dd日  HH:mm:ss"/> </p>
          <p style="float: left;width:580px;height:auto;margin-left: 20px;">
              ${diary_comment.content }
          </p>
      </div>
  </c:forEach>
   <hr style="width: 650px;float: left;">
   <p style="width: 250px;float: left;">你的回应:</p>
   <input type="hidden" id="from_user" value="${sessionScope.current_user.user_id}">
   <input type="hidden" id="item" value="3"><!-- 3：日志 -->
   <input type="hidden" id="item_id" value="${ current_diary.diary_id}"> 
   <textarea id="content" type="text" class="form-control" placeholder="想对TA说些什么..." style="width:650px; height:100px; margin-bottom: 5px;word-break:break-all;resize:none;"></textarea>
   <input type="button" id="send_diary_comment" value="加上去">
</div><!-- left -->
 
 
   <div id="right"><!--右边-->
    
   </div><!--右边-->
  
 <jsp:include page="/lsr/douban_foot.jsp"></jsp:include>
</body>

</html>

