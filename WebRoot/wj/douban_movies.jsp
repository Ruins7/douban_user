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
    <script src="wj/js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="wj/js/jquery.cityselect.js"></script>
    <script type="text/javascript" src="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js"></script>
   
   <script src="wj/js/bootstrap.min.js"></script>
   <script src="wj/js/jquery.js"></script>
   <link href="wj/js/jquery-ui.css" rel="stylesheet" >
   <link href="wj/js/bootstrap.min.css" rel="stylesheet" type="text/css">
   <link href="wj/js/bootstrap-theme.css" rel="stylesheet" type="text/css">
   <script src="wj/js/jquery-ui.js"></script>
   <script src="wj/js/Myjs.js"></script>
    
   
   

  <link href="wj/css/douban_movies.css" rel="stylesheet" type="text/css">
    <title>电影</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <jsp:include page="/wj/douban_moviehead.jsp"></jsp:include>
    
    <div id="content"><!--曲奇的整体内容-->
      <div id="left" style="margin-right: 20px;"><!--左边内容-->
        <br>
        <p style="font-size: 22px;color: #2F92CF;font-weight: 900;">正在热映</p>
        <hr style="width: 620px">
        <table>
        	<tr style="">
        	<c:forEach var="movie" items="${requestScope.movies}" varStatus="i" begin="0" end="3" step="1">
            	<td style="width:140px; height:210px;text-align: center;">
                	<div><a href="/douban_user/servlet/movieServlet?method=findOneMovie&movie_id=${movie.movie_id}"><img src=${movie.imgs }></a></div>
                    <div><a href="/douban_user/servlet/movieServlet?method=findOneMovie&movie_id=${movie.movie_id}" style="font-size:12px; color:#000000">${movie.m_name }</a></div>
                    <div style="color: red;">得分：${movie.avgscore }</div>
                </td>
              </c:forEach>
            </tr>	
        </table> <!--////////////////////正在热映/////////////////////////////////-->
        <br>
        <!--///插个图片//////-->
        <div >
        	<img src="wj/img/movies2.png">
        </div>
        <br>
        <!--////////////////////////////////////查询条件///////////////////////////////////////-->
        <br>
        <p style="font-size: 22px;color: #2F92CF;font-weight: 900;">选电影</p>
        <hr><br>
        <table >
            <tr>
                <td style="width:80px;"><a href="javascript:void(0);" style="text-decoration:none; font-size:12px" id="hot1">热门  </a></td>
                <td style="width:80px;"><a href="javascript:void(0);" style="text-decoration:none;font-size:12px" id="newMovies">最新 </a></td>
                <td style="width:80px;"><a href="javascript:void(0);"  style="text-decoration:none;font-size:12px" id="classics"> 经典 </a></td>
                <td style="width:80px;"><a href="javascript:void(0);"  style="text-decoration:none;font-size:12px" id="movement">动作 </a></td>
                <td style="width:80px;"><a href="javascript:void(0);"  style="text-decoration:none;font-size:12px" id="highMark">  曲奇高分 </a></td>
                <td style="width:80px;"><a href="javascript:void(0);"  style="text-decoration:none;font-size:12px" id="china"> 华语</a></td>
                <td style="width:80px;"><a href="javascript:void(0);"  style="text-decoration:none;font-size:12px" id="america"> 欧美 </a></td>
                <td style="width:80px;"><a href="javascript:void(0);" style="text-decoration:none;font-size:12px" id="korea"> 韩国</a></td>
            </tr>
            <tr>
                <td style="width:80px;"><a href="javascript:void(0);"  style="text-decoration:none" id="japan">日本  </a></td>
                <td style="width:80px;"><a href="javascript:void(0);"  style="text-decoration:none" id="comedy"> 喜剧 </a></td>
                <td style="width:80px;"><a href="javascript:void(0);"  style="text-decoration:none" id="love">  爱情</a></td>
                <td style="width:80px;"><a href="javascript:void(0);"  style="text-decoration:none" id="scienceFiction">  科幻 </a></td>
                <td style="width:80px;"><a href="javascript:void(0);"  style="text-decoration:none" id="suspense"> 悬疑</a></td>
                <td style="width:80px;"><a href="javascript:void(0);"  style="text-decoration:none" id="terror"> 恐怖</a></td>
                <td style="width:80px;"><a href="javascript:void(0);"  style="text-decoration:none" id="art"> 文艺 </a></td>
            </tr>
        </table>
        <br><hr>
            <div style="float:left; position:relative">
            	<input type="radio" name="movie_type" id="hot">按热度排序
                <input type="radio" name="movie_type" id="date" >按时间排序
                <input type="radio" name="movie_type" id="score">按得分排序
            </div>    
        <br>
     <!--////////////////////////////////////查询结果///////////////////////////////////////-->
        <div style=" float:left; width:623px;" id="sort">
            <c:forEach var="movie" items="${requestScope.movies}" varStatus="i" begin="0" end="7" step="1">
                   <div style='width:140px; height:190px;float:left;margin-top:10px;position:relative;'>
                   		<div><a href="/douban_user/servlet/movieServlet?method=findOneMovie&movie_id=${movie.movie_id}" style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src=${movie.imgs }></a></div>
                   		<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>${movie.m_name }</a></div>
                   </div>
            </c:forEach>
        </div>
        <!--////////////////////////////////////查询结果///////////////////////////////////////-->
        <br>
        <p style="font-size: 22px;color: #2F92CF;font-weight: 900;">热门推荐</p>
        <hr>
        <!----//////////////////轮播//////---->
        <div style="height:273px; width:623px">
           <div id="carousel-example-generic" class="carousel slide">
           <!-- Indicators -->
           <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
          </ol>
  <!-- Wrapper for slides -->
  <div class="carousel-inner" >
      <div class="item active">
     	 <div style="float:left; position:relative;"><img src="wj/img/movies3.png" alt="First slide"></div>
         <div style="float:left; position:relative; padding-top:70px; padding-left:20px; padding-top:50px;" align="center">台词是用来励志的<br>
         		来自 Ruins7 的相册:<br>所以才算没白看</div>
      </div>
      <div class="item">
     	 <div style="float:left; position:relative; width:353px"><img src="wj/img/movies4.png" alt="First slide"></div>
         <div style="float:left; position:relative; width:270px; padding-top:50px;" align="center">“小恶魔” 彼特·丁拉基的幕后生活<br>
         		来自 太龌龊欧蓝德 的日记:<br>莎士比亚说：我宁愿重用一个活跃的侏儒，也不要一个贪睡的巨人。
                这句反讽意味浓重的名言，现在看来，竟成了演员彼特·丁拉基的最好注脚。</div>
      </div>
      <div class="item ">
      	 <div style="float:left; position:relative;width:353px"><img src="wj/img/movies5.png" alt="First slide"></div>
         <div style="float:left; position:relative;width:270px; padding-top:50px;" align="center">看《银河护卫队》前你该知道的事<br>
         		来自Jin Unrated的日记:<br>电影里的主要人物就算对漫威迷来说也不算熟悉，但如果你想更好地欣赏这部片子的牛逼之处，
                可以事先做做功课。</div>
      </div>
    </div>  
  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
  </a>
