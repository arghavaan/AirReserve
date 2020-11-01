package edu.uncc.teamfive.airreserve.controllers;

import edu.uncc.teamfive.airreserve.models.Airport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}