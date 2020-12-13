package edu.uncc.teamfive.airreserve.models;

public class BookingModel {

	private Person person;
	private PaymentMethod paymentMethod;
	private ComfirmationViewModel comfirmationViewModel;
	
	
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
	public ComfirmationViewModel getComfirmationViewModel() {
		return comfirmationViewModel;
	}
	public void setComfirmationViewModel(ComfirmationViewModel comfirmationViewModel) {
		this.comfirmationViewModel = comfirmationViewModel;
	}

	
	
}
