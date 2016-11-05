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
						console.log("open");
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
								categories: ["Spiritually self-reliant", "Answers to questions", "Faith over doubt", "My own decisions", "Setting goals", "Financially self-reliant", "Stand as a witness"]
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