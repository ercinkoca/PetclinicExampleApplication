package com.ercin.petclinic.web;

import java.net.URI;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ercin.petclinic.model.Owner;

public class PetClinicRestControllerTests {
	
	private RestTemplate restTemplate;
	
	@Before
	public void setUp() {
		restTemplate=new RestTemplate();
	}
	
	@Test
	public void testGetOwnerById() {
		ResponseEntity<Owner> response = restTemplate.getForEntity("http://localhost:8080/rest/ownersById/2", Owner.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(),Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getFirstName(),Matchers.equalTo("Erçin"));
	}
	
	@Test
	public void testCreateOwner() {
		Owner owner=new Owner();
		owner.setFirstName("Erçinnn");
		owner.setLastName("Kocaaa");
		URI location=restTemplate.postForLocation("http://localhost:8080/rest/createOwner", owner);
		
		Owner owner2=restTemplate.getForObject(location, Owner.class);
		MatcherAssert.assertThat(owner2.getFirstName(), Matchers.equalTo(owner.getFirstName()));
		MatcherAssert.assertThat(owner2.getLastName(), Matchers.equalTo(owner.getLastName()));
	}
	
	@Test
	public void testUpdateOwner() {
		Owner owner=restTemplate.getForObject("http://localhost:8080/rest/ownersById/1", Owner.class);
		MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Erçin"));
		owner.setFirstName("Erçin2n");
		restTemplate.put("http://localhost:8080/rest/ownersById/1", owner);
		owner=restTemplate.getForObject("http://localhost:8080/rest/ownersById/1", Owner.class);
		MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Erçin2n"));
	}
	
	@Test
	public void testDeleteOwner() {
		restTemplate.delete("http://localhost:8080/rest/deleteOwner/1");
	}
	

}
