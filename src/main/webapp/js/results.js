jQuery(function($){
		$(document).ready(function ($) {
				var wsUri = "ws://" + document.location.host + "/LiveVote/results";
				var websocket = new WebSocket(wsUri);
				var chart = undefined;
				var firstLoad = true;
				
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
						console.log(evt.data);
					var msg = JSON.parse(decodeURIComponent(evt.data));
					updateChart(msg);
					if (firstLoad){
						$.each(msg.options, function(index, option){
								$('#lessonLinks').append(
										'<p><a target="_blank" href="'+option.url+'">'+option.prompt+'</a></p>'
								);
						});
					  firstLoad = false;
					}
				};

				function getChart(categories){
						return new Highcharts.Chart('container', {
								chart: {
										type: 'column'
								},
								title: {
										text: 'Survey Results'
								},
								xAxis: {
										categories: categories
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
				}
		
				function updateChart(msg){
						if (firstLoad){
								var categories = msg.options.map(function(option){
										return option.abbreviation;
								});
								chart = getChart(categories);
						}
						var series = chart.get("seriesResults");
						series.setData(msg.votes);
				}
				
				$('#reset').click(function(){
						sendMessage("");
				});
		});
});