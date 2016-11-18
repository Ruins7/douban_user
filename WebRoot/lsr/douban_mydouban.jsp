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
    
    <title>我的曲奇</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <link href="lsr/css/douban_mydouban.css" rel="stylesheet" type="text/css">
  <link href="lsr/css/bootstrap.min.css" rel="stylesheet" type="text/css">
  <link href="lsr/css/bootstrap-theme.css" rel="stylesheet" type="text/css">
  <link href="lsr/css/jquery-ui.css" rel="stylesheet">
  <script src="lsr/js/jquery-2.1.1.js"></script>
  <script src="lsr/js/bootstrap.min.js"></script>
  <script src="lsr/js/jquery-ui.js"></script>
  <script src="lsr/js/douban_mydouban.js"></script>
<script>
$(function(){
	 
	 $('.invo').click(function(e){
		e.preventDefault();
	 });
	 
	 //取消关注某用户
	 $('.cancelfocus').click(function(e){
		 e.preventDefault();
		 var user_id = $(this).prev().val();
		 var url = '/douban_user/user/userinfo?method=cancelFocusOtherUser';
			$.ajax({
				type : 'post',
				url : url,
				dataType : 'json',
				data : {to_user:user_id},
				success : function(json, status, xhr) {
					if(json == true){
	                	   alert("取消关注成功！");
	                   }else{
	                	   alert("取消关注失败！");
	                   }
				},
				error : function(responseText, status, xhr) {
					//alert(responseText + "   " + status + "   " + xhr);
					alert("取消关注失败！");
				}
			});
	 });
	 
	 //关注某人
	 $('.focus').click(function(e){
		 e.preventDefault();
		 var user_id = $(this).prev().val();
		 var url = '/douban_user/user/userinfo?method=FocusOtherUser';
			$.ajax({
				type : 'post',
				url : url,
				dataType : 'json',
				data : {to_user:user_id},
				success : function(json, status, xhr) {
					if(json == true){
	                	   alert("关注成功！");
	                   }else{
	                	   alert("关注失败！");
	                   }
				},
				error : function(responseText, status, xhr) {
					//alert(responseText + "   " + status + "   " + xhr);
					alert("关注失败！");
				}
			});
	 });
});
</script>
  </head>
  
  <body>
 <jsp:include page="/lsr/douban_head.jsp"></jsp:include>
    <div id="left"><!--左边-->
    <!-- 我的曲奇head -->
    	  <div style="float: left;height: 80px;width: 650px;">
    	      <div style="height: 100px;float: left;margin-right: 10px;"><img src="<%=basePath%>${requestScope.current_user.imgs}" width="80px" height="80px"></div>
    	      <div style="font-size:24px;font-weight:900;padding-left:5px;float: left;padding-top: 5px;margin-right: 10px;">${requestScope.current_user.username}</div>
    	      <div style="font-size:13px;margin-top: 16px;">
    	            <input type="text" name="desc" class="whats_up" id="user_desc" value="${requestScope.current_user.user_desc }">
    	            <input type="hidden" id="uid" value="${requestScope.current_user.user_id }">
    	            <c:if test="${sessionScope.current_user.user_id == requestScope.current_user.user_id}">
    	            <a href="" id="modify">
    	                             修改
    	            </a>  
    	            </c:if> 
    	      </div>
    	      <div style="height: 12px; width: 500px;">
    	        <ul id="left_menu">
                	<li><a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${requestScope.current_user.user_id}">${requestScope.current_user.username}的主页</a></li>
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
          </div> <!-- 我的曲奇head -->
          <div class="my" style="font-size: 13px;margin-top: 50px;"><!-- 我喜欢 --> 
             <div style="font-size: 14px;color: #007722;margin-bottom: 10px;">
                 <img src="lsr/img/mylike.png" width="20px" height="20px">我喜欢· · · · · ·(<a href="<%=basePath%>user/userinfo?method=showAllUserLike&user_id=${requestScope.current_user.user_id}">全部 ${mylike_size}</a>)
             </div>
             <!-- 循环5个 -->
           <c:forEach var="like" items="${mylike}" begin="0" end="5" step="1">
		   <c:if test="${like.item_name=='书籍'}">
		   <div style="width: 650px;height: auto;float: left;">
                  <p style="float:left;color: #3377AA ;border-radius:5px;">[书籍]:  <a href="<%=basePath%>book/bookmanage?method=showOnebook&book_id=${like.book.book_id }">${like.book.book_name }</a> </p> 
                 <div style="float: right;color: #545652;margin-top: 5px;margin-bottom: 5px;margin-right: 30px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd"/></div>
            </div>  
	 	    <hr style="float: left;"> 
		   </c:if>
		   <c:if test="${like.item_name=='电影'}">
		   <div style="width: 650px;height: auto;float: left;">
                  <p style="float:left;color: #3377AA ;border-radius:5px;">[电影]: <a href="/douban_user/servlet/movieServlet?method=findOneMovie&movie_id=${like.movie.movie_id}">${like.movie.m_name }</a> </p> 
                 <div style="float: right;color: #545652;margin-top: 5px;margin-bottom: 5px;margin-right: 30px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd"/></div>
            </div>  
	 	    <hr style="float: left;"> 
		   </c:if>
		   <c:if test="${like.item_name=='东西'}">
		   <div style="width: 650px;height: auto;float: left;">
                  <p style="float:left;color: #3377AA ;border-radius:5px;">[东西]: <a href="<%=basePath%>things/thingsinfo?method=showOneThing&things_id=${like.thing.things_id}">${like.thing.things_name }</a> </p> 
                 <div style="float: right;color: #545652;margin-top: 5px;margin-bottom: 5px;margin-right: 30px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd"/></div>
            </div>  
	 	    <hr style="float: left;"> 
		   </c:if>
		   <c:if test="${like.item_name=='日志'}">
		   <div style="width: 650px;height: auto;float: left;">
               <p style="float:left;color: #3377AA;border-radius:5px;">[日志]:  <a href="<%=basePath%>user/userinfo?method=showOneDiary&user_id=${requestScope.current_user.user_id}&diary_id=${like.diary.diary_id}">${like.diary.title }</a> </p>
               <div style="float: right;color: #545652;margin-top: 5px;margin-bottom: 5px;margin-right: 30px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd"/></div> 
            </div>  
	 	    <hr style="float: left;"> 
		   </c:if>
		   <c:if test="${like.item_name=='相册'}">
		   <div style="width:650px;height: auto;float: left;">
                  <p style="float:left;color: #3377AA;border-radius:5px;">[相册]: <a href="<%=basePath%>user/userinfo?method=showOneAlbum&album_id=${like.album.album_id}">${like.album.album_name }</a> </p>                 
                 <div style="float: right;color: #545652;margin-top: 5px;margin-bottom: 5px;margin-right: 30px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd"/></div>
            </div>  
	 	    <hr style="float: left;"> 
		   </c:if>
		   <c:if test="${like.item_name=='线下活动'}">
		   <div style="width: 650px;height: auto;float: left;">
                  <p style="float:left;color: #3377AA;border-radius:5px;">[活动]:  <a href="<%=basePath%>hyy/service/ActivityServlet?method=searchOneOffActivity&activity_id=${like.activity.offactivity_id}">${like.activity.offactivity_title }</a> </p>
                  <div style="float: right;color: #545652;margin-top: 5px;margin-bottom: 5px;margin-right: 30px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd"/></div>        
            </div>  
	 	    <hr style="float: left;"> 
		   </c:if>
		   <%-- <c:if test="${like.item_name=='照片'}">
		   <div style="width: 650px;height: auto;float: left;">
                  <p style="float:left;color: #3377AA;border-radius:5px;">[照片]:  ${like.photo.photo_name } </p>
                  <div style="float: right;color: #545652;margin-top: 5px;margin-bottom: 5px;margin-right: 30px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd"/></div>
            </div>  
	 	    <hr style="float: left;"> 
		   </c:if> --%>
		   <c:if test="${like.item_name=='话题'}">
		   <div style="width: 650px;height: auto;float: left;">
                  <p style="float:left;color: #3377AA;border-radius:5px;">[话题]:  <a href="groupServlet?method=queryOnePost&&post_id=${like.topic.id}">${like.topic.post_title }</a> </p>
                 <div style="float: right;color: #545652;margin-top: 5px;margin-bottom: 5px;margin-right: 30px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd"/></div>  
            </div>  
	 	    <hr style="float: left;"> 
		   </c:if>
		   <c:if test="${like.item_name=='广播'}">
		   <div style="width: 650px;height: auto;float: left;">
                  <p style="float:left;color: #3377AA;border-radius:5px;">[广播]:<c:out value="${fn:substring(like.bro.content, 0, 10)}..." />   </p>
                 <div style="float: right;color: #545652;margin-top: 5px;margin-bottom: 5px;margin-right: 30px;"><fmt:formatDate value="${like.time }" pattern="yyyy-MM-dd"/></div>  
            </div>  
	 	    <hr style="float: left;"> 
		   </c:if>    
        </c:forEach> 
		  </div><!-- 我喜欢 -->
          <div class="my"><!-- 我的日记 -->
          <c:set var="page" value="${requestScope.mydiary }"></c:set>
             <div style="font-size: 14px;color: #007722;margin-bottom: 10px;">
                                          我的日记· · · · · ·(<a href="<%=basePath%>user/userinfo?method=showAllUserDiarys&user_id=${requestScope.current_user.user_id}&typepage=ul">全部 ${page.totalRecord }</a>)
             </div>
             <!-- 循环5个 -->
             <c:forEach var="diary" items="${page.data }" begin="0" end="4" step="1">
             <div style="font-size: 13px;color: #3377AA;margin-top: 5px;margin-bottom: 5px;float: left;"><a href="<%=basePath%>user/userinfo?method=showOneDiary&user_id=${requestScope.current_user.user_id}&diary_id=${diary.diary_id}">${diary.title }</a></div>
             <div style="float: right;color: #545652;margin-top: 5px;margin-bottom: 5px;margin-right: 30px;"><fmt:formatDate value="${diary.time }" pattern="yyyy-MM-dd"/></div>
             <hr style="margin-top: 1px;margin-bottom: 1px;width: 650px;float: left;">
             </c:forEach>
          </div><!-- 我的日记 -->
          
          <div class="my"><!-- 我的相册 -->
           <c:set var="page" value="${requestScope.myalbum }"></c:set>
             <div style="font-size: 14px;color: #007722;margin-bottom: 10px;">
                                          我的相册· · · · · ·(<a href="<%=basePath%>user/userinfo?method=showUserAllAlbums&user_id=${requestScope.current_user.user_id}&typepage=ul">全部 ${page.totalRecord }</a>)
             </div>
             <!-- 循环4个 -->
             <ul id="myalbum">
             <c:forEach var="album" items="${page.data }" begin="0" end="3" step="1">
             <li>
                <div style="font-size: 13px;color: #3377AA;margin-top: 5px;margin-bottom: 5px;float: left;text-align: center;">
                   <a href="<%=basePath%>user/userinfo?method=showOneAlbum&album_id=${album.album_id}"><img src="<%=basePath%>${album.imgs}" width="120px" height="120xp"><br>
                  <span> ${album.album_name}</span><br>
                  </a>
                  <span style="color: #545652;"><fmt:formatDate value="${album.time }" pattern="yyyy-MM-dd"/></span>
                </div>    
             </li>
             </c:forEach>
             </ul>
          </div><!-- 我的相册 -->
          
          <div class="my"><!--  我看 -->
             <div style="font-size: 14px;color: #007722;margin-bottom: 10px;">
                                          我看· · · · · ·(<a href="<%=basePath%>servlet/movieServlet?method=allfindMovies">全部 ${wantsawmovies_size+sawmovies_size}</a>·想看 ${wantsawmovies_size}·已看 ${sawmovies_size })
             </div>
             <div style="width: 650px;height: 150px;float: left;margin-bottom: 20px">
             <span style="float: left;margin-top: 60px;font-size: 13px;margin-right: 10px;color: #ACACAC;">想看</span>
             <c:forEach var="wantsee" items="${wantsawmovies }" begin="0" end="4" step="1">
                 <a href="/douban_user/servlet/movieServlet?method=findOneMovie&movie_id=${wantsee.movie.movie_id}"><img alt="" src="<%=basePath%>${wantsee.movie.imgs}" width="100px" height="150px;" style="float: left;margin-right: 20px;"></a>
             </c:forEach>
             </div>
             <div style="width: 650px;height: 150px;float: left;margin-bottom: 20px">
             <span style="float: left;margin-top: 60px;font-size: 13px;margin-right: 10px;color: #ACACAC;">已看</span>
             <c:forEach var="sawmovies" items="${sawmovies }" begin="0" end="4" step="1">
                 <a href="/douban_user/servlet/movieServlet?method=findOneMovie&movie_id=${sawmovies.movie.movie_id}"><img alt="" src="<%=basePath%>${sawmovies.movie.imgs}" width="100px" height="150px;" style="float: left;margin-right: 20px;"></a>
             </c:forEach>
             </div>
          </div><!--  我看 -->
          
          <div class="my"><!-- 我听 -->  
             <div style="font-size: 14px;color: #007722;margin-bottom: 10px;">
                                          我听· · · · · ·(<a href="#">全部 5</a>)
             </div>
             <!-- 循环5个 -->
             <div style="font-size: 13px;color: #3377AA;margin-top: 5px;margin-bottom: 5px;float: left;"><a href="#" class="invo">MadBall</a></div>
             <div style="float: right;color: #3377AA;margin-top: 5px;margin-bottom: 5px;margin-right: 30px;">2014-01-01</div>
             <hr style="margin-top: 1px;margin-bottom: 1px;width: 650px;float: left;">
             <div style="font-size: 13px;color: #3377AA;margin-top: 5px;margin-bottom: 5px;float: left;"><a href="#" class="invo">长春硬核占线</a></div>
             <div style="float: right;color: #3377AA;margin-top: 5px;margin-bottom: 5px;margin-right: 30px;">2014-01-01</div>
             <hr style="margin-top: 1px;margin-bottom: 1px;width: 650px;float: left;">
             <div style="font-size: 13px;color: #3377AA;margin-top: 5px;margin-bottom: 5px;float: left;"><a href="#" class="invo">NYHC</a></div>
             <div style="float: right;color: #3377AA;margin-top: 5px;margin-bottom: 5px;margin-right: 30px;">2014-01-01</div>
             <hr style="margin-top: 1px;margin-bottom: 1px;width: 650px;float: left;">
             <div style="font-size: 13px;color: #3377AA;margin-top: 5px;margin-bottom: 5px;float: left;"><a href="#" class="invo">Stand Up! New York</a></div>
             <div style="float: right;color: #3377AA;margin-top: 5px;margin-bottom: 5px;margin-right: 30px;">2014-01-01</div>
             <hr style="margin-top: 1px;margin-bottom: 1px;width: 650px;float: left;">
             <div style="font-size: 13px;color: #3377AA;margin-top: 5px;margin-bottom: 5px;float: left;"><a href="#" class="invo">Hourglass</a></div>
             <div style="float: right;color: #3377AA;margin-top: 5px;margin-bottom: 5px;margin-right: 30px;">2014-01-01</div>
             <hr style="margin-top: 1px;margin-bottom: 1px;width: 650px;float: left;">
          
          </div><!-- 我听 -->
          
          <div class="my"><!-- 我读-->
             <div style="font-size: 14px;color: #007722;margin-bottom: 10px;">
                                          我读· · · · · ·(<a href="<%=basePath%>book/bookinfo?method=searchBookByTimeDecs">全部  ${readedbook_size+readingbook_size+wantreadbook_size}</a>·想读 ${wantreadbook_size}·已读 ${readedbook_size }·在读 ${readingbook_size })
             </div>
             
             <div style="width: 650px;height: 150px;float: left;margin-bottom: 20px">
             <span style="float: left;margin-top: 60px;font-size: 13px;margin-right: 10px;color: #ACACAC;">想读</span>
             <c:forEach var="wantreadbook" items="${wantreadbook }" begin="0" end="4" step="1">
                 <a href="<%=basePath%>book/bookmanage?method=showOnebook&book_id=${wantreadbook.book.book_id }"><img alt="" src="<%=basePath%>${wantreadbook.book.imgs}" width="100px" height="150px;" style="float: left;margin-right: 20px;"></a>
             </c:forEach>
             </div>
             
             <div style="width: 650px;height: 150px;float: left;margin-bottom: 20px">
             <span style="float: left;margin-top: 60px;font-size: 13px;margin-right: 10px;color: #ACACAC;">在读</span>
             <c:forEach var="readingbook" items="${readingbook }" begin="0" end="4" step="1">
                 <a href="<%=basePath%>book/bookmanage?method=showOnebook&book_id=${readingbook.book.book_id }"><img alt="" src="<%=basePath%>${readingbook.book.imgs}" width="100px" height="150px;" style="float: left;margin-right: 20px;"></a>
             </c:forEach>
             </div>
             
             <div style="width: 650px;height: 150px;float: left;margin-bottom: 20px">
             <span style="float: left;margin-top: 60px;font-size: 13px;margin-right: 10px;color: #ACACAC;">已读</span>
            <c:forEach var="readedbook" items="${readedbook }" begin="0" end="4" step="1">
                 <a href="<%=basePath%>book/bookmanage?method=showOnebook&book_id=${readedbook.book.book_id }"><img alt="" src="<%=basePath%>${readedbook.book.imgs}" width="100px" height="150px;" style="float: left;margin-right: 20px;"></a>
             </c:forEach>
             </div>
           </div><!-- 我读-->
          
          <div class="my"><!-- 我的留言板-->  
          <c:set var="page" value="${requestScope.myleavemessage }"></c:set>    
             <div style="font-size: 14px;color: #007722;margin-bottom: 10px;">
                                          留言板· · · · · ·(全部  ${page.totalRecord })
             </div>
             <div id="mes">
                   <input type="hidden" id="uid" name="to_user" value="${requestScope.current_user.user_id }">
                   <textarea  id="leavemessage" type="text" name="content" class="form-control" placeholder="想对TA说些什么..." style="width:620px; height:100px; margin-bottom: 5px;resize:none;"></textarea>
                   <input type="button" id="sendleavemessage" value="留言">
             </div>
             <!-- 生成刚才留言的信息（待做） -->
             <div style="float: right;color: #545652;margin-top:15px;margin-bottom: 5px;margin-right: 30px;"><fmt:formatDate value="${message.time}" pattern="yyyy-MM-dd HH:mm"/></div>
             <hr style="margin-top: 1px;margin-bottom: 1px;width: 650px;float: left;">
             <c:forEach var="message" items="${page.data }">
             <div id="mess${message.message_id}" style="width:495px;font-size: 13px;color: #3377AA;margin-top: 20px;margin-bottom: 5px;float: left;">
                <a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${message.f_user.user_id}" style="float: left;">
                   <img src="<%=basePath%>${message.f_user.imgs }" width="50px" height="50px" style="border-radius:25px;">
                    ${message.f_user.username } 
                </a>
                <div style="color: #545652;padding-left: 5px;margin-top:15px;float:left;width:370px;height:auto;word-wrap:break-word;">说：  ${message.content }</div>
             </div>
             <div id="mess${message.message_id}l" style="float: right;color: #545652;margin-top: 35px;margin-bottom: 5px;margin-right: 10px;">
                <c:if test="${sessionScope.current_user.user_id == requestScope.current_user.user_id}">
                  <a href="" id="deletemess" style="font-size: 12px;">删除</a><input type="hidden" id="messid" value="${message.message_id}">
                </c:if>
                <fmt:formatDate value="${message.time}" pattern="yyyy-MM-dd HH:mm"/></div>
             <hr id="mess${message.message_id}h" style="margin-top: 1px;margin-bottom: 1px;width: 650px;float: left;">
             </c:forEach>
          </div><!-- 我的留言板-->
          
          <div class="my"><!-- 我的东西-->  
             <c:set var="page" value="${requestScope.mythings }"></c:set>   
             <div style="font-size: 14px;color: #007722;margin-bottom: 10px;">
                                          我的东西· · · · · ·(<a href="<%=basePath%>things/thingsinfo?method=searchThingsByPage&typepage=ul&user_id=${sessionScope.current_user.user_id}">全部  ${page.totalRecord }</a>)
             </div>
              <!-- 循环5个 -->
             <ul id="mythings">
             <c:forEach var="things" items="${page.data }" begin="0" end="4" step="1">
             <li>
                <div style="font-size: 13px;color: #3377AA;margin-top: 5px;margin-bottom: 5px;float: left;text-align: center;">
                  <a href="<%=basePath%>things/thingsinfo?method=showOneThing&things_id=${things.things_id}">
                     <img src="<%=basePath%>${things.imgs}" width="100px" height="100xp"><br>
                   <br>
                   </a>
                </div>    
             </li>
             </c:forEach>
             </ul>
             <hr style="margin-top: 1px;margin-bottom: 1px;width: 650px;float: left;">
          </div><!-- 我的东西-->
    </div><!--左边-->
    <div id="righttt"><!-- 右边 -->
        <div id="myself"><!-- myself -->
           <div id="myself_up">
              <img src="<%=basePath%>${requestScope.current_user.imgs}" width="auto" height="auto" style="float: left;margin-right: 10px;max-width: 160px;max-height: 160px;">
              <p>常居地:<a href="">${requestScope.current_user.city.city_desc }</a></p>
              <p>${requestScope.current_user.join_in_time }加入</p>
           </div>
           <hr style="float: left;width: 260px;">      
            <c:if test="${requestScope.current_user.user_id != sessionScope.current_user.user_id }"> 
           <p style="margin-left: 10px;">
           <c:if test="${guanzhu==false}">
                <input type="hidden" value="${requestScope.current_user.user_id }">
                <img alt="" src="<%=basePath%>/lsr/img/focushim.png" class="focus" style="cursor: pointer;">
           </c:if>
           <c:if test="${guanzhu==true}">
           <input type="hidden" value="${requestScope.current_user.user_id }">
                <img alt="" src="<%=basePath%>/lsr/img/unfocushim.png" class="cancelfocus" style="cursor: pointer;">
           </c:if>
           </p>
        </c:if>  
           <div id="myself_down">
                  <textarea id="simple_desc" rows="4" cols="39" style="resize:none;display:none;"></textarea>
               <pre id="show_simple_desc">${requestScope.current_user.simple_intro }</pre>
               <p id="modify_simple_desc">
               <c:if test="${sessionScope.current_user.user_id == requestScope.current_user.user_id}">
               <a href="" >编辑</a>
               </c:if>
               </p>
           </div>
        </div><!-- myself -->
        <!-- 我关注的好友 -->
        <div style="margin-top:20px;padding-top: 20px;font-size: 12px;">
		           <p style=" color:#007722; padding-left:5px; font-size:14px">我的关注······(成员：<a class="item" href="<%=basePath%>user/userinfo?method=MyFocus&user_id=${requestScope.current_user.user_id}"> ${requestScope.myfocusnum }  </a>)</p>
		    <div id="myFocus">
		       <ul><!-- 循环5个 -->	         
		       <c:forEach items="${requestScope.myfocus }" var="myfriend" begin="0" end="4" step="1">
		            <li>
		              <a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${myfriend.user_id }">
		                <img src=" ${myfriend.imgs }" width="40px;" height="40px;" style="border-radius:20px;"><br>
		                <p>${myfriend.username }</p>
		              </a>
		            </li> 
		       </c:forEach>    
               </ul>
		    </div>
		    <div style="float: left;margin-bottom: 15px;margin-top: 10px;">→ 我被  <a class="item invo" href="#">${requestScope.whofocusme }</a>人关注</div>
		  </div><!-- 我关注的好友 -->
		  <hr style="clear: both;">
		  
		  <!-- 我的活动 -->
		  <div id="myactivity">
		      <div style="font-size: 14px;color: #007722;">
                                          我的活动······(全部<a href="#" class="samecity""> ${attendact_size } </a>)
                 <c:forEach var="act" items="${attendact}" begin="0" end="4" step="1">
                 <div style="width: 280px;min-height: 30px;height: auto;">
                 <br>
                      <span style="font-size: 14px;color: #3377AA;">活动名称:<a href="<%=basePath%>hyy/service/ActivityServlet?method=searchOneOffActivity&activity_id=${act.offactivity_id}">${act.offactivity_title }</a></span><br>
                      <span style="font-size: 12px;color: #545652;">时间：<fmt:formatDate value="${act.start_time}" pattern="yyyy-MM-dd HH:mm:ss"/></span><br>
                      <span style="font-size: 12px;color: #545652;">地点：${act.city.city_desc}-${act.position }</span><br>
                      <span style="font-size: 12px;color: #545652;">100人参加    100人感兴趣</span>
                 </div>
                 </c:forEach>        
             </div>
		  </div><!-- 我的活动 -->
		  
		  <hr>
		  <!-- 我的豆列 -->
		  <div id="mydoulist">
		      <div style="font-size: 14px;color: #007722;">
                                           我的豆列···(全部 <a href="<%=basePath%>doulist/doulistinfo?method=showMyDouList&user_id=${requestScope.current_user.user_id}">${my_created_doulie_size}</a>)
                 <div style="width: 280px;min-height: 30px;height:auto;font-size: 13px;">
                      <c:forEach var="doulist" items="${my_created_doulie }"  begin="0" end="4" step="1">  
                      <br>
                      <span style="color: #545652;">[${doulist.content_type_table.type_name }]</span><a href="<%=basePath%>doulist/doulistinfo?method=showOneDouList&doulie_id=${doulist.doulist_id}&doulie_type=${doulist.content_type_table.content_type_id}"> ${doulist.list_name }</a><br>    
                      </c:forEach> 
                 </div>
             </div>
		  </div><!-- 我的豆列 -->
		  
		  <hr>
		  <!-- 我的小组 -->
		  <div id="mygroup">
		      <div style="font-size: 14px;color: #007722;">
                                           我常去的小组······(全部<a href="<%=basePath %>groupServlet?method=loginGroup"> ${oftengroup_size } </a>)
                 <div style="width: 280px;min-height: 30px;height: auto;">
                      <div id="myFocus2">
		              <ul><!-- 循环4个 -->
		              <c:forEach items="${oftengroup}" var="mygroup" begin="0" end="3" step="1">
		                  <li>
		                    <a href="groupServlet?method=queryOneGroup&&group_id=${mygroup.group_id}">
		                      <img src=" ${mygroup.group.imgs }" width="65px;" height="65px;" style="border-radius:20px;"><br>
		                      <p style="font-size: 12px;">${mygroup.group.group_name }</p>
		                    </a>
		                  </li>     
		              </c:forEach>    
                      </ul>
		              </div>
                 </div>
             </div>
		  </div><!-- 我的小组 -->
    </div><!-- 右边 -->   
      <jsp:include page="/lsr/douban_foot.jsp"></jsp:include>   	 
  </body>

</html>

