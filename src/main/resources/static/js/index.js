   $(document).ready(function(){
    	$("#from").keyup(function(){
    		var from = $('#from').val();
    		$.ajax({     
    			type: "GET",
    		     url: 'airportslive?name=' + from,
    		     dataType: "json",
    		     success: function (data) {
    		    	 console.log(data);
    		    	 	if(data[0]){
		    	 			var placeNames = [];
    		    	 		for(i=0; i<data.length; i++){
    		    	 			placeNames.push(data[i].PlaceId + ' ' + data[i].PlaceName);
    		    	 		}
    		    	 		
		    		    	$( "#from" ).autocomplete({
		    		             source: placeNames,
		    		             minLength:0
		    		        }).bind('focus', function(){ $(this).autocomplete("search"); } );
    		    	 	}else{
    		    	 		
    		    	 	}
    		         }
    		});
    	});
    	
    	$("#to").keyup(function(){
    		var to = $('#to').val();
    		$.ajax({     
    			type: "GET",
    		     url: 'airportslive?name=' + to,
    		     dataType: "json",
    		     success: function (data) {
    		    	 if(data[0]){
		    	 			var placeNames = [];
 		    	 		for(i=0; i<data.length; i++){
 		    	 			placeNames.push(data[i].PlaceId + ' ' + data[i].PlaceName);
 		    	 		}
		    		    	$( "#to" ).autocomplete({
		    		             source: placeNames,
		    		             minLength:0
		    		        }).bind('focus', function(){ $(this).autocomplete("search"); } );
 		    	 	}else{
 		    	 		
 		    	 	}
 		         }
    		});
    	});

    	$("input[name=options]:radio").click(function(){
    		
    		$("#option1").parent().removeClass('active');
    		$("#option2").parent().removeClass('active');
    		$("#option3").parent().removeClass('active');

    		$(this.parentNode).addClass('active');
            
    		switch (this.id) {
    	    case 'option1':
    	    	$("#return").prop('required', true);
    	    	$("#return").prop('disabled', false);
    	      break;
    	    case 'option2':
    	    	$("#return").prop('required', false);
    	    	$("#return").prop('disabled', true);
    	      break;
    	    case 'option3':
      	      break;
    	  }
    	});
    	
    	$("#flightSearch").submit(function(e) {

    	    e.preventDefault(); // avoid to execute the actual submit of the form.

    	    var form = $(this);
    	    var url = form.attr('action');
    	    
    	    $.ajax({
    	           type: "POST",
    	           url: url,
    	           data: form.serialize(), // serializes the form's elements.
    	           success: function(data)
    	           {
    	               alert(data);
    	           }
    	         });

    	    
    	});
        
    });