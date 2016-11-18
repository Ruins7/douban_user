<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
	<link type="text/css" href="wj/css/movieStyle.css" rel="stylesheet">	
		<title>电影</title>
    <meta charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
   <link href="wj/css/movieaInfo.css" rel="stylesheet" type="text/css">
   <link href="lsr/css/douban_onething.css" rel="stylesheet"type="text/css">
    <script src="wj/js/jquery-2.1.1.js"></script>
    <script type="text/javascript">
    $(function(){
    	$('.div1').mouseover(function(){
    		$('#div2').css('visibility','visible');
    		});
        $('.div1').mouseout(function(){
    		$('#div2').css('visibility','hidden');	
        });
        
      //选择观看状态
    	$('#wantread').click(function(){
    		$('#read_state').val();
    		$('#read_state').val("想看");
    	});	 
    	$('#duguo').click(function(){
    		$('#read_state').val();
    		$('#read_state').val("已看");
    	});
    	
    	 //打分,选择观看状态，写短评的请求提交
    	 $('#addscore').click(function(){
    		 var score = $('#givescore').val();
    		 var bs_content = $('#bs_content').val();
    		 var station = $('#read_state').val();
    		 var movie_id = $('#item_id').val();  
    		 var url = '/douban_user/servlet/ShortMovieCommentServlet?method=addshortComment';				 
    		 $.ajax({
    		     type : 'post',
    		     url : url,
    		     dataType : 'json',
    		     data : {score : score,short_c_masg:bs_content,station:station,movie_id:movie_id},
    		     success : function(json, status, xhr){
    		          //alert("success "+json+"   "+status+"   "+xhr);	          
    		          if(json == false){
    		             alert("评论失败！");
    		          }else{
    		        	  alert("评论成功！");
    	              }
    		          //显示刚才打得分和选择的阅读状态，隐藏短评框
    		        
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
			 var sim_comm = $('<textarea id="my_commons" name="my_commons" type="text" class="form-control" placeholder="写下你的短评..." style="width:600px; height:100px; margin-bottom: 5px;word-break:break-all;resize:none;"></textarea>');
	    	 $('#add_to_doulie_div').append('<p style="font-size:24px;color:#000;font-weight:700;">将本电影推荐到我的广播</p><hr style="width:600px;float:left;">');
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
			 var type_table = '3';//3:电影
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
			        	  //推荐成功则不可以再次推荐
			        	  
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
 
	 <jsp:include page="/wj/douban_moviehead.jsp"></jsp:include>
       
     <div id="add_to_doulie_div_arround" style="display: none;"></div>
     <div id="add_to_doulie_div" style="border-radius:10px;background-color:#48A355;z-index:5;display: none;width: 800px;height: 400px; position: absolute;left:300px;padding-top: 50px;padding-left: 80px;"></div>
       
       
    <!-- content-->
    <div style="float:left;margin-right:30px;margin-bottom:30px;background-color:#F0F3F5;margin-top:20px;width: 620px;height: auto;margin-left: 200px;border-radius:20px;padding: 10px;box-shadow: 5px 5px 10px #ccc;border: 1px solid #ccc;"> 
    <!-- content--1 -->
        <div style="width:600px;height:auto; float:left;" id="movieInfo">
               <div style="width:500px; height:40px; font-size:28px;font-weight:900 ;margin-bottom:20px;">${requestScope.movie.m_name}</div>
               <div style=" position:relative; width:500px; height:200px">
                 <div style=" width:160px; height:200px;float:left; position:relative">
                     <img src=${requestScope.movie.imgs}; style=" width:130px; height:180px">
                 </div>
                 <div style=" width:340px; height:200px; float:left; position:relative">
                     <div style="margin-top:10px;">导演：<a href="<%=basePath%>servlet/movieServlet?method=showMovieByDirector&director_id=${director.director_id}" style="text-decoration:none; color:#268DCD;">${requestScope.director.d_name}</a></div>
                     <div style="margin-top:10px;">类型：${requestScope.movie_type.type_name}</div>
                     <div style="margin-top:10px;">上映时间：${requestScope.movie.m_screentime}</div>
                     <div style="margin-top:10px;">演员：${requestScope.movie.m_actors}</div>
                     <div style="margin-top:10px;">制片国家/地区：${requestScope.movie.m_district}</div>
                     <div style="margin-top:10px;">语言：${requestScope.movie.m_language}</div>
                     <div style="margin-top:10px;color: red;">得分：${requestScope.movie.avgscore}</div>
                 </div>
               </div>     
          <!-- 短评，打分，观看状态 -->       
          <div style="width:500px; height:80px;">
             <input type="hidden" id="item_id" value="${requestScope.movie.movie_id}">
            <img src="<%=basePath%>/lsr/img/tuijian.png" style="cursor: pointer;margin-left: 500px;" id="tuijian"> 
             <c:if test="${requestScope.movie.current_user_movie_station!=null && requestScope.movie.current_user_movie_station.station=='想看' }">
		        <p style="font-size: 12px;color: #000;">您当前对该影片的观看状态:<img alt="" src="<%=basePath%>lsr/img/xiangkan.png"></p> 
		     </c:if>	 
		     <c:if test="${requestScope.movie.current_user_movie_station!=null && requestScope.movie.current_user_movie_station.station=='已看' }">
		             <p style="font-size: 12px;color: #000;">您当前对该影片的观看状态:<img alt="" src="<%=basePath%>lsr/img/kanguo.png"></p>
		     </c:if>
		     <c:if test="${requestScope.movie.current_user_movie_station==null }">
		      <img style="cursor: pointer;" alt="" src="<%=basePath%>lsr/img/xiangkan.png" id="wantread">
		      <img style="cursor: pointer;" alt="" src="<%=basePath%>lsr/img/kanguo.png" id="duguo">
		      <input type="hidden" value="" id="read_state">
		     </c:if>
		     <c:if test="${requestScope.movie.current_user_movie_station==null }">
		     <input type="text" id="givescore" value="打分" style="width: 50px;height:25px;margin-bottom: 5px;border-radius:5px;"><span style="font-size: 12px;color: #666666;;margin-left: 20px;">0 ~ 100</span>
		     <div style="width: 600px;height: auto;float: left;" >    
			    <textarea rows="3" cols="90%" id="bs_content" value="写短评" style="border-radius:5px;resize:none;"></textarea>
			    <input type="button" value="提交" id="addscore" style="margin-bottom: 20px;width: 40px;height: 30px;">
		    </div>
		    </c:if>
		    <c:if test="${requestScope.movie.current_user_movie_station!=null }">
			     <p style="font-size: 12px;color: #000;">您对该影片给出的分数为：<span style="color: red;">${requestScope.movie.current_user_movie_station.score }</span></p>  
		    </c:if>
        </div><!-- 短评，打分，观看状态 --> 
        
            <div style="height:30px; font-size:18px; color:#2F7C4B;margin-top: 10px;float: left;">${requestScope.movie.m_name}的剧情介绍……</div>
            <div style="text-indent:10px; margin-top: 20px;float: left;">${requestScope.movie.m_describe}……</div>
      </div><!-- content--1 -->
             
  <!-- content--3 -->
  <div style=" width:600px; float:left;">
  		 <!-- 影评 -->
  		 <div style="height:30px; font-size:18px; color:#2F7C4B;margin-top: 50px;">${requestScope.movie.m_name}的影评</div>
  		       <c:forEach var="movieComment" items="${requestScope.movieComments}" varStatus="i" begin="0" end="2" step="1">
  		                   <div style="width: 600px;height: auto;float: left;">
  		                   <span style="float: left;width: 50px;height: 100px;">
  		                       <a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${movieComment.user.user_id }" style="text-decoration:none; color: #368ACC;">
  		                         <img alt="" src="<%=basePath%>${movieComment.user.imgs }" width="50px" height="50px" style="border-radius:25px;">
  		                       </a>
  		                     </span>
  		                   <div style="float:left;margin-top: 10px;width: 550px;">
  		                     <span style="margin-left: 10px;">${movieComment.user.username }</span>
  		                     <span style="margin-left: 10px;">
  		                       <fmt:formatDate value="${movieComment.c_date}" pattern="yyyy-MM-dd HH:mm:ss"/> 
  		                     </span>
  		                   </div>
                           <div style="float:left;margin-top: 10px;width: 540px;margin-bottom: 20px;margin-left: 10px;">${movieComment.c_masg}</div>
                           </div>
                </c:forEach>
              <hr>
         <!-- 短评 --> 
         <div style="width: 600px;height: auto;float: left;">
          <div style="height:30px; font-size:18px; color:#2F7C4B;margin-top: 50px;">${requestScope.movie.m_name}的短评</div>
                <c:forEach var="shortMovieComment" items="${requestScope.shortMovieComments}" varStatus="i" begin="0" end="2" step="1">
                           <div style="width: 600px;height: auto;float: left;">
                           <span style="float: left;width: 50px;height: 100px;">
  		                       <a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${shortMovieComment.user.user_id }" style="text-decoration:none; color: #368ACC;">
  		                         <img alt="" src="<%=basePath%>${shortMovieComment.user.imgs }" width="50px" height="50px" style="border-radius:25px;">
  		                       </a>
  		                     </span>
  		                   <div style="float:left;margin-top: 10px;width: 550px;">
  		                     <span style="margin-left: 10px;">${shortMovieComment.user.username }</span>
  		                     <span style="margin-left: 10px;">
  		                       <fmt:formatDate value="${shortMovieComment.date}" pattern="yyyy-MM-dd HH:mm:ss"/> 
  		                     </span>
  		                   </div>
                           <div style="float:left;margin-top: 10px;width: 540px;margin-bottom: 20px;margin-left: 10px;">${shortMovieComment.short_c_masg}</div>
                           </div>
                </c:forEach>  
         </div>
   </div>
   <!-- content--3 -->
</div><!-- content -->
        <!-- 谁看这本电影 -->
        <div style="width: 330px;height: auto;float: left;margin-top: 40px;padding: 15px;background-color: #F0F3F5;border-radius:20px;box-shadow: 5px 5px 10px #ccc;border: 1px solid #ccc;">
		<p style="float: left;font-size: 16px;color: #2F7C4B;margin-bottom: 20px;">谁在看这部电影</p>
		<c:forEach var="peopleread" items="${peopleread}">
		<div style="width: 300px;height: auto;float: left;">
		    <a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${peopleread.user.user_id }"><img alt="" src="<%=basePath%>${peopleread.user.imgs}" width="50px" height="50px" style="border-radius:25px;float: left;margin-right: 20px;"></a>
		    <span style="font-size: 12px;float: left;margin-left: 5px;margin-top: 15px;color: #268DCD;">${peopleread.user.username}</span> 
		    <span style="font-size: 12px;float: left;margin-left: 15px;margin-top: 15px;"><fmt:formatDate value="${peopleread.date}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
		    <span style="font-size: 12px;float: left;margin-left: 15px;margin-top: 15px;color: red;">${peopleread.station }</span> 
		</div>
		</c:forEach>
		</div>  <!-- 谁看这本电影 -->
           <!-- content--2 -->
           <div style="width:300px; height:600px; background: #87F0CB;margin-left:20px; float:left;margin-top: 20px;">
              <img style="width:300px; height:251px; margin-top:50px" src="wj/img/douban4.png">
          </div>  
          <!-- content--2 -->  
           

 
       <!-- 底部 -->
        <!--     <div style="height:30px;margin-left:50px;margin-top:300px">   
      <div style="float:right;  position:relative;padding-top:5px;">
    	<a href="#" style=" text-decoration:none;font-size:11px ; color:#3377AA">关于曲奇</a> ·

        <a href="#" style=" text-decoration:none; font-size:11px;color:#3377AA">联系我们 </a>·
       
        <a href="#" style=" text-decoration:none;font-size:11px;color:#3377AA "> 帮助中心 </a>·
        <a href="#" style=" text-decoration:none;font-size:11px;color:#3377AA ">开发者 移动应用 · 曲奇广告</a>·

        <a href="#" style=" text-decoration:none;font-size:11px;color:#3377AA "> 曲奇广告</a>
      </div>
   </div> --><!-- 底部 -->
	
</body>
</html>
