$(function(){
	//分页查看全部东西
     $(window.document).bind('scroll', function(){          
        if($(window.document).scrollTop() >= ($(document).height()-$(window).height())){
         var currNum = $('#pagenum').val();
	     var newNum = ++currNum;//自增加1
		 var url = '/douban_user/things/thingsinfo?method=searchThingsByPage';				 
		 $.ajax({
		     type : 'post',
		     url : url,
		     dataType : 'json',
		     data : {currentPage : newNum},
		     success : function(json, status, xhr){
		           //alert(json+"   "+status+"   "+xhr);	          
		          if(json == false){
		             alert("没有更多了！");
		          }else{
		              //设置当前页数
                      $('#pagenum').val(json.currentPage);      
                      //循环显示表格数据
                      $.each(json.data, function(index, record){      
                        var div1 = $('<div class="th"></div>');
                        
                        var div2 = $('<div class="thingsimg"></div>');
                        var a1 = $('<a href="/douban_user/things/thingsinfo?method=showOneThing&things_id='+json.data[index].things_id+'"></a>');
                        var thingimg = '/douban_user/'+json.data[index].imgs
                        var img1 = $('<img src='+ thingimg + ' width="300xp" height="300px">');
                        
                        var one = div2.append(a1.append(img1));
                        
                        var div3 = $('<div class="thingsname"></div>');
                        var thingname = json.data[index].things_name;
                        
                        var two = div3.append(thingname);
                        
                        var div4 = $('<div class="thingsuser"></div>');
                        var thinguserimg = '/douban_user/'+json.data[index].f_user.imgs;
                        var img2 = $('<img src='+ thinguserimg + ' width="40px" height="40px" style="border-radius:20px;">');
                        var thingusername = json.data[index].f_user.username + '  发布  :';
                        
                        var three = div4.append(img2).append(thingusername);
                        
                        var div5 = $('<div class="thingscommon"></div>');
                        var img3 = $('<img src="lsr/img/frontyinhao.png">');
                        var thingsim = json.data[index].simple_desc;
                        var img4 = $('<img src="lsr/img/afteryinhao.png">');
                        
                        var four = div5.append(img3).append(thingsim).append(img4);
                        
                        var div6 = $('<div class="thingsbottom"></div>');
                        var thingtime = json.data[index].time;
                        var fmtt = $('<span style="float: left;">' + thingtime + '</span><input type="hidden" value="'+json.data[index].things_id+'"><a href="#" class="likeit" style="float: left;margin-left: 150px;color: #7D4B2F;">喜欢</a>');
                        
                        var five = div6.append(fmtt);
                        
                        var big = div1.append(one).append(two).append(three).append(four).append(five);
                        $('#thing_side').append(big);
                        	 
                      });                         
		          }	       	                      
		     },
		     error : function(responseText, status, xhr){
		          alert(responseText+"   "+status+"   "+xhr);
		     },     
  	     });

	    	                	 
	    };     	 		 
      }); 
    
    
  //分类查看东西
	$('.things_type').click(function(e){
		e.preventDefault(); 
		$('#thing_side').empty();		
		var type_id = $(this).prev().val();	 	 
		var url = '/douban_user/things/thingsinfo?method=showThingsByType';				 
		 $.ajax({
		     type : 'post',
		     url : url,
		     dataType : 'json',
		     data : {type : type_id},
		     success : function(json, status, xhr){
		         // alert("success "+json+"   "+status+"   "+xhr);	          
		          if(json == false){
			             alert("没有更多了！");
			          }else{
	                      //循环显示表格数据
	                      $.each(json, function(index, record){      
	                        var div1 = $('<div class="th"></div>');
	                        
	                        var div2 = $('<div class="thingsimg"></div>');
	                        var a1 = $('<a href="/douban_user/things/thingsinfo?method=showOneThing&things_id='+json[index].things_id+'"></a>');
	                        var thingimg = '/douban_user/'+json[index].imgs;
	                        var img1 = $('<img src='+ thingimg + ' width="300xp" height="300px">');
	                        
	                        var one = div2.append(a1.append(img1));
	                        
	                        var div3 = $('<div class="thingsname"></div>');
	                        var thingname = json[index].things_name;
	                        
	                        var two = div3.append(thingname);
	                        
	                        var div4 = $('<div class="thingsuser"></div>');
	                        var thinguserimg = '/douban_user/'+json[index].f_user.imgs;
	                        var img2 = $('<img src='+ thinguserimg + ' width="40px" height="40px">');
	                        var thingusername = json[index].f_user.username + '  发布  :';
	                        
	                        var three = div4.append(img2).append(thingusername);
	                        
	                        var div5 = $('<div class="thingscommon"></div>');
	                        var img3 = $('<img src="lsr/img/frontyinhao.png">');
	                        var thingsim = json[index].simple_desc;
	                        var img4 = $('<img src="lsr/img/afteryinhao.png">');
	                        
	                        var four = div5.append(img3).append(thingsim).append(img4);
	                        
	                        var div6 = $('<div class="thingsbottom"></div>');
	                        var thingtime = json[index].time;
	                        var fmtt = $('<span style="float: left;">' + thingtime + '</span><input type="hidden" value="'+json.data[index].things_id+'"><a href="#" class="likeit" style="float: left;margin-left: 150px;color: #7D4B2F;">喜欢</a>');
	                        
	                        var five = div6.append(fmtt);
	                        
	                        var big = div1.append(one).append(two).append(three).append(four).append(five);
	                        $('#thing_side').append(big);
 
	                      });   
	                      $('#thing_side').append('<br><br><hr style="width:9000px;clear:both;float:left;">');
			          }	    	                      
		     },
		     error : function(responseText, status, xhr){
		          // alert("fail  "+responseText+"   "+status+"   "+xhr);
		    	 alert("由于后台程序原因，加载失败！");
		     },     
		     });
	});
	
	
	//喜欢
	$(document).on('click','.likeit',function(e){
		e.preventDefault();
		var id = $(this).prev().val();
		var item = '4';//4：东西
		var url = '/douban_user/user/userinfo?method=likeSomething';				 
		 $.ajax({
		     type : 'post',
		     url : url,
		     dataType : 'json',
		     data : {item:item,item_id:id},
		     success : function(json, status, xhr){
		           //alert("success "+json+"   "+status+"   "+xhr);	          
		          if(json == false){
		             alert("很遗憾!喜欢失败！");
		          }else{ 
		        	  alert("喜欢成功！");
		        };	
		       
		     },
		     error : function(responseText, status, xhr){
		         //alert("fail  "+responseText+"   "+status+"   "+xhr);
		    	 alert("由于后台程序原因，喜欢失败！");
		    	 
		     },     
		     });
	});
	
	  //原生ajax	  
    /*   xhr = new XMLHttpRequest();
         xhr.open('post', url, true);
         xhr.onreadystatechange = function(){
         if(xhr.readyState == 4){
            if(xhr.status == 200){
                var json = eval('('+ xhr.responseText +')');
                $('#pagenum').val(json.length);
            }		 
         }		     
        };		     
        xhr.send(); */	     
});
      