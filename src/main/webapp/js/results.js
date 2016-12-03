jQuery(function($){
		$(document).ready(function ($) {
				var wsUri = "ws://" + document.location.host + "/LiveVote/results";
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
				};

				websocket.onmessage = function (evt) {
					var msg = JSON.parse(decodeURIComponent(evt.data));
					updateChart(msg);
				};

				var chart = new Highcharts.Chart('container', {
						chart: {
								type: 'column'
						},
						title: {
								text: 'Survey Results'
						},
						xAxis: {
								categories: ["Serve in the church", "Leader", "Teacher", "Share the gospel", "Symbols of 2nd Coming", "Prepare for 2nd Coming", "Testimony of Son of God"]
						},
						yAxis: {
								min: 0
						},
						plotOptions: {
								column: {
										pointPadding: 0.2,
										borderWidth: 0
								}
						},
						series: [{
								id: "seriesResults",
								data: [0,0,0,0,0,0,0]
						}],
						export: {
								enabled: false
						},
						credits: {
								enabled: false
						}
				});
		
				function updateChart(msg){
						var series = chart.get("seriesResults");
						series.setData(msg.votes);
				}
				
				$('#reset').click(function(){
						sendMessage("");
				});
		});
});