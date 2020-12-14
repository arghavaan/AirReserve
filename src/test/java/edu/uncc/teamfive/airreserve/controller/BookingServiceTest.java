package edu.uncc.teamfive.airreserve.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.uncc.teamfive.airreserve.Services.BookingService;
import edu.uncc.teamfive.airreserve.models.BookingModel;
import edu.uncc.teamfive.airreserve.models.ComfirmationViewModel;
import edu.uncc.teamfive.airreserve.models.CreditCard;
import edu.uncc.teamfive.airreserve.models.Person;


public class BookingServiceTest {
	
	private BookingService classUnderTest;
	
	@BeforeEach
	public void setup() {
		classUnderTest = new BookingService();
	}
	
	
	@Test
	public void bookNowTest_ValidValuesANDPaymentUnder300() {
		Map<String,Object> model = new HashMap<>();
		BookingModel bookingModel = new BookingModel();
		Person person = new Person();
		CreditCard creditCard =  new CreditCard();
		person.setEmailId("emailId");
		person.setFirstName("firstName");
		person.setLastName("lastName");
		person.setMobile("mobile");
		person.setPassportNo("passportNumber");
		person.setPassword("password");
		person.setUserName("username");
		bookingModel.setPerson(person);
		
		creditCard.setCardHolderName("holdername");
		creditCard.setCardNumber("cardno");
		creditCard.setCvc("cvc");
		creditCard.setExpiryMonth(3);
		creditCard.setExpiryYear(3);
		
		ComfirmationViewModel comfirmationViewModel = new ComfirmationViewModel();
		comfirmationViewModel.setFlightDetails("flightDetails");
		comfirmationViewModel.setIsAccepted(true);
		comfirmationViewModel.setMessage("messageId");
		comfirmationViewModel.setTripId("tripId");
		bookingModel.setComfirmationViewModel(comfirmationViewModel);
		
		model.put("price", 100.00);
		model.put("email", "emailId");
		model.put("uname", "username");
		model.put("pwd", "password");
		model.put("fname", "nirali");
		model.put("lname", "patel");
		model.put("mobile", "9802267034");
		model.put("passno", "passNumber");
		model.put("paymentType", "Credit");
		model.put("holdername", "holdername");
		model.put("cardno", "cardno");
		model.put("cvcpwd","cvcpwd");
		model.put("expmonth",5);
		model.put("expyear",8);
		model.put("quoteId", "quoteId");
		model.put("flightDetails", "flightDetails");
		
		assertEquals("emailId", person.getEmailId());
		assertEquals("firstName", person.getFirstName());
		assertEquals("lastName", person.getLastName());
		assertEquals("mobile", person.getMobile());
		assertEquals("passportNumber", person.getPassportNo());
		assertEquals("password", person.getPassword());
		assertEquals("username", person.getUserName());
		assertEquals(person, bookingModel.getPerson());
		
		assertEquals("holdername",creditCard.getCardHolderName());
		assertEquals("cardno",creditCard.getCardNumber());
		assertEquals("cvc",creditCard.getCvc());
		assertEquals(3,creditCard.getExpiryMonth());
		assertEquals(3,creditCard.getExpiryYear());
		
		assertEquals("flightDetails", comfirmationViewModel.getFlightDetails());
		assertTrue(comfirmationViewModel.getIsAccepted());
		assertEquals("messageId", comfirmationViewModel.getMessage());
		assertEquals("tripId", comfirmationViewModel.getTripId());
		
		assertEquals(comfirmationViewModel,bookingModel.getComfirmationViewModel());
		assertNotNull(classUnderTest.bookNow(model));
	}
	
