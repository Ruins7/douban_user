$(function(){
	
	var mycity = remote_ip_info['city'];
    
		//$('#currentcity').html(mycity);
		$('#city option').each(function(index, element) {
			if ($(this).first().text() == mycity) {
				$('#sel').val($(this).val());
				$('#sel').text(mycity);
			}
			$('#sel').attr("selected", true);
		});
	
	
	
	$.ajax({
		type : 'post',
		url : 'http://localhost:8080/douban_user/hyy/service/ActivityManageServlet' ,
		data : {
				method:"searchAll",
				bigType:$("#music").prev().text(),
				smallType:""
					},
		success : function(data , status , xhr ) {
			 
			$("#music_div").empty();
			var data1 = eval('('+data+')');
			$.each(data1 , function(index , value){   //value -- json
				var div1 = $("<div style='float:left; position:relative; width:74px; height:139px'><img style='width:71px;height:91px;' src="+value.imgs+"></div>");
				var div2 = $("<div style='float:left; position:relative;width:236px; height:139px;'></div>");
				var a = $("<a href='hyy/service/ActivityManageServlet?method=searchAOffActivity&id="+value.offactivity_id+"'>"+value.offactivity_title+"</a>");
				var p = $("<p style='font-size:12px; color:#666666'>"+value.position+"<br>"+value.attentionPerson.length+"人参加</p>");

				div2.append(a).append(p);
				$("#music_div").append(div1).append(div2);
			});
		}
	});
	
	
	
	$("#music li").click("on",function(){
		//alert($(this).children("a").children("span").text());
		$.ajax({
			type : 'post',
			url : 'http://localhost:8080/douban_user/hyy/service/ActivityManageServlet' ,
			data : {
					method:"searchAll",
					bigType:$("#music").prev().text(),
					smallType:$(this).children("a").children("span").text()
						},
			success : function(data , status , xhr ) {
				$("#music_div").empty();
				var data1 = eval('('+data+')');
				$.each(data1 , function(index , value){   //value -- json

					var div1 = $("<div style='float:left; position:relative; width:74px; height:139px'><img style='width:71px;height:91px;' src="+value.imgs+"></div>");
					var div2 = $("<div style='float:left; position:relative;width:236px; height:139px;'></div>");
					var a = $("<a href='hyy/service/ActivityManageServlet?method=searchAOffActivity&id="+value.offactivity_id+"'>"+value.offactivity_title+"</a>");
					var p = $("<p style='font-size:12px; color:#666666'>"+value.position+"<br>"+value.attentionPerson.length+"人参加</p>");

					div2.append(a).append(p);
					$("#music_div").append(div1).append(div2);
				});
			}
		});
	});
	
	$("#action").children("div").mouseover(function(){
			$(this).css("background-color","#FFD6D6").css("border-color","#EE2E2E");
			$(this).children("a").css("color","#FFFFFF").css("text-decoration","none");
	});
	
	
	$("#action").children("div").mouseleave(function(){
			$(this).children("div").css("background-color","#FDDADA").css("border-color","#FFA9AA");
			$(this).children("a").css("color","#FF484B");
	});
	
	$("#addDouList").click(function(event){
		event.preventDefault();
		$("#lastDiv").css("visibility","visible").css("top","41px").css("left","368px").css("width","500px").css("height","245px").css("background-color","#686868").css("position","fixed").css("border","10px solid rgba(0,0,0,0.1)").css("background-color","rgba(255,255,255,0.9)").css("border-radius","4px");
		
	});
	
	$("#addDouList").blur(function(){

	});
	
	$("#ask a").mouseover(function(){
		$(this).css("color","#FFFFFF").css("background-color","#000DFF");
	});
	
	$("#ask a").mouseleave(function(){
		$(this).css("color","#0024FF").css("background-color","#FFFFFF");
		});
});