package com.ercin.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ercin.petclinic.dao.OwnerRepository;
import com.ercin.petclinic.dao.PetRepository;
import com.ercin.petclinic.exception.OwnerNotFoundException;
import com.ercin.petclinic.model.Owner;

@Service
@Transactional
public class PetClinicServiceImpl implements PetClinicService {

	@Autowired
	private OwnerRepository ownerRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	
//	public void setOwnerRepository(OwnerRepository ownerRepository) {
//		this.ownerRepository = ownerRepository;
//	}
	
	@Override
	public List<Owner> findOwners() {
		return ownerRepository.findAll();
	}

	@Override
	public List<Owner> findOwnerByLastName(String lastName) {
		return ownerRepository.findByLastName(lastName);
	}

	@Override
	public Owner findOwner(Long id) throws OwnerNotFoundException {
		Owner owner=ownerRepository.findById(id);
		if(owner==null) {
			throw new OwnerNotFoundException("Owner bulunamadı");
		}else {
			return owner;
		}
	}

	@Override
	public void createOwner(Owner owner) {
		ownerRepository.create(owner);
	}

	@Override
	public void updateOwner(Owner owner) {
		ownerRepository.update(owner);

	}

	@Override
	public void deleteOwner(Long id) {
		petRepository.deleteByOwnerId(id);
		ownerRepository.delete(id);
		//if(true) throw new RuntimeException("testing rollback");
	}

}
