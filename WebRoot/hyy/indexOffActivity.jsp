<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	
</head>
   <link href="hyy/css/jquery-ui.css" rel="stylesheet" >
   <link href="hyy/css/bootstrap.min.css" rel="stylesheet" type="text/css">
   <link href="hyy/css/bootstrap-theme.css" rel="stylesheet" type="text/css">
   <link href="hyy/css/douban_city.css" rel="stylesheet" type="text/css">

<body>
	<div id="title"><!--菜单栏div-->
		<div style="width:250px; float:left; position:relative; height:29px;">
    		<ul class="menu">  <!--菜单栏内容-->
                <li><a href="<%=basePath%>user/userinfo?method=searchFriendsBroByPage&user_id=${sessionScope.current_user.user_id}">豆瓣</a></li>
                <li><a href="<%=basePath%>douban_read.jsp"  >读书</a></li>
                <li><a href="<%=basePath%>servlet/movieServlet?method=allfindMovies"  >电影</a></li>
                <li><a href="#"  >音乐</a></li>
                <li><a href="<%=basePath%>hyy/douban_city.jsp" >同城</a></li>
                <li><a href="<%=basePath %>groupServlet?method=loginGroup"  >小组</a></li>
                <li><a href="<%=basePath%>things/thingsinfo?method=searchThingsByPage&typepage=ul&user_id=${sessionScope.current_user.user_id}" >东西</a></li>
                <li><a href="#">更多</a></li>
        	</ul>
		</div>
		<div style="float:right; position:relative; width:200px;">
            <ul class="menu" >
                <li ><a href="#" style="font-size:12px;">提醒</a></li>
                <li ><a href="<%=basePath%>douban_douemail.html" style="font-size:12px;">豆邮</a></li>
                <li><a href="#" style="font-size:12px; width:90px">用户名的账号</a></li>
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
             		<div class="amenu"><a href="#" style="text-decoration:none">同城活动</a></div>
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
        <div style="margin-top:30px; width:950px; height:28px; margin-left:150px;">
        	<h3 style="font-weight:bold;">天津最近一周同城活动</h3>
        </div>
		
        <!-- 下方大体 -->
		<div style="width:1000px; height:auto; min-height:100px; margin-left:150px; margin-top:30px;">
        	<!-- 左边 -->
            <div style="width:650px; height:auto; min-height:20px; float:left; position:relative;">
            	<div style="width:auto; height:20px;">
                	<ul id="off_type">
                    <li style="list-style-type:none; float:left; position:relative;">类型：</li>
                	<li style="list-style-type:none; float:left; position:relative; margin-left:5px;"><a style="text-decoration:none; margin-left:5px; background-color:#BDD3FF;" href="#" ><span>全部</span></a></li>
                    	<li style="list-style-type:none; float:left; position:relative; margin-left:5px;"><a style="text-decoration:none; margin-left:5px;" href="#"><span>音乐</span></a></li>
                    	<li style="list-style-type:none; float:left; position:relative; margin-left:5px;"><a style="text-decoration:none; margin-left:5px;" href="#"><span>聚会</span></a></li>
                       <li style="list-style-type:none; float:left; position:relative; margin-left:5px;"><a style="text-decoration:none; margin-left:5px;" href="#"><span>讲座</span></a></li>
                    </ul>
                </div>
                <div style="margin-top:10px; height:20px; width:auto;" id="off_time">
                	<ul>
                    	<li style="list-style-type:none; float:left; position:relative;">时间：</li>
                        <li style="list-style-type:none; float:left; position:relative; margin-left:5px;"><a style="text-decoration:none; margin-left:5px; background-color:#BDD3FF;" href="#"><span >全部</span></a></li>
                        <li style="list-style-type:none; float:left; position:relative; margin-left:5px;"><a style="text-decoration:none; margin-left:5px;" href="#"><span>今天</span></a></li>
                        <li style="list-style-type:none; float:left; position:relative; margin-left:5px;"><a style="text-decoration:none; margin-left:5px;" href="#"><span>明天</span></a></li>
                        <li style="list-style-type:none; float:left; position:relative; margin-left:5px;"><a style="text-decoration:none; margin-left:5px;" href="#"><span>周末</span></a></li>
                        <li style="list-style-type:none; float:left; position:relative; margin-left:5px;"><a style="text-decoration:none; margin-left:5px;" href="#"><span>最近一周</span></a></li>
                    </ul>
                </div>
                <hr>
                
                <!-- 单个 -->
                <div id="activity">
                
                </div>
            </div>
            
            <!-- 右边 -->
            <div style="width:310px; height:auto; min-height:30px; float:right; position:relative; ">
            	<div>
                	<a href="#"><img src="img/adverse1502.png" style="height:250px; width:300px;"></a>
                </div>
                <div style="width:120px; height:25px; color:#FFFFFF;  border-radius:2px; margin-top:20px;">
                	<span><a href="#" style="text-decoration:none; align:center; color:#FFFFFF;">+发起同城活动</a></span>
                </div>
                
                <!-- 活跃的主办方 -->
                <div style="height:240px; width:310px; margin-top:20px;">
                	<div style="height:20px; width:auto; ">
                    	<div style="float:left; position:relative; color:#333333">天津活跃的主办方</div>
                        <div style="float:right; position:relative; height:14px; width:36px; font-size:12px;" ><a href="#" style="text-decoration:none;"><span id="title_span">更多></span>></a></div>
                    </div>
                    <!-- 活动主办方展示 ，单个-->
                    <div style="width:310px; height:198px; ">
                    	<div style="width:310px; height:50px;  margin-top:5px;">
                        	<div style="height:48px; width:48px;  float:left; position:relative;">
                            	<a href="#"><img src="#" style="height:48px; width:48px;"></a>
                            </div>
                            <div style="width:310px; height:43px; ">
                            	<div style="width:250px; height:20px; float:left; position:relative;">
                            		<span id="title_span"><a href="#" style="text-decoration:none;">猫的天空之城</a></span>
                           		</div>
                            	<div style="float:left; position:relative; width:250px;">
                            		<span>有<a href="#" style="text-decoration:none;">18个活动</a>正在进行</span>
                            	</div>
                            </div>
                        </div>
                        
                        <div style="width:310px; height:50px;  margin-top:5px;">
                        	<div style="height:48px; width:48px;  float:left; position:relative;">
                            	<a href="#"><img src="#" style="height:48px; width:48px;"></a>
                            </div>
                            <div style="width:310px; height:43px; ">
                            	<div style="width:250px; height:20px; float:left; position:relative;">
                            		<span id="title_span"><a href="#" style="text-decoration:none;">猫的天空之城</a></span>
                           		</div>
                            	<div style="float:left; position:relative; width:250px;">
                            		<span>有<a href="#" style="text-decoration:none;">18个活动</a>正在进行</span>
                            	</div>
                            </div>
                        </div>
                        
                        <div style="width:310px; height:50px; margin-top:5px;">
                        	<div style="height:48px; width:48px;  float:left; position:relative;">
                            	<a href="#"><img src="#" style="height:48px; width:48px;"></a>
                            </div>
                            <div style="width:310px; height:43px; ">
                            	<div style="width:250px; height:20px; float:left; position:relative;">
                            		<span id="title_span"><a href="#" style="text-decoration:none;">猫的天空之城</a></span>
                           		</div>
                            	<div style="float:left; position:relative; width:250px;">
                            		<span>有<a href="#" style="text-decoration:none;">18个活动</a>正在进行</span>
                            	</div>
                            </div>
                        </div>
                    </div>                                     
                </div>
                
            </div>
        </div>
   </body>
