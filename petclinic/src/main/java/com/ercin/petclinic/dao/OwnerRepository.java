package com.ercin.petclinic.dao;

import java.util.List;

import com.ercin.petclinic.model.Owner;

public interface OwnerRepository {
	
	List<Owner> findAll();
	Owner findById(Long id);
	List<Owner> findByLastName(String lastName);
	void create(Owner owner);
	Owner update(Owner owner);
	void delete(Long id);
	
}
