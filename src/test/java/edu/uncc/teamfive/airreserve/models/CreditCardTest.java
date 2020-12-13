package edu.uncc.teamfive.airreserve.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreditCardTest {
	
	private CreditCard classUnderTest;
	
	@BeforeEach
	public void setup() {
		classUnderTest = new CreditCard();
	}
	
	@Test
	public void setterGetters_balanceLessThen300() {
		classUnderTest.setCardHolderName("cardHolderName");
		classUnderTest.setCardNumber("cardNumber");
		classUnderTest.setCvc("cvc");
		classUnderTest.setExpiryMonth(3);
		classUnderTest.setExpiryYear(1);
		classUnderTest.acceptPayment(125.00);
		
		assertEquals("cardHolderName", classUnderTest.getCardHolderName());
		assertEquals("cardNumber", classUnderTest.getCardNumber());
		assertEquals("cvc", classUnderTest.getCvc());
		assertEquals(3, classUnderTest.getExpiryMonth());
		assertEquals(1,classUnderTest.getExpiryYear());
		
		
		
		
	}
	
	@Test
	public void setterGetters_GreaterThen300() {
		classUnderTest.setCardHolderName("");
		classUnderTest.setCardNumber("");
		classUnderTest.setCvc("");
		classUnderTest.setExpiryMonth(3);
		classUnderTest.setExpiryYear(1);
		classUnderTest.acceptPayment(345.00);
		
		
		
		assertEquals("", classUnderTest.getCardHolderName());
		assertEquals("", classUnderTest.getCardNumber());
		assertEquals("", classUnderTest.getCvc());
		assertNotEquals(0, classUnderTest.getExpiryMonth());
		assertNotEquals(3,classUnderTest.getExpiryYear());
		
	}

}
