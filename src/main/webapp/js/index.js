jQuery(function($){
		$(document).ready(function ($) {
				var wsUri = "ws://" + document.location.host + "/livevote/survey";
				var websocket = new WebSocket(wsUri);
				
				function sendMessage(data){
						websocket.send(JSON.stringify(data));
				}
				
				websocket.onerror = function (evt) {
						console.log("error");
						console.log(evt);
				};

				websocket.onclose = function (close) {
						console.log("closed");
						console.log(close);
				};

				websocket.onopen = function (evt) {
						console.log("open");
				};

				websocket.onmessage = function (evt) {
					var msg = JSON.parse(decodeURIComponent(evt.data));
					console.log(msg);
				};
				
				$('#btnEnter').click(function(){
						sendMessage({
								results: [
										{
												option: "1",
												votes: 1
										},
										{
												option: "2",
												votes: 1
										}
								]
						});
				});
		});
});