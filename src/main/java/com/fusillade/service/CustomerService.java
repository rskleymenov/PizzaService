package com.fusillade.service;

import java.util.List;

import com.fusillade.domain.entity.Customer;

public interface CustomerService {
	
	Customer findById(int id);

	Customer save(Customer customer);

	void delete(int id);

	List<Customer> getAll();
}