</div>
</div>
 <!----//////////////////轮播结束//////---->        
      
  
 
   <p style="font-size: 22px;color: #2F92CF;font-weight: 900;">最受欢迎的影评</p>
   <hr>
   <c:forEach var="comment" items="${comment}" begin="1" end="10" step="1">
   <div style="margin-bottom: 20px;width: 620px;height: auto;float: left;">
    	<div  style="text-align: center;float: left;margin-right: 20px;width: 100px;height: 130px;"><img src="<%=basePath%>${comment.movie.imgs}"></div>
        <div class="vright" style="float: left;width: 500px;">
        <span style="font-size: 12px;color: #545652;"><a href="javascript:void(0);" style="text-decoration: none;">${comment.user.username}</a></span> 评论 <a href="javascript:void(0);"  style="text-decoration: none;">《${comment.movie.m_name}》</a><br>
        <pre style="width: 500px;height: 125px;">
           ${comment.c_masg}
           </pre>		
        </div>  
    </div>  
   </c:forEach>
</div><!--左边内容-->
    <!-----///////////////////////////////////////////////////////////////左边内容/////////////////////---------------------> 
       
        <div id="right"><!--右边内容-->
        <!--搜索内容-->
        	<div class="seach_add" style="width: 300px">
                 <form>
                    <div style="float:left; position:relative; padding-top:16px; padding-left:20px;">                	  
                   	  <select style="width:80px; height:26px;" id="city" name="location">                 	
                        <option value="天津" selected="selected">天津</option>
                        <option value="北京">北京</option>
                        <option value="上海">上海</option> 
                        <option value="南京">南京</option>
                        <option value="重庆">重庆</option>
                        <option value="武汉">武汉</option>
                        <option value="大连">大连</option>
                        <option value="香港">香港</option>
                        <option value="澳门">澳门</option>
                      </select>
                    </div>
                    <div style="float:left; position:relative;padding-left:10px; padding-top:16px;">
                    	<!-- <input style="width:150px; height:26px; " placeholder="搜索本地影院"> -->   
                    	<select style="width:150px; height:26px;" id="cinema">
                    	   
                    	</select>                     
                    </div>
