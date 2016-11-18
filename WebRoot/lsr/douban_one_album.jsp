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
<link href="lsr/css/douban_one_album.css" rel="stylesheet">
<script src="lsr/js/jquery-2.1.1.js"></script>
<script src="lsr/js/bootstrap.min.js"></script>
<script src="lsr/js/douban_loginAfter.js"></script>
<script src="lsr/js/jquery-ui.js"></script>

<script>
$(function(){
	//改变相册封面照片
	 $('.set_as_cover').click(function(e){
		 e.preventDefault(); 
		 var photo_id = $(this).prev().val(); 
		 var album_id = $(this).prev().prev().val(); 	 
	  var url = '/douban_user/user/userinfo?method=setAlbumCover';	
	  $.ajax({
	     type : 'post',
	     url : url,
	     dataType : 'json',
	     data : {photo_id : photo_id, album_id : album_id},
	     success : function(json, status, xhr){
	          alert("success "+json+"   "+status+"   "+xhr);	          
	          if(json == false){
	             alert("更改失败!");
	          }else{
	        	  alert("更改成功!");                
	          }	       	                      
	     },
	     error : function(responseText, status, xhr){
	          //alert("fail  "+responseText+"   "+status+"   "+xhr);
	    	 alert("由于后台程序原因，更改失败！");
	     },     
	     });
	 
	 });
	 
	 //查看原图
	 $('.big_img').click(function(e){
		  e.preventDefault();
		/*   alert($(this).prev().val()); */
		  //生成showpage层
		  var showdiv = $('<div id="showpage" style=""></div>');
		  var divimg = $('<div style="width: 400xp;height: 500px;float: left;"><img alt="" src="'+ $(this).prev().prev().val() +'" width="auto" height="auto" style="max-width:400px;max-height:500px;margin-left:150px"></div>');
		  var comment = $('<div style="width:700px;float:left;margin-top:20px;word-wrap :break-word;">'+$(this).prev().prev().prev().prev().val()+'</div>');
		  var time = $('<p style="float:left;width:500px;margin-top:20px;">上传于  '+ $(this).prev().val() +'</p>');
		  var huiyinxihuan = $('<input type="hidden" value="'+$(this).prev().prev().prev().val()+'" id="item_idphoto" style="float: right;">'+
				 '<img src="/douban_user/lsr/img/tuijian.png" style="float: right;cursor: pointer;margin-right: 30px;margin-top:20px;" id="tuijianphoto">'+
				 '<input type="button" value="喜欢该照片" id="likephoto" style="float: right;margin-right: 30px;margin-top:20px;">');
		 /* 循环该照片的评论 ---待解决*/
		 var mess = $('<hr style="width: 750px;float: left;">'+
				   '<p style="width:250px;float: left;">你的回应:</p>'+
				   '<input type="hidden" id="from_user" value="${sessionScope.current_user.user_id}">'+
				   '<input type="hidden" id="item" value="1"><!-- 1：照片 -->'+
				   '<input type="hidden" id="item_id" value="'+ $(this).prev().prev().prev().val() +'"> '+
				   '<textarea id="content" type="text" class="form-control" placeholder="想对TA说些什么..." style="width:650px; height:100px; margin-bottom: 5px;word-break:break-all;resize:none;"></textarea>'+
				   '<input type="button" id="send_photo_comment" value="加上去">');
		  var cancelshow = $('<input type="button" id="cancelshow" value="返回" style="margin-bottom:50px;">');  
		  
		  $('#bigarround').after(showdiv.append(divimg).append(comment).append(time).append(huiyinxihuan).append(mess).append(cancelshow));
		  var addarround_height = document.body.scrollHeight;
		  var width = document.body.scrollWidth;
		  $('#bigarround').css('height',addarround_height);
		  $('#bigarround').css('width',width);
		  $('#showpage,#bigarround').fadeIn(800,function(){  
			  $(this).css('display','block');
		  });
	});
	//取消查看原图
	 $(document).on('click','#cancelshow',function(){
		 //删除showpage
         $('#showpage').remove();
         //隐藏背景
         $('#bigarround').fadeOut(1000,function(){  
   		  $(this).css('display','none');
   	  });
	 });
	 
	//发表照片评论
		$(document).on("click", "#send_photo_comment", function(){//jquery1.7 之后动态绑定时间编程使用$(document).on('click','#id',function(){});
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
			          //删除showpage
			          $('#showpage').remove();
			          //隐藏背景
			          $('#bigarround').fadeOut(1000,function(){  
			    		  $(this).css('display','none');
			    	  });
			     },
			     error : function(responseText, status, xhr){
			          //alert("fail  "+responseText+"   "+status+"   "+xhr);
			    	 alert("由于后台程序原因，评论失败！");
			    	 //删除showpage
			          $('#showpage').remove();
			          //隐藏背景
			          $('#bigarround').fadeOut(1000,function(){  
			    		  $(this).css('display','none');
			    	  });
			     },     
			     });
		});
	
		//推荐到我的广播---照片
		//构造虚层---照片
		 $(document).on('click','#tuijianphoto',function(e){
			 var sim_comm = $('<textarea id="my_commons" name="my_commons" type="text" class="form-control" placeholder="写下你的短评..." style="width:450px; height:100px; margin-bottom: 5px;word-break:break-all;resize:none;">');
			 $('#add_to_doulie_div').append('<h3>您将要将该照片转发至您的广播</h3><hr style="width:450px;">');
			 $('#add_to_doulie_div').append(sim_comm);
	    	 $('#add_to_doulie_div').append('<img style="cursor:pointer" id="submittuijianphoto" src="/douban_user/lsr/img/finistuijian.png">');
	    	 $('#add_to_doulie_div').append('<img style="cursor:pointer" id="canceltuijianphoto" src="/douban_user/lsr/img/canceladdtodoulist.png">');
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
		
		//提交推荐---照片
		 $(document).on('click','#submittuijianphoto',function(){
			 var mycomm = $('#my_commons').val();
			 var sub_item_id = $('#item_id').val();
			 alert(sub_item_id);
			 var type_table = '8';//8：照片
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
		
		//取消推荐---照片
		 $(document).on('click','#canceltuijianphoto',function(){
			//隐藏虚层
	         $('#add_to_doulie_div_arround,#add_to_doulie_div').fadeOut(800,function(){  
				 $('#add_to_doulie_div').css('display','none');
			});
	        //清空add_to_doulie_div
				$('#add_to_doulie_div').empty();
		 });
	
	
	
		//推荐到我的广播---相册
		//构造虚层---相册
		$('#tuijian').click(function(e){
			 var sim_comm = $('<textarea id="my_commons" name="my_commons" type="text" class="form-control" placeholder="写下你的短评..." style="width:450px; height:100px; margin-bottom: 5px;word-break:break-all;resize:none;">');
			 $('#add_to_doulie_div').append('<h3>您将要将该广播转发至您的广播</h3><hr style="width:450px;">');
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
		
		//提交推荐---相册
		 $(document).on('click','#submittuijian',function(){
			 var mycomm = $('#my_commons').val();
			 var sub_item_id = $('#item_id').val();
			 alert(sub_item_id);
			 var type_table = '6';//6：相册
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
		
		//取消推荐---相册
		 $(document).on('click','#canceltuijian',function(){
			//隐藏虚层
	         $('#add_to_doulie_div_arround,#add_to_doulie_div').fadeOut(800,function(){  
				 $('#add_to_doulie_div').css('display','none');
			});
	        //清空add_to_doulie_div
				$('#add_to_doulie_div').empty();
		 });
		
		//喜欢---相册
		$('#like').click(function(){
			 var id = $('#item_id').val();
			 var item = '6';//6：相册
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
		
		//喜欢--照片
		 $(document).on('click','#likephoto',function(){
			 var id = $('#item_idphoto').val();
			 var item = '8';//8：照片
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
   
  <div id="bigarround" style="display: none;"></div>
 
<div id="left">
  <div id="myfocuspeople">
      <img src="<%=basePath%>${thisalbum.user_info.imgs}" width="80px" height="80px">
              ${thisalbum.user_info.username}的相册 - ${thisalbum.album_name }       
  </div><!-- myfocuspeople -->
  
  <hr style="width: 650px;float: left;">
  <div id="friends">
  <c:if test="${thisalbum==null}">
     <c:out value="该相册已不复存在"></c:out>
  </c:if>
  <c:if test="${thisalbum!=null}">
       <c:if test="${albumphotos==null}">
         <c:out value="该相册内没有照片"></c:out>
      </c:if>
      <c:if test="${albumphotos!=null}">
           <!-- 遍历照片 -->
           <c:forEach var="photo" items="${albumphotos}">
		   <div id="myalbums">
		           
		           <input type="hidden" value="${photo.simple_desc }">
		           <input type="hidden" value="${photo.photo_id}">
		           <input type="hidden" value="<%=basePath%>${photo.photo_address}">
                   <input type="hidden" value="${photo.upload_time}">                
                   <a href="#" class="big_img">                  
                     <img src="<%=basePath%>${photo.photo_address}" width="175px" height="175px">
                   </a>
                   <br>
                   <span>${photo.photo_name}</span>
                    <br>
                   <span><fmt:formatDate value="${photo.upload_time}" pattern="yyyy-MM-dd"/></span>    
                   <br>
                   <input type="hidden" id="album_id" value="${album_id}">
                   <input type="hidden" id="photo_id" value="${photo.photo_id}">
                   <c:if test="${thisalbum.user_info.user_id==sessionScope.current_user.user_id}">
                        <a href="#" class="set_as_cover">设为封面 </a>
                   </c:if>
                 
            </div>   
            </c:forEach>     
      </c:if>
  </c:if>
  </div><!-- friends -->
  <hr style="width: 650px;float: left;">
  <p style="float: left;font-size: 13px;color: #666;">创建于:<fmt:formatDate value="${thisalbum.time }" pattern="yyyy-MM-dd HH:mm:ss"/> </p>
  <input type="hidden" value="${thisalbum.album_id }" id="item_id" style="float: right;">
  <img src="<%=basePath%>/lsr/img/tuijian.png" style="float: left;cursor: pointer;margin-left: 300px;" id="tuijian">
  <input type="button" value="喜欢该相册" id="like" style="float: left;margin-left: 30px;">
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
		    <p style="color:#007722;">→ 我被  <a class="item" href="">${sessionScope.whofocusme }</a>人关注</p>
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

