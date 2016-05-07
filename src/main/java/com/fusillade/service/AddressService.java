package com.fusillade.service;

import java.util.List;

import com.fusillade.domain.entity.Address;

public interface AddressService {
	
	Address findById(int id);

	Address save(Address address);

	void delete(int id);

	List<Address> getAll();

}
