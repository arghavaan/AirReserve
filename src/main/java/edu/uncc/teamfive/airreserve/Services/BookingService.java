package edu.uncc.teamfive.airreserve.Services;

import java.time.Instant;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.uncc.teamfive.airreserve.models.BookingModel;
import edu.uncc.teamfive.airreserve.models.ComfirmationViewModel;
import edu.uncc.teamfive.airreserve.models.CreditCard;
import edu.uncc.teamfive.airreserve.models.Person;

@Service
public class BookingService {

	public ComfirmationViewModel bookNow(Map<String, Object> model) {
    	BookingModel bookingModel = new BookingModel();

    	try {
			
			Double amount = Double.parseDouble(model.get("price").toString());
			
	    	bookingModel.setPerson(new Person());
	    	bookingModel.setComfirmationViewModel(new ComfirmationViewModel());
	    	
	    	bookingModel.getPerson().setEmailId(model.get("email").toString());
	    	bookingModel.getPerson().setUserName(model.get("uname").toString());
	    	bookingModel.getPerson().setPassword(model.get("pwd").toString());
	    	bookingModel.getPerson().setFirstName(model.get("fname").toString());
	    	bookingModel.getPerson().setLastName(model.get("lname").toString()); 
	    	bookingModel.getPerson().setMobile(model.get("mobile").toString());
	    	bookingModel.getPerson().setPassportNo(model.get("passno").toString());
	    	
	    	if(model.get("paymentType").toString().equalsIgnoreCase("Credit")) {
	        	CreditCard creditCard =  new CreditCard();
	        	creditCard.setCardHolderName(model.get("holdername").toString());
	        	creditCard.setCardNumber(model.get("cardno").toString());
	        	creditCard.setCvc(model.get("cvcpwd").toString());
	        	creditCard.setExpiryMonth(Long.parseLong(model.get("expmonth").toString()));
	        	creditCard.setExpiryYear(Long.parseLong(model.get("expyear").toString()));    		
	        	bookingModel.setPaymentMethod(creditCard);
	    	}
	        
	    	Boolean isAccepted = bookingModel.getPaymentMethod().acceptPayment(amount);
	    	if(isAccepted) {
	    		Long epoch = Instant.now().toEpochMilli();
	    		String tripId = model.get("quoteId").toString() +  epoch;
	    		bookingModel.getComfirmationViewModel().setIsAccepted(isAccepted);
	    		bookingModel.getComfirmationViewModel().setFlightDetails(model.get("flightDetails").toString());
	    		bookingModel.getComfirmationViewModel().setMessage("Success!!");
	    		bookingModel.getComfirmationViewModel().setTripId(tripId);
	    	}else {
	    		bookingModel.getComfirmationViewModel().setIsAccepted(isAccepted);
	    		bookingModel.getComfirmationViewModel().setMessage("Insufficient Balance!!");
	    	}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}

    	return bookingModel.getComfirmationViewModel();
	}
}
