package edu.uncc.teamfive.airreserve.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.net.ConnectException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import edu.uncc.teamfive.airreserve.Services.APIServices;
import edu.uncc.teamfive.airreserve.controllers.AirportController;
import edu.uncc.teamfive.airreserve.models.Airport;
import edu.uncc.teamfive.airreserve.models.Place;
import edu.uncc.teamfive.airreserve.models.QuoteViewModel;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 
 * Using spring mockMVC approach for testing, since we don't want to start the
 * server and just want to test the layer below that, where Spring handles the
 * incoming HTTP request and hands it off to the controller. Full stack is user
 * and code will be called exactly the same way as if it was processing the real
 * HTTP request but without the cost of starting server.
 * 
 *
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AirportControllerTest {
	public static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("UTF-8"));

	private AirportController classUnderTest;
	private MockMvc mockMvc;
	@Autowired
	private APIServices apiServices;

	@BeforeEach
	public void setup() {
		classUnderTest = new AirportController();
		mockMvc = MockMvcBuilders.standaloneSetup(classUnderTest).build();

	}

	@DisplayName("when application tries the /airports api, the values that we have injected are as expected")
	@Test
	public void airportsCall_EqualstheValues() throws Exception {
		MvcResult result = mockMvc.perform(get("/airports")).andExpect(status().isOk()).andReturn();

		String content = result.getResponse().getContentAsString();

		String name = "Charlotte";
		Model model = Mockito.mock(Model.class);
		model.addAttribute("name", name);

		List<String> entries = new ArrayList<String>();
		Airport testAirport = new Airport();
		testAirport.AirportID = 1;
		testAirport.Name = "JFK";
		testAirport.CityName = "NY";
		testAirport.CountryName = "US";
		entries = Arrays.asList(new String[] { String.valueOf(testAirport.AirportID), testAirport.Name,
				testAirport.CountryName, testAirport.CityName });
		assertNotNull(classUnderTest.index(name, model));
		assertNotNull(content);
		assertNotEquals(content, entries);

	}

	@DisplayName("when application tries the /airports api, the values that we have injected are not as expected")
	@Test
	public void airportsCall_notEqualstheValues() throws Exception {
		MvcResult result = mockMvc.perform(get("/airports")).andExpect(status().isOk()).andReturn();

		String content = result.getResponse().getContentAsString();

		List<String> entries = new ArrayList<String>();
		Airport testAirport = new Airport();
		testAirport.AirportID = 2;
		testAirport.Name = "CLT";
		testAirport.CityName = "NC";
		testAirport.CountryName = "US";
		entries = Arrays.asList(new String[] { String.valueOf(testAirport.AirportID), testAirport.Name,
				testAirport.CountryName, testAirport.CityName });
		assertNotNull(content);
		assertNotEquals(content, entries);

	}

	/*
	 * @DisplayName("when application tries to connect to the skyscanner api with /airportsLive api, "
	 * + "gives null pointer error because it doesn't connect to api")
	 * 
	 * @Test public void airportsLiveCall_EqualstheValues() throws Exception {
	 * assertThrows(NullPointerException.class, () ->{ MvcResult result =
	 * mockMvc.perform(get("/airportslive")) .andExpect(status().isOk())
	 * .andReturn();
	 * 
	 * classUnderTest.live("Charlotte"); String content =
	 * result.getResponse().getContentAsString(); Place[] place =
	 * apiServices.getPlaces("Charlotte"); List<Object> placelist =
	 * Arrays.asList(place); assertNotNull(content); assertNotNull(placelist); }); }
	 */

	@DisplayName("gives 400 error - when we submit the /airportslive")
	@Test
	public void airportsLiveCall_notConnected() throws Exception {
		MvcResult result = mockMvc.perform(post("/airportslive")).andExpect(status().is4xxClientError()).andReturn();

		String content = result.getResponse().getContentAsString();
		assertNotNull(content);
		assertNotEquals(content, "content");

	}

	@DisplayName("when application tries to connect to the skyscanner api with /getQuotes api, " + "and not connected")
	@Test
	public void getQuotes_notConnected() throws Exception {
		MvcResult result = mockMvc.perform(get("/getQuotes")).andExpect(status().is4xxClientError()).andReturn();

		String content = result.getRequest().getContentType();
		assertNull(content);

	}

	@DisplayName("when application tries to connect to the skyscanner api with /getQuotes api, " + " connected")
//	@Test	
	public void getQuotesConnected() throws Exception {
		Map<String, Object> model = new HashMap<>();
		List<QuoteViewModel> quoteslist = new ArrayList<QuoteViewModel>();
		String originplace = "Charlotte";
		String destinationplace = "North Carolina";
		String outboundpartialdate = "10/19/2020";
		String inboundpartialdate = "11/12/2020";
		model.put(originplace, originplace);
		model.put(destinationplace, destinationplace);
		model.put(outboundpartialdate, outboundpartialdate);
		model.put(inboundpartialdate, inboundpartialdate);
		classUnderTest.quotes(model);

	}

}
