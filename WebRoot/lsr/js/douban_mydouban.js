$(function() {

	//修改desc
	$('#modify').click(function(event) {
		event.preventDefault();
		$('#user_desc').removeClass('whats_up').addClass('edit_whats_up');
	});
	$('#user_desc').blur(function() {
		$('#user_desc').removeClass('edit_whats_up').addClass('whats_up');
		var uid = $('#uid').val();//获取当前用户id
		var udesc = $('#user_desc').val();//获取当前用户的desc
		var url = '/douban_user/user/userinfo?method=modifyUserDesc';
		$.ajax({
			type : 'post',
			url : url,
			dataType : 'json',
			data : {user_id:uid, user_desc:udesc},
			success : function(json, status, xhr) {
				if(json == true){
                	   alert("修改成功！");
                   }else{
                	   alert("修改失败！");
                   }
			},
			error : function(responseText, status, xhr) {
				//alert(responseText + "   " + status + "   " + xhr);
				alert("修改失败！");
			}
		});

	});//ajax
	
	//修改simple_desc
	$('#modify_simple_desc').click(function(event) {
		event.preventDefault();
		if($('#simple_desc').css('display') != "none"){
			//保存提交
			var uid = $('#uid').val();//获取当前用户id
			var simpledesc = $('#simple_desc').val();//获取当前用户的simple_desc
			var url = '/douban_user/user/userinfo?method=modifyUserSimpleDesc';
			$.ajax({
				type : 'post',
				url : url,
				dataType : 'json',
				data : {user_id:uid, simple_desc:simpledesc},
				success : function(json, status, xhr) {	 
					if(json != false){
	                	   alert("修改成功！"+json);
	                	   $('#show_simple_desc').html(simpledesc);
	                	   $('#simple_desc').hide();
	           			   $('#show_simple_desc').show();
	                   }else{
	                	   alert("修改失败！");  
	                   }
				},
				error : function(responseText, status, xhr) {
					//alert(responseText + "   " + status + "   " + xhr);
					alert("修改失败！");
				}
		});//ajax
	
		}else if($('#simple_desc').css('display') == "none"){
			//显示开始修改
			$('#simple_desc').show();
			$('#simple_desc').val($('#show_simple_desc').first().html());
			$('#show_simple_desc').hide();
		}
	});
	
	//好友留言
	$('#sendleavemessage').click(function() {
			var uid = $('#uid').val();//获取当前被留言用户id
			var leavemessage = $('#leavemessage').val();//获取留言内容
			var url = '/douban_user/user/userinfo?method=sendLeaveMessage';
			$.ajax({
				type : 'post',
				url : url,
				dataType : 'json',
				data : {to_user:uid, content:leavemessage},
				success : function(json, status, xhr) {
					if(json != false){
	                	   alert("留言成功！");
	                	   $('#leavemessage').val("");
	                	   //显示刚才的留言信息（待做）
	                	     
	                   }else{
	                	   alert("留言失败！");  
	                   }
				},
				error : function(responseText, status, xhr) {
					//alert(responseText + "   " + status + "   " + xhr);
					alert("修改失败！");
				}
		});
	});//ajax
	
	
	//删除留言
	$('#deletemess').click(function(event) {
		event.preventDefault();
		var messid = $('#messid').val();//获取留言id
		var url = '/douban_user/user/userinfo?method=delLeaveMessage';
		$.ajax({
			type : 'post',
			url : url,
			dataType : 'json',
			data : {message_id:messid},
			success : function(json, status, xhr) {
				if(json != false){
					alert("删除成功！");
					$('#mess'+messid).remove();
					$('#mess'+messid+'l').remove();
					$('#mess'+messid+'h').remove();
				}else{
					alert("删除失败！");  
				}
			},
			error : function(responseText, status, xhr) {
				//alert(responseText + "   " + status + "   " + xhr);
				alert("修改失败！");
			}
		});
	});//ajax
	
	$('.invo').click(function(e){
		e.preventDefault();
	});

});
