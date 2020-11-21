package edu.uncc.teamfive.airreserve.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "CarrierId",
    "Name"
})
public class Carrier {

    @JsonProperty("CarrierId")
    private String carrierId;
	@JsonProperty("Name")
    private String name;
	
	
    @JsonProperty("CarrierId")
    public String getCarrierId() {
		return carrierId;
	}
    @JsonProperty("CarrierId")
	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}
	@JsonProperty("Name")
	public String getName() {
		return name;
	}
	@JsonProperty("Name")
	public void setName(String name) {
		this.name = name;
	}

}
