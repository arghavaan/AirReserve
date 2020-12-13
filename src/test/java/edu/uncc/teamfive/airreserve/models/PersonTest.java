package edu.uncc.teamfive.airreserve.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonTest {
	
	private Person classUnderTest;
	
	@BeforeEach
	public void setup() {
		classUnderTest = new Person();
	}
	
	@Test
	public void setterGettersTest() {
		Person person = new Person();
		person.setEmailId("emailId");
		person.setFirstName("firstName");
		person.setLastName("lastName");
		person.setMobile("mobile");
		person.setPassportNo("passportNo");
		person.setPassword("password");
		person.setUserName("userName");
	
		person.setDob(new Date());
		assertEquals("emailId", person.getEmailId());
		assertEquals("firstName",person.getFirstName());
		assertEquals("lastName", person.getLastName());
		assertEquals("mobile", person.getMobile());
		assertEquals("passportNo", person.getPassportNo());
		assertEquals("password", person.getPassword());
		assertEquals("userName", person.getUserName());
		assertNotNull(person.getDob());
	}
	
	@Test
	public void setterGettersTest_EmptyValues() {
		Person person = new Person();
		person.setEmailId("");
		person.setFirstName("");
		person.setLastName("");
		person.setMobile("");
		person.setPassportNo("");
		person.setPassword("");
		person.setUserName("");
		assertEquals("", person.getEmailId());
		assertEquals("",person.getFirstName());
		assertEquals("", person.getLastName());
		assertEquals("", person.getMobile());
		assertEquals("", person.getPassportNo());
		assertEquals("", person.getPassword());
		assertEquals("", person.getUserName());
	}
	
	@Test
	public void setterGettersTest_NullValues() {
		Person person = new Person();
		person.setEmailId(null);
		person.setFirstName(null);
		person.setLastName(null);
		person.setMobile(null);
		person.setPassportNo(null);
		person.setPassword(null);
		person.setUserName(null);
		assertNull(person.getEmailId());
		assertNull(person.getFirstName());
		assertNull( person.getLastName());
		assertNull(person.getMobile());
		assertNull( person.getPassportNo());
		assertNull( person.getPassword());
		assertNull(person.getUserName());
	}

}
