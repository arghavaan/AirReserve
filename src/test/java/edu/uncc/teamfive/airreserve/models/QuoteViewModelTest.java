package edu.uncc.teamfive.airreserve.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuoteViewModelTest {
	
	private QuoteViewModel classUnderTest;
	
	@BeforeEach
	public void setup() {
		classUnderTest = new QuoteViewModel();
	}
	
	@Test
	public void setterGettersTest() {
		classUnderTest.setQuoteId("QuoteId");
		Place origin = new Place();
		origin.setPlaceId("PlaceId");
		origin.setPlaceName("PlaceName");
		origin.setCountryId("CountryId");
		origin.setRegionId("RegionId");
		origin.setCityId("CityId");
		origin.setCountryName("CountryName");
		classUnderTest.setOrigin(origin);
		classUnderTest.setDestination(origin);
		classUnderTest.setPrice(Long.valueOf(1864));
		classUnderTest.setIsDirect(true);
		Carrier carrier = new Carrier();
		carrier.setCarrierId("CarrierId");
		carrier.setName("Name");
		classUnderTest.setCarrier(carrier);
		
		
		assertEquals("QuoteId", classUnderTest.getQuoteId());
		assertEquals(origin, classUnderTest.getOrigin());
		assertEquals(origin, classUnderTest.getDestination());
		assertTrue(classUnderTest.getIsDirect());
		assertEquals(1864, classUnderTest.getPrice());
		assertEquals(carrier, classUnderTest.getCarrier());
		
		assertNotEquals("Id", classUnderTest.getQuoteId());	
		assertNotEquals(186, classUnderTest.getPrice());
	}
	
	@Test
	public void setterGettersTest_BlankValues() {
		classUnderTest.setQuoteId("");
		Place origin = new Place();
		origin.setPlaceId("");
		origin.setPlaceName("");
		origin.setCountryId("");
		origin.setRegionId("");
		origin.setCityId("");
		origin.setCountryName("");
		classUnderTest.setOrigin(origin);
		classUnderTest.setDestination(origin);
		classUnderTest.setPrice(Long.valueOf(65L));
		classUnderTest.setIsDirect(false);
		Carrier carrier = new Carrier();
		carrier.setCarrierId("");
		carrier.setName("");
		classUnderTest.setCarrier(carrier);
		
		
		assertEquals("", classUnderTest.getQuoteId());
		assertEquals(origin, classUnderTest.getOrigin());
		assertEquals(origin, classUnderTest.getDestination());
		assertFalse(classUnderTest.getIsDirect());
		assertEquals(carrier, classUnderTest.getCarrier());
		assertEquals(65, classUnderTest.getPrice());
		assertNotEquals("Id", classUnderTest.getQuoteId());	
	}
	
	@Test
	public void setterGettersTest_NullValues() {
		classUnderTest.setQuoteId(null);
		classUnderTest.setOrigin(null);
		classUnderTest.setDestination(null);
		classUnderTest.setPrice(null);
		classUnderTest.setIsDirect(false);
		classUnderTest.setCarrier(null);
		
		
		assertNull(classUnderTest.getQuoteId());
		assertNull(classUnderTest.getOrigin());
		assertNull(classUnderTest.getDestination());
		assertFalse(classUnderTest.getIsDirect());
		assertNull( classUnderTest.getCarrier());
		assertNull( classUnderTest.getPrice());
		assertNotEquals("Id", classUnderTest.getQuoteId());	
	}

}
