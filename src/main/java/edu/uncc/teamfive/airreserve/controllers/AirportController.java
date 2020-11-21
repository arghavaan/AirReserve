package edu.uncc.teamfive.airreserve.controllers;

import edu.uncc.teamfive.airreserve.models.*;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

	private static final String QUERYSEPARATOR = "?";
	private static final String SKYKEYWORD = "sky";
	private static final String FORWARDSLASHSEPARATOR = "/";
	
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
    public Place[] live(@RequestParam(name="name", required=false, defaultValue="Charlotte") String name, Model model) throws IOException {
        model.addAttribute("name", name);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/autosuggest/v1.0/USA/USD/en-US/?query=" + name)
                .get()
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "d81debeabemsh298ee99a6fadd38p1c39d1jsnf31e18d621be")
                .build();

        Response response = client.newCall(request).execute();

        if(response.isSuccessful()){
            String jsonData = response.body().string();
            JSONObject Jobject = new JSONObject(jsonData);
            JSONArray Jarray = Jobject.getJSONArray("Places");
            List<Place> placelist = new ArrayList<Place>();
            for (int i = 0; i < Jarray.length(); i++) {
                JSONObject object     = Jarray.getJSONObject(i);
                Place place = new Place();
                place.setPlaceId(object.getString("PlaceId"));
                place.setPlaceName(object.getString("PlaceName"));
                place.setCountryId(object.getString("CountryId"));
                place.setRegionId(object.getString("RegionId"));
                place.setCityId(object.getString("CityId"));
                place.setCountryName(object.getString("CountryName"));
                placelist.add(place);
            }
            return placelist.toArray(new Place[0]);
        }

        return new Place[0];
    }
    
    @RequestMapping( value = "/getQuotes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public  List<QuoteViewModel> quotes(@RequestBody Map<String, Object> model) throws IOException {
        
        List<QuoteViewModel> quoteslist = new ArrayList<QuoteViewModel>();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/US/USD/en-US").newBuilder();

//        urlBuilder.addQueryParameter("originplace", model.get("from").toString().split(DASHSEPARATOR)[0] + DASHSEPARATOR + SKYKEYWORD);
//        urlBuilder.addQueryParameter("destinationplace", model.get("to").toString().split(DASHSEPARATOR)[0] + DASHSEPARATOR + SKYKEYWORD);
//        urlBuilder.addQueryParameter("outboundpartialdate", model.get("departure").toString());
//        if(model.get("options").toString() == "RoundTrip") {
//            urlBuilder.addQueryParameter("inboundpartialdate", model.get("return").toString());        	
//        }
        
        String originplace = model.get("from").toString().split(" ")[0];
        String destinationplace = model.get("to").toString().split(" ")[0];
        String outboundpartialdate = model.get("departure").toString().split(" ")[0];
        String inboundpartialdate = "";
        if(model.get("options").toString() == "RoundTrip") {
        	inboundpartialdate = model.get("return").toString().split(" ")[0];
        }        
 

        String url = urlBuilder.build().toString() + FORWARDSLASHSEPARATOR + originplace + FORWARDSLASHSEPARATOR + destinationplace + FORWARDSLASHSEPARATOR + outboundpartialdate +  QUERYSEPARATOR + "inboundpartialdate=" + inboundpartialdate;
        
        OkHttpClient client = new OkHttpClient();
        
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "d81debeabemsh298ee99a6fadd38p1c39d1jsnf31e18d621be")
                .build();

        Response response = client.newCall(request).execute();

        if(response.isSuccessful()){
            String jsonData = response.body().string();
            JSONObject Jobject = new JSONObject(jsonData);
            
            JSONArray placesJarray = Jobject.getJSONArray("Places");
            List<Place> placelist = new ArrayList<Place>();
            for (int i = 0; i < placesJarray.length(); i++) {
                JSONObject object     = placesJarray.getJSONObject(i);
                Place place = new Place();
                place.setPlaceId(String.valueOf(object.getLong("PlaceId")));
                place.setPlaceName(object.getString("Name"));
                place.setCityId(object.getString("CityId"));
                place.setCountryName(object.getString("CountryName"));
                placelist.add(place);
            }
            
            
            JSONArray carriersJArray = Jobject.getJSONArray("Carriers");
            List<Carrier> carrierlist = new ArrayList<Carrier>();
            for (int i = 0; i < carriersJArray.length(); i++) {
                JSONObject object = carriersJArray.getJSONObject(i);
                Carrier carrier = new Carrier();
                carrier.setCarrierId(String.valueOf(object.getLong("CarrierId")));
                carrier.setName(object.getString("Name"));
                carrierlist.add(carrier);
            }
            
            
            JSONArray quotesJArray = Jobject.getJSONArray("Quotes");
            for (int i = 0; i < quotesJArray.length(); i++) {
                JSONObject object = quotesJArray.getJSONObject(i);
                QuoteViewModel quote = new QuoteViewModel();
                quote.setQuoteId(String.valueOf(object.getLong("QuoteId")));
                quote.setOrigin(placelist.get(0));
                quote.setDestination(placelist.get(1));
                quote.setPrice(object.getLong("MinPrice"));
                quote.setIsDirect(object.getBoolean("Direct"));
                quote.setCarrier(carrierlist.get(i));
                
                quoteslist.add(quote);
            }

        }
        return quoteslist;

    }

}