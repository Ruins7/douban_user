
onmessage  = function(event) {
	var source;
	if(event.data == 'async') {
		console.log('recevied')
		  window.onload = function(){
			
			alert()
		}
		  
	}
};

 

function $$(id) {
	return document.getElementById(id);
}

function cleardata() {
	$$('msg').innerHTML = '';
}

function stopSSE() {
	source.close();
}

function getSSEData() {

	if (!!window.EventSource) {
	
		source = new EventSource("/douban_user/servlet/sse");
	} else {
		alert("Your browser doesn't support server-sent event");
		return;
	}

	source.addEventListener('message', function(event) {
		$$('msg').innerHTML += "server data : " + event.data + "<br>";   
	}, false);
	

	
	source.addEventListener('open', function(event) {
		$$('loading').innerHTML = "loading";
		
	}, false);

	source.addEventListener('error', function(event) {
		if (event.readyState == EventSource.CLOSED) {
			$$('msg').innerHTML = event.data;
		}
	}, false);


}


