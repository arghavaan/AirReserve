package edu.uncc.teamfive.airreserve.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConfirmationViewModelTest {
	
	private ComfirmationViewModel classUnderTest;
	
	@BeforeEach
	public void setup() {
		classUnderTest = new ComfirmationViewModel();
	}
	
	@Test
	public void setterGettersTest_withvalues() {
		classUnderTest.setFlightDetails("flightDetails");
		classUnderTest.setMessage("message");
		classUnderTest.setTripId("tripId");
		classUnderTest.setIsAccepted(true);
		
		assertEquals("flightDetails", classUnderTest.getFlightDetails());
		assertEquals("message", classUnderTest.getMessage());
		assertEquals("tripId", classUnderTest.getTripId());
		assertTrue(classUnderTest.getIsAccepted());	
	}
	
	@Test
	public void setterGettersTest_withEmptyValues() {
		classUnderTest.setFlightDetails("");
		classUnderTest.setMessage("");
		classUnderTest.setTripId("");
		classUnderTest.setIsAccepted(false);
		
		assertEquals("", classUnderTest.getFlightDetails());
		assertEquals("", classUnderTest.getMessage());
		assertEquals("", classUnderTest.getTripId());
		assertFalse(classUnderTest.getIsAccepted());	
	}
	
	@Test
	public void setterGettersTest_withNullvalues() {
		classUnderTest.setFlightDetails(null);
		classUnderTest.setMessage(null);
		classUnderTest.setTripId(null);
		classUnderTest.setIsAccepted(false);
		
		assertNull(classUnderTest.getFlightDetails());
		assertNull(classUnderTest.getMessage());
		assertNull(classUnderTest.getTripId());
		assertFalse(classUnderTest.getIsAccepted());	
	}

}
