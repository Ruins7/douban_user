<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>活动信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <link href="hyy/css/douban_city.css" rel="stylesheet" type="text/css">
    <link href="lsr/css/douban_onething.css" rel="stylesheet"type="text/css">
    <script src="hyy/js/jquery-2.1.1.js"></script>
    <script src="hyy/js/bootstrap.min.js"></script>
    <script src="hyy/js/jquery.js"></script>
    <script src="hyy/js/jquery-ui.js"></script>
  <script>
  $(function(){
	  //参加活动
	  $('#iwantattend').click(function(){
		    var act_id = $(this).prev().val();
		    var url = '/douban_user/hyy/service/ActivityServlet?method=attendActivity';				 
			 $.ajax({
			     type : 'post',
			     url : url,
			     dataType : 'json',
			     data : {act_id : act_id},
			     success : function(json, status, xhr){
			        //  alert("success "+json+"   "+status+"   "+xhr);	          
			          if(json == false){
			             alert("参加失败！");
			          }else{
			        	  alert("参加成功！");
			        	  //显示取消
		              }	       	                      
			     },
			     error : function(responseText, status, xhr){
			          //alert("fail  "+responseText+"   "+status+"   "+xhr);
			    	 alert("由于后台程序原因，参加失败！");
			     },     
			     });
	  });
	  
	  
	  //取消参加活动
	  $('#cancelattend').click(function(e){
		  e.preventDefault();
		  var act_id = $(this).prev().val();
		    var url = '/douban_user/hyy/service/ActivityServlet?method=cancleAttendActivity';				 
			 $.ajax({
			     type : 'post',
			     url : url,
			     dataType : 'json',
			     data : {act_id : act_id},
			     success : function(json, status, xhr){
			        //  alert("success "+json+"   "+status+"   "+xhr);	          
			          if(json == false){
			             alert("取消失败！");
			          }else{
			        	  alert("取消成功！");
			        	  //显示参加img
			        	  
		              }	       	                      
			     },
			     error : function(responseText, status, xhr){
			          //alert("fail  "+responseText+"   "+status+"   "+xhr);
			    	 alert("由于后台程序原因，取消失败！");
			     },     
			     });
	  });
	  
	  //活动讨论
	  $('#addmywords').click(function(){
		   var ask_comment = $('#mywords').val();
		   var act_id = $('#act_id').val();
		   var url = '/douban_user/hyy/service/ActivityServlet?method=addActivityComment';				 
			 $.ajax({
			     type : 'post',
			     url : url,
			     dataType : 'json',
			     data : {activity_id : act_id,ask_comment:ask_comment},
			     success : function(json, status, xhr){
			        //  alert("success "+json+"   "+status+"   "+xhr);	          
			          if(json == false){
			             alert("讨论失败！");
			          }else{
			        	  alert("讨论成功！");
			        	  //显示刚才的信息
			        	  
		              }	       	                      
			     },
			     error : function(responseText, status, xhr){
			          //alert("fail  "+responseText+"   "+status+"   "+xhr);
			    	 alert("由于后台程序原因，讨论失败！");
			     },     
			     });
	  });
	  
	  
	//推荐到我的广播
		//构造虚层
		$('#tuijian').click(function(e){
			 var sim_comm = $('<textarea id="my_commons" name="my_commons" type="text" class="form-control" placeholder="写下你的短评..." style="width:600px; height:100px; margin-bottom: 5px;word-break:break-all;resize:none;"></textarea>');
	    	 $('#add_to_doulie_div').append('<p style="font-size: 28px;color: #333;font-weight:900;">将此活动推荐到我的广播</p><hr style="width:600px;">');
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
			 var sub_item_id = $('#act_id').val();
			 var type_table = '7';//7：线下活动
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
	  
  });
  </script>
  </head>
  
  <body>
  <jsp:include page="/hyy/activity/douban_activityhead.jsp"></jsp:include>
  
   <div id="add_to_doulie_div_arround" style="display: none;"></div>
   <div id="add_to_doulie_div" style="border-radius:10px;background-color:#48A355;z-index:5;display: none;width: 800px;height: 400px; position: absolute;left:300px;padding-top: 50px;padding-left: 80px;"></div>
  <div style="float:left;width: 660px;height: auto;margin-left: 200px;margin-top:30px;padding: 15px;margin-bottom: 30px;border-radius:20px;border:1px solid #ccc;">
  	 <div style="float: left; width: 630px;height:auto;">
  	    <img alt="" src="<%=basePath%>${off.imgs}" width="220px" height="300px" style="float: left;margin-right: 30px;"> 
        <p></p>
        <p style="color: #664433;font-size: 22px;font-weight: 700;">${off.offactivity_title}</p>
        <br>
        <p style="font-size: 12px;color: #666;">时间： <fmt:formatDate value="${off.start_time }" pattern="yyyy年MM月dd日 HH:mm:ss"/> ~ <fmt:formatDate value="${off.end_time }" pattern="yyyy年MM月dd日 HH:mm:ss"/> </p>
        <p style="font-size: 12px;color: #666;">地点： ${off.city.city_desc } -- ${off.position}</p>
        <p style="font-size: 12px;color: #666;">主办方： ${off.user.username }</p>
        <p style="font-size: 12px;color: #666;">活动类型：${off.at.type_name }</p>
        <br><br>
        <p>
           <input type="hidden" value="${off.offactivity_id}" id="act_id">
           <c:if test="${current_user_attend=='no' }">
              <img src="<%=basePath%>/lsr/img/iwantattend.png" style="cursor: pointer;" id="iwantattend">
           </c:if>
           <c:if test="${current_user_attend=='yes'}">
               <a href="#" id="cancelattend">取消参加</a>
           </c:if>
           <img src="<%=basePath%>/lsr/img/tuijian.png" style="cursor: pointer;" id="tuijian"> 
        </p>
        <hr>
  	 </div>
  	 <div style="float: left; width: 630px;height:auto;margin-top: 30px;">
  	    <p style="color: #664433;font-size: 14px;">活动详情</p>
  	     <pre style="width: 610px;height: auto;white-space: pre-wrap;">
  	         ${off.offactivity_desc }
  	     </pre>
  	     <hr>
  	 </div>
  	 <div style="float: left; width: 630px;height:auto;margin-top: 10px;">
  	    <p style="color: #664433;font-size: 14px;">活动讨论区</p>
  	    <hr>
  	    <c:forEach var="ask" items="${ask }">
  	        <div>
  	            <a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${ask.user_f.user_id }"><img src="<%=basePath%>${ask.user_f.imgs}" width="50px" height="50px" style="border-radius:25px;"><span style="font-size: 12px;color: #666;margin-left:10px;margin-top: 15px;"></a>说</span><span style="font-size: 12px;color: #666;float: right;margin-top: 15px;"><fmt:formatDate value="${ask.time }" pattern="yyyy年MM月dd日 HH:mm:ss"/></span>
  	            <pre style="margin-top: 10px;width: 610px;height: auto;white-space: pre-wrap;">${ask.ask_comment }</pre>
  	            
  	        </div>
  	        <hr>
  	    </c:forEach>   
  	 </div>	 
  	 <div style="float: left; width: 630px;height:auto;margin-top: 10px;">
  	     <p style="color: #664433;font-size: 14px;">我也想讨论</p>
  	     <textarea rows="3" cols="38%" class="form-control" id="mywords" style="resize:none;"></textarea>
  	     <input type="button" value="加上去" id="addmywords">
  	 </div>
  </div>
  
  
  
  	 <div style="float: left; width: 300px;height:auto;margin-left: 60px;margin-top: 40px;min-height: 150px">
  	      <p style="color: #664433;font-size: 14px;margin-bottom: 20px;">参加该活动的人</p>
  	     <c:forEach var="user" items="${users}">
  	         <a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${user.user_id }"><img src="<%=basePath%>${user.imgs}" width="50px" height="50px" style="border-radius:25px;margin: 5px;"></a>
  	     </c:forEach>
  	 </div>
  	 <div style="float: left; width: 300px;height:auto;margin-left: 60px;margin-top: 40px;">
  	      <p style="color: #664433;font-size: 14px;margin-bottom: 20px;">发起该活动的人</p>
  	         <a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${off.user.user_id }"><img src="<%=basePath%>${off.user.imgs }" width="50px" height="50px" style="border-radius:25px;margin: 5px;"></a>
  	 </div>
  </body>
</html>
