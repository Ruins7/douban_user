<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
	
  </head>
  
  <body style="width: 100%;height: auto;">
    <jsp:include page="/book/douban_bookhead.jsp"></jsp:include>
    <div style="width: 1000px;height: auto;margin-top: 30px;float: left;margin-left: 200px;"><!--曲奇的整体内容-->
      <div style="width: 560px;height: auto;float: left;margin-bottom:30px;margin-right: 50px;border-radius:20px;padding: 20px;background-color: #F6F6F1;border-radius:10px;box-shadow: 3px 6px 10px #ccc;border: 1px solid #ccc;"><!--左边内容-->
        <span style="font-size: 18px;color: #5B422A;font-weight: 900;margin-right: 20px;">新书速递</span> 
        <!-- <span><a href="douban_classify.jsp" style="text-decoration: none;color:#5B422A; ">更多>></a></span> -->
        <hr style="margin-top: 20px;">
        <div style="width: 560px;height: 230px;margin-bottom: 20px;margin-top:10px;">
         <c:forEach var="newbook" items="${newbook}" begin="0" end="3" step="1">
              <div style="width:140px; height:210px;float: left;margin-top: 30px;text-align: center;">
               <div><img src="<%=basePath%>${newbook.imgs}" width="100px" height="150px;"></div>
               <div style="color: #3377AA;"><a href="<%=basePath%>book/bookmanage?method=showOnebook&book_id=${newbook.book_id }" style="text-decoration: none;color: #5B422A;font-size: 14px;font-weight: 900;">${newbook.book_name}</a></div>
               <div><p style="font-size:12px;margin-top:3px;color: #A5A39A;"><a href="<%=basePath%>book/bookmanage?method=showbooksByAuthor&author_id=${newbook.b_author.author_id}" style="text-decoration: none;color: #777;">${newbook.b_author.author_name}</a></p></div>
               <p style="font-size:12px;margin-top:3px;color: #666666;"><c:out value="${fn:substring(newbook.simple_desc, 0, 10)}..." /></p>
              </div>
        </c:forEach>   
        </div>
       <!--////////////////////新书速递/////////////////////////////////-->
        <br>
        <span style="font-size: 18px;color: #5B422A;font-weight: 900;margin-right: 20px;">最受关注图书榜 </span>
        <hr style="margin-top: 20px;">
        <div style="width: 600px;height: auto;float: left;">
        <c:forEach var="rankt" items="${rank_type}">
        <div style="width: 600px;height: auto;min-height:210px;float: left;">
             <p style="margin-top: 10px;margin-bottom:10px;color: #5B422A;font-size: 14px;font-weight: 900;">[${rankt.type_name}]</p>
             <c:forEach var="bookr" items="${bookranklist}">
             <c:if test="${rankt.book_type_id==bookr.type.book_type_id}">
                 <div style="height:auto; width:250px;float: left;background-color: #5A4129;padding: 10px;margin-right: 10px;margin-bottom:20px;border-radius:10px;box-shadow: 3px 6px 10px #B1934B;">
                	<div style="float:left;width:auto; height:auto;margin-right:10px;"><img src="<%=basePath%>${bookr.book.imgs}" width="100px" height="150px"></div>
                    <div style="float:left;width:140px; height:140px;margin-top: 5px;">
                    	<p style="margin-bottom: 10px;"><a href="<%=basePath%>book/bookmanage?method=showOnebook&book_id=${bookr.book.book_id }" style="font-size: 16px;font-weight: 700;color: #F6F6F1;">${bookr.book.book_name }</a></p>
                        <p style="font-size: 13px;color: #F6F6F1;">作者：${bookr.book.b_author.author_name }</p>
                        <p style="font-size: 13px;color: #F6F6F1;">评分：${bookr.score}</p>
                        <p style="font-size: 13px;color: #F6F6F1;">页数：${bookr.book.page_num}</p>
                        <p style="font-size: 13px;color: #F6F6F1;">出版时间： <fmt:formatDate value="${bookr.book.publish_date}" pattern="yyyy-MM-dd"/></p>
                        <p style="font-size: 13px;color: #F6F6F1;">简介：<c:out value="${fn:substring(bookr.book.simple_desc, 0, 16)}..." /></p>
                    </div>
                </div>
                </c:if>
        </c:forEach>  
        </div>          
        </c:forEach>
        </div>
        <!--////////////////////图书榜单/////////////////////////////////-->
         
        <span style="font-size: 18px;color: #5B422A;font-weight: 900;margin-right: 20px;">我的购书心愿单</span><span><a href="<%=basePath%>book/bookmanage?method=showMyWannaBuy&user_id=${sessionScope.current_user.user_id}" style="text-decoration: none;color:#5B422A; ">更多>></a></span>
        <hr style="margin-top: 20px;">
        <div style="width: 560px;height: auto;margin-top: 20px;margin-bottom: 20px;"><!-- 我的购书心愿单 -->
              <c:forEach var="wishtobuy" items="${wannebuy}" begin="0" end="4" step="1">
               <div style="width:111px; height:230px;float: left;text-align: center;">
                <div><img src="<%=basePath%>${wishtobuy.b.imgs}"  width="100px" height="150px"></div>
                <div style="margin-bottom:4px;"><a href="<%=basePath%>book/bookmanage?method=showOnebook&book_id=${wishtobuy.b.book_id }" style="text-decoration: none;color: #5B422A;font-size: 14px;font-weight:900;">${wishtobuy.b.book_name}</a></div>
                <div style="margin-bottom:4px;"><p style="font-size:12px;color: #A5A39A;"><a href="<%=basePath%>book/bookmanage?method=showbooksByAuthor&author_id=${wishtobuy.b.b_author.author_id}" style="text-decoration: none;color: #777;" >${wishtobuy.b.b_author.author_name}</a></p></div>
                <p style="font-size:12px;color: #666666;"><fmt:formatDate value="${wishtobuy.time}" pattern="yyyy-MM-dd"/>加入</p>
              </div>
              </c:forEach>
        </div><!-- 我的购书心愿单 -->
       <span style="font-size: 18px;color: #5B422A;font-weight: 900;margin-right: 20px;margin-top: 20px;">精彩书评</span>
       <hr style="margin-top: 20px;">
       <div style="width: 600px;height: auto;float: left;"><!-- 精彩书评 -->
              <c:forEach var="bookcomm" items="${bookcomment}">
               <div style="width:600px; height:auto;float: left;margin-bottom: 10px; ">
               <img src="<%=basePath%>${bookcomm.user.imgs}" width="50px" height="50px" style="border-radius:25px;float: left;margin-right: 20px;margin-top: 10px;margin-bottom: 10px;">
               <div style="float: left;margin-top: 10px;margin-right: 3px;"><a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${bookcomm.user.user_id }" style="text-decoration: none;color: #B2B048;">${bookcomm.user.username}</a></div>
               <p style=";width:450px;font-size:12px;float: left;margin-top: 10px;margin-bottom: 10px;"> 评论《<a href="<%=basePath%>book/bookmanage?method=showOnebook&book_id=${bookcomm.book.book_id }" style="text-decoration: none;color: #5B422A;">${bookcomm.book.book_name}</a>》 | <span style="color: #AAAAAA;"><c:out value="${fn:substring(bookcomm.book.simple_desc, 0, 15)}..." /></span><span style="float: right;"><fmt:formatDate value="${bookcomm.date}" pattern="yyyy-MM-dd HH:mm:ss"/></span></p>
               <p style="width: 360px;height: auto;float: left;">${bookcomm.masg }</p>
               
              </div>
              </c:forEach>
        </div><!-- 精彩书评 -->
        
        
      </div><!--左边内容-->
    <!-----///////////////////////////////////////////////////////////////左边内容---------------------> 
       
        <div ><!--右边内容-->
        	<div class="right_img"><img src="lsr/img/douban4.png"><br></div>
            <div class="douban_right_read">  <!--曲奇阅读器的div-->
            曲奇阅读器
            <hr>
            <ul class="right_menu">
            	<li><a href="#"><img src="lsr/img/web.png"></a></li>
                <li><a href="#"><img src="lsr/img/iphone.png"></a></li>
                <li><a href="#"><img src="lsr/img/ipad.png"></a></li>
                <li><a href="#"><img src="lsr/img/andriod.png"></a></li>
            </ul>
            </div> <!--曲奇阅读器的div-->           
            <div style="margin-bottom: 30px;">     <!--热门标签-->
            	<p style="font-size: 18px;color: #5B422A;font-weight: 900;margin-right: 20px;margin-bottom:10px;margin-top: 20px;">热门标签</p>
                 <c:forEach var="alltype" items="${book_type}">
                  <span style="color:#583F26;margin-right:5px;">[<a href="<%=basePath%>book/bookmanage?method=showBooksByType&type_id=${alltype.book_type_id}" style="text-decoration: none;color:#583F26; ">${alltype.type_name}</a>]</span>
                 </c:forEach>
            </div> <!--热门标签-->
            <div>
            	<img src="lsr/img/douban3.png">
            </div>
            <div>
            	<img src="lsr/img/yuedu4.png">
            </div>
        </div><!--右边内容-->
     </div><!--曲奇的整体内容-->
      	<div id="all_bottom" style="height:30px; width:960px; margin-left:150px;float: left;">
		<hr style="margin: 0px;color: #583F26;">
		<div style="font-size:12px; float:left; position:relative;padding-top:5px;color: #3377AA;">©
			2005－2014 Ruins7.com, all rights reserve</div>
		<div style="float:right;  position:relative;">
			<a href="#"
				style=" text-decoration:none;font-size:11px ; color:#3377AA">关于曲奇</a>
			· <a href="#"
				style=" text-decoration:none;font-size:11px ;color:#3377AA">在曲奇工作</a>·
			<a href="#"
				style=" text-decoration:none; font-size:11px;color:#3377AA">联系我们
			</a>· <a href="#"
				style=" text-decoration:none;font-size:11px;color:#3377AA ">
				免责声明</a>· <a href="#"
				style=" text-decoration:none;font-size:11px;color:#3377AA ">
				帮助中心 </a>· <a href="#"
				style=" text-decoration:none;font-size:11px;color:#3377AA ">开发者
				移动应用 · 曲奇广告</a>· <a href="#"
				style=" text-decoration:none;font-size:11px;color:#3377AA ">
				移动应用 </a>· <a href="#"
				style=" text-decoration:none;font-size:11px;color:#3377AA ">
				曲奇广告</a>
		</div>
	</div>
	<!---底部栏--->
  </body>
</html>

 