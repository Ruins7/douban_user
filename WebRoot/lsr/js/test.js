//ajax反推送的worker

var work;
$(function(){
	 
	
	workerhandler();
	
});
function workerhandler(){

	if (typeof(Worker) !== "undefined") {
		alert('Yes! Web worker support!');
		
		work = new Worker('lsr/js/sse.js'); //启动一个异步线程  
		
		work.postMessage('async');
		
		alert();
		 
	} else {
		alert('Sorry! No Web Worker support..') ;
	}
}