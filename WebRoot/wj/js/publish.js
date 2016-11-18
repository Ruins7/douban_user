// JavaScript Document

$(function(){
	$("#conent_input1").click(function(){
		alert("点击了");
		
		var contents = $("#conent_div");//获取的是全部的div
		var word = $('#conent').val();//获得发布的内容
		
		//存入的预存框架
		var node = "<div id='publish_1'><div class='c_1' id='div_1' style='width:100px;position:relative;float:left;left:5px;'><img src='img/ali.jpg' width='100px' height='80px'></div><div class='c_2' id='div_2' style='height:80px;top:5px; padding:5px;margin:5px;word-break:break-all;overflow:hidden;' ><span>用户名:架客机当地时间10日9时18分左右在德黑兰的梅赫拉巴德机起飞后坠毁。</span><br><span>时间</span></div></div><hr><br>";
		contents.prepend($(node));  //调用写好的框架
		});
		
		//限制字数
		
		$("#conent").keyup(function(){   
		   //keydown输入字
			//var count = $("#conent");  
			        
			var length = $('#conent').val().length;
	//		alert( length);
			var zishu = 120;              //规定输入的字数
			var zishu_count = zishu - length;  //剩余字数
		
			
			if(zishu_count<=0){
					$("conent").disabled = true;
					
					return;
				}
			else{
				$('#result') .html("您还能输入"+zishu_count+"个字");
				}
			                                               //result 还能输入多少字数
			
			});
		
		
	
	
	
	
	});