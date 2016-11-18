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
<title>广播</title>
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
<script src="lsr/js/jquery-2.1.1.js"></script>
<script src="lsr/js/bootstrap.min.js"></script>
<script src="lsr/js/douban_loginAfter.js"></script>
<script src="lsr/js/jquery-ui.js"></script>
<script>
$(function(){
	$(document).on('click','.invo',function(e){
		e.preventDefault();
	});
	
	//添加转播
	//推荐到我的广播
	//构造虚层
	$(document).on('click','.sendtomybor',function(e){
		 var sim_comm = $('<textarea id="my_commons" name="my_commons" type="text" class="form-control" placeholder="写下你的短评..." style="resize:none;width:450px; height:100px; margin-bottom: 5px;word-break:break-all;">');
    	 $('#add_to_doulie_div').append('<h3>您将要将该广播转发至您的广播</h3><hr style="width:450px;">');
    	 $('#add_to_doulie_div').append(sim_comm);
    	 $('#add_to_doulie_div').append('<input type="hidden" id="guoduid" value="">');
    	 $('#add_to_doulie_div').append('<img style="cursor:pointer" id="submittuijian" src="/douban_user/lsr/img/finistuijian.png" style="margin-right:50px;"> ');
    	 $('#add_to_doulie_div').append('<img style="cursor:pointer" id="canceltuijian" src="/douban_user/lsr/img/canceladdtodoulist.png">');
    	 $('#guoduid').val($(this).prev().val());
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
		 var sub_item_id = $('#guoduid').val();
		 alert(sub_item_id);
		 var type_table = '2';//2：广播
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
	 $(document).on('click','.like',function(){
		 var id = $(this).next().val();
		 var item = '10';//10：广播
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
	
	
	//回应
	//构造虚层
		$(document).on('click','.addcomment',function(e){
			 var sim_comm = $('<textarea id="my_commons" name="my_commons" type="text" class="form-control" placeholder="写下你的短评..." style="resize:none;width:450px; height:100px; margin-bottom: 5px;word-break:break-all;">');
			 $('#add_to_comment_div').append('<h3>你对该广播有什么看法</h3><hr style="width:450px;">');
			 $('#add_to_comment_div').append(sim_comm);
	    	 $('#add_to_comment_div').append('<input type="hidden" id="guoduid2" value="">');
	    	 $('#add_to_comment_div').append('<input type="button" id="submithuiyin" value="发表" style="margin-right:10px;">');
	    	 $('#add_to_comment_div').append('<input type="button" id="cancelhuiyin" value="取消">');
	    	 $('#guoduid2').val($(this).next().next().val());
	    	 //显示虚层  
		        var addarround_height = document.body.scrollHeight;
				  var width = document.body.scrollWidth;
				  $('#add_to_comment_div_arround').css('height',addarround_height);
				  $('#add_to_comment_div_arround').css('width',width);	 
				  var yy = e.pageY;
	           $('#add_to_comment_div').css('top',yy-100);
				  $('#add_to_comment_div_arround,#add_to_comment_div').fadeIn(800,function(){  
					  $('#add_to_comment_div').css('display','block');
				  }); 
		});
		
		//提交回应
		 $(document).on('click','#submithuiyin',function(){
			 var mycomm = $('#my_commons').val();
			 var sub_item_id = $('#guoduid2').val();
			 alert(sub_item_id);
			 var type_table = '2';//2：广播
			 var url = '/douban_user/user/userinfo?method=giveSomethingCommons';				 
			 $.ajax({
			     type : 'post',
			     url : url,
			     dataType : 'json',
			     data : {content:mycomm,item:type_table,item_id:sub_item_id},
			     success : function(json, status, xhr){
			           //alert("success "+json+"   "+status+"   "+xhr);	          
			          if(json == false){
			             alert("很遗憾!回应失败！");
			          }else{ 
			        	  alert("回应成功！");
			        };	
			        //隐藏虚层
			         $('#add_to_comment_div_arround,#add_to_comment_div').fadeOut(800,function(){  
						 $('#add_to_comment_div').css('display','none');
					});
			        //清空add_to_comment_div
					$('#add_to_comment_div').empty();
			     },
			     error : function(responseText, status, xhr){
			         //alert("fail  "+responseText+"   "+status+"   "+xhr);
			    	 alert("由于后台程序原因，回应失败！");
			    	//隐藏虚层
			         $('#add_to_comment_div_arround,#add_to_comment_div').fadeOut(800,function(){  
						 $('#add_to_comment_div').css('display','none');
					});
			        //清空add_to_comment_div
						$('#add_to_comment_div').empty();
			     },     
			     });
		 });
		
		//取消回应
		 $(document).on('click','#cancelhuiyin',function(){
			//隐藏虚层
	         $('#add_to_comment_div_arround,#add_to_comment_div').fadeOut(800,function(){  
				 $('#add_to_comment_div').css('display','none');
			});
	        //清空add_to_comment_div
				$('#add_to_comment_div').empty();
		 });
		
});
</script>
</head>
<body>
  <jsp:include page="/lsr/douban_head.jsp"></jsp:include>
   <!-- 转播 -->
   <div id="add_to_doulie_div_arround" style="display: none;z-index: 4;position: absolute;left: 0px;top: 0px;background-color:#CCD6DA;filter:alpha(opacity=60);-moz-opacity:0.6;-khtml-opacity: 0.6;opacity: 0.6;)"></div>
    <div id="add_to_doulie_div" style="border-radius:10px;background-color:#48A355;z-index:5;display: none;width: 600px;height: 350px; position: absolute;left:400px;padding-top: 50px;padding-left: 80px;">
    </div>
   <!--   回应 -->
    <div id="add_to_comment_div_arround" style="display: none;z-index: 4;position: absolute;left: 0px;top: 0px;background-color:#CCD6DA;filter:alpha(opacity=60);-moz-opacity:0.6;-khtml-opacity: 0.6;opacity: 0.6;)"></div>
    <div id="add_to_comment_div" style="border-radius:10px;background-color:#EEC06C;z-index:5;display: none;width: 600px;height: 350px; position: absolute;left:400px;padding-top: 50px;padding-left: 80px;">
    </div>
  
<div id="left">
  <div id="myfocuspeople">
     <img src="<%=basePath%>${this_people.imgs}" width="80px" height="80px">
              ${this_people.username}的广播
              <div style="height: 12px; width: 500px;">
    	        <ul id="left_menu">
    	        <li><a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${this_people.user_id}">${this_people.username}的主页</a></li>
                    <li><a href="<%=basePath%>user/userinfo?method=searchMyBroByPage&user_id=${this_people.user_id}&typepage=ul">广播</a></li>
                    <li><a href="<%=basePath%>user/userinfo?method=showUserAllAlbums&user_id=${this_people.user_id}&typepage=ul">相册</a></li>
                    <li><a href="<%=basePath%>user/userinfo?method=showAllUserDiarys&user_id=${this_people.user_id}&typepage=ul">日记</a></li>
                    <li><a href="<%=basePath%>user/userinfo?method=showAllUserLike&user_id=${this_people.user_id}">喜欢</a></li>
                    <li><a href="<%=basePath%>doulist/doulistinfo?method=showMyDouList&user_id=${this_people.user_id}">豆列</a></li>
                    <c:if test="${sessionScope.current_user.user_id == this_people.user_id}">
                    <li><a href="<%=basePath%>user/sandmailnotice?method=showPersonalInfo&user_id=${this_people.user_id}">设置</a></li>
                    </c:if>
    	        </ul>
              </div>   
  </div>
  
  <hr style="width: 650px;float: left;">
  <div id="friends">
     <c:set var="page" value="${requestScope.mybro }"></c:set>
		    <c:forEach var="bro" items="${page.data }">
		    <div style="font-size:13px;background-color:#F9F9F9;width:670px;height:250px;margin-top: 15px;margin-bottom: 20px;padding-top: 5px;padding-bottom: 10px;padding-left:20px;">
				 <div style="width:60px;margin-top:10px;height:200px;position:relative;float:left;">
					  <a href="">
					     <img src="<%=basePath%>${bro.user_info.imgs}" width="60px" height="60px" style="border-radius:30px;">
					  </a><!-- 用户头像 -->
				 </div>
				 <div style="height:220px;width:560px; padding:10px;margin:10px;word-break:break-all;overflow:hidden;float: left;">
                      <span style="color: #3377AA;">
                         <a class="item">
                            ${bro.user_info.username }
                         </a>说 :
                      </span>		
					  <div style="float: right;margin-right: 30px;margin-left: 15px;">
					     <c:if test="${bro.imgs!=null}">
					                <img width="140px" height="170px" src="<%=basePath%>${bro.imgs }">
					     </c:if> 
					  </div>
					  <div style="height: 170px;width: 380px;padding: 10px;"> 
					      <img src="lsr/img/frontyinhao.png"> 
					          ${bro.content } 
					      <img src="lsr/img/afteryinhao.png">
					  </div>
					  <span style="color: #999999;margin-right: 150px;"> 
					      <fmt:formatDate value="${bro.time }" pattern="yyyy-MM-dd"/> 
					  </span>
					  <a class="item addcomment invo" href="#">回应</a>   
					  <a class="item like invo" href="#">赞</a> 
					  <input type="hidden" value="${bro.broadcast_id }">  
					  <a class="item sendtomybor invo" href="#">转播</a>   
				 </div> 
		    </div>	    
		    </c:forEach> 
		                <div id="mybro_more" style="width: 700px;float: left;font-size: 12px;height: auto;width: auto"></div>
		                <div style="width: 700px;float: left;text-align: center;font-size: 12px;"><a id="momybro" class="item" href="">-- 加载更多 --</a><input id="mybro_pagenum" type="hidden" value="${page.currentPage }"><input id="uid" type="hidden" value="${this_people.user_id}"></div> 
						</div>
					</div>
				</div><!-- 我的广播结束 -->
  </div>
</div><!-- left -->
   <div id="right" style="margin-top:50px;"><!--右边-->
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
		       <c:forEach items="${sessionScope.myfocus}" var="myfriend" begin="0" end="9" step="1">
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