<!--                     <div style="float:left; position:relative;padding-top:15px;">
                        	<a href="#"><img src="wj/img/search2.png"></a>
                    </div> -->
                 </form>
            </div>
        <!--搜索内容-->
        	<div class="right_img"><img src="wj/img/douban6.png"><br></div>
            
            
            <div class="remen" style="height: auto;">
            	<p style="font-size: 22px;color: #2F92CF;font-weight: 900;">本周口碑榜</p>
                <hr style=" padding:3px; margin:8px;">
                <ul id="Rinking">
                   <li style=" list-style:none;"><a href="#" style="font-size:14px;">银河护卫队</a></li><hr style=" padding:3px; margin:8px;">
                   <li style="list-style:none;"><a href="#"style="font-size:14px;">亲爱的</a></li><hr style=" padding:3px; margin:8px;">
                   <li style="list-style:none;"><a href="#"style="font-size:14px;">神的病历簿2</a></li><hr style="padding:3px; margin:8px;">
                   <li style="list-style:none;"><a href="#"style="font-size:14px;">麦兜和我的妈妈</a></li><hr style="padding:3px; margin:8px;">
                   <li style="list-style:none;"><a href="#"style="font-size:14px;">看见台湾</a></li><hr style="padding:3px; margin:8px;">
                   <li style="list-style:none;"><a href="#"style="font-size:14px;">海雾</a></li><hr style="padding:3px; margin:8px;">
                   <li style="list-style:none;"><a href="#"style="font-size:14px;">送乡人</a></li><hr style="padding:3px; margin:8px;">
                   <li style="list-style:none;"><a href="#"style="font-size:14px;">韩公主</a></li><hr style="padding:3px; margin:8px;">
                   <li style="list-style:none;"><a href="#"style="font-size:14px;">龙虎少年队2</a></li><hr style="padding:3px; margin:8px;">
                   <li style="list-style:none;"><a href="#"style="font-size:14px;">龙虎少年3</a></li><hr style="padding:3px; margin:8px;">
                </ul>
            </div>
            <div>
            	<img src="wj/img/yuedu4.png">
            </div>
        </div><!--右边内容-->
        </div><!--曲奇的整体内容-->
 
 
  </body>
</html>

