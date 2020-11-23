package edu.uncc.teamfive.airreserve.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.uncc.teamfive.airreserve.models.FastPlace;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import edu.uncc.teamfive.airreserve.models.Carrier;
import edu.uncc.teamfive.airreserve.models.Place;
import edu.uncc.teamfive.airreserve.models.QuoteViewModel;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class APIServices {

	private static final String QUERYSEPARATOR = "?";
	private static final String FORWARDSLASHSEPARATOR = "/";
	
	public FastPlace[] getPlaces(String name) throws IOException {
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
            List<FastPlace> placelist = new ArrayList<FastPlace>();
            for (int i = 0; i < Jarray.length(); i++) {
                JSONObject object     = Jarray.getJSONObject(i);
                FastPlace place = new FastPlace();
                place.value = object.getString("PlaceId");
                place.label = object.getString("PlaceName") + " (" + object.getString("CountryName") + ")";
                placelist.add(place);
            }
            return placelist.toArray(new FastPlace[0]);
        }

        return new FastPlace[0];
	}
	
	public List<QuoteViewModel> getQuotes(Map<String, Object> model) throws IOException {
        
		List<QuoteViewModel> quoteslist = new ArrayList<QuoteViewModel>();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/US/USD/en-US").newBuilder();
        
        String originplace = model.get("from").toString();
        String destinationplace = model.get("to").toString();
        String outboundpartialdate = model.get("departure").toString();
        String inboundpartialdate = "";
        if(model.get("options").toString() == "RoundTrip") {
        	inboundpartialdate = model.get("return").toString();
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
