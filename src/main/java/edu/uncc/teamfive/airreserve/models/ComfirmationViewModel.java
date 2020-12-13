package edu.uncc.teamfive.airreserve.models;

public class ComfirmationViewModel {

	private Boolean isAccepted;
	private String flightDetails;
	private String tripId;
	private String message;
	
	public Boolean getIsAccepted() {
		return isAccepted;
	}
	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	public String getFlightDetails() {
		return flightDetails;
	}
	public void setFlightDetails(String flightDetails) {
		this.flightDetails = flightDetails;
	}
	public String getTripId() {
		return tripId;
	}
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
