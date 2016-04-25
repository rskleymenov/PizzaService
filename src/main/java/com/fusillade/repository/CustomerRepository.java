package com.fusillade.repository;

import java.util.List;

import com.fusillade.domain.entity.Customer;

public interface CustomerRepository {
	
	Customer findById(int id);

	void create(Customer customer);

	void update(Customer customer);

	void delete(int id);

	List<Customer> getAll();
}
