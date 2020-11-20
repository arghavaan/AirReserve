package edu.uncc.teamfive.airreserve.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "QuoteId",
    "Origin",
    "Destination",
    "Price"
})
public class QuoteViewModel {

}
