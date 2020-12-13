package edu.uncc.teamfive.airreserve.controllers;

import edu.uncc.teamfive.airreserve.Services.*;
import edu.uncc.teamfive.airreserve.models.*;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AirportController {
	
	@Autowired
	APIServices apiServices;

	@Autowired
	BookingService bookingService;

    @GetMapping("/airports")
    public Airport[] index(@RequestParam(name="name", required=false, defaultValue="Charlotte") String name, Model model) {
        model.addAttribute("name", name);
        Airport testAirport = new Airport();
        testAirport.AirportID = 1;
        testAirport.Name = "JFK";
        testAirport.CityName = "NY";
        testAirport.CountryName = "US";
        return new Airport[]{ testAirport };
    }

    @GetMapping("/airportslive")
    public FastPlace[] live(@RequestParam(name="name", required=false, defaultValue="Charlotte") String name) throws IOException {
    	if(name != null) {
    	 return	apiServices.getPlaces(name);    		
    	}
    	return null;
    }
    
    @RequestMapping( value = "/getQuotes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public  List<QuoteViewModel> quotes(@RequestBody Map<String, Object> model) throws IOException {
        List<QuoteViewModel> quoteslist = new ArrayList<QuoteViewModel>();
        quoteslist = apiServices.getQuotes(model);
        return quoteslist;
    }
    

    @RequestMapping( value = "/boobkNow", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public  String book(@RequestBody Map<String, Object> model) throws IOException {
    	String result = bookingService.bookNow(model);
    	return result;
    }


}