// JavaScript Document

$(function() {

	$('.fabupng').mousemove(function(){
		$('.fabupng').attr('src','lsr/img/fabuonpng.png');		
	});
	$('.fabupng').mouseout(function(){
		$('.fabupng').attr('src','lsr/img/fabu.png');		
	});
	$('#fabutubro').click(function(){//发布广播
		document.sanbro.submit();
	});
	$('#fabutuimg').click(function(){//发布图片广播
		document.sandimgsbro.submit();
	});
	$('#fabuthings').click(function(){//发布东西
		document.sendthings.submit();
	});
	
	//图片发布预览
	$('#uploadbroimg').change(function(event){
		var file =  event.target.files[0];
		previewl(file);
	});
	//图片发布预览
	$('#uploadbroimg3').change(function(event){
		var file =  event.target.files[0];
		preview(file);
	});
	
	//加载更多  我的广播
	$('#momybro').click(function(event){
		event.preventDefault();
		var uid = $('#uid').val();//获取当前用户id
		var pagenum = $('#mybro_pagenum').val();//获取当前页码	
		var url = '/douban_user/user/userinfo?method=searchMyBroByPage';
		$.ajax({
			type : 'post',
			url : url,
			dataType : 'json',
			data : {user_id:uid, currentPage:++pagenum},
			success : function(json, status, xhr) {
			    //alert("success:"+json+"   "+status+"   "+xhr);	
			    if(json == false){
		             alert("没有更多了！");
		          }else{
		              //设置当前页数
                     $('#mybro_pagenum').val(json.currentPage);                        
                     //循环显示表格数据
                     $.each(json.data, function(index, record){                                               
                        //微博弊病！！append节点分开写！独立添加样式和事件！
                  		var div1 = $('<div style="font-size:13px;background-color:#F9F9F9;width:670px;height:250px;margin-top: 15px;margin-bottom: 20px;padding-top: 5px;padding-bottom: 10px;padding-left:20px;"></div>');
                  		var div2 = $('<div style="width:60px;margin-top:10px;height:200px;position:relative;float:left;"></div>');
                  		var a1   = $('<a href=""></a>');
                  		var user_img = json.data[index].user_info.imgs;
                  		var img1 = $('<img src="http://localhost:8080/douban_user/' + user_img + '" width="60px" height="60px" style="border-radius:30px;">');//用户头像
                  		
                  		var one = div2.append(a1.append(img1));
                  		
                  		var div3  = $('<div style="height:220px;width:560px; padding:10px;margin:10px;word-break:break-all;overflow:hidden;float: left;"></div>');
                  		var span1 = $('<span style="color: #3377AA;"></span>');
                  		var a2    = $('<a class="item">' + json.data[index].user_info.username + '</a>');//用户名
                  		
                  		var two = span1.append(a2);
                  		
                  		var div4  = $('<div style="float: right;margin-right: 30px;margin-left: 15px;"></div');
                  		var cif   = $('<c:if test="${bro.imgs } != null"></c:if>');
                  		var content_img = 'http://localhost:8080/douban_user/' + json.data[index].imgs; 
                  		var img2  = $('<img width="140px" height="170px" src="' + content_img + '">');//内容照片
                  		
                  		var three = div4.append(cif.append(img2));
                  		
                  		var div5  = $('<div style="height: 170px;width: 380px;padding: 10px;"></div');
                  		var img3  = $('<img src="lsr/img/frontyinhao.png">');
                  		var content = json.data[index].content;
                  		var img4  = $('<img src="lsr/img/afteryinhao.png">');
                  		
                  		var four = div5.append(img3).append(content).append(img4);
                  		
                  		var content_time = json.data[index].time;
                  		var span2 = $('<span style="color: #999999;margin-right: 150px;"></span>');
                  		
                  		var a3    = $('<a class="item addcomment invo" href="#">回应</a>');
                  		var a4    = $('<a class="item like invo" href="#">赞</a>');
                  		var a6    = $('<input type="hidden" value="'+json.data[index].broadcast_id+'">  ');
                  		var a5    = $('<a class="item sendtomybor invo" href="#">转播</a>');
                  	         
                  		var five = span2.append(content_time);
                  	 
                  	    var small = div3.append(two).append(three).append(four).append(five).append(a3).append(a4).append(a6).append(a5);
                        div1.append(one).append(small);
                        $('#mybro_more').append(div1);
                     });                         
		          }	 		    
			},//success
			error : function(json, status, xhr) {
				alert("error:"+json + "   " + status + "   " + xhr);	 
			}
		});//ajax
	});

	
	
	///////////////////////////////////////////////
	
	
	
	//加载更多  好友广播
	$('#mofribro').click(function(event){
		event.preventDefault();
		var uid = $('#uid').val();//获取当前用户id
		var pagenum = $('#fribro_pagenum').val();//获取当前页码	
		var url = '/douban_user/user/userinfo?method=searchFriendsBroByPage';
		$.ajax({
			type : 'post',
			url : url,
			dataType : 'json',
			data : {user_id:uid, currentPage:++pagenum},
			success : function(json, status, xhr) {
			    //alert("success:"+json+"   "+status+"   "+xhr);	
			    if(json == false){
		             alert("没有更多了！");
		          }else{
		              //设置当前页数
                     $('#fribro_pagenum').val(json.currentPage);                        
                     //循环显示表格数据
                     $.each(json.data, function(index, record){                                               
                        //微博弊病！！append节点分开写！独立添加样式和事件！
                  		var div1 = $('<div style="font-size:13px;background-color:#F9F9F9;width:670px;height:250px;margin-top: 15px;margin-bottom: 20px;padding-top: 5px;padding-bottom: 10px;padding-left:20px;"></div>');
                  		var div2 = $('<div style="width:60px;margin-top:10px;height:200px;position:relative;float:left;"></div>');
                  		var a1   = $('<a href=""></a>');
                  		var user_img = json.data[index].user_info.imgs;
                  		var img1 = $('<img src="http://localhost:8080/douban_user/' + user_img + '" width="60px" height="60px">');//用户头像
                  		
                  		var one = div2.append(a1.append(img1));
                  		
                  		var div3  = $('<div style="height:220px;width:560px; padding:10px;margin:10px;word-break:break-all;overflow:hidden;float: left;"></div>');
                  		var span1 = $('<span style="color: #3377AA;"></span>');
                  		var a2    = $('<a class="item">' + json.data[index].user_info.username + '</a>');//用户名
                  		
                  		var two = span1.append(a2);
                  		
                  		var div4  = $('<div style="float: right;margin-right: 30px;margin-left: 15px;"></div');
                  		var cif   = $('<c:if test="${bro.imgs } != null"></c:if>');
                  		var content_img = 'http://localhost:8080/douban_user/' + json.data[index].imgs; 
                  		var img2  = $('<img width="140px" height="170px" src="' + content_img + '">');//内容照片
                  		
                  		var three = div4.append(cif.append(img2));
                  		
                  		var div5  = $('<div style="height: 170px;width: 380px;padding: 10px;"></div');
                  		var img3  = $('<img src="lsr/img/frontyinhao.png">');
                  		var content = json.data[index].content;
                  		var img4  = $('<img src="lsr/img/afteryinhao.png">');
                  		
                  		var four = div5.append(img3).append(content).append(img4);
                  		
                  		var content_time = json.data[index].time;
                  		var span2 = $('<span style="color: #999999;margin-right: 150px;"></span>');
                  		
                  		var a3    = $('<a class="item" href="#">回应</a>    ');
                  		var a4    = $('<a class="item" href="#">赞</a>     ');
                  		var a5    = $('<a class="item" href="#">转播</a>');
                  	         
                  		var five = span2.append(content_time);
                  	 
                  	    var small = div3.append(two).append(three).append(four).append(five).append(a3).append(a4).append(a5);
                        div1.append(one).append(small);
                        $('#fribro_more').append(div1);
                     });                         
		          }	 		    
			},//success
			error : function(json, status, xhr) {
				alert("error:"+json + "   " + status + "   " + xhr);	 
			}
		});//ajax
	});

	
	
	
	/////////////////////////////////////////////////
	
	
	
	//加载更多  我的推荐
	$('#momyrec').click(function(event){
		event.preventDefault();
		var uid = $('#uid').val();//获取当前用户id
		var pagenum = $('#myrec_pagenum').val();//获取当前页码
		var url = '/douban_user/user/userinfo?method=searchMyRecByPage';
		$.ajax({
			type : 'post',
			url : url,
			dataType : 'json',
			data : {user_id:uid, currentPage:++pagenum},
			success : function(json, status, xhr) {
			    //alert("success:"+json+"   "+status+"   "+xhr);	
			    if(json == false){
		             alert("没有更多了！");
		          }else{
		              //设置当前页数
                     $('#myrec_pagenum').val(json.currentPage);                        
                     //循环显示表格数据
                     $.each(json.data, function(index, record){                                     
                        //微博弊病！！append节点分开写！独立添加样式和事件！
                  		var div1 = $('<div style="font-size:13px;background-color:#F9F9F9;width:670px;height:250px;margin-top: 15px;margin-bottom: 20px;padding-top: 5px;padding-bottom: 10px;padding-left:20px;"></div>');
                  		var div2 = $('<div style="width:60px;margin-top:10px;height:200px;position:relative;float:left;"></div>');
                  		var a1   = $('<a href=""></a>');
                  		var user_img = json.data[index].user_info.imgs;
                  		var img1 = $('<img src="http://localhost:8080/douban_user/' + user_img + '" width="60px" height="60px">');//用户头像
                  		
                  		var one = div2.append(a1.append(img1));
                  		
                  		var div3  = $('<div style="height:220px;width:560px; padding:10px;margin:10px;word-break:break-all;overflow:hidden;float: left;"></div>');
                  		var span1 = $('<span style="color: #3377AA;"></span>');
                  		var a2    = $('<a class="item">' + json.data[index].user_info.username + '</a>');//用户名
                  		var tuijian = ' 推荐  ';
                  		var a6    = $(' <a class="item" href=""></a>');
                  		var item_table = json.data[index].item.type_table;
                  		
                  		var two = span1.append(a2).append(tuijian).append(a6.append(item_table));
                  		
                  		var div4  = $('<div style="float: right;margin-right: 30px;margin-left: 15px;"></div');
                  		var cif   = $('<c:if test="${bro.imgs } != null"></c:if>');
                  		var item_img = 'http://localhost:8080/douban_user/' + json.data[index].item.imgs; 
                  		var img_item = $('<img src="'+ item_img +'" width="140px" height="170px">');
                  		var a7  = $('<a href=""></a>');
                  		
                  		var three = div4.append(cif.append(a7.append(img_item)));
                  		
                  		var div5  = $('<div style="height: 170px;width: 380px;padding: 10px;"></div');
                  	    var p1    = $(' <p style="color: #3377AA;"></p>');
                  	    var name  = '名称 : ' + json.data[index].item.name ;
                  		var img3  = $('<img src="lsr/img/frontyinhao.png">');
                  		var commons = json.data[index].my_commons;
                  		var img4  = $('<img src="lsr/img/afteryinhao.png">');
                  		
                  		var four = div5.append(p1.append(name)).append(img3).append(commons).append(img4);
                  		
                  		var content_time = json.data[index].time;
                  		var span2 = $('<span style="color: #999999;margin-right: 150px;"></span>');
                  		
                  		var a3    = $('<a class="item" href="#">回应</a>    ');
                  		var a4    = $('<a class="item" href="#">赞</a>     ');
                  		var a5    = $('<a class="item" href="#">转播</a>');
                  	         
                  		var five = span2.append(content_time);
                  	 
                  	    var small = div3.append(two).append(three).append(four).append(five).append(a3).append(a4).append(a5);
                        div1.append(one).append(small);
                        $('#myrec_more').append(div1);
                     });                         
		          }	 
			    
			    
			},//success
			error : function(json, status, xhr) {
				alert("error:"+json + "   " + status + "   " + xhr);
				 
			}
		});//ajax
	});
	
	
	
	///////////////////////////////////////////////////
	
	
	
	//加载更多 好友推荐
	$('#mofrirec').click(function(event){
		event.preventDefault();
		var uid = $('#uid').val();//获取当前用户id
		var pagenum = $('#frirec_pagenum').val();//获取当前页码
		var url = '/douban_user/user/userinfo?method=searchFriendsRecByPage';
		$.ajax({
			type : 'post',
			url : url,
			dataType : 'json',
			data : {user_id:uid, currentPage:++pagenum},
			success : function(json, status, xhr) {
			    //alert("success:"+json+"   "+status+"   "+xhr);	
			    if(json == false){
		             alert("没有更多了！");
		          }else{
		              //设置当前页数
                     $('#frirec_pagenum').val(json.currentPage);                        
                     //循环显示表格数据
                     $.each(json.data, function(index, record){                                     
                        //微博弊病！！append节点分开写！独立添加样式和事件！
                  		var div1 = $('<div style="font-size:13px;background-color:#F9F9F9;width:670px;height:250px;margin-top: 15px;margin-bottom: 20px;padding-top: 5px;padding-bottom: 10px;padding-left:20px;"></div>');
                  		var div2 = $('<div style="width:60px;margin-top:10px;height:200px;position:relative;float:left;"></div>');
                  		var a1   = $('<a href=""></a>');
                  		var user_img = json.data[index].user_info.imgs;
                  		var img1 = $('<img src="http://localhost:8080/douban_user/' + user_img + '" width="60px" height="60px">');//用户头像
                  		
                  		var one = div2.append(a1.append(img1));
                  		
                  		var div3  = $('<div style="height:220px;width:560px; padding:10px;margin:10px;word-break:break-all;overflow:hidden;float: left;"></div>');
                  		var span1 = $('<span style="color: #3377AA;"></span>');
                  		var a2    = $('<a class="item">' + json.data[index].user_info.username + '</a>');//用户名
                  		var tuijian = ' 推荐  ';
                  		var a6    = $(' <a class="item" href=""></a>');
                  		var item_table = json.data[index].item.type_table;
                  		
                  		var two = span1.append(a2).append(tuijian).append(a6.append(item_table));
                  		
                  		var div4  = $('<div style="float: right;margin-right: 30px;margin-left: 15px;"></div');
                  		var cif   = $('<c:if test="${bro.imgs } != null"></c:if>');
                  		var item_img = 'http://localhost:8080/douban_user/' + json.data[index].item.imgs; 
                  		var img_item = $('<img src="'+ item_img +'" width="140px" height="170px">');
                  		var a7  = $('<a href=""></a>');
                  		
                  		var three = div4.append(cif.append(a7.append(img_item)));
                  		
                  		var div5  = $('<div style="height: 170px;width: 380px;padding: 10px;"></div');
                  	    var p1    = $(' <p style="color: #3377AA;"></p>');
                  	    var name  = '名称 : ' + json.data[index].item.name ;
                  		var img3  = $('<img src="lsr/img/frontyinhao.png">');
                  		var commons = json.data[index].my_commons;
                  		var img4  = $('<img src="lsr/img/afteryinhao.png">');
                  		
                  		var four = div5.append(p1.append(name)).append(img3).append(commons).append(img4);
                  		
                  		var content_time = json.data[index].time;
                  		var span2 = $('<span style="color: #999999;margin-right: 150px;"></span>');
                  		
                  		var a3    = $('<a class="item" href="#">回应</a>    ');
                  		var a4    = $('<a class="item" href="#">赞</a>     ');
                  		var a5    = $('<a class="item" href="#">转播</a>');
                  	         
                  		var five = span2.append(content_time);
                  	 
                  	    var small = div3.append(two).append(three).append(four).append(five).append(a3).append(a4).append(a5);
                        div1.append(one).append(small);
                        $('#frirec_more').append(div1);
                     });                         
		          }	     
			},//success
			error : function(json, status, xhr) {
				alert("error:"+json + "   " + status + "   " + xhr);			 
			}
		});//ajax
	});
	 
	
});

