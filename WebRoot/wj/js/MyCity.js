/**
 * 
 */
$(function(){
	   $("#city").change(function(){
		     var city1=$(this).val();
			$.ajax({
				type:'post',
	            url:'http://localhost:8080/douban_user/servlet/CinemaServlet',
	            data:{method:"findCinemasByCity",city:city1
	            	},
			    success:function(data,status,xhr){
			    	$("#cinema").empty();
			    	var data1 = eval('('+data+')'); 
			    		$.each(data1 , function(index , value){   //value -- json			
			    			var option = $("<option value='"+value.cinema_name+"' selected>"+value.cinema_name+"</option>");
			    			$("#cinema").append(option);
			    		});
			    }
			});
		   
		   });
	var mycity = remote_ip_info['city'];
		//$('#currentcity').html(mycity);
		$('#city option').each(function(index, element) {
			if ($(this).first().text() == mycity) {
				$('#sel').val($(this).val());
				$('#sel').text(mycity);
			}
			$('#sel').attr("selected", "selected");
		});
});