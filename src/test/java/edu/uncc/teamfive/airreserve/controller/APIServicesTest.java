package edu.uncc.teamfive.airreserve.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.uncc.teamfive.airreserve.Services.APIServices;
import edu.uncc.teamfive.airreserve.models.Carrier;
import edu.uncc.teamfive.airreserve.models.FastPlace;
import edu.uncc.teamfive.airreserve.models.Place;
import edu.uncc.teamfive.airreserve.models.QuoteViewModel;

@SpringBootTest
public class APIServicesTest {
	
	@Autowired
	private APIServices classUnderTest;
	
	
	
	@Test
	public void getPlaces_Values () throws IOException {
		FastPlace[] place = classUnderTest.getPlaces("Charlotte");
		 List<Object> placelist = Arrays.asList(place);
		assertNotNull(placelist);
		
	}
	
	@Test
	public void getPlaces_OtherThenGivenPlace () throws IOException {
		FastPlace[] place = classUnderTest.getPlaces("New York");
		 List<Object> placelist = Arrays.asList(place);
		assertNotNull(placelist);
		
	}
	
	@Test
	public void getPlaces_ValuesEmpty() throws IOException {
		FastPlace[] place = classUnderTest.getPlaces("");
		 List<Object> placelist = Arrays.asList(place);
		assertNotNull(placelist);
		
	}
	
	
	@Test
	public void getQuotes_Values() throws IOException {
		Map<String, Object> model = new HashMap<>();
	     model.put("from", "CLT-sky");
	     model.put("to", "JFK-sky");
	     model.put("departure", "2020-11-26");
		model.put("options", "RoundTrip");
		model.put("return", "2020-12-16");
		QuoteViewModel quoteViewmodel = new QuoteViewModel();
		Carrier carrier = new Carrier();
		carrier.setCarrierId("carrierId");
		carrier.setName("name");
		quoteViewmodel.setCarrier(carrier);
		Place place = new Place();
		place.setCityId("cityId");
		place.setCountryId("countryId");
		place.setCountryName("countryName");
		place.setPlaceId("placeId");
		place.setPlaceName("placeName");
		place.setRegionId("regionId");
		quoteViewmodel.setDestination(place);
		quoteViewmodel.setIsDirect(true);
		quoteViewmodel.setQuoteId("quoteId");
		quoteViewmodel.setOrigin(place);
		quoteViewmodel.setPrice(145L);
		List<QuoteViewModel> quoteslist = classUnderTest.getQuotes(model);
		quoteslist.add(quoteViewmodel);
		assertEquals(carrier, quoteViewmodel.getCarrier());
		assertEquals("quoteId", quoteViewmodel.getQuoteId());
		assertEquals(place, quoteViewmodel.getDestination());
		assertTrue(quoteViewmodel.getIsDirect());
		assertEquals(place,quoteViewmodel.getOrigin());
		assertEquals(145L, quoteViewmodel.getPrice());
		assertNotNull(quoteslist);
		assertNotNull(model);
	}
	
	
	@Test
	public void getQuotes_InvalidPlace() throws IOException {
		Map<String, Object> model = new HashMap<>();
	     model.put("from", "Charlotte");
	     model.put("to", "Florida");
	     model.put("departure", "2020-11-26");
		model.put("options", "RoundTrip");
		model.put("return", "2020-12-16");
		QuoteViewModel quoteViewmodel = new QuoteViewModel();
		quoteViewmodel.setCarrier(null);
		
		quoteViewmodel.setDestination(null);
		quoteViewmodel.setIsDirect(true);
		quoteViewmodel.setQuoteId("quoteId");
		quoteViewmodel.setOrigin(null);
		quoteViewmodel.setPrice(145L);
		List<QuoteViewModel> quoteslist = classUnderTest.getQuotes(model);
		quoteslist.add(quoteViewmodel);
		assertNull(quoteViewmodel.getCarrier());
		assertEquals("quoteId", quoteViewmodel.getQuoteId());
		assertNull( quoteViewmodel.getDestination());
		assertTrue(quoteViewmodel.getIsDirect());
		assertNull(quoteViewmodel.getOrigin());
		assertEquals(145L, quoteViewmodel.getPrice());
		assertNotNull(quoteslist);
		assertNotNull(model);
	}
	
	@Test
	public void getQuotes_FutureDates() throws IOException {
		Map<String, Object> model = new HashMap<>();
	     model.put("from", "CLT-sky");
	     model.put("to", "JFK-sky");
	     model.put("departure", "2020-12-16");
		model.put("options", "RoundTrip");
		model.put("return", "2020-12-26");
		QuoteViewModel quoteViewmodel = new QuoteViewModel();
		Carrier carrier = new Carrier();
		carrier.setCarrierId("carrierId");
		carrier.setName("name");
		quoteViewmodel.setCarrier(carrier);
		Place place = new Place();
		place.setCityId("cityId");
		place.setCountryId("countryId");
		place.setCountryName("countryName");
		place.setPlaceId("placeId");
		place.setPlaceName("placeName");
		place.setRegionId("regionId");
		quoteViewmodel.setDestination(place);
		quoteViewmodel.setIsDirect(true);
		quoteViewmodel.setQuoteId("quoteId");
		quoteViewmodel.setOrigin(place);
		quoteViewmodel.setPrice(145L);
		List<QuoteViewModel> quoteslist = classUnderTest.getQuotes(model);
		quoteslist.add(quoteViewmodel);
		assertEquals(carrier, quoteViewmodel.getCarrier());
		assertEquals("quoteId", quoteViewmodel.getQuoteId());
		assertEquals(place, quoteViewmodel.getDestination());
		assertTrue(quoteViewmodel.getIsDirect());
		assertEquals(place,quoteViewmodel.getOrigin());
		assertEquals(145L, quoteViewmodel.getPrice());
		assertNotNull(quoteslist);
		assertNotNull(model);

		
	}
	
	
	
	


}
