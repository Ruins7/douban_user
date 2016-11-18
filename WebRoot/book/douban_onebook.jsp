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

<title>读书</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="lsr/js/jquery-2.1.1.js" type="text/javascript"></script>
<script src="lsr/js/bootstrap.min.js" type="text/javascript"></script>
 

 
<link href="lsr/css/douban_onething.css" rel="stylesheet"type="text/css">

<script>
$(function(){
	 
	//添加书评
	$('#addbookcomment').click(function(){
		var content = $('#bookcomment_content').val();
		var book_id = $(this).prev().val();
		var url = '/douban_user/book/bookmanage?method=writeBookComment';				 
		 $.ajax({
		     type : 'post',
		     url : url,
		     dataType : 'json',
		     data : {content : content,book_id:book_id},
		     success : function(json, status, xhr){
		          //alert("success "+json+"   "+status+"   "+xhr);	          
		          if(json == false){
		             alert("评论失败！");
		          }else{
		        	  alert("评论成功！");
	              }
		          //清空写的评论
		          $('#bookcomment_content').val("");
		     },
		     error : function(responseText, status, xhr){
		          //alert("fail  "+responseText+"   "+status+"   "+xhr);
		    	 alert("由于后台程序原因，评论失败！");
		     },     
		     });

	});
	
	
	//选择阅读状态
	$('#wantread').click(function(){
		$('#read_state').val();
		$('#read_state').val("想读");
	});
	$('#zaidu').click(function(){
		$('#read_state').val();
		$('#read_state').val("在读");
	});
	$('#duguo').click(function(){
		$('#read_state').val();
		$('#read_state').val("已读");
	});
	
	 //打分,选择阅读状态，写短评的请求提交
	 $('#addscore').click(function(){
		 var score = $('#givescore').val();
		 var bs_content = $('#bs_content').val();
		 var station = $('#read_state').val();
		 var book_id = $('#book_id').val();  
		 var url = '/douban_user/book/bookmanage?method=giveBookStation';				 
		 $.ajax({
		     type : 'post',
		     url : url,
		     dataType : 'json',
		     data : {score : score,comment:bs_content,station:station,book_id:book_id},
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
	 
	 //添加到购书心愿单
	 $(document).on('click','#addtowish',function(e){
		 e.preventDefault();
			var url = '/douban_user/book/bookmanage?method=addBookToWannaBuy';				 
			var book_id = $('#book_id').val();  
			$.ajax({
			     type : 'post',
			     url : url,
			     dataType : 'json',
			     data : {book_id:book_id},
			     success : function(json, status, xhr){
			          //alert("success "+json+"   "+status+"   "+xhr);	          
			          if(json == false){
			             alert("添加失败！");
			          }else{
			        	  alert("添加成功！");
			        	//显示为已经添加,并且修改id
			        	$('#addtowish').text('取消加入心愿购书单');
			        	$('#addtowish').attr('id','canceladdtowish'); 
		              }      
			     },
			     error : function(responseText, status, xhr){
			           //alert("fail  "+responseText+"   "+status+"   "+xhr);
			    	  alert("由于后台程序原因，添加失败！");
			     },     
			     });
	});
	 
	 
	 //取消购书心愿
	 $(document).on('click','#canceladdtowish',function(e){
		 e.preventDefault();
			var url = '/douban_user/book/bookmanage?method=delBookFromWannaBuy';				 
			var book_id = $('#book_id').val();  
			$.ajax({
			     type : 'post',
			     url : url,
			     dataType : 'json',
			     data : {book_id:book_id},
			     success : function(json, status, xhr){
			          //alert("success "+json+"   "+status+"   "+xhr);	          
			          if(json == false){
			             alert("取消失败！");
			          }else{
			        	  alert("取消成功！");
			        	//显示为已经添加,并且修改id
			        	$('#canceladdtowish').text('加入心愿购书单');
			        	$('#canceladdtowish').attr('id','addtowish'); 
		              }      
			     },
			     error : function(responseText, status, xhr){
			           //alert("fail  "+responseText+"   "+status+"   "+xhr);
			    	  alert("由于后台程序原因，取消失败！");
			     },     
			     });
	 });
	 
	//推荐到我的广播
		//构造虚层
		$('#tuijian').click(function(e){
			 var sim_comm = $('<textarea id="my_commons" name="my_commons" type="text" class="form-control" placeholder="写下你的短评..." style="width:550px; height:100px; margin-bottom: 5px;word-break:break-all;resize:none;border-radius:10px;text-indent:10px;float:left;clear:both;"></textarea>');
	    	 $('#add_to_doulie_div').append('<p style="font-size: 28px;color:#000;font-weight: 900;margin-bottom:10px;">将该书籍推荐到我的广播</p><hr style="width:550px;float:left;margin-bottom:20px;">');
	    	 $('#add_to_doulie_div').append(sim_comm);
	    	 $('#add_to_doulie_div').append('<img style="cursor:pointer;float:left;" id="submittuijian" src="/douban_user/lsr/img/finistuijian.png">');
	    	 $('#add_to_doulie_div').append('<img style="cursor:pointer;float:left;" id="canceltuijian" src="/douban_user/lsr/img/canceladdtodoulist.png">');
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
			 var type_table = '1';//1：书籍
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

	<jsp:include page="/book/douban_bookhead.jsp"></jsp:include>

  <div id="add_to_doulie_div_arround" style="display: none;"></div>
     <div id="add_to_doulie_div" style="border-radius:10px;background-color:#48A355;z-index:5;display: none;width:600px;height: 300px; position: absolute;left:300px;padding-top: 50px;padding-left: 80px;">
     </div>


	<div style="width: 650px;height: auto;float: left;margin-left: 200px;margin-top: 20px;padding: 20px;margin-bottom:30px;background-color: #F6F6F1;border-radius:20px;box-shadow: 3px 6px 10px #ccc;border: 1px solid #ccc;""><!--曲奇的整体内容-->
		<div id="left1" style="font-size:20px;"><!--左边内容-->
		    <p style="font-size: 28px;color:#494949;font-weight: 900;margin-bottom: 20px;">${book.book_name }</p>
			<img src="<%=basePath%>${book.imgs}" width="100px" height="150px" style="float: left;margin-right: 30px;">
			<p style="color: #666;font-size: 13px;">作者：[${book.b_author.country}] <a href="<%=basePath%>book/bookmanage?method=showbooksByAuthor&author_id=${book.b_author.author_id}" style="text-decoration: none;color: #777;" >${book.b_author.author_name}</a></p>
			<p style="color: #666;font-size: 13px;">译者： ${book.b_translator.author_name }</p>
			<p style="color: #666;font-size: 13px;">又名： ${book.sub_title }</p>
			<p style="color: #666;font-size: 13px;">页数： ${book.page_num }</p>
			<p style="color: #666;font-size: 13px;">分类标签： [<a href="<%=basePath%>book/bookmanage?method=showBooksByType&type_id=${book.b_type.book_type_id}"  style="text-decoration: none;color:#583F26; ">${book.b_type.type_name }</a>]</p>
			<p style="color: #666;font-size: 13px;">出版社：${book.b_publisher.publisher_name}</p>
			<p style="color: #666;font-size: 13px;">出版日期：
				<fmt:formatDate value="${book.publish_date }" pattern="yyyy-MM-dd" />
			</p>
			<p style="color: red;font-size: 13px;">得分：${book.avgscore }</p>
			<p style="float: left;clear: both;margin-top: 10px;margin-bottom: 20px;">
			<c:if test="${alreadywishbuy=='yes'}">
			<a href="#" id="canceladdtowish" style="text-decoration: none;color: red;">取消加入心愿购书单</a>
			</c:if>
			<c:if test="${alreadywishbuy=='no'}">
			<a href="#" id="addtowish"  style="text-decoration: none;color: green;">加入心愿购书单</a>
			</c:if>
			</p>
		</div>
		<div style="margin-top: 10px;margin-bottom: 10px;"><!-- 推荐到我的广播 -->
		<input type="hidden" id="item_id" value="${book.book_id}">
		<img src="<%=basePath%>/lsr/img/tuijian.png" style="cursor: pointer;" id="tuijian"> 
		</div>
		<div style="height: auto;width: 630px;float: left;"><!-- 打分(有短评) ，想读，在读，已读 -->
		
		<c:if test="${book.current_user_book_station!=null && book.current_user_book_station.station=='想读' }">
		<p style="font-size: 13px;margin-bottom: 10px;">您当前对该书的阅读状态: <img alt="" src="<%=basePath%>lsr/img/wantread.png"> </p>
		</c:if>
		<c:if test="${book.current_user_book_station!=null && book.current_user_book_station.station=='在读' }">
		<p style="font-size: 13px;margin-bottom: 10px;">您当前对该书的阅读状态: <img alt="" src="<%=basePath%>lsr/img/zaidu.png"></p>
		</c:if>
		<c:if test="${book.current_user_book_station!=null && book.current_user_book_station.station=='已读' }">
		<p style="font-size: 13px;margin-bottom: 10px;">您当前对该书的阅读状态:  <img alt="" src="<%=basePath%>lsr/img/duguo.png"></p>
		</c:if>
		
		<c:if test="${book.current_user_book_station==null }">
		<img style="cursor: pointer;" alt="" src="<%=basePath%>lsr/img/wantread.png" id="wantread">
		<img style="cursor: pointer;" alt="" src="<%=basePath%>lsr/img/zaidu.png" id="zaidu">
		<img style="cursor: pointer;" alt="" src="<%=basePath%>lsr/img/duguo.png" id="duguo">
		<input type="hidden" value="" id="read_state">
		</c:if>
		<c:if test="${book.current_user_book_station==null }">
			    <input type="text" id="givescore" value="打分" style="width: 50px;height:25px;margin-bottom: 5px;border-radius:5px;"><span style="font-size: 12px;color: #666666;;margin-left: 20px;">0 ~ 100</span>
			    <textarea rows="3" cols="85%" id="bs_content" value="写短评" style="border-radius:5px;resize:none;"></textarea>
			    <input type="button" value="提交" id="addscore" style="margin-bottom: 20px;width: 40px;height: 30px;">
		</c:if>
		<c:if test="${book.current_user_book_station.score!=null }">
			<p style="font-size: 13px;margin-bottom: 10px;">您对该书给出的分数为：<span style="color: red;">${book.current_user_book_station.score }</span></p>
			 
		</c:if>
		</div><!-- 打分 ，想看 看过 在看 -->
		<hr style="float: left;width: 600px;color: #E9E2E2;">
		<div style="height: auto;width: 630px;float: left;margin-top: 30px;"><!-- 写书评 -->
			<p style="font-size: 14px;color: #007722;margin-bottom: 20px;">写书评······</p>
			<textarea rows="3" cols="90%" id="bookcomment_content" class="form-control" style="resize:none;"></textarea>
			<input type="hidden" value="${book.book_id}" id="book_id"> 
			<input type="button" value="添加书评" id="addbookcomment" style="width: 60px;height: 30px;margin-top: 10px;margin-bottom: 20px;">
		</div>
		<!-- 写书评 -->
		<hr style="float: left;width: 600px;color: #E9E2E2;">
		<div style="height: auto;width: 630px;float: left;">
			<!-- 书籍简介，作者简介，译者简介 , 预读-->
			<div>
				<p style="font-size: 14px;color: #007722;margin-bottom: 20px;margin-top: 30px;">内容简介······</p>
				<pre style="white-space:pre-wrap;width: 600px;font-size: 12px;">
                  ${book.simple_desc } 
               </pre>
			</div>
			<hr style="float: left;width: 600px;color: #E9E2E2;">
			<div>
				<p style="font-size: 14px;color: #007722;margin-bottom: 20px;margin-top: 30px;">作者简介······</p>
				<pre style="white-space:pre-wrap;width: 600px;font-size: 12px;">
                  ${book.b_author.simple_desc } 
               </pre>
			</div>
			<hr style="float: left;width: 600px;color: #E9E2E2;">
			<c:if test="${book.b_translator != null}">
				<div>
				<p style="font-size: 14px;color: #007722;margin-bottom: 20px;margin-top: 30px;">译者简介······</p>
				<pre style="white-space:pre-wrap;width: 600px;font-size: 12px;">
                  ${book.b_translator.simple_desc } 
               </pre>
				</div>
			</c:if>
			<hr style="float: left;width: 600px;color: #E9E2E2;">
			<div>
				<p style="font-size: 14px;color: #007722;margin-bottom: 20px;margin-top: 30px;">预读······</p>
				<pre style="white-space:pre-wrap;width: 600px;font-size: 12px;">
                  ${book.pre_read.pre_read_content } 
               </pre>
			</div>
		</div>
		<!-- 书籍简介，作者简介，译者简介, 预读 -->
		<div>
			<!-- 系统推荐 -->

		</div>
		<!-- 系统推荐 -->
		<hr style="float: left;width: 600px;color: #E9E2E2;">
		<div style="height: auto;width: 630px;float: left;">
			<!-- 打分时的评价（短评） -->
			<p style="font-size: 14px;color: #007722;margin-bottom: 20px;margin-top: 30px;">短评······</p>
			<c:forEach var="bs" items="${book.bookstation}">
			<div style="width: 630px;height: auto;float: left;margin-bottom: 20px;">
				<a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${bs.user.user_id }"><img src="<%=basePath%>${bs.user.imgs}" width="50px" height="50px" style="border-radius:25px;float: left;margin-right: 10px;"></a>
				<span style="font-size: 13px;color: #583F26;margin-bottom: 5px;margin-left: 10px;"> ${bs.user.username} 打分：<span style="color: red;">${bs.score }</span> </span>
                <span style="font-size: 13px;color: #583F26;float: right;margin-left: 10px;"><fmt:formatDate value="${bs.date }" pattern="yyyy-MM-dd HH:mm:ss" /></span>
				<pre style="white-space:pre-wrap;margin-top: 10px;margin-left: 10px;width: 400px;float: left;">${bs.comment}</pre>
			</div>
			</c:forEach>
		</div>
		<hr style="float: left;width: 600px;color: #E9E2E2;">
		<!-- 打分时的评价（短评）-->
		 
		<div style="height: auto;width: 630px;float: left;margin-top: 10px;">
			<!-- 书评 -->
			<p style="font-size: 14px;color: #007722;margin-bottom: 20px;margin-top: 30px;">书评······</p>
			<c:forEach var="bc" items="${book.bookcoment}">
			<div style="width: 630px;height: auto;float: left;margin-bottom: 20px;">
				<a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${bc.user.user_id }"><img src="<%=basePath%>${bc.user.imgs}" width="50px" height="50px" style="border-radius:25px;float: left;margin-right: 10px;"></a>
				<span style="font-size: 13px;color: #583F26;margin-left: 10px;">${bc.user.username}</span>
				<span style="font-size: 13px;color: #583F26;float: right;margin-left: 10px;"><fmt:formatDate value="${bc.date }" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                <pre style="white-space:pre-wrap;margin-left: 10px;margin-top: 10px;width: 400px;float: left;">${bc.masg}</pre>
			</div>	 
			</c:forEach>
		</div>
		
		<!-- 书评-->

		<!--左边内容-->
	
	</div>
  	<!--曲奇的整体内容-->
   	<!--右边内容-->
		<div style="width: 300px;float: left;margin-left: 50px;margin-top: 20px;">
			<!--热门标签-->
			<p style="font-size: 18px;color: #5B422A;font-weight: 900;margin-right: 20px;margin-bottom:10px;margin-top: 20px;">热门标签</p>
			<c:forEach var="alltype" items="${book_type}">
                   <span style="color:#583F26;margin-right:5px;">[<a href="<%=basePath%>book/bookmanage?method=showBooksByType&type_id=${alltype.book_type_id}"  style="text-decoration: none;color:#583F26; ">${alltype.type_name}</a>]</span>
            </c:forEach>
		</div><!--热门标签-->
		<div  style="width: 300px;float: left;margin-left: 50px;"><!-- 谁读这本书 -->
		<p style="font-size: 18px;color: #5B422A;font-weight: 900;margin-right: 20px;margin-bottom:10px;margin-top: 20px;margin-top: 50px;">谁读这本书</p>
		<c:forEach var="peopleread" items="${peopleread}">
		<div style="width: 300px;height:70px;float:left; ">
		  <a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${peopleread.user.user_id }"><img alt="" src="<%=basePath%>${peopleread.user.imgs}" width="50px" height="50px" style="border-radius:25px;float: left;margin-right: 20px;"></a> 
		     <span style="font-size: 13px;margin-top: 15px;float: left;margin-right: 10px;">${peopleread.user.username}</span>
		   <span style="font-size: 13px;margin-top: 15px;float: left;color: #666;"><fmt:formatDate value="${peopleread.date}" pattern="yyyy-MM-dd" /><span style="color: Red;margin-left:20px;">${peopleread.station }</span> </span>
		</div>
		</c:forEach>
		</div><!-- 谁读这本书 -->
		
		<!--右边内容-->


	<div id="bottom">
		<!--曲奇图片div-->
		<div class="doubanphoto">
			<img src="lsr/img/douban5.png">
		</div>
	</div>
	<!--曲奇图片div-->
</body>
</html>
