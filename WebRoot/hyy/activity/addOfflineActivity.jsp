<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>发起线下活动</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="hyy/css/douban_city.css" rel="stylesheet" type="text/css">
  <script>
  $(function(){
	 
  });
  </script>

  </head>
  
  <body>
  <jsp:include page="/hyy/activity/douban_activityhead.jsp"></jsp:include>
    <div style="width: 650px;height: auto;float: left;margin-left: 200px;margin-top: 30px;padding: 15px;margin-bottom: 30px;">
    <p style="color: #664433;font-size: 22px;float: left;font-weight: 700;width: 630px;">创建线下活动</p>
    <form action="<%=basePath%>hyy/service/ActivityManageServlet?method=addOffActivity" method="post" enctype="multipart/form-data">
    
    <table style="width: 530px;height: auto;float: left;">
        <tr>
           <td style="width: 150px;height: 70px;">活动名称</td>
           <td><input type="text" name="offactivity_title" class="form-control" placeholder="活动名称..." style="width:220px; height:34px;"></td>
        </tr>
        <tr>
          <td style="width: 150px;height: 70px;">活动类型</td>
          <td><select class="form-control" name="offActivity_type">
          <c:forEach var="t" items="${type_list}">
            <option value="${t.type_id }">${t.type_name }</option>
          </c:forEach>   
          </select></td>
        </tr>
        <tr>
           <td style="width: 150px;height: 70px;">活动时间</td>
           <td><input type="datetime" name="start_time" class="form-control" placeholder="活动开始时间..." style="width:220px; height:34px;"></td>
           <td><input type="datetime" name="end_time" class="form-control" placeholder="活动结束时间..." style="width:220px; height:34px;"></td>
        </tr>
        <tr>
           <td style="width: 150px;height: 70px;">活动地点</td>
           <td>请选择城市:<select class="form-control" name="city_id">
                 <c:forEach var="c" items="${applicationScope.city }">
                     <option value="${c.city_id }">${c.city_desc }</option>
                 </c:forEach>
           </select></td>
           <td>详细地址：<input type="text" name="position" class="form-control" placeholder="活动详细地址..." style="width:220px; height:34px;"></td>
        </tr>
        
        <tr>
            <td style="width: 150px;height: 70px;">上传活动海报</td>
            <td><input type="file" name="imgs"></td>
        </tr>
       
    </table>
    <table style="width: 530px;height: auto;float: left;">
    <tr>
           <td style="width: 80px;height: 70px;">活动描述</td>
           <td><textarea rows="3" cols="30%" class="form-control" name="offactivity_desc" style="resize:none;"></textarea> </td>
        </tr>
          <tr>
            <td></td>
            <td><input type="submit" value="创建该活动" style="width: 80px;height: 50px;"></td>
        </tr>
    </table>
     </form>
</div>
<div style="width: auto;height: auto;float: left;margin-left: 10px;margin-top: 30px;">
<img alt="" src="<%=basePath%>lsr/img/huodongxuzhi.png">
</div>
  </body>
</html>
