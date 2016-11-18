<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'offlineActivity.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

</head>
   <link href="hyy/css/jquery-ui.css" rel="stylesheet" >
   <link href="hyy/css/bootstrap.min.css" rel="stylesheet" type="text/css">
   <link href="hyy/css/bootstrap-theme.css" rel="stylesheet" type="text/css">
   <link href="hyy/css/douban_aOffActivity.css" rel="stylesheet" type="text/css">

<body>
	<div id="title"><!--菜单栏div-->
		<div style="width:750px; float:left; position:relative; height:29px;">
    		<ul class="menu">  <!--菜单栏内容-->
            <li><a href="<%=basePath%>user/userinfo?method=searchFriendsBroByPage&user_id=${sessionScope.current_user.user_id}">豆瓣</a></li>
			<li><a href="<%=basePath%>douban_read.jsp">读书</a></li>
			<li><a href="<%=basePath%>servlet/movieServlet?method=allfindMovies">电影</a></li>
			<li><a href="#">音乐</a></li>
			<li><a href="<%=basePath%>hyy/douban_city.jsp">同城</a></li>
			<li><a href="<%=basePath %>groupServlet?method=loginGroup">小组</a></li>
			<li><a href="<%=basePath%>things/thingsinfo?method=searchThingsByPage&typepage=ul&user_id=${sessionScope.current_user.user_id}">东西</a></li>
			<li><a href="#">更多</a></li>
        	</ul>
		</div>
		<div style="float:right; position:relative; width:200px;">
            <ul class="menu" >
                <li ><a href="#" style="font-size:12px;">提醒</a></li>
                <li ><a href="<%=basePath%>douban_douemail.html" style="font-size:12px;">豆邮</a></li>
                <li><a href="#" style="font-size:12px; width:90px">${sessionScope.current_username }</a></li>
            </ul>
        </div>
   	</div><!--菜单栏div-->
    
    	<div id="city_title">
    		<div class="city_img"><img src="hyy/img/city.png"></div>
    		<form>
            	<div style="padding-top:18px; float:left; position:relative; padding-left:10px;">
                	<select style="width:70px; height:30px; ">
                    	<option>天津</option>
                    	<option>北京</option>
                    	<option>上海</option>
                   		<option>南京</option>
                	</select>
            	</div>
            	<div style="padding-top:23px; float:left; position:relative; padding-left:30px;">
             		<div class="amenu"><a href="indexOffActivity.jsp" style="text-decoration:none">同城活动</a></div>
                	<div class="amenu"><a href="#" style="text-decoration:none">主办方</a></div>
                	<div class="amenu"><a href="#" style="text-decoration:none">舞台剧</a></div>
                	<div class="amenu"><a href="#"style="text-decoration:none" >我的同城</a></div>
            	</div>
             
             	<!--搜索-->
           		<div class="seach">
                   <input type="text" class="form-control" placeholder="活动名称，地点，介绍，舞台剧" style=" float:left; position:relative;width:220px; height:30px">
                  <button type="submit"  style="float:left; position:relative">搜索</button>
            	</div>
       		</form>
    	</div>
        
        
        <!-- 下方整体 -->
        <div style=" margin-left:150px; width:950px;">
        	<!-- 左边 -->
        	<div style="margin-top:50px; width:950px; height:18px;">
            	<h4>
                	${requestScope.title }
                </h4>
            </div>
            
            <!-- 下方整体 -->
            <div style=" width:950px; margin-top:50px; background-color:#87FF75; height:auto;">
            	<!-- 左边 -->
                <div style="float:left; position:relative; height:auto; ">
                	<div style="background-color:#f5f5f5; height:25px; width:590px;">
                		<div id="action1" style="background-color:#f5f5f5;margin-left:20px; float:left;">
                        	<a href="#" style=" height:25px;">
                            	<span style="font-size:12px;">大家的提问</span>
                            </a>
                        </div>
                   		<div id="action2" style="background-color:#f5f5f5;margin-left:20px; float:left;">
                         	<a href="hyy/service/ActivityServlet?from_user_id=${requestScope.from_user_id }&to_user_id=${requestScope.to_user_id }&offActivity_id=${requestScope.activity_id }&method=setParameter&title=${requestScope.title}&user_id=${requestScope.user_id}" style=" height:25px;">
                            	<span style="font-size:12px;">我的提问</span>
                            </a>
                    	</div>                       
                	</div>
                    <div style=" margin-top:20px;" id="ask_div">
                        	<span>在此向主办方提出你关于这个活动的问题：</span>
                            <div>
                            	<form>
                                	<textarea rows="5" cols="70" name="askContent" id="askContent"></textarea><br>
                                    <input type="button" value="  提问  " id="button1">
                                </form>
                            </div>
                     </div>
                </div>
                <!-- 右边 -->
                <div style="width:350px;  height:100px; float:left; position:relative;">
                	<span style="margin-left:20px;"><a href="ActivityManageServlet?method=searchAOffActivity&id=${activity_id}" >  > 去 ${requestScope.title } 的网页</a></span>
                </div>
            </div>
        </div>
   </body>
</html>

<script src="hyy/js/jquery-2.1.1.js"></script>
<script src="hyy/js/bootstrap.min.js"></script>
<script src="hyy/js/jquery.js"></script>

   <script src="hyy/js/jquery-ui.js"></script>
   <script src="hyy/js/askOffActivity.js"></script>
<script>
$(function(){
	$("#return").click(function(){
		alert("点击事件");
	})
	
	$("#action1").click(function(event){
			event.preventDefault();
			$.ajax({
				type : 'post',
				url : 'http://localhost:8080/douban_user/hyy/service/ActivityServlet' ,
				data : {
						method:"askOffContent",
						activity_id:${activity_id}
							},
				success : function(data , status , xhr ) {
					if(data==0){
						$("#ask_div").empty();
						var div = $("这里还没有人提问,点击我的提问向发起者提问");
						$("#ask_div").append(div);
					}else{
						var data1 = eval('('+data+')');
						$("#ask_div").empty();
						$.each(data1,function(index,element){
							var div = $("<div style='width:590px; background-color:#F5F5F5; height:auto; margin-top:10px;'></div>");
							var div1 = $("<div style='width:590px;'>"+element.time.year+"-"+(element.time.month+1)+"-"+element.time.day+" "+element.time.hours+":"+element.time.minutes+":"+element.time.seconds+"</div>");
							var pic = $("<a href='#'><img style='width:20px; height:20px;' src='"+element.user_from.imgs+"'></a>");
							var div2;
							if(${requestScope.user_id==element.user_from.user_id}){
								div2 = $("<div style='width:590px;height:auto;'><a href='#'>发起者</a> 说： "+element.ask_comment+"</div>");
							}else{
								div2 = $("<div style='width:590px;height:auto;'><a href='#'>"+element.user_from.username+"</a>说："+element.ask_comment+"</div>");
							}
							div.append(div1).append(pic).append(div2);
							$("#ask_div").append(div);
						});
					}
				}
			});	
	})
	
	
	
	
	$("#button1").click(function(){
		alert($("#askContent").val());
		$.ajax({
			type : 'post',
			url : 'http://localhost:8080/douban_user/hyy/service/ActivityServlet' ,
			data : {
					method:"askOff",
					activity_id:${activity_id},
					ask_from_id:${requestScope.from_user_id},
					ask_content:$("#askContent").val()
						},
			dataType: 'json', 
			success : function(data , status , xhr ) {
				if(data==0){
					alert("抱歉，提问失败");
				}else{
					alert("恭喜，提问成功");
					$("#askContent").val("");
				}
			}
		});
	});	
	

});
</script>