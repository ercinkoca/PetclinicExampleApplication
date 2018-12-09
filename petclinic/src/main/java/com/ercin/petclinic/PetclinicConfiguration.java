package com.ercin.petclinic;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PetclinicConfiguration {
	
	@Autowired
	private PetclinicProperties petClinicProperties;
	
	@PostConstruct
	public void init() {
		
		System.out.println("Display owners with pet:"+petClinicProperties.isDisplayOwnersWithPets());
	}

}
