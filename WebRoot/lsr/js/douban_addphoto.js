$(function(){
	//图片发布预览
	$('#uploadbroimg').change(function(event){
		var file =  event.target.files[0];
		preview2(file);
	 
	});
	
});

//发图预览
function preview2(file){

	var reader = new FileReader();
	reader.onload = function(e){
		var $img = $('<img width="250px" height="250px">').attr("src", e.target.result);
		$('#add_preview').empty().append($img);	
	};
	reader.readAsDataURL(file);  	
};