package edu.uncc.teamfive.airreserve.models;

public class BookingModel {

	private Person person;
	private PaymentMethod paymentMethod;
	private QuoteViewModel quoteViewModel;
	
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public QuoteViewModel getQuoteViewModel() {
		return quoteViewModel;
	}
	public void setQuoteViewModel(QuoteViewModel quoteViewModel) {
		this.quoteViewModel = quoteViewModel;
	}
	
	
}
