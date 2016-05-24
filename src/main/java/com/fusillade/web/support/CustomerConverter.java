package com.fusillade.web.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.fusillade.domain.entity.Customer;
import com.fusillade.service.CustomerService;

public class CustomerConverter implements Converter<String, Customer>{

	@Autowired
	private CustomerService customerService;
	
	@Override
	public Customer convert(String customerId) {
		Customer customer;
		if ((customerId == null) || customerId.isEmpty()) {
			customer = new Customer();
		} else {
			int id = Integer.valueOf(customerId);
			if (id <= 0) {
				throw new IllegalArgumentException();
			}
			customer = customerService.findById(id);
		}
		return customer;
	}

}
