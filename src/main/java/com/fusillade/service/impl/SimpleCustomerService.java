package com.fusillade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fusillade.domain.entity.Customer;
import com.fusillade.repository.CustomerRepository;
import com.fusillade.service.CustomerService;

@Service
@Transactional
public class SimpleCustomerService implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer findById(int id) {
		return customerRepository.findById(id);
	}

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void delete(int id) {
		customerRepository.delete(id);
	}

	@Override
	public List<Customer> getAll() {
		return customerRepository.getAll();
	}

}
