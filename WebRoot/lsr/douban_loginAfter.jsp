<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
 <link href="lsr/css/douban_loginAfter.css" rel="stylesheet"type="text/css">
<link href="lsr/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="lsr/css/bootstrap-theme.css" rel="stylesheet" type="text/css">
<link href="lsr/css/jquery-ui.css" rel="stylesheet">
<script src="lsr/js/jquery-2.1.1.js"></script>
<script src="lsr/js/bootstrap.min.js"></script>
<script src="lsr/js/douban_loginAfter.js"></script>
<script src="lsr/js/jquery-ui.js"></script>
<script> 
$(function(){
	$('#fabiao').click(function(){
		document.fabiaorizhi.submit();
	});
	
	$('.invo').click(function(e){
		e.preventDefault();
	});
});
</script>
</head>
<body>
 <jsp:include page="/lsr/douban_head.jsp"></jsp:include>
<div id="all">
	<div id="left"><!--左边-->
	      <div class="panel-group" id="accordion">
				<div class="panel panel-default"><!-- 说句话开始 -->
					<div class="panel-heading" style="text-align: center;">
						<h4 class="panel-title">
							<a style="color:#545652;font-size: 14px;" data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"><img src='lsr/img/shuojuhua.png' width="20px" height="20px"> 写句话 </a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse">
						<div class="panel-body">
			                 <form action="user/userinfo?method=sendBroadcast" enctype="multipart/form-data"  method="post" name="sanbro"><!--广播内容框-->
				             <textarea id="bro" class="conent" rows="3" cols="90%" name="content" style="color:#545652; border: 0px;padding-bottom: 10px;" onKeyUp="words_sum_up()"></textarea>  
		                     <div class="panel-body" style="color:#545652;height: 35px;margin-top: 0xp;padding-top: 10px;"><span id="broleft" style="font-size: 12px;padding-right: 450px;color: #3377AA;">还能输入280个字</span><img src="lsr/img/fabu.png" class="fabupng" id="fabutubro"></div>
						     </form>
						</div>
					</div>
				</div><!-- 说句话结束 -->
				<div class="panel panel-default"><!-- 发照片开始 -->
					<div class="panel-heading" style="text-align: center;">
						<h4 class="panel-title">
							<a style="color:#545652;font-size: 14px;" data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"><img src='lsr/img/fazhaopian.png' width="20px" height="20px">  发照片</a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse">
						<div class="panel-body">
							 <form action="user/userinfo?method=sendBroadcast" enctype="multipart/form-data" method="post" name="sandimgsbro">
                                  <textarea id="bro1" class="conent" rows="3" cols="90%" name="content" style="color:#545652; border: 0px;margin-bottom: 5px;" onKeyUp="words_sum_up1()"></textarea>     
                                  <div id="uploadimgdiv" style="background-image: url('lsr/img/uploadbroimg.png');width: 673px;height: 113px;">
                                  <input id="uploadbroimg" type="file" name="imgs" style="opacity:0;">
                                  <div id="preview" style="width:auto;height:auto;margin-left: 100px; border:0px solid gray;"></div>
                                </div>
                                <div style="margin-top: 20px;margin-left:20px;float: left;">
                                  <span id="broleft1" style="font-size: 12px;color: #3377AA;">还能输入280个字</span>
                                </div>
                                <img src="lsr/img/fabu.png" class="fabupng" id="fabutuimg" style="float: right;margin-top: 10px;">
                             </form>
                       </div>
				    </div>
				  </div><!-- 发照片结束 -->
				<div class="panel panel-default"><!-- 写日志开始 -->
					<div class="panel-heading" style="text-align: center;">
						<h4 class="panel-title">
							<a style="color:#545652;font-size: 14px;" data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseThree"><img src='lsr/img/xierizhi.png' width="20px" height="20px"> 写日志</a>
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse">
						<div class="panel-body">
							<p style=" font-size:12px;">写下一段令人难忘的事...</p>
							<form action="user/userinfo?method=writeDiary" name="fabiaorizhi" method="post" enctype="multipart/form-data">
							<table>
							    <tr >
							       <td style="width: 100px;">题目:</td>
							       <td><input type="text" name="title" class="form-control" placeholder="为你的日记起个名字吧..." style="width:220px; height:34px;"></td>
							       <td></td>
							    </tr>
							    <tr  >
							       <td>正文:</td>
							       <td><textarea id="bro" class="form-control" placeholder="想说些什么..." rows="10" cols="90%" name="content" style="color:#545652; border: 2px;padding-bottom: 10px;word-wrap:break-word;resize:none;margin-top: 10px;margin-bottom: 10px;" onKeyUp="words_sum_up()"></textarea></td>
							       <td></td>
							    </tr>
							    <tr>
							       <td>阅读权限:</td>
							       <td>
							          <select name="read_root" style="width:150px; height:34px;text-align: center;border-radius:5px 5px 5px 5px;margin-top: 10px;margin-bottom: 10px; ">
							              <option value="1">所有人可见</option>
							              <option value="2">仅好友可见</option>
							              <option value="3">仅自己可见</option>
							          </select>
							       </td>
							       <td></td>
							    </tr>
							    <tr>
							       <td></td>
							       <td>
							          <img id="fabiao" style="cursor: pointer;" src="lsr/img/rizhifabiao.png">
							       </td>
							       <td></td>
							    </tr>
							</table>
							</form>
						</div>
					</div>
				</div><!-- 写日志结束 -->
				<div class="panel panel-default"><!-- 发布东西开始 -->
					<div class="panel-heading" style="text-align: center;">
						<h4 class="panel-title">
							<a style="color:#545652;font-size: 14px;" data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseFour"><img src='lsr/img/fabudongxi.png' width="20px" height="20px"> 发布东西 </a>
						</h4>
					</div>
					<div id="collapseFour" class="panel-collapse collapse">
						<div class="panel-body">
							<p style=" font-size:12px;color: #3377AA;">发布创意新奇的玩意儿，并说说你对它们的看法...</p>
							<div>
							 <form action="things/thingsinfo?method=publishThings" enctype="multipart/form-data" method="post" name="sendthings">
							    东西名称：<input name="things_name" type="text" class="form-control" placeholder="输入你发布的东西的名称..." style="width:220px; height:34px;">
							  <br>
							  类型选择:<select name="type" style="width:100px; height:34px;text-align: center; border-radius:5px;">	 
								<option value="1">女装</option>							
								<option value="2">女鞋</option>							
								<option value="3">男装</option>							
								<option value="4">男鞋</option>							
								<option value="5">箱包</option>							
								<option value="6">配饰</option>							
								<option value="7">家居装饰</option>							
								<option value="8">美妆</option>							
								<option value="9">美食</option>							
								<option value="10">数码</option>							
								<option value="11">户外运动</option>							
								<option value="12">礼物</option>							
								<option value="13">日用百货</option>							
						     </select>
						     <br>
							    你的评价：<input id="bro2"  name="simple_desc" type="text" class="form-control" placeholder="输入你对它的评价..." style="width:650px; height:34px;margin-bottom: 5px;"  onKeyUp="words_sum_up2()">
							 <span id="broleft2" style="font-size: 12px;color: #3377AA;">还能输入40个字</span>
							 <input type="file" id="uploadbroimg3" name="imgs"> 
							 </form>
							 <div id="preview3" style="width:auto;height:auto;margin-left: 100px; border:0px solid gray;"></div>
							 <img src="lsr/img/fabu.png" class="fabupng" id="fabuthings" style="float: right;margin-top: 10px;">
							</div>
						</div>
					</div>
				</div><!-- 发布东西结束 -->
				  <div class="panel panel-default"><!-- 好友广播开始 -->
					<div class="panel-heading" style="text-align: center;">
						<h4 class="panel-title">
							<a style="color:#545652;font-size: 14px;" data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseFive"><img src='lsr/img/friendsbro.png' width="20px" height="20px"> 好友广播 </a>
						</h4>
					</div>
					<div id="collapseFive" class="panel-collapse collapse">
						<div class="panel-body">
							<!-- 我的好友的广播 --> 
							<c:set var="page" value="${requestScope.myfriendsBro }"></c:set>
		                    <c:forEach var="bro" items="${page.data }">
		                    <div style="font-size:13px;background-color:#F9F9F9;width:670px;height:250px;margin-top: 15px;margin-bottom: 20px;padding-top: 5px;padding-bottom: 10px;padding-left:20px;">
				              <div style="width:60px;margin-top:10px;height:200px;position:relative;float:left;">
					                <a href=""><img src="<%=basePath%>${bro.user_info.imgs}" width="60px" height="60px" style="border-radius:30px;"></a><!-- 用户头像 -->
				              </div>
				              <div style="height:220px;width:560px; padding:10px;margin:10px;word-break:break-all;overflow:hidden;float: left;">
                              <span style="color: #3377AA;"><a class="item">${bro.user_info.username }</a>说 :</span>		
					          <div style="background-color:#000000;float: right;margin-right: 30px;margin-left: 15px;">
					             <c:if test="${bro.imgs!=null}">
					                 <img width="140px" height="170px" src="<%=basePath%>${bro.imgs }">
					             </c:if></div>
					          <div style="height: 170px;width: 380px;padding: 10px;">
					          <img src="lsr/img/frontyinhao.png"> ${bro.content } <img src="lsr/img/afteryinhao.png"></div>
					          <span style="color: #999999;margin-right: 70px;"> 
					          <fmt:formatDate value="${bro.time }" pattern="yyyy-MM-dd hh:mm"/></span>
					          <a class="item" href="#">回应</a>  <a class="item" href="#">赞</a>  <a class="item" href="#">转播</a>
				            </div> 
		                </div>	    
		                </c:forEach>
		                <div id="fribro_more" style="width: 700px;float: left;font-size: 12px;height: auto;width: auto"></div>
		                <div style="width: 700px;float: left;text-align: center;font-size: 12px;"><a id="mofribro" class="item" href="">-- 加载更多 --</a><input id="fribro_pagenum" type="hidden" value="${page.currentPage }"></div> 
						</div>
					</div>
				</div><!-- 好友广播结束 -->  
				  <div class="panel panel-default"><!-- 好友推荐开始 -->
					<div class="panel-heading" style="text-align: center;">
						<h4 class="panel-title">
							<a style="color:#545652;font-size: 14px;" data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapsesix"><img src='lsr/img/friendsrec.png' width="20px" height="20px"> 好友推荐 </a>
						</h4>
					</div>
					<div id="collapsesix" class="panel-collapse collapse">
						<div class="panel-body">
							<!-- 我的好友的推荐 --> 
							<c:set var="page" value="${requestScope.myfriendsRec }"></c:set> 
		                      <c:forEach var="rec" items="${page.data }">
		                      <div style="font-size:13px; background-color:#F9F9F9;width:670px;height:250px;margin-top: 15px;margin-bottom: 20px;padding-top: 5px;padding-bottom: 10px;padding-left:20px;">
			            	     <div style="width:60px;margin-top:10px;height:200px;position:relative;float:left;">
			          		        <a href=""><img src="<%=basePath%>${rec.user_info.imgs }" width="60px" height="60px" style="border-radius:30px;"></a><!-- 用户头像 -->
				                 </div>
				                 <div style="height:220px;width:560px; padding:10px;margin:10px;word-break:break-all;overflow:hidden;float: left;">
					                <span style="color: #3377AA;"><a class="item">${rec.user_info.username }</a> 推荐  <a class="item" href="">${rec.item.type_table}</a></span>		
					                <div style="float: right;margin-right: 30px;margin-left: 15px;">
					                <c:if test="${rec.item.imgs!=null}">
					                <a href="">     
					                <img src="<%=basePath%>${rec.item.imgs }" width="140px" height="170px">
					                </a>
					                 </c:if>
					                 </div>
					                <div style="height: 170px;width: 380px;padding: 10px;"><p style="color: #3377AA;">名称 :  ${rec.item.name } </p>
					                  <img src="lsr/img/frontyinhao.png"> ${rec.my_commons } <img src="lsr/img/afteryinhao.png">
					                </div>
					                  <span style="color: #999999;margin-right: 70px;">
					                   <fmt:formatDate value="${rec.time }" pattern="yyyy-MM-dd HH:mm"/>  </span>
					                  <a class="item" href="#">回应</a>  <a class="item" href="#">赞</a>  <a class="item" href="#">转播</a>
				                 </div> 
		                      </div>   
		                      </c:forEach> 
		                <div id="frirec_more" style="width: 700px;float: left;font-size: 12px;height: auto;width: auto"></div>
		                <div style="width: 700px;float: left;text-align: center;font-size: 12px;"><a id="mofrirec" class="item" href="">-- 加载更多 --</a><input id="frirec_pagenum" type="hidden" value="${page.currentPage }"></div> 
						</div>
					</div>
				</div><!-- 好友推荐结束 -->  
				<div class="panel panel-default"><!-- 我的广播开始 -->
					<div class="panel-heading" style="text-align: center;">
						<h4 class="panel-title">
							<a style="color:#545652;font-size: 14px;" data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapsesev"><img src='lsr/img/mybro.png' width="20px" height="20px"> 我的广播 </a>
						</h4>
					</div>
					<div id="collapsesev" class="panel-collapse collapse">
						<div class="panel-body">
							 <!-- 我的广播 -->  
			<c:set var="page" value="${requestScope.mybro }"></c:set>
		    <c:forEach var="bro" items="${page.data }">
		    <div style="font-size:13px;background-color:#F9F9F9;width:670px;height:250px;margin-top: 15px;margin-bottom: 20px;padding-top: 5px;padding-bottom: 10px;padding-left:20px;">
				 <div style="width:60px;margin-top:10px;height:200px;position:relative;float:left;">
					  <a href="">
					     <img src="<%=basePath%>${bro.user_info.imgs}" width="60px" height="60px"  style="border-radius:30px;">
					  </a><!-- 用户头像 -->
				 </div>
				 <div style="height:220px;width:560px; padding:10px;margin:10px;word-break:break-all;overflow:hidden;float: left;">
                      <span style="color: #3377AA;">
                         <a class="item">
                            ${bro.user_info.username }
                         </a>说 :
                      </span>		
					  <div style="float: right;margin-right: 30px;margin-left: 15px;">
					     <c:if test="${bro.imgs!=null}">
					                <img width="140px" height="170px" src="<%=basePath%>${bro.imgs }">
					     </c:if> 
					  </div>
					  <div style="height: 170px;width: 380px;padding: 10px;"> 
					      <img src="lsr/img/frontyinhao.png"> 
					          ${bro.content } 
					      <img src="lsr/img/afteryinhao.png">
					  </div>
					  <span style="color: #999999;margin-right: 150px;"> 
					      <fmt:formatDate value="${bro.time }" pattern="yyyy-MM-dd"/> 
					  </span>
					  <a class="item" href="#">回应</a>  <a class="item" href="#">赞</a>  <a class="item" href="#">转播</a>
				 </div> 
		    </div>	    
		    </c:forEach> 
		                <div id="mybro_more" style="width: 700px;float: left;font-size: 12px;height: auto;width: auto"></div>
		                <div style="width: 700px;float: left;text-align: center;font-size: 12px;"><a id="momybro" class="item" href="">-- 加载更多 --</a><input id="mybro_pagenum" type="hidden" value="${page.currentPage }"><input id="uid" type="hidden" value="${sessionScope.current_user.user_id}"></div> 
						</div>
					</div>
				</div><!-- 我的广播结束 -->
				
				<div class="panel panel-default"><!-- 我的推荐开始 -->
					<div class="panel-heading" style="text-align: center;">
						<h4 class="panel-title">
							<a style="color:#545652;font-size: 14px;" data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseeight"><img src='lsr/img/myrec.png' width="20px" height="20px"> 我的推荐</a>
						</h4>
					</div>
					<div id="collapseeight" class="panel-collapse collapse">
						<div class="panel-body">
							<!-- 我的推荐-->
		   <c:set var="page" value="${requestScope.myrec }"></c:set>
		   <c:forEach var="rec" items="${page.data }">
		    <div style="font-size:13px; background-color:#F9F9F9;width:670px;height:250px;margin-top: 15px;margin-bottom: 20px;padding-top: 5px;padding-bottom: 10px;padding-left:20px;">
				  <div style="width:60px;margin-top:10px;height:200px;position:relative;float:left;">
					  <a href="">
					    <img src="<%=basePath%>${rec.user_info.imgs }" width="60px" height="60px" style="border-radius:30px;">
					  </a><!-- 用户头像 -->
				  </div>
				  <div style="height:220px;width:560px; padding:10px;margin:10px;word-break:break-all;overflow:hidden;float: left;">
					 <span style="color: #3377AA;">
					    <a class="item">
					       ${rec.user_info.username }
					    </a>
					                 推荐 
					    <a class="item" href="">
					       ${rec.item.type_table}
					    </a>
					 </span>		
					   <div style="float: right;margin-right: 30px;margin-left: 15px;">
					       <c:if test="${rec.item.imgs!=null}">
					      <a href="">
					         <img src="<%=basePath%>${rec.item.imgs }" width="140px" height="170px">
					      </a>
					      </c:if>
					   </div>
					   <div style="height: 170px;width: 380px;padding: 10px;">
					      <p style="color: #3377AA;">
					                      名称 :  ${rec.item.name } 
					      </p>
					      <img src="lsr/img/frontyinhao.png"> 
					         ${rec.my_commons } 
					      <img src="lsr/img/afteryinhao.png">
					   </div>
					   <span style="color: #999999;margin-right: 150px;"> 
					      <fmt:formatDate value="${rec.time }" pattern="yyyy-MM-dd"/> 
					   </span>
					   <a class="item" href="#">回应</a>  <a class="item" href="#">赞</a>  <a class="item" href="#">转播</a>
				 </div> 
		    </div>   
		    </c:forEach>  
		                <div id="myrec_more" style="width: 700px;float: left;font-size: 12px;height: auto;width: auto"></div>
		                <div style="width: 700px;float: left;text-align: center;font-size: 12px;"><a id="momyrec" class="item" href="">-- 加载更多 --</a><input id="myrec_pagenum" type="hidden" value="${page.currentPage }"></div> 
						</div>
					</div>
				</div><!-- 我的推荐结束 -->
				 
        </div> 
		<div id="conent_div"><!--整体内容的div--> 
        </div><!--整体内容的div-->
     </div><!--留言板-->
   </div><!--左边-->
   <div id="right"><!--右边-->
	  <div class="fix_1">
	      <div style="padding-top:15px; color:#007722; padding-left:15px; font-size:14px">曲奇正在发生 · · · · · ·</div>
		  <br>
		  <ul id="fix_1_ul" style="padding-left:15px; font-size:14px;list-style-type: circle;margin-left: 20px;color: #3377AA;">
		     <li><a href="#">金羊毛开听：给欧巴赚钱</a></li>
			 <br>
			 <li><a href="#">金羊毛开听：给欧巴赚钱</a></li>
			 <br>
			 <li><a href="#">金羊毛开听：给欧巴赚钱</a></li>
			 <br>
			 <li><a href="#">金羊毛开听：给欧巴赚钱</a></li>
		  </ul>
		  <div style="margin-top:20px;padding-top: 20px;font-size: 12px;">
		           <p style=" color:#007722; padding-left:5px; font-size:14px">我的关注···(成员：<a class="item" href="<%=basePath%>user/userinfo?method=MyFocus&user_id=${sessionScope.current_user.user_id}">${sessionScope.myfocusnum}</a>)</p>
		    <div id="myFocus">
		       <ul>
		       <c:forEach items="${sessionScope.myfocus}" var="myfriend" begin="0" end="9" step="1">
		            <li><a href="<%=basePath%>user/userinfo?method=searchPeople&user_id=${myfriend.user_id }">
		                <img src=" ${myfriend.imgs }" width="40px;" height="40px;" style="border-radius:20px;">
		                </a>
		            </li> 
		       </c:forEach>    
               </ul>
		    </div>
		    <p style="color:#007722;">→ 我被  <a class="item invo" href="#" >${sessionScope.whofocusme }</a>人关注</p>
		  </div>
		  <div style="padding-top:20px;">
		     <img src="lsr/img/af2.png">
		  </div>
			 <img src="lsr/img/af1.png">
		  </div> 
      </div><!--右边-->
 <jsp:include page="/lsr/douban_foot.jsp"></jsp:include>
</body>

</html>

