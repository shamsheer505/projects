package com.hotelbooking.customer.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.hotelbooking.customer.HotelBookingApplication;
import com.hotelbooking.customer.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelBookingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class hotelbookingcontrollerT {

	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void bookHotel() {
		
		User user = new User();
		user.setUserId("123");
		user.setNumberOfRooms(2);
		user.setRoomCategory("Single Room");
		
		HttpEntity<User> entity = new HttpEntity<User>(user, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/hotelbookingservice/api/rooms"),
				HttpMethod.POST, entity, String.class);
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
	
		assertTrue(actual.contains("/hotelbookingservice/api/rooms"));


		
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
