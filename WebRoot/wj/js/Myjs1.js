/**
 * 
 */
$(function(){

	$.ajax({
		type:'post',
        url:'http://localhost:8080/douban_user/servlet/movieServlet',
        data:{method:"getMovieJsonByDate"},
	    success:function(data,status,xhr){
	    	$("#top250").empty();
	    	var data1 = eval('('+data+')'); 
	    		$.each(data1 , function(index , value){   //value -- json
	    			var div1 = $("<div style='width:80px; height:120px; position:relative; float:left;'></div>");
	    			var div2 = $("<div style='width:80px; height:90px'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img src="+value.imgs+" style=' width:75px; height:90px'></a></div>");
	    			var div3 = $("<div style='margin-top:10px;' align='center'><a style='font-size:12px;text-decoration:none;color:#208ACC;' href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
	    			

	    			div1.append(div2).append(div3);
	    			$("#top250").append(div1);
	    		});
	    }
	});
	$("#movement").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonType",type_id:4},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#top250").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:80px; height:120px; position:relative; float:left;'></div>");
		    			var div2 = $("<div style='width:80px; height:90px'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img src="+value.imgs+" style=' width:75px; height:90px'></a></div>");
		    			var div3 = $("<div style='margin-top:10px;' align='center'><a style='font-size:12px;text-decoration:none;color:#208ACC;' href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			

		    			div1.append(div2).append(div3);
		    			$("#top250").append(div1);
		    		});
		    }
		});
	});
	
	$("#love").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonType",type_id:2},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#top250").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:80px; height:120px; position:relative; float:left;'></div>");
		    			var div2 = $("<div style='width:80px; height:90px'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img src="+value.imgs+" style=' width:75px; height:90px'></a></div>");
		    			var div3 = $("<div style='margin-top:10px;' align='center'><a style='font-size:12px;text-decoration:none;color:#208ACC;' href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			

		    			div1.append(div2).append(div3);
		    			$("#top250").append(div1);
		    		});
		    }
		});
	});
	
	$("#comedy").click("on",function(){
		$.ajax({
			type:'post',
            url:'http://localhost:8080/douban_user/servlet/movieServlet',
            data:{method:"getMovieJsonType",type_id:8},
            dataType: 'json', 
		    success:function(data,status,xhr){
		    	$("#top250").empty();
		    		$.each(data , function(index , value){   //value -- json
		    			var div1 = $("<div style='width:80px; height:120px; position:relative; float:left;'></div>");
		    			var div2 = $("<div style='width:80px; height:90px'><a href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'><img src="+value.imgs+" style=' width:75px; height:90px'></a></div>");
		    			var div3 = $("<div style='margin-top:10px;' align='center'><a style='font-size:12px;text-decoration:none;color:#208ACC;' href='/douban_user/servlet/movieServlet?method=findOneMovie&movie_id="+value.movie_id+"' style='font-size:12px; color:#000000'>"+value.m_name+"</a></div>");
		    			

		    			div1.append(div2).append(div3);
		    			$("#top250").append(div1);
		    		});
		    }
		});
	});
	
});