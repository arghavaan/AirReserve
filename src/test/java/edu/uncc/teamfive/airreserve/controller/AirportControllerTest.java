package edu.uncc.teamfive.airreserve.controller;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import edu.uncc.teamfive.airreserve.Services.APIServices;
import edu.uncc.teamfive.airreserve.controllers.AirportController;
import edu.uncc.teamfive.airreserve.models.Airport;

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
	private APIServices apiServices;

	@BeforeEach
	public void setup() {
		classUnderTest = new AirportController();
		mockMvc = MockMvcBuilders.standaloneSetup(classUnderTest).build();
		apiServices = new APIServices();
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

	@DisplayName("Testing the apiService, with the place")
	@Test
	public void liveTest_Place() throws IOException {

		apiServices.getPlaces("Charlotte");
		ReflectionTestUtils.setField(classUnderTest, "apiServices", apiServices);
		assertNotNull(classUnderTest.live("Charlotte"));
	}
	
	@DisplayName("Testing the apiService, withoutthe place")
	@Test
	public void liveTest_PlaceEmpty() throws IOException {

		apiServices.getPlaces("");
		ReflectionTestUtils.setField(classUnderTest, "apiServices", apiServices);
		assertNotNull(classUnderTest.live(""));
		
	}
	
	@DisplayName("Testing the apiService, withoutthe place")
	@Test
	public void liveTest_PlaceNull() throws IOException {

		apiServices.getPlaces(null);
		ReflectionTestUtils.setField(classUnderTest, "apiServices", apiServices);
		assertNull(classUnderTest.live(null));
		
	}
	
	@Test
	public void getQuotes() throws IOException {
		Map<String, Object> model = new HashMap<>();
	     model.put("from", "CLT-sky");
	     model.put("to", "JFK-sky");
	     model.put("departure", "2020-11-26");
		model.put("options", "RoundTrip");
		model.put("return", "2020-12-16");
		apiServices.getQuotes(model);
		ReflectionTestUtils.setField(classUnderTest, "apiServices", apiServices);
		assertNotNull(classUnderTest.quotes(model));
	}

}
