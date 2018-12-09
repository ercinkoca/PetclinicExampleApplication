package com.ercin.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ercin.petclinic.exception.OwnerNotFoundException;
import com.ercin.petclinic.model.Owner;
import com.ercin.petclinic.service.PetClinicService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class PetClinicRestController {//Spring Boot ile RestFul servis yazma

	@Autowired
	private PetClinicService petClinicService;
	
	@RequestMapping(method=RequestMethod.GET,value="/owners")
	public ResponseEntity<List<Owner>> getOwners() {//Get metodu ile direk tüm kayıtları json tipinde döndürür.
		
		List<Owner> owners = petClinicService.findOwners();
		return ResponseEntity.ok(owners);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/ownersByLastName")
	public ResponseEntity<List<Owner>> getOwners(@RequestParam("ln") String lastName){
		//Yine get metodu ile bu kez @RequestParam anotasyonu yardımıyla parametre alarak json tipinde değer döndürür./rest/ownersByLastName?ln=Koca
		List<Owner> owners=petClinicService.findOwnerByLastName(lastName);
		return ResponseEntity.ok(owners);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/ownersById/{id}")
	public ResponseEntity <Owner> getOwnerById(@PathVariable("id") Long id){
		//Yine get metodu ile bu kez id parametresini kullanarak değer döndürüyor. /rest/ownersById/3
		Owner owners=petClinicService.findOwner(id);
		return ResponseEntity.ok(owners);
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/createOwner")
	public ResponseEntity<URI> createOwner(@RequestBody Owner owner){
		try {
			petClinicService.createOwner(owner);//yeni bir owner yaratmak için kullanılır.
			Long id=owner.getId();
			URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();//servlet sayesinde yeni bir id tanımlaması yapar.
			return ResponseEntity.created(location).build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/updateOwner/{id}")
	public ResponseEntity<?> updateOwner(@PathVariable("id") Long id,@RequestBody Owner ownerRequest){
		try {
			
			Owner owner =petClinicService.findOwner(id);
			owner.setFirstName(ownerRequest.getFirstName());
			owner.setLastName(ownerRequest.getLastName());
			petClinicService.updateOwner(owner);
			return ResponseEntity.ok().build();
		} catch (OwnerNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/deleteOwner/{id}")
	public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id,@RequestBody Owner ownerRequest){
		
		petClinicService.deleteOwner(id);
		return ResponseEntity.ok().build();
		
	}
	
}
