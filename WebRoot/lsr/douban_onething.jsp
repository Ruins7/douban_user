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

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="lsr/css/douban_thing.css" rel="stylesheet" type="text/css">
<link href="lsr/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="lsr/css/bootstrap-theme.css" rel="stylesheet"type="text/css">
<link href="lsr/css/douban_onething.css" rel="stylesheet"type="text/css">
<link href="lsr/css/jquery-ui.css" rel="stylesheet">
<script src="lsr/js/jquery-2.1.1.js"></script>
<script src="lsr/js/bootstrap.min.js"></script>
<script src="lsr/js/jquery-ui.js"></script>
 
 <script>
 $(function(){
	//发表东西评论
		$('#send_thing_comment').click(function(){
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
			          //alert("success "+json+"   "+status+"   "+xhr);	          
			          if(json == false){
			             alert("评论失败！");
			          }else{
			        	  alert("评论成功！");
			        	  //清空内容
			        	  $('#content').val("");
		              }	       	                      
			     },
			     error : function(responseText, status, xhr){
			          //alert("fail  "+responseText+"   "+status+"   "+xhr);
			    	 alert("由于后台程序原因，评论失败！");
			     },     
			     });
		}); 
	
	//加入豆列
	//查询当前用户的所有相同类型的豆列
	 $('#addtodoulie').click(function(e){
		 var d_type = '4';
		 var url = '/douban_user/doulist/doulistinfo?method=showSameTypeDoulist';				 
		 $.ajax({
		     type : 'post',
		     url : url,
		     dataType : 'json',
		     data : {d_type : d_type},
		     success : function(json, status, xhr){
		          //alert("success "+json+"   "+status+"   "+xhr);	          
		          if(json == false){
		             alert("当前没有可用豆列！请先创建豆列!");
		             //转移到创建豆列
		          }else{ 
		        	 var sel = $('<select name="belongto_doulist" id="samelist" class="form-control" style="width:200px;margin-top:10px;margin-bottom:10px;"></select>');
		        	 $('#add_to_doulie_div').append('<h3>加入豆列</h3><hr style="width:600px;">');
		        	 $('#add_to_doulie_div').append(sel);
		        	 var sim_comm = $('<textarea id="simple_common" name="simple_common" type="text" class="form-control" placeholder="写下你的短评..." style="width:600px; height:100px; margin-bottom: 5px;word-break:break-all;"></textarea>'); 
		        	 $('#add_to_doulie_div').append(sim_comm);
		        	 $('#add_to_doulie_div').append('<img style="cursor:pointer" id="finishadd" src="/douban_user/lsr/img/finishaddtodoulist.png">');
		        	 $('#add_to_doulie_div').append('<img style="cursor:pointer" id="canceladd" src="/douban_user/lsr/img/canceladdtodoulist.png">');
		        	 $.each(json, function(index, record){   
		        	     var opt = $('<option value="'+json[index].doulist_id+'">'+json[index].list_name+'</option>');
		        	      $('#samelist').append(opt);
		        	  });	     	 
		        };	 
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
		     },
		     error : function(responseText, status, xhr){
		          //alert("fail  "+responseText+"   "+status+"   "+xhr);
		    	 alert("由于后台程序原因，加载豆列失败！");
		     },     
		     });
	 });
	
	//提交添加请求
	 $(document).on('click','#finishadd',function(){
		  var item_id = $('#item_id').val();
		  var doulist_id = $('#samelist').val();
		  var simple_common = $('#simple_common').val();
		  var url = '/douban_user/doulist/doulistinfo?method=addItemToMyDouList';				 
			 $.ajax({
			     type : 'post',
			     url : url,
			     dataType : 'json',
			     data : {item_id : item_id,belongto_doulist:doulist_id,simple_common:simple_common},
			     success : function(json, status, xhr){
			           //alert("success "+json+"   "+status+"   "+xhr);	          
			          if(json == false){
			             alert("很遗憾!添加到豆列失败！");
			          }else{ 
			        	  alert("添加到豆列成功！");
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
			    	 alert("由于后台程序原因，添加失败！");
			    	//隐藏虚层
			         $('#add_to_doulie_div_arround,#add_to_doulie_div').fadeOut(800,function(){  
						 $('#add_to_doulie_div').css('display','none');
					});
			        //清空add_to_doulie_div
						$('#add_to_doulie_div').empty();
			     },     
			     });
		 
	 });
	
	//取消添加到豆列
	 $(document).on('click','#canceladd',function(){
		//隐藏虚层
         $('#add_to_doulie_div_arround,#add_to_doulie_div').fadeOut(800,function(){  
			 $('#add_to_doulie_div').css('display','none');
		});
        //清空add_to_doulie_div
			$('#add_to_doulie_div').empty();
	 });
	
	//推荐到我的广播
	//构造虚层
	$('#tuijian').click(function(e){
		 var sim_comm = $('<textarea id="my_commons" name="my_commons" type="text" class="form-control" placeholder="写下你的短评..." style="width:600px; height:100px; margin-bottom: 5px;word-break:break-all;"></textarea>');
		 $('#add_to_doulie_div').append('<h3>推荐到我的广播</h3><hr style="width:600px;">');
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
		 var type_table = '4';//4：东西
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
	<jsp:include page="/lsr/douban_thinghead.jsp"></jsp:include>
	
	 <div id="add_to_doulie_div_arround" style="display: none;"></div>
     <div id="add_to_doulie_div" style="border-radius:10px;background-color:#48A355;z-index:5;display: none;width: 800px;height: 400px; position: absolute;left:300px;padding-top: 50px;padding-left: 80px;"></div>
	 <div style="margin-left:200px;width:1000px;height: auto;margin-top: 30px;margin-bottom:30px;float: left;background-color: #fff;border-radius:10px;box-shadow: 10px 10px 5px #ccc;">
	   <div style="float: left;width: 600px;height: auto;margin-left: 20px;margin-bottom:30px;margin-top: 20px;">
	      <img src="<%=basePath%>${thing.f_user.imgs}" width="60px" height="60px" style="border-radius:30px;float:left;margin-right: 30px;"><p style="height: 60px;padding-top: 18px;margin-left: 60px;">由   <a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${thing.f_user.user_id}"><span style="color: #7F4B2F;">${thing.f_user.username}</span></a> 发布</p>
	      <img src="<%=basePath%>${thing.imgs}" width="300px;" height="300px;" style="float:left;margin-right: 30px;">   
          <span style="font-size: 18px;color:#7F4B2F;font-weight: 900;width: 270px;height:auto;">${thing.things_name}</span><br>
          <span style="color:#AA99BB;font-size: 13px;">类别：[${thing.type_table.type_name}]  </span> <br><br><br>
          <p style="float:left;width: 270px;height: auto;font-size: 14px;"><img src="lsr/img/frontyinhao.png">
           ${thing.simple_desc}
          <img src="lsr/img/afteryinhao.png"></p> <br><br><br><br><br>
          <input type="hidden" id="item_id" value="${thing.things_id}">
          <img src="<%=basePath%>/lsr/img/addtodoulie.png" style="border-radius:4px;cursor: pointer;margin-right: 30px;" id="addtodoulie"> 
	      <img src="<%=basePath%>/lsr/img/tuijian.png" style="cursor: pointer;" id="tuijian"> 
	      <span style="color:#AA99BB;font-size: 13px;float: left;clear: both;margin-top: 20px;">发布于 <fmt:formatDate value="${thing.time}" pattern="yyyy年MM月dd日  HH:mm:ss"/></span><br>
	   </div>
	   <div style="width: 300px;height: auto;margin-left: 50px;margin-right:30px;margin-top: 30px;margin-bottom: 30px;background-color: #EFEFEF;box-shadow: 10px 10px 5px #ccc;border-radius:10px;float: right;padding: 20px;">
	      <p style="font-size: 12px;color:#999999;font-weight: 500;">所属豆列</p>
	      <c:forEach var="doulist" items="${belongdoulist}">
	         <div style="height: auto;width: 260px;float: left;margin-bottom: 15px;margin-top: 15px;">
	         <a href="#"><img src="<%=basePath%>${doulist.f_user.imgs}" width="40px" height="40px" style="border-radius:20px;float:left;"></a>
	         <span style="font-size: 14px;color:#999999;margin-left: 20px;padding-top: 10px;float: left;">[${doulist.content_type_table.type_name }]</span>
	         <a href="<%=basePath%>doulist/doulistinfo?method=showOneDouList&doulie_id=${doulist.doulist_id}&doulie_type=${doulist.content_type_table.content_type_id}"><span style="font-size: 14px;color:#7F4B2F;margin-left: 20px;padding-top: 10px;float: left;">${doulist.list_name}</span></a> 
	      </div>
	     </c:forEach>
	   </div>
	   <div style="float: left;width: 600px;height: auto;margin-left: 20px;margin-bottom: 30px;">
	     <p style="width: 550px;float: left;">所有回应:</p>
	     <hr style="width: 600px;float: left;">
	     <c:forEach var="common" items="${thing_commons}">
	     <div style="width: 600px;height: auto;float: left;margin-top: 20px;margin-bottom: 20px;">
	         <img alt="" src="<%=basePath%>${common.f_user.imgs}" width="50px;" height="50px;" style="border-radius:25px;float: left;">
	         <span style="float: left;width: 370px;height: auto;margin-left: 30px;margin-top: 20px;">${common.content}</span>      <span style="float: right;margin-top: 20px;"><fmt:formatDate value="${common.time}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
	     </div>
	     </c:forEach>
	   </div>
	   <div style="float: left;width: 600px;height: auto;margin-left: 20px;margin-bottom: 30px;">
	     <hr style="width: 600px;float: left;">
         <p style="width: 550px;float: left;">你的回应:</p>
         <input type="hidden" id="from_user" value="${sessionScope.current_user.user_id}">
         <input type="hidden" id="item" value="5"><!-- 5：东西 -->
         <input type="hidden" id="item_id" value="${thing.things_id}"> 
         <textarea id="content" type="text" class="form-control" placeholder="你对此物件的看法..." style="resize:none;width:600px; height:100px; margin-bottom: 5px;word-break:break-all;"></textarea>
         <input type="button" id="send_thing_comment" value="加上去" style="">
       </div>   
   </div>
    <jsp:include page="/lsr/douban_foot.jsp"></jsp:include>
</body>

</html>
