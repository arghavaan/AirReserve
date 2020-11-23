package edu.uncc.teamfive.airreserve.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarrierTest {
	
	private Carrier classUnderTest;
	
	@BeforeEach
	public void setup() {
		classUnderTest = new Carrier();
	}
	
	@Test
	public void settersGettersTest() {
		classUnderTest.setCarrierId("CarrierId");
		classUnderTest.setName("Name");
		assertEquals("Name",classUnderTest.getName());
		assertEquals("CarrierId", classUnderTest.getCarrierId());
		
		assertNotEquals("nirali", classUnderTest.getName());
		assertNotEquals("1233", classUnderTest.getCarrierId());
		
		
		assertNotNull(classUnderTest.getCarrierId());
		assertNotNull(classUnderTest.getName());
		
	}
	
	@Test
	public void settersGettersTest_BlankValues() {
		classUnderTest.setCarrierId("");
		classUnderTest.setName("");
		assertEquals("",classUnderTest.getName());
		assertEquals("", classUnderTest.getCarrierId());
		
		
	}
	
	@Test
	public void settersGettersTest_Null() {
		classUnderTest.setCarrierId(null);
		classUnderTest.setName(null);
		assertNull(classUnderTest.getName());
		assertNull(classUnderTest.getCarrierId());
		
		
	}

}
