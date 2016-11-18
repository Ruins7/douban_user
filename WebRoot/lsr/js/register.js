//豆瓣用户模块的js
$(function() {
	 
	var mycity = remote_ip_info['city'];

		$('#currentcity').html(mycity);
		$('#city option').each(function(index, element) {
			if ($(this).first().text() == mycity) {
				$('#sel').val($(this).val());
				$('#sel').text(mycity);
			}
			$('#sel').attr("selected", true);
		});
		$("#checkcode").click(
				function() {$("#checkcode").attr('src','/douban_user/user/validationServlet?random='
				+ new Date().toString());
				});
		
		$('#zhuce').click(function(){
			document.register.submit();
		});
//$(function(){})结束


	 

});