</html>

<script src="hyy/js/jquery-2.1.1.js"></script>

<script>
$(function(){
	$("#title_span").mouseover(function(){
		$(this).css("color","#FFFFFF").css("background-color","#3377AA");
	});
	$("#title_span").mouseleave(function(){
		$(this).css("color","#005CFF").css("background-color","#FFFFFF");
	});
	
	
	var type = '全部';
	var time = '全部';
			//刚进入网页的时候加载的东西
		$.ajax({
			type : 'post',
			url : 'http://localhost:8080/douban_user/hyy/service/ActivityManageServlet' ,
			data : {
					method:"getIndexOffActivity",
					type:type,
					time:time
						},
			success : function(data , status , xhr ) {
				$("#music_div").empty();
				var json = eval('('+data+')');
				$.each(json , function(index , value){   //value -- json
					var s = value.offactivity_id;
					var div1 = $("<div style='float:left; position:relative; width:74px; height:139px'><img style='width:71px;height:91px;' src="+value.imgs+"></div>");
					var div2 = $("<div style='float:left; position:relative;width:236px; height:139px;'></div>");
					var a = $("<a href='hyy/service/ActivityManageServlet?method=searchAOffActivity&id='"+s+"'>"+value.offactivity_title+"</a>");
					var p = $("<p style='font-size:12px; color:#666666'>"+value.position+"<br>"+value.attentionPerson.length+"人参加</p>");

					div2.append(a).append(p);
					$("#music_div").append(div1).append(div2);
				});
				$.ajax({
					type : 'post',
					url : 'http://localhost:8080/douban_user/hyy/service/ActivityManageServlet' ,
					data : {
							method:"getIndexOffActivity",
							type:type,
							time:time
								},
					dataType: 'json', 
					success : function(data , status , xhr ) {
						$("#activity").empty();
						$.each(data , function(index , value){   //value -- json
							//创建一个底层
							var div = $("<div style='width:590px; height:165px; margin-top:10px;'></div>");
							//创建一个图片层
							var div_pic = $("<div style='float:left; position:relative;'><a href='#'><img src='"+value.imgs+"' style='height:150px; width:110px;'></a></div>");
							
							//创建一个文字描述的底层
							var div_desc = $("<div style=' width:460px; height:148px; float:right; position:relative;'></div>");
							var div_title = $("<div> <a href='hyy/service/ActivityManageServlet?method=searchAOffActivity&id="+value.offactivity_id+"' style='text-decoration:none; color:005CFF;'><span id='title_span'>"+value.offactivity_title+"</span></a></div>");
							
							var div_time = $("<div style='margin-top:5px;'><span>时间:"+value.start_time+"至"+value.end_time+" "+value.everyday+"</span></div>");
							var div_user = $("<div style='margin-top:5px;'><span>发起者：<a href='#'>"+value.user.username+"</a></span></div>");
							
							var div_attention = $("<div style='margin-top:10px; width:139px; height:25px;'></div>");
							var p_attend = $("<span style='font-size:13px; color:#666666;'>"+value.attendPerson.length+"人参加</span>/|");
							var p_attention = $("<span style='font-size:13px; color:#666666;'>"+value.attentionPerson.length+"人关注</span>");
							
							
							div_attention.append(p_attend).append(p_attention);
							div_desc.append(div_title).append(div_time).append(div_user).append(div_attention);
							div.append(div_pic).append(div_desc);
							$("#activity").append(div);			
							
						});
					}
				});
				
			}
		});
	
	
	
	

	
	
	//定义两个标签，分别保存类型和时间的值
	$("#off_type  a").click(function(event){
		event.preventDefault();
		type=$(this).children("span").text();
		/* alert(type+"--"+time); */
		$(this).children("span").css("background-color","#BDD3FF");
		$(this).parent("li").prevAll("li").children("a").children("span").css("background-color","#FFFFFF");
		$(this).parent("li").nextAll("li").children("a").children("span").css("background-color","#FFFFFF");
		
		$.ajax({
			type : 'post',
			url : 'http://localhost:8080/douban_user/hyy/service/ActivityManageServlet' ,
			data : {
					method:"getIndexOffActivity",
					type:type,
					time:time
						},
			success : function(data , status , xhr ) {
				$("#music_div").empty();
				var json = eval('('+data+')');
				/* alert( "json:"+json); */
				$.each(json , function(index , value){   //value -- json
					var div1 = $("<div style='float:left; position:relative; width:74px; height:139px'><img style='width:71px;height:91px;' src="+value.imgs+"></div>");
					var div2 = $("<div style='float:left; position:relative;width:236px; height:139px;'></div>");
					var a = $("<a href='hyy/service/ActivityManageServlet?method=searchAOffActivity&id="+value.offactivity_id+"'>"+value.offactivity_title+"</a>");
					var p = $("<p style='font-size:12px; color:#666666'>"+value.position+"<br>"+value.attentionPerson.length+"人参加</p>");

					div2.append(a).append(p);
					$("#music_div").append(div1).append(div2);
				});
				$.ajax({
					type : 'post',
					url : 'http://localhost:8080/douban_user/hyy/service/ActivityManageServlet' ,
					data : {
							method:"getIndexOffActivity",
							type:type,
							time:time
								},
					dataType: 'json', 
					success : function(data , status , xhr ) {
						$("#activity").empty();
						$.each(data , function(index , value){   //value -- json
							//创建一个底层
							var div = $("<div style='width:590px; height:165px; margin-top:10px;'></div>");
							//创建一个图片层
							var div_pic = $("<div style='float:left; position:relative;'><a href='hyy/service/ActivityManageServlet?method=searchAOffActivity&id="+value.offActivity_id+"'><img src='"+value.imgs+"' style='height:150px; width:110px;'></a></div>");
							
							//创建一个文字描述的底层
							var div_desc = $("<div style='width:460px; height:148px; float:right; position:relative;'></div>");
							var div_title = $("<div> <a href='hyy/service/ActivityManageServlet?method=searchAOffActivity&id="+value.offactivity_id+"' style='text-decoration:none; color:005CFF;'><span id='title_span'>"+value.offactivity_title+"</span></a></div>");
							
							var div_time = $("<div style='margin-top:5px;'><span>时间:"+value.start_time+"至"+value.end_time+" "+value.everyday+"</span></div>");
							var div_user = $("<div style='margin-top:5px;'><span>发起者：<a href='#'>"+value.user.username+"</a></span></div>");
							
							var div_attention = $("<div style='margin-top:10px; width:139px; height:25px;'></div>");
							var p_attend = $("<span style='font-size:13px; color:#666666;'>"+value.attendPerson.length+"人参加</span>/|");
							var p_attention = $("<span style='font-size:13px; color:#666666;'>"+value.attentionPerson.length+"人关注</span>");
							
							
							div_attention.append(p_attend).append(p_attention);
							div_desc.append(div_title).append(div_time).append(div_user).append(div_attention);
							div.append(div_pic).append(div_desc);
							$("#activity").append(div);			
							
						});
					}
				});
				
			}
		});
	});
	
	$("#off_time a").click(function(event){
		event.preventDefault();
		time=$(this).children("span").text();
		/* alert(type+"--"+time); */
		/* alert($(this).children("span").text()); */
		$(this).children("span").css("background-color","#BDD3FF");
		$(this).parent("li").prevAll("li").children("a").children("span").css("background-color","#FFFFFF");
		$(this).parent("li").nextAll("li").children("a").children("span").css("background-color","#FFFFFF");
		$.ajax({
			type : 'post',
			url : 'http://localhost:8080/douban_user/hyy/service/ActivityManageServlet' ,
			data : {
					method:"getIndexOffActivity",
					type:type,
					time:time
						},
			success : function(data , status , xhr ) {
				$("#music_div").empty();
				var json = eval('('+data+')');
				/* alert( "json:"+json); */
				$.each(json , function(index , value){   //value -- json
					var div1 = $("<div style='float:left; position:relative; width:74px; height:139px'><img style='width:71px;height:91px;' src="+value.imgs+"></div>");
					var div2 = $("<div style='float:left; position:relative;width:236px; height:139px;'></div>");
					var a = $("<a href='hyy/service/ActivityManageServlet?method=searchAOffActivity&id="+value.offactivity_id+"'>"+value.offactivity_title+"</a>");
					var p = $("<p style='font-size:12px; color:#666666'>"+value.position+"<br>"+value.attentionPerson.length+"人参加</p>");

					div2.append(a).append(p);
					$("#music_div").append(div1).append(div2);
				});
				$.ajax({
					type : 'post',
					url : 'http://localhost:8080/douban_user/hyy/service/ActivityManageServlet' ,
					data : {
							method:"getIndexOffActivity",
							type:type,
							time:time
								},
					dataType: 'json', 
					success : function(data , status , xhr ) {
						$("#activity").empty();
						$.each(data , function(index , value){   //value -- json
							//创建一个底层
							var div = $("<div style='width:590px; height:165px; margin-top:10px;'></div>");
							//创建一个图片层
							var div_pic = $("<div style='float:left; position:relative;'><a href='hyy/service/ActivityManageServlet?method=searchAOffActivity&id="+value.offactivity_id+"'><img src='"+value.imgs+"' style='height:150px; width:110px;'></a></div>");
							
							//创建一个文字描述的底层
							var div_desc = $("<div style='width:460px; height:148px; float:right; position:relative;'></div>");
							var div_title = $("<div> <a href='hyy/service/ActivityManageServlet?method=searchAOffActivity&id="+value.offactivity_id+"' style='text-decoration:none; color:005CFF;'><span id='title_span'>"+value.offactivity_title+"</span></a></div>");
							
							var div_time = $("<div style='margin-top:5px;'><span>时间:"+value.start_time+"至"+value.end_time+" "+value.everyday+"</span></div>");
							var div_user = $("<div style='margin-top:5px;'><span>发起者：<a href='#'>"+value.user.username+"</a></span></div>");
							
							var div_attention = $("<div style='margin-top:10px; width:139px; height:25px;'></div>");
							var p_attend = $("<span style='font-size:13px; color:#666666;'>"+value.attendPerson.length+"人参加</span>/|");
							var p_attention = $("<span style='font-size:13px; color:#666666;'>"+value.attentionPerson.length+"人关注</span>");
							
							
							div_attention.append(p_attend).append(p_attention);
							div_desc.append(div_title).append(div_time).append(div_user).append(div_attention);
							div.append(div_pic).append(div_desc);
							$("#activity").append(div);			
							
						});
					}
				});
				
			}
		});
	});
})
</script>