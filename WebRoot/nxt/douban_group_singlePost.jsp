<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>话题</title>
<base href="<%=basePath%>">
<link rel="icon" href="nxt/images/favicon_16x16.png">
<link rel="stylesheet" href="nxt/css/header.css" >
<link rel="stylesheet" href="nxt/css/main_nav.css">
<link rel="stylesheet" type="text/css" href="nxt/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="nxt/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="nxt/css/grouppost_left.css">
<link rel="stylesheet" type="text/css" href="nxt/css/grouppost_right.css">
<script src="nxt/js/jquery.min.js"></script>
<script src="nxt/js/bootstrap.min.js"></script>
<script src="nxt/js/jquery-2.1.1.js"></script>
<script>
$(function(){
	 
	//推荐到我的广播
	//构造虚层
	$('#tuijian').click(function(e){
		e.preventDefault();
		 var sim_comm = $('<textarea id="my_commons" name="my_commons" type="text" class="form-control" placeholder="写下你的短评..." style="width:450px; height:100px; margin-bottom: 5px;word-break:break-all;resize:none;">');
		 $('#add_to_doulie_div').append('<h3>您将要将该话题推荐至您的广播</h3><hr style="width:450px;">');
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
		 //alert(sub_item_id);
		 var type_table = '9';//9：话题
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
	$('#like').click(function(e){
		e.preventDefault();
		 var id = $('#item_id').val();
		 var item = '9';////9：话题
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
 <jsp:include page="douban_group_head.jsp"></jsp:include>

<div id="add_to_doulie_div_arround" style="display: none;z-index: 4;position: absolute;left: 0px;top: 0px;background-color:#CCD6DA;filter:alpha(opacity=60);-moz-opacity:0.6;-khtml-opacity: 0.6;opacity: 0.6;)"></div>
   <div id="add_to_doulie_div" style="border-radius:10px;background-color:#48A355;z-index:5;display: none;width: 600px;height: 300px; position: absolute;left:400px;padding-top: 20px;padding-left: 80px;">
   </div>

<div id="grouppost_left">
	<div id="post_title">${requestScope.post.post_title}</div><!--end of post_title-->
    <div id="post_content">
    	<div id="user_pic"><a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${requestScope.post_author.user_id }"><img src="${requestScope.post_author.imgs}" width="60px" height="60px" style="border-radius:30px;"></a></div>
        <div id="post_doc">
        	<div id="post_from">
        		<span>来自：<a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${requestScope.post_author.user_id }">${requestScope.post_author.username }</a></span> 
           		<span id="pub_time">${requestScope.post.post_pubtime}</span><br>
            </div>
            
            <div id="post">
            ${requestScope.post.group_content}
            </div>
        </div><!--post_doc-->
    </div><!--end of post_content-->
    
    <div id="br_null"></div>
    
    <div id="user_operate">
    	<div id="rec_num">
        	<span><a href="#" >1341人</a>推荐</span>
        	<input type="hidden" value="${requestScope.post.id}" id="item_id">
            <label><a href="#" id="tuijian">推荐</a></label>
        </div>
        <div id="like_num">
        	<span><a href="">1244人</a>喜欢</span>
            <label><a href="#" id="like">喜欢</a></label>
        </div>
    </div><!--end of user_operate-->
    
    <div id="br_null"></div>
    
    <div id="people_reviews">
    	<div id="review_nav">
        	
          
            
        </div>
        <hr>
        
        <div id="review_list">
        <c:if test="${! empty requestScope.item_replies }">
        <c:forEach items="${requestScope.item_replies }" var="reply">
        	<div id="review_item">
            	<div id="user_face"><img src="${reply.imgs }"  width="50px" height="50px" style="border-radius:25px;"></div>
                <div id="item">
                	<div id="item_from">
                		<span><a href="">${reply.username }</a></span>
                    	<span>${reply.reply_time}</span>
                     </div>
                	<div id="whatever">
                		<div id="item_content">
                		${reply.reply_content }
                		</div>
                	</div>	
                    	<div id="operate">
                    		
                        	<a href="">赞</a>
                        	
                    	</div>
                	
            	</div>
            
          	</div>
            <div id="br_null"></div>
           </c:forEach>
           </c:if>
           
        </div><!--end of review_list-->
        
    </div><!--end of people_reviews-->
    

    <c:if test="${! empty sessionScope.current_user }">
    <c:if test="${! empty sessionScope.role }">
     <div id="your_reply" style="margin-bottom: 30px;">
    	<div id="your_reply_h">你的回应</div>
    	<form action="groupServlet?method=userAddReply&&post_id=${requestScope.post.id}&&reply_author=${sessionScope.current_user.user_id}" method="post">
        <div id="reply_textarea">
        	<textarea name="reply_content" class="control"></textarea>
        </div>
        <input type="submit" value="加上去"> 
        </form>
    </div>
    </c:if>
    </c:if>
</div><!--end of group_post_left-->

<div id="grouppost_right">
	<div></div>
</div><!--end of grouppost_right-->


<div style="float: right;margin-right: 160px;margin-top: 100px;">
    	<img src="<%=basePath%>/nxt/images/ads/ad2.png" width="auto" height=""auto"" style="">
    </div> 

</body>
</html>

