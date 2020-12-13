package edu.uncc.teamfive.airreserve.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "FirstName",
    "LastName",
    "Dob",
    "PassportNo",
    "EmailId",
    "UserName",
    "Password",
    "Mobile",
})
public class Person {

	@JsonProperty("FirstName")
    private String firstName;
	
	@JsonProperty("LastName")
    private String lastName;

	@JsonProperty("Dob")
    private Date dob;

	@JsonProperty("PassportNo")
    private String passportNo;
	
	@JsonProperty("EmailId")
    private String emailId;
	
	@JsonProperty("UserName")
    private String userName;
	
	@JsonProperty("Password")
    private String password;

	@JsonProperty("Mobile")
    private String mobile;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
