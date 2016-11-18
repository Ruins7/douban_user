<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>豆列</title>
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
<script src="lsr/js/jquery-2.1.1.js"></script>
<script src="lsr/js/bootstrap.min.js"></script>
<script src="lsr/js/douban_loginAfter.js"></script>
<script src="lsr/js/jquery-ui.js"></script>
<script src="lsr/js/douban_myalbum.js"></script>
<script>
$(function(){
	 
	
});
</script>

</head>
<body>
  <jsp:include page="/lsr/douban_head.jsp"></jsp:include>
   
<div id="left">
  <div id="myfocuspeople">
      <img src="<%=basePath%>${doulie_user.imgs}" width="80px" height="80px">
              ${doulie_user.username} 的豆列 [${thisdoulie.content_type_table.type_name}] ${thisdoulie.list_name }<br>
              
              <div style="height: 12px; width: 500px;">
    	        <ul id="left_menu">
                	<li><a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${requestScope.current_user.user_id}">${current_user.username}的主页</a></li>
                    <li><a href="<%=basePath%>user/userinfo?method=searchMyBroByPage&user_id=${requestScope.current_user.user_id}&typepage=ul">广播</a></li>
                    <li><a href="<%=basePath%>user/userinfo?method=showUserAllAlbums&user_id=${requestScope.current_user.user_id}&typepage=ul">相册</a></li>
                    <li><a href="<%=basePath%>user/userinfo?method=showAllUserDiarys&user_id=${requestScope.current_user.user_id}&typepage=ul">日记</a></li>
                    <li><a href="<%=basePath%>user/userinfo?method=showAllUserLike&user_id=${requestScope.current_user.user_id}">喜欢</a></li>
                    <li><a href="<%=basePath%>doulist/doulistinfo?method=showMyDouList&user_id=${requestScope.current_user.user_id}">豆列</a></li>
                    <c:if test="${sessionScope.current_user.user_id == requestScope.current_user.user_id}">
                    <li><a href="<%=basePath%>user/sandmailnotice?method=showPersonalInfo&user_id=${requestScope.current_user.user_id}">设置</a></li>
                    </c:if>
                 </ul>
              </div>    
  </div><!-- myfocuspeople -->
  
  
  <p style="font-size: 12px;color: #999999;margin-top: 50px;">创建于：<fmt:formatDate value="${thisdoulie.time}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
  <hr style="width: 650px;float: left;">
  <div id="friends">
  <c:if test="${d_type=='1'}"><!-- 线下活动 -->
  <c:forEach var="d_content" items="${requestScope.doulie_content }">
		   <div style="width: 600px;height: auto;background-color: #F5F5F5;padding: 20px;">
                  <p style="font-size:16px;color: #999999;border-radius:5px;text-indent: 2em;"><a href="<%=basePath%>hyy/service/ActivityServlet?method=searchOneOffActivity&activity_id=${d_content.offactivity_id}">${d_content.offactivity_title }</a> </p>
                  <div style="font-size:12px;color:#3377AA;width: 570px;height: auto;background-color: #fff;border-radius:10px;padding-left: 20px;">
                  <p style="margin-top: 20px;">活动日期:<fmt:formatDate value="${d_content.start_time }" pattern="yyyy-MM-dd HH:mm"/> 至 <fmt:formatDate value="${d_content.end_time }" pattern="yyyy-MM-dd HH:mm"/></p>
                  <p>活动说明: ${d_content.offactivity_desc}  </p>
                  <p>活动发起人: ${d_content.user.username}  </p>
                  </div>
                  <img src="<%=basePath%>${d_content.imgs}"  style="margin-left: 20px;">
            </div>  
	 	    <hr> 
 </c:forEach> 
  </c:if>
  <c:if test="${d_type=='2'}"><!-- 书籍 -->
  <c:forEach var="d_content" items="${requestScope.doulie_content }">
		   <div style="width: 630px;height: auto;background-color: #F5F5F5;padding: 20px;">
                  <p style="font-size:16px;color: #999999;border-radius:5px;text-indent: 2em;"><a href="<%=basePath%>book/bookmanage?method=showOnebook&book_id=${d_content.book_id }">${d_content.book_name }</a> </p>
                  <div style="font-size:12px;color:#3377AA;width: 570px;height: auto;background-color: #fff;border-radius:10px;padding-left: 20px;">
                  <p style="margin-top: 20px;">作者：${d_content.b_author.author_name }</p>
                  <p>出版社: ${d_content.b_publisher.publisher_name}  </p>                 
                  <p>出版日期: ${d_content.publish_date}  </p>
                  <p>页数: ${d_content.page_num}  </p>
                  <p>梗概: ${d_content.simple_desc}  </p>
                  <p>类型: ${d_content.b_type.type_name}  </p>
                  </div>
                  <img src="<%=basePath%>${d_content.imgs}"  style="margin-left: 20px;">                                     
            </div>  
	 	    <hr> 
 </c:forEach> 
  </c:if>
  <c:if test="${d_type=='3'}"><!-- 电影 -->
  <c:forEach var="d_content" items="${requestScope.doulie_content }">
		   <div style="width: 630px;height: auto;background-color: #F5F5F5;padding: 20px;">
                  <p style="font-size:16px;color:#999999;text-indent: 2em;"><a href="/douban_user/servlet/movieServlet?method=findOneMovie&movie_id=${d_content.movie_id}">${d_content.m_name }</a> </p>
                  <div style="font-size:12px;color:#3377AA;width: 570px;height: auto;background-color: #fff;border-radius:10px;padding-left: 20px;">
                  <p style="margin-top: 20px;">导演：${d_content.m_d.d_name }</p>
                  <p>上映日期: <fmt:formatDate value="${d_content.m_screentime }" pattern="yyyy-MM-dd"/></p>                 
                  <p>主演: ${d_content.m_actors}  </p>                  
                  <p>语言: ${d_content.m_language}  </p>
                  <p>类型: ${d_content.m_type.type_name}  </p>
                  </div>
                  <img src="<%=basePath%>${d_content.imgs}" style="margin-left: 20px;">                                     
            </div>  
	 	    <hr> 
 </c:forEach> 
  </c:if>
  <c:if test="${d_type=='4'}"><!-- 东西-->
  <c:forEach var="d_content" items="${requestScope.doulie_content }">
		   <div style="width: 630px;height: auto;background-color: #F5F5F5;padding: 20px;">
                  <p style="font-size:16px;color:#999999;text-indent: 2em;"><a href="<%=basePath%>things/thingsinfo?method=showOneThing&things_id=${d_content.things_id}">${d_content.things_name }</a> </p>
                  <div style="font-size:12px;color:#3377AA;width: 570px;height: auto;background-color: #fff;border-radius:10px;padding-left: 20px;">
                  <p style="margin-top: 20px;">推荐人：${d_content.f_user.username }</p>
                  <p>日期: <fmt:formatDate value="${d_content.time }" pattern="yyyy-MM-dd"/></p>                  
                  <p>类型: ${d_content.type_table.type_name}  </p>
                  <p>简述: ${d_content.simple_desc}  </p>     
                  </div>
                  <img src="<%=basePath%>${d_content.imgs}" style="margin-left: 20px;">                                   
            </div>  
	 	    <hr> 
 </c:forEach> 
  </c:if>
  
		   
  </div><!-- friends -->
  
</div><!-- left -->
 
 
   <div id="right"><!--右边-->
    
   </div><!--右边-->
  
 <jsp:include page="/lsr/douban_foot.jsp"></jsp:include>
</body>

</html>

