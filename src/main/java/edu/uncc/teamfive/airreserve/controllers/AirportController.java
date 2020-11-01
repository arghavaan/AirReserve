package edu.uncc.teamfive.airreserve.controllers;

import edu.uncc.teamfive.airreserve.models.Airport;
import edu.uncc.teamfive.airreserve.models.Place;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AirportController {

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

}