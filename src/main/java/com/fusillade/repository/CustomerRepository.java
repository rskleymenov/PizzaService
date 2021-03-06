package com.fusillade.repository;

import java.util.List;

import com.fusillade.domain.entity.Customer;

public interface CustomerRepository {
	
	Customer findById(int id);

	Customer save(Customer customer);

	void delete(int id);

	List<Customer> getAll();
}
