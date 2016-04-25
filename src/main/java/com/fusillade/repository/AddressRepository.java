package com.fusillade.repository;

import java.util.List;

import com.fusillade.domain.entity.Address;

public interface AddressRepository {
	
	Address findById(int id);

	void create(Address address);

	void update(Address address);

	void delete(int id);

	List<Address> getAll();
}
