package edu.uncc.teamfive.airreserve.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookingModelTest {
	
	private BookingModel classUnderTest;
	private PaymentMethod payment;
	QuoteViewModel quoteViewModel;
	
	@BeforeEach
	public void setup() {
		classUnderTest = new BookingModel();
	}
	
	@Test
	public void setterGettersTest_paymentNull() {
		Person person = new Person();
		person.setEmailId("emailId");
		person.setFirstName("firstName");
		person.setLastName("lastName");
		person.setMobile("mobile");
		person.setPassportNo("passportNo");
		person.setPassword("password");
		person.setUserName("userName");
		
		classUnderTest.setPaymentMethod(null);
		
		classUnderTest.setPerson(person);
		classUnderTest.setComfirmationViewModel(null);
		
		assertNull(classUnderTest.getPaymentMethod());
		assertNotNull(classUnderTest.getPerson());
		assertEquals("emailId", person.getEmailId());
		assertEquals("firstName",person.getFirstName());
		assertEquals("lastName", person.getLastName());
		assertEquals("mobile", person.getMobile());
		assertEquals("passportNo", person.getPassportNo());
		assertEquals("password", person.getPassword());
		assertEquals("userName", person.getUserName());
		
	}
	
	@Test
	public void setterGettersTest_personNull() {
		
		
		
		classUnderTest.setPaymentMethod(null);
		
		classUnderTest.setPerson(null);
		classUnderTest.setComfirmationViewModel(null);
		assertNull(classUnderTest.getPaymentMethod());
		assertNull(classUnderTest.getPerson());
		assertNull(classUnderTest.getComfirmationViewModel());
		
	}

	
}
