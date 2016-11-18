$(function(){
	var mycity = remote_ip_info['city'];
	$('#currentcity').html(mycity);
	$('#city option').each(function(index, element) {
		if ($(this).first().text() == mycity) {
			$('#sel').val($(this).val());
			$('#sel').text(mycity);
		}
		$('#sel').attr("selected", true);
	});
	$("#checkcode").click(function() {
		$("#checkcode").attr('src','/douban_user/user/validationServlet?random='+ new Date().toString());
	});

	//图片发布预览
	$('#uploadbroimg').change(function(event){
		var file =  event.target.files[0];
		preview(file);
	});
	
	$('#confirm').click(function(){
		if($('#this_id').val() == $('#curr_id').val()){
			document.modify.submit();
		}else{
			$('#mess').html("非当前登陆用户！");
		}
		
	});
});

//发图预览
function preview(file){
	var reader = new FileReader();
	reader.onload = function(e){
		var $img = $('<img>').attr("src", e.target.result);
		$('#preview2').empty().append($img);	
	};
	reader.readAsDataURL(file);  	
};