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

<title>心愿购书单</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<link href="" rel="stylesheet" type="text/css">
 

</head>

<body>

	<jsp:include page="/book/douban_bookhead.jsp"></jsp:include>
	<div style="margin-left: 200px;margin-top: 20px;margin-bottom: 10px;">
	  <a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${cuser.user_id }"><img src="<%=basePath%>${cuser.imgs}" width="50px" height="50px" style="border-radius:25px;margin-right: 20px;float: left;"></a>
	  <p style="float:left;margin-top:15px;color: #5d442d;font-size: 16px;font-weight: 900;">${cuser.username } 的心愿购书单</p> 
	</div>
    <div style="margin-bottom:30px;margin-top:10px;margin-left:200px;width: 600px;height: auto;float: left;clear:left;padding:20px;background-color: #F6F6F1;border-radius:10px;box-shadow: 3px 6px 10px #ccc;border: 1px solid #ccc;"><!--左边内容-->
		       <c:forEach var="book" items="${book}">
                 <div style="width: 560px;height: auto;float: left;margin-top: 20px;margin-bottom: 20px;">
                   <img src="<%=basePath%>${book.b.imgs}" width="100px" height="150px" style="float: left;margin-right: 20px;">
                   <p style="margin-bottom: 10px;"><a href="<%=basePath%>book/bookmanage?method=showOnebook&book_id=${book.b.book_id }" style="text-decoration: none;font-size: 16px;color:#5d442d;font-weight: 900;">${book.b.book_name }</a></p>
                   <p style="color: #666666;font-size: 12px;">作者：[${book.b.b_author.country}]  ${book.b.b_author.author_name} | ${book.b.b_translator.author_name } </p> 
                   <p style="color: #666666;font-size: 12px;">出版社：${book.b.b_publisher.publisher_name}</p> 
                   <p style="color: #666666;font-size: 12px;">出版时间：<fmt:formatDate value="${book.b.publish_date }" pattern="yyyy-MM-dd"/></p> 
                   <p style="color: #666666;font-size: 12px;">页数：${book.b.page_num }</p> 
                   <p style="color: #666666;font-size: 12px;">类型：[${book.b.b_type.type_name }]</p> 
                   <p style="color: red;font-size: 12px;">评分：${book.b.avgscore }</p>
                   <br>
                   <p style="color: #5d442d;font-size: 12px;">“${book.b.simple_desc }”</p>
                   <br>
                   <p style="color: #666666;font-size: 12px;">加入时间：<fmt:formatDate value="${book.time }" pattern="yyyy-MM-dd"/></p>
                 </div>
                 <hr style="width: 600px;float: left;color:#F6F6F1; ">
             </c:forEach>
		</div><!--左边内容-->
		<!--右边内容-->
		 <div style="float: left;margin-left: 100px;margin-top: 10px;width: 300px;height: auto;">     <!--热门标签-->
            	 
            	<p style="font-size: 18px;color: #5B422A;font-weight: 900;margin-right: 20px;margin-bottom: 10px;margin-top: 10px;">热门作者</p>
            	    <c:forEach var="allauthor" items="${book_author}">
                        <span style="color:#583F26;margin-right:5px;">[<a href="<%=basePath%>book/bookmanage?method=showbooksByAuthor&author_id=${allauthor.author_id}"  style="font-size:14px;text-decoration: none;color:#583F26; ">${allauthor.author_name}</a>]</span>
                     </c:forEach>
            	 
            	<p style="font-size: 18px;color: #5B422A;font-weight: 900;margin-right: 20px;margin-bottom: 10px;margin-top: 20px;">热门标签</p>
                 <c:forEach var="alltype" items="${rank_type}">
                  <span style="color:#583F26;margin-right:5px;">[<a href="<%=basePath%>book/bookmanage?method=showBooksByType&type_id=${alltype.book_type_id}"  style="font-size:14px;text-decoration: none;color:#583F26; ">${alltype.type_name}</a>]</span>
                 </c:forEach>
            	    	
            </div> <!--热门标签-->
		<!--右边内容-->  
	 
	
	 
	
 
		<!--曲奇图片div-->
		  <div class="doubanphoto">
			<img src="lsr/img/douban5.png">
		</div> <!--曲奇图片div-->
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
