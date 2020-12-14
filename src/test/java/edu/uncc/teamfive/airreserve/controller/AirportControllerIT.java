package edu.uncc.teamfive.airreserve.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.uncc.teamfive.airreserve.AirReserveApplication;


/* Launch the entire Spring Boot Application on a Random Port
 * Autowire the random port into the variable so that we can use it create the url.
 * We use entity so that we have the flexibility of adding in request headers in future.
 * Fire a GET request to the specify uri and get the response as a String.
 * Assert that the response contains expected fields.
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AirReserveApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AirportControllerIT {
	
	
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();

	@Test
	public void getAirportsTest() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/airports"),
				HttpMethod.GET, entity, String.class);
		
		
		String actual = "[{\"AirportID\":1,\"Name\":\"JFK\",\"CityName\":\"NY\",\"CountryName\":\"US\"}]";

		String expected = "[{\"AirportID\":\"1\",\"Name\":\"JFK\",\"CityName\":\"NY\",\"CountryName\":\"US\"}]";
		assertEquals(actual, response.getBody());
		assertNotEquals(expected, response.getBody());

	}
	
	@Test
	public void getPlacesTest() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/airportslive"),
				HttpMethod.GET, entity, String.class);
		
		String expected = "[{\"label\":\"Charlotte Douglas (United States)\",\"value\":\"CLT-sky\"},{\"label\":\"Charlottetown (Canada)\",\"value\":\"YYG-sky\"},{\"label\":\"Charlottesville (United States)\",\"value\":\"CHO-sky\"},{\"label\":\"Punta Gorda (United States)\",\"value\":\"PGD-sky\"},{\"label\":\"Charlottetown (Canada)\",\"value\":\"YHG-sky\"}]";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	
	
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
