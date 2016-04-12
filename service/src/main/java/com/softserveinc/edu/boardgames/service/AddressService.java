package com.softserveinc.edu.boardgames.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.softserveinc.edu.boardgames.persistence.entity.Address;
import com.softserveinc.edu.boardgames.persistence.repository.AddressRepository;

@Service
@Transactional
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Transactional
    public void update(Address address) {
    	addressRepository.saveAndFlush(address);
    }
}
