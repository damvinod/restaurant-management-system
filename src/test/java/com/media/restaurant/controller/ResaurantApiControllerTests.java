package com.media.restaurant.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.media.restaurant.domain.Users;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ResaurantApiControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	StringBuilder getAllUsersBuilder = new StringBuilder("");

	@Before
	public void createUri() {
		getAllUsersBuilder.append("/addUser");
	}

	private HttpEntity<String> getEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String requestJson = "{\"userName\": \"string\",\"role\": \"on\",\"role\": \"DR\","
	            + "\"servicingAgentId\": \"1037875\"}";
		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		return entity;
	}

	@Test
	public void addUser() throws Exception {
		HttpEntity<String> entity = getEntity();
		ResponseEntity<Users> addressInformationResponse = restTemplate.exchange(getAllUsersBuilder.toString(),
				HttpMethod.POST, entity, Users.class);

		assertEquals(HttpStatus.OK, addressInformationResponse.getStatusCode());

	}
	
	//@Test
	public void getAllUsers() throws Exception {
		ResponseEntity<Users> addressInformationResponse = restTemplate.exchange("/getAllUsers",
				HttpMethod.GET, null, Users.class);

		assertEquals(HttpStatus.OK, addressInformationResponse.getStatusCode());

	}
}
