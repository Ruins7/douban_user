<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
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
  
  
  <body style="min-width:1237px;">
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
    <div id="bottom">
    	<!-- 左边 -->
    	<div id="bottom_left">
        <!-- 图片和文字信息 -->
        	<div style="height:260px; width:590px; ">
        		<div id="img_div">
           	 		<img src="${requestScope.offActivity.imgs }" id="img_style">
           		 </div>
           		 <div id="info_div">
            		<h3 style="font-weight:bold;">${requestScope.offActivity.offactivity_title }</h3>
             	   	<div>
                		<div>
                			<span id="info_desc">时间：</span><span>
                				<c:if test="${requestScope.offActivity.start_time.year!=requestScope.offActivity.end_time.year }">
                					<fmt:formatDate value="${requestScope.offActivity.start_time }" pattern="MM.dd"/>.
                					<fmt:formatDate value="${requestScope.offActivity.start }"/>
                					<fmt:formatDate value="${requestScope.offActivity.start_time }" pattern="HH:mm"/>-
                					<fmt:formatDate value="${requestScope.offActivity.end_time }" pattern="HH:mm"/>
                				</c:if>
                				<c:if test="${requestScope.offActivity.start_time.year==requestScope.offActivity.end_time.year&&requestScope.offActivity.start_time.month==requestScope.offActivity.end_time.month&&requestScope.offActivity.start_time.day!=requestScope.offActivity.end_time.day }">
                					<fmt:formatDate value="${requestScope.offActivity.start_time }" pattern="MM-dd"/>至<fmt:formatDate value="${requestScope.offActivity.end_time }" pattern="MM-dd"/>
                					<fmt:formatDate value="${requestScope.offActivity.start_time }" pattern="HH:mm"/>-<fmt:formatDate value="${requestScope.offActivity.end_time }" pattern="HH:mm"/>
                					${requestScope.offActivity.everyday }
                				</c:if>
                				
                				<c:if test="${requestScope.offActivity.start_time.year==requestScope.offActivity.end_time.year&&requestScope.offActivity.start_time.month==requestScope.offActivity.end_time.month&&requestScope.offActivity.start_time.day==requestScope.offActivity.end_time.day }">
                					<fmt:formatDate value="${requestScope.offActivity.start_time }" pattern="MM-dd E"/>
                					<fmt:formatDate value="${requestScope.offActivity.start_time }" pattern="HH:mm"/>-<fmt:formatDate value="${requestScope.offActivity.end_time }" pattern="HH:mm"/>
                				</c:if>
                			</span>
                		</div>
                   	 	<div><span id="info_desc">地点：</span><span>${requestScope.offActivity.position }</span> </div>
                    	<div><span id="info_desc">费用：</span><span> 
                    		<c:forEach items="${requestScope.offActivity.tickets }" var="ticket">
                    			${ticket.ticket_price }${ticket.ticket_desc } 
                    		</c:forEach>
                    	</span></div>
                   	 	<div><span id="info_desc">类型：</span><span><a href="">${requestScope.offActivity.offactivity_bigtype }-${requestScope.offActivity.offActivity_smalltype }</a></span></div>
                    	<div><span id="info_desc">发起人：</span><span><a href="">${requestScope.offActivity.user.username }</a></span></div>
                    	<div id="info_people">
                    		<span style="font-weight:bold;">${fn:length(requestScope.offActivity.attentionPerson )}</span><span>人感兴趣</span>
                    		<span style="font-weight:bold;">${fn:length(requestScope.offActivity.attendPerson )}</span><span>参加</span>
                    	</div>
          			</div>
            	</div>

            	<div id="action">
            		<div style="margin-left:10px; background-color:#FFD6D6; border:#FDDADA; border-radius:2px; height:24px; width:64px; float:left; position:relative;"><a href="#"  style="align:center; color:#FF484B">我感兴趣</a></div>
                	<div style="margin-left:10px; background-color:#FFD6D6; border:#FDDADA; border-radius:2px; height:24px; width:64px; float:left; position:relative;"><a href="#"  style="align:center; color:#FF484B">我要参加</a></div>
                
               		<span style="margin-left:20px;" id="addDouList"><a href="">添加到豆列</a></span>
               		<span style="margin-left:20px;"><a href="">分享到</a></span>
               		<span style="margin-left:20px;"><a href="">推荐</a></span>
            	</div>
    		</div>
            <!-- 活动详情 -->
            <div style=" height:auto; width:auto;">
            	<h3 style="color:#007722;">活动详情</h3>
                ${requestScope.offActivity.offactivity_desc }
            </div>
            
            <!-- 我来问主办方 -->
            <div style="margin-top:10px;" id="ask">
                  <div style="color:#007722; font-size:14px;">我来问主办方...(<a href="">全部${requestScope.ask_total }个</a>.<a href="hyy/service/ActivityServlet?from_user_id=${sessionScope.current_user.user_id }&to_user_id=${requestScope.offActivity.user.user_id }&offActivity_id=${requestScope.offActivity.offactivity_id }&method=setParameter&title=${offActivity.offactivity_title}&user_id=${offActivity.user.user_id}">提问</a>)</div>
            </div>
            <div style="width:auto; min-width:590px; height:80px;">
            	<a href="">查看记录</a>
            </div>
            <div>
            	<div style="color:#007722; font-size:14px;">这个活动的论坛</div>
                <div style="margin-left:10px; background-color:#FFD6D6; border:#FDDADA; border-radius:2px; height:24px; width:250px; " id="">
                	<a href="#"  style="align:center; color:#FF484B">第一个在本活动的论坛里发言</a>
                </div>
            </div>
        </div>
        
        <!-- 右边 -->
        <div id="bottom_right">
        	<a href="#"><img src="hyy/img/adverse.png" style="height:250px; width:300px;"></a>
            <div style="margin-top:10px; height:310px; width:243px;">
            	<span style="font-size:14px; color:#007722;">活动地图</span><span style="font-size:12px;">(<a href="#">查看大图</a>)</span>
                <div id="allmap" style="height:243px; width:310px;"></div>
            </div>
            <div style="margin-top:20px;">
            	<span style="font-size:14px; color:#007722;">活动组织者<span style="font-size:12px;">(<a href="#">我要组织</a>)</span>
                <div style=" height:auto; min-height:43px; min-width:310px;">
                	<img src="" style="width:35px; height:35px;">
                </div>
            </div>
            
            <!-- 参加、感兴趣人员 -->
            <div style="margin-top:20px">
            	<span style="font-size:14px; color:#007722;">活动成员<span style="font-size:12px;">(<a href="#">${fn:length(requestScope.offActivity.attendPerson )}参加</a>.<a href="#">${fn:length(requestScope.offActivity.attentionPerson )}人感兴趣</a>)</span>
                <div style=" width:310px; min-height:43px; height:auto;">
                	<c:forEach items="${requestScope.offActivity.attendPerson }" var="user">
                		<div style="width:43px; height:43px; float:left; position:relative;">
                    		<a href=""><img src="${user.imgs }" style="width:40px; height:40px;"></a>
                   		</div>
                	</c:forEach>
                	
                	<c:forEach items="${requestScope.offActivity.attentionPerson }" var="user">
                		<div style="width:43px; height:43px; float:left; position:relative;">
                    		<a href=""><img src="${user.imgs }" style="width:40px; height:40px;"></a>
                   		</div>
                	</c:forEach>
                	<!-- <div style="width:43px; height:43px; float:left; position:relative;">
                    	<img src="" style="width:40px; height:40px;">
                    </div>
                    <div style="width:43px; height:43px; float:left; position:relative;">
                    	<img src="" style="width:40px; height:40px;">
                    </div>
                    <div style="width:43px; height:43px; float:left; position:relative;">
                    	<img src="" style="width:40px; height:40px;">
                    </div>
                    <div style="width:43px; height:43px; float:left; position:relative;">
                    	<img src="" style="width:40px; height:40px;">
                    </div>
                    <div style="width:43px; height:43px; float:left; position:relative;">
                    	<img src="" style="width:40px; height:40px;">
                    </div>
                    <div style="width:43px; height:43px; float:left; position:relative;">
                    	<img src="" style="width:40px; height:40px;">
                    </div>
                    <div style="width:43px; height:43px; float:left; position:relative;">
                    	<img src="" style="width:40px; height:40px;">
                    </div>
                    <div style="width:43px; height:43px; float:left; position:relative;">
                    	<img src="" style="width:40px; height:40px;">
                    </div> -->
                </div>
            </div>
            
            <div style="margin-top:20px;">
            	<a href="#"><img src="hyy/img/客户端.png"></a>
            </div>
            
        </div>
        
       
    </div>
    
     <!-- 弹出的div层 -->
        <div id="lastDiv"  style=" visibility:hidden;top:100px; left:368px; width:500px; height:245px; position:fixed; background-color:#FFEAE4;border-radius:5px;" >
        	<div style=" height:26px; background-color:#78FF6D; border-radius:5px 5px 0 0;">
            	<span style="width:278px; height:26px; float:left; position:relative;margin-top:5px; margin-left:5px;">添加到同城活动豆列</span>
                <span style="float:right; position:relative; margin-right:10px;margin-top:5px;"><a href="">X</a></span>
       		</div>
            <div style="width:500px; height:200px; background-color:#FFFFFF;">
                	<div style="">
                    	<input type="radio" name="sel" checked style=" margin-left:-280px;">已有豆列
                    	<select>
                        	<option>同城豆列</option>
                        </select>
                    </div>
                    <div><input type="radio" name="sel">新建豆列</div>
             </div>
        	
        </div>
</html>

<script src="hyy/js/jquery-2.1.1.js"></script>
<script src="hyy/js/bootstrap.min.js"></script>  
<script src="hyy/js/jquery.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=Lul5WnlMiXvUUCz77nOj3uk3"></script>
<script type="text/javascript">
	alert("进来了");
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,12);
	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();
	// 将地址解析结果显示在地图上,并调整地图视野
	myGeo.getPoint("${offActivity.position}", function(point){
		if (point) {
			map.centerAndZoom(point, 16);
			map.addOverlay(new BMap.Marker(point));
		}
	}, "${offActivity.city.city_desc}");
</script>
<script src="hyy/js/jquery-ui.js"></script>

