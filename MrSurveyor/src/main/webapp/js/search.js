$(document).ready(function() {
	$('.live-search').keyup(function() {
		var input = $(this).val();
		
		if(input != "") {
			$.ajax({
				url:"http://localhost:8080/MrSurveyor/SearchServlet",
				method:"GET",
				data:{search:input},
				
				success:function(data) {
					$('#search-results').css("display", "block");
					$('#search-results').html(data);
				},
				error: function(err) {
                	console.error(err);
                }
			});
		} else {
			$('#search-results').css("display", "none");
		}
	});
});