	@Test
	public void bookNowTest_ValidValuesANDPaymentAbove300() {
		Map<String,Object> model = new HashMap<>();
		BookingModel bookingModel = new BookingModel();
		
		CreditCard creditCard =  new CreditCard();
		
		bookingModel.setPerson(null);
		
		creditCard.setCardHolderName("");
		creditCard.setCardNumber("");
		creditCard.setCvc("");
		creditCard.setExpiryMonth(0);
		creditCard.setExpiryYear(0);
		
		
		bookingModel.setComfirmationViewModel(null);
		
		model.put("price", 400.00);
		model.put("email", "emailId");
		model.put("uname", "username");
		model.put("pwd", "password");
		model.put("fname", "nirali");
		model.put("lname", "patel");
		model.put("mobile", "9802267034");
		model.put("passno", "passNumber");
		model.put("paymentType", "Credit");
		model.put("holdername", "holdername");
		model.put("cardno", "cardno");
		model.put("cvcpwd","cvcpwd");
		model.put("expmonth",5);
		model.put("expyear",8);
		model.put("quoteId", "quoteId");
		model.put("flightDetails", "flightDetails");
		
		
		assertNull( bookingModel.getPerson());
		
		assertEquals("",creditCard.getCardHolderName());
		assertEquals("",creditCard.getCardNumber());
		assertEquals("",creditCard.getCvc());
		assertEquals(0,creditCard.getExpiryMonth());
		assertEquals(0,creditCard.getExpiryYear());
		
		
		
		assertNull(bookingModel.getComfirmationViewModel());
		assertNotNull(classUnderTest.bookNow(model));
	}
	

	
	@Test
	public void bookNowTest_Exceptions() {
		Map<String,Object> model = new HashMap<>();
		BookingModel bookingModel = new BookingModel();
		Person person = new Person();
		CreditCard creditCard =  new CreditCard();
		person.setEmailId("");
		person.setFirstName("");
		person.setLastName("");
		person.setMobile("");
		person.setPassportNo("");
		person.setPassword("");
		person.setUserName("");
		bookingModel.setPerson(person);
		
		creditCard.setCardHolderName("holdername");
		creditCard.setCardNumber("cardno");
		creditCard.setCvc("cvc");
		creditCard.setExpiryMonth(3);
		creditCard.setExpiryYear(3);
		
		ComfirmationViewModel comfirmationViewModel = new ComfirmationViewModel();
		comfirmationViewModel.setFlightDetails("");
		comfirmationViewModel.setIsAccepted(false);
		comfirmationViewModel.setMessage("");
		comfirmationViewModel.setTripId("");
		bookingModel.setComfirmationViewModel(comfirmationViewModel);
		model.put("price", 50.00);
		model.put("email", "emailId");
		model.put("uname", "username");
		model.put("pwd", "password");
		model.put("fname", "nirali");
		model.put("lname", "patel");
		model.put("mobile", "9802267034");
		model.put("passno", "passNumber");
		model.put("paymentType", "Debit");
		model.put("holdername", "holdername");
		model.put("cardno", "cardno");
		model.put("cvcpwd","cvcpwd");
		model.put("expmonth",5);
		model.put("expyear",8);
		
		assertEquals("", person.getEmailId());
		assertEquals("", person.getFirstName());
		assertEquals("", person.getLastName());
		assertEquals("", person.getMobile());
		assertEquals("", person.getPassportNo());
		assertEquals("", person.getPassword());
		assertEquals("", person.getUserName());
		assertEquals(person, bookingModel.getPerson());
		
		assertEquals("holdername",creditCard.getCardHolderName());
		assertEquals("cardno",creditCard.getCardNumber());
		assertEquals("cvc",creditCard.getCvc());
		assertEquals(3,creditCard.getExpiryMonth());
		assertEquals(3,creditCard.getExpiryYear());
		
		assertEquals("", comfirmationViewModel.getFlightDetails());
		assertFalse(comfirmationViewModel.getIsAccepted());
		assertEquals("", comfirmationViewModel.getMessage());
		assertEquals("", comfirmationViewModel.getTripId());
		
		assertEquals(comfirmationViewModel,bookingModel.getComfirmationViewModel());
		assertNotNull(classUnderTest.bookNow(model));
	}
	

}
