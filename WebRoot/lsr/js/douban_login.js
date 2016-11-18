$(function(){
	$("#checkcode").click(
			function() {$("#checkcode").attr('src','/douban_user/user/validationServlet?random='
			+ new Date().toString());
			});
	
	$('#loginimg').mousemove(function(){
		$('#loginimg').attr('src','lsr/img/focuslogin.png');		
	});
	$('#loginimg').mouseout(function(){
		$('#loginimg').attr('src','lsr/img/login.png');		
	});
	$('#loginimg').click(function(){//登陆
		document.loginform.submit();
	});
	
});