<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>曲奇未登录主页</title>
<base href="<%=basePath%>">
<link rel="icon" href="lsr/img/quqi.ico">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="lsr/css/douban_login.css" rel="stylesheet" type="text/css">
<link href="lsr/css/jquery-ui.css" rel="stylesheet">
<link href="lsr/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="lsr/css/bootstrap-theme.css" rel="stylesheet" type="text/css">
<script src="lsr/js/jquery-2.1.1.js"></script>
<script src="lsr/js/bootstrap.min.js"></script>
<script src="lsr/js/jquery.js"></script>
<script src="lsr/js/jquery-ui.js"></script>
<script src="lsr/js/douban_login.js"></script>
<script src="lsr/js/lsrCity.js"></script>
<script>
$(function(){
	$('.invo').click(function(e){
		e.preventDefault();
	});
});
</script>
</head>

<body>
	<div id="head" style="background-color: #E77918;" >
		<!--头部-->
		<div style="padding-left:100px; padding-top:10px; float:left;">
		 	<img src="lsr/img/quqi1.png" width="150px" height="80px"/>
		</div>
		<!--搜索表单-->
		<form action="">
			<div class="head1">
				<input type="text" class="form-control" style=" height:30px;width:260px;float: left;"
					placeholder="书籍，电影，音乐，小组，小站，成员">
				<button class="btn btn-default" type="submit" style=" height:30px;width:50px;">搜索</button>
			</div>
		</form>

		<div class="menu">
			<ul>
				<li><a href="#" class="invo" style="color:#584029">读书</a></li>
				<li><a href="#" class="invo"  style="color:#278DCE">电影</a></li>
				<li><a href="#" class="invo"  style="color:#F9C388">音乐</a></li>
				<li><a href="#" class="invo"  style="color:#2CA2A9">小组</a></li>
				<li><a href="#" class="invo" style="color:#48BAF5">同城</a></li>
				<li><a href="#" class="invo"  style="color:#1C9439">東西</a></li>
			</ul>
		</div>
	</div>
	<!--头部-->

	<!--/////////////////////////////////////////////////////////////////////////////////登录的头部-->
	<div id="logins">
		<!--登陆注册-->
		<div class="register">
			<a href="<%=basePath%>lsr/register.jsp" style="text-decoration: none;"><p style="text-decoration:none; color:#F8F5F5; font-size:16px; padding-top:10px;text-align: center;font-weight: 500;">加入我们注册</p></a>
		</div>
		<!-----------登录-->
			<div class="login">
			<form action="user/userlogin?method=UserLogin" method="post" name="loginform">
			  <table>
                <tr>
                  <td><input type="text" name="username" class="form-control" placeholder="你的名号.." style="width:150px; height:34px;"></td>
                  <td><input type="password" name="pwd" class="form-control" placeholder="你的密码.." style="width:150px; height:34px;"></td>
                  <td><input type="text" name="checkcode" class="form-control" placeholder="验证码.." style="width:150px; height:34px;"></td>
                  <td><img id="loginimg" src="lsr/img/login.png"></td>
               </tr>
               <tr>
                  <td><input type="checkbox" name="freetologin" value="yes">记住我</td>
                  <td><a href="" >忘记密码</a></td>
                  <td><img id="checkcode" src="user/validationServlet" height="40" width="150"/></td>
              </tr>
            </table>
		</form>
			</div>
		
	</div>
	<!--登陆注册-->
	<!--/////////////////////////////////////////////////////////////////////////////////登陆注册/-->
	<div id="photos">
		<!--图片1-->
		<img src="lsr/img/2.png" style="padding-left:200px; padding-top:25px">
	</div>
	<!--图片1-->
	<!--//////////////////////////////////////////////////////////////////////////////图片////-->
	<div id="hot">
		<!--热点内容-->
		<div class="hot_left">
			<!--左边-->
			<p style=" font-size:14px;color: #E77918;width: 800px;float: left;">热点内容······</p>
			<div style="width:50%; float:left;">
				<img src="lsr/img/a.png"><br>
				<a>画家Horace Pippin</a><br> <img src="lsr/img/b.png"><br>
				<a>旧底片中洗出的中国记忆</a><br>
			</div>
			<div style="width:50%; float:left; position:relative;">
				<img src="lsr/img/c.png"><br> <a>❀Zoo ❀</a><br> <img
					src="lsr/img/d.png"><br> <a>て</a>
			</div>
		</div>
		<!--左边-->
		<div class="hot_be" style="float: left;margin-top: 30px;">
			<!--中间-->

			<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a style="color:#4637C5;" data-toggle="collapse"
								data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"> 分享丨8千元普吉岛蜜月行，7天7夜全攻略 </a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in">
						<div class="panel-body">
							<p style=" font-size:12px;">
								简七君的日记<br> 还有一些图片，因为曲奇传图实在太费劲，所以没传了，原贴地址在这里 8000块7天7夜...
							</p>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a style="color:#4637C5;" data-toggle="collapse"
								data-toggle="collapse" data-parent="#accordion"
								href="#collapseTwo"> fb上的中国通朋友们 </a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse">
						<div class="panel-body">
							<p style=" font-size:12px;">fb上早先认识的会中文的几位国际友人（有位芬兰大叔当初十年前在夏令营做辅导员所以认识了他，曾在驻华使馆工作；自己翻译古文观止）</p>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a style="color:#4637C5;" data-toggle="collapse"
								data-toggle="collapse" data-parent="#accordion"
								href="#collapseThree"> 肖申克是如何被救赎的们 </a>
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse">
						<div class="panel-body">
							<p style=" font-size:12px;">肖申克公映20周年际，名利场访到一众人等来了篇全情纪要。
								这边厢粗略编译下，以纪念这部从录像带到VCD再到DVD，对我的迷影路留下不可磨灭痕迹的电</p>
						</div>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a style="color:#4637C5;" data-toggle="collapse"
								data-toggle="collapse" data-parent="#accordion"
								href="#collapseFour"> 其他的都可有可无 </a>
						</h4>
					</div>
					<div id="collapseFour" class="panel-collapse collapse">
						<div class="panel-body">
							<p style=" font-size:12px;">最近一次回国，我把国内家里放着的那一大堆漫画都带了过来。</p>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a style="color:#4637C5;" data-toggle="collapse"
								data-toggle="collapse" data-parent="#accordion"
								href="#collapseFive"> 为什么你的时间总是不够用？ </a>
						</h4>
					</div>
					<div id="collapseFive" class="panel-collapse collapse" style="height: 300px;">
						<div class="panel-body">
							<p style=" font-size:12px;">每次上微信，就有小盆友抱怨“时间不够用的”。这是一个很值得思考的现象。
								每个人都只有24小时，为什么有人在24小时里就能干出轰轰烈烈的事情，有的人24小时却什么</p>
						</div>
					</div>
				</div>

			</div>

		</div>
		<!--中间-->
		<div class="hot_right" style="float: left;margin-top: 27px;">
			<!--右边-->
			<img src="lsr/img/2014-10-09_092433.png"><br>
			<br> 线上活动.......&nbsp;&nbsp;&nbsp;(<a href="#">更多</a>)<br>
			<a href="#">【有奖活动】晒你曾经单曲循环的那些歌</a><br> 时间：9月11日 - 10月15日<br>
			1173人参加<br> <a href="#">我的百日读书单</a><br> 时间：9月15日 - 12月14日<br>
			1670人参加<br> <a href="#">【有奖活动】写下你对未来自己的憧憬</a><br> 时间：9月30日
			- 10月30日 <br> 1316人参加<br> 品牌<a href="#"> 穿越时光，你是我的The
				One</a><br> 时间：9月1日-11月30日<br> 592人参加
		</div>
		<!--右边-->
	</div>
	<!--热点内容-->
	<!--///////////////////////////////////////////////////////////////////////////////热点内容//-->
	<div id="dongxi">
		<!--东西-->
		<div class="dongxi_left">
			<!--左边-->
			<p style="font-size:24px; padding-top:20px;color: #DE533C;font-weight: 700;">东西</p>
			<a href="#" style="color:#A16B4E;">男士</a><br>
			<a style="color:#A16B4E;" href="#">女士</a><br>
			<a style="color:#A16B4E;" href="#">居家</a><br>
			<a style="color:#A16B4E;" href="#">数码</a><br>
			<a style="color:#A16B4E;" href="#">户外</a><br>
			<a href="#" style="color:#A16B4E;">美妆</a><br>
			<a href="#" style="color:#A16B4E;">食品</a><br>
			<a href="#" style="color:#A16B4E;">女装</a><br>
			<a href="#" style="color:#A16B4E;">男鞋</a><br>
			<a href="#" style="color:#A16B4E;">科技</a><br>
			<a href="#" style="color:#A16B4E;">创意</a><br>
			<a href="#" style="color:#A16B4E;">果壳</a><br>
			<a href="#" style="color:#A16B4E;">知乎</a><br>
			<a href="#" style="color:#A16B4E;">家居</a><br>
			<a href="#" style="color:#A16B4E;">艺术</a><br>
			<img src="lsr/img/7.png"><br>
			<a href="#" style="color:#A16B4E;">关注我们</a>

		</div>
		<!--左边-->
		<div class="dongxi_be">
			<!--中间-->
			<p style="padding-top:20px; font-size:18px">
				热门东西...... &nbsp;&nbsp;&nbsp;(<a href="#">更多</a>)
			</p>
			<div style="width:50%; float:left;">
				<img src="lsr/img/e.png"><br> <img src="lsr/img/f.png">
			</div>
			<div style="width:50%; float:left; position:relative;">
				<div style="width:50%; float:left;">
					<img src="lsr/img/e1.png"><br>
					<a href="#" style="color:#A16B4E">瓢虫项链</a><br> <img
						src="lsr/img/e3.png"><br>
					<a href="#" style="color:#A16B4E">CPB粉底霜</a><br>
					<br>
					<br> <img src="lsr/img/f1.png"><br>
					<a href="#" style="color:#A16B4E">复古肩包</a><br> <img
						src="lsr/img/f3.png"><br>
					<a href="#" style="color:#A16B4E">黄铜票据夹</a>
				</div>
				<div style="width:50%; float:left; position:relative;">
					<img src="lsr/img/e2.png"><br>
					<a href="#" style="color:#A16B4E">猫咪钱包</a><br> <img
						src="lsr/img/e4.png"><br>
					<a href="#" style="color:#A16B4E">三七花引</a><br>
					<br>
					<br> <img src="lsr/img/f2.png"><br>
					<a href="#" style="color:#A16B4E">原木餐桌</a><br> <img
						src="lsr/img/f4.png"><br>
					<a href="#" style="color:#A16B4E">萌猫地毯</a>
				</div>
			</div>
		</div>
		<!--中间-->
		<div class="dongxi_right">
			<!--右边-->
			<div style=" height:177px;">
				<img src="lsr/img/g.png" style="padding-top:43px;">
			</div>
			<p style="font-size:16px; color:#007722">
				热门图文......&nbsp;&nbsp;&nbsp;(<a href="#">更多</a>)
			</p>

			<a href="#" style="color:#A16B4E;">各大热卖无硅洗护试用报告（一）入门篇</a>
			<div>
				<div class="dongxi_right_left">咳咳。我叫Lulu，是一艘资深潜水艇，也是一名美发控。对于最近火热得不行的无硅洗发水，...</div>
				<div class="dongxi_right_right">
					<img src="lsr/img/m.png"><br>
					<br>
				</div>
			</div>
			<a href="#" style="color:#A16B4E;">各大热卖无硅洗护亲身试用报告（二）进阶篇</a>
			<div>
				<div class="dongxi_right_left">开始前的言： 各位神样的豆哥豆姐们~，Lulu只是个小逗比啦。
					试用是要时间体验嘀~，用个...</div>
				<div class="dongxi_right_right">
					<img src="lsr/img/q.png"><br>
					<br>
				</div>
			</div>
			<a href="#" style="color:#A16B4E;">如何给女朋友/普通女性朋友购买礼物</a>
			<div>
				<div class="dongxi_right_left">【本文内所有链接皆为曲奇东西上随便找的现有链接，买礼物涉及皮肤方面的最好正规专柜...</div>
				<div class="dongxi_right_right">
					<img src="lsr/img/w.png"><br>
					<br>
				</div>
			</div>
			<br> <a href="#" style="color:#A16B4E;">长草美妆＋护肤品一网打尽❤︎试用点...</a>
			<div>
				<div class="dongxi_right_left">来美帝的时间不长，但是对这里便宜又好用的化妆品护肤品长草已久！像在国内大红的fresh...</div>
				<div class="dongxi_right_right">
					<img src="lsr/img/r.png">
				</div>
			</div>

		</div>
		<!--右边-->
	</div>
	<!--东西-->
	<div class="dongxi_bottom" align="center">
		<!--底部-->
		<img src="lsr/img/h.png">
	</div>
	<!--底部-->
	<!--//////////////////////////////////////////////////////////////////////////////////东西////-->
	<div id="movies">
		<!--电影-->
		<div class="dongxi_left">
			<!--左边-->
			<p style="font-size:24px; padding-top:20px;color: #01ADDD;">电影</p>
			<a href="#">影讯 & 购票</a><br>
			<a href="#">选电影</a><br>
			<a href="#">电视剧</a><br>
			<a href="#">排行榜</a><br>
			<a href="#">分类</a><br>
			<a href="#">影评</a><br>
			<a href="#">导演</a><br>
			<a href="#">猛男</a><br>
			<a href="#">靓女</a><br>
			<a href="#">小清新</a><br>
			<a href="#">重口味</a><br>
			<a href="#">冷门</a><br>
			<img src="lsr/img/j.png"><br>
			<a href="#">曲奇电影</a>

		</div>
		<!--左边-->
		<div class="dongxi_be">
			<!--中间-->
			<p style="padding-top:20px; font-size:18px">
				正在热映...... &nbsp;&nbsp;&nbsp;(<a href="#">更多</a>)
			</p>
			<table class="dongxi_be_photo">
				<tr style="width:130px">
					<td class="movies_td"><div>
							<img src="lsr/img/a1.png">
						</div>
						<div>
							<a href="#" style="padding-left:20px;">心花路放</a>
						</div>
						<div>
							<span class="allstar40">☆☆☆☆☆</span><i>7.2</i>
						</div></td>
					<td class="movies_td">
						<div>
							<img src="lsr/img/a2.png">
						</div>
						<div>
							<a href="#" style="padding-left:20px;">银河护卫队</a>
						</div>
						<div>
							<span class="allstar40">☆☆☆☆☆</span><i>8.1</i>
						</div>
					</td>
					<td class="movies_td">
						<div>
							<img src="lsr/img/a3.png">
						</div>
						<div>
							<a href="#" style="padding-left:20px;">食人虫</a>
						</div>
						<div>
							<span class="allstar40">☆☆☆☆☆</span><i>3.4</i>
						</div>
					</td>
					<td class="movies_td">
						<div>
							<img src="lsr/img/a4.png">
						</div>
						<div>
							<a href="#" style="padding-left:20px;">我的青春密友</a>
						</div>
						<div>
							<span class="allstar40">☆☆☆☆☆</span><i>7.4</i>
						</div>
					</td>
				</tr>
				<tr style="width:130px">
					<td class="movies_td">
						<div>
							<img src="lsr/img/a5.png">
						</div>
						<div>
							<a href="#" style="padding-left:20px;">心花路放</a>
						</div>
						<div>
							<span class="allstar40">☆☆☆☆☆</span><i>7.2</i>
						</div>
					</td>
					<td class="movies_td">
						<div>
							<img src="lsr/img/a6.png">
						</div>
						<div>
							<a href="#" style="padding-left:20px;">亲爱的</a>
						</div>
						<div>
							<span class="allstar40">☆☆☆☆☆</span><i>8.1</i>
						</div>
					</td>
					<td class="movies_td">
						<div>
							<img src="lsr/img/a7.png">
						</div>
						<div>
							<a href="#" style="padding-left:20px;">痞子英雄2</a>
						</div>
						<div>
							<span class="allstar40">☆☆☆☆☆</span><i>8.1</i>
						</div>
					</td>
					<td class="movies_td">
						<div>
							<img src="lsr/img/a8.png">
						</div>
						<div>
							<a href="#" style="padding-left:10px;">麦兜我和我妈妈</a>
						</div>
						<div>
							<span class="allstar40">☆☆☆☆☆</span><i>8.1</i>
						</div>
					</td>
			</table>

		</div>
		<!--中间-->
		<div class="dongxi_right">
			<!--右边-->
			<p style="padding-top:20px; font-size:18px">
				影片分类...... &nbsp;&nbsp;&nbsp;(<a href="#">更多</a>)
			</p>
			<tr>
				<td><a href="#" style="width:70px; font-size:14px;">爱情 </a></td>
				<td><a href="#" style="width:70px; font-size:14px;">喜剧</a></td>
				<td><a href="#" style="width:70px; font-size:14px;"> 动画 </a></td>
				<td><a href="#" style="width:70px; font-size:14px;">科幻 经典</a></td>
				<td><a href="#" style="width:70px; font-size:14px;"> 经典</a></td>
			</tr>
			<p style="padding-top:20px; font-size:18px">
				近期热门...... &nbsp;&nbsp;&nbsp;(<a href="#">更多</a>)
			</p>
			<ul>
				<li><a href="#" style="font-size:14px;">罪恶之城2
						<hr>
				</a></li>
				<li><a href="#" style="font-size:14px;">彗星来的那一夜
						<hr>
				</a></li>
				<li><a href="#" style="font-size:14px;">超体</a>
				<hr></li>
				<li><a href="#" style="font-size:14px;">歌曲改变人生</a>
				<hr></li>
				<li><a href="#" style="font-size:14px;">摩纳哥王妃</a>
				<hr></li>
				<li><a href="#" style="font-size:14px;">弗兰克</a>
				<hr></li>
			</ul>

		</div>
		<!--右边-->

	</div>
	<!--电影-->
	<div class="dongxi_bottom" align="center">
		<!--底部-->
		<img src="lsr/img/x.png">
	</div>
	<!--底部-->
	<!--/////////////////////////////////////////////////////////////////////////////////电影///-->
	<div id="read">
		<!--读书-->
		<div class="dongxi_left">
			<!--左边-->
			<p style="font-size:24px; padding-top:20px;color: #4A2F14;">读书</p>
			<a href="#" style="color:#4A2F14;">分类浏览</a><br>
			<a style="color:#4A2F14;" href="#">阅读</a><br>
			<a style="color:#4A2F14;" href="#">作者</a><br>
			<a style="color:#4A2F14;" href="#">书评</a><br>
			<a style="color:#4A2F14;" href="#">购书单</a><br>
			<a style="color:#4A2F14;" href="#">亚马逊</a><br>
			<a style="color:#4A2F14;" href="#">译者</a><br>
			<a style="color:#4A2F14;" href="#">私人定制</a><br>
			<img src="lsr/img/yuedu.png"><br>
			<a href="#" style="color:#4A2F14;">曲奇阅读</a><br>
			<img src="lsr/img/yuedu2.png"><br>
			<a href="#" style="color:#4A2F14;">曲奇笔记</a><br>
			<img src="lsr/img/yuedu3.png"><br>
			<a href="#" style="color:#4A2F14;">曲奇购物单</a>
		</div>
		<!--左边-->
		<div class="dongxi_be">
			<!--中间-->
			<p style="padding-top:20px; font-size:18px" style="color:#007722">
				新书速递 ...... (<a href="#" style="color:#017A85">更多</a>)
			</p>
			<table class="dongxi_be_photo">
				<tr style="width:130px">
					<td class="movies_td"><div>
							<img src="lsr/img/r1.png">
						</div>
						<div>
							<a href="#" style="color:#614E3C">遇见孩子，遇见...</a>
						</div>
						<div>
							<span>〔美〕赛西·高...</span>
						</div>
						<div align="center"
							style="width:68px; height:24px; background:#999A95;">
							<a href="#" style="color:#F8F3F3">免费试读</a>
						</div></td>
					<td class="movies_td">
						<div>
							<img src="lsr/img/r2.png">
						</div>
						<div>
							<a href="#" style="color:#614E3C">步行者日记</a>
						</div>
						<div>
							<span>〔美〕唐纳德·...</span>
						</div>
						<div align="center"
							style="width:68px; height:24px; background:#999A95;">
							<a href="#" style="color:#F8F3F3">免费试读</a>
						</div>
					</td>
					<td class="movies_td">
						<div>
							<img src="lsr/img/r3.png">
						</div>
						<div>
							<a href="#" style="color:#614E3C">魔戒（全集）</a>
						</div>
						<div>
							<span>〔英〕托尔金(...</span>
						</div>
						<div align="center"
							style="width:68px; height:24px; background:#999A95;">
							<a href="#" style="color:#F8F3F3">免费试读</a>
						</div>
					</td>
					<td class="movies_td">
						<div>
							<img src="lsr/img/r4.png">
						</div>
						<div>
							<a href="#" style="color:#614E3C">我们都是爱过的...</a>
						</div>
						<div>
							<span>王臣</span>
						</div>
						<div align="center"
							style="width:68px; height:24px; background:#999A95;">
							<a href="#" style="color:#F8F3F3">免费试读</a>
						</div>
					</td>
				</tr>

			</table>

			<p style="padding-top:20px; font-size:18px" style="color:#007722">
				原创数字作品...... (<a href="#" style="color:#017A85">更多</a>)
			</p>
			<table class="dongxi_be_photo">
				<tr style="width:130px">
					<td class="movies_td">
						<div>
							<img src="lsr/img/s1.png">
						</div>
						<div>
							<a href="#" style="color:#614E3C">有爱我们床上谈...</a>
						</div>
						<div>
							<span>大给</span>
						</div>
						<div align="center"
							style="width:68px; height:24px; background:#999A95;">
							<a href="#" style="color:#F8F3F3">免费试读</a>
						</div>
					</td>
					<td class="movies_td">
						<div>
							<img src="lsr/img/s2.png">
						</div>
						<div>
							<a href="#" style="color:#614E3C">芬妮·希尔：欢...</a>
						</div>
						<div>
							<span>〔英〕约翰·克利兰</span>
						</div>
						<div align="center"
							style="width:68px; height:24px; background:#999A95;">
							<a href="#" style="color:#F8F3F3">免费试读</a>
						</div>
					</td>
					<td class="movies_td">
						<div>
							<img src="lsr/img/s3.png">
						</div>
						<div>
							<a href="#" style="color:#614E3C">拖延症再见：知...</a>
						</div>
						<div>
							<span>动机在杭州</span>
						</div>
						<div align="center"
							style="width:68px; height:24px; background:#999A95;">
							<a href="#" style="color:#F8F3F3">免费试读</a>
						</div>
					</td>
					<td class="movies_td">
						<div>
							<img src="lsr/img/s4.png">
						</div>
						<div>
							<a href="#" style="color:#614E3C">开放的智力：知...</a>
						</div>
						<div>
							<span>采铜</span>
						</div>
						<div align="center"
							style="width:68px; height:24px; background:#999A95;">
							<a href="#" style="color:#F8F3F3">免费试读</a>
						</div>
					</td>
				</tr>
			</table>

		</div>
		<!--中间-->
		<div class="dongxi_right">
			<!--右边-->
			<p style="padding-top:20px; font-size:18px">
				热门标签 ...... (<a href="#">更多</a>)
			</p>
			<br> [文学]<a href="#">小说</a> <a href="#">随笔</a> <a href="#">散文</a><a
				href="#"> 日本文学</a> <a href="#">童话</a> <a href="#">诗歌</a> <a href="#">名著</a>
			<a href="#">港台</a> <a href="douban_classify.html">更多</a>
			<hr>
			[流行]<a href="#">漫画 </a> <a href="#"> 绘本</a> <a href="#"> 推理</a><a
				href="#"> 青春 </a> <a href="#">言情 </a> <a href="#">科幻</a> <a href="#">
				武侠</a> <a href="#"> 奇幻</a> <a href="douban_classify.html">更多</a>
			<hr>
			[文化]<a href="#">历史 </a> <a href="#">哲学</a> <a href="#"> 传记 </a><a
				href="#"> 设计</a> <a href="#"> 建筑 </a> <a href="#">电影 </a> <a
				href="#">回忆录</a> <a href="#"> 音乐</a> <a href="douban_classify.html">更多</a>
			<hr>
			[生活]<a href="#">旅行 </a> <a href="#"> 励志</a> <a href="#"> 职场</a><a
				href="#"> 美食</a> <a href="#"> 教育</a> <a href="#"> 灵修</a> <a href="#">
				健康</a> <a href="#"> 家居</a> <a href="douban_classify.html">更多</a>
			<hr>
			[经管]<a href="#">经济学</a> <a href="#"> 管理 </a> <a href="#">金融</a><a
				href="#"> 商业 </a> <a href="#">营销</a> <a href="#"> 理财</a> <a href="#">
				股票</a> <a href="#"> 企业史</a> <a href="douban_classify.html">更多</a>
			<hr>
			[科技]<a href="#">科普 </a> <a href="#"> 互联网</a> <a href="#"> 编程</a><a
				href="#"> 交互设计</a> <a href="#"> 算法 </a> <a href="#">通信</a> <a
				href="#"> 神经网络</a> <a href="douban_classify.html">更多</a>
			<hr>



		</div>
		<!--右边-->
	</div>
	<!--读书-->
	<!--/////////////////////////////////////////////////////////////////////////////////读书//-->
	<div id="music">
		<!--音乐-->
		<div class="dongxi_left">
			<!--左边-->
			<p style="font-size:24px; padding-top:20px; color:#F38C00;font-weight: 600;">音乐</p>
			<a href="#">音乐人</a><br>
			<a href="#">金羊毛计划</a><br>
			<a href="#">节目</a><br>
			<a href="#">排行榜</a><br>
			<a href="#">摇滚</a><br>
			<a href="#">流行</a><br>
			<a href="#">嘻哈</a><br>
			<a href="#">HipPop</a><br>
			<a href="#">独立</a><br>
			<a href="#">厂牌</a><br>
			<a href="#">北欧</a><br>
			<a href="#">日韩</a><br>
			<a href="#">分类浏览</a><br>
			<br>
			<img src="lsr/img/k.png"><br>
			<a href="#">曲奇音乐人</a>
		</div>
		<!--左边-->
		<div class="dongxi_be">
			<!--中间-->
			<p style="padding-top:20px; font-size:18px" style="color:#007722">
				曲奇新碟榜 ...... (<a href="#" style="color:#017A85">更多</a>)
			</p>
			<table class="dongxi_be_photo">
				<tr style="width:130px">
					<td class="music_td">
						<div>
							<img src="lsr/img/m1.png">
						</div>
						<div>
							<a href="#">1. 適婚的年齡</a>
						</div>
						<div>
							<span>My Little Airport</span>
						</div>
						<div>
							<span class="allstar40">☆☆☆☆☆</span><i>7.2</i>
						</div>
					</td>
					<td class="music_td">
						<div>
							<img src="lsr/img/m2.png">
						</div>
						<div>
							<a href="#">2. 南山南</a>
						</div>
						<div>
							<span>马頔</span>
						</div>
						<div>
							<span class="allstar40">☆☆☆☆☆</span><i>7.2</i>
						</div>
					</td>
					<td class="music_td">
						<div>
							<img src="lsr/img/m3.png">
						</div>
						<div>
							<a href="#">3. 说时依旧</a>
						</div>
						<div>
							<span>好妹妹乐队</span>
						</div>
						<div>
							<span class="allstar40">☆☆☆☆☆</span><i>7.2</i>
						</div>
					</td>
					<td class="music_td">
						<div>
							<img src="lsr/img/m4.png">
						</div>
						<div>
							<a href="#">4. Kontinue</a>
						</div>
						<div>
							<span>谢安琪</span>
						</div>
						<div>
							<span class="allstar40">☆☆☆☆☆</span><i>7.2</i>
						</div>
					</td>
				</tr>
				<tr style="width:130px">
					<td class="music_td">
						<div>
							<img src="lsr/img/m5.png">
						</div>
						<div>
							<a href="#">5. I Don't Want To Change You</a>
						</div>
						<div>
							<span>Damien Rice/span>
						</div>
						<div>
							<span class="allstar40">☆☆☆☆☆</span><i>7.2</i>
						</div>
					</td>
					<td class="music_td">
						<div>
							<img src="lsr/img/m6.png">
						</div>
						<div>
							<a href="#">6. Tomorrow's Modern Boxes</a>
						</div>
						<div>
							<span>Thom Yorke</span>
						</div>
						<div>
							<span class="allstar40">☆☆☆☆☆</span><i>7.2</i>
						</div>
					</td>
					<td class="music_td">
						<div>
							<img src="lsr/img/m7.png">
						</div>
						<div>
							<a href="#">7. The Last Dawn</a>
						</div>
						<div>
							<span>Mono</span>
						</div>
						<div>
							<span class="allstar40">☆☆☆☆☆</span><i>7.2</i>
						</div>
					</td>
					<td class="music_td">
						<div>
							<img src="lsr/img/m8.png">
						</div>
						<div>
							<a href="#">8. ありきたりな女</a>
						</div>
						<div>
							<span>椎名林檎</span>
						</div>
						<div>
							<span class="allstar40">☆☆☆☆☆</span><i>7.2</i>
						</div>
					</td>
			</table>

			<p style="padding-top:20px; font-size:18px" style="color:#007722">
				热门节目...... (<a href="#" style="color:#017A85">更多</a>)
			</p>
			<table>
				<tr style="width:130px">
					<td style="width:150px">
						<div>
							<a href="#"><img src="lsr/img/m11.png"></a>
						</div>
						<div>
							<span>那些唱到我心底的民谣</span>
						</div>
					</td>
					<td style="width:150px">
						<div>
							<a href="#"><img src="lsr/img/m22.png"></a>
						</div>
						<div>
							<span>治愈系</span>
						</div>
					</td>
					<td style="width:150px">
						<div>
							<a href="#"><img src="lsr/img/m33.png"></a>
						</div>
						<div>
							<span>12 首 Coldplay 極品單曲</span>
						</div>
					</td>
					<td style="width:150px">
						<div>
							<a href="#"><img src="lsr/img/m44.png"></a>
						</div>
						<div>
							<span>安和桥北-宋冬野</span>
						</div>
					</td>
				</tr>
			</table>

		</div>
		<!--中间-->
		<div class="dongxi_right">
			<!--右边-->
			<img src="lsr/img/m55.png"><br>
			<p style="padding-top:20px; font-size:18px">
				本周流行音乐人 ......(<a href="#">更多</a>)
			</p>
			<ul>
				<li>
					<div
						style="float:left; position:relative; width:55px; height:50px;">
						<img src="lsr/img/q1.png">
					</div>
					<div>
						好妹妹<br>
						<p style="color:#999999">
							流派: 民谣 Folk<br>78686人关注
						</p>
					</div>
					<br>
				</li>
				<li>
					<div
						style="float:left; position:relative; width:55px; height:50px;">
						<img src="lsr/img/q2.png">
					</div>
					<div>
						李志 <br>
						<p style="color:#999999">
							流派: 摇滚 Rock <br>10678人关注
						</p>
					</div>
					<br>
				</li>
				<li>
					<div
						style="float:left; position:relative; width:55px; height:50px;">
						<img src="lsr/img/q3.png">
					</div>
					<div>
						金玟岐_Vanessa<br>
						<p style="color:#999999">
							流派: 流行 Pop <br>78686人关注
						</p>
					</div>
					<br>
				</li>
				<li>
					<div
						style="float:left; position:relative; width:55px; height:50px;">
						<img src="lsr/img/q4.png">
					</div>
					<div>
						花粥<br>
						<p style="color:#999999">
							流派: 民谣 Folk <br>128620人关注
						</p>
					</div>
					<br>
				</li>
				<li>
					<div
						style="float:left; position:relative; width:55px; height:50px;">
						<img src="lsr/img/q5.png">
					</div>
					<div>
						袁惟仁<br>
						<p style="color:#999999">
							流派: 民谣 Folk <br>1064人关注
						</p>
					</div>
					<br>
				</li>

			</ul>

		</div>
		<!--右边-->

	</div>
	<!--音乐-->
	<div class="dongxi_bottom" align="center">
		<!--底部-->
		<img src="lsr/img/v.png">
	</div>
	<!--底部-->
	<!--///////////////////////////////////////////////////////////////////////////////////音乐////-->
	<div id="topic">
		<!--小组-->
		<div class="dongxi_left">
			<!--左边-->
			<p style="font-size:24px; padding-top:20px; color:#017A85">小组</p>
			<a href="#" style="color:#017A85;">精选</a><br>
			<a style="color:#017A85;" href="#">文化</a><br>
			<a style="color:#017A85;" href="#">行摄</a><br>
			<a style="color:#017A85;" href="#">娱乐</a><br>
			<a style="color:#017A85;" href="#">时尚</a><br>
			<a href="#" style="color:#017A85;">生活</a><br>
			<a href="#" style="color:#017A85;">科技</a><br>
			<a href="#" style="color:#017A85;">IT</a><br>
			<a href="#" style="color:#017A85;">Geek</a><br>
			<a href="#" style="color:#017A85;">言论</a><br>
			<br>
			<img src="lsr/img/22.png"><br>
			<a href="#" style="color:#017A85;">曲奇小组</a>
		</div>
		<!--左边-->
		<div class="dongxi_be">
			<!--中间-->
			<p style="padding-top:20px; font-size:18px">
				热门小组...... (<a href="#">更多</a>)
			</p>

			<div class="topic_left">
				<ul>
					<li style="list-style:none;float: left;width: 400px;">
						<div
							style="width:54px; height:80px; float:left; position:relative;">
							<img src="lsr/img/t1.png">
						</div>
						<div>
							<a href="#">没有厨房我也爱做饭</a><br>53818 个成员
						</div>
						<br>
					<br>
					</li>
					<li style="list-style:none;float: left;width: 400px;">
						<div
							style="width:54px; height:80px; float:left; position:relative;">
							<img src="lsr/img/t2.png">
						</div>
						<div>
							<a href="#">像一位孤独的诗人</a><br>33957 个成员
						</div>
						<br>
					<br>
					</li>
					<li style="list-style:none;float: left;width: 400px;">
						<div
							style="width:54px; height:80px; float:left; position:relative;">
							<img src="lsr/img/t3.png">
						</div>
						<div>
							<a href="#">破越古怪问题研究所</a><br>82898 个成员
						</div>
						<br>
					<br>
					</li>
					<li style="list-style:none;float: left;width: 400px;">
						<div
							style="width:54px; height:80px; float:left; position:relative;">
							<img src="lsr/img/t4.png">
						</div>
						<div>
							<a href="#">实况足球</a><br>10029 个成员
						</div>
						<br>
					<br>
					</li>
					<li style="list-style:none;float: left;width: 400px;">
						<div
							style="width:54px; height:80px; float:left; position:relative;">
							<img src="lsr/img/t5.png">
						</div>
						<div>
							<a href="#">科学松鼠会读者花园</a><br>133050 个成员
						</div>
						<br>
					<br>
					</li>
					<li style="list-style:none;float: left;width: 400px;">
						<div
							style="width:54px; height:80px; float:left; position:relative;">
							<img src="lsr/img/t6.png">
						</div>
						<div>
							<a href="#">哦？地球上竟然还有这种事？！</a><br>16456 个成员
						</div>
						<br>
					<br>
					</li>
				</ul>
			</div>
			<div class="topic_right">
				<ul>
					<li style="list-style:none;float: left;width: 400px;">
						<div
							style="width:54px; height:80px; float:left; position:relative;">
							<img src="lsr/img/t11.png">
						</div>
						<div>
							<a href="#">课文回忆录</a><br>14945 个成员
						</div>
						<br>
					<br>
					</li>
					<li style="list-style:none;float: left;width: 400px;">
						<div
							style="width:54px; height:80px; float:left; position:relative;">
							<img src="lsr/img/t22.png">
						</div>
						<div>
							<a href="#">我想开书店</a><br>27503 个成员
						</div>
						<br>
					<br>
					</li>
					<li style="list-style:none;float: left;width: 400px;">
						<div
							style="width:54px; height:80px; float:left; position:relative;">
							<img src="lsr/img/t33.png">
						</div>
						<div>
							<a href="#">减肥餐设计与体型管理</a><br>176784 个成员
						</div>
						<br>
					<br>
					</li>
					<li style="list-style:none;float: left;width: 400px;">
						<div
							style="width:54px; height:80px; float:left; position:relative;">
							<img src="lsr/img/t44.png">
						</div>
						<div>
							<a href="#">我要做漫画家！！</a><br>24584 个成员
						</div>
						<br>
					<br>
					</li>
					<li style="list-style:none;float: left;width: 400px;">
						<div
							style="width:54px; height:80px; float:left; position:relative;">
							<img src="lsr/img/t55.png">
						</div>
						<div>
							<a href="#">||---胶---片---日---记---||</a><br>10226 个成员
						</div>
						<br>
					<br>
					</li>
					<li style="list-style:none;float: left;width: 400px;">
						<div
							style="width:54px; height:80px; float:left; position:relative;">
							<img src="lsr/img/t66.png">
						</div>
						<div>
							<a href="#">我是测试狂</a><br>234134 个成员
						</div>
						<br>
					<br>
					</li>
				</ul>
			</div>

		</div>
		<!--中间-->
		<div class="dongxi_right">
			<!--右边-->
			<p style="padding-top:20px; font-size:18px">
				小组分类 ...... (<a href="#">更多</a>)
			</p>
			<br> 兴趣>><a href="#">旅行</a> <a href="#"> 摄影</a> <a href="#">
				影视 </a><a href="#"> 音乐</a> <a href="#"> 文学</a> <a href="#"> 游戏</a> <a
				href="#">动漫</a> <a href="#"> 运动</a> <a href="#"> 戏曲</a><a href="#">
				桌游</a><a href="#"> 怪癖</a> <br>
			<br> 生活>><a href="#">健康 </a> <a href="#"> 美食 </a> <a href="#">
				宠物</a><a href="#">美容</a> <a href="#"> 化妆</a> <a href="#"> 护肤 </a> <a
				href="#"> 服饰</a> <a href="#"> 公益 </a> <a href="#">家庭</a><a href="#">
				育儿</a><a href="#"> 汽车</a> <br>
			<br> 购物>><a href="#">淘宝 </a> <a href="#">二手</a> <a href="#">
				团购 </a><a href="#">数码</a> <a href="#"> 品牌 </a> <a href="#">文具 </a> <br>
			<br> 社会>><a href="#">求职 </a> <a href="#"> 租房</a> <a href="#">
				励志</a><a href="#"> 留学</a> <a href="#"> 出国</a> <a href="#">理财</a> <a
				href="#"> 传媒 </a> <a href="#"> 创业</a> <a href="#"> 考试</a> <br>
			<br> 艺术>><a href="#">设计</a> <a href="#"> 手工 </a> <a href="#">
				展览 </a><a href="#"> 曲艺 </a> <a href="#"> 舞蹈 </a> <a href="#"> 雕塑 </a> <a
				href="#"> 纹身</a> <br>
			<br> 学术>><a href="#">人文</a> <a href="#"> 社科 </a> <a href="#">
				自然</a><a href="#"> 建筑</a> <a href="#"> 国学 </a> <a href="#">语言 </a> <a
				href="#"> 宗教</a> <a href="#"> 哲学</a> <A href="#"> 软件</a><a href="#">
				硬件</a><a href="#"> 互联网</a> <br>
			<br> 情感>><a href="#">恋爱 </a> <a href="#"> 心情</a> <a href="#">
				心理学 </a><a href="#"> 星座 </a> <a href="#"> 塔罗 </a> <a href="#">LES </a> <a
				href="#"> GAY</a> <br>
			<br> 闲聊>><a href="#">吐槽 </a> <a href="#"> 笑话 </a> <a href="#">
				直播</a><a href="#"> 八卦 </a> <a href="#"> 发泄 </a> <br>
			<br>



		</div>
		<!--右边-->
	</div>
	<!--小组-->
	<!--///////////////////////////////////////////////////////////////////////////////////////小组/-->
	<div id="city">
		<!--同城-->
		<div class="city_left">
			<!--左边-->
			<p style="font-size:24px; padding-top:20px; color:#EC5303">同城</p>
			<a href="#" style="color:#664433;">同城活动</a><br>
			<a style="color:#664433;" href="#">主办方</a><br>
			<a style="color:#664433;" href="#">舞台剧</a><br>
			<br>
			<img src="lsr/img/l.png"><br>
			<a href="#" style="color:#664433;">关注我们</a>
		</div>
		<!--左边-->
		<div class="city_be">
			<!--中间-->
			<p style="padding-top:20px; font-size:18px">
				天津 · 本周热门活动......(<a href="#" style="color:#664433">更多</a>)
			</p>
			<div class="topic_left">
				<ul>
					<li style="list-style:none;float: left;width: 400px;">
						<div
							style="width:80px; height:100px; float:left; position:relative;">
							<img src="lsr/img/c1.png">
						</div>
						<div>
							<a href="#">【留一面墙 只挂原作】当代艺术展</a><br>
							<p style="font-size:12px;">
								9月27日 周六 - 10月19日 周日 <br>曲阜道38号友谊精品广场负...<br>25人关注
							</p>
						</div>
						<br>
					</li>
					<li style="list-style:none;float: left;width: 400px;">
						<div
							style="width:80px; height:100px; float:left; position:relative;">
							<img src="lsr/img/c1.png">
						</div>
						<div>
							<a href="#">北纬零度无厘头感人话</a><br>
							<p style="font-size:12px;">
								剧《名字没想好》9月10月@天津.... 9月6日 周六 - 10月26日 周日 <br>曹禺剧院<br>17人关注
							</p>
						</div>
						<br>
					</li>

				</ul>
			</div>
			<div class="topic_right">
				<ul>
					<li style="list-style:none;float: left;width: 400px;">
						<div
							style="width:80px; height:100px; float:left; position:relative;">
							<img src="lsr/img/c11.png">
						</div>
						<div>
							<a href="#">闻香识女人---电影音乐欣赏</a><br>
							<p style="font-size:12px;">
								10月17日 周五 20:00 - 21:30 <br>友谊北路3号 西岸艺术馆<br>9人关注
							</p>
						</div>
						<br>
					</li>
					<li style="list-style:none;float: left;width: 400px;">
						<div
							style="width:80px; height:100px; float:left; position:relative;">
							<img src="lsr/img/c22.png">
						</div>
						<div>
							<a href="#">儿童剧《小狗阿疤》</a><br>
							<p style="font-size:12px;">
								11月1日 周六 - 11月9日 周日<br> 文化中心 天津大剧院<br>3人关注
							</p>
						</div>
						<br>
					</li>

				</ul>
			</div>


		</div>
		<!--中间-->
		<div class="city_right">
			<!--右边-->
			<p style="padding-top:20px; font-size:18px">活动标签......</p>
			音乐>><br> <a href="#">小型现场</a> <a href="#"> 音乐会 </a> <a href="#">
				演唱会 </a><a href="#"> 音乐节 </a> <br> 戏剧>><br> <a href="#">话剧
			</a> <a href="#">音乐剧</a> <a href="#"> 舞剧</a><a href="#"> 歌剧 </a> <a
				href="#"> 戏曲 </a> <br> 聚会>><br> <a href="#">生活</a> <a
				href="#"> 摄影</a> <a href="#"> 外语</a><a href="#"> 桌游 </a> <a href="#">
				交友 </a> <a href="#">夜店 </a> <a href="#">集市</a> <br> 电影>><br> <a
				href="#">主题放映</a> <a href="#"> 影展 </a> <a href="#"> 影院活动</a> <br>
			其他>><br> <a href="#">讲座</a> <a href="#"> 展览 </a> <a href="#">
				运动 </a><a href="#">旅行 </a><a href="#">公益</a> <br>



		</div>
		<!--右边-->

	</div>
	<!--同城-->
	<div class="dongxi_bottom" align="center">
		<!--底部-->
		<img src="lsr/img/n.png">
	</div>
	<!--底部-->
</body>

</html>



