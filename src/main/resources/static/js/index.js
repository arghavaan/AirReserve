var allFlights = [];
var selectedFlight = {};

function setDetails(i){
	var data = allFlights[i];
	$('#dtl-from').html(data.Origin.PlaceName + " (" + data.Origin.CountryName + ")");
	$('#dtl-to').html(data.Destination.PlaceName + " (" + data.Destination.CountryName + ")");
	$('#dtl-price').html("$" + data.Price);
	$('#dtl-al').html(data.Carrier.Name);
	selectedFlight = data;
}

$(document).ready(function(){


	var current_fs, next_fs, previous_fs; //fieldsets
	var opacity;

	$(".next").click(function(){

		current_fs = $(this).parent().parent();
		next_fs = $(this).parent().parent().next();

//Add Class Active
		$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

//show the next fieldset
		next_fs.show();
//hide the current fieldset with style
		current_fs.animate({opacity: 0}, {
			step: function(now) {
// for making fielset appear animation
				opacity = 1 - now;

				current_fs.css({
					'display': 'none',
					'position': 'relative'
				});
				next_fs.css({'opacity': opacity});
			},
			duration: 600
		});
	});

	$(".previous").click(function(){

		current_fs = $(this).parent().parent();
		previous_fs = $(this).parent().parent().prev();

//Remove class active
		$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

//show the previous fieldset
		previous_fs.show();

//hide the current fieldset with style
		current_fs.animate({opacity: 0}, {
			step: function(now) {
// for making fielset appear animation
				opacity = 1 - now;

				current_fs.css({
					'display': 'none',
					'position': 'relative'
				});
				previous_fs.css({'opacity': opacity});
			},
			duration: 600
		});
	});

	$(".submit").click(function(){
		return false;
	})


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
    	        			   var availableFlight=$("<div class='list-group'> <div href='#' class='list-group-item list-group-item-action'> <div class='d-flex w-100 justify-content-between'> <h5>" + data[i].Origin.PlaceName + "  <span> &#9992; </span>  " + data[i].Destination.PlaceName + "</h5> <small><b>$" + data[i].Price + "</b></small> </div> <span class=\"flight-" + (data[i].IsDirect ? "direct" : "indirect") + "\"> " + (data[i].IsDirect ? "Direct Flight" : "Indirect Flight")  + "</span> <span class='ml-2'>" + data[i].Carrier.Name + "</span><button onclick='setDetails("+ i +")' type=\"button\" class=\"btn btn-primary float-right\" data-toggle=\"modal\" data-target=\"#detailsModal\">Details</button> </div></div>");
            	       	    	$("#availableFlights").append(availableFlight);
    	        		   }
    	        	   }else{
    	        		 	$("#availableFlights").html("<p class='no-flight'>No Flights are available on selected dates!!!</p>");
    	        	   }
    	           }
    	         });

    	    
    	});
    	
    	
    	
    	
    	$("#msform").submit(function(e) {

    	    e.preventDefault(); // avoid to execute the actual submit of the form.

    	    var data = {};
    	    var form = $(this)[0];
    	    var url = form.action;

    	    
    	    data.email = form.email.value;
    	    data.uname = form.uname.value;
    	    data.pwd = form.pwd.value;
    	    data.fname = form.fname.value;
    	    data.lname = form.lname.value;
    	    data.mobile = form.mobile.value;
    	    data.passno = form.passno.value;

    	    data.holdername = form.holdername.value;
    	    data.cardno = form.cardno.value;
    	    data.cvcpwd = form.cvcpwd.value;
    	    data.expmonth = form.expmonth.value;
    	    data.expyear = form.expyear.value;
    	    data.flightDetails = selectedFlight;
    	    data.price = selectedFlight.Price;
    	    data.paymentType = "Credit";

    	    $.ajax({
    	           type: "POST",
    	           contentType: "application/json",
    	           url: url,
    	           dataType: 'json',
    	           data: JSON.stringify(data), // serializes the form's elements.
    	           success: function(data)
    	           {
    	        	   alert(data);
    	           }
    	         });

    	    
    	});
        
    });