//微博字数统计函数
function words_sum_up() { 
	var l = document.getElementById('bro').value.length;
	var count = 280-l;
	if(count <= -1) {
		 alert("您的广播太......长了");
         var cont = document.getElementById('bro').value;
		 var trip_cont = cont.substring(0,280);
		 document.getElementById('bro').value = trip_cont;
		 var l = document.getElementById('bro').value.length;
	     count = 280-l;	 
	}
	document.getElementById('broleft').innerHTML = '还能输入'+count+'个字';
}
//微博字数统计函数
function words_sum_up1() {
	
	var l = document.getElementById('bro1').value.length;
	var count = 280-l;
	if(count <= -1) {
		alert("您的广播太......长了");
		var cont = document.getElementById('bro1').value;
		var trip_cont = cont.substring(0,280);
		document.getElementById('bro1').value = trip_cont;
		var l = document.getElementById('bro1').value.length;
		count = 280-l;	 
	}
	document.getElementById('broleft1').innerHTML = '还能输入'+count+'个字';
}

//微博字数统计函数
function words_sum_up2() {
	
	var l = document.getElementById('bro2').value.length;
	var count = 40-l;
	if(count <= -1) {
		alert("您的评论太......长了");
		var cont = document.getElementById('bro2').value;
		var trip_cont = cont.substring(0,40);
		document.getElementById('bro2').value = trip_cont;
		var l = document.getElementById('bro2').value.length;
		count = 40-l;	 
	}
	document.getElementById('broleft2').innerHTML = '还能输入'+count+'个字';
}

//发图预览
function previewl(file){
	var reader = new FileReader();
	reader.onload = function(e){
		var $img = $('<img>').attr("src", e.target.result);
		$('#preview').empty().append($img);	
	};
	reader.readAsDataURL(file);  	
};
//发图预览
function preview(file){
	var reader = new FileReader();
	reader.onload = function(e){
		var $img = $('<img>').attr("src", e.target.result);
		$('#preview3').empty().append($img);	
	};
	reader.readAsDataURL(file);  	
};