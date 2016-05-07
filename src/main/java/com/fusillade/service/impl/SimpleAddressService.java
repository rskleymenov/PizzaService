package com.fusillade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fusillade.domain.entity.Address;
import com.fusillade.repository.AddressRepository;
import com.fusillade.service.AddressService;

@Service
public class SimpleAddressService implements AddressService{

	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public Address findById(int id) {
		return addressRepository.findById(id);
	}

	@Override
	public Address save(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public void delete(int id) {
		addressRepository.delete(id);
	}

	@Override
	public List<Address> getAll() {	
		return addressRepository.getAll();
	}

}
