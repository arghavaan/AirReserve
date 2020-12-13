package edu.uncc.teamfive.airreserve.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.uncc.teamfive.airreserve.Services.BookingService;


public class BookingServiceTest {
	
	private BookingService classUnderTest;
	
	@BeforeEach
	public void setup() {
		classUnderTest = new BookingService();
	}
	
	
	@Test
	public void bookNowTest_ValidValuesANDPaymentUnder300() {
		Map<String,Object> model = new HashMap<>();
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
		assertNotNull(classUnderTest.bookNow(model));
	}
	
	@Test
	public void bookNowTest_ValidValuesANDPaymentAbove300() {
		Map<String,Object> model = new HashMap<>();
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
		assertNotNull(classUnderTest.bookNow(model));
	}
	
	@Test
	public void bookNowTest_EmptyValuesANDPaymentAbove300() {
		assertThrows(NullPointerException.class, () -> {
		Map<String,Object> model = new HashMap<>();
		model.put("price", 400.00);
		model.put("email", "");
		model.put("uname", "");
		model.put("pwd", "");
		model.put("fname", "");
		model.put("lname", "");
		model.put("mobile", "");
		model.put("passno", "");
		model.put("paymentType", "");
		model.put("holdername", "");
		model.put("cardno", "");
		model.put("cvcpwd","");
		model.put("expmonth",0);
		model.put("expyear",0);
		classUnderTest.bookNow(model);
		});
	}
	

}
