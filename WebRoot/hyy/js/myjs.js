$(function(){
		//设置表格中id 列不显示
		$("table tr td:first-child").css("display","none");
		//当页面加载完成后， 请求加载商品数据
		formbtn();
		
		function formbtn(){
			//jQuery 中将表单中的参数转成json 字符串的方式， 非常重要
			var jsonStr = $("form").serialize();
			
			//juqery 方式的ajax
			$.getJSON("shoesServlet?rmp=ajaxLoadData",jsonStr, function(data){
				// 1. 清空 <tfoot> 标签中的内容 
			
				
				$("#t_foot").empty();
				
				// 2. 重新构建<tfoot> 标签下的<tr/> 元素
				for(var i=0; i<data.length; i++){
					$("#t_foot").append($("<tr></tr>"));
				}
				
				// 3. 获取刚刚添加的所有 tr 
				var t_trs = $("#t_foot tr");
				
				
				// 2. 重新构建 <tfoot> 标签中每个<tr> 中的<td> 
				  $.each(data, function(i,item){ //each 为遍历从服务器段传回来的list 对象
					 //获取一个 <tr></tr> 元素
					 var tr = $(t_trs[i]);
						tr.append(createTd(item.id));
						tr.append(createTd(item.name));
						tr.append(createTd(item.brand));
						tr.append(createTd(item.price));
						tr.append(createTd(item.sex));
						tr.append(createTd(item.type));
						tr.append(createTd(item.date));
						tr.append(createTd(item.size));
				  });
				  
				  //给新生成的表格添加样式
				  $("table tr td:first-child").css("display","none");
				  $("table tr").css("text-align","center").css("hover","red");
			});
		}
		//创建一个html 元素
		function createTd(value){
			var str = $("<td>" + value + "</td>");
			return str;
		}
		
		//给表单中的一些数据添加触发异步ajax 的事件
		$("option").click(formbtn);
		$("input[type='radio']").click(formbtn);
		$("#formBtn").click(formbtn);
	});