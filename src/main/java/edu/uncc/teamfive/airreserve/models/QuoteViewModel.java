package edu.uncc.teamfive.airreserve.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "QuoteId",
    "Origin",
    "Destination",
    "Price",
    "IsDirect",
    "Carrier"
})
public class QuoteViewModel {

	@JsonProperty("QuoteId")
    private String quoteId;
	@JsonProperty("Origin")
    private Place origin;
	@JsonProperty("Destination")
    private Place destination;
	@JsonProperty("Price")
    private Long price;
	@JsonProperty("IsDirect")
    private Boolean isDirect;
	@JsonProperty("Carrier")
    private Carrier carrier;

		
	
	@JsonProperty("QuoteId")
	public String getQuoteId() {
		return quoteId;
	}
	
	@JsonProperty("QuoteId")
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	
	
	@JsonProperty("Origin")
    public Place getOrigin() {
		return origin;
	}

	@JsonProperty("Origin")
    public void setOrigin(Place origin) {
		this.origin = origin;
	}

	@JsonProperty("Destination")
    public Place getDestination() {
		return destination;
	}

	@JsonProperty("Destination")
    public void setDestination(Place destination) {
		this.destination = destination;
	}

	@JsonProperty("Price")
	public Long getPrice() {
		return price;
	}

	@JsonProperty("Price")
	public void setPrice(Long price) {
		this.price = price;
	}

	@JsonProperty("IsDirect")
	public Boolean getIsDirect() {
		return isDirect;
	}

	@JsonProperty("IsDirect")
	public void setIsDirect(Boolean isDirect) {
		this.isDirect = isDirect;
	}

	@JsonProperty("Carrier")
	public Carrier getCarrier() {
		return carrier;
	}

	@JsonProperty("Carrier")
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	
	
	
}
