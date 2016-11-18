/*
 * 电影首页ajax分类和排序
 * 
 */

$(function(){
	
/*	   $("#city").change(function(){
		     var city1=$(this).val();
			$.ajax({
				type:'post',
	            url:'http://localhost:8080/douban_user//servlet/CinemaServlet',
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
		
		*/
		
	$.ajax({
		type:'post',
        url:'http://localhost:8080/douban_user/servlet/movieServlet',
        data:{method:"getMovieJsonByCount"},
	    success:function(data,status,xhr){
	    	$("#Rinking").empty();
	    	var data1 = eval('('+data+')'); 
	    		$.each(data1 , function(index , value){   //value -- json
	    			var li = $("<li style=' list-style:none;'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value[0]+"' style='font-size:14px;'>"+value[1]+"</a></li><hr style=' padding:3px; margin:8px;'>");
	    			$("#Rinking").append(li);
	    			});
	    }
	});
	
	//选电影：最热(按点击量movielist:count)
	$('#hot1').click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonByCount"},
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    	var data1 = eval('('+data+')'); 
		    		$.each(data1 , function(index , value){   //value -- json
		
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value[0]+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value[9]+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value[0]+"' style='font-size:12px; color:#000000'>"+value[1]+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	
	
	//最新(按上映时间)
	$("#newMovies").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonByDate"},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value.imgs+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	//经典movie_type:7
	$("#classics").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonType",type_id:7},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value.imgs+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	//动作movie_type:4
	$("#movement").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonType",type_id:4},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value.imgs+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	 
	//华语
	$("#china").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonDistrict",m_district:"中国"},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value.imgs+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	//欧美
	$("#america").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonDistrict",m_district:"美国"},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value.imgs+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	//韩国
	$("#korea").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonDistrict",m_district:"韩国"},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value.imgs+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	//日本
	$("#japan").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonDistrict",m_district:"日本"},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value.imgs+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	//喜剧type_id:8
	$("#comedy").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonType",type_id:8},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value.imgs+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	//爱情type_id:2
	$("#love").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonType",type_id:2},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value.imgs+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	//科幻type_id:6
	$("#scienceFiction").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonType",type_id:6},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value.imgs+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	//悬疑type_id:9
	$("#suspense").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonType",type_id:9},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value.imgs+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	//恐怖type_id:10
	$("#terror").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonType",type_id:10},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value.imgs+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	//文艺type_id:11
	$("#art").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonType",type_id:11},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value.imgs+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	//高分
	$("#highMark").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonByAvgScore"},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value.imgs+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	//排序：按热度
	$("#hot").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonByCount"},
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    	var data1 = eval('('+data+')'); 
		    		$.each(data1 , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value[0]+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value[9]+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value[0]+"' style='font-size:12px; color:#000000'>"+value[1]+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	
	
	//排序：按时间
	$("#date").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonByDate"},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value.imgs+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	
	//按得分
	$("#score").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonByAvgScore"},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#sort").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:140px; height:190px;margin-top:10px;float:left;position:relative;'></div>");
		    			var div2 = $("<div><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img style='width:110px;height:147px;' src="+value.imgs+"></a></div>");
		    			var div3 = $("<div style='padding-top:5px;width:110px;' align='center'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			
		    			div1.append(div2).append(div3);
		    			$("#sort").append(div1);
		    		});
		    }
		});
	});
	
	
});