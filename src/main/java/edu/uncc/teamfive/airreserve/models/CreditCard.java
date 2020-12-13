/**
 * 
 */
package edu.uncc.teamfive.airreserve.models;

/**
 * @author Virupax
 *
 */
public class CreditCard implements PaymentMethod {

	private String cardHolderName;
	private String cardNumber;
	private String cvc;
	private long expiryMonth;
	private long expiryYear;
	private static long balance = 300;
	
	
	@Override
	public Boolean acceptPayment(Double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public long getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(long expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public long getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(long expiryYear) {
		this.expiryYear = expiryYear;
	}
	
	

}
