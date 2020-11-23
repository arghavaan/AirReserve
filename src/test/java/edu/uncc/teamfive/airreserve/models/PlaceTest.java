package edu.uncc.teamfive.airreserve.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlaceTest {
	
	private Place classUnderTest;
	
	@BeforeEach
	public void setup() {
		classUnderTest = new Place();
	}
	
	@Test
	public void settersGettersTest() {
		String name = "LA";
		classUnderTest.setPlaceId("PlaceId");
		classUnderTest.setPlaceName("PlaceName");
		classUnderTest.setCountryId("CountryId");
		classUnderTest.setRegionId("RegionId");
		classUnderTest.setCityId("CityId");
		classUnderTest.setCountryName("CountryName");
		classUnderTest.setAdditionalProperty("name", name);
		
		
		assertEquals("PlaceId", classUnderTest.getPlaceId());
		assertEquals("PlaceName", classUnderTest.getPlaceName());
		assertEquals("CountryId", classUnderTest.getCountryId());
		assertEquals("RegionId", classUnderTest.getRegionId());
		assertEquals("CityId", classUnderTest.getCityId());
		assertEquals("CountryName",classUnderTest.getCountryName());
		assertNotNull(classUnderTest.getAdditionalProperties());
		assertNotNull(classUnderTest.toString());
		
		assertNotEquals("id", classUnderTest.getPlaceId());
		assertNotEquals("Place", classUnderTest.getPlaceName());
		assertNotEquals("Country", classUnderTest.getCountryId());
		assertNotEquals("Region", classUnderTest.getRegionId());
		assertNotEquals("City", classUnderTest.getCityId());
		assertNotEquals("Country",classUnderTest.getCountryName());			
	}
	
	@Test
	public void settersGettersTest_BlankValues() {
		String name = "";
		classUnderTest.setPlaceId("");
		classUnderTest.setPlaceName("");
		classUnderTest.setCountryId("");
		classUnderTest.setRegionId("");
		classUnderTest.setCityId("");
		classUnderTest.setCountryName("");
		classUnderTest.setAdditionalProperty("", name);
		
		
		assertEquals("", classUnderTest.getPlaceId());
		assertEquals("", classUnderTest.getPlaceName());
		assertEquals("", classUnderTest.getCountryId());
		assertEquals("", classUnderTest.getRegionId());
		assertEquals("", classUnderTest.getCityId());
		assertEquals("",classUnderTest.getCountryName());
		assertNotNull(classUnderTest.getAdditionalProperties());
		assertNotNull(classUnderTest.toString());
		
		
	}
	
	
	@Test
	public void settersGettersTest_NullValues() {
		String name = null;
		classUnderTest.setPlaceId(null);
		classUnderTest.setPlaceName(null);
		classUnderTest.setCountryId(null);
		classUnderTest.setRegionId(null);
		classUnderTest.setCityId(null);
		classUnderTest.setCountryName(null);
		classUnderTest.setAdditionalProperty(null, name);
		
		
		assertNull(classUnderTest.getPlaceId());
		assertNull(classUnderTest.getPlaceName());
		assertNull(classUnderTest.getCountryId());
		assertNull(classUnderTest.getRegionId());
		assertNull(classUnderTest.getCityId());
		assertNull(classUnderTest.getCountryName());
		assertNotNull(classUnderTest.getAdditionalProperties());
		assertNotNull(classUnderTest.toString());
		
		
	}

}
