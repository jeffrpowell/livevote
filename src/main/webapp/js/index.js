jQuery(function($){
		$(document).ready(function ($) {
				var wsUri = "ws://" + document.location.host + "/LiveVote/survey";
				var websocket = new WebSocket(wsUri);
				var options = [];
				
				function sendMessage(data){
						websocket.send(JSON.stringify(data));
				}
				
				websocket.onopen = function (evt) {
				};

				websocket.onmessage = function (evt) {
					options = JSON.parse(decodeURIComponent(evt.data));
					$.each(options, function(index, option){
							$('#options').append(
									"<div class='checkbox'>"+
										"<label>"+
											"<input type='checkbox' name='votes' value='"+index+"'>"+
											option+
										"</label>"+
									"</div>"
							);
					});
				};
				
				$('#castVote').click(function(){
						var selected = [];
						$('#options input:checked').each(function(){
								var input = $(this);
								selected.push(options[input.attr('value')]);
						});
						sendMessage(selected);
						$('#options input').each(function(){
								$(this).prop('checked', false);
						});
				});
		});
});