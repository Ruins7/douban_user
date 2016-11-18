<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'aOffActivity.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

  </head>
  
  <body>
     活动信息:<hr>
        id: ${offActivity.offactivity_id }
    标题:${offActivity.offactivity_title }
    开始时间:<fmt:formatDate value="${offActivity.start_time }" pattern="yyyy年MM月dd日  HH:mm:ss E"/>
    结束时间:<fmt:formatDate value="${offActivity.end_time }" pattern="yyyy年MM月dd日  HH:mm:ss E"/>
  城市:${offActivity.city.city_name }<br>
    相册:${offActivity.activityAlbum.activityAlbum_id }
    创办者:${ offActivity.user.userName}
    描述：${offActivity.offactivity_desc }
  <hr>
  <form action="ActivityManageServlet">
 <input type="hidden" value="updateOffActivity" name="method">
  <input type="hidden" value="${offActivity.offactivity_id }" name="offactivity_id">
     标题: <input type="text" value="${offActivity.offactivity_title }" name="offactivity_title"> 
     <br>
    开始时间:<input type="datetime" value="${offActivity.start_time }" name="start_time"><br>
  结束时间:<input type="datetime" value="${offActivity.end_time }" name="end_time"><br>
描述:<input type="text" value="${offActivity.offactivity_desc }" name="offactivity_desc">
状态:<input type="text" value="${offActivity.offactivity_statue }" name="offactivity_statue">
    大标题:<input type="text" value="${offActivity.offactivity_bigtype }" name="offactivity_bigtype"> 
    小标题:<input type="text" value="${offActivity.offActivity_smalltype }" name="offActivity_smalltype">
    <input type="submit" value="提交"><input type="reset" value="重置">
  </form>
  </body>
</html>
