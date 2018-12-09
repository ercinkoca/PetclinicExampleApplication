package com.ercin.petclinic.service;

import java.util.List;

import com.ercin.petclinic.exception.OwnerNotFoundException;
import com.ercin.petclinic.model.Owner;

public interface PetClinicService {

	List<Owner> findOwners();
	List<Owner> findOwnerByLastName(String lastName);
	Owner findOwner(Long id) throws OwnerNotFoundException;
	void createOwner(Owner owner);
	void updateOwner(Owner owner);
	void deleteOwner(Long id);
	
}
