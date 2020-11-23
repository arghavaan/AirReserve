var allFlights = [];

function setDetails(i){
	var data = allFlights[i];
	$('#dtl-from').html(data.Origin.PlaceName + " (" + data.Origin.CountryName + ")");
	$('#dtl-to').html(data.Destination.PlaceName + " (" + data.Destination.CountryName + ")");
	$('#dtl-price').html("$" + data.Price);
	$('#dtl-al').html(data.Carrier.Name);
}

$(document).ready(function(){

	jQuery.ui.autocomplete.prototype._resizeMenu = function () {
		var ul = this.menu.element;
		ul.outerWidth(this.element.outerWidth());
	}


	$( "#from" ).autocomplete({
		source: function (request, response) {
			$.ajax({
				url: "airportslive?name=" + request.term,
				dataType: "json",
				success: function (data) {
					response(data);
				}
			});
		},
		minLength: 2,
		select: function (event, ui) {
			event.preventDefault();
			$('#from').val(ui.item.label);
			$('#fromid').val(ui.item.value);
		}
	});

	$( "#to" ).autocomplete({
		source: function (request, response) {
			$.ajax({
				url: "airportslive?name=" + request.term,
				dataType: "json",
				success: function (data) {
					response(data);
				}
			});
		},
		minLength: 2,
		select: function (event, ui) {
			event.preventDefault();
			$('#to').val(ui.item.label);
			$('#toid').val(ui.item.value);
		}
	});
		$('input[type=radio][name=options]').change(function() {
			console.log('CHECK CHECK!');
			if (this.value == 'round') {
				$("#return").prop('required', true);
				$("#return").prop('disabled', false);
			}
			else if (this.value == 'oneway') {
				$("#return").prop('required', false);
				$("#return").prop('disabled', true);
			}
		});


    	$("#flightSearch").submit(function(e) {

    	    e.preventDefault(); // avoid to execute the actual submit of the form.

    	    var data = {};
    	    var form = $(this)[0];
    	    var url = form.action;

    	    
    	    data.options = $('input[name="options"]:checked').val();
    	    data.from = form.fromid.value;
    	    data.to = form.toid.value;
    	    data.departure = form.departure.value;
    	    data.return = form.return.value;
			$("#availableFlights").html('<div class="spinner-border text-danger" role="status"><span class="sr-only">Loading...</span></div>');
			$("#result-flights").show();
    	    $.ajax({
    	           type: "POST",
    	           contentType: "application/json",
    	           url: url,
    	           dataType: 'json',
    	           data: JSON.stringify(data), // serializes the form's elements.
    	           success: function(data)
    	           {
					   $("#availableFlights").html('');
    	        	   if(data[0]){
						   allFlights = data;
    	        		   for(var i=0; i<data.length; i++){
    	        			   var availableFlight=$("<div class='list-group'> <div href='#' class='list-group-item list-group-item-action'> <div class='d-flex w-100 justify-content-between'> <h4>" + data[i].Origin.PlaceName + "<span>&#9992;</span>" + data[i].Destination.PlaceName + "</h4> <small><b>$" + data[i].Price + "</b></small> </div> <p>Direct Flight : " + data[i].IsDirect + "</p> <p>" + data[i].Carrier.Name + "</p><button onclick='setDetails("+ i +")' type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#detailsModal\">Details</button> </div></div>");
            	       	    	$("#availableFlights").append(availableFlight);
    	        		   }
    	        	   }else{
    	        		 	$("#availableFlights").html("<p class='no-flight'>No Flights are available on selected dates!!!</p>");
    	        	   }
    	           }
    	         });

    	    
    	});
        